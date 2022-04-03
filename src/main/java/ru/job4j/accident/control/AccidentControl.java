package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.service.AccidentJdbcTemplateService;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AccidentControl {

    private final AccidentJdbcTemplateService accidentJdbcTemplate;

    public AccidentControl(AccidentJdbcTemplateService accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("rules", accidentJdbcTemplate.findByRuleAll());
        model.addAttribute("user", accidentJdbcTemplate.findByAccidentId(id));
        model.addAttribute("types", accidentJdbcTemplate.findByTypeAll());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        accident.setAccidentType(accidentJdbcTemplate.findByAccidentTypeId(id));
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(accidentJdbcTemplate.findRulesByIds(ids));
        accidentJdbcTemplate.save(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rules", accidentJdbcTemplate.findByRuleAll());
        model.addAttribute("types", accidentJdbcTemplate.findByTypeAll());
        return "accident/create";
    }


}