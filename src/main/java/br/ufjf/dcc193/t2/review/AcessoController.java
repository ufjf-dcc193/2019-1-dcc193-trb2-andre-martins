package br.ufjf.dcc193.t2.review;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/acesso")
public class AcessoController
{
    @Autowired
    AvaliadorRepository avaliadorRepo;

    @Autowired
    TrabalhoRepository trabalhoRepo;

    @Autowired
    RevisaoRepository revisaoRepo;

    public Boolean checkAccess(Avaliador avaliador, String token)
    {
        return avaliador.getCodigoAcesso().equals(token);
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
    @RequestMapping({"", "/", "/index"})
    public String loginRedirect()
    {
        return "redirect:/acesso/login";
    }

    @RequestMapping("/authenticate")
    public String authentication(String email, String token)
    {
        String destination = "/";

        Avaliador avaliador = avaliadorRepo.findByEmail(email);
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            // ToDo(andre:2019-06-16): Retornar uma mensagem informando que ocorreu um erro
            return "redirect:/acesso/login";
        }

        // ToDo(andre:2019-06-16): Não passar o token como um parâmetro na URL
        return "redirect:/acesso/" + avaliador.getId() + destination + "?token=" + token;
    }

    @RequestMapping({"/{id}", "/{id}/", "/{id}/index"})
    public ModelAndView index(@PathVariable Long id, String token)
    {
        ModelAndView mv = new ModelAndView();

        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            mv.setViewName("redirect:/acesso/login");
            return mv;
        }

        mv.setViewName("acesso-index");
        mv.addObject("avaliador", avaliador);
        mv.addObject("token", token);

