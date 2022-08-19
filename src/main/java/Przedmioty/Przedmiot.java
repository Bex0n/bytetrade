package Przedmioty;

public abstract class Przedmiot {
    protected String nazwa;
    protected double liczba;
    protected int poziom;

    public String podajNazwa() {
        return nazwa;
    }

    public double podajLiczba() {
        return liczba;
    }

    public int podajPoziom() {
        return poziom;
    }

    public void ustawLiczbe(double x) {
        liczba = x;
    }
    public void dodajLiczbe(double x) {liczba += x; }


    public static boolean porownaj (Przedmiot a, Przedmiot b) {
        if (a.nazwa == b.nazwa && a.poziom == b.poziom)
            return true;
        return false;
    }

    public static Przedmiot kopiuj (Przedmiot a) {
        switch(a.nazwa) {
            case "diamenty":
                return Diamenty.stworz(a.liczba, a.poziom);
            case "jedzenie":
                return Jedzenie.stworz((int) a.liczba, a.poziom);
            case "ubrania":
                return Ubrania.stworz((int) a.liczba, a.poziom);
            case "narzedzia":
                return Narzedzia.stworz((int) a.liczba, a.poziom);
            case "programy":
                return ProgramyKomputerowe.stworz((int) a.liczba, a.poziom);
        }
        return null;
    }
}
