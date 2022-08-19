package Osoby.Kariera;

public class Inżynier extends SciezkaKariery {
    private Inżynier (int poziom) {
        super.nazwa = "inzynier";
        super.produkt = "narzedzia";
        super.poziom = poziom;
    }

    public static Inżynier stworz (int poziom) { return new Inżynier(poziom); }
}
