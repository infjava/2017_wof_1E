/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.predmety;

import fri.worldOfFri.hra.Hrac;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class Bageta implements IPredmet {

    @Override
    public String getNazov() {
        return "bageta";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.println("Zjedol si bagetu");
        
        hrac.zjedz(15);
        hrac.zrusPredmet(this);
    }

    @Override
    public boolean jeZahoditelny() {
        return true;
    }

    @Override
    public void zapisStav(DataOutputStream zapisovacSave) {
        
    }

    @Override
    public void nacitajStav(DataInputStream citacSave) {
        
    }
}
