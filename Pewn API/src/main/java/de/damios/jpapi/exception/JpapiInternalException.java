package de.damios.jpapi.exception;

/**
 * Wird geschmissen, wenn ein interner Fehler in jpapi auftritt (z.B. eine
 * falsch konfigurierte URL) und sollte im Normalfall <i>nie</i> auftreten.
 * 
 * @author damios
 * @since 0.1.0
 */
public class JpapiInternalException extends RuntimeException {

	private static final long serialVersionUID = 100L;

	/**
	 * Erstellt eine neue JpapiInternalException mit null als Fehlermeldung.
	 * 
	 * @see RuntimeException#RuntimeException() RuntimeException()
	 */
	public JpapiInternalException() {
		super();
	}

	/**
	 * Erstellt eine neue JpapiInternalException mit der übergebenen
	 * Fehlermeldung.
	 * 
	 * @param message
	 *            Die Fehlermeldung.
	 * @see RuntimeException#RuntimeException(String) RuntimeException(String)
	 */
	public JpapiInternalException(String message) {
		super(message);
	}

	/**
	 * 
	 * Erstellt eine neue JpapiInternalException mit der übergebenen Ursache und
	 * Fehlermeldung.
	 * 
	 * @param message
	 *            Die Fehlermeldung.
	 * @param cause
	 *            Die Ursache.
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 *      RuntimeException(String, Throwable)
	 */
	public JpapiInternalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Erstellt eine neue JpapiInternalException mit der übergebenen Ursache und
	 * einer folgendermaßen lautenden Fehlermeldung:
	 * {@code (cause==null ? null :
	 * cause.toString())} (Normalerweise die Klasse und Fehlermeldung der
	 * Ursache).
	 * 
	 * @param cause
	 *            Die Ursache.
	 * @see RuntimeException#RuntimeException(Throwable)
	 *      RuntimeException(Throwable)
	 */
	public JpapiInternalException(Throwable cause) {
		super(cause);
	}

}
