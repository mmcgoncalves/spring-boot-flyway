package br.com.flyway.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {	
	
	@Value("${upload.path}")
    private String path;
		
	private final Path rootLocation = Paths.get("upload-dir");
	 
	public void storeFile(MultipartFile file) throws IOException {
        String checksumSHA256 = DigestUtils.sha256Hex(
        		file.getBytes()
        );
        
        System.out.println(path);        
        
        System.out.println("checksumSHA256 : " + checksumSHA256);  
        
        /*
         String filepath = Paths.get(path, "8c7dd922ad47494fc02c388e12c00eac.pdf").toString();
    	
        String checksumSHA256_outro = DigestUtils.sha256Hex(
        		new FileInputStream(filepath)
        );        
        
        System.out.println("checksumSHA256_outro : " + checksumSHA256_outro);*/        

        String checksumMD5 = DigestUtils.md5Hex(file.getBytes());
        System.out.println("checksumMD5 : " + checksumMD5);
        
        
		System.out.println(FilenameUtils.getExtension(file.getOriginalFilename()));
		System.out.println(FilenameUtils.getBaseName(file.getOriginalFilename()));
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		
		String fileName = checksumMD5 + "." + FilenameUtils.getExtension(file.getOriginalFilename());
		//Files.copy(file.getInputStream(), this.rootLocation.resolve(checksumMD5 + ".pdf"), StandardCopyOption.REPLACE_EXISTING);
		
		Files.copy(file.getInputStream(), Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
		   

	}

	
	public Resource loadFile() throws MalformedURLException {
		Path filepath = Paths.get(path, "8c7dd922ad47494fc02c388e12c00eac.pdf");	    
	    Resource resource = new UrlResource(filepath.toUri());
		
		
		if (resource.exists() || resource.isReadable()) {
			return resource;
		} else {
			throw new RuntimeException("FAIL!");
		}

	}

}
