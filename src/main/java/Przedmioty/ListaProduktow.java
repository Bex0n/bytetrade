package Przedmioty;

import java.util.*;

public class ListaProduktow {
    private Vector <Przedmiot> listaproduktow;

    private ListaProduktow() {
        this.listaproduktow = new Vector <Przedmiot>();
        this.listaproduktow.add(Diamenty.stworz(100));
        this.listaproduktow.add(Jedzenie.stworz(100));
        this.listaproduktow.add(Narzedzia.stworz(100));
        this.listaproduktow.add(Ubrania.stworz(100));
        this.listaproduktow.add(ProgramyKomputerowe.stworz(100));
    }

    public static ListaProduktow stworz() {
        return new ListaProduktow();
    }

    public Vector <Przedmiot> podaj() {
        return listaproduktow;
    }
}
