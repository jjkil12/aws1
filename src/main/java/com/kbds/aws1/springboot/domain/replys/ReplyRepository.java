package com.kbds.aws1.springboot.domain.replys;

import com.kbds.aws1.springboot.domain.books.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    //Board 삭제시에 댓글들 삭제
    //@Modifying
    //@Query("delete from Reply r where r.books.book_id =:book_id")
    //void delete_book_id(int book_id);

    //게시물로 댓글 목록 가져오기
    //List<Reply> getRepliesByBoardOrderByRno(Books books);

}
