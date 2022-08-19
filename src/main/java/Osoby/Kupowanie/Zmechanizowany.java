package Osoby.Kupowanie;

import Gielda.*;
import Osoby.*;
import java.util.*;

public class Zmechanizowany extends TaktykaKupowania {

    private int liczba_narzedzi;

    private Zmechanizowany(int liczba_narzedzi) {
        this.liczba_narzedzi = liczba_narzedzi;
    }

    public static Zmechanizowany stworz(int liczba_narzedzi) {
        return new Zmechanizowany(liczba_narzedzi);
    }

    public int podajLiczbaNarzedzi() {
        return liczba_narzedzi;
    }
    public void zrobZakupy(Vector<Wymiana> wymiany, Robotnik robotnik, Vector <OfertaSpekulanta> oferty) {
        TaktykaKupowania.zrobZakupyJedzenia(wymiany, robotnik, oferty);
        TaktykaKupowania.zrobZakupyNarzedzia(wymiany, robotnik, oferty, liczba_narzedzi);
        TaktykaKupowania.zrobZakupyUbrania(wymiany, robotnik, oferty);
        return;
    }

    public Map <String, Object> toMap() {
        Map <String, Object> map = new HashMap<String, Object>();
        map.put("typ", "zmechanizowany");
        map.put("liczba_narzedzi", liczba_narzedzi);
        return map;
    }
}
