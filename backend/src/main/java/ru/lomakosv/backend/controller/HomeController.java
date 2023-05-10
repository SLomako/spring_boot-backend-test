package ru.lomakosv.backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.lomakosv.backend.domain.Author;
import ru.lomakosv.backend.domain.Book;
import ru.lomakosv.backend.exception.InvalidRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping
@Controller
public class HomeController {

    private Map<String, Author> author = Map.of(
            "1", Author.builder().firstName("Dima").lastName("Дима").id("1").build(),
            "2", Author.builder().firstName("noDima").lastName("неДима").id("2").build()
    );

    private Map<String, Book> book = Map.of(
            "1", Book.builder().author("Dima Дима").id("1").build(),
            "2", Book.builder().author("noDima неДима").id("2").build()
    );

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @ResponseBody
    @PostMapping("/book")
    public Book doBook(@RequestBody Author author) {
        if (author.getId().equals("1")) {
            return Book.builder().author(author.getFirstName() + " " + author.getLastName()).id(author.getId()).build();
        }
        if (author.getId().equals("2")) {
            return Book.builder().author(author.getFirstName() + " " + author.getLastName()).id(author.getId()).build();
        } else {
            throw new InvalidRequestException();
        }
    }

    @ResponseBody
    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        List<Author> allAuthors = new ArrayList<>();
        for (Map.Entry<String, Author> entry : author.entrySet()) {
            allAuthors.add(entry.getValue());
        }
        return author.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }
}

