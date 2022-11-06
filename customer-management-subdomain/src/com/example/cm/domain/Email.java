package com.example.cm.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

// Value Object
// 1. No identity -> should belong to an entity
// 2. Immutable
@ValueObject
public final class Email {
	private final String value;
	private static Map<String, Email> emailCache = new ConcurrentHashMap<>();

	private Email(String value) {
		this.value = value;
	}

	public static Email valueOf(String value) {
		// validation
		if (Objects.isNull(value))
			throw new IllegalArgumentException("Email should have a value!");
		if (!value.matches(".+@.+"))
			throw new IllegalArgumentException("Email should have a value!");
		// object pooling
		var cachedEmail = emailCache.get(value);
		if (Objects.isNull(cachedEmail)) {
			cachedEmail = new Email(value);
			emailCache.put(value, cachedEmail);
		}
		return cachedEmail;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "Email [value=" + value + "]";
	}

}