package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import java.util.Collection;

@Controller
public class IndexControl {

    @GetMapping("/")
    public String index(Model model) {
        Collection<Accident> list =  new AccidentService().findByAll();
        model.addAttribute("users", list);
        return "index";
    }
}
