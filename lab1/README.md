Konkordans

Om du redovisar labben senast den 17 september får du en labbleveranspoäng, som kan ge högre betyg på labbmomentet. Till labben hör teoriuppgifter som kan redovisas för en teoripoäng till tentan, och detta görs på övningen den 9 september (ingen annan redovisningsmöjlighet finns). Det är frivilligt att redovisa teoriuppgifterna, men för att klara av att göra labben bör du ha gjort dom. 

Målen för labb 1 är att du ska

öva att programmera efter en funktionsspecifikation,
bygga en datastruktur som har litet (konstant) primärminnesbehov och som ändå kan söka snabbt i en stor fil på sekundärminne,
arbeta med texter lagrade med olika teckenkodning,
testa och utvärdera parprogrammering.
En konkordans är en databas där man kan slå upp ord och då få se alla förekomster av ordet tillsammans med orden närmast före och närmast efter i texten. Detta är ett stort hjälpmedel för lingvister som vill undersöka hur olika ord används i språket.

I denna uppgift ska du skriva ett program som givet en text skapar en konkordansdatabas och ett program som frågar användaren efter ord, slår upp ordet och presenterar alla förekomster av ordet i sitt sammanhang. Det är viktigt att varje sökning går mycket snabbt så det gäller att det första programmet lagrar konkordansen på ett sådant sätt att det går snabbt att göra en sökning.

Exempel på körning av sökprogrammet:

$ java Konkordans algoritmens
Det finns 12 förekomster av ordet.
let.  Historia  Cooley  Tukey-algoritmens historia börjar kring år 1805
 att jämföra målvariabeln med algoritmens estimering av målvariabeln. V
alsdivision skulle detta vara algoritmens totala komplexitet. Dock kvar
maximala använda minnet under algoritmens gång är det intressanta. Form
använda binär exponentiering. Algoritmens korrekthet förklaras som följ
 rötter kommer att klara båda algoritmens steg. Stage Junior 2006 Stage
r till nod 4 (se nästa bild). Algoritmens steg upprepas, och det är nu
att lära en robothund att gå. Algoritmens förmåga att lösa problemet be
ma problem. Ett annat ord för algoritmens resursberoende är komplexitet
rterad listan är från början. Algoritmens komplexitet blir O (N²).  Ins
2006) har visat att Masreliez-algoritmens prestanda är relativt bättre
 det behövs även en algoritm. Algoritmens uppgifter är att dela in talp 
Parprogrammering
För att få labbleveranspoäng på denna labb måste du (förutom att redovisa den senast ovanstående datum) genomföra den i tvåpersonsgrupper som arbetar enligt den agila programutvecklingstekniken parprogrammering. Läs här om parprogrammeringLänkar till en externa sida..

Krav
Följande krav ställs på din lösning:

Programmet ska vara skrivet i ett riktigt programspråk och inte något operativsystemnära skriptspråk eller liknande.

Konkordansen ska inte skilja på stora och små bokstäver. Användaren ska alltså kunna skriva in alla sökfrågor med små bokstäver, stora bokstäver eller godtycklig blandning.

Det givna programmet tokenizer.c på kurskatalogen (se nedan) definierar hur texten ska delas upp i enskilda ord.
Konstruktionsprogrammet behöver inte vara jättesnabbt eftersom det bara ska köras en gång, men det måste vara någorlunda effektivt så att det kan skapa konkordansen på rimlig tid. Det får inte ta mer än tre minuter att skapa konkordansen på en Ubuntudator (utöver tiden att köra tokenizer och sort).

Sökprogrammets utmatning ska inledas med en rad som anger antalet förekomster. Därefter ska varje förekomst av ordet presenteras på varje rad med till exempel 30 tecken före och 30 tecken efter. Ersätt radbyten med mellanslag. Om det finns fler än 25 förekomster ska programmet fråga användaren om vederbörande vill ha förekomsterna utskrivna på skärmen.

Man ska kunna söka efter ett ord, till exempel bil, genom att i terminalfönstret ge kommandot ./konkordans bil (om du använt C, C++ eller liknande), python3 konkordans.py bil (om du använt Python) eller java Konkordans bil (om du använt Java). 

Svaret (som alltså innehåller antalet förekomster men högst 25 rader med förekomster) måste komma inom en sekund på skolans Ubuntudatorer. Vid redovisningen ska programmet exekveras på en av skolans Ubuntudatorer (via fjärrinloggning, se nedan).

Sökprogrammet ska inte läsa igenom hela texten och får inte använda speciellt mycket internminne. Internminnesbehovet ska inte växa med antalet distinkta ord i den ursprungliga texten (med andra ord ha konstant internminneskomplexitet). Du ska därför använda latmanshashning (se föreläsning 3) som datastruktur. Vid redovisningen ska du kunna motivera varför internminneskomplexiteten är konstant.

