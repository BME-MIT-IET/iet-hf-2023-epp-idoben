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
7. Equipment felvétele a mezőről
8. Maximális kapacitás elérése equipmentekből
9. Genetikai kód megtanulása
10. Összes vírus megtanulása, játék vége

A tesztelés során a célunk minél több ui funkció kipróbálása manuális módon.

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

## 7. Equipment felvétele a mezőről

Tesztelt funkció: A teszt során azt ellenőrizzük, hogy egy virológus fel tud-e venni egy equipmentet a mezőről, és utána bekerül-e az equipmentek közé.

Teszt előtti állapot: látjuk, hogy a V1 virológus a mezőn áll, ahol található egy Protection equipment. 

![](manualis_teszteles_imgs/7_before.png)

Teszt utáni állapot: Látható, hogy sikeresen felvettük a mezőről az equipmentet, és bekerült az inventory-ba, valamint a mezőről eltűnt.

![](manualis_teszteles_imgs/7_after.png)

## 8. Maximális kapacitás elérése equipmentekből

Elvárt viselkedés: A virológus már rendelkezik 3 equipmentel, de megpróbál felvenni egy 4.et. Az equipment nem kerül a virológushoz és lent marad a mezőn.

Tesztelés előtti állapot: A V1 virológus invevóntoryjában 3 db. equipment található, és olyan mezőn áll, ahol van még egy 4.

![](manualis_teszteles_imgs/8_before.png)

Tesztelés utáni állapot: Ahogyan a képen is látszik az equipment a mezőn maradt és a virológus nem kapta meg, valamint a lépésszáma csökkent, ami mutatja, hogy megpróbálta felvenni a mezőről.


![](manualis_teszteles_imgs/8_after.png)

## 9. Genetikai kód megtanulása

Elvárt viselkedés: A virológus aki még nem rendelkezik egy adott vírussal, egy mezőn van ahol található ez a vírus. A pickupra kattintva megtanulja a vírus genetikai kódját, de a vírus a mezőn marad, mivel az permanens.

Tesztelés előtti állapot: A V1 virológus barlang mezőn áll, és nincs még egyetlen megtanult genetikai kódja sem:

![](manualis_teszteles_imgs/9_before.png)

Ahogyan az a képen is látszik, a vírust a virológus megtanulta és a genetikai kód a mezőn maradt, mert azt megtanulhatja más is.

![](manualis_teszteles_imgs/9_after.png)

## 10. Összes vírus megtanulása, játék vége

Elvárt viselkedés: Amikor a virológus az utolsó vírust is megtanulta, akkor a játéknak vége lesz.

Teszt előtti állapot: A képen látható, hogy V1 virológus megtanult már 3 genetikai kódot, a nyeréshez szükséges 4-ből, és olyan mezőn áll, ahol az utolsót is megtanulhatja.

![](manualis_teszteles_imgs/10_before.png)

Ahogyan az a képen is látszik a játéknak vége lett, hiszen nyert a V1 játékos az utolsó genetikai kód megtanulásával:

![](manualis_teszteles_imgs/10_after.png)


## Összefoglalás:

A tesztelés során 10-ből 9 helyesen, az elvárt módon működött. Nehézségeket egyrészt az okozott, hogy már emlékeztünk a megvalósított projekt részleteire, másrészt a UI nem volt valami felhasználóbarát. Látható, hogy a projektlabor tárgy esetén nem fektettünk nagy hangsúlyt a felhasználói élményre, pedig egy játék esetén ez egy fontos szempont lenne. 