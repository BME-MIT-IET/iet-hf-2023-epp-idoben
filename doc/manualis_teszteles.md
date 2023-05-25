# Manuális tesztek megtervezése, végrehajtása és dokumentálása

* A következőkben a felhasználói felület manuális tesztelését fogjuk elvégezni, hogy ellenőrizzük, hogy a játék a specifikációknak megfelelően működik-e.

## Tesztek megtervezése:

10 darab tesztet fogunk elvégezni a tesztelés során. Ezek a következők:

1. Nem bénult virológus továbblép egy szomszédos mezőre
2.
3.
4.
5.
6.
7.
8.
9.
10.

## Tesztek végrehajtása:

### 1.  Nem bénult virológus továbblép egy szomszédos mezőre:

A képen látható a tesztelés előtti állapot. A V1 virológus van soron, aki nincs lebénulva, hiszen nincs rajta semmilyen *Active effect*.

![](manualis_teszteles_imgs/1_before.png)

A funkció teszteléséhez rákattintunk az egyik szomszédos mezőre, hogy lássuk, hogy megtörténik-e az elvárt változás.

A képen látható, hogy a lépés sikeres volt, a V1 virológus átlépett a szomszédos mezőre, és a remaining steps értéke csökkent 1-el, hiszen végrehajtott egy lépést.

![](manualis_teszteles_imgs/1_after.png)

