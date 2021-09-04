package com.kbds.aws1.springboot.web;

import com.kbds.aws1.springboot.service.books.BooksService;
import com.kbds.aws1.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BooksApiController {
    private final BooksService booksService;

    @PostMapping("/api/v1/{book_id}/reply")
    public Long reply_save(@PathVariable Long book_id, @RequestBody ReplySaveRequestDto replySaveRequestDto) {
        return booksService.reply_save(book_id, replySaveRequestDto);
    }

    @DeleteMapping("/api/v1/books/{book_id}/reply/{reply_id}")
    public Long reply_delete(@PathVariable Long book_id, @PathVariable int reply_id){
        booksService.reply_delete(book_id,reply_id);
        return  book_id;
    }

    @PutMapping("/api/v1/books/{book_id}/reply/{reply_id}")
    public Long reply_update(@PathVariable int reply_id,@PathVariable Long book_id,
                             @RequestBody ReplyUpdateRequestDto requestDto) {
        booksService.reply_update(reply_id,book_id, requestDto);

        return book_id;
    }

    @PostMapping("/api/v1/books")
    public Long save(@RequestBody BooksSaveRequestDto requestDto) {
        return booksService.save(requestDto);
    }

    @PutMapping("/api/v1/books/{book_id}")
    public Long update(@PathVariable Long book_id, @RequestBody BooksUpdateRequestDto requestDto) {
        return booksService.update(book_id, requestDto);
    }


    @DeleteMapping("/api/v1/books/{book_id}")
    public Long delete(@PathVariable Long book_id) {
        booksService.delete(book_id);
        return book_id;
    }

    @GetMapping("/api/v1/books/{book_id}")
    public BooksResponseDto findById(@PathVariable Long book_id) {
        return booksService.findById(book_id);
    }

    @GetMapping("/api/v1/books/list")
    public List<BooksListResponseDto> findAll() {
        return booksService.findAllDesc();
    }
}
