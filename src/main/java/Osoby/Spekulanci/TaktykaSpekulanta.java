package Osoby.Spekulanci;

import Gielda.OfertaSpekulanta;
import Input.SpekulantInput;
import Przedmioty.*;
import Osoby.*;

import java.util.Vector;

public abstract class TaktykaSpekulanta {

    protected String nazwa;

    public static TaktykaSpekulanta stworz(SpekulantInput spekulantInput) {
        switch(spekulantInput.kariera) {
            case("wypukly"):
                return Wypukly.stworz();
            case("sredni"):
                return Średni.stworz(spekulantInput.historia_spekulanta_sredniego);
            case("regulujacy"):
                return Regulujący.stworz();
        }
        return null;
    }

    public abstract double policzCeneSprzedazy(Spekulant spekulant, Przedmiot produkt);

    public abstract void sprzedaj(Vector <OfertaSpekulanta> oferty, Spekulant spekulant);

    public abstract double policzCeneKupna(Spekulant spekulant, Przedmiot produkt);

    public abstract void kupuj(Vector<OfertaSpekulanta> oferty, Spekulant spekulant);

    public String podajNazwa() { return nazwa; }
}
