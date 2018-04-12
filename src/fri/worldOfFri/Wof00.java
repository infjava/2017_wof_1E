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
    - peniaze (penazenka)
    - isic
    - bageta
    - ...
- vyhra
- questy
    - ziskat (rozhovor)
    - zrusit
    - splnit
        - kontrola splnenia
        - odmena
    - priklady
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
    - bufetarka (predava bagetu)
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
---------------------------
...
Npc: upratovacka
> hovor upratovacka
Dobry den, nieje toto vas isic?
1) Ano je!
2) Nie.
> 2
Dobre
---------------------------
...
Npc: upratovacka
> hovor upratovacka
Dobry den, nieje toto vas isic?
1) Ano je!
2) Nie.
> 1
Nech sa paci (da nam isic)
1) Vdaka.
2) ...
> Dobre
---------------------------
Npc: bufetarka
> hovor bufetarka
Dobry den, co si prosis?
1) Pizza (10)
2) Bagetu (5)
3  Navleky (2)
4) Nic
> 1
Nech sa paci (zaplatim a da nam pizzu)
1) Vdaka.
> Dobre
...
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
