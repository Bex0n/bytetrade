package Przedmioty;

public class Jedzenie extends Przedmiot {

    private Jedzenie(int x) {
        super.nazwa = "jedzenie";
        super.liczba = x;
        super.poziom = 1;
    }

    public void dodaj(int x) {
        liczba += x;
        return;
    }

    public void ustaw(int x) {
        liczba = x;
        return;
    }

    public int wartosc() {
        return (int) liczba;
    }

    public static Jedzenie stworz(int x) {
        return new Jedzenie(x);
    }

    public static Jedzenie stworz(int x, int y) {return new Jedzenie(x); }
}
