package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;

import de.damios.jpapi.core.Api;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <i>Java-Modell des JSON-Activity-Wrapper-Objekts.</i>
 * <p>
 * Die zentrale Klasse um die neuesten Aktivitäten auf Pewn zu erhalten.
 * 
 * @author damios
 * @since 0.5.0
 */
public class Activity implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static ActivityService service = Api.createService(ActivityService.class);

	private static final long serialVersionUID = 110L;
	private String type;
	private BlogPost blogPost;
	private DesignComment designComment;
	private NewsComment newsComment;
	private ProjectComment projectComment;
	private ForumTopic forumTopic;

	/**
	 * Liefert den Typ der Aktivität.
	 * <p>
	 * Mögliche Werte:
	 * <ul>
	 * <li>FORUM_TOPIC
	 * <li>BLOG_POST
	 * <li>DESIGN_COMMENT
	 * <li>NEWS_COMMENT
	 * <li>PROJECT_COMMENT
	 * </ul>
	 * 
	 * @return Der Typ.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return Liefert den Blogpost; null wenn der Typ nicht 'BLOG_POST' ist.
	 */
	public BlogPost getBlogPost() {
		return blogPost;
	}

	/**
	 * @return Liefert den Design-Kommentar; null wenn der Typ nicht 'DESIGN_COMMENT' ist.
	 */
	public DesignComment getDesignComment() {
		return designComment;
	}

	/**
	 * @return Liefert den Neuigkeiten-Kommentar; null wenn der Typ nicht 'NEWS_COMMENT' ist.
	 */
	public NewsComment getNewsComment() {
		return newsComment;
	}

	/**
	 * @return Liefert den Projekt-Kommentar; null wenn der Typ nicht 'PROJECT_COMMENT' ist.
	 */
	public ProjectComment getProjectComment() {
		return projectComment;
	}

	/**
	 * @return Liefert das Foren-Topic; null wenn der Typ nicht 'FORUM_TOPIC' ist.
	 */
	public ForumTopic getForumTopic() {
		return forumTopic;
	}

	/**
	 * Liefert die letzten Aktivitäten auf Pewn.
	 * 
	 * @param x
	 *            Die Anzahl der Aktivitäten.
	 * @return Die Spiele als ProjectWrapper-Array; wenn ein Nutzer keine Spiele
	 *         hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Activity[] getLast(int x) throws IOException {
		return Api.executeCall(service.getLast(x));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für
	 * Aktivitäten zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface ActivityService {

		@GET("v1/contents/activities?format=json")
		Call<Activity[]> getLast(@Query("size") int size);

	}

}
