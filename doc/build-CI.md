# Build keretrendszer beüzemelése + CI beüzemelése

## Build keretrendszer beüzemelése
A létező Java projektet Maven projektté konvertáltuk az IntelliJ segítségével. A `pom.xml` fájlban a szükséges függőségeket hozzádtuk a JUnit beüzemeléséhez.

## CI beüzemelése
A GitHub Actions használatához hozzádtuk a projekthez a `maven.yml` fájlt, amely a CI folyamatot definiálja. Kipróbáltuk a működését, a GitHub Actions fülön megjelent a build folyamat, amely sikeresen lefutott.

## Tesztelhetőség hozzáadása
A projektet a GitHub Actions segítségével tesztelhetővé tettük, mely minden push esetén lefut. A tesztek a `src/test/java` mappában találhatóak. A tesztek futtatásához a `mvn test` parancsot kell kiadni a projekt gyökérkönyvtárában. Ez fut automatikusan a GitHub felületén is minden push esetén.

Egy példa tesztet hoztunk létre ami azt teszteli, hogy a lépés esetén a *Virológus* elmozdul-e a jelenlegi mezőről a szomszédos mezőre.