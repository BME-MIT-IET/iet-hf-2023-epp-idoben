# Tesztelés a Cucumber tesztkörnyezet használatával

## A tesztelési környezet beüzemelése (Cucumber)

A BDD (Behaviour Driven Development) tesztelési módszerhez a Cucuber eszközt választottuk. A Cucumber teszteléshez a több Maven függőséget adtunk hozzá a `pom.xml` fájlhoz. A tesztek a `src/test` mappában találhatóak. Elsőként kipróbáltuk, hogy a tesztek lokálisan meűködnek-e. Miután sikerült őket futtatni, beleépítettük a tesztelést a Maven teszt folyamatába, így a GitHub Actions segítségével a tesztek minden a pull request esetén is lefutnak.

## Tesztek megírása

A Cucumber eszközzel 12 tesztet készítettünk 64 steppel. A tesztek a `src/test/resources` mappában találhatóak. A tesztek a `*.feature` fájlokban vannak leírva. A tesztekhez tartozó implementációk a `*.java` fájlokban találhatóak. A tesztek futtatásához a `mvn test` parancsot kell kiadni a projekt gyökérkönyvtárában. Ez fut automatikusan a GitHub felületén is minden push esetén.

## Tesztek

A tesztek a projekt különböző függvényeit tesztelik. Igyekeztünk minél több use-case-t lefedni és ezáltal minél teljesebbé tenni a tesztelést. Teszteltük például a mozgást, a támadásokat és a nyersanyagokkal illetve eszközökkel való interrakciókat. A tesztelés során problémát okozott, hogy bizonyos változókhoz nem tartoztak getterek, így néha bonyolult volt jó assertet írni. A cucumber előnye, hogy kellemes olvani a teszteseteket és az egyes kifejezések újra felhasználhatóak, így idővel egyre gyorsabbá válik a tesztesetek írása, hiszen használhatóak a korábban megírt függvények is.