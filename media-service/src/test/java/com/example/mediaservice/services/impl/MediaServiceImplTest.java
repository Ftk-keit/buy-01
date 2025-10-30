package com.example.mediaservice.services.impl;

import com.example.mediaservice.data.entities.Media;
import com.example.mediaservice.data.repositories.MediaRepository;
import com.example.mediaservice.services.MediaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MediaServiceImplTest {
    @Mock
    MediaRepository mediaRepository;
    @InjectMocks
    MediaServiceImpl mediaServiceImpl;

    @Test
    void getAllMedias() {
        when(mediaRepository.findAll()).thenReturn(List.of(new Media("1223", "imagePath", "111")));
        List<Media> mediaList = mediaServiceImpl.getAllMedias();
        assertEquals(1, mediaList.size());
    }
}