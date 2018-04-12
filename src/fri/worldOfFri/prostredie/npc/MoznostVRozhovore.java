/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.npc;

/**
 *
 * @author janik
 */
public class MoznostVRozhovore {

    private final String replikaHraca;
    private final PoziciaVRozhovore dalsiaPozicia;

    public MoznostVRozhovore(String replikaHraca, PoziciaVRozhovore dalsiaPozicia) {
        this.replikaHraca = replikaHraca;
        this.dalsiaPozicia = dalsiaPozicia;
        
    }

    public String getReplikaHraca() {
        return this.replikaHraca;
    }

    public PoziciaVRozhovore getDalsiaPozicia() {
        return this.dalsiaPozicia;
    }
    
}
