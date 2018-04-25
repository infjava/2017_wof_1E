package fri.worldOfFri.hra;

import fri.worldOfFri.prikazy.Prikaz;
import fri.worldOfFri.prikazy.Parser;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Trieda Hra je hlavna trieda aplikacie "World of FRI".
 * "World of FRI" je velmi jednoducha textova hra - adventura. 
 * Hrac sa moze prechadzat po niektorych priestoroch - miestnostiach fakulty. 
 * To je v tejto verzii vsetko. Hru treba skutocne zancne rozsirit,
 * aby bola zaujimava.
 * 
 * Ak chcete hrat "World of FRI", vytvorte instanciu triedy Hra (hra) 
 * a poslite jej spravu hraj.
 * 
 * Hra vytvori a inicializuje vsetky potebne objekty:
 * vytvori vsetky miestnosti, vytvori parser a zacne hru. Hra tiez vyhodnocuje
 * a vykonava prikazy, ktore vrati parser.
 * 
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
*/
 
public class Hra  {
    private static final int SAVE_MAGIC_NUMBER = 1243293261;
    private static final int SAVE_VERSION = 2;
    
    private Hrac hrac;
    
    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() {
        this.hrac = new Hrac();
    }


    /**
     *  Hlavna metoda hry.
     *  Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void hraj() {            
        this.vypisPrivitanie();

        // Vstupny bod hlavneho cyklu.
        // Opakovane nacitava prikazy hraca
        // vykonava ich kym hrac nezada prikaz na ukoncenie hry.
                
        boolean jeKoniec;
        
        do {
            Prikaz prikaz = Parser.getInstancia().nacitajPrikaz();
            jeKoniec = this.vykonajPrikaz(prikaz);
        } while (!jeKoniec);
        
        System.out.println("Maj sa fajn!");
    }

    /**
     * Vypise privitanie hraca do terminaloveho okna.
     */
    private void vypisPrivitanie() {
        System.out.println();
        System.out.println("Vitaj v hre World of FRI!");
        System.out.println("World of FRI je nova, neuveritelne nudna adventura.");
        System.out.println("Zadaj 'pomoc' ak potrebujes pomoc.");
        System.out.println();
        System.out.print("Teraz si v miestnosti ");
        this.hrac.getAktualnaMiestnost().vypisInfo();
    }

    /**
     * Prevezne prikaz a vykona ho.
     * 
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    private boolean vykonajPrikaz(Prikaz prikaz) {
        boolean jeKoniec = false;

        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        String nazovPrikazu = prikaz.getNazov();
        
        switch (nazovPrikazu) {
            case "pomoc":
                this.vypisNapovedu();
                return false;
            case "chod":
                this.chodDoMiestnosti(prikaz);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            case "zober":
                this.zoberPredmet(prikaz);
                return false;
            case "staty":
                this.zobrazStaty(prikaz);
                return false;
            case "zahod":
                this.zahodPredmet(prikaz);
                return false;
            case "pouzi":
                this.pouziPredmet(prikaz);
                return false;
            case "hovor":
                this.hovorSNpc(prikaz);
                return false;
            case "nahraj":
                this.nahrajMakro(prikaz);
                return false;
            case "zopakuj":
                this.zopakujMakro(prikaz);
                return false;
            case "save":
                this.save(prikaz);
                return false;
            case "load":
                this.load(prikaz);
                return false;
            default:
                return false;
        }
    }

    // implementacie prikazov:

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        System.out.println("   chod ukonci pomoc");
    }

    /** 
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     */
    private void chodDoMiestnosti(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();

        if (this.hrac.chodSmerom(smer)) {
            this.hrac.getAktualnaMiestnost().vypisInfo();
        }
    }


    /** 
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * Prikaz ukoncenia nema parameter.
     * 
     * @return true, if this command quits the game, false otherwise.
     * @return true, ak prikaz konci hru, inak false.
     */
    private boolean ukonciHru(Prikaz prikaz) {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }

    private void zoberPredmet(Prikaz prikaz) {
        String nazovPredmetu = prikaz.getParameter();
        
        if (!this.hrac.zoberPredmet(nazovPredmetu)) {
            System.out.println("Taky predmet momentalne neviem zdvihnut");
        }
    }

    private void zobrazStaty(Prikaz prikaz) {
        this.hrac.zobrazStaty();
    }

    private void zahodPredmet(Prikaz prikaz) {
        String nazovPredmetu = prikaz.getParameter();
        
        if (!this.hrac.zahodPredmet(nazovPredmetu)) {
            System.out.println("Taky predmet nemam v inventari");
        }
    }

    private void pouziPredmet(Prikaz prikaz) {
        String nazovPredmetu = prikaz.getParameter();
        this.hrac.pouziPredmet(nazovPredmetu);
    }

    private void hovorSNpc(Prikaz prikaz) {
        String menoNpc = prikaz.getParameter();
        this.hrac.hovorSNpc(menoNpc);
    }

    private void nahrajMakro(Prikaz prikaz) {
        String nazovMakra = prikaz.getParameter();
        int pocetPrikazov = Parser.getInstancia().nacitajInt("Zadaj pocet prikazov:");
        try {
            Parser.getInstancia().ulozMakro(nazovMakra, pocetPrikazov);
        } catch (IOException ex) {
            System.out.println("Chyba zapisu");
        }
    }

    private void zopakujMakro(Prikaz prikaz) {
        String nazovMakra = prikaz.getParameter();
        try {
            Parser.getInstancia().zopakujMakro(nazovMakra);
        } catch (IOException ex) {
            System.out.println("Chyba citania");
        }
    }

    private void save(Prikaz prikaz) {
        File suborSoSave = new File(prikaz.getParameter() + ".sav");
        
        try (DataOutputStream zapisovacSave = new DataOutputStream(new FileOutputStream(suborSoSave))) {
            zapisovacSave.writeInt(Hra.SAVE_MAGIC_NUMBER);
            zapisovacSave.writeInt(Hra.SAVE_VERSION);
            
            this.hrac.save(zapisovacSave);
           
            System.out.println("Ulozil si hru do " + suborSoSave.getName());
        } catch (IOException ex) {
            System.out.println("Nepodarilo sa ulozit hru");
        }
    }

    private void load(Prikaz prikaz) {
        File suborSoSave = new File(prikaz.getParameter() + ".sav");
        
        try (DataInputStream citacSave = new DataInputStream(new FileInputStream(suborSoSave))) {
            int magicNumber = citacSave.readInt();
            if (magicNumber != Hra.SAVE_MAGIC_NUMBER) {
                System.out.println("Chybny save subor");
                return;
            }
            
            int saveVersion = citacSave.readInt();
            if (saveVersion > Hra.SAVE_VERSION) {
                System.out.println("Prilis vysoka verzia save, skus novsiu verziu hry");
                return;
            }
            
            this.hrac.load(citacSave, saveVersion);
            
            System.out.println("Nacital si hru z " + suborSoSave.getName());
            
            this.hrac.getAktualnaMiestnost().vypisInfo();
        } catch (IOException ex) {
            System.out.println("Nepodarilo sa hru nacitat");
        }
    }
}
