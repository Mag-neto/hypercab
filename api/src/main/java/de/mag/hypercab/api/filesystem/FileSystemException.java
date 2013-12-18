package de.mag.hypercab.api.filesystem;

public class FileSystemException extends RuntimeException {

	private static final long serialVersionUID = 1110954084984415182L;

	public FileSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSystemException(String message) {
		super(message);
	}

	public FileSystemException(Throwable cause) {
		super(cause);
	}

}
