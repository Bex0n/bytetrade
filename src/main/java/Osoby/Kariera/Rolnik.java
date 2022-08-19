package Osoby.Kariera;

public class Rolnik extends SciezkaKariery {
    private Rolnik (int poziom) {
        super.nazwa = "rolnik";
        super.produkt = "jedzenie";
        super.poziom = poziom;
    }

    public static Rolnik stworz (int poziom) {
        return new Rolnik(poziom);
    }
}
