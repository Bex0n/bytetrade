package Osoby.Praca;

import Gielda.*;
import Osoby.*;
import java.util.*;

public class Student extends TaktykaPracy {
    private int zapas;
    private int okres;

    private Student(int zapas, int okres) {
        super.nazwa = "student";
        this.zapas = zapas;
        this.okres = okres;
    }

    public static Student stworz(int zapas, int okres) {
        return new Student(zapas, okres);
    }

    public boolean czyPracuje(Robotnik robotnik) {
        double diamenty = robotnik.podajZasoby().podajDiamenty().podajLiczba();
        double cena = Gielda.sredniaZOstatnichDni(robotnik.podajHistorieGieldy(), okres, "jedzenie");
        if (diamenty / cena >= 100 * zapas)
            return false;
        else
            return true;
    }

    public Map <String, Object> toMap() {
        Map <String, Object> map = new HashMap <String, Object>();
        map.put("typ", "student");
        map.put("zapas", zapas);
        map.put("okres", okres);
        return map;
    }
}
