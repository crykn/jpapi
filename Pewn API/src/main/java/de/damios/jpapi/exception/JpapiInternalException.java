package de.damios.jpapi.exception;

/**
 * Wird geschmissen, wenn ein interner Fehler auftritt (z.B. eine falsch
 * konfigurierte URL) und sollte im Normalfall <i>nie</i> auftreten
 * 
 * @author damios
 * @since 0.1.0
 */
public class JpapiInternalException extends RuntimeException {

	private static final long serialVersionUID = 100L;

	public JpapiInternalException() {
		super();
	}

	public JpapiInternalException(String message) {
		super(message);
	}

	public JpapiInternalException(String message, Throwable cause) {
		super(message, cause);
	}

	public JpapiInternalException(Throwable cause) {
		super(cause);
	}

}
