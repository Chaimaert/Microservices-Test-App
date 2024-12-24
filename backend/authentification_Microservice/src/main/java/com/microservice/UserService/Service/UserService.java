package com.microservice.UserService.Service;
import com.microservice.UserService.DTO.UserProfile;
import com.microservice.UserService.entities.User;
import com.microservice.UserService.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserProfile getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfile(user.getUsername(), user.getEmail(), user.getRole());
    }
}
