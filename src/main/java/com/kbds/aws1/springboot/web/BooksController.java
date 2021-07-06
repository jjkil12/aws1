package com.kbds.aws1.springboot.web;

import com.kbds.aws1.springboot.config.auth.LoginUser;
import com.kbds.aws1.springboot.config.auth.dto.SessionUser;
import com.kbds.aws1.springboot.domain.replys.Reply;
import com.kbds.aws1.springboot.domain.user.User;
import com.kbds.aws1.springboot.service.books.BooksService;
import com.kbds.aws1.springboot.web.dto.BooksListResponseDto;
import com.kbds.aws1.springboot.web.dto.BooksResponseDto;
import com.kbds.aws1.springboot.web.dto.PostsListResponseDto;
import com.kbds.aws1.springboot.web.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class BooksController {

    private final BooksService booksService;

    @GetMapping("/book")
    public String book() {
        //model.addAttribute("posts",postsService.findAllDesc());

       return "book";
    }

    @GetMapping("/book_list")
    public String book_list(Model model , @LoginUser SessionUser user) {
        List<BooksListResponseDto> list = booksService.findAllDesc();

        if(user != null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("userMail",user.getEmail());
        }

        model.addAttribute("books", list);


        return "book_list";
    }

    @GetMapping("/book_save")
    public String book_save(Model model, @LoginUser SessionUser user) {
        if (user == null) {
            return "index";
        } else {
            model.addAttribute("userMail", user.getEmail());
        }
        return "book_save";
    }

    @GetMapping("/book_detail/{book_id}")
    public String book_detail(@PathVariable Long book_id, Model model , @LoginUser SessionUser user) {
        if (user == null) {
            return "index";
        } else {
            model.addAttribute("userMail", user.getEmail());
        }
        BooksResponseDto booksResponseDto = booksService.findById(book_id);
        List<ReplyDto> reply = booksResponseDto.getReply().stream().map(ReplyDto::new).collect(Collectors.toList());
        Iterator iterator = reply.iterator();

        while (iterator.hasNext()) {
            ReplyDto gdto = (ReplyDto) iterator.next();
            System.out.println("댓글" + gdto.getText());
        }
        //System.out.println("댓글 : "+reply.get(0).getText());

        System.out.println("유저"+booksResponseDto.getUser());
        model.addAttribute("books",booksResponseDto);
        model.addAttribute("reply",reply);


        return "book_detail";
    }

    @GetMapping("/book_update/{book_id}")
    public String book_update(@PathVariable Long book_id, Model model , @LoginUser SessionUser user) {
        if (user == null) {
            return "index";
        } else {
            model.addAttribute("userMail", user.getEmail());
        }
        BooksResponseDto booksResponseDto = booksService.findById(book_id);
        model.addAttribute("books",booksResponseDto);


        return "book_update";
    }

    @GetMapping("/book_test")
    public String book_test() {
        return "book_test";
    }
}
