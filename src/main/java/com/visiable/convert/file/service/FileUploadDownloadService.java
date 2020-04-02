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
			throw new FileUploadException("������ ���ε��� ���丮�� �������� ���߽��ϴ�.", e);
		}
	}

	public Path storeFile(MultipartFile file) {
		// �������� ���� ������ ���� ����
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// ���ϸ� ������ ���ڰ� �ִ��� Ȯ���Ѵ�.
			if (fileName.contains(".."))
				throw new FileUploadException("���ϸ� ������ ���ڰ� ���ԵǾ� �ֽ��ϴ�. " + fileName);
			
			//��� �߰� ���ִ� �ڵ�
			//UploadFileUtils.makeDir(fileLocation+ "/" + addtionalPath);
			
			Path targetLocation = this.fileLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return this.fileLocation.resolve(fileName).normalize();
		} catch (Exception e) {
			throw new FileUploadException("[" + fileName + "] ���� ���ε忡 �����Ͽ����ϴ�. �ٽ� �õ��Ͻʽÿ�.", e);
		}
	}
	
	 public Resource loadFileAsResource(String fileName) {
	        try {
	            Path filePath = this.fileLocation.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            
	            if(resource.exists()) {
	                return resource;
	            }else {
	                throw new FileDownloadException(fileName + " ������ ã�� �� �����ϴ�.");
	            }
	        }catch(MalformedURLException e) {
	            throw new FileDownloadException(fileName + " ������ ã�� �� �����ϴ�.", e);
	        }
	    }

}
