package haedalSpring2025_1.MyFirstProj.dto;


import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String username;
    private String password;
    private String name;
    private String bio;
}