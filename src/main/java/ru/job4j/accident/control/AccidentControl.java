package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.service.AccidentJdbcTemplateService;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentServiceHibernate;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AccidentControl {

    @Autowired
    private final AccidentServiceHibernate accidents;

    public AccidentControl(AccidentServiceHibernate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", accidents.findByAccidentId(id));
        model.addAttribute("rules", accidents.findByRuleAll());
        model.addAttribute("types", accidents.findByTypeAll());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        System.out.println(accidents.findByAccidentTypeId(id));
        accident.setAccidentType(accidents.findByAccidentTypeId(id));
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(accidents.findRulesByIds(ids));
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rules", accidents.findByRuleAll());
        model.addAttribute("types", accidents.findByTypeAll());
        return "accident/create";
    }


}