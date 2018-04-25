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


public class Penazenka implements IPredmet {

    private int mnozstvoPenazi;

    public Penazenka() {
        this.mnozstvoPenazi = 100;
    }
    
    @Override
    public String getNazov() {
        return "penazenka";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.println("Penazenku nemozes pouzit priamo.");
        System.out.println("Momentalne ma "+this.mnozstvoPenazi);
    }

    @Override
    public boolean jeZahoditelny() {
        return false;
    }

    public boolean zaplat(int suma) {
        if (suma <= this.mnozstvoPenazi) {
            this.mnozstvoPenazi -= suma;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void zapisStav(DataOutputStream zapisovacSave)
            throws IOException {
        zapisovacSave.writeInt(this.mnozstvoPenazi);
    }

    @Override
    public void nacitajStav(DataInputStream citacSave)
            throws IOException {
        this.mnozstvoPenazi = citacSave.readInt();
    }
    
}
