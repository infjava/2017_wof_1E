/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.predmety;

import fri.worldOfFri.hra.Hrac;
import fri.worldOfFri.prostredie.Miestnost;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class PredmetMapa implements IPredmet {

    @Override
    public String getNazov() {
        return "mapa";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.println("Miestnosti:");
        for (Miestnost miestnost : hrac.getMapa().getZoznamMiestnosti()) {
            System.out.println("- " + miestnost.getPopisMiestnosti());
            
            for (String vychod : miestnost.getVychody()) {
                Miestnost ciel = miestnost.getVychod(vychod);
                
                System.out.println("  * " + vychod + " => " + ciel.getNazov());
            }
        }
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
