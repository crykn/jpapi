package de.damios.jpapi.exception;

/**
 * Wird geschmissen wenn ein Fehler bei einer Anfrage an die Pewn-Api auftritt.<br>
 * <i>Ist momentan aus Gründen der Bequemlichkeit keine checked Exception</i>
 * 
 * @author damios
 * @since 1.0
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
