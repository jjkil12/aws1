package com.kbds.aws1.springboot.web.dto;

import com.kbds.aws1.springboot.domain.books.Books;
import com.kbds.aws1.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BooksSaveRequestDto {
    private String genre;
    private String title;
    private String comment;
    private String user_email;
    private String book_name;

    @Builder
    public BooksSaveRequestDto(String genre, String title, String comment, String user_email, String book_name){
        this.genre = genre;
        this.title = title;
        this.comment = comment;
        this.user_email = user_email;
        this.book_name = book_name;
    }


    public Books toEntity(){
        return Books.builder()
                .genre(genre)
                .title(title)
                .comment(comment)
                .book_name(book_name)
                .build();
    }
}
