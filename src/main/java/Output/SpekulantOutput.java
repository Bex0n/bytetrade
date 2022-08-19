package Output;

import Osoby.Spekulant;

public class SpekulantOutput {
    private int id;
    private String kariera;
    private ZasobyOutput zasoby;

    public SpekulantOutput(Spekulant spekulant) {
        this.id = spekulant.podajID();
        this.kariera = spekulant.podajTaktykaNapis();
        this.zasoby = new ZasobyOutput(spekulant.podajZasoby());
    }
}
