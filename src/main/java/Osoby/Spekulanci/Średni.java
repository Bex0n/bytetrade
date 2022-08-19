package Osoby.Spekulanci;

import Osoby.*;
import Przedmioty.*;
import Gielda.*;
import java.util.*;

public class Średni extends TaktykaSpekulanta {

    private int historia_spekulanta_sredniego;
    private Średni(int historia_spekulanta_sredniego) {
        super.nazwa = "sredni";
        this.historia_spekulanta_sredniego = historia_spekulanta_sredniego;
    }

    public static Średni stworz(int historia_spekulanta_sredniego) {
        return new Średni(historia_spekulanta_sredniego);
    }

    public double policzCeneSprzedazy(Spekulant spekulant, Przedmiot produkt) {
        Vector <Dzien> historia = spekulant.podajGielda().podajHistorie();
        double suma = 0;
        int liczba = 0;
        for (int i = 1; i <= historia_spekulanta_sredniego; i++) {
            if (historia.size() - i < 0)
                break;
            if (historia.get(historia.size() - i).srednia(produkt.podajNazwa()) != 0) {
                suma += historia.get(historia.size() - i).srednia(produkt.podajNazwa());
                liczba++;
            }
        }
        if (liczba == 0)
            return 0;
        return (double) suma * 105 / 100 / liczba;
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
        double suma = 0;
        int liczba = 0;
        for (int i = 1; i <= historia_spekulanta_sredniego; i++) {
            if (historia.size() - i < 0)
                break;
            if (historia.get(historia.size() - i).srednia(produkt.podajNazwa()) != 0) {
                suma += historia.get(historia.size() - i).srednia(produkt.podajNazwa());
                liczba++;
            }
        }
        if (liczba == 0)
            return 0;
        return (double) suma / liczba;
    }

    public void kupuj(Vector <OfertaSpekulanta> oferty, Spekulant spekulant) {
        Zasoby zasoby = spekulant.podajZasoby();

        if (zasoby.podajJedzenie().podajLiczba() > 0 && spekulant.policzCeneSprzedazy(zasoby.podajJedzenie()) > 0)
            oferty.add(OfertaSpekulanta.stworz(zasoby.podajJedzenie(),
                    spekulant.policzCeneSprzedazy(zasoby.podajJedzenie()) * 90 / 100,
                    spekulant)
            );
        else
            oferty.add(OfertaSpekulanta.stworz(zasoby.podajJedzenie(),
                    spekulant.policzCeneSprzedazy(zasoby.podajJedzenie()) * 95 / 100,
                    spekulant)
            );
        for (int i = 0; i < zasoby.podajNarzedzia().size(); i++) {
            if (zasoby.podajNarzedzia().get(i).podajLiczba() > 0 && spekulant.policzCeneSprzedazy(zasoby.podajNarzedzia().get(i)) > 0)
                oferty.add(OfertaSpekulanta.stworz(zasoby.podajNarzedzia().get(i),
                        spekulant.policzCeneSprzedazy(zasoby.podajNarzedzia().get(i)) * 90 / 100,
                        spekulant)
                );
            else
                oferty.add(OfertaSpekulanta.stworz(zasoby.podajNarzedzia().get(i),
                        spekulant.policzCeneSprzedazy(zasoby.podajNarzedzia().get(i)) * 95 / 100,
                        spekulant)
                );
        }
        for (int i = 0; i < zasoby.podajUbrania().size(); i++) {
            if (zasoby.podajUbrania().get(i).podajLiczba() > 0 && spekulant.policzCeneSprzedazy(zasoby.podajUbrania().get(i)) > 0)
                oferty.add(OfertaSpekulanta.stworz(zasoby.podajUbrania().get(i),
                        spekulant.policzCeneSprzedazy(zasoby.podajUbrania().get(i)) * 90 / 100,
                        spekulant)
                );
            else
                oferty.add(OfertaSpekulanta.stworz(zasoby.podajUbrania().get(i),
                        spekulant.policzCeneSprzedazy(zasoby.podajUbrania().get(i)) * 95 / 100,
                        spekulant)
                );
        }
        for (int i = 0; i < zasoby.podajProgramy().size(); i++) {
            if (zasoby.podajProgramy().get(i).podajLiczba() > 0 && spekulant.policzCeneSprzedazy(zasoby.podajProgramy().get(i)) > 0)
                oferty.add(OfertaSpekulanta.stworz(zasoby.podajProgramy().get(i),
                        spekulant.policzCeneSprzedazy(zasoby.podajProgramy().get(i)) * 90 / 100,
                        spekulant)
                );
            else
                oferty.add(OfertaSpekulanta.stworz(zasoby.podajProgramy().get(i),
                        spekulant.policzCeneSprzedazy(zasoby.podajProgramy().get(i)) * 95 / 100,
                        spekulant)
                );
        }
        return;
    }
}
