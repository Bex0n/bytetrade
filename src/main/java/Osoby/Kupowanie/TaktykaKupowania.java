package Osoby.Kupowanie;

import Gielda.*;
import Osoby.*;
import Osoby.Produkcja.TaktykaProdukcji;
import Przedmioty.*;

import java.util.*;

public abstract class TaktykaKupowania {

    public static TaktykaKupowania stworz(Map <String, Object> map) {
        switch((String) map.get("typ")) {
            case "gadzeciarz":
                return Gadzeciarz.stworz(((Double) map.get("liczba_narzedzi")).intValue());
            case "zmechanizowany":
                return Zmechanizowany.stworz(((Double) map.get("liczba_narzedzi")).intValue());
            case "czyscioszek":
                return Czyścioszek.stworz();
            case "technofob":
                return Technofob.stworz();
        }
        return null;
    }

    public abstract void zrobZakupy(Vector <Wymiana> wymiany, Robotnik robotnik, Vector <OfertaSpekulanta> oferty);

    public static void zrobZakupyJedzenia(Vector<Wymiana> wymiany, Robotnik robotnik, Vector <OfertaSpekulanta> oferty) {
        Gielda.wykonajOferteKupnaRobotnika(wymiany, oferty, OfertaRobotnika.stworz(Jedzenie.stworz(100), robotnik));
    }

    public static void zrobZakupyNarzedzia(Vector<Wymiana> wymiany, Robotnik robotnik, Vector <OfertaSpekulanta> oferty, int liczba_narzedzi) {
        Gielda.wykonajOferteKupnaRobotnika(wymiany, oferty, OfertaRobotnika.stworz(Narzedzia.stworz(liczba_narzedzi), robotnik));
    }

    public static void zrobZakupyUbrania(Vector<Wymiana> wymiany, Robotnik robotnik, Vector <OfertaSpekulanta> oferty) {
        int liczba = Math.max(0, 100 - robotnik.liczbaUbran());
        Gielda.wykonajOferteKupnaRobotnika(wymiany, oferty, OfertaRobotnika.stworz(Ubrania.stworz(liczba), robotnik));
    }

    public static void zrobZakupyProgramy(Vector<Wymiana> wymiany, Robotnik robotnik, Vector <OfertaSpekulanta> oferty) {
        int liczba = robotnik.podajProdukcjeOstatniegoDnia();
        Gielda.wykonajOferteKupnaRobotnika(wymiany, oferty, OfertaRobotnika.stworz(ProgramyKomputerowe.stworz(liczba), robotnik));
    }

    public abstract Map <String, Object> toMap ();
}
