package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentServiceHibernate;

import java.util.Collection;

@Controller
public class IndexControl {

    @Autowired
    private final AccidentServiceHibernate accidents;

    public IndexControl(AccidentServiceHibernate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", accidents.getAll());

        return "index";
    }
}
