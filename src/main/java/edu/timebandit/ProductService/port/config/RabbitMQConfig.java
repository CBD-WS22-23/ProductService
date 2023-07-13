package edu.timebandit.ProductService.port.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("add_product_to_basket_queue")
    private String addQueueName;

    @Value("add_product_to_basket_routing_key")
    private String addRoutingKey;

    @Value("product_exchange")
    private String exchange;

    @Value("product_added_to_basket_queue")
    private String productAddedName;

    @Value("product_added_to_basket_routing_key")
    private String productAddedRoutingKey;

    @Value("product_removed_from_basket_queue")
    private String productRemovedName;

    @Value("product_removed_from_basket_routing_key")
    private String productRemovedRoutingKey;

    @Value("basket_exchange")
    private String basketExchange;

    @Value("product_bought_queue")
    private String checkoutQueueName;

    @Value("checkout_product_bought_routing_key")
    private String checkoutRoutingKey;

    @Value("checkout_exchange")
    private String checkoutExchange;

    @Bean
    public Queue addToBasketQueue() {
        return new Queue(addQueueName);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue productAddedToBasketQueue() {
        return new Queue(productAddedName);
    }

    @Bean
    public Queue productRemovedFromBasketQueue() {
        return new Queue(productRemovedName);
    }

    @Bean
    public DirectExchange basketExchange() {
        return new DirectExchange(basketExchange);
    }

    @Bean
    public Queue productBoughtQueue() {
        return new Queue(checkoutQueueName);
    }

    @Bean
    public DirectExchange checkoutExchange() {
        return new DirectExchange(checkoutExchange);
    }

    @Bean
    public Binding createBinding(){
        return BindingBuilder
                .bind(addToBasketQueue())
                .to(exchange())
                .with(addRoutingKey);
    }


    @Bean
    public Binding productAddedToBasketBinding() {
        return BindingBuilder
                .bind(productAddedToBasketQueue())
                .to(basketExchange())
                .with(productAddedRoutingKey);
    }

    @Bean
    public Binding productRemovedFromBasketBinding() {
        return BindingBuilder
                .bind(productRemovedFromBasketQueue())
                .to(basketExchange())
                .with(productRemovedRoutingKey);
    }

    @Bean
    public Binding productBoughtBinding() {
        return BindingBuilder
                .bind(productBoughtQueue())
                .to(checkoutExchange())
                .with(checkoutRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
