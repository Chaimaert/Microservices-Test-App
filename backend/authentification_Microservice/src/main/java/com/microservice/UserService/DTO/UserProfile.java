package com.microservice.UserService.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class UserProfile {
    private String username;
    private String email;
    private List<String> roles;
}
