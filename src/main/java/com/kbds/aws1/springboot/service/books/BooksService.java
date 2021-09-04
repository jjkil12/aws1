package com.kbds.aws1.springboot.service.books;

import com.kbds.aws1.springboot.domain.books.Books;
import com.kbds.aws1.springboot.domain.books.BooksRepository;
import com.kbds.aws1.springboot.domain.replys.Reply;
import com.kbds.aws1.springboot.domain.replys.ReplyRepository;
import com.kbds.aws1.springboot.domain.user.User;
import com.kbds.aws1.springboot.domain.user.UserRepository;
import com.kbds.aws1.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BooksService {
    private final BooksRepository booksRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    @Transactional
    public void reply_update(int reply_id, Long book_id, ReplyUpdateRequestDto requestDto){
        Reply reply = replyRepository.findById(reply_id).orElseThrow(()->{
            return new IllegalArgumentException("댓글찾기 실패 : 댓글 id를 찾을수 없습니다");
        });

        User user = userRepository.findByEmail(requestDto.getUser_mail()).orElseThrow(()-> {
            return new IllegalArgumentException("댓글쓰기 실패 : 유저 id를 찾을수 없음");
        });

        Books book = booksRepository.findById(book_id).orElseThrow(()-> {
            return new IllegalArgumentException("댓글찾기 실패 : 게시글 id를 찾을수 없음");
        });
        System.out.println("-----파라미터 확인-----");
        System.out.println("수정내용 : "+requestDto.getUpdate_text());
        System.out.println("book : "+book);
        System.out.println("user : "+user);
        System.out.println("댓글 번호 : "+reply_id);
        reply.update(requestDto.getUpdate_text(),book,user, reply_id);

    }
    @Transactional
    public  void reply_delete(Long book_id, int reply_id){
        Books books = booksRepository.findById(book_id).orElseThrow(()-> {
            return new IllegalArgumentException("댓글찾기 실패 : 게시글 id를 찾을수 없음");
        });

//        Reply reply = replyRepository.findById(reply_id).orElseThrow(() -> {
//            return new IllegalArgumentException("댓글찾기 실패 : 댓글 id를 찾을수 없음");
//        });
        replyRepository.deleteById(reply_id);

    }
    @Transactional
    public Long reply_save(Long book_id, ReplySaveRequestDto replySaveRequestDto){
        User user = userRepository.findByEmail(replySaveRequestDto.getUser_mail()).orElseThrow(()-> {
            return new IllegalArgumentException("댓글쓰기 실패 : 유저 id를 찾을수 없음");
        });

        Books books = booksRepository.findById(book_id).orElseThrow(()-> {
            return new IllegalArgumentException("댓글쓰기 실패 : 게시글 id를 찾을수 없음");
        });

        System.out.println("book_id : " + books.getBook_id());

        Reply reply = new Reply();
        reply.save(replySaveRequestDto.getText(), books, user);


        /*
        ReplySaveRequestDto requestDto = new ReplySaveRequestDto(books,user, replySaveRequestDto.getText());

         */
        return replyRepository.save(reply).getBooks().getBook_id();

    }

    @Transactional
    public Long save(BooksSaveRequestDto booksSaveRequestDto){
        User user = userRepository.findByEmail(booksSaveRequestDto.getUser_email()).orElseThrow(()-> {
            return new IllegalArgumentException("게시글 쓰기 실패 : 유저 email을 찾을수 없음");
        });

        Books books = new Books();
        books.save(booksSaveRequestDto.getGenre(), booksSaveRequestDto.getTitle(),
                booksSaveRequestDto.getComment(), booksSaveRequestDto.getBook_name(), user );

        return booksRepository.save(books).getBook_id();

    }
    @Transactional
    public Long update(Long book_id, BooksUpdateRequestDto requestDto) {
        Books books = booksRepository.findById(book_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. book_id=" + book_id));

        books.update(requestDto.getGenre(),requestDto.getTitle()
                , requestDto.getComment(), requestDto.getBook_name());

        return book_id;
    }

    @Transactional
    public void delete (Long book_id) {
        Books books = booksRepository.findById(book_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + book_id));
        List<Reply> reply = books.getReply();
        Iterator iterator = reply.iterator();
        while (iterator.hasNext()) {
            Reply reply1 = (Reply) iterator.next();
            replyRepository.delete(reply1);
        }

        booksRepository.delete(books);
    }

    @Transactional(readOnly = true)
    public BooksResponseDto findById(Long book_id) {
        Books entity = booksRepository.findById(book_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + book_id));

        return new BooksResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BooksListResponseDto> findAllDesc() {
        return booksRepository.findAllDesc().stream()
                .map(BooksListResponseDto::new)
                .collect(Collectors.toList());
    }
}
