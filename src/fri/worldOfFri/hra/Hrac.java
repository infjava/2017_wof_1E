/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.hra;

import fri.worldOfFri.prostredie.Mapa;
import fri.worldOfFri.prostredie.Miestnost;
import fri.worldOfFri.prostredie.Predmet;
import java.util.HashMap;

/**
 *
 * @author janik
 */
class Hrac {
    private Miestnost aktualnaMiestnost;
    private HashMap<String, Predmet> inventar;

    Hrac() {
        Mapa mapa = new Mapa();
        
        this.aktualnaMiestnost = mapa.getStartovaciaMiestnost();
        this.inventar = new HashMap<String, Predmet>();
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }
    
    public boolean chodSmerom(String smer) {
        Miestnost nova = this.aktualnaMiestnost.getVychod(smer);
        
        if (nova != null) {
            this.aktualnaMiestnost = nova;
            return true;
        } else {
            return false;
        }
    }

    boolean zoberPredmet(String nazovPredmetu) {
        Predmet predmet = this.aktualnaMiestnost.zoberPredmet(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        this.inventar.put(predmet.getNazov(), predmet);
        return true;
    }

    void zobrazStaty() {
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
        Predmet predmet = this.inventar.remove(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        this.aktualnaMiestnost.polozPredmet(predmet);
        return true;
    }

    void pouziPredmet(String nazovPredmetu) {
        Predmet predmet = this.inventar.get(nazovPredmetu);
        
        if (predmet == null) {
            return;
        }
        
        predmet.pouziSa();
    }
}
