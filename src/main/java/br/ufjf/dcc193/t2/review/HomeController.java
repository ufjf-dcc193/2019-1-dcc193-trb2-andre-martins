package br.ufjf.dcc193.t2.review;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("index")
    public String home() {
        return "index";
    }
}