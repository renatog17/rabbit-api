package com.renato.rabbitmq.api.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renato.rabbitmq.api.dto.PagamentoDTO;

@Component
public class PagamentoRequestProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public void integrar(PagamentoDTO pagamentoDTO) throws JsonProcessingException, AmqpException {
		amqpTemplate.convertAndSend(
				"pagamento-request-exchange", 
				"pagamento-request-rout-key",
				objectMapper.writeValueAsString(pagamentoDTO));
	}
}
