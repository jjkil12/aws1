package com.kbds.aws1.springboot.domain.books;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kbds.aws1.springboot.domain.BaseTimeEntity;
import com.kbds.aws1.springboot.domain.replys.Reply;
import com.kbds.aws1.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Books extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    @Column(length = 500, nullable = false)
    private String genre;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;


    //@Column(length = 500, nullable = false)
    //private String user;

    @Column(length = 500, nullable = false)
    private String book_name;

    @ManyToOne
    @JoinColumn(name="id")
    private User user;

    @OneToMany(mappedBy = "books", fetch = FetchType.EAGER)//mappedBy 연관관계의 주인이 아니다 (난 FK가 아님) db에 컬럼 만들지 말라는 뜻
    @JsonIgnoreProperties({"books"})
    private List<Reply> reply;

    @Builder
    public Books(Long book_id,String genre, String title, String comment,User user, String book_name) {
        this.book_id = book_id;
        this.genre = genre;
        this.title = title;
        this.comment = comment;
        this.user = user;
        this.book_name = book_name;
    }

    public void save(String genre, String title, String comment, String book_name,User user){
        this.genre = genre;
        this.title = title;
        this.comment = comment;
        this.user = user;
        this.book_name = book_name;
    }

    public void update(String genre, String title, String comment, String book_name) {
        this.genre = genre;
        this.title = title;
        this.comment = comment;
        this.book_name = book_name;
    }
}
