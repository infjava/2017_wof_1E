/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie;

import fri.worldOfFri.prostredie.predmety.Bageta;
import fri.worldOfFri.prostredie.predmety.Peniaze;
import fri.worldOfFri.prostredie.predmety.Navleky;

/**
 *
 * @author janik
 */
public class Mapa {

    private final Miestnost startovaciaMiestnost;

    /**
     * Vytvori mapu hry - miestnosti.
     */
    public Mapa() {
        Miestnost terasa = new Miestnost("Terasa - tu byva FRIfest");
        Miestnost vratnica = new Miestnost("Vratnica - tu byva vratnicka");
        Miestnost ic = new Miestnost("IC - tu byvaju knihy");
        Miestnost chodbaA = new Miestnost("Chodba A - tu byva prvy automat na kavu");
        Miestnost wc = new Miestnost("WC - tu byva smrad");
        Miestnost bufet = new Miestnost("Bufet - tu byva jedlo");
        Miestnost chodbaB = new Miestnost("Chodba B - tu byva druhy automat na kavu");
        Miestnost chillZone = new Miestnost("Chill Zone - tu byva vela studentov");
        Miestnost b1 = new Miestnost("B1 - tu byva ticho");
        
        terasa.nastavVychod("vychod", vratnica);
        
        terasa.polozPredmet(new Navleky());
        terasa.polozPredmet(new Bageta());
        terasa.polozPredmet(new Peniaze());
        
        vratnica.nastavVychod("vychod", ic);
        vratnica.nastavVychod("zapad", terasa);
        vratnica.nastavVychod("sever", chodbaA);
        vratnica.nastavVychod("juh", chodbaB);
        
        ic.nastavVychod("zapad", vratnica);
        
        chodbaA.nastavVychod("vychod", wc);
        chodbaA.nastavVychod("zapad", bufet);
        chodbaA.nastavVychod("juh", vratnica);
        
        wc.nastavVychod("zapad", chodbaA);
        
        bufet.nastavVychod("vychod", chodbaA);
        
        chodbaB.nastavVychod("zapad", b1);
        chodbaB.nastavVychod("sever", vratnica);
        chodbaB.nastavVychod("juh", chillZone);
        
        b1.nastavVychod("vychod", chodbaB);
        
        chillZone.nastavVychod("sever", chodbaB);
        
        this.startovaciaMiestnost = terasa;
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }
}
