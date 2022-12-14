package com.javachinna.service;
import com.javachinna.fileUpload.FileStorageException;
import com.javachinna.model.DatabaseFile;
import com.javachinna.model.User;
import com.javachinna.repo.DatabaseFileRepository;
import com.javachinna.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class DatabaseFileService {

    @Autowired
    private DatabaseFileRepository dbFileRepository;
    @Autowired
    private UserRepository iUserRepo;

    public DatabaseFile storeFile(MultipartFile file, Long id) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        User user = iUserRepo.findById(id).orElse(null);

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());
            dbFile.setUser(user);
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatabaseFile getFile(String fileId) throws FileNotFoundException {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
