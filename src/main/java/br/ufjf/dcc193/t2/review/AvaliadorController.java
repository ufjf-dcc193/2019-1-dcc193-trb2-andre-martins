package br.ufjf.dcc193.t2.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/avaliador")
public class AvaliadorController {
    @Autowired
    AvaliadorRepository avaliadorRepo;

    @RequestMapping({"", "/", "index"})
    public ModelAndView list() {
        List<Avaliador> avaliadores = avaliadorRepo.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("avaliador-list");
        mv.addObject("avaliadores", avaliadores);
        return mv;
    }

    @RequestMapping("/{id}")
    public ModelAndView read(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("avaliador-read");
        mv.addObject("avaliador", avaliadorRepo.findById(id).get());

        return mv;
    }

    @RequestMapping("/{id}/update")
    public ModelAndView update(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("avaliador-update");
        Avaliador avaliador = avaliadorRepo.findById(id).get();
        mv.addObject("avaliador", avaliador);
        mv.addObject("areas", String.join(", ", avaliador.getAreas()));

        return mv;
    }

    @RequestMapping("/{id}/update-confirm")
    public String updateConfirm(Avaliador avaliador) {
        avaliadorRepo.save(avaliador);

        return "redirect:/avaliador/{id}";
    }
}