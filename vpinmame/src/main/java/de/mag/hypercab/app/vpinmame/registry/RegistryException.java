package de.mag.hypercab.app.vpinmame.registry;

public class RegistryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	RegistryException(String message, Throwable cause) {
		super(message, cause);
	}

	RegistryException(String message) {
		super(message);
	}

	RegistryException(Throwable cause) {
		super(cause);
	}

}
