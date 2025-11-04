package com.example.mediaservice.web.controllers.impl;

import com.example.mediaservice.data.entities.Media;
import com.example.mediaservice.services.MediaService;
import com.example.mediaservice.services.impl.MediaServiceImpl;
import com.example.mediaservice.services.impl.S3Service;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(MediaControllerImpl.class)
class MediaControllerImplTest  {
    @Autowired
    private  MockMvc mockMvc;
    @MockitoBean
    private MediaService mediaService;
    @MockitoBean
    private S3Service s3Service;

    @Test
    void getAllMedias() throws Exception{
        when(mediaService.getAllMedias()).thenReturn(List.of(new Media("1223", "imagePath", "111")));
        mockMvc.perform(get("/api/v1/media"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.medias[0].id").value("1223"));

    }

    @Test
    void getMediaById() {
    }

    @Test
    void getMediaByProductId() {
    }

    @Test
    void createMedia() throws Exception{
        List<MultipartFile> multipartFiles = List.of(
                new MockMultipartFile("imagePath", "test2.jpg", "image/jpeg", "FAKE_IMAGE_CONTENT_1".getBytes()),
                new MockMultipartFile("imagePath", "avatar.png", "image/png", "FAKE_IMAGE_CONTENT_2".getBytes()),
                new MockMultipartFile("imagePath", "test1.jpg", "image/jpeg", "FAKE_IMAGE_CONTENT_1".getBytes()),
                new MockMultipartFile("imagePath", "avatar.png", "image/png", "FAKE_IMAGE_CONTENT_2".getBytes())
        );
        mockMvc.perform(multipart("/api/v1/media")
                .file((MockMultipartFile) multipartFiles.get(0))
                .file((MockMultipartFile) multipartFiles.get(1))
                .file((MockMultipartFile) multipartFiles.get(0))
                .file((MockMultipartFile) multipartFiles.get(1))
                .param("productId", "123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateMedia() {
    }

    @Test
    void deleteMedia() {
    }

    @Test
    void deleteByImagePath() {
    }
}