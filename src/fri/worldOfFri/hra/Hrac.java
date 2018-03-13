/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.hra;

import fri.worldOfFri.prostredie.Bageta;
import fri.worldOfFri.prostredie.IPredmet;
import fri.worldOfFri.prostredie.Mapa;
import fri.worldOfFri.prostredie.Miestnost;
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

    Hrac() {
        Mapa mapa = new Mapa();
        
        this.aktualnaMiestnost = mapa.getStartovaciaMiestnost();
        this.inventar = new HashMap<String, IPredmet>();
        
        this.hungerBar = Hrac.MAX_HUNGER_BAR;
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }
    
    public boolean chodSmerom(String smer) {
        if (this.hungerBar == 0) {
            return false;
        }
        
        Miestnost nova = this.aktualnaMiestnost.getVychod(smer);
        
        if (nova != null) {
            this.aktualnaMiestnost = nova;
            this.hungerBar--;
            return true;
        } else {
            return false;
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
        IPredmet predmet = this.inventar.remove(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
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
}
