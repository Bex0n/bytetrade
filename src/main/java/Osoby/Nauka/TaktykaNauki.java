package Osoby.Nauka;

import Osoby.*;

public abstract class TaktykaNauki {
    protected String nazwa;
    public abstract void uczSie(Robotnik robotnik);

    public static TaktykaNauki stworz(String zmiana) {
        switch(zmiana) {
            case "konserwatysta":
                return Konserwatysta.stworz();
            case "rewolucjonista":
                return Rewolucjonista.stworz();
        }
        return null;
    }

    public String podajNazwa() { return nazwa; }
}
