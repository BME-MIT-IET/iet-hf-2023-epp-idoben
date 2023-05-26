# Manuális kód átvizsgálás +  Statikus analízis eszköz futtatása és jelzett hibák átnézése és javítása

A feladat elvégzéséhez IntelliJ-ben használtuk a SonarLint plug-int, annak segítségével csináltuk a statikus kódanalízist, a gyakorlatokon látottak alapján kiindulva. A folyamat során törekedtünk arra, hogy minél különbözőbb fajta hibákat javítsunk ki, hogy minél nagyobb részét lefedhessük a hibáknak. A feladatot Vass Annamária és Kiovics Dániel végezték.

## 1. lépés: SonarLint beüzemelése

A SonarLint plug-in marketplace-ről letöltöttük a megfelelő eszközöket, majd a projektünkhöz hozzáadtuk, így elkezdhettük a munkát.

## MainWindow.java fájlban található hibák javítása:

### 1. hiba:

A egy attribútumot aminek a típusa JMenuBar és a neve menuBar módosítottunk, mert a neve elfedte a Frame objektum menuBar attribútumát. Ezért a SonarLint által javasolt megoldás alapján megváltoztattuk a nevét topMenuBar-ra, lokális, valamint final változóvá tettük. Ez egy kritikus code-smell volt.

### 2. hiba:

A *ClickListener* osztály megvalósítja a MouseListener interface-t, és a SonarLint bejelez, amiért a *mouseClicked* nevű függvényt üres implementációval felülírjuk, viszont ezt muszáj bennehagynunk, hiszen az interface megvalósításához szükséges a függvény.

### 3. hiba:

A *MainWindow* konstruktorban hiba volt, hogy a font-ok létrehozásnál kétszer használjuk az "Arial" stringet a Font típusának megadására, és ez egy code-smellt eredményez. A hibát úgy javítottuk, hogy bevezettünk egy változót a az "Arial" stringre, és mindenhol ezt használtuk.

### 4. hiba:

A fájlban levő összes osztályunk a GUI package-ben van, viszont ez nem követi az elnevezési konvenciót, ezért átneveztük gui-ra, hiszen Java-ban kisbetűsek a nevek. Ez egy minor code-smell volt.

### 5. hiba:

2 helyen is switch statementet használtunk egy case lehetőséggel, ezért ezeket helyettesítettük egy-egy if-el, mert nem volt értelme a switch-nek. Ez egy minor code-smell volt.

### 6.hiba:
A virologistClicked függvény a megengedett 15-ös komplexitás helyett 19-es volt, ezért szétbontottuk kisebb függvényekre, annak érdekében, hogy ne legyenek olvashatatlan metódusaink. Ez egy Major code-smell volt.

### 7.hiba:
A handleRob függvényben találtunk egy felesleges string-gé castolást, ezért ezt eltávolítottuk a kódból, hogy olvashatóbb legyen.

### 8.hiba:
Az actionPerformed függvényben egy helyen eltávolítottunk egy lambdát, ami helyett egy függvényreferenciát használtunk, így csökkentve a felesleges komplexitást

## Virologist.java fájlban található hibák javítása:

### 9. hiba:

A SonarLint segítségével találtunk 2 változót az osztályban, amiket sehol nem használunk, ezért töröltük őket. Ezek a requiredAmino és reqiredNucleo attribútumok voltak. Ez egy critical code-smell volt.

### 10. hiba:

A *toString* metódusban StringBuilder használata helyett sztring összefűzéseket használtunk, ami nem a legszebb és legminőségibb megoldás, úgyhogy módosítottuk a kódot, hogy StringBuilder segítségével építse fel a string-eket.

### 11. hiba:

A Virologist toString függvényében 3 helyen is az ArrayList.isEmpty() függvénye helyett azt vizsgáltuk, hogy a lista mérete 0-e? Ezt javítottuk, hiszen az első az sokkal elegánsabb megoldás.

## Game.java fájlban található hibák javítása:

### 12. hiba:

A *loadGame* függvényben használt br BufferedReader erőforrást nem zártuk be, erre bejelzett a SonarLint. Beleírtuk a kódba, hogy bezárjuk az erőforrást, try-cattch-finally blokkot használva, hiszen ez egy Blocker súlyosságú code-smell volt.

### 13. hiba:

A *saveMap* nevű függvényben az indentálás hibásan volt, és egy if statement után bejelzett a SonarLint, hogy a második sor már feltétel nélkül lefut. Ezt kijavítottuk, hiszen a jól érthető kód fontos jellemzője, hogy helyesen van indentálva.

## Field.java fájlban található hibák javítása:

### 14. hiba:

A Field absztrakt osztály láthatóságát megváltoztattuk protectedre publicról, hiszen úgyis csak a leszármazottakban használható. Ez egy Major code-smell volt.

### 15. hiba:

A *GetRandomNeigbour* függvényben az (int)Math.Random helyett a SonarLint azt ajánlotta, hogy használjunk java.util.Random.nextInt()-et, hiszen integer változóra van szükségünk, nem float-ra.

## SafeHouse.java fájlban található hibák javítása:

### 16. hiba:

A *Collect* nevű függvényben találtunk összevonható if-feltételeket, így a 2 db. if-et lecseréltünk 1-re, ahelyett, hogy feleslegesen 2 használnánk. Később máshol is találtunk ilyen hibát, azokat is javítottuk.

## Triangle.java fájlban található hibák javítása:

### 17. hiba:

A Triangle osztályban található x és y attribútumok elfedik a Component x és y attrbútumait, ezért megváltoztattuk azok nevét.

## Resources.java fájlban található hibák javítása:

### 18. hiba:

A Resources absztrakt osztályban a Java konvenciók szerint nem jó sorrendben adtuk meg az osztály adattagjait, ezért módosítottunk, hogy láthatóság szerint helyes sorrendben legyenek.

## Timer.java fájlban található hibák javítása:

### 19. hiba:

 Észrevettük, hogy a Timer osztályban a *Tick* függvény teljesen feleslegesen vesz részt a projektben. A Tick függvény a paraméterül kapott Virologist-ot lépteti, de ezen kívül semmi funkciója nincs. így amikor ezt használnánk, simán meghívjuk a Virologist Step függvényét.

## Bear.java fájlban található hibák javítása:

### 20. hiba:

Voltak függvények, ahol nem volt Override annotáció, pedig az ős viselkedését írtuk felül benne. Ezeket javítottuk a kód szépségének és olvashatóságának érdekében. Ezeket még 10 másik fájlban is megtaláltuk, és kijavítottuk, hiszen Major code-smellek voltak.

## Bear.java fájlban található hibák javítása:

### 21. hiba:

Nem használt importokat távolítottunk el többekközött ebből a fájlból, de más fájlokból is. Ennek segítségével nem maradt felesleges kódunk.

## Összefoglalás:

Összességében úgy látjuk, hogy sok-sok szépítenivaló volt még a kódon, mindenképpen látszik, hogy eléggé siettünk a játék implementálásánál és ez nagyon sokszor a kód átláthatóságának, szépségének és minőségének a rovására ment. Nagyon hasznos eszköznek ítéljük meg a SonarLint plug-int, amely nagy segítségünkre volt a kód elemzés és analizálás esetén.


