package haedalSpring2025_1.MyFirstProj.controller;

import haedalSpring2025_1.MyFirstProj.domain.User;
import haedalSpring2025_1.MyFirstProj.dto.UserDetailResponseDto;
import haedalSpring2025_1.MyFirstProj.dto.UserSimpleResponseDto;
import haedalSpring2025_1.MyFirstProj.dto.UserUpdateRequestDto;
import haedalSpring2025_1.MyFirstProj.service.AuthService;
import haedalSpring2025_1.MyFirstProj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserSimpleResponseDto>> getUsers(@RequestParam(required = false) String username, HttpServletRequest request) {
        User currentUser = authService.getCurrentUser(request);

        List<UserSimpleResponseDto> users;
        if (username == null || username.isEmpty()) {
            users = userService.getAllUsers(currentUser);
        } else {
            users = userService.getUserByUsername(currentUser, username);
        }

        return ResponseEntity.ok(users);
    }


    @PutMapping("/users/profile")
    public ResponseEntity<UserDetailResponseDto> updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto, HttpServletRequest request) {
        User currentUser = authService.getCurrentUser(request);
        UserDetailResponseDto updated = userService.updateUser(currentUser, userUpdateRequestDto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/users/{userId}/profile")
    public ResponseEntity<UserDetailResponseDto> getUserProfile(@PathVariable Long userId, HttpServletRequest request) {
        User currentUser = authService.getCurrentUser(request);

        UserDetailResponseDto userDetailResponseDto = userService.getUserDetail(currentUser, userId);

        return ResponseEntity.ok(userDetailResponseDto);
    }
}