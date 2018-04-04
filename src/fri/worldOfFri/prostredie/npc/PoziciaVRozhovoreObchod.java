/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.npc;

import fri.worldOfFri.hra.Hrac;
import fri.worldOfFri.prostredie.predmety.Bageta;
import fri.worldOfFri.prostredie.predmety.IPredmet;
import fri.worldOfFri.prostredie.predmety.Isic;
import fri.worldOfFri.prostredie.predmety.Navleky;
import fri.worldOfFri.prostredie.predmety.Predmet;

/**
 *
 * @author duracik2
 */
public class PoziciaVRozhovoreObchod extends PoziciaVRozhovore {

    private final int cena;
    private final String predmet;
    
    public PoziciaVRozhovoreObchod(int cena, String predmet) {
        super("");
        this.cena = cena;
        this.predmet = predmet;
    }

    @Override
    void vykonajAkciu(Hrac hrac) {
        //Zaplat
        
        switch (this.predmet) {
            case "bageta":
                hrac.pridajPredmet(new Bageta());
                break;
            case "navleky":
                hrac.pridajPredmet(new Navleky());
                break;
            default:
                hrac.pridajPredmet(new Predmet(this.predmet));
        }
    }
    
    
}
