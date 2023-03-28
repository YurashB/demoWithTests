package com.example.demowithtests.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
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

    @Enumerated(EnumType.STRING)
    private PhotoType photoType;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
}
