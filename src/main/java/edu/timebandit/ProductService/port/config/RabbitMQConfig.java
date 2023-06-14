package edu.timebandit.ProductService.port.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    @Value("create_product_queue")
    private String createQueueName;

    @Value("create_product_routing_key")
    private String createRoutingKey;

    @Value("product_exchange")
    private String exchange;

    @Bean
    public Queue createQueue() {
        return new Queue(createQueueName);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding createBinding(){
        return BindingBuilder
                .bind(createQueue())
                .to(exchange())
                .with(createRoutingKey);
    }
}
