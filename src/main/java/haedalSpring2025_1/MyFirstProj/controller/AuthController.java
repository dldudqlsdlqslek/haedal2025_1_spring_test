package haedalSpring2025_1.MyFirstProj.controller;

import haedalSpring2025_1.MyFirstProj.domain.User;
import haedalSpring2025_1.MyFirstProj.dto.LoginRequestDto;
import haedalSpring2025_1.MyFirstProj.dto.UserRegistrationRequestDto;
import haedalSpring2025_1.MyFirstProj.dto.UserSimpleResponseDto;
import haedalSpring2025_1.MyFirstProj.service.AuthService;
import haedalSpring2025_1.MyFirstProj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserSimpleResponseDto> registerUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = new User(
                userRegistrationRequestDto.getUsername(),
                userRegistrationRequestDto.getPassword(),
                userRegistrationRequestDto.getName()
        );
        UserSimpleResponseDto savedUser = userService.saveUser(user);

        return ResponseEntity.ok(savedUser);
    }


    @PostMapping("/auth/login")
    public ResponseEntity<UserSimpleResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        UserSimpleResponseDto userSimpleResponseDto = authService.login(loginRequestDto, request);
        return ResponseEntity.ok(userSimpleResponseDto);
    }


    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/auth/me")
    public ResponseEntity<UserSimpleResponseDto> me(HttpServletRequest request) {
        User currentUser = authService.getCurrentUser(request);

        UserSimpleResponseDto userSimpleResponseDto = userService.convertUserToSimpleDto(currentUser, currentUser);
        return ResponseEntity.ok(userSimpleResponseDto);
    }
}