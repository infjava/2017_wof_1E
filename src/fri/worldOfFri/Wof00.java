package fri.worldOfFri;


import fri.worldOfFri.hra.Hra;

/**
 * Hlavna trieda hry WoF s metodou main - spustanie v NB
 * 
 * @author Lubomir Sadlon
 * @version 21.2.2012
 */
/*
- itemy
    - atributy
        - nazov
    - operacie:
        - ziskat
            > zober bageta
        - zahodit
            > zahod bageta
        - pouzit
            > pouzi navleky
        - predat
        - kupit
        - preskumat
        - znicit
        - zobraz staty
            > staty
    - kava z automatu
    - navleky
    - peniaze
    - isic
    - bageta
    - ...
- vyhra
- questy
    - dones mi kavu
    - stratil som isic
    - ziskaj navleky
    - dostat sa do labaku (pomocou isicu a v navlekoch)
- npc
    - vratnicka
    - upratovacka (nasla isic)
    - dekan (chce kavu)
    - studijne referentky
    - student nejaky
- talenty (skills)
    - teleport po 3 kavach
    - nerostrhnutie navlekov
- zivoty + hunger
- crafting
- nepriatelia
*/
/*
...
Npc: dekan
> hovor dekan
Dobry den.
1) Dobry!
2) Hee?
> 1
Chcem kavu.
1) Okej, zozeniem Vam
2) Co za to?
3) Aj ja
> 1
Dobre
---------------------------
...
Npc: dekan
> hovor dekan
Dobry den.
1) Dobry!
2) Hee?
> 1
Chcem kavu.
1) Okej, zozeniem Vam
2) Co za to?
3) Aj ja
> 2
Bagetu
1) Okej, zozeniem Vam
2) Nechcem
> 1
Dobre
---------------------------
...
Npc: dekan
> hovor dekan
Dobry den.
1) Dobry!
2) Hee?
> 1
Chcem kavu.
1) Okej, zozeniem Vam
2) Co za to?
3) Aj ja
> 2
Bagetu
1) Okej, zozeniem Vam
2) Nechcem
> 2
Dobre
---------------------------
...
Npc: dekan
> hovor dekan
Dobry den.
1) Dobry!
2) Hee?
> 1
Chcem kavu.
1) Okej, zozeniem Vam
2) Co za to?
3) Aj ja
> 3
Fight!
---------------------------
...
Npc: dekan
> hovor dekan
Dobry den.
1) Dobry!
2) Hee?
> 2
Fight!
*/
public class Wof00 {

    /**
     * @param args parametre programu
     */
    public static void main(String[] args) {
        Hra hra = new Hra();
        hra.hraj();
    }
}
