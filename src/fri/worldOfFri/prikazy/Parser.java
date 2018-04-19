package fri.worldOfFri.prikazy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Trieda Parser cita vstup zadany hracom do terminaloveho okna a pokusi sa
 * interpretovat ho ako prikaz hry. Kazda sprava dajPrikaz sposobi, ze parser
 * precita jeden riadok z terminaloveho okna a vyberie z neho prve dve slova.
 * Tie dve slova pouzije ako parametre v sprave new triede Prikaz.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Parser {
    private static Parser instancia = new Parser();
    
    private NazvyPrikazov prikazy;  // odkaz na pripustne nazvy prikazov
    private Scanner citac;         // zdroj vstupov od hraca
    private ArrayList<Prikaz> historia;
    private Prikaz poslednyVykonavany;
    private ArrayList<Prikaz> aktualneMakro;
    private Prikaz poslednyVMakre;
    
    public static Parser getInstancia() {
        return Parser.instancia;
    }

    /**
     * Vytvori citac na citanie vstupov z terminaloveho okna.
     */
    private Parser() {
        this.prikazy = new NazvyPrikazov();
        this.citac = new Scanner(System.in);
        this.historia = new ArrayList<Prikaz>();
        this.poslednyVykonavany = null;
        this.aktualneMakro = new ArrayList<Prikaz>();
        this.poslednyVMakre = null;
    }

    /**
     * @return prikaz zadany hracom
     */
    public Prikaz nacitajPrikaz() {
        if (!this.aktualneMakro.isEmpty()) {
            return this.nacitajPrikazZMakra();
        }
        
        this.poslednyVMakre = null;
        
        System.out.print("> ");     // vyzva pre hraca na zadanie prikazu

        String vstupnyRiadok = this.citac.nextLine();

        String prikaz = null;
        String parameter = null;

        // najde prve dve slova v riadku 
        Scanner tokenizer = new Scanner(vstupnyRiadok);
        if (tokenizer.hasNext()) {
            prikaz = tokenizer.next();      // prve slovo
            if (tokenizer.hasNext()) {
                parameter = tokenizer.next();      // druhe slovo
                // vsimnite si, ze zbytok textu sa ignoruje
            }
        }
        
        // kontrola platnosti prikazu
        if (this.prikazy.jePrikaz(prikaz)) {
            // vytvori platny prikaz
            Prikaz ret = new Prikaz(prikaz, parameter);
            this.historia.add(ret);
            this.poslednyVykonavany = ret;
            return ret;
        } else {
            // vytvori neplatny - "neznamy" prikaz
            return new Prikaz(null, parameter); 
        }
    }

    private Prikaz nacitajPrikazZMakra() {
        this.poslednyVMakre = this.aktualneMakro.remove(0);
        System.out.format("> %s %s%n", this.poslednyVMakre.getNazov(), this.poslednyVMakre.getParameter());
        return this.poslednyVMakre;
    }
    
    public int nacitajInt(String otazka) {
        if (this.poslednyVMakre != null) {
            return this.nacitajIntZMakra(otazka);
        }
        
        Scanner riadok;
        do {                    
            System.out.print(otazka);
            System.out.print(" ");
            riadok = new Scanner(this.citac.nextLine());
        } while (!riadok.hasNextInt());

        int cislo = riadok.nextInt();
        this.poslednyVykonavany.pridajAkciu(Integer.toString(cislo));
        return cislo;
    }

    private int nacitajIntZMakra(String otazka) {
        int ret = Integer.parseInt(this.poslednyVMakre.nacitajAkciuZMakra());
        System.out.print(otazka);
        System.out.print(" ");
        System.out.println(ret);
        return ret;
    }
    
    public String nacitajString(String otazka) {
        if (this.poslednyVMakre != null) {
            return this.nacitajStringZMakra(otazka);
        }
        
        System.out.print(otazka);
        System.out.print(" ");
        String riadok = this.citac.nextLine();
        this.poslednyVykonavany.pridajAkciu(riadok);
        return riadok;
    }

    private String nacitajStringZMakra(String otazka) {
        String ret = this.poslednyVMakre.nacitajAkciuZMakra();
        System.out.print(otazka);
        System.out.print(" ");
        System.out.println(ret);
        return ret;
    }

    public void ulozMakro(String nazovMakra, int pocetPrikazov) throws IOException {
        File suborSMakrom = new File(nazovMakra + ".mac");
        try (PrintWriter makro = new PrintWriter(suborSMakrom)) {
            int posledneCisloPrikazu = this.historia.size() - 1;
            int cisloPrikazu = posledneCisloPrikazu - pocetPrikazov;
            if (cisloPrikazu < 0) {
                cisloPrikazu = 0;
            }
            for (int i = cisloPrikazu; i < posledneCisloPrikazu; i++) {
                Prikaz zapisovany = this.historia.get(i);
                if (zapisovany.maParameter()) {
                    makro.format("> %s %s%n", zapisovany.getNazov(), zapisovany.getParameter());
                } else {
                    makro.format("> %s%n", zapisovany.getNazov());
                }
                for (String akcia : zapisovany.getAkcie()) {
                    makro.println(akcia);
                }
            }
        }
    }

    public void zopakujMakro(String nazovMakra) throws IOException {
        this.aktualneMakro.clear();
        
        Prikaz poslednyVAktualnomMakre = null;
        
        File suborSMakrom = new File(nazovMakra + ".mac");
        try (Scanner makro = new Scanner(suborSMakrom)) {
            while (makro.hasNextLine()) {
                String riadok = makro.nextLine();
                if (riadok.startsWith("> ")) {
                    Scanner citacRiadku = new Scanner(riadok);
                    citacRiadku.next(); // preskoci "> "
                    String nazovPrikazu = citacRiadku.next();
                    String parameterPrikazu;
                    if (citacRiadku.hasNext()) {
                        parameterPrikazu = citacRiadku.next();
                    } else {
                        parameterPrikazu = null;
                    }
                    poslednyVAktualnomMakre = new Prikaz(nazovPrikazu, parameterPrikazu);
                    this.aktualneMakro.add(poslednyVAktualnomMakre);
                } else if (poslednyVAktualnomMakre != null) {
                    poslednyVAktualnomMakre.pridajAkciu(riadok);
                }
            }
        }
    }
}
