/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.hra;

import fri.worldOfFri.prostredie.predmety.IPredmet;
import fri.worldOfFri.prostredie.Mapa;
import fri.worldOfFri.prostredie.Miestnost;
import fri.worldOfFri.prostredie.npc.Npc;
import fri.worldOfFri.prostredie.predmety.Penazenka;
import java.util.HashMap;

/**
 *
 * @author janik
 */
public class Hrac {
    private static final int MAX_HUNGER_BAR = 15;
    
    private Miestnost aktualnaMiestnost;
    private HashMap<String, IPredmet> inventar;
    private int hungerBar;
    private final Mapa mapa;

    Hrac() {
        this.mapa = new Mapa();
        
        this.aktualnaMiestnost = this.mapa.getStartovaciaMiestnost();
        this.inventar = new HashMap<String, IPredmet>();
        
        this.hungerBar = Hrac.MAX_HUNGER_BAR;
        
        this.inventar.put("penazenka", new Penazenka());
    }

    public Mapa getMapa() {
        return this.mapa;
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }
    
    public boolean chodSmerom(String smer) {
        if (this.hungerBar == 0) {
            System.out.println("Mas prazdny hungerBar");
            return false;
        }
        
        Miestnost nova = this.aktualnaMiestnost.getVychod(smer);
        if (nova == null) {
            System.out.println("Tam nie je vychod!");
            return false;
        }
        else if (!nova.mozemVojst(this)) {
            System.out.println("Do miestnosti nemozes vojst!");
            return false;
        } else {
            this.aktualnaMiestnost = nova;
            this.hungerBar--;
            return true;
        }
    }

    boolean zoberPredmet(String nazovPredmetu) {
        IPredmet predmet = this.aktualnaMiestnost.zoberPredmet(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        this.inventar.put(predmet.getNazov(), predmet);
        return true;
    }

    void zobrazStaty() {
        System.out.println("Hunger bar: " + this.hungerBar);
        
        if (!this.inventar.isEmpty()) {
            System.out.println("Inventar:");
            
            for (String predmet : this.inventar.keySet()) {
                System.out.println("- " + predmet);
            }
        } else {
            System.out.println("Mas prazdny inventar");
        }
    }

    boolean zahodPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.get(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        if (!predmet.jeZahoditelny()) {
            return false;
        }
        
        this.inventar.remove(nazovPredmetu);
        this.aktualnaMiestnost.polozPredmet(predmet);
        return true;
    }

    void pouziPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.get(nazovPredmetu);
        
        if (predmet == null) {
            return;
        }
        
        predmet.pouziSa(this);
    }

    public void zjedz(int energia) {
        this.hungerBar += energia;
        
        if (this.hungerBar > Hrac.MAX_HUNGER_BAR) {
            this.hungerBar = Hrac.MAX_HUNGER_BAR;
        }
    }

    public void zrusPredmet(IPredmet predmet) {
        this.inventar.remove(predmet.getNazov());
    }

    void hovorSNpc(String menoNpc) {
        Npc npc = this.aktualnaMiestnost.getNpc(menoNpc);
        
        if (npc == null) {
            return;
        }
        
        npc.rozhovor(this);
    }

    public void pridajPredmet(IPredmet predmet) {
        this.inventar.put(predmet.getNazov(), predmet);
    }

    public boolean maPredmet(String nazov) {
        return this.inventar.containsKey(nazov);
    }

    public boolean zaplat(int suma) {
        if (!this.maPredmet("penazenka")) {
            return false;
        }
        /*IPredmet predmet = this.inventar.get("penazenka");
        
        if (predmet instanceof Penazenka) {
            Penazenka penazenka = (Penazenka) predmet;
            return penazenka.zaplat(suma);
        }*/
        Penazenka penazenka = (Penazenka) this.inventar.get("penazenka");
        return penazenka.zaplat(suma);
    }
}
