package Osoby.Spekulanci;

import Osoby.*;
import Przedmioty.*;
import Gielda.*;
import java.util.*;

public class Regulujący extends TaktykaSpekulanta {
    private Regulujący() {
        super.nazwa = "regulujacy";
    }

    public static Regulujący stworz() {
        return new Regulujący();
    }

    public double policzCeneSprzedazy(Spekulant spekulant, Przedmiot produkt) {
        Vector <Dzien> historia = spekulant.podajGielda().podajHistorie();
        double stala = policzP_i(spekulant, produkt) * Math.max(1, policzP_i_poprzednie(spekulant, produkt));

        return (double) historia.get(historia.size()-1).srednia(produkt.podajNazwa()) * stala * 110 / 100;
    }

    public void sprzedaj(Vector <OfertaSpekulanta> oferty, Spekulant spekulant) {
        if(spekulant.podajGielda().podajHistorie().size() == 1)
            return;

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

    public static int policzP_i_poprzednie(Spekulant spekulant, Przedmiot produkt) {
        Vector <Dzien> historia = spekulant.podajGielda().podajHistorie();
        Vector <OfertaRobotnika> poprzednie_oferty = spekulant.podajGielda().podajPoprzednieOferty();
        int suma = 0;
        for (int i = 0; i < poprzednie_oferty.size(); i++)
            if (poprzednie_oferty.get(i).podajPrzedmiot().podajNazwa() == produkt.podajNazwa())
                if (poprzednie_oferty.get(i).podajPrzedmiot().podajPoziom() == produkt.podajPoziom())
                    suma += poprzednie_oferty.get(i).podajPrzedmiot().podajLiczba();
        return suma;
    }

    public static int policzP_i(Spekulant spekulant, Przedmiot produkt) {
        Vector <Dzien> historia = spekulant.podajGielda().podajHistorie();
        Vector <OfertaRobotnika> aktualne_oferty = spekulant.podajGielda().podajAktualneOferty();
        int suma = 0;
        for (int i = 0; i < aktualne_oferty.size(); i++)
            if (aktualne_oferty.get(i).podajPrzedmiot().podajNazwa() == produkt.podajNazwa())
                if (aktualne_oferty.get(i).podajPrzedmiot().podajPoziom() == produkt.podajPoziom())
                    suma += aktualne_oferty.get(i).podajPrzedmiot().podajLiczba();
        return suma;
    }

    public double policzCeneKupna(Spekulant spekulant, Przedmiot produkt) {
        Vector <Dzien> historia = spekulant.podajGielda().podajHistorie();
        double stala = policzP_i(spekulant, produkt) * Math.max(1, policzP_i_poprzednie(spekulant, produkt));

        return (double) historia.get(historia.size()-1).srednia(produkt.podajNazwa()) * stala * 90 / 100;
    }

    public void kupuj(Vector <OfertaSpekulanta> oferty, Spekulant spekulant) {
        if(spekulant.podajGielda().podajHistorie().size() == 1)
            return;

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
