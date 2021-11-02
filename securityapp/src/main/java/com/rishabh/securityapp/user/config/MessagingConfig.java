package com.rishabh.securityapp.user.config;


import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    private static final String TOPIC = "userRegisteredTopic";
    private static final String QUEUE_NAME = "user.registered";

    @Bean
    public Queue userRegisteredQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange userRegisteredTopic(){
        return new TopicExchange(TOPIC);
    }

    @Bean
    ConnectionFactory connectionFactory(){
        return new ConnectionFactory();
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("user.#");
    }
}
