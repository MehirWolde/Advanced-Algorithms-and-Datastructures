Flöden och matchningar

Om du redovisar labben senast den 4 november får du en labbleveranspoäng, som kan ge högre betyg på labbmomentet. Till labben hör teoriuppgifter som kan redovisas för en teoripoäng till tentan, och detta görs på övningen den 6 oktober (ingen annan redovisningsmöjlighet finns). Det är frivilligt att redovisa teoriuppgifterna, men för att klara av att göra labben bör du ha gjort dom. 

Målen för labb 3 är att du ska

öva att programmera efter en detaljerad algoritmisk specifikation,
programmera en algoritm som använder en hjälpalgoritm som en svart låda (dvs en reduktion),
implementera en effektiv algoritm för flödesproblemet,
implementera en effektiv algoritm för bipartit matchning.
Du ska i tre steg skriva ett program som får en bipartit graf som indata och producerar en matchning av maximal storlek som utdata genom att reducera (transformera) matchningsproblemet till flödesproblemet. Korrekthet och effektivitet testas genom att lösningarna på de tre stegen skickas till Kattis (Länkar till en externa sida.). För att klara labben ska du bli godkänd av Kattis på de tre stegen samt redovisa labben för en handledare. Kattis kontrollerar både att programmet gör rätt och att det löser problemet tillräckligt snabbt. Kattis klarar av programspråken Java, C, C++ och Python, men tidskraven i denna labb gör att vi avråder från Python.

Steg 1: Reducera problemet till flödesproblemet
Du ska skriva ett program som löser matchningsproblemet med hjälp av en svart låda som löser flödesproblemet. Programmet ska fungera enligt denna översiktliga programstruktur:

Läs indata för matchningsproblemet från standard input.
Översätt matchningsinstansen till en flödesinstans.
Skriv indata för flödesproblemet till standard output (se till att utdata flushas).
Den svarta lådan löser flödesproblemet.
Läs utdata för flödesproblemet från standard input.
Översätt lösningen på flödesproblemet till en lösning på matchningsproblemet.
Skriv utdata för matchningsproblemet till standard output.
Se nedan hur in- och utdataformaten för matchnings- och flödesproblemen ser ut.

Ditt program ska lösa problemet effektivt. Kattis kommer att provköra programmet på bipartita grafer på upp till (5000+5000) hörn och upp till 10000 kanter. Kattis känner till problemet som oldkattis.adkreducetoflow (Länkar till en externa sida.) (reflektera gärna över varför problemet heter reduce TO flow).

Det finns ett programskelett för steg 1 i några olika språk på katalogen /afs/kth.se/misc/info/kurser/DD2350/adk21/labb3/exempelprogram

Steg 2: Lös flödesproblemet
Nu ska du skriva ett program som löser flödesproblemet. Programmet ska läsa indata från standard input och skriva lösningen till standard output. Se nedan hur in- och utdataformaten för flödesproblemet ser ut.

Ditt program ska lösa problemet effektivt. Kattis kommer att provköra programmet på generella flödesgrafer på upp till 2000 hörn och 10000 kanter. Kattis känner till problemet som oldkattis.adkmaxflow (Länkar till en externa sida.).

Steg 3: Kombinera steg 1 & 2
I steg 1 löste du matchningsproblemet med hjälp av en lösning till flödesproblemet. I steg 2 löste du flödesproblemet. Nu ska du kombinera dessa lösningar till ett enda program genom att byta ut kommunikationen av flödesinstansen över standard input och standard output till ett funktionsanrop. Programmet ska fortfarande läsa indata från standard input och skriva lösningen till standard output.

Ditt program ska lösa problemet effektivt. Kattis kommer att provköra programmet på bipartita grafer på upp till (5000+5000) hörn och upp till 10000 kanter. Kattis känner till problemet som oldkattis.adkbipmatch (Länkar till en externa sida.).

Matchningsproblemet
Givet en bipartit graf G = (X,Y,E) finn en maximal matchning.

Indata
Den första raden består av två heltal som anger antalet hörn i X respektive Y.
Den andra raden består av ett tal som anger |E|, det vill säga antalet kanter i grafen.
De följande |E| raderna består var och en av två heltal som svarar mot en kant.

Hörnen numreras från 1 och uppåt. Om man angett a hörn i X och b hörn i Y så låter vi X = {1, 2,..., a} och Y = {a+1, a+2,..., a+b}. En kant anges med ändpunkterna (först X-hörnet och sedan Y-hörnet).

Exempel: En graf kan till exempel kodas så här.

2 3
4
1 3
1 4
2 3
2 5
Denna graf har alltså X = {1, 2} och Y = {3, 4, 5}. Kantmängden E innehåller kanterna (1, 3), (1, 4), (2, 3) och (2, 5).

Utdata
Först skrivs en rad som är densamma som den första i indata, och därefter en rad med ett heltal som anger antalet kanter i den funna matchningen. Därefter skrivs en rad för varje kant som ingår i matchningen. Kanten beskrivs av ett talpar på samma sätt som i indata.

Exempel: Om vi har grafen ovan som indata så kan utdata se ut så här.

