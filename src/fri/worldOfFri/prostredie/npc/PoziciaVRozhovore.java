/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.npc;

import fri.worldOfFri.prostredie.npc.MoznostVRozhovore;
import java.util.ArrayList;

/**
 *
 * @author janik
 */
public class PoziciaVRozhovore {

    private final String replikaNpc;
    private final ArrayList<MoznostVRozhovore> replikyHraca;

    public PoziciaVRozhovore(String replikaNpc) {
        this.replikaNpc = replikaNpc;
        this.replikyHraca = new ArrayList<MoznostVRozhovore>();
    }

    public void pridajMoznost(String replikaHraca, PoziciaVRozhovore dalsiaPozicia) {
        this.replikyHraca.add(new MoznostVRozhovore(replikaHraca, dalsiaPozicia));
    }

    public String getReplikaNpc() {
        return this.replikaNpc;
    }

    public void vypisMoznosti() {
        int pocitadloReplik = 1;
        for (MoznostVRozhovore replika : this.replikyHraca) {
            System.out.format("%d) %s%n", pocitadloReplik, replika.getReplikaHraca());
            pocitadloReplik++;
        }
    }

    public PoziciaVRozhovore getDalsiaPozicia(int moznost) {
        return this.replikyHraca.get(moznost - 1).getDalsiaPozicia();
    }
    
    public boolean jeKoniec() {
        return this.replikyHraca.isEmpty();
    }

    public int getPocetMoznosti() {
        return this.replikyHraca.size();
    }
}
