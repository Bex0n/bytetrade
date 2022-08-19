package Osoby.Kariera;

import java.util.*;

public class Kariera {
    private Vector <SciezkaKariery> kariera;
    private int aktualnakarieraindex;

    private Kariera (String poczatkowaKariera, int poziom) {
        this.kariera = new Vector <SciezkaKariery>();
        this.kariera.add(SciezkaKariery.stworz("rolnik", 1));
        this.kariera.add(SciezkaKariery.stworz("gornik", 1));
        this.kariera.add(SciezkaKariery.stworz("rzemieslnik", 1));
        this.kariera.add(SciezkaKariery.stworz("inzynier", 1));
        this.kariera.add(SciezkaKariery.stworz("programista", 1));

        for (int i = 0; i < this.kariera.size(); i++) {
            if (this.kariera.get(i).podajNazwa().equals(poczatkowaKariera)) {
                this.kariera.get(i).poziom = poziom;
                this.aktualnakarieraindex = i;
            }
        }
    }

    public static Kariera stworz(String kariera, int poziom) {
        return new Kariera(kariera, poziom);
    }

    public int podajPremie(String produkt) {
        if (!this.aktualnakariera().podajProdukt().equals(produkt))
            return 0;
        for (int i = 0; i < kariera.size(); i++) {
            if (kariera.get(i).produkt.equals(produkt))
                return kariera.get(i).podajPremie();
        }
        return 0;
    }

    public SciezkaKariery aktualnakariera() {
        return kariera.get(aktualnakarieraindex);
    }

    public void zmienNa (String produkt) {
        if (this.aktualnakariera().produkt.equals(produkt))
            this.aktualnakariera().podniesPoziom();
        else
            for (int i = 0; i < kariera.size(); i++)
                if (kariera.get(i).produkt.equals(produkt))
                    aktualnakarieraindex = i;
        return;
    }

    public Vector <SciezkaKariery> podajKariera() { return kariera; }

}
