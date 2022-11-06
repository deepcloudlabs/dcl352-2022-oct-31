package com.example.cem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerEventListener {
	Logger logger = LoggerFactory.getLogger(CustomerEventListener.class);

	@KafkaListener(topics = "cm-events", groupId = "customer-engagement")
	public void listenCustomerEvent(String customerEvent) {
		logger.info("New customer event has arrived: {}", customerEvent);
	}
}
