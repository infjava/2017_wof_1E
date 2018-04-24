/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie;

import fri.worldOfFri.hra.Hrac;
import fri.worldOfFri.questy.ZiskajPredmetQuest;

/**
 *
 * @author duracik2
 */
public class MiestnostIC extends Miestnost {
    
    public MiestnostIC(String popis) {
        super(popis);
    }

    @Override
    public boolean mozemVojst(Hrac hrac) {
        if (!hrac.maPredmet("isic") && !hrac.maQuest("najdi-isic")) {
            hrac.pridajQuest(new ZiskajPredmetQuest("najdi-isic", "Musis najst kde si stratil isic", "isic"));
        }
        if (!hrac.maPredmet("navleky") && !hrac.maQuest("kup-navleky")) {
            hrac.pridajQuest(new ZiskajPredmetQuest("kup-navleky", "Chod si kupit navleky", "navleky"));
        }
        
        return hrac.maPredmet("isic") && hrac.maPredmet("navleky");
    }
    
    
}
