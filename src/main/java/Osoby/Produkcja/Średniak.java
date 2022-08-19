package Osoby.Produkcja;

import Gielda.*;
import Osoby.*;
import Przedmioty.*;
import java.util.*;

public class Średniak extends TaktykaProdukcji {
    private int historia_sredniej_produkcji;
    private Średniak(int historia_sredniej_produkcji) {
        this.historia_sredniej_produkcji = historia_sredniej_produkcji;
    }

    public static Średniak stworz(int historia_sredniej_produkcji){
        return new Średniak(historia_sredniej_produkcji);
    }

    public String znajdzProdukt(Robotnik robotnik) {
        Vector <Dzien> historia = robotnik.podajHistorieGieldy();
        Vector <Przedmiot> lista = ListaProduktow.stworz().podaj();
        double maxi = 0;
        String produkt = "";
        for (int i = 0; i < lista.size(); i++) {
            String sprawdzanyprodukt = lista.get(i).podajNazwa();
            if (sprawdzanyprodukt != "diamenty") {
                for (int j = 1; j <= historia_sredniej_produkcji; j++) {
                    double sredniaproduktu = historia.get(Math.max(0, historia.size()-j)).srednia(lista.get(i).podajNazwa());
                    if (sredniaproduktu >= maxi) {
                        maxi = sredniaproduktu;
                        produkt = sprawdzanyprodukt;
                    }
                }
            }
        }
        return produkt;
    }

    public Map <String, Object> toMap() {
        Map <String, Object> map = new HashMap <String, Object>();
        map.put("typ", "sredniak");
        map.put("historia_sredniej_produkcji", historia_sredniej_produkcji);
        return map;
    }
}
