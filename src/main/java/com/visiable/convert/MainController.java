package com.visiable.convert;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gml.primalspace.IndoorFeatures;
import com.visiable.convert.convertmodule.Convert;
import com.visiable.convert.file.service.FileUploadDownloadService;
import com.visiable.convert.jaxb.Jaxb;

@RestController
public class MainController {
	@Autowired
	private FileUploadDownloadService fileService;
	
	@CrossOrigin(value = "http://localhost:3000",allowCredentials = "true")
	@PostMapping("/IndoorGML")
	public @ResponseBody String uploadFile(@RequestParam("indoorGML") MultipartFile indoorGML,@RequestParam("coordinates") JSONObject coordinates) throws JAXBException {
		Path path = fileService.storeFile(indoorGML);
		IndoorFeatures indoorFeatures  = Jaxb.unmarshall(path);
		
		JSONObject indoorGMLCoordinates = Jaxb.getConvertingParamPos(indoorFeatures);
		
		JSONArray jsonArray = new JSONArray();

		jsonArray.put(new JSONObject().put("coordinate",coordinates.get("left") + ", " + indoorGMLCoordinates.get("left")));
		jsonArray.put(new JSONObject().put("coordinate",coordinates.get("right") + ", " + indoorGMLCoordinates.get("right")));
		jsonArray.put(new JSONObject().put("coordinate",coordinates.get("top") + ", " + indoorGMLCoordinates.get("top")));
		jsonArray.put(new JSONObject().put("coordinate",coordinates.get("bottom") + ", " + indoorGMLCoordinates.get("bottom")));
		
		return Convert.convertCoordinate(jsonArray, indoorFeatures).toString();
	}
	
	@CrossOrigin(value = "http://localhost:3000",allowCredentials = "true")
	@GetMapping("/IndoorGML")
	public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileService.loadFileAsResource("converted_indoorGML.gml");

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			System.out.println("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
