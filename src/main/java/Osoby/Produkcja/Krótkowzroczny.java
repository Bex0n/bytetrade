package Osoby.Produkcja;

import Gielda.*;
import Osoby.*;
import Przedmioty.*;
import java.util.*;

public class Krótkowzroczny extends TaktykaProdukcji {
    private Krótkowzroczny() {}

    public static Krótkowzroczny stworz(){
        return new Krótkowzroczny();
    }

    public String znajdzProdukt(Robotnik robotnik) {
        Vector <Dzien> historia = robotnik.podajHistorieGieldy();
        Dzien ostatnidzien = historia.get(historia.size()-1);
        Vector <Przedmiot> lista = ListaProduktow.stworz().podaj();
        double maxi = 0;
        String produkt = "";
        for (int i = 0; i < lista.size(); i++) {
            String sprawdzanyprodukt = lista.get(i).podajNazwa();
            if (sprawdzanyprodukt != "diamenty") {
                double sredniaproduktu = ostatnidzien.srednia(lista.get(i).podajNazwa());
                if (sredniaproduktu >= maxi) {
                    maxi = sredniaproduktu;
                    produkt = sprawdzanyprodukt;
                }
            }
        }
        return produkt;
    }

    public Map <String, Object> toMap() {
        Map <String, Object> map = new HashMap <String, Object>();
        map.put("typ", "krotkowzroczny");
        return map;
    }
}
