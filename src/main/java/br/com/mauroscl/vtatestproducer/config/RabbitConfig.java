package br.com.mauroscl.vtatestproducer.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

//  @Bean
//  Queue insertionQueue() {
//    return new Queue(insertionQueue);
//  }
//
//  @Bean
//  Queue validationQueue() {
//    return new Queue(validationQueue);
//  }
//
//  @Bean
//  Queue responseQueue() {
//    return new Queue(responseQueue);
//  }
//
//
//  @Bean
//  TopicExchange exchange() {
//    return new TopicExchange(responseExchange);
//  }
//
//  @Bean
//  Binding binding(Queue responseQueue, TopicExchange exchange) {
//    return BindingBuilder
//        .bind(responseQueue)
//        .to(exchange)
//        .with(responseRoutingKey);
//  }

//  @Bean
//  SimpleMessageListenerContainer validationContainer(ConnectionFactory connectionFactory/*,
//      MessageListenerAdapter validationListenerAdapter*/) {
//    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//    container.setConnectionFactory(connectionFactory);
//    container.setQueueNames(validationQueue);
//    //container.setConcurrentConsumers(numberOfValidationConsumers);
//    //container.setMessageListener(validationListenerAdapter);
//    return container;
//  }

//  @Bean
//  MessageListenerAdapter validationListenerAdapter(ValidationConsumer validationConsumer) {
//    return new MessageListenerAdapter(validationConsumer, "receive");
//  }

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
    return rabbitTemplate;
  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
