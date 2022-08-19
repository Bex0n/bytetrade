package Osoby.Produkcja;

import Gielda.*;
import Osoby.*;
import Przedmioty.*;
import java.util.*;

public class Losowy extends TaktykaProdukcji {
    private Random random;
    private Losowy() {
        this.random = new Random();
    }

    public static Losowy stworz(){
        return new Losowy();
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
                int n = random.nextInt(1000);
                if (n >= maxi) {
                    maxi = n;
                    produkt = sprawdzanyprodukt;
                }
            }
        }
        return produkt;
    }

    public Map <String, Object> toMap() {
        Map <String, Object> map = new HashMap <String, Object>();
        map.put("typ", "losowy");
        return map;
    }
}
