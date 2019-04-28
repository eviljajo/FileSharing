package com.filesharing.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.filesharing.exceptions.FileStorageException;
import com.filesharing.exceptions.MyFileNotFoundException;
import com.filesharing.model.DBfile;
import com.filesharing.repositories.DBfileRepository;

@Service
public class DBfileStorageService {
	
	@Autowired
	private DBfileRepository dBfileRepository;
	
	public DBfile storeFile(MultipartFile file) {
     
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
               if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBfile dbFile = new DBfile(fileName, file.getContentType(), file.getBytes());

            return dBfileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
	

    public DBfile getFile(String fileId) {
        return dBfileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
