/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie;

import java.util.ArrayList;

/**
 *
 * @author janik
 */
class PoziciaVRozhovore {

    private final String replikaNpc;
    private final ArrayList<String> replikyHraca;

    PoziciaVRozhovore(String replikaNpc) {
        this.replikaNpc = replikaNpc;
        this.replikyHraca = new ArrayList<String>();
    }

    void pridajMoznost(String replikaHraca, PoziciaVRozhovore dalsiaPozicia) {
        this.replikyHraca.add(replikaHraca);
    }

    String getReplikaNpc() {
        return this.replikaNpc;
    }

    void vypisMoznosti() {
        int pocitadloReplik = 1;
        for (String replika : this.replikyHraca) {
            System.out.format("%d) %s%n", pocitadloReplik, replika);
            pocitadloReplik++;
        }
    }
}
