package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import java.util.Collection;

@Controller
public class IndexControl {

    @GetMapping("/")
    public String index(Model model) {
        AccidentMem.instOf().save(new Accident("Вася", "Дтп", "Ленина 12"));
        Collection<Accident> list =  AccidentMem.instOf().findByAll();
        model.addAttribute("users", list);
        return "index";
    }
}
