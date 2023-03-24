package com.example.demowithtests.domain;

import com.example.demowithtests.util.PhotoUrl;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime addDate = LocalDateTime.now();

    private String description;

    private String cameraType;

    @NotNull
    @PhotoUrl
    private String photoUrl;

    private boolean isDeleted = Boolean.FALSE;

    private boolean isMain = Boolean.TRUE;
}
