package Osoby.Praca;

import Osoby.Robotnik;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Rozkładowy extends TaktykaPracy {

    private Random random;
    private Rozkładowy() {
        super.nazwa = "rozkladowy";
        this.random = new Random();
    }

    public static Rozkładowy stworz() { return new Rozkładowy(); }

    public boolean czyPracuje(Robotnik robotnik) {
        int n = random.nextInt(robotnik.podajHistorieGieldy().size() + 3);
        if(n == 0)
            return false;
        else
            return true;
    }
    public Map<String, Object> toMap() {
        Map <String, Object> map = new HashMap<String, Object>();
        map.put("typ", "rozkladowy");
        return map;
    }
}
