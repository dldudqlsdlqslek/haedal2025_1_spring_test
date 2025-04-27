package haedalSpring2025_1.MyFirstProj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private UserSimpleResponseDto user;
    private String imageData;
    private String content;
    private Long likeCount;
    private Boolean isLike;
    private String createdAt;
}