package com.gymconnect.classes.service;

import com.gymconnect.classes.dto.ClassCreateRequest;
import com.gymconnect.classes.dto.ClassResponse;
import com.gymconnect.classes.entity.Class;
import com.gymconnect.classes.mapper.ClassMapper;
import com.gymconnect.classes.repository.ClassRepository;
import com.gymconnect.common.response.ApiResponse;
import com.gymconnect.common.storage.FileStorageService;
import com.gymconnect.user.entity.User;
import com.gymconnect.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService {

    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final ClassMapper classMapper;
    private final FileStorageService fileStorageService;

    @Override
    @Transactional
    public ApiResponse create(
            ClassCreateRequest request,
            MultipartFile image
    ) {

        User trainer = userRepository.findById(request.trainerId())
                .orElseThrow(() -> new IllegalArgumentException("Trainer not found"));

        Class entity = classMapper.toEntity(request);
        entity.setId(UUID.randomUUID());
        entity.setTrainer(trainer);

        if (image != null && !image.isEmpty()) {
            String imageUrl = fileStorageService.storeClassImage(entity.getId(), image);
            entity.setImageUrl(imageUrl);
        }

        classRepository.save(entity);

        return ApiResponse.success(null);
    }

    @Override
    @Transactional
    public ApiResponse findAll(UUID trainerId) {

        List<Class> entities = (trainerId != null)
                ? classRepository.findAllByTrainer_Id(trainerId)
                : classRepository.findAll();

        List<ClassResponse> response = classMapper.toResponseList(entities);
        return ApiResponse.success(response);
    }
}
