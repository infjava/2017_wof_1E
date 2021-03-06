/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.hra;

import fri.worldOfFri.prostredie.predmety.IPredmet;
import fri.worldOfFri.prostredie.Mapa;
import fri.worldOfFri.prostredie.Miestnost;
import fri.worldOfFri.prostredie.npc.Npc;
import fri.worldOfFri.prostredie.predmety.Penazenka;
import fri.worldOfFri.questy.Quest;
import fri.worldOfFri.questy.ZiskajPredmetQuest;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author janik
 */
public class Hrac {
    private static final int MAX_HUNGER_BAR = 15;
    
    private Miestnost aktualnaMiestnost;
    private HashMap<String, IPredmet> inventar;
    private HashMap<String, Quest> questy;
    private int hungerBar;
    private final Mapa mapa;

    Hrac() {
        this.mapa = new Mapa();
        
        this.aktualnaMiestnost = this.mapa.getStartovaciaMiestnost();
        this.inventar = new HashMap<String, IPredmet>();
        this.questy = new HashMap<String, Quest>();
        this.hungerBar = Hrac.MAX_HUNGER_BAR;
        
        this.inventar.put("penazenka", new Penazenka());
    }

    public Mapa getMapa() {
        return this.mapa;
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }
    
    public boolean chodSmerom(String smer) {
        if (this.hungerBar == 0) {
            System.out.println("Mas prazdny hungerBar");
            return false;
        }
        
        Miestnost nova = this.aktualnaMiestnost.getVychod(smer);
        if (nova == null) {
            System.out.println("Tam nie je vychod!");
            return false;
        }
        else if (!nova.mozemVojst(this)) {
            System.out.println("Do miestnosti nemozes vojst!");
            return false;
        } else {
            this.aktualnaMiestnost = nova;
            this.hungerBar--;
            return true;
        }
    }

    boolean zoberPredmet(String nazovPredmetu) {
        IPredmet predmet = this.aktualnaMiestnost.zoberPredmet(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        this.pridajPredmet(predmet);
        return true;
    }

    void zobrazStaty() {
        System.out.println("Hunger bar: " + this.hungerBar);
        
        if (!this.inventar.isEmpty()) {
            System.out.println("Inventar:");
            
            for (String predmet : this.inventar.keySet()) {
                System.out.println("- " + predmet);
            }
        } else {
            System.out.println("Mas prazdny inventar");
        }
        
        System.out.println("Questy:");
        for (Quest quest : this.questy.values()) {
            if (quest.jeAktivny()) {
                System.out.println(quest.getPopis());
            }
        }
    }

    boolean zahodPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.get(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        if (!predmet.jeZahoditelny()) {
            return false;
        }
        
        this.inventar.remove(nazovPredmetu);
        this.aktualnaMiestnost.polozPredmet(predmet);
        return true;
    }

    void pouziPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.get(nazovPredmetu);
        
        if (predmet == null) {
            return;
        }
        
        predmet.pouziSa(this);
    }

    public void zjedz(int energia) {
        this.hungerBar += energia;
        
        if (this.hungerBar > Hrac.MAX_HUNGER_BAR) {
            this.hungerBar = Hrac.MAX_HUNGER_BAR;
        }
    }

    public void zrusPredmet(IPredmet predmet) {
        this.inventar.remove(predmet.getNazov());
    }

    void hovorSNpc(String menoNpc) {
        Npc npc = this.aktualnaMiestnost.getNpc(menoNpc);
        
        if (npc == null) {
            return;
        }
        
        npc.rozhovor(this);
    }

    public void pridajPredmet(IPredmet predmet) {
        this.inventar.put(predmet.getNazov(), predmet);
        this.skontrolujQuesty();
    }

    public boolean maPredmet(String nazov) {
        return this.inventar.containsKey(nazov);
    }

    public boolean zaplat(int suma) {
        if (!this.maPredmet("penazenka")) {
            return false;
        }
        /*IPredmet predmet = this.inventar.get("penazenka");
        
        if (predmet instanceof Penazenka) {
            Penazenka penazenka = (Penazenka) predmet;
            return penazenka.zaplat(suma);
        }*/
        Penazenka penazenka = (Penazenka) this.inventar.get("penazenka");
        return penazenka.zaplat(suma);
    }

    public void pridajQuest(Quest quest) {
        this.questy.put(quest.getNazov(), quest);
        System.out.println("Ziskal si quest:");
        System.out.println(quest.getPopis());
    }

    public boolean maQuest(String nazov) {
        return this.questy.containsKey(nazov);
    }

    private void skontrolujQuesty() {
        for (Quest quest : this.questy.values()) {
            if (quest.jeAktivny()) {
                quest.skontrolujSplnenie(this);
            }
        }
    }

    void save(DataOutputStream zapisovacSave) throws IOException {
        zapisovacSave.writeUTF(this.aktualnaMiestnost.getNazov());
        
        zapisovacSave.writeInt(this.hungerBar);
        
        zapisovacSave.writeInt(this.inventar.size());
        for (IPredmet predmet : this.inventar.values()) {
            zapisovacSave.writeUTF(predmet.getNazov());
            predmet.zapisStav(zapisovacSave);
        }
    }

    void load(DataInputStream citacSave, int saveVersion) throws IOException {
        String nazovAktualnejMiestnosti = citacSave.readUTF();
        this.aktualnaMiestnost = this.mapa.getMiestnost(nazovAktualnejMiestnosti);
        
        if (saveVersion >= 1) {
            this.hungerBar = citacSave.readInt();
        }
        
        if (saveVersion >= 2) {
            this.inventar.clear();
            
            int pocetPredmetov = citacSave.readInt();
            for (int i = 0; i < pocetPredmetov; i++) {
                String nazovPredmetu = citacSave.readUTF();
                IPredmet predmet = this.mapa.getPredmet(nazovPredmetu);
                this.pridajPredmet(predmet);
                if (saveVersion >= 3) {
                    predmet.nacitajStav(citacSave);
                }
            }
        }
    }
}
