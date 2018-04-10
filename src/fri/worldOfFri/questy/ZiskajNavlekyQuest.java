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
public class ZiskajNavlekyQuest extends Quest {

    public ZiskajNavlekyQuest() {
        super("Pre splnenie tohto questu musis najst navleky");
    }

    @Override
    public void skontrolujSplnenie(Hrac hrac) {
        if (hrac.maPredmet("navleky")) {
            super.oznacAkoSplneny();
        }
    }
    
}
