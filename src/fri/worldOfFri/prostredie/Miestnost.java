package fri.worldOfFri.prostredie;


import fri.worldOfFri.prostredie.predmety.IPredmet;
import java.util.HashMap;
import java.util.Set;

/**
 * Trieda Miestnost realizuje jednu miestnost/priestor v celom priestore hry.
 * Kazda "miestnost" je z inymi miestnostami spojena vychodmi. 
 * Vychody z miestnosti su oznacovane svetovymi stranami sever, vychod, juh
 * a zapad. Pre kazdy vychod si miestnost pamata odkaz na susednu miestnost
 * alebo null, ak tym smerom vychod nema.
 *
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Miestnost {
    private String popisMiestnosti;
    private HashMap<String, Miestnost> vychody;
    private HashMap<String, IPredmet> predmety;

    /**
     * Vytvori miestnost popis ktorej je v parametrom.
     * Po vytvoreni miestnost nema ziadne vychody. Popis miesnost strucne 
     * charakterizuje.
     * 
     * @param popis text popisu miestnosti.
     */
    public Miestnost(String popis) {
        this.vychody = new HashMap<String, Miestnost>();
        this.predmety = new HashMap<String, IPredmet>();
        this.popisMiestnosti = popis;
    }

    public String getPopisMiestnosti() {
        return this.popisMiestnosti;
    }

    /**
     * Nastavi vychody z miestnosti. Kazdy vychod je urceny bud odkazom 
     * na miestnost alebo hodnotou null, ak vychod tym smerom neexistuje.
     * 
     * @param nazov nazov smeru.
     * @param vychod miestnost danym smerom .
     */
    public void nastavVychod(String nazov, Miestnost vychod) {
        this.vychody.put(nazov, vychod);
    }

    public Miestnost getVychod(String smer) {
        return this.vychody.get(smer);
    }

    public void vypisInfo() {
        System.out.println(this.popisMiestnosti);
        
        this.vypisZoznam("Vychody", this.vychody.keySet());
        this.vypisZoznam("Predmety", this.predmety.keySet());
    }

    private void vypisZoznam(String popis, Set<String> polozky) {
        if (!polozky.isEmpty()) {
            System.out.print(popis + ": ");

            for (String nazov : polozky) {
                System.out.print(nazov + " ");
            }

            System.out.println();
        }
    }

    public void polozPredmet(IPredmet predmet) {
        this.predmety.put(predmet.getNazov(), predmet);
    }

    public IPredmet zoberPredmet(String nazovPredmetu) {
        return this.predmety.remove(nazovPredmetu);
    }
}
