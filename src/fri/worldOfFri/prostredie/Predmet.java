/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie;

/**
 *
 * @author janik
 */
public class Predmet {
    private final String nazov;
    private boolean obuty;

    Predmet(String nazov) {
        this.nazov = nazov;
        this.obuty = false;
    }

    public String getNazov() {
        return this.nazov;
    }

    public void pouziSa() {
        this.obuty = !this.obuty;
        if (this.obuty) {
            System.out.println("Obul si si " + this.nazov);
        } else {
            System.out.println("Vyzul si si " + this.nazov);
        }
    }
}
