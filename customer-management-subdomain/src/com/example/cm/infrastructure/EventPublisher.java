package com.example.cm.infrastructure;

import com.example.cm.domain.event.CustomerEvent;

public interface EventPublisher<E extends CustomerEvent> {
	public void publishEvent(E event);
}
