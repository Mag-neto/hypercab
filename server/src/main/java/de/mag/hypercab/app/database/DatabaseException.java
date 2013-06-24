package de.mag.hypercab.app.database;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 2686002933762862122L;

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
