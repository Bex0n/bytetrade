package Osoby.Kariera;

public abstract class SciezkaKariery {
    protected String nazwa;
    protected String produkt;
    protected int poziom;

    public static SciezkaKariery stworz (String nazwa, int poziom) {
        switch(nazwa) {
            case "rolnik":
                return Rolnik.stworz(poziom);
            case "gornik":
                return Górnik.stworz(poziom);
            case "rzemieslnik":
                return Rzemieślnik.stworz(poziom);
            case "inzynier":
                return Inżynier.stworz(poziom);
            case "programista":
                return Programista.stworz(poziom);
        }
        return Rolnik.stworz(poziom);
    }

    public String podajNazwa() { return nazwa; }

    public String podajProdukt() { return produkt; }

    public int podajPoziom() { return poziom; }

    public int podajPremie() {
        if (poziom == 1)
            return 50;
        if (poziom == 2)
            return 150;
        if (poziom >= 3)
            return 300 + (poziom-3) * 25;
        return 0;
    }

    public void podniesPoziom() {
        poziom++;
    }
}
