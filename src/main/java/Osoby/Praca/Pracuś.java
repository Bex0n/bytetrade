package Osoby.Praca;

import Gielda.Gielda;
import Osoby.Robotnik;

import java.util.HashMap;
import java.util.Map;

public class Pracuś extends TaktykaPracy{

    private Pracuś() {
        super.nazwa = "pracus";
    }

    public static Pracuś stworz() { return new Pracuś(); }

    public boolean czyPracuje(Robotnik robotnik) {
        return true;
    }
    public Map<String, Object> toMap() {
        Map <String, Object> map = new HashMap<String, Object>();
        map.put("typ", "pracus");
        return map;
    }
}
