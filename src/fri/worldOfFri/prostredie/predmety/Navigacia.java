/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.predmety;

import fri.worldOfFri.hra.Hrac;
import fri.worldOfFri.prostredie.Miestnost;
import java.util.Scanner;


public class Navigacia implements IPredmet {

    @Override
    public String getNazov() {
        return "navigacia";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.print("Zadaj cielovu miestnost: ");
        Scanner s = new Scanner(System.in);
        String nazovCielu = s.nextLine();
        Miestnost ciel = hrac.getMapa().getMiestnost(nazovCielu);
        
        if (ciel == null) {
            System.out.println("Neznama miestnost");
            return;
        }
        
        final Iterable<Miestnost> cesta = hrac.getMapa().getCesta(hrac.getAktualnaMiestnost(), ciel);
        
        if (cesta == null) {
            System.out.println("Neda sa najst cesta!");
            return;
        }
        
        Miestnost predchadzajuca = null;
        System.out.println("Cesta:");
        for (Miestnost miestnost : cesta) {
            if (predchadzajuca == null) {
                System.out.println("- " + miestnost.getNazov());
            } else {
                System.out.println("- " + predchadzajuca.getNazovVychoduDo(miestnost)
                        + " => " + miestnost.getNazov());
            }
            
            predchadzajuca = miestnost;
            
        }
    }

    @Override
    public boolean jeZahoditelny() {
        return true;
    }
    
}
