package Gielda;

import Osoby.*;
import Przedmioty.*;

import java.util.*;

public class Dzien {
    private Vector <Wymiana> wymiany;

    private Dzien(Vector <Wymiana> wymiany) {
        this.wymiany = wymiany;
    }

    public static Dzien stworz(Vector <Wymiana> wymiany) { return new Dzien(wymiany); }

    public static Dzien zerowyStworz(Map <String, Object> ceny) {
        ProgramyKomputerowe programy = ProgramyKomputerowe.stworz(1, 1);
        Jedzenie jedzenie = Jedzenie.stworz(1, 1);
        Ubrania ubrania = Ubrania.stworz(1, 1);
        Narzedzia narzedzia = Narzedzia.stworz(1, 1);
        Diamenty programycena = Diamenty.stworz((double) ceny.get("programy"));
        Diamenty jedzeniecena = Diamenty.stworz((double) ceny.get("jedzenie"));
        Diamenty ubraniacena = Diamenty.stworz((double) ceny.get("ubrania"));
        Diamenty narzedziacena = Diamenty.stworz((double) ceny.get("narzedzia"));
        Vector <Wymiana> wymiany = new Vector <Wymiana>();
        wymiany.add(Wymiana.stworz(programy, programycena));
        wymiany.add(Wymiana.stworz(jedzenie, jedzeniecena));
        wymiany.add(Wymiana.stworz(ubrania, ubraniacena));
        wymiany.add(Wymiana.stworz(narzedzia, narzedziacena));
        return new Dzien(wymiany);
    }

    public Vector <Wymiana> podaj() {
        return wymiany;
    }

    public Vector <Wymiana> filtruj(String produkt) {
        Vector <Wymiana> filtrowane_wymiany = new Vector <Wymiana>();
        for (int i = 0; i < wymiany.size(); i++)
            if (wymiany.get(i).sprzedanyPrzedmiot().podajNazwa().equals(produkt))
                filtrowane_wymiany.add(wymiany.get(i));
        return filtrowane_wymiany;
    }

    public double srednia(String produkt) {
        Vector <Wymiana> wymiany_produktu = this.filtruj(produkt);
        double suma = 0;
        double liczba = 0;
        for (int i = 0; i < wymiany_produktu.size(); i++) {
            suma += wymiany_produktu.get(i).cena();
            liczba += wymiany_produktu.get(i).sprzedanyPrzedmiot().podajLiczba();
        }
        if (liczba == 0)
            return 0;
        return suma / liczba;
    }

    public double maximum(String produkt) {
        Vector <Wymiana> wymiany_produktu = this.filtruj(produkt);
        double maxi = 0;
        for (int i = 0; i < wymiany_produktu.size(); i++) {
            maxi = Math.max(maxi, wymiany_produktu.get(i).cenaZaSztuke());
        }
        return maxi;
    }

    public double minimum(String produkt) {
        Vector <Wymiana> wymiany_produktu = this.filtruj(produkt);
        double mini = 0;
        for (int i = 0; i < wymiany_produktu.size(); i++) {
            mini = Math.max(mini, wymiany_produktu.get(i).cenaZaSztuke());
        }
        return mini;
    }

    public int policz(String produkt) {
        Vector <Wymiana> wymiany_produktu = this.filtruj(produkt);
        int suma = 0;
        for (int i = 0; i < wymiany_produktu.size(); i++)
            suma += wymiany_produktu.get(i).sprzedanyPrzedmiot().podajLiczba();
        return suma;
    }
}
