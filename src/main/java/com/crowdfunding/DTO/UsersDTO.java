package com.crowdfunding.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class UsersDTO {
    private Long iduser;
    private String name;
    private String occupation;
    private String email;
    private String passwordhash;
    private String avatarfilename;
    private String role;
    private String token;
    private LocalDateTime create_at;
    private LocalDateTime update_at;

}
