package com.orangeandbronze.money;

public class CurrencyMismatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1745289047431552747L;

	public CurrencyMismatchException() {
	}

	public CurrencyMismatchException(String message) {
		super(message);
	}

	public CurrencyMismatchException(Throwable cause) {
		super(cause);
	}

	public CurrencyMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public CurrencyMismatchException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
