package Osoby.Kariera;

public class Rzemieślnik extends SciezkaKariery {
    private Rzemieślnik (int poziom) {
        super.nazwa = "rzemieslnik";
        super.produkt = "ubrania";
        super.poziom = poziom;
    }

    public static Rzemieślnik stworz (int poziom) {
        return new Rzemieślnik(poziom);
    }
}
