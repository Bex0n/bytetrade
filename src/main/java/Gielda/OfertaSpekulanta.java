package Gielda;

import Osoby.*;
import Przedmioty.*;

public class OfertaSpekulanta {
    private Przedmiot przedmiot;
    private double cena;
    private Spekulant spekulant;

    private OfertaSpekulanta(Przedmiot przedmiot, double cena, Spekulant spekulant) {
        this.przedmiot = przedmiot;
        this.cena = cena;
        this.spekulant = spekulant;
    }

    public static OfertaSpekulanta stworz(Przedmiot przedmiot, double cena, Spekulant spekulant) {
        return new OfertaSpekulanta(przedmiot, cena, spekulant);
    }

    public Przedmiot podajPrzedmiot() {
        return przedmiot;
    }

    public double podajCene() {
        return cena;
    }

    public Spekulant podajOsobe() {
        return spekulant;
    }
}
