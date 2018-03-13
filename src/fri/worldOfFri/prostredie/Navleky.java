/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie;


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
    public void pouziSa() {
        this.obute = !this.obute;
        if (this.obute) {
            System.out.println("Obul si si navleky");
        } else {
            System.out.println("Vyzul si si navleky");
        }
    }
}
