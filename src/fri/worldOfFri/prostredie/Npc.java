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
public class Npc {

    private final String meno;
    private final PoziciaVRozhovore zaciatocnaPozicia;

    Npc(String meno, PoziciaVRozhovore zaciatocnaPozicia) {
        this.meno = meno;
        this.zaciatocnaPozicia = zaciatocnaPozicia;
    }

    String getMeno() {
        return this.meno;
    }
    
    public void rozhovor() {
        System.out.println(this.zaciatocnaPozicia.getReplikaNpc());
        
        this.zaciatocnaPozicia.vypisMoznosti();
    }
}
