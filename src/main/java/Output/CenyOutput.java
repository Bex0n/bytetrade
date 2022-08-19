package Output;

import Gielda.*;
import java.util.*;
import Przedmioty.*;

public class CenyOutput {

    private double programy;
    private double jedzenie;
    private double ubrania;
    private double narzedzia;

    private CenyOutput(double programy, double jedzenie, double ubrania, double narzedzia) {
        this.programy = programy;
        this.jedzenie = jedzenie;
        this.ubrania = ubrania;
        this.narzedzia = narzedzia;
    }

    public static double policzSrednia(Vector <Dzien> historia, String produkt) {
        double suma = 0;
        int liczba = 0;
        for (int i = 0; i < historia.size(); i++) {
            for (int j = 0; j < historia.get(i).podaj().size(); j++) {
                Przedmiot sprzedany = historia.get(i).podaj().get(j).sprzedanyPrzedmiot();
                if (sprzedany.podajNazwa().equals(produkt)) {
                    suma += historia.get(i).podaj().get(j).cena();
                    liczba += sprzedany.podajLiczba();
                }
            }
        }
        if (liczba == 0)
            return 0;
        return suma / liczba;
    }

    public static double policzMax(Vector <Dzien> historia, String produkt) {
        double maxi = 0;
        for (int i = 0; i < historia.size(); i++) {
            for (int j = 0; j < historia.get(i).podaj().size(); j++) {
                Przedmiot sprzedany = historia.get(i).podaj().get(j).sprzedanyPrzedmiot();
                if (sprzedany.podajNazwa().equals(produkt)) {
                    maxi = Math.max(maxi, historia.get(i).podaj().get(j).cenaZaSztuke());
                }
            }
        }
        return maxi;
    }

    public static double policzMin(Vector <Dzien> historia, String produkt) {
        double mini = 0;
        for (int i = 0; i < historia.size(); i++) {
            for (int j = 0; j < historia.get(i).podaj().size(); j++) {
                Przedmiot sprzedany = historia.get(i).podaj().get(j).sprzedanyPrzedmiot();
                if (sprzedany.podajNazwa().equals(produkt)) {
                    mini = Math.max(mini, historia.get(i).podaj().get(j).cenaZaSztuke());
                }
            }
        }
        return mini;
    }


    public static CenyOutput cenySrednie(Vector <Dzien> historia) {
        double jedzenie = policzSrednia(historia, "jedzenie");
        double narzedzia = policzSrednia(historia, "narzedzia");
        double ubrania = policzSrednia(historia, "ubrania");
        double programy = policzSrednia(historia, "programy");

        return new CenyOutput(programy, jedzenie, ubrania, narzedzia);
    }

    public static CenyOutput cenyMax(Vector <Dzien> historia) {
        double jedzenie = policzMax(historia, "jedzenie");
        double narzedzia = policzMax(historia, "narzedzia");
        double ubrania = policzMax(historia, "ubrania");
        double programy = policzMax(historia, "programy");

        return new CenyOutput(programy, jedzenie, ubrania, narzedzia);
    }

    public static CenyOutput cenyMin(Vector <Dzien> historia) {
        double jedzenie = policzMin(historia, "jedzenie");
        double narzedzia = policzMin(historia, "narzedzia");
        double ubrania = policzMin(historia, "ubrania");
        double programy = policzMin(historia, "programy");

        return new CenyOutput(programy, jedzenie, ubrania, narzedzia);
    }
}
