package Przedmioty;

public class ProgramyKomputerowe extends Przedmiot {

    private ProgramyKomputerowe(int x, int y) {
        super.nazwa = "programy";
        super.liczba = x;
        super.poziom = y;
    }

    public static ProgramyKomputerowe stworz(int x, int y) {
        return new ProgramyKomputerowe(x, y);
    }

    public static ProgramyKomputerowe stworz(int x) { return new ProgramyKomputerowe(x, 1); }

    public int podajPoziom() {
        return poziom;
    }
}
