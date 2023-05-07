package com.example.demowithtests.dto;

import com.example.demowithtests.domain.PhotoType;

import java.time.LocalDateTime;

public class PhotoDto {
    public LocalDateTime addDate = LocalDateTime.now();
    public PhotoType photoType;
    public byte[] image;
}
