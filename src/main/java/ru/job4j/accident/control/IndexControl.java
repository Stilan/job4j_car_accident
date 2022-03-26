package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import java.util.Collection;

@Controller
public class IndexControl {

    @Autowired
    private  AccidentService accidentService;

    @GetMapping("/")
    public String index(Model model) {
        Collection<Accident> list =  accidentService.findByAll();
        model.addAttribute("users", list);
        return "index";
    }
}
