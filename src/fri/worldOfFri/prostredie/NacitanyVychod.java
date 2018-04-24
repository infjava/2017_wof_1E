/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.prostredie;

/**
 *
 * @author janik
 */
class NacitanyVychod {

    private final String zdrojovaMiestnost;
    private final String smer;
    private final String cielovaMiestnost;

    NacitanyVychod(String zdrojovaMiestnost, String smer, String cielovaMiestnost) {
        this.zdrojovaMiestnost = zdrojovaMiestnost;
        this.smer = smer;
        this.cielovaMiestnost = cielovaMiestnost;
    }

    public String getZdrojovaMiestnost() {
        return this.zdrojovaMiestnost;
    }

    public String getSmer() {
        return this.smer;
    }

    public String getCielovaMiestnost() {
        return this.cielovaMiestnost;
    }
    
}
