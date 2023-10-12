package com.pl_project.pl_project.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedMetric;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.pl_project.pl_project.dao.UserInformationDao;
import com.pl_project.pl_project.entities.User_Information;
import com.pl_project.pl_project.payload.FileResponse;
import com.pl_project.pl_project.payload.HistoryResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class FileServiceImpl implements FileService{
	@Autowired
	CompressionService compressionService;
	@Value("${project.image}")
	private String path;
	@Autowired 
	private UserInformationDao infodao;
	
	private static final String EXTERNAL_FILE_PATH = "C:/Users/Rishi/Desktop/PLACEMENT_PREP/pl_project/pl_project/";
	@Override
	public FileResponse uploadImage(String username, MultipartFile file) throws IOException {
		path=path+"/"+username;
		//File name
		String name=file.getOriginalFilename();
		
		//RandomName
		String randomID=UUID.randomUUID().toString();
		
		String fileName1= randomID.concat(name.substring(name.lastIndexOf('.')));
		//Fullpath
		String filePath=path+"/"+fileName1;
		
		//create folder if not created
		File f=new File(path);	
		if(!f.exists()) {
			f.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(filePath));
		String compressedFilePath=compressionService.compressFile(filePath, path,name);
		
		User_Information info=new User_Information(); 
		info.setUsername(username);info.setInputfileUrl(filePath);info.setCompressesfileUrl(compressedFilePath);
		infodao.save(info);	
		compressedFilePath="/file/download/"+compressedFilePath;
		FileResponse response=new FileResponse(name, "sucessfully_uploaded", compressedFilePath);
		return response;
	}
	
	@Override
	public void DownloadImage(HttpServletRequest request, HttpServletResponse response, String fileName)throws IOException {
		File file = new File(EXTERNAL_FILE_PATH + fileName);
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}
		else {
			response.sendError(-2, "file "+fileName+" doesn't exist");
		}
	}

	@Override
	public HistoryResponse listConversions(String username) throws IOException {
		HistoryResponse response=new HistoryResponse();
		List<String>l=new ArrayList<>();
		List<User_Information>list =null;
		
		return response;
	}
}
