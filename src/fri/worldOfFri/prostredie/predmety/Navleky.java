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


public class Navleky implements IPredmet {
    private boolean obute;

    public Navleky() {
        this.obute = false;
    }

    @Override
    public String getNazov() {
        return "navleky";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        this.obute = !this.obute;
        if (this.obute) {
            System.out.println("Obul si si navleky");
        } else {
            System.out.println("Vyzul si si navleky");
        }
    }

    @Override
    public boolean jeZahoditelny() {
        return !this.obute;
    }

    @Override
    public void zapisStav(DataOutputStream zapisovacSave) throws IOException {
        zapisovacSave.writeBoolean(this.obute);
    }

    @Override
    public void nacitajStav(DataInputStream citacSave) throws IOException {
        this.obute = citacSave.readBoolean();
    }
}
