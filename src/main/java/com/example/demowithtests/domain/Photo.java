package com.example.demowithtests.domain;

import com.example.demowithtests.util.PhotoUrl;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    private LocalDate addDate = LocalDate.now();

    private String description;
    private String cameraType;

    @NotNull
    @PhotoUrl
    private String photoUrl;
}
