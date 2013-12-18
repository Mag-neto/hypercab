package de.mag.hypercab.api.filesystem;

public class FileSystemException extends RuntimeException {

	private static final long serialVersionUID = 1110954084984415182L;

	FileSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	FileSystemException(String message) {
		super(message);
	}

	FileSystemException(Throwable cause) {
		super(cause);
	}

}
