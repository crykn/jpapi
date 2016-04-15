# jpapi
Ein Java Wrapper für die [Pewn API](http://pewn.de/papi/)

##Funktionsweise
jpapi sendet die Anfragen an die offizielle Pewn API und erhält die Antwort im JSON Format. Die Antwort wird dann mit [gson](https://github.com/google/gson) zu den entsprechenden Java Objekten geparst.

##Beispiel
Hier ist ein kleines Beispiel zur Verwendung des Wrappers:
```java
Project project = Project.get(8568);

User author = project.getAuthor();
System.out.println(author.getName());

Hashtag[] hashtags = project.getHashtags();
for (Hashtag h : hashtags) {
	System.out.println(h.getName());
}

System.out.println(Project.getRandom().getImages()[0].getFileName());
```
Weitere Beispiele sind im [Wiki](http://crykn.github.io/jpapi-javadoc/) zu finden.

##Download
[0.4.1](https://github.com/crykn/jpapi/releases/tag/0.4.1) <br>
[Ältere Versionen...](https://github.com/crykn/jpapi/releases)

##Abhängigkeiten
* [gson](http://search.maven.org/#artifactdetails%7Ccom.google.code.gson%7Cgson%7C2.6.2%7Cjar)

##Dokumentation
[Wiki](http://crykn.github.io/jpapi-javadoc/) <br>
[Javadoc](https://github.com/crykn/jpapi/wiki)