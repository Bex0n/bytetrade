package Przedmioty;

public class Ubrania extends Przedmiot {
    private int zuzycie;

    private Ubrania(int x, int y, int z) {
        super.nazwa = "ubrania";
        super.liczba = x;
        super.poziom = y;
        this.zuzycie = z;
    }

    public int podajPoziom() { return poziom; }

    public int podajZuzycie() { return zuzycie; }

    public void uzyj() {
        zuzycie++;
    }

    public boolean zniszczone() {
        return zuzycie >= poziom * poziom;
    }

    public static Ubrania stworz(int x, int y) {
        return new Ubrania(x, y, 0);
    }

    public static Ubrania stworz(int x) { return new Ubrania(x, 1, 0); }
}
