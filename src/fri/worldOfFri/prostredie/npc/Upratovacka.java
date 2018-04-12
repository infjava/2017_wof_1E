/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.npc;

import fri.worldOfFri.hra.Hrac;
import fri.worldOfFri.prostredie.npc.Npc;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author duracik2
 */
public class Upratovacka extends Npc {
    
    public Upratovacka(PoziciaVRozhovore zaciatocnaPozicia) {
        super("upratovacka", zaciatocnaPozicia);
    }

    @Override
    public void rozhovor(Hrac hrac) {
        int aktualnaHodina = Calendar.getInstance().get(Calendar.HOUR);
        if (aktualnaHodina >= 8 && aktualnaHodina < 10) {
            super.rozhovor(hrac);
        }
        else {
            System.out.println("Momentalne nepracujem");
        }
    }
    
    
    
}
