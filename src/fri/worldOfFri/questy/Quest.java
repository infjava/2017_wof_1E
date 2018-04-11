/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.worldOfFri.questy;

import fri.worldOfFri.hra.Hrac;

/**
 *
 * @author duracik2
 */
public abstract class Quest {
    
    private String popis;
    private StavQuestu stav;

    public Quest(String popis) {
        this.popis = popis;
        this.stav = StavQuestu.Aktualny;
    }
    
    public void zrus() {
        if (this.stav == StavQuestu.Aktualny) {
            this.stav = StavQuestu.Nesplneny;
        }
    }

    public StavQuestu getStav() {
        return this.stav;
    }
    
    public abstract void skontrolujSplnenie(Hrac hrac);

    protected void oznacAkoSplneny() {
        if (this.stav == StavQuestu.Aktualny) {
            this.stav = StavQuestu.Splneny;
        }
    }

    public String getPopis() {
        return this.popis;
    }

    public boolean jeAktivny() {
        return this.stav == StavQuestu.Aktualny;
    }
    
}
