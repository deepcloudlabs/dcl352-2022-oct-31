package com.example.cm.domain.event;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.example.cm.domain.Email;

public abstract class CustomerEvent {
	private final Email email;
	private final String eventId = UUID.randomUUID().toString();
	private final ZonedDateTime time = ZonedDateTime.now();
	private final long sequence = 1;

	public CustomerEvent(Email email) {
		this.email = email;
	}

	public Email getEmail() {
		return email;
	}

	public String getEventId() {
		return eventId;
	}

	public ZonedDateTime getTime() {
		return time;
	}

	public long getSequence() {
		return sequence;
	}

	@Override
	public String toString() {
		return "CustomerEvent [email=" + email + ", eventId=" + eventId + ", time=" + time + ", sequence=" + sequence
				+ "]";
	}

}
