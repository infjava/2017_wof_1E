/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie.npc;

import fri.worldOfFri.hra.Hrac;
import fri.worldOfFri.questy.Quest;

/**
 *
 * @author duracik2
 */
public class PoziciaVRozhovoreSQuestom extends PoziciaVRozhovore {

    private final Quest quest;
    
    public PoziciaVRozhovoreSQuestom(String replikaNpc, Quest quest) {
        super(replikaNpc);
        this.quest = quest;
    }

    @Override
    void vykonajAkciu(Hrac hrac) {
        hrac.pridajQuest(this.quest);
    }
    
    
    
}
