package Osoby.Kariera;

public class Górnik extends SciezkaKariery {
    private Górnik (int poziom) {
        super.nazwa = "gornik";
        super.produkt = "diamenty";
        super.poziom = poziom;
    }

    public static Górnik stworz (int poziom) {
        return new Górnik(poziom);
    }
}
