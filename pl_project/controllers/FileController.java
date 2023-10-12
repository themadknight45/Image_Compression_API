package com.pl_project.pl_project.controllers;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpHeaders;

import org.apache.coyote.http11.Http11InputBuffer;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Couchbase;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pl_project.pl_project.payload.FileResponse;
import com.pl_project.pl_project.payload.HistoryResponse;
import com.pl_project.pl_project.services.FileService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

@RestController
@RequestMapping("/file")
public class FileController {
	@Value("${project.image}")
	private String path;
	@Value("${project.token}")
	private int Token;
	
	@Autowired
	private FileService fileService;
	
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse>fileUploadController(
		@RequestParam("token")int token,@RequestParam("image")MultipartFile image,@RequestParam("username")String username){
		FileResponse fileResponse=new FileResponse();
		if(token!=Token) {
			fileResponse.setMessase("invalid token");
			return new ResponseEntity<>(fileResponse,HttpStatus.FORBIDDEN);
		}
		try {
			fileResponse = fileService.uploadImage(username, image);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(fileResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(fileResponse,HttpStatus.OK);
	}
	
	@GetMapping("/download/{filetype}/{username}/{fileName:.+}")
	public void fileDownloadController(
			HttpServletRequest request, HttpServletResponse response,@PathVariable String filetype,@PathVariable String username,
			@PathVariable("fileName") String fileName) throws IOException {
			fileService.DownloadImage(request,response,filetype+"/"+username+"/"+fileName);
	}
	
	@GetMapping("/list")
	public ResponseEntity<HistoryResponse>List_Conversions(
			@RequestParam("token")int token,@RequestParam("username")String username){
			HistoryResponse response=new HistoryResponse();
			if(token!=Token) {
				response.setResponse_msg("invalid_token");
				return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
			}
			try {
				response = fileService.listConversions(username);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	
}
