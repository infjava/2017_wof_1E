/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie;

import fri.worldOfFri.hra.Hrac;

/**
 *
 * @author duracik2
 */
public class MiestnostIC extends Miestnost {
    
    public MiestnostIC() {
        super("IC - tu byvaju knihy");
    }

    @Override
    public boolean mozemVojst(Hrac hrac) {
        return hrac.maPredmet("isic") && hrac.maPredmet("navleky");
    }
    
    
}
