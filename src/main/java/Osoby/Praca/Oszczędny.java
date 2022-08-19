package Osoby.Praca;

import Gielda.Gielda;
import Osoby.Robotnik;

import java.util.HashMap;
import java.util.Map;

public class Oszczędny extends TaktykaPracy {
    private int limit_diamentów;

    private Oszczędny(int limit_diamentów) {
        super.nazwa = "oszczedny";
        this.limit_diamentów = limit_diamentów;
    }

    public static Oszczędny stworz(int limit_diamentów) { return new Oszczędny(limit_diamentów); }

    public boolean czyPracuje(Robotnik robotnik) {
        double diamenty = robotnik.podajZasoby().podajDiamenty().podajLiczba();
        if (diamenty > limit_diamentów)
            return true;
        else
            return false;
    }

    public Map<String, Object> toMap() {
        Map <String, Object> map = new HashMap<String, Object>();
        map.put("typ", "oszczedny");
        map.put("limit_diamentów", limit_diamentów);
        return map;
    }
}
