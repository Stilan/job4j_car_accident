package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentServiceSpringDate;

@Controller
public class IndexControl {

    @Autowired
    private final AccidentServiceSpringDate accidents;

    public IndexControl(AccidentServiceSpringDate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", accidents.getAccidentsBy());
        return "index";
    }
}
