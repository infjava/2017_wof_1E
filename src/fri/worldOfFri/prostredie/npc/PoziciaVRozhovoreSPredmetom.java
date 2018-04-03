/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.npc;

/**
 *
 * @author duracik2
 */
public class PoziciaVRozhovoreSPredmetom extends PoziciaVRozhovore {
    
    public PoziciaVRozhovoreSPredmetom(String replikaNpc) {
        super(replikaNpc);
    }

    @Override
    void vykonajAkciu() {
        super.vykonajAkciu();
        //Pridaj predmet ISIC do inventara hraca
    }
    
}
