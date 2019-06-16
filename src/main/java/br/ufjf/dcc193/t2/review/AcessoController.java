package br.ufjf.dcc193.t2.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/acesso")
public class AcessoController
{
    @Autowired
    AvaliadorRepository avaliadorRepo;

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
            // ToDo(andre:2019-06-16): Retornar uma mensagem informando que ocorreu um erro
            mv.setViewName("redirect:/acesso/login");
            return mv;
        }

        mv.setViewName("acesso-index");
        mv.addObject("avaliador", avaliador);
        
        return mv;
    }
}