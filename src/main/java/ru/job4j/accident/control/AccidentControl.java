package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;


@Controller
public class AccidentControl {
    private final AccidentService accidents;

    public AccidentControl(AccidentService accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", accidents.findById(id));
        model.addAttribute("types", accidents.findByTypeAll());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id) {
        accident.setAccidentType(accidents.findByTypeId(id));
        System.out.println(accident.getName());
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.findByTypeAll());
        return "accident/create";
    }


}