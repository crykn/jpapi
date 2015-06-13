# jpapi
Ein Java Wrapper für die [Pewn API](http://pewn.de/papi/)

##Downloads
[Beta 1.0.0](https://github.com/crykn/jpapi/releases/tag/1.0.0)

##Funktionsweise
jpapi sendet die Anfragen an die offizielle Pewn API und erhält die Antwort im JSON Format. Die Antwort wird dann mit [gson](https://github.com/google/gson) zu den entsprechenden Java Objekten geparst.

##Beispiel
Hier ist ein kleines Beispiel zur Verwendung des Wrappers.
```java
Project project = Project.getRandom();
User author = project.author;
System.out.println(author.username);

project = Project.getAll(OrderedBy.RATING)[0];
System.out.println(project.images[0].fileName);
```

##Abhängigkeiten
* [gson]{http://search.maven.org/#artifactdetails%7Ccom.google.code.gson%7Cgson%7C2.3.1%7Cjar}
