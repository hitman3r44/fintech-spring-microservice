package com.wolverinesolution.profile.application.config;

import com.wolverinesolution.profile.domain.event.UserRegisteredEventHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    private static final String TOPIC = "userRegisteredTopic";
    private static final String QUEUE_NAME = "user.registered.profile";

    @Bean
    public Queue userRegisteredQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange userRegisteredTopic(){
        return new TopicExchange(TOPIC);
    }


    @Bean
    public Binding binding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("user.#");
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(QUEUE_NAME);
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);

        return simpleMessageListenerContainer;
    }

    @Bean
    public MessageListenerAdapter userRegisteredEventListner(UserRegisteredEventHandler userRegisteredEventHandler){
        return new MessageListenerAdapter(userRegisteredEventHandler,"handleUserRegistration");
    }

}
