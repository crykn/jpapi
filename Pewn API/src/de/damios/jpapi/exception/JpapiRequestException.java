package de.damios.jpapi.exception;

/**
 * Wird geschmissen wenn ein Fehler beim Verarbeiten eines Requests für die
 * Pewn-Api auftritt<br>
 * 
 * @author damios
 * @version 1.0
 *
 */
public class JpapiRequestException extends RuntimeException {

	private static final long serialVersionUID = 100L;

	public JpapiRequestException() {
		super();
	}

	public JpapiRequestException(String message) {
		super(message);
	}

	public JpapiRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public JpapiRequestException(Throwable cause) {
		super(cause);
	}

}
