/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.predmety;

import fri.worldOfFri.hra.Hrac;


public class Isic implements IPredmet {

    @Override
    public String getNazov() {
        return "isic";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        
    }

    @Override
    public boolean jeZahoditelny() {
        return true;
    }
    
}
