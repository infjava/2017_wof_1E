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
    }

    /**
     * @return prikaz zadany hracom
     */
    public Prikaz nacitajPrikaz() {
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
    
    public int nacitajInt(String otazka) {
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
    
    public String nacitajString(String otazka) {
        System.out.print(otazka);
        System.out.print(" ");
        String riadok = this.citac.nextLine();
        this.poslednyVykonavany.pridajAkciu(riadok);
        return riadok;
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
}
