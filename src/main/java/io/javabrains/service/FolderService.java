package io.javabrains.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import io.javabrains.entity.Folder;

@Service
public class FolderService {
    
    public List<Folder> getDefaultFolders(String userId){
        return Arrays.asList(
            new Folder(userId, "Inbox", "blue"),
            new Folder(userId, "Sent Items", "green"),
            new Folder(userId, "Important", "red")
        );
    }
}
