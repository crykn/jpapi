package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;

import de.damios.jpapi.core.Api;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * <i>Java-Modell des JSON-Projekt-Wrapper-Objekts.</i>
 * <p>
 * Die zentrale Klasse zum Umgang mit den Spielen in der Top-Box auf der
 * Pewn-Startseite.
 * 
 * @author damios
 * @since 0.5.0
 */
public class ProjectWrapper implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static ProjectWrapperService service = Api.createService(ProjectWrapperService.class);

	private static final long serialVersionUID = 110L;
	private String reason;
	private Project project;

	/**
	 * @return Liefert den Grund dafür, dass das Spiel auf der Startseite
	 *         angezeigt wird.
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @return Liefert das Projekt.
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Liefert alle Spiele in der obersten Spielebox auf der Pewn-Startseite.
	 * 
	 * @return Die Spiele als ProjectWrapper-Array; wenn ein Nutzer keine Spiele
	 *         hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static ProjectWrapper[] getTopProjectBox() throws IOException {
		return Api.executeCall(service.getTopProjectBox());
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Projekte
	 * in der Top-Box auf der Startseite von Pewn zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface ProjectWrapperService {

		@GET("v1/contents/games/top?format=json")
		Call<ProjectWrapper[]> getTopProjectBox();

	}
}
