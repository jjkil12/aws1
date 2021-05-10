package com.kbds.aws1.springboot.web;

import ch.qos.logback.core.CoreConstants;
import com.kbds.aws1.springboot.config.auth.LoginUser;
import com.kbds.aws1.springboot.config.auth.dto.SessionUser;
import com.kbds.aws1.springboot.service.posts.PostsService;
import com.kbds.aws1.springboot.web.dto.PostsListResponseDto;
import com.kbds.aws1.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
    //public String index(Model model) {

        List<PostsListResponseDto> list1 = postsService.findAllDesc();

        Iterator iterator = list1.iterator();

        while (iterator.hasNext()) {
            PostsListResponseDto strCol = (PostsListResponseDto) iterator.next();
            if("[COP]".equals(strCol.getTitle().substring(0,5))) {
                strCol.setColor("#6f42c1");
            }
            else {
                strCol.setColor("#007bff");
            }
        }
        model.addAttribute("posts", list1);
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        //Model model1 = (Model) model.getAttribute("posts");

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
/*
            if(model1.getAttribute("title").toString().toLowerCase().charAt(0) == '[') {
                model.addAttribute("color", "#e83e8c");
            }
            else {
                model.addAttribute("color", "#007bff");
            }

 */
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
