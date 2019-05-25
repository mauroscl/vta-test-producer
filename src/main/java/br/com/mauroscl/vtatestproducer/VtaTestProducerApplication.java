package br.com.mauroscl.vtatestproducer;

import java.util.Scanner;
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
    Scanner in = new Scanner(System.in);
    producerService.enviarMensagens();
  }
}
