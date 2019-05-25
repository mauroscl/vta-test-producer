package br.com.mauroscl.vtatestproducer;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

  private static final int QUANTIDADE_CLIENTES = 100;
  private static final int QUANTIDADE_CARACTERES_DOMINIO_URL = 4;

  private static final String INSERTION_QUEUE = "insertion.queue";
  private static final String VALIDATION_QUEUE = "validation.queue";

  private final RabbitTemplate rabbitTemplate;
  private final List<String> clients;
  private final Random random;

  public ProducerService(final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
    clients = gerarClientes();
    random = new Random();
  }

  public void enviarMensagens() throws InterruptedException {
    while (true) {
      int i = 0;
      while (i < 100) {
        enviarInsercao();
        enviarValidacao();
        i++;
      }
      Thread.sleep(1000);
    }
  }

  private void enviarInsercao() {

    rabbitTemplate.convertAndSend(INSERTION_QUEUE, gerarRegra());
  }

  private void enviarValidacao() {
    rabbitTemplate.convertAndSend(VALIDATION_QUEUE,  gerarValidacao());
    rabbitTemplate.convertAndSend(VALIDATION_QUEUE, gerarValidacao());
  }

  private List<String> gerarClientes() {
    return IntStream.range(1, QUANTIDADE_CLIENTES + 1)
        .boxed()
        .map(i -> "client" + String.format("%03d", i))
    .collect(Collectors.toList());
  }

  private String obterCliente() {
    return clients.get(random.nextInt(QUANTIDADE_CLIENTES));
  }

  private WhiteListRule gerarRegra() {

    final WhiteListRule whiteListRule = new WhiteListRule();
    whiteListRule.setRegex(gerarExpressao());

    if (random.nextBoolean()) {
      whiteListRule.setClient(obterCliente());
    }

    return whiteListRule;
  }

  private ValidationCommand gerarValidacao() {
    final ValidationCommand validationCommand = new ValidationCommand();
    validationCommand.setClient(obterCliente());
    validationCommand.setUrl(gerarUrl());
    validationCommand.setCorrelationId(random.nextInt());
    return validationCommand;
  }

  private String gerarUrl() {
    return "www." + new String(gerarCaracteres())  +  ".com";
  }

  private String gerarExpressao() {
    return "[a-z]+\\." + new String(gerarCaracteres()) + "\\.[a-z]+";
  }

  private char[] gerarCaracteres() {
    char[] caracteres = new char[QUANTIDADE_CARACTERES_DOMINIO_URL];
    for(int i = 0 ; i < QUANTIDADE_CARACTERES_DOMINIO_URL; i ++ ) {
      caracteres[i] = (char) (97 + random.nextInt(26));
    }
    return caracteres;
  }

}
