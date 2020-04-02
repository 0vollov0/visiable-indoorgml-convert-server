package com.visiable.convert;

import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
