package Osoby.Kariera;

public class Programista extends SciezkaKariery {
    private Programista(int poziom) {
        super.nazwa = "programista";
        super.produkt = "programy";
        super.poziom = poziom;
    }

    public static Programista stworz(int poziom) {
        return new Programista(poziom);
    }
}
