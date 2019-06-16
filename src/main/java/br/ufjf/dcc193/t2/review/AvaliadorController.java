package br.ufjf.dcc193.t2.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/avaliador")
public class AvaliadorController {
    @Autowired
    AvaliadorRepository avaliadorRepo;

    @RequestMapping({"/", "index"})
    public ModelAndView list() {
        List<Avaliador> avaliadores = avaliadorRepo.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("avaliador-list");
        mv.addObject("avaliadores", avaliadores);
        return mv;
    }
}