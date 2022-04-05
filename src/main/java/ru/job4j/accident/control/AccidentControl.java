package ru.job4j.accident.control;

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

import javax.servlet.http.HttpServletRequest;


@Controller
public class AccidentControl {


    private final AccidentHibernate accidentHibernate;

    public AccidentControl(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", accidentHibernate.findByAccidentId(id));
        model.addAttribute("rules", accidentHibernate.findByRuleAll());
        model.addAttribute("types", accidentHibernate.findByTypeAll());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        System.out.println(accidentHibernate.findByAccidentTypeId(id));
        accident.setAccidentType(accidentHibernate.findByAccidentTypeId(id));
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(accidentHibernate.findRulesByIds(ids));
        accidentHibernate.save(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rules", accidentHibernate.findByRuleAll());
        model.addAttribute("types", accidentHibernate.findByTypeAll());
        return "accident/create";
    }


}