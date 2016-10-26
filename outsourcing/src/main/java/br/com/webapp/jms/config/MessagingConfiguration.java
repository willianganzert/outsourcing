package br.com.webapp.jms.config;

import java.util.Arrays;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
public class MessagingConfiguration {

	private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	
	private static final String DEPARTMENT_QUEUE = "department-queue";

	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		connectionFactory.setTrustedPackages(Arrays.asList("br.com.webapp"));
		return connectionFactory;
	}
	
	@Bean
	public MessageConverter messageConverter(){
		MessageConverter messageConverter  = new MappingJackson2MessageConverter();
		return messageConverter;
	}
	
	
	@Bean 
	public JmsTemplate jmsTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setMessageConverter(messageConverter());
		template.setDefaultDestinationName(DEPARTMENT_QUEUE);
		return template;
	}
	
}
