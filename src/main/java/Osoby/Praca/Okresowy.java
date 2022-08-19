package Osoby.Praca;

import Osoby.Robotnik;

import java.util.HashMap;
import java.util.Map;

public class Okresowy extends TaktykaPracy {
    private int okresowosc_nauki;

    private Okresowy(int okresowosc_nauki) {
        super.nazwa = "okresowy";
        this.okresowosc_nauki = okresowosc_nauki;
    }

    public static Okresowy stworz(int okresowosc_nauki) { return new Okresowy(okresowosc_nauki); }

    public boolean czyPracuje(Robotnik robotnik) {
        int dzien = robotnik.podajHistorieGieldy().size();
        if (dzien % okresowosc_nauki == 0)
            return false;
        else
            return true;
    }

    public Map<String, Object> toMap() {
        Map <String, Object> map = new HashMap<String, Object>();
        map.put("typ", "okresowy");
        map.put("okresowosc_nauki", okresowosc_nauki);
        return map;
    }
}
