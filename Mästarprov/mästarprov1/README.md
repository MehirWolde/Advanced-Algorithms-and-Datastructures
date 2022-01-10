Uppgift 2 Antal sätt att producera ett prov

Betygskriterium: utveckla algoritmer med datastrukturer för icketriviala problem.

N stycken lärare ska producera x stycken uppgifter till ett prov. Lärare nummer i kan göra mellan 0 och u[i] uppgifter, där u[i] är högst 10. På hur många olika sätt kan man fördela jobbet?

Exempel: Två lärare (N=2) som var för sig kan göra max 2 uppgifter (u[1]=u[2]=2) kan producera ett mästarprov med 3 stycken uppgifter (x=3) på två olika sätt: 1+2 eller 2+1.

Deluppgift 2.1

Konstruera en algoritm som givet N, x och u[1..N] beräknar antalet sätt som jobbet kan fördelas på. Om det inte finns något sätt ska 0 returneras.

Beskriv algoritmen med pseudokod och analysera dess tidskomplexitet. Tidskomplexiteten måste vara polynomisk i N, dvs O(Nc) för en konstant c. Motivera också att algoritmen är korrekt.

Deluppgift 2.2

Skriv en funktion print_all_ways som skriver ut alla sätt som jobbet kan fördelas på. I exemplet ovan ska funktionen alltså skriva ut:

1+2
2+1

Det finns inget krav på i vilken ordning delresultaten ska skrivas ut.

Även denna algoritm ska beskrivas med pseudokod. Utöver tydlig och väldokumenterad pseudokod behöver du däremot inte motivera korrekthet och du behöver inte heller analysera tidskomplexiteten.
