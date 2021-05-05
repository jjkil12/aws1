package com.kbds.aws1.springboot.web.dto;

import com.kbds.aws1.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String modifiedDate;
    private String createdDate;


    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        //this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
