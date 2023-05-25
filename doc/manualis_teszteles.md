# Manuális tesztek megtervezése, végrehajtása és dokumentálása

* A következőkben a felhasználói felület manuális tesztelését fogjuk elvégezni, hogy ellenőrizzük, hogy a játék a specifikációknak megfelelően működik-e.

## Tesztek megtervezése:

10 darab tesztet fogunk elvégezni a tesztelés során. Ezek a következők:

1. Nem bénult virológus továbblép egy szomszédos mezőre
2. Egy virológus *virusdance* ágenst ken egy másik virológusra
3. Maximum nyersanyag kapacitással, nyersanyag felvétele
4. Paralize vírus kenése
5. Paralize vírus hatása
6. A paralized állapotban lévő virológus kirablása
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

## 2. Egy virológus *virusdance* ágenst ken egy másik virológusra

Elvárt működés: Egy virológus miután felszedett elég nyersanyagot (5 db Amino, 5db Nucleo) és a vírust, azután rákeni az ellenfelére, aki emiatt a következő köre elején véletlenszerűen lép.

A képen látható, hogy a V4 és V1 virológusok egy mezőn állnak, és a V1 minden követelménnyel rendelkezik, hogy rákenje az ágenst a V4-re, de hiába nyomjuk a Rub gombot, nem történik semmi. 

Tesztelés előtti állapot:

![](manualis_teszteles_imgs/2_before.png)

Tesztelés utáni állapot:

![](manualis_teszteles_imgs/2_after.png)

Látható, hogy nem változik semmi, tehát ez a teszt sikertelen volt, felfedeztünk egy hibát a játék működésében.

## 3. Maximum nyersanyag kapacitással, nyersanyag felvétele

Elvárt működés: A nyersanyagot nem kapja meg a virológus és az a mezőn marad.

Tesztelés előtti állapot:

![](manualis_teszteles_imgs/3_before.png)

Tesztelés utáni állapot:

![](manualis_teszteles_imgs/3_after.png)

A képen látható, hogy a nyersanyag ottmaradt és a virológus pedig nem tudta felvenni a nyersanyagot, de a lépésszáma csökkent egyel, tehát a lépés maga megtörtént eredmény nélkül.

## 4. Paralize vírus kenése

Elvárt működés: A megfelelő mennyiségű nyersanyaggal (5 nukleotid és 5 aminosav) és paralize vírussal rendelkező virológus megkeni az ellenfelét. Az ellenfél ennek a hatására kirabolható és mozgásképtelen lesz. A kenő virológus nyersanyagai elfogynak.

Tesztelés előtti állapot: Látható a képen, hogy a V1 virológus rendelkezik a megfelelő darab nyersanyaggal, és egy mezőn áll a V2 virológussal:

![](manualis_teszteles_imgs/4_before.png)

Tesztelés közben: Amikor megnyomjuk a Rub gombot, felugrik egy ablak, amiben kiválaszthatjuk a kenhető ágenst:

![](manualis_teszteles_imgs/4_during.png)

Az *ok* gombot megnyomva a következő állapot fogad minket, látható, hogy a kenés sikeres volt, mert a nyersanyagok lenullázódtak és a hátralevő lépések száma csökkent 1-el.

![](manualis_teszteles_imgs/4_during_1.png)

A képen látható hogy az ellenfél megkenődött (az Active effects-nél megjelent a paralizzed virus). A nyersanyagok elfogytak és az ellenfél mozgásképtelenné vált.

![](manualis_teszteles_imgs/4_during_2.png)

## 5. Paralize vírus hatása

Elvárt működés: A paralized állapotban lévő virológus mozgásképtelen, szomszédos mezőre kattintva nem mozog, csak a lépései fogynak.

Tesztelés előtti állapot:

![](manualis_teszteles_imgs/5_before.png)

A képen látható, hogy a virológus nem mozog, viszont a lépései fogytak.

![](manualis_teszteles_imgs/5_after.png)

## 6. A paralized állapotban lévő virológus kirablása

Elvárt működés: A paralized virológus, akinek vannak equipmentjei kirabolhatóvá válik. A másik virológus megkapja az adott equipmentet és a paralized virológus pedig elveszti azt.

Tesztelés előtti állapot: A képen látható, hogy a V2 virológus bénult állapotban van, valamint van nála egy balta az Equipmentek közt

![](manualis_teszteles_imgs/6_before.png)

Tesztelés közben: A V1 virológus rabolni akar a V2-től, és feljön egy ablak,hogy mit akar elrabolni tőle:

![](manualis_teszteles_imgs/6_during.png)

A képeken látható, hogy a rabló virológus megkapja a paralized virológus eqipmentjét aki pedig elveszti azt. Tesztelés utáni állapot:

![](manualis_teszteles_imgs/6_after.png)