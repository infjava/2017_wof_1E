/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.questy;

import fri.worldOfFri.hra.Hrac;

/**
 *
 * @author duracik2
 */
public class ZiskajPredmetQuest extends Quest {

    private String nazovPredmetu;
    
    public ZiskajPredmetQuest(String popis, String nazovPredmetu) {
        super(popis);
        this.nazovPredmetu = nazovPredmetu;
    }

    @Override
    public void skontrolujSplnenie(Hrac hrac) {
        if (hrac.maPredmet(nazovPredmetu)) {
            super.oznacAkoSplneny();
        }
    }
    
}
