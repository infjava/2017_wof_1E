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
public class Peniaze implements IPredmet {

    @Override
    public String getNazov() {
        return "peniaze";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        if (hrac.getAktualnaMiestnost().getPopisMiestnosti().startsWith("Chodba ")) {
            System.out.println("Automat ti dal kavu");
            hrac.getAktualnaMiestnost().polozPredmet(new Predmet("kava"));
            hrac.zrusPredmet(this);
        } else {
            System.out.println("Tu nevies nijako pouzit peniaze");
        }
    }

    @Override
    public boolean jeZahoditelny() {
        return true;
    }
}
