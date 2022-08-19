package Przedmioty;

import java.util.*;
public class Zasoby {

    private Diamenty diamenty;
    private Jedzenie jedzenie;
    private Vector <Ubrania> ubrania;
    private Vector <Narzedzia> narzedzia;
    private Vector <ProgramyKomputerowe> programykomputerowe;

    private Zasoby(double diamenty, int jedzenie, int [][] ubrania, int [][] narzedzia, int [][] programykomputerowe) {
        this.diamenty = Diamenty.stworz(diamenty);
        this.jedzenie = Jedzenie.stworz(jedzenie);
        this.ubrania = new Vector <Ubrania>();
        for (int i = 0; i < ubrania.length; i++)
            this.ubrania.add(Ubrania.stworz(ubrania[i][0], ubrania[i][1]));
        this.narzedzia = new Vector <Narzedzia>();
        for (int i = 0; i < narzedzia.length; i++)
            this.narzedzia.add(Narzedzia.stworz(narzedzia[i][0], narzedzia[i][1]));
        this.programykomputerowe = new Vector <ProgramyKomputerowe>();
        for (int i = 0; i < programykomputerowe.length; i++)
            this.programykomputerowe.add(ProgramyKomputerowe.stworz(programykomputerowe[i][0], programykomputerowe[i][1]));
    }

    public static Zasoby stworz(double diamenty, int jedzenie, int [][] ubrania, int [][] narzedzia, int [][] programykomputerowe) {
        return new Zasoby(diamenty, jedzenie, ubrania, narzedzia, programykomputerowe);
    }

    public static Zasoby stworz(Map <String, Object> map) {
        int [][] ubrania = new int [1][2];
        int [][] narzedzia = new int [1][2];
        int [][] programy = new int [1][2];
        ubrania[0][0] = ((Double) map.get("ubrania")).intValue();
        ubrania[0][1] = 1;
        narzedzia[0][0] = ((Double) map.get("narzedzia")).intValue();
        narzedzia[0][1] = 1;
        programy[0][0] = ((Double) map.get("programy")).intValue();
        programy[0][1] = 1;

        return Zasoby.stworz((Double) map.get("diamenty"), ((Double) map.get("jedzenie")).intValue(), ubrania, narzedzia, programy);
    }
    public Diamenty podajDiamenty() {
        return diamenty;
    }

    public Jedzenie podajJedzenie() { return jedzenie; }

    public Vector <Ubrania> podajUbrania() { return ubrania; }

    public Vector <Narzedzia> podajNarzedzia() { return narzedzia; }

    public Vector <ProgramyKomputerowe> podajProgramy() {
        return programykomputerowe;
    }

    public int maxiUbrania() {
        int maxi = 0;
        for (int i = 0; i < ubrania.size(); i++)
            maxi = Math.max(maxi, ubrania.get(i).podajPoziom());
        return maxi;
    }

    public int maxiNarzedzia() {
        int maxi = 0;
        for (int i = 0; i < narzedzia.size(); i++)
            maxi = Math.max(maxi, narzedzia.get(i).podajPoziom());
        return maxi;
    }

    public int maxiProgramy() {
        int maxi = 0;
        for (int i = 0; i < programykomputerowe.size(); i++)
            maxi = Math.max(maxi, programykomputerowe.get(i).podajPoziom());
        return maxi;
    }

    public int liczbaUbran() {
        int suma = 0;
        for (int i = 0; i < ubrania.size(); i++)
            suma += ubrania.get(i).liczba;
        return suma;
    }

    public class ProgramComparator implements Comparator<ProgramyKomputerowe> {
        @Override
        public int compare(ProgramyKomputerowe a, ProgramyKomputerowe b) {
            return a.podajPoziom() < b.podajPoziom() ? -1 : a.podajPoziom() == b.podajPoziom() ? 0 : 1;
        }
    }

    public void posortujProgramy() {
        Collections.sort(programykomputerowe, new ProgramComparator());
    }

    public int uzyjNarzedzia() {
        int suma = 0;
        for (int i = 0; i < narzedzia.size(); i++)
            suma += narzedzia.get(i).liczba * narzedzia.get(i).podajPoziom();
        narzedzia.clear();

        return suma;
    }

    public int uzyjUbrania() {
        int suma = 0;
        for (int i = 0; i < ubrania.size(); i++) {
            if(100 - suma >= ubrania.get(i).podajLiczba()) {
                ubrania.get(i).uzyj();
                suma += ubrania.get(i).podajLiczba();
                if (ubrania.get(i).zniszczone())
                    ubrania.remove(i);
            }
            else {
                int staraliczba = (int) ubrania.get(i).podajLiczba();
                int starypoziom = ubrania.get(i).podajPoziom();
                ubrania.get(i).ustawLiczbe(100-suma);
                ubrania.get(i).uzyj();
                if (ubrania.get(i).zniszczone())
                    ubrania.remove(i);
                this.dodaj(Ubrania.stworz(staraliczba - (100 - suma), starypoziom));
                suma = 100;
            }
            if(suma == 100)
                break;
        }
        return (100-suma);
    }

    public void dodaj(Przedmiot produkt) {
        switch(produkt.nazwa) {
            case "diamenty":
                diamenty.dodajLiczbe(produkt.liczba);
                return;
            case "jedzenie":
                jedzenie.dodajLiczbe(produkt.liczba);
                return;
            case "ubrania":
                for (int i = 0; i < ubrania.size(); i++)
                    if (ubrania.get(i).podajPoziom() == produkt.poziom)
                        if (ubrania.get(i).podajZuzycie() == ((Ubrania) produkt).podajZuzycie()) {
                            ubrania.get(i).dodajLiczbe(produkt.liczba);
                            return;
                        }
                ubrania.add(Ubrania.stworz((int) produkt.podajLiczba(), produkt.podajPoziom()));
                return;
            case "narzedzia":
                for (int i = 0; i < narzedzia.size(); i++)
                    if (narzedzia.get(i).podajPoziom() == produkt.poziom) {
                        narzedzia.get(i).dodajLiczbe(produkt.liczba);
                        return;
                    }
                narzedzia.add(Narzedzia.stworz((int) produkt.podajLiczba(), produkt.podajPoziom()));
                return;
            case "programy":
                for (int i = 0; i < programykomputerowe.size(); i++)
                    if (programykomputerowe.get(i).podajPoziom() == produkt.poziom) {
                        programykomputerowe.get(i).dodajLiczbe(produkt.liczba);
                        return;
                    }
                programykomputerowe.add(ProgramyKomputerowe.stworz((int) produkt.podajLiczba(), produkt.podajPoziom()));
                return;
        }
        return;
    }

}
