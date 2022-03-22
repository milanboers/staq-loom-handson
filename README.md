# Staq 2022 Loom Handson

### JDK
Download Loom Early Access Builds hier:
```bash
https://jdk.java.net/loom/
```

### Build met maven
```bash
JAVA_HOME=/path/to/jdk-19.jdk/Contents/Home mvn clean verify
```

### Uitleg
We zijn een bibliotheek en hebben een service ontwikkeld om de taal van een bepaald boek te vinden. We hebben een
database met al onze boeken erin, en de ISBN van dit boek:

| bookId | isbn           |
|--------|----------------|
| 1      | 9780887580517  |
| 2      | 9780400754644  |
| ...    | ...            |

Voor het vinden van de taal gebruiken we een externe service genaamd Metabook®. Metabook® kan op basis van de ISBN de 
taal van het boek vinden.

Zowel de database als Metabook® reageren helaas nogal traag. Ze doen er beide 1 seconde over om antwoord te geven.

### Opdrachten
We hebben een HTTP service gebouwd die de taal van een boek opzoekt via een `GET /books/{bookId}` endpoint. We hebben
een `BookService` met een `getLanguage(bookId)` methode. In `nl.quintor.staq.handler` hebben we verschillende HTTP
handlers, die de `BookService` moeten aanroepen. De `DumbRequestHandler` is al geïmplementeerd. Deze handelt de requests
af op de HTTP listener thread. Helaas is deze niet zo snel... Aan jou de taak betere handlers te bouwen.

Er is een testje beschikbaar in `nl.quitor.staq.AppTest` waarmee je de performance van je handler kunt testen. Gebruik
deze tijdens het implementeren van de verschillende handlers, en kijk hoe hoog je de load kunt maken en wat voor effect
dit heeft op je latency en CPU/geheugen gebruik. Er worden tijdens de test wat metrics gelogd.

Implementeer de onafgemaakte classes in deze volgorde:

- nl.quintor.staq.service.BookService
- nl.quintor.staq.handler.ThreadingRequestHandler
- nl.quintor.staq.handler.ThreadPoolRequestHandler
- nl.quintor.staq.service.ReactiveBookService
- nl.quintor.staq.service.LoomRequestHandler

### Let op!
- IntelliJ support compileren met Java 19 source niet. Zodra je de LoomRequestHandler gaat implementeren, zul je de compilatie en test via maven moeten draaien.
