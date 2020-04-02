package com.visiable.convert.file.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.visiable.convert.exception.FileDownloadException;
import com.visiable.convert.exception.FileUploadException;



@Service
public class FileUploadDownloadService {
	private final Path fileLocation;

	@Autowired
	public FileUploadDownloadService() {
		this.fileLocation = Paths.get("uploadedIndoorGML").toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileLocation);
		} catch (Exception e) {
			throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
		}
	}

	public Path storeFile(MultipartFile file) {
		// 유저네임 폴더 생성후 파일 저장
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// 파일명에 부적합 문자가 있는지 확인한다.
			if (fileName.contains(".."))
				throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
			
			//경로 추가 해주는 코드
			//UploadFileUtils.makeDir(fileLocation+ "/" + addtionalPath);
			
			Path targetLocation = this.fileLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return this.fileLocation.resolve(fileName).normalize();
		} catch (Exception e) {
			throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.", e);
		}
	}
	
	 public Resource loadFileAsResource(String fileName) {
	        try {
	            Path filePath = this.fileLocation.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            
	            if(resource.exists()) {
	                return resource;
	            }else {
	                throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
	            }
	        }catch(MalformedURLException e) {
	            throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
	        }
	    }

}
