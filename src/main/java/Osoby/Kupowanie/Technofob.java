package Osoby.Kupowanie;

import Gielda.*;
import Osoby.*;
import java.util.*;

public class Technofob extends TaktykaKupowania {

    private Technofob() {}

    public static Technofob stworz() {
        return new Technofob();
    }
    public void zrobZakupy(Vector<Wymiana> wymiany, Robotnik robotnik, Vector <OfertaSpekulanta> oferty) {
        TaktykaKupowania.zrobZakupyJedzenia(wymiany, robotnik, oferty);
        return;
    }

    public Map <String, Object> toMap() {
        Map <String, Object> map = new HashMap<String, Object>();
        map.put("typ", "technofob");
        return map;
    }
}
