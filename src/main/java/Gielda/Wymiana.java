package Gielda;

import Osoby.*;
import Przedmioty.*;

public class Wymiana {
    private Przedmiot przedmiot1;
    private Przedmiot przedmiot2;

    private Wymiana(Przedmiot przedmiot1, Przedmiot przedmiot2) {
        this.przedmiot1 = przedmiot1;
        this.przedmiot2 = przedmiot2;
    }

    public static Wymiana stworz(Przedmiot przedmiot1, Przedmiot przedmiot2) { return new Wymiana(przedmiot1, przedmiot2); }

    public double cena() {
        if (przedmiot1.podajNazwa().equals("diamenty"))
            return przedmiot1.podajLiczba();
        if (przedmiot2.podajNazwa().equals("diamenty"))
            return przedmiot2.podajLiczba();
        return 0;
    }

    public double cenaZaSztuke() {
        if (przedmiot1.podajNazwa().equals("diamenty"))
            return przedmiot1.podajLiczba() / przedmiot2.podajLiczba();
        if (przedmiot2.podajNazwa().equals("diamenty"))
            return przedmiot2.podajLiczba() / przedmiot1.podajLiczba();
        return 0;
    }

    public Przedmiot sprzedanyPrzedmiot() {
        if (przedmiot1.podajNazwa().equals("diamenty"))
            return przedmiot2;
        return przedmiot1;
    }
}