2 3
2
1 3
2 5
Flödesproblemet
Givet en flödesgraf G = (V,E) finn ett maximalt flöde. Lös flödesproblemet med Edmonds-Karps algoritm, det vill säga Ford-Fulkersons algoritm där den kortaste stigen hittas med breddenförstsökning.

Ford-Fulkersons algoritm i pseudokod

c[u,v] är kapaciteten från u till v, f[u,v] är flödet, cf[u,v] är restkapaciteten.

for varje kant (u,v) i grafen do 
    f[u,v]:=0; f[v,u]:=0 
    cf[u,v]:=c[u,v]; cf[v,u]:=c[v,u] 
while det finns en stig p från s till t i restflödesgrafen do 
    r:=min(cf[u,v]: (u,v) ingår i p) 
    for varje kant (u,v) i p do 
         f[u,v]:=f[u,v]+r; f[v,u]:= -f[u,v] 
         cf[u,v]:=c[u,v] - f[u,v]; cf[v,u]:=c[v,u] - f[v,u]

Indata
Den första raden består av ett heltal som anger antalet hörn i V.
Den andra raden består av två heltal s och t som anger vilka hörn som är källa respektive utlopp.
Den tredje raden består av ett tal som anger |E|, det vill säga antalet kanter i grafen.
De följande |E| raderna består var och en av tre heltal som svarar mot en kant.

Hörnen numreras från 1 och uppåt. Om man angett a hörn i V så låter vi V = {1, 2,..., a}. En kant anges med ändpunkterna (först från-hörnet och sedan till-hörnet) följt av dess kapacitet.

Exempel: En graf kan till exempel kodas så här.

4
1 4
5
1 2 1
1 3 2
2 4 2
3 2 2
3 4 1
Utdata
Den första raden består av ett heltal som anger antalet hörn i V.
Den andra raden består av tre heltal s,t, samt flödet från s till t.
Den tredje raden består av ett heltal som anger antalet kanter med positivt flöde.
Därefter skrivs en rad för varje sådan kant. Kanten beskrivs av tre tal på liknande sätt som i indata, men i stället för kapacitet har vi nu flöde.

Exempel: Om vi har grafen ovan som indata så kan utdata se ut så här.

4
1 4 3
5
1 2 1
1 3 2
2 4 2
3 2 1
3 4 1
Testning
För att komma åt programmen på kurskatalogen på AFS behöver du antingen köra direkt på en Ubuntudator på KTH eller göra en fjärrinloggning på shell-servern student-shell.sys.kth.se (den som är anställd som amanuens använder istället staff-shell.sys.kth.se).

I katalogen /afs/kth.se/misc/info/kurser/DD2350/adk21/labb3 ligger programmen bipgen, flowgen, maxflow, combine och matchtest som du kan köra för att testa dina program.

Programmet bipgen genererar en slumpvis vald bipartit graf. Grafen skrivs på standard output på ovan angivet format för indata till matchningsprogrammet. 

/afs/kth.se/misc/info/kurser/DD2350/adk21/labb3/bipgen x y e 

ger en graf med x hörn i X, y hörn i Y och e kanter.

Programmet flowgen genererar en slumpvis vald flödesgraf. Grafen skrivs på standard output på ovan angivet format för indata till flödesprogrammet. 

/afs/kth.se/misc/info/kurser/DD2350/adk21/labb3/flowgen v e c 

ger en graf med v hörn och e kanter vars kapaciteter är positiva heltal inte större än c.

Programmet maxflow löser flödesproblemet och kan användas som svart låda i steg 1. maxflow tar en flödesgraf på standard input och skriver ut ett maximalt flöde på standard output.

Programmet combine är ett hjälpprogram som du kan använda dig av i steg 1 för att få ditt program att prata med den svarta lådan. Följande kommando (som ska skrivas på en rad)

/afs/kth.se/misc/info/kurser/DD2350/adk21/labb3/combine java MatchReduce \; /afs/kth.se/misc/info/kurser/DD2350/adk21/labb3/maxflow < graffil > matchfil 

kommer att köra java MatchReduce som lösning på steg 1, och använda kursens maxflow-program som svart låda. Indatagrafen tas från filen graffil och utdata skickas till filen matchfil.
Programmet matchtest läser en graf följt av utdata från ett matchningsprogram (alltså, först grafen och sedan matchningen) och kontrollerar att matchningen är maximalt stor. Utdata skrivs på standard outputoch kan vara Matchning av maximal storlek, Matchning av mindre än maximal storlek eller Ingen matchning.
Så här kan du använda bipgen och matchtest för att testa din lösning på steg 3 (minlabb).

/afs/kth.se/misc/info/kurser/DD2350/adk21/labb3/bipgen 5000 5000 10000 > graffil 
minlabb < graffil > matchfil 
cat graffil matchfil | /afs/kth.se/misc/info/kurser/DD2350/adk21/labb3/matchtest

Bra testfall att testa de tre stegen med finns på /afs/kth.se/misc/info/kurser/DD2350/adk21/labb3/testfall/
Om du inte vet vad tecknen >, < och | betyder i exemplen ovan så kan du titta här (Länkar till en externa sida.) och här (Länkar till en externa sida.) i bash-manualen eller fråga en labbhandledare. För att kolla hur lång tid ditt program kör på dina egna testfall kan du använda kommandot time och titta på user time.
