package br.com.mauroscl.vtatestproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VtaTestProducerApplication implements CommandLineRunner {

  @Autowired
  private ProducerService producerService;

  public static void main(String[] args) {
    SpringApplication.run(VtaTestProducerApplication.class, args);
  }

  @Override
  public void run(final String... args) throws Exception {

    if (args.length != 2) {
      System.out.println("obrigatório informar 2 parâmetros");
      System.exit(0);
    }

    int quantidadeInsercoes = 0;
    int quantidadeValidacoes = 0;
    try {
      quantidadeInsercoes = Integer.parseInt(args[0]);
      quantidadeValidacoes = Integer.parseInt(args[1]);

    } catch (NumberFormatException e) {
      System.out.println("parâmetros inválidos");
      System.exit(0);
    }

    producerService.enviarMensagens(quantidadeInsercoes, quantidadeValidacoes);
  }
}
