package ch.fhnw.acrm.controller;

/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/book")
public class BookController {

    @GetMapping
    public String getBookView(){
        return "acrm/book.html";
    }

    @GetMapping("/create")
    public String getCustomerCreateView(){
        return "../acrm/bookCreate.html";
    }

    @GetMapping("/edit")
    public String getCustomerEditView(){
        return "../acrm/bookEdit.html";
    }
}
