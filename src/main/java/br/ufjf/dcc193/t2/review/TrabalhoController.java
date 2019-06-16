package br.ufjf.dcc193.t2.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/trabalho")
public class TrabalhoController {
    @Autowired
    TrabalhoRepository trabalhoRepo;

    @RequestMapping({"", "/", "/index"})
    public ModelAndView list() {
        List<Trabalho> trabalhos = trabalhoRepo.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("trabalho-list");
        mv.addObject("trabalhos", trabalhos);
        return mv;
    }

    // @GetMapping("/create")
    // public ModelAndView create() {
    //     ModelAndView mv = new ModelAndView();
    //     mv.setViewName("trabalho-create");
    //     mv.addObject("trabalho", new Trabalho());
    //     return mv;
    // }

    // @PostMapping("/create")
    // public String create(Trabalho sede) {
    //     trabalhoRepo.save(sede);

    //     return "redirect:/trabalho";
    // }

    @RequestMapping("/{id}")
    public ModelAndView read(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("trabalho-read");
        mv.addObject("trabalho", trabalhoRepo.findById(id).get());

        return mv;
    }

    // @GetMapping("/{id}/update")
    // public ModelAndView update(@PathVariable Long id) {
    //     ModelAndView mv = new ModelAndView();
    //     mv.setViewName("trabalho-update");
    //     Trabalho trabalho = trabalhoRepo.findById(id).get();
    //     mv.addObject("trabalho", trabalho);
    //     mv.addObject("areas", String.join(", ", trabalho.getAreas()));

    //     return mv;
    // }

    // @PostMapping("/{id}/update")
    // public String update(Trabalho trabalho) {
    //     trabalhoRepo.save(trabalho);

    //     return "redirect:/trabalho/{id}";
    // }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
		trabalhoRepo.deleteById(id);

        return "redirect:/trabalho";
    }
}