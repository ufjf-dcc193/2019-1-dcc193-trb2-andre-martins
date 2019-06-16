package br.ufjf.dcc193.t2.review;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		AvaliadorRepository avaliadorRepo = ctx.getBean(AvaliadorRepository.class);
		TrabalhoRepository trabalhoRepo = ctx.getBean(TrabalhoRepository.class);
		
		List<String> avaliadorAreas = new ArrayList<String>();
		avaliadorAreas.add("eu");
		avaliadorAreas.add("não eu");
		avaliadorAreas.add("alguem");

		Avaliador avaliador = new Avaliador("Eu", "eu@eu.eu", "acesso-eu", avaliadorAreas);
		avaliadorRepo.save(avaliador);
		
		Trabalho trabalho = new Trabalho("Nome", "É um nome", "www.nome.com", "nomes");
		trabalhoRepo.save(trabalho);
	}

}
