# jpapi
jpapi ist ein Java Wrapper für die [Pewn API](http://pewn.de/papi/). Ziel ist es, eine möglichst übersichtliche und intuitiv benutzbare Schnittstelle bereitzustellen, die in Java einen einfachen Zugriff auf die Funktionen der API erlaubt.

## Erste Schritte
[Das jpapi Wiki](https://github.com/crykn/jpapi/wiki) enthält ausführliche Informationen zum Aufsetzen eines Projekts, der Funktionsweise des Wrappers und einige aufschlussreiche Beispiele. Die Java Dokumentation lässt sich [hier](http://crykn.github.io/jpapi-javadoc/) finden.

```java
Project project = Project.getByProjectId(8568);

User author = project.getAuthor();
System.out.println(author.getName());

Hashtag[] hashtags = project.getHashtags();
for (Hashtag h : hashtags) {
	System.out.println(h.getName());
}

System.out.println(Project.getRandom().getImages()[0].getFileName());
```

## Download
Die aktuellste Version steht [hier](https://github.com/crykn/jpapi/releases/) zum Download bereit.

## Abhängigkeiten
* [Gson](http://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5)
* [Retrofit](http://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit/2.4.0)
* [Retrofit Gson Adapter](http://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson/2.4.0)
