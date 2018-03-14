/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.predmety;

import fri.worldOfFri.hra.Hrac;

/**
 *
 * @author janik
 */
public interface IPredmet {

    String getNazov();

    void pouziSa(Hrac hrac);
    
    boolean jeZahoditelny();
}
