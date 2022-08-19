package Osoby.Spekulanci;

import Osoby.*;
import Przedmioty.*;
import Gielda.*;
import java.util.*;

public class Wypukly extends TaktykaSpekulanta {
    private Wypukly() {
        super.nazwa = "wypukly";
    }

    public static Wypukly stworz() {
        return new Wypukly();
    }

    public double policzCeneSprzedazy(Spekulant spekulant, Przedmiot produkt) {
        Vector <Dzien> historia = spekulant.podajGielda().podajHistorie();
        double a = 0, b = 0, c = 0;
        if (historia.size()-1 >= 0 )
            a = historia.get(historia.size()-1).srednia(produkt.podajNazwa());
        if (historia.size()-2 >= 0 )
            b = historia.get(historia.size()-2).srednia(produkt.podajNazwa());
        if (historia.size()-3 >= 0 )
            c = historia.get(historia.size()-3).srednia(produkt.podajNazwa());
        if (a == 0 || b == 0 || c == 0)
            return (a * 110) / 100;
        else if (2 * b > a + c)
            return (a * 110) / 100;
        return 0;
    }

    public void sprzedaj(Vector <OfertaSpekulanta> oferty, Spekulant spekulant) {
        Zasoby zasoby = spekulant.podajZasoby();

        if (zasoby.podajJedzenie().podajLiczba() > 0 && spekulant.policzCeneSprzedazy(zasoby.podajJedzenie()) > 0)
            oferty.add(OfertaSpekulanta.stworz(zasoby.podajJedzenie(),
                    spekulant.policzCeneSprzedazy(zasoby.podajJedzenie()),
                    spekulant)
            );
        for (int i = 0; i < zasoby.podajNarzedzia().size(); i++) {
            if (zasoby.podajNarzedzia().get(i).podajLiczba() > 0 && spekulant.policzCeneSprzedazy(zasoby.podajNarzedzia().get(i)) > 0)
                oferty.add(OfertaSpekulanta.stworz(zasoby.podajNarzedzia().get(i),
                        spekulant.policzCeneSprzedazy(zasoby.podajNarzedzia().get(i)),
                        spekulant)
                );
        }
        for (int i = 0; i < zasoby.podajUbrania().size(); i++) {
            if (zasoby.podajUbrania().get(i).podajLiczba() > 0 && spekulant.policzCeneSprzedazy(zasoby.podajUbrania().get(i)) > 0)
                oferty.add(OfertaSpekulanta.stworz(zasoby.podajUbrania().get(i),
                        spekulant.policzCeneSprzedazy(zasoby.podajUbrania().get(i)),
                        spekulant)
                );
        }
        for (int i = 0; i < zasoby.podajProgramy().size(); i++) {
            if (zasoby.podajProgramy().get(i).podajLiczba() > 0 && spekulant.policzCeneSprzedazy(zasoby.podajProgramy().get(i)) > 0)
                oferty.add(OfertaSpekulanta.stworz(zasoby.podajProgramy().get(i),
                        spekulant.policzCeneSprzedazy(zasoby.podajProgramy().get(i)),
                        spekulant)
                );
        }
        return;
    }


    public double policzCeneKupna(Spekulant spekulant, Przedmiot produkt) {
        Vector <Dzien> historia = spekulant.podajGielda().podajHistorie();
        double a = 0, b = 0, c = 0;
        if (historia.size()-1 >= 0 )
            a = historia.get(historia.size()-1).srednia(produkt.podajNazwa());
        if (historia.size()-2 >= 0 )
            b = historia.get(historia.size()-2).srednia(produkt.podajNazwa());
        if (historia.size()-3 >= 0 )
            c = historia.get(historia.size()-3).srednia(produkt.podajNazwa());
        if (a == 0 || b == 0 || c == 0)
            return (a * 90) / 100;
        else if (2 * b < a + c)
            return (a * 90) / 100;
        return 0;
    }

    public void kupuj(Vector <OfertaSpekulanta> oferty, Spekulant spekulant) {
        Zasoby zasoby = spekulant.podajZasoby();
        double cena_jedzenia = spekulant.policzCeneKupna(Diamenty.stworz(100));
        double cena_narzedzi = spekulant.policzCeneKupna(Narzedzia.stworz(100));
        double cena_ubran = spekulant.policzCeneKupna(Ubrania.stworz(100));
        double cena_programow = spekulant.policzCeneKupna(ProgramyKomputerowe.stworz(100));

        if (cena_jedzenia > 0)
            oferty.add(OfertaSpekulanta.stworz(Jedzenie.stworz(100),
                    cena_jedzenia,
                    spekulant)
            );

        if (cena_narzedzi > 0)
            for (int i = 1; i <= 20; i++)
                oferty.add(OfertaSpekulanta.stworz(Narzedzia.stworz(100, i),
                        cena_narzedzi,
                        spekulant)
                );

        if (cena_ubran > 0)
            for (int i = 1; i <= 20; i++)
                oferty.add(OfertaSpekulanta.stworz(Ubrania.stworz(100, i),
                        cena_ubran,
                        spekulant)
                );

        if (cena_programow > 0)
            for (int i = 1; i <= 20; i++)
                oferty.add(OfertaSpekulanta.stworz(ProgramyKomputerowe.stworz(100, i),
                        cena_programow,
                        spekulant)
                );
        return;
    }
}
