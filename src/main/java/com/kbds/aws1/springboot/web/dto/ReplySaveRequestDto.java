package com.kbds.aws1.springboot.web.dto;

import com.kbds.aws1.springboot.domain.books.Books;
import com.kbds.aws1.springboot.domain.replys.Reply;
import com.kbds.aws1.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplySaveRequestDto {
    private String user_mail;
    private String text;

    @Builder
    public ReplySaveRequestDto(String user_mail , String reply_text){
        this.user_mail = user_mail;
        this.text = reply_text;
    }

    public Reply toEntity(){
        return Reply.builder()
                .text(text)
                .build();
    }

}
