package Osoby.Kupowanie;

import Gielda.*;
import Osoby.*;
import java.util.*;

public class Czyścioszek extends TaktykaKupowania {

    private Czyścioszek() {}

    public static Czyścioszek stworz() {
        return new Czyścioszek();
    }
    public void zrobZakupy(Vector<Wymiana> wymiany, Robotnik robotnik, Vector <OfertaSpekulanta> oferty) {
        TaktykaKupowania.zrobZakupyJedzenia(wymiany, robotnik, oferty);
        TaktykaKupowania.zrobZakupyUbrania(wymiany, robotnik, oferty);
        return;
    }

    public Map <String, Object> toMap() {
        Map <String, Object> map = new HashMap<String, Object>();
        map.put("typ", "czyscioszek");
        return map;
    }
}
