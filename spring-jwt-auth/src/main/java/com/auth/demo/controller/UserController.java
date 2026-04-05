package com.auth.demo.controller;

import com.auth.demo.dto.ApiResponse;
import com.auth.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    // GET /api/user/profile — harus login (ada JWT valid)
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Map<String, Object> profileData = Map.of(
                "id", user.getId(),
                "fullName", user.getFullName(),
                "email", user.getEmail(),
                "role", user.getRole()
        );
        return ResponseEntity.ok(ApiResponse.ok("Profile berhasil diambil", profileData));
    }

    // GET /api/admin/dashboard — hanya ADMIN
    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> adminDashboard() {
        return ResponseEntity.ok(ApiResponse.ok("Selamat datang di Admin Dashboard!", null));
    }
}
