/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie;

import fri.worldOfFri.prostredie.npc.PoziciaVRozhovore;
import fri.worldOfFri.prostredie.npc.Upratovacka;
import fri.worldOfFri.prostredie.npc.Npc;
import fri.worldOfFri.prostredie.npc.PoziciaVRozhovoreObchod;
import fri.worldOfFri.prostredie.npc.PoziciaVRozhovoreSPredmetom;
import fri.worldOfFri.prostredie.npc.PoziciaVRozhovoreSQuestom;
import fri.worldOfFri.prostredie.predmety.Bageta;
import fri.worldOfFri.prostredie.predmety.Isic;
import fri.worldOfFri.prostredie.predmety.Navigacia;
import fri.worldOfFri.prostredie.predmety.Peniaze;
import fri.worldOfFri.prostredie.predmety.Navleky;
import fri.worldOfFri.prostredie.predmety.PredmetMapa;
import fri.worldOfFri.questy.Quest;
import fri.worldOfFri.questy.ZiskajPredmetQuest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author janik
 */
public class Mapa {

    private Miestnost startovaciaMiestnost;
    private final ArrayList<Miestnost> zoznamMiestnosti;
    private final HashMap<String, Miestnost> miestnosti;
    private final ArrayList<NacitanyVychod> nacitaneVychody;

