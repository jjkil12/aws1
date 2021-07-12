package com.kbds.aws1.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// @Builder랑 사용할 시 ( json에 데이터를 담아서 사용할시에 생성자 문제로 인하여 사용해야함 ex> noArgs나 allargs등)
public class BooksUpdateRequestDto {

    private String genre;
    private String title;
    private String comment;
    private String book_name;

    @Builder
    public BooksUpdateRequestDto(String genre, String title, String comment, String book_name){

        this.title = title;
        this.genre = genre;
        this.comment = comment;
        this.book_name = book_name;
    }
}
