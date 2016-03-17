# jpapi
Ein Java Wrapper für die [Pewn API](http://pewn.de/papi/)

## Wichtig!
Die zur Zeit auftretenden *MalformedURLExceptions* werden nicht durch jpapi, sondern durch fehlerhafte Downloadlinks auf Pewn verursacht.

##Funktionsweise
jpapi sendet die Anfragen an die offizielle Pewn API und erhält die Antwort im JSON Format. Die Antwort wird dann mit [gson](https://github.com/google/gson) zu den entsprechenden Java Objekten geparst.

##Beispiel
Hier ist ein kleines Beispiel zur Verwendung des Wrappers:
```java
Project project = Project.get(8568);

User author = project.getAuthor();
System.out.println(author.getUsername());

Set<String> hashtags = project.getHashtags();
for (String s : hashtags) {
		System.out.println(s);
	}

System.out.println(Project.getRandom().getImages()[0].getFileName());
```

##Download
[0.3.1](https://github.com/crykn/jpapi/releases/tag/0.3.1) <br>
[Ältere Versionen...](https://github.com/crykn/jpapi/releases)

##Abhängigkeiten
* [gson](http://search.maven.org/#artifactdetails%7Ccom.google.code.gson%7Cgson%7C2.6.2%7Cjar)

##Dokumentation
[Javadoc](http://crykn.github.io/jpapi-javadoc/)
