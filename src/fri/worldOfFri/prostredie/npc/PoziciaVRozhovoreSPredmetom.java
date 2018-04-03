/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.npc;

import fri.worldOfFri.hra.Hrac;
import fri.worldOfFri.prostredie.predmety.IPredmet;

/**
 *
 * @author duracik2
 */
public class PoziciaVRozhovoreSPredmetom extends PoziciaVRozhovore {

    private final IPredmet predmet;
    
    public PoziciaVRozhovoreSPredmetom(String replikaNpc, IPredmet predmet) {
        super(replikaNpc);
        this.predmet = predmet;
    }

    @Override
    void vykonajAkciu(Hrac hrac) {
        super.vykonajAkciu(hrac);
        hrac.pridajPredmet(this.predmet);
    }
    
}
