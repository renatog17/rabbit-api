package com.renato.rabbitmq.api.facade;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.renato.rabbitmq.api.dto.PagamentoDTO;
import com.renato.rabbitmq.api.producer.PagamentoRequestProducer;

@Service
public class PagamentoFacade {

	@Autowired private PagamentoRequestProducer pagamentoRequestProducer;
	
	public String solicitarPagamento(PagamentoDTO request) {
		try {
			pagamentoRequestProducer.integrar(request);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ocorreu um erro ao solicitar pagamento";
		} catch (AmqpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ocorreu um erro ao solicitar pagamento";
		}
		return "Pagamento aguardando confirmacao...";
	}

	public void erroPagamento(String payload) {
		System.err.println("==== RESPOSTA ERRO ===="+payload);
	}

	public void sucessoPagamento(String payload) {
		System.out.println("==== RESPOSTA SUCESSO ===="+payload);
		
	}

	
}
