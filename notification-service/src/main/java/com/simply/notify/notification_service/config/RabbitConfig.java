package com.simply.notify.notification_service.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
	
	@Value("${notification.exchange}")
	private String exchangeName;
	
	@Value("${notification.queue}")
	private String queueName;
	
	@Value("${notification.routing-key}")
	private String routingKey;
	

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchangeName, true, false);
	}
	
	@Bean
	public Queue queue() {
		return QueueBuilder.durable(queueName).build();
	}
	
	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}
	
}
