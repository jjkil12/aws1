package com.kbds.aws1.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BooksController {

    @GetMapping("/book")
    public String book() { return "book"; }
}
