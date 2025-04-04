package haedalSpring2025_1.MyFirstProj.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSimpleResponseDto {
    private Long id;
    private String username;
    private String name;
    private String imageUrl;
    private Boolean isFollowing;
}