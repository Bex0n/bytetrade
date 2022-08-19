package Osoby.Produkcja;

import java.util.*;

import Przedmioty.ListaProduktow;
import Przedmioty.Przedmiot;

public class Produktywnosc {
    private Vector <Przedmiot> produkty;

    private Produktywnosc(String [] produkty, int [] produktywnosc) {
        ListaProduktow lista = ListaProduktow.stworz();
        for (int i = 0; i < produkty.length; i++)
            for (int j = 0; j < lista.podaj().size(); j++)
                if (produkty[i].equals(lista.podaj().get(j).podajNazwa()))
                    lista.podaj().get(j).ustawLiczbe(produktywnosc[i]);
        this.produkty = lista.podaj();
    }

    public static Produktywnosc stworz(String [] produkty, int [] produktywnosc) {
        return new Produktywnosc(produkty, produktywnosc);
    }

    public static Produktywnosc stworz(Map <String, Object> map) {
        Vector <Przedmiot> lista = ListaProduktow.stworz().podaj();
        String [] produkty = new String[lista.size()];
        int [] produktywnosc = new int[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            produkty[i] = lista.get(i).podajNazwa();
            produktywnosc[i] = ((Double) map.get(lista.get(i).podajNazwa())).intValue();
        }

        return new Produktywnosc(produkty, produktywnosc);
    }

    public int produktywnoscPrzedmiotu (String produkt) {
        for (int i = 0; i < produkty.size(); i++)
            if (produkty.get(i).podajNazwa().equals(produkt))
                return (int) produkty.get(i).podajLiczba();
        return 100;
    }

    public Map <String, Object> toMap() {
        Map <String, Object> map = new HashMap <String, Object>();
        map.put((String) "programy", this.produktywnoscPrzedmiotu("programy"));
        map.put((String) "jedzenie", this.produktywnoscPrzedmiotu("jedzenie"));
        map.put((String) "diamenty", this.produktywnoscPrzedmiotu("diamenty"));
        map.put((String) "ubrania", this.produktywnoscPrzedmiotu("ubrania"));
        map.put((String) "narzedzia", this.produktywnoscPrzedmiotu("narzedzia"));
        return map;
    }
}
