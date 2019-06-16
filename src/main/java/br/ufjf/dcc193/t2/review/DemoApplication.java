package br.ufjf.dcc193.t2.review;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		// ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		// AvaliadorRepository avaliadorRepo = ctx.getBean(AvaliadorRepository.class);
		// TrabalhoRepository trabalhoRepo = ctx.getBean(TrabalhoRepository.class);
		
		// List<String> avaliadorAreas = new ArrayList<String>();
		// avaliadorAreas.add("eu");
		// avaliadorAreas.add("não eu");
		// avaliadorAreas.add("alguem");

		// Avaliador avaliador;
		// avaliador = new Avaliador("Eu", "eu@eu.eu", "acesso-eu", avaliadorAreas);
		// avaliadorRepo.save(avaliador);
		// avaliador = new Avaliador("Alguem", "alguem@eu.eu", "acesso-alguem", avaliadorAreas);
		// avaliadorRepo.save(avaliador);
		// avaliador = new Avaliador("Outrém", "outrem@eu.eu", "acesso-outrem", avaliadorAreas);
		// avaliadorRepo.save(avaliador);
		
		// Trabalho trabalho;
		// trabalho = new Trabalho("Nome", "É um nome", "https://www.nome.com", "nomes");
		// trabalhoRepo.save(trabalho);
		// trabalho = new Trabalho("Outro nome", "É um outro nome", "https://www.outro-nome.com", "nomes");
		// trabalhoRepo.save(trabalho);
		// trabalho = new Trabalho("Homonimo", "É um outro nome para o mesmo nome", "https://www.homonimo.com", "nomes");
		// trabalhoRepo.save(trabalho);
	}

}
