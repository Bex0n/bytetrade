package Osoby.Praca;

import Osoby.*;
import java.util.*;

public abstract class TaktykaPracy {
    protected String nazwa;

    public String podajNazwa() { return nazwa; }
    public abstract boolean czyPracuje(Robotnik robotnik);

    public static TaktykaPracy stworz(Map <String, Object> map) {
        switch((String) map.get("typ")) {
            case "pracus":
                return Pracuś.stworz();
            case "oszczedny":
                return Oszczędny.stworz((int) map.get("limit_diamentów"));
            case "student":
                return Student.stworz(((Double) map.get("zapas")).intValue(),((Double) map.get("okres")).intValue());
            case "okresowy":
                return Okresowy.stworz((int) map.get("okresowosc_nauki"));
            case "rozkladowy":
                return Rozkładowy.stworz();
        }
        return null;
    }

    public abstract Map <String, Object> toMap();
}