    /**
     * Vytvori mapu hry - miestnosti.
     */
    public Mapa() {
        this.zoznamMiestnosti = new ArrayList<Miestnost>();
        
        this.startovaciaMiestnost = null;
        
        this.miestnosti = new HashMap<String, Miestnost>();
        
        this.nacitaneVychody = new ArrayList<NacitanyVychod>();
        
        File suborMapy = new File("mapa.txt");
        try (Scanner citacMapy = new Scanner(suborMapy)) {
            while (citacMapy.hasNextLine()) {
                String riadokSDefiniciou = citacMapy.nextLine();
                
                Scanner citacRiadkuSDefiniciou = new Scanner(riadokSDefiniciou);
                String prikaz = citacRiadkuSDefiniciou.next();
                String parameter = citacRiadkuSDefiniciou.next();
                String druhyParameter;
                if (citacRiadkuSDefiniciou.hasNext()) {
                    druhyParameter = citacRiadkuSDefiniciou.next();
                } else {
                    druhyParameter = null;
                }
                
                switch (prikaz) {
                    case "miestnost":
                        this.citajMiestnost(parameter, druhyParameter, citacMapy);
                        break;
                    case "npc":
                        this.citajNpc(parameter, citacMapy);
                        break;
                    case "start":
                        this.citajStart(parameter, citacMapy);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Nenasiel sa subor s mapou", ex);
        }
        
        for (NacitanyVychod nacitanyVychod : this.nacitaneVychody) {
            Miestnost zMiestnosti = this.miestnosti.get(nacitanyVychod.getZdrojovaMiestnost());
            Miestnost doMiestnosti = this.miestnosti.get(nacitanyVychod.getCielovaMiestnost());
            zMiestnosti.nastavVychod(nacitanyVychod.getSmer(), doMiestnosti);
        }
        
        /*
        Miestnost terasa = new Miestnost("Terasa - tu byva FRIfest");
        Miestnost vratnica = new Miestnost("Vratnica - tu byva vratnicka");
        Miestnost ic = new MiestnostIC();
        Miestnost chodbaA = new Miestnost("Chodba A - tu byva prvy automat na kavu");
        Miestnost wc = new Miestnost("WC - tu byva smrad");
        Miestnost bufet = new Miestnost("Bufet - tu byva jedlo");
        Miestnost chodbaB = new Miestnost("Chodba B - tu byva druhy automat na kavu");
        Miestnost chillZone = new Miestnost("Chill Zone - tu byva vela studentov");
        Miestnost b1 = new Miestnost("B1 - tu byva ticho");
        
        this.zoznamMiestnosti.add(terasa);
        this.zoznamMiestnosti.add(vratnica);
        this.zoznamMiestnosti.add(ic);
        this.zoznamMiestnosti.add(chodbaA);
        this.zoznamMiestnosti.add(wc);
        this.zoznamMiestnosti.add(bufet);
        this.zoznamMiestnosti.add(chodbaB);
        this.zoznamMiestnosti.add(chillZone);
        this.zoznamMiestnosti.add(b1);
        
        terasa.nastavVychod("vychod", vratnica);
        
        terasa.polozPredmet(new Navleky());
        terasa.polozPredmet(new Bageta());
        terasa.polozPredmet(new Peniaze());
        terasa.polozPredmet(new PredmetMapa());
        terasa.polozPredmet(new Navigacia());
        
        //Rozhovor dekan
        PoziciaVRozhovore dobryDen = new PoziciaVRozhovore("Dobry den.");
        PoziciaVRozhovore chcemKavu = new PoziciaVRozhovore("Chcem kavu.");
        PoziciaVRozhovore dobre = new PoziciaVRozhovore("Dobre");
        PoziciaVRozhovore bageta = new PoziciaVRozhovore("Bageta");
        PoziciaVRozhovore fight = new PoziciaVRozhovore("Fight!");
        PoziciaVRozhovore ineDobre = new PoziciaVRozhovore("Dobre");
        Quest zozenKavu = new  ZiskajPredmetQuest("dones-kavu", "Prines kavu p. dekanovi", "kava");
        PoziciaVRozhovore dobreDonesKavu = new PoziciaVRozhovoreSQuestom("Dakujem.", zozenKavu);
        
        dobryDen.pridajMoznost("Dobry!", chcemKavu);
        dobryDen.pridajMoznost("Hee?", fight);
        
        chcemKavu.pridajMoznost("Okej, zozeniem vam", dobreDonesKavu);
        chcemKavu.pridajMoznost("Co za to?", bageta);
        chcemKavu.pridajMoznost("Aj ja", fight);
        
        bageta.pridajMoznost("Okej, zozeniem vam", dobre);
        bageta.pridajMoznost("Nechcem", ineDobre);
        
        terasa.postavNpc(new Npc("dekan", dobryDen));
        
        //Rozhovor upratovacka
        PoziciaVRozhovore dobryDenIsic = new PoziciaVRozhovore("Dobry den, nieje toto vas isic?");
        PoziciaVRozhovore nechSaPaciIsic = new PoziciaVRozhovoreSPredmetom("Nech sa paci", new Isic());
        
        dobryDenIsic.pridajMoznost("Ano je!", nechSaPaciIsic);
        dobryDenIsic.pridajMoznost("Nie", dobre);
        
        nechSaPaciIsic.pridajMoznost("Vdaka", dobre);
        nechSaPaciIsic.pridajMoznost("...", fight);
        
        terasa.postavNpc(new Upratovacka(dobryDenIsic));
        
        
        //Rozhovor bufetarka
        PoziciaVRozhovore bufetarkaMenu = new PoziciaVRozhovore("Dobry den, co si prosis?");
        PoziciaVRozhovore kupujPizza = new PoziciaVRozhovoreObchod(10, "pizza");
        PoziciaVRozhovore kupujBageta = new PoziciaVRozhovoreObchod(5, "bageta");
        PoziciaVRozhovore kupujNavleky = new PoziciaVRozhovoreObchod(2, "navleky");
        PoziciaVRozhovore nekupujem = new PoziciaVRozhovore("Dovidenia");
        bufetarkaMenu.pridajMoznost("pizza (10)", kupujPizza);
        bufetarkaMenu.pridajMoznost("bageta (5)", kupujBageta);
        bufetarkaMenu.pridajMoznost("navleky (2)", kupujNavleky);
        bufetarkaMenu.pridajMoznost("nic", nekupujem);
        
        terasa.postavNpc(new Npc("bufetarka", bufetarkaMenu));
        
        
        vratnica.nastavVychod("vychod", ic);
        vratnica.nastavVychod("zapad", terasa);
        vratnica.nastavVychod("sever", chodbaA);
        vratnica.nastavVychod("juh", chodbaB);
        
        ic.nastavVychod("zapad", vratnica);
        
        chodbaA.nastavVychod("vychod", wc);
        chodbaA.nastavVychod("zapad", bufet);
        chodbaA.nastavVychod("juh", vratnica);
        
        wc.nastavVychod("zapad", chodbaA);
        
        bufet.nastavVychod("vychod", chodbaA);
        
        chodbaB.nastavVychod("zapad", b1);
        chodbaB.nastavVychod("sever", vratnica);
        chodbaB.nastavVychod("juh", chillZone);
        
        b1.nastavVychod("vychod", chodbaB);
        
        chillZone.nastavVychod("sever", chodbaB);
        
        this.startovaciaMiestnost = terasa;
        */
    }

    private void citajMiestnost(String nazovMiestnosti, String triedaMiestnosti, Scanner citacMapy) {
        StringBuilder popis = new StringBuilder();
        String riadokPopisu;
        do {
            riadokPopisu = citacMapy.nextLine().trim();
            
            popis.append(riadokPopisu);
            popis.append('\n');
        } while (!riadokPopisu.equals(""));
        
        Miestnost miestnost;
        if (triedaMiestnosti == null) {
            miestnost = new Miestnost(nazovMiestnosti + " - " + popis.toString().trim());
        } else {
            switch (triedaMiestnosti) {
                case "(MiestnostIC)":
                    miestnost = new MiestnostIC(nazovMiestnosti + " - " + popis.toString().trim());
                    break;
                default:
                    throw new AssertionError();
            }
        }
        
        String riadokPrikazu;
        do {
            riadokPrikazu = citacMapy.nextLine().trim();
            
            if (riadokPrikazu.contains(" => ")) {
                Scanner citacPrikazu = new Scanner(riadokPrikazu);
                
                String smer = citacPrikazu.next();
                citacPrikazu.next(); // nacitaj sipocku
                String cielovaMiestnost = citacPrikazu.next();
                
                this.nacitaneVychody.add(new NacitanyVychod(nazovMiestnosti, smer, cielovaMiestnost));
            }
        } while (!riadokPrikazu.equals(""));
        
        this.zoznamMiestnosti.add(miestnost);
        this.miestnosti.put(nazovMiestnosti, miestnost);
    }

    private void citajNpc(String nazovNpc, Scanner citacMapy) {
        String riadokPrikazu;
        do {
            riadokPrikazu = citacMapy.nextLine().trim();
        } while (!riadokPrikazu.equals(""));
    }

    private void citajStart(String nazovMiestnosti, Scanner citacMapy) {
        this.startovaciaMiestnost = this.miestnosti.get(nazovMiestnosti);
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }

    public Iterable<Miestnost> getZoznamMiestnosti() {
        return this.zoznamMiestnosti;
    }

    public Miestnost getMiestnost(String nazovCielu) {
        for (Miestnost miestnost : this.zoznamMiestnosti) {
            if (miestnost.getNazov().equals(nazovCielu)) {
                return miestnost;
            }
        }
        
        return null;
    }

    public Iterable<Miestnost> getCesta(Miestnost vychod, Miestnost ciel) {
        int cisloVychod = this.getCisloMiestnosti(vychod);
        int cisloCiel = this.getCisloMiestnosti(ciel);
        
        int[][] maticaVzdialenosti = new int[this.getPocetMiestnosti()][this.getPocetMiestnosti()];
        int[][] maticaPrechodov = new int[this.getPocetMiestnosti()][this.getPocetMiestnosti()];
        
        this.inicializujFloyda(maticaVzdialenosti, maticaPrechodov);
        this.vypocitajFloyda(maticaVzdialenosti, maticaPrechodov);
        return this.najdiFloyda(maticaPrechodov, cisloVychod, cisloCiel);
    }
    
    private int getPocetMiestnosti() {
        return this.zoznamMiestnosti.size();
    }

    private int getCisloMiestnosti(Miestnost miestnost) {
        return this.zoznamMiestnosti.indexOf(miestnost);
    }

    private void inicializujFloyda(int[][] maticaVzdialenosti, int[][] maticaPrechodov) {
        for (int y = 0; y < this.getPocetMiestnosti(); y++) {
            for (int x = 0; x < this.getPocetMiestnosti(); x++) {
                maticaPrechodov[y][x] = -1;
                // 1000000 je nekonecno.
                // Nemoze byt Integer.MAX_VALUE, lebo sa budu scitavat
                maticaVzdialenosti[y][x] = 1000000;
            }
        }
        
        
        for (Miestnost vychodzia : this.zoznamMiestnosti) {
            int vychodziaCislo = this.getCisloMiestnosti(vychodzia);
            for (Miestnost cielova : vychodzia.getCieloveMiestnosti()) {
                int cielovaCislo = this.getCisloMiestnosti(cielova);
                
                maticaVzdialenosti[vychodziaCislo][cielovaCislo] = 1;
                maticaPrechodov[vychodziaCislo][cielovaCislo] = vychodziaCislo;
            }
        }
    }

    private void vypocitajFloyda(int[][] maticaVzdialenosti, int[][] maticaPrechodov) {
        for (int vychodzia = 0; vychodzia < this.getPocetMiestnosti(); vychodzia++) {
            for (int cielova = 0; cielova < this.getPocetMiestnosti(); cielova++) {
                for (int prechodova = 0; prechodova < this.getPocetMiestnosti(); prechodova++) {
                    int staraVzdialenost = maticaVzdialenosti[vychodzia][cielova];
                    int novaVzdialenost = maticaVzdialenosti[vychodzia][prechodova]
                            + maticaVzdialenosti[prechodova][cielova];
                    
                    if (novaVzdialenost < staraVzdialenost) {
                        maticaVzdialenosti[vychodzia][cielova] = novaVzdialenost;
                        maticaPrechodov[vychodzia][cielova] = prechodova;
                    }
                }
            }
        }
    }

    private Iterable<Miestnost> najdiFloyda(int[][] maticaPrechodov, int cisloVychod, int cisloCiel) {
        ArrayList<Miestnost> ret = new ArrayList<Miestnost>();
        
        int cisloAktualna = cisloCiel;
        
        while (cisloAktualna != cisloVychod) {
            ret.add(0, this.zoznamMiestnosti.get(cisloAktualna));
            cisloAktualna = maticaPrechodov[cisloVychod][cisloAktualna];
            
            if (cisloAktualna == -1) {
                return null;
            }
        }
        
        ret.add(0, this.zoznamMiestnosti.get(cisloVychod));
        
        return ret;
    }
}
