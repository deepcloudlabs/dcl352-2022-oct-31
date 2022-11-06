package com.example.cm.domain;

import java.util.Base64;
import java.util.Objects;

@ValueObject
public final class Photo {
	private final byte[] values;

	private Photo(byte[] values) {
		this.values = values;
	}

	public static Photo valuesOf(byte[] values) {
		Objects.requireNonNull(values);
		return new Photo(values);
	}

	public byte[] getValues() {
		return values;
	}

	public String getBase64Values() {
		return Base64.getEncoder().encodeToString(values);
	}

	public static Photo valuesOf(String base64Value) {
		Objects.requireNonNull(base64Value);
		return new Photo(Base64.getDecoder().decode(base64Value));
	}

}
