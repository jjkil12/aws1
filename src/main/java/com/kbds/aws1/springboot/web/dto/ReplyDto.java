package com.kbds.aws1.springboot.web.dto;

import com.kbds.aws1.springboot.domain.books.Books;
import com.kbds.aws1.springboot.domain.replys.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
public class ReplyDto {
    private int rno;
    private String text;
    private String reply_user;
    private Long book_id;


    public ReplyDto(Reply reply){
        this.rno = reply.getRno();
        this.text = reply.getText();
        this.reply_user = reply.getReply_user();
        this.book_id = reply.getBooks().getBook_id();
    }
    /*
    @Builder
    public ReplyDto(int rno, String text , String reply_user, Long book_id){
        this.rno = rno;
        this.text = text;
        this.reply_user = reply_user;
        this.book_id = book_id;
    }

    public Reply toEntity() {
        return Reply.builder()
                .rno(rno)
                .text(text)
                .reply_user(reply_user)
                .books(Books.builder().book_id(book_id).build())
                .build();
    }

     */


}
