package com.example.cm.domain.event;

import com.example.cm.domain.DomainEvent;
import com.example.cm.domain.Email;

@DomainEvent
public class CustomerAcquiredEvent extends CustomerEvent {

	public CustomerAcquiredEvent(Email email) {
		super(email);
	}

}
