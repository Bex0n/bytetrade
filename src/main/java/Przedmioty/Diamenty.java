package Przedmioty;

public class Diamenty extends Przedmiot {

    private Diamenty(double x) {
        super.nazwa = "diamenty";
        super.liczba = x;
        super.poziom = 1;
    }

    @Override
    public double podajLiczba() {
        return liczba;
    }

    public static Diamenty stworz(double x) { return new Diamenty((double) x); }

    public static Diamenty stworz(double x, int y) { return new Diamenty((double) x); }
}
