# Tesztelés a Cucumber tesztkörnyezet használatával

## A tesztelési környezet beüzemelése (Cucumber)

A BDD (Behaviour Driven Development) tesztelési módszerhez a Cucuber eszközt választottuk. A Cucumber teszteléshez a több Maven függőséget adtunk hozzá a `pom.xml` fájlhoz. A tesztek a `src/test` mappában találhatóak. Elsőként kipróbáltuk, hogy a tesztek lokálisan meűködnek-e. Miután sikerült őket futtatni, beleépítettük a tesztelést a Maven teszt folyamatába, így a GitHub Actions segítségével a tesztek minden a pull request esetén is lefutnak.

## Tesztek megírása

A Cucumber eszközzel X tesztet készítettünk. A tesztek a `src/test/resources` mappában találhatóak. A tesztek a `*.feature` fájlokban vannak leírva. A tesztekhez tartozó implementációk a `*.java` fájlokban találhatóak. A tesztek futtatásához a `mvn test` parancsot kell kiadni a projekt gyökérkönyvtárában. Ez fut automatikusan a GitHub felületén is minden push esetén.

***írjunk még a tesztekről***