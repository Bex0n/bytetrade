package Przedmioty;

public class Narzedzia extends Przedmiot {
    private Narzedzia(int x, int y) {
        super.nazwa = "narzedzia";
        super.liczba = x;
        super.poziom = y;
    }

    public int podajPoziom() {
        return poziom;
    }

    public static Narzedzia stworz(int x, int y) {
        return new Narzedzia(x, y);
    }

    public static Narzedzia stworz(int x) {return new Narzedzia(x, 1); }
}
