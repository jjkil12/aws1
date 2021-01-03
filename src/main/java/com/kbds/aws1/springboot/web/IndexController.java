package com.kbds.aws1.springboot.web;

import com.kbds.aws1.springboot.config.auth.LoginUser;
import com.kbds.aws1.springboot.config.auth.dto.SessionUser;
import com.kbds.aws1.springboot.service.posts.PostsService;
import com.kbds.aws1.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
    //public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if (user == null) {
            return "index";
        }
        else {
            model.addAttribute("userEmail", user.getEmail());
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if (user == null) {
            return "index";
        }
        //model.addAttribute("userEmail", user.getEmail());
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        if(dto.getAuthor().equals(user.getEmail())) {
            model.addAttribute("pageAuth", false);
        }
        else {
            model.addAttribute("pageAuth", true);
        }


        return "posts-update";
    }


}
