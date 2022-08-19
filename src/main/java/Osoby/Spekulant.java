package Osoby;

import Osoby.Spekulanci.TaktykaSpekulanta;
import Przedmioty.*;
import Gielda.*;
import Input.*;

import java.util.Vector;

public class Spekulant extends Agent {

    private TaktykaSpekulanta taktykaSpekulanta;

    private Gielda gielda;

    private Spekulant(int id, Zasoby zasoby, TaktykaSpekulanta taktykaSpekulanta, Gielda gielda) {
        super.id = id;
        super.zasoby = zasoby;
        this.taktykaSpekulanta = taktykaSpekulanta;
        this.gielda = gielda;
    }

    public static Spekulant stworz(SpekulantInput spekulantInput, Gielda gielda) {
        return new Spekulant(spekulantInput.id,
                             Zasoby.stworz(spekulantInput.zasoby),
                             TaktykaSpekulanta.stworz(spekulantInput),
                             gielda);
    }

    public Gielda podajGielda() { return gielda; }

    public String podajTaktykaNapis() { return taktykaSpekulanta.podajNazwa(); }

    public double policzCeneKupna (Przedmiot produkt) {
        return this.taktykaSpekulanta.policzCeneKupna(this, produkt);
    }
    public void kupuj(Vector <OfertaSpekulanta> oferty) {
        this.taktykaSpekulanta.kupuj(oferty, this);
    }

    public double policzCeneSprzedazy (Przedmiot produkt) {
        return this.taktykaSpekulanta.policzCeneSprzedazy(this, produkt);
    }

    public void sprzedaj(Vector <OfertaSpekulanta> oferty) {
        this.taktykaSpekulanta.sprzedaj(oferty, this);
    }
    public void wykonajRuch(Vector <OfertaSpekulanta> ofertyKupna, Vector <OfertaSpekulanta> ofertySprzedazy) {
        this.kupuj(ofertyKupna);
        this.sprzedaj(ofertySprzedazy);
    }
}
