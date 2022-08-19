package Output;

import java.util.*;
import Przedmioty.*;

public class ZasobyOutput {
    public double diamenty;
    public int [] ubrania;
    public int [] narzedzia;
    public int jedzenie;
    public int [] programy;

    public ZasobyOutput(Zasoby zasoby) {
        this.diamenty = zasoby.podajDiamenty().podajLiczba();
        int [] ubrania = new int [zasoby.maxiUbrania()];
        for (int i = 0; i < zasoby.podajUbrania().size(); i++) {
            int pozycja = zasoby.podajUbrania().get(i).podajPoziom() - 1;
            ubrania[pozycja] += zasoby.podajUbrania().get(i).podajLiczba();
        }
        this.ubrania = ubrania;
        int [] narzedzia = new int [zasoby.maxiNarzedzia()];
        for (int i = 0; i < zasoby.podajNarzedzia().size(); i++) {
            int pozycja = zasoby.podajNarzedzia().get(i).podajPoziom() - 1;
            narzedzia[pozycja] += zasoby.podajNarzedzia().get(i).podajLiczba();
        }
        this.narzedzia = narzedzia;
        this.jedzenie = (int) zasoby.podajJedzenie().podajLiczba();
        int [] programy = new int [zasoby.maxiProgramy()];
        for (int i = 0; i < zasoby.podajProgramy().size(); i++) {
            int pozycja = zasoby.podajProgramy().get(i).podajPoziom() - 1;
            programy[pozycja] += zasoby.podajProgramy().get(i).podajLiczba();
        }
        this.programy = programy;
    }
}
