/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.npc;

import fri.worldOfFri.prostredie.predmety.IPredmet;

/**
 *
 * @author duracik2
 */
public class PoziciaVRozhovoreObchod extends PoziciaVRozhovore {

    private final int cena;
    private final IPredmet predmet;
    
    public PoziciaVRozhovoreObchod(int cena, IPredmet predmet) {
        super("");
        this.cena = cena;
        this.predmet = predmet;
    }
    
}
