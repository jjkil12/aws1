package com.kbds.aws1.springboot.web.dto;

import com.kbds.aws1.springboot.domain.books.Books;
import com.kbds.aws1.springboot.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyUpdateRequestDto {
    private String update_text;
    //private Long book_id;
    private String user_mail;
}
