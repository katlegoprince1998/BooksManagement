package com.qda.user_service.entitty;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "usertbl")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    @Embedded
    private Gender gender;
    @Embedded
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean userProfileIsActive;

}
