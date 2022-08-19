package Osoby.Nauka;

import Osoby.*;

public class Konserwatysta extends TaktykaNauki {
    private Konserwatysta() {
        super.nazwa = "konserwatysta";
    }

    public static Konserwatysta stworz() {
        return new Konserwatysta();
    }

    public void uczSie(Robotnik robotnik) {
        robotnik.podajKariera().aktualnakariera().podniesPoziom();
    }
}
