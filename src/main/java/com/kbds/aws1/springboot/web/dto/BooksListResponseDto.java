package com.kbds.aws1.springboot.web.dto;

import com.kbds.aws1.springboot.domain.books.Books;
import lombok.Getter;

@Getter
public class BooksListResponseDto {
    private Long book_id;
    private String genre;
    private String title;
    private String comment;
    private Long user;
    private String book_name;

    public BooksListResponseDto(Books entity){
        this.book_id = entity.getBook_id();
        this.genre = entity.getGenre();
        this.title = entity.getTitle();
        this.comment = entity.getComment();
        this.user = entity.getUser().getId();
        this.book_name = entity.getBook_name();
    }
}
