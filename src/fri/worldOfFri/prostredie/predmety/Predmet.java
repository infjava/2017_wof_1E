/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.predmety;

import fri.worldOfFri.hra.Hrac;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author janik
 */
public class Predmet implements IPredmet {
    private final String nazov;

    public Predmet(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.println(this.nazov + " sa neda nijako pouzit");
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