        return mv;
    }

    @RequestMapping("/{id}/areas")
    public ModelAndView areas(@PathVariable Long id, String token)
    {
        ModelAndView mv = new ModelAndView();

        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            mv.setViewName("redirect:/acesso/login");
            return mv;
        }

        mv.setViewName("acesso-areas");
        mv.addObject("avaliador", avaliador);
        mv.addObject("areas", avaliador.getAreas());
        mv.addObject("token", token);

        return mv;
    }

    @RequestMapping("/{id}/areas/{area}")
    public ModelAndView area(@PathVariable Long id, @PathVariable String area, String token)
    {
        ModelAndView mv = new ModelAndView();

        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            mv.setViewName("redirect:/acesso/login");
            return mv;
        }

        mv.setViewName("acesso-trabalhos-area");
        mv.addObject("avaliador", avaliador);
        mv.addObject("area", area);
        // mv.addObject("trabalhos", trabalhoRepo.findAllByArea(area));
        mv.addObject("trabalhos", trabalhoRepo.findAllByAreaOrderByQuantRevisoesAsc(area));
        mv.addObject("token", token);

        return mv;
    }

    @GetMapping("/{id}/revisoes")
    public ModelAndView revisoes(@PathVariable Long id, String token)
    {
        ModelAndView mv = new ModelAndView();

        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            mv.setViewName("redirect:/acesso/login");
            return mv;
        }

        mv.setViewName("acesso-revisoes-avaliador");
        mv.addObject("avaliador", avaliador);
        mv.addObject("revisoes", revisaoRepo.findAllByAvaliadorIdAndStatusIn(id, Arrays.asList(1)));
        // mv.addObject("revisoes", revisaoRepo.findAllByAvaliadorId(id)); // NOTE(andre:2019-06-19): Apenas para debug
        // mv.addObject("revisoes", revisaoRepo.findAll()); // NOTE(andre:2019-06-19): Apenas para debug
        mv.addObject("token", token);

        return mv;
    }

    @GetMapping("/{id}/revisoes/{tid}")
    public ModelAndView revisao(@PathVariable Long id, @PathVariable Long tid, String token)
    {
        ModelAndView mv = new ModelAndView();

        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            mv.setViewName("redirect:/acesso/login");
            return mv;
        }

        mv.setViewName("acesso-revisao");
        mv.addObject("avaliador", avaliador);
        mv.addObject("trabalho", trabalhoRepo.findById(tid).get());
        mv.addObject("revisao", new Revisao());
        mv.addObject("token", token);

        return mv;
    }

    @PostMapping(path = "/{id}/revisoes/{tid}", params = "later")
    public ModelAndView revisaoLater(@PathVariable Long id, @PathVariable Long tid, Revisao revisao, String token)
    {
        ModelAndView mv = new ModelAndView();

        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            mv.setViewName("redirect:/acesso/login");
            return mv;
        }

        Trabalho trabalho = trabalhoRepo.findById(tid).get();
        revisao.setAvaliador(avaliador);
        revisao.setTrabalho(trabalho);
        revisao.setStatus(0);
        revisaoRepo.save(revisao);

        mv.setViewName("redirect:/acesso/{id}/areas/" + trabalho.getArea() + "?token=" + token);

        return mv;
    }

    @PostMapping(path = "/{id}/revisoes/{tid}", params = "now")
    public ModelAndView revisaoNow(@PathVariable Long id, @PathVariable Long tid, Revisao revisao, String token)
    {
        ModelAndView mv = new ModelAndView();

        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            mv.setViewName("redirect:/acesso/login");
            return mv;
        }

        Trabalho trabalho = trabalhoRepo.findById(tid).get();
        revisao.setAvaliador(avaliador);
        revisao.setTrabalho(trabalho);
        revisao.setStatus(1);
        revisaoRepo.save(revisao);

        mv.setViewName("redirect:/acesso/{id}/areas/" + trabalho.getArea() + "?token=" + token);

        return mv;
    }

    @PostMapping(path = "/{id}/revisoes/{tid}", params = "skip")
    public ModelAndView revisaoSkip(@PathVariable Long id, @PathVariable Long tid, Revisao revisao, String token)
    {
        ModelAndView mv = new ModelAndView();

        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            mv.setViewName("redirect:/acesso/login");
            return mv;
        }

        Trabalho trabalho = trabalhoRepo.findById(tid).get();
        revisao = new Revisao();
        revisao.setAvaliador(avaliador);
        revisao.setTrabalho(trabalho);
        revisao.setStatus(2);
        revisaoRepo.save(revisao);

        mv.setViewName("redirect:/acesso/{id}/areas/" + trabalho.getArea() + "?token=" + token);

        return mv;
    }

    @RequestMapping("/{id}/revisoes/{rid}/delete")
    public String delete(@PathVariable Long id, @PathVariable Long rid, String token)
    {
        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            return "redirect:/acesso/login";
        }

		revisaoRepo.deleteById(rid);

        return "redirect:/acesso/{id}/revisoes?token=" + token;
    }

    @RequestMapping("/{id}/revisoes/{rid}/evaluated")
    public String evaluated(@PathVariable Long id, @PathVariable Long rid, String token)
    {
        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            return "redirect:/acesso/login";
        }

        Revisao revisao = revisaoRepo.findById(rid).get();
        revisao.setStatus(1);
        revisaoRepo.save(revisao);

        return "redirect:/acesso/{id}/revisoes?token=" + token;
    }

    @RequestMapping("/{id}/revisoes/{rid}/validated")
    public String validated(@PathVariable Long id, @PathVariable Long rid, String token)
    {
        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            return "redirect:/acesso/login";
        }

        Revisao revisao = revisaoRepo.findById(rid).get();
        revisao.setStatus(3);
        revisaoRepo.save(revisao);

        return "redirect:/acesso/{id}/revisoes?token=" + token;
    }

    @RequestMapping("/{id}/revisoes/{rid}/invalidated")
    public String invalidated(@PathVariable Long id, @PathVariable Long rid, String token)
    {
        Avaliador avaliador = avaliadorRepo.findById(id).get();
        if (avaliador == null || !checkAccess(avaliador, token))
        {
            return "redirect:/acesso/login";
        }

        Revisao revisao = revisaoRepo.findById(rid).get();
        revisao.setStatus(4);
        revisaoRepo.save(revisao);

        return "redirect:/acesso/{id}/revisoes?token=" + token;
    }
}