package com.microservice.UserService.Service;

import com.microservice.UserService.DTO.UserProfile;
import com.microservice.UserService.entities.User;
import com.microservice.UserService.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // Autres méthodes de service ici

    public UserProfile getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Supposons que user.getRole() retourne un objet Role, convertissez-le
        List<String> roles = List.of(user.getRole().getName()); // Convertir le rôle en liste

        return new UserProfile(user.getUsername(), user.getEmail(), roles);
    }
}