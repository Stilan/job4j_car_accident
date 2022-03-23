package ru.job4j.accident.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {

    @GetMapping("/")
    public String index(Model model) {
        List<String> list = new ArrayList<>();
        list.add("Petr Arsentev");
        list.add("Pob");
        model.addAttribute("users", list);
        return "index";
    }
}
