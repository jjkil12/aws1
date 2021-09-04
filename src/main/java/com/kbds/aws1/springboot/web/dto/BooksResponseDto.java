package com.kbds.aws1.springboot.web.dto;

import com.kbds.aws1.springboot.domain.books.Books;
import com.kbds.aws1.springboot.domain.replys.Reply;
import com.kbds.aws1.springboot.domain.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class BooksResponseDto {
    
    private Long book_id;
    private String genre;
    private String title;
    private String comment;
    private String user;
    private String book_name;
    private List<Reply> reply;

    public  BooksResponseDto(Books entity){
        this.book_id = entity.getBook_id();
        this.title = entity.getTitle();
        this.genre = entity.getGenre();
        this.comment = entity.getComment();
        this.user = entity.getUser().getEmail();
        this.reply = entity.getReply();
        this.book_name = entity.getBook_name();
    }

}