Tips
För att komma åt filerna på kurskatalogen på AFS behöver du antingen köra direkt på en Ubuntudator på KTH eller göra en fjärrinloggningLänkar till en externa sida. på shell-servern student-shell.sys.kth.se (den som är anställd som amanuens använder istället staff-shell.sys.kth.se)

Texten, som ligger på /afs/kth.se/misc/info/kurser/DD2350/adk21/labb1/korpus, är en stor fil och ska inte i sin helhet läsas in i internminnet under sökningen. Istället bör sökprogrammet öppna filen och hoppa till dom avsnitt som ska presenteras med seek (använd till exempel fseek i stdio.h i C, seek i Python (Länkar till en externa sida.) eller seek i java.io.RandomAccessFile (Länkar till en externa sida.) i Java). Texten har teckenkodningen ISO-8859-1, som också kallas ISO-Latin 1 (Länkar till en externa sida.). Det betyder att varje tecken lagras i en byte, vilket är praktiskt när man ska adressera sig till en viss position i filen.

Ange teckenkodningen när du öppnar en fil/ström för läsning eller skrivning. I Java kan det till exempel se ut som

new InputStreamReader(rawindexfile, StandardCharsets.ISO_8859_1)
och i Python

open(rawindexfilename, encoding = "latin-1")
Då kommer teckenkonverteringen nog att lösa sig av sig själv. Det går också att konvertera strängar/bytearrayer själv med new String(b, "ISO-8859-1") och s.getBytes("ISO-8859-1") i Java (Länkar till en externa sida.) och med metoderna encode och decode i Python (Länkar till en externa sida.).

I C är konvertering mellan ISO-8859-1 och Unicode-kodningar svårare. Om du använder C räcker det att sökprogrammet kan användas med teckenkodningen ISO-8859-1. Tänk då på att det går att ställa om terminalfönstrets teckenkodning.

När du använder skolans Ubuntudatorer (till exempel student-shell.sys.kth.se) ska du inte kopiera textfilen utan istället låta sökprogrammet använda ursprungstextfilen på kurskatalogen.

Konstruktionsprogrammet måste skapa något slags index som talar om för varje ord på vilka positioner i texten det förekommer. Detta index blir av samma storleksordning som texten och sökprogrammet ska därför inte heller läsa in hela indexet. Låt det ligga på en fil (eller flera filer) och positionera med hjälp av seek även i denna fil.

Indexfilerna blir stora och får nog inte plats på din skivminnesarea, så skapa dom istället på temporärarean /var/tmp och ta bort dom när du är klar.

Använd gärna färdiga Unixverktyg som sort vid konstruktionen. En enkel tokeniserare (ett program som läser en text och plockar ut dom enskilda orden samt deras position i texten) finns på /afs/kth.se/misc/info/kurser/DD2350/adk21/labb1/tokenizer.c. 
Du kan använda en Makefile eller ett shell-skript för att starta flera program (till exempel tokenizer och sort) när du konstruerar konkordansen. Kommandot som kör tokenizer och sort kan se ut ungefär så här: 

/afs/kth.se/misc/info/kurser/DD2350/adk21/labb1/tokenizer < /afs/kth.se/misc/info/kurser/DD2350/adk21/labb1/korpus | sort > /var/tmp/rawindex.txt
Eftersom Ubuntu normalt använder teckenkodningen UTF-8 behöver du sätta shellvariabeln LC_COLLATE till C (med kommandot export LC_COLLATE=C i bash) innan du kör sort. Detta gör att sort tolkar texten som ISO-8859-1 och därmed sorterar tecknen i ordningen A B C ... Z a b c ... z Ä Å Ö ä å ö (notera ordningen mellan alfabetets tre sista bokstäver!). För enkelhets skull finns filen rawindex.txt som skapats med kommandot ovan färdig på kurskatalogen som /afs/kth.se/misc/info/kurser/DD2350/adk21/labb1/rawindex.txt
Använd helst den färdiga rawindex.txt så att du inte behöver skapa en egen kopia av denna jättefil.

Testa ditt program noga. Tänk ut svåra testfall (olika ytterligheter som enbokstavsord, första eller sista ordet i korpusen eller i indexet etc, se även sista teoriuppgiften).

Java är ganska snabbt, men just vid filhantering är det viktigt att man är noggrann när man använder Java. När du skapar konkordansdatabasen kommer du troligen att vilja skriva många gånger på en eller flera filer. Se till att de strömmar du konstruerar för skrivning (och läsning) är buffrade (läsning och skrivning på en RandomAccessFile kan inte buffras). Du kan läsa om Javas in- och utmatning i Oracles dokumentation.
