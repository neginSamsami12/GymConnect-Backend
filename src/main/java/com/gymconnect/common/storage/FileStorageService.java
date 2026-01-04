package com.gymconnect.common.storage;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileStorageService {

    String storeProfileImage(UUID ownerId, MultipartFile file);

    String storeClassImage(UUID classId, MultipartFile file);

}
