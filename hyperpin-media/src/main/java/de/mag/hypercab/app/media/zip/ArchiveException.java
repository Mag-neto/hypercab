package de.mag.hypercab.app.media.zip;

public class ArchiveException extends RuntimeException {

	private static final long serialVersionUID = 3167547334105437162L;

	public ArchiveException(String message) {
		super(message);
	}

	public ArchiveException(Throwable cause) {
		super(cause);
	}

	public ArchiveException(String message, Throwable cause) {
		super(message, cause);
	}
}
