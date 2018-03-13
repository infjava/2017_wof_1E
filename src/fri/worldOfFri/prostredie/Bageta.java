/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie;


public class Bageta implements IPredmet {

    @Override
    public String getNazov() {
        return "bageta";
    }

    @Override
    public void pouziSa() {
        System.out.println("Zjedol si bagetu");
    }
}
