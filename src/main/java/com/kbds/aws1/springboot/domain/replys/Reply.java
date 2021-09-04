package com.kbds.aws1.springboot.domain.replys;

import com.kbds.aws1.springboot.domain.BaseTimeEntity;
import com.kbds.aws1.springboot.domain.books.Books;
import com.kbds.aws1.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno; //시퀀스, auto_increment

    private String text;

    private String reply_user;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Books books;

    @ManyToOne
    @JoinColumn(name="id")
    private User user;

    public void save(String text,  Books books,User user){
        this.text = text;
        this.books = books;
        this.reply_user = user.getEmail();
        this.user = user;
    }

    public void update(String text,  Books books,User user , int rno){
        this.text = text;
        this.books = books;
        this.reply_user = user.getEmail();
        this.user = user;
        this.rno = rno;
    }
    
    @Builder
    public Reply(int rno, String text, String reply_user, Books books,User user){
        this.rno = rno;
        this.text = text;
        this.reply_user = reply_user;
        this.user = user;
        this.books = books;
    }

}
