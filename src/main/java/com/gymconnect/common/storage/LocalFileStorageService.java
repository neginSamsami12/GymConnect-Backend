package com.gymconnect.common.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService {

    private static final String BASE_DIR = "uploads";

    @Override
    public String storeProfileImage(UUID userId, MultipartFile file) {
        return store(file, "profile-images", userId.toString());
    }

    @Override
    public String storeClassImage(UUID classId, MultipartFile file) {
        return store(file, "class-images", classId.toString());
    }

    private String store(MultipartFile file, String folder, String name) {
        try {
            Path dir = Paths.get(BASE_DIR, folder);
            Files.createDirectories(dir);

            String ext = getExtension(file.getOriginalFilename());
            Path path = dir.resolve(name + "." + ext);

            Files.write(path, file.getBytes());

            return "/" + BASE_DIR + "/" + folder + "/" + path.getFileName();
        } catch (Exception e) {
            throw new RuntimeException("File storage failed", e);
        }
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
