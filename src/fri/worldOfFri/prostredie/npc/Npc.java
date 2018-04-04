/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.npc;

import fri.worldOfFri.hra.Hrac;
import java.util.Scanner;

/**
 *
 * @author janik
 */
public class Npc {

    private final String meno;
    private final PoziciaVRozhovore zaciatocnaPozicia;

    public Npc(String meno, PoziciaVRozhovore zaciatocnaPozicia) {
        this.meno = meno;
        this.zaciatocnaPozicia = zaciatocnaPozicia;
    }

    public String getMeno() {
        return this.meno;
    }
    
    public void rozhovor(Hrac hrac) {
        Scanner vstup = new Scanner(System.in);
        
        PoziciaVRozhovore aktualna = this.zaciatocnaPozicia;
        
        for (;;) {
            aktualna.vykonajAkciu(hrac);
            System.out.println(aktualna.getReplikaNpc());
            
            if (aktualna.jeKoniec()) {
                return;
            }
            
            aktualna.vypisMoznosti();
            
            int moznost;
            final int pocetMoznosti = aktualna.getPocetMoznosti();
            do {                
                Scanner riadok;
                do {                    
                    System.out.format("(1-%d)> ", pocetMoznosti);
                    riadok = new Scanner(vstup.nextLine());
                } while (!riadok.hasNextInt());
                
                moznost = riadok.nextInt();
            } while (moznost > pocetMoznosti || moznost < 1);
            
            aktualna = aktualna.getDalsiaPozicia(moznost);
        }
    }
}