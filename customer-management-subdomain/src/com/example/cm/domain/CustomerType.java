package com.example.cm.domain;

@ValueObject
public enum CustomerType {
	STANDARD_CUSTOMER {
		@Override
		public CustomerType upgrade() {
			return SILVER_CUSTOMER;
		}
	},
	SILVER_CUSTOMER {
		@Override
		public CustomerType upgrade() {
			return GOLD_CUSTOMER;
		}
	},
	GOLD_CUSTOMER {

		@Override
		public CustomerType upgrade() {
			return GOLD_CUSTOMER;
		}

	};

	public abstract CustomerType upgrade();
}
