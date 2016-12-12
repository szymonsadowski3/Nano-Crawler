package pl.sadowski.exceptions;

public class DownloaderException extends Exception {
	/**
	 * Default constructor
	 */
	public DownloaderException() {
		super();
	}

	/**
	 * Parametrized constructor
	 * @param message
	 * Exception message
	 */
	public DownloaderException(String message) {
		super(message);
	}

	/**
	 * Parametrized constructor
	 * @param message
	 * Exception message
	 * @param cause
	 * Throwable that caused this throwable to be constructed
	 */
	public DownloaderException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Parametrized constructor
	 * @param cause
	 * Throwable that caused this throwable to be constructed
	 */
	public DownloaderException(Throwable cause) {
		super(cause);
	}
}