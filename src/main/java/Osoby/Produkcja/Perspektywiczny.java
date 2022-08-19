package Osoby.Produkcja;

import Gielda.*;
import Osoby.*;
import Przedmioty.*;
import java.util.*;

public class Perspektywiczny extends TaktykaProdukcji {
    private int historia_perspektywy;
    private Perspektywiczny(int historia_perspektywy) {
        this.historia_perspektywy = historia_perspektywy;
    }

    public static Perspektywiczny stworz(int historia_perspektywy){
        return new Perspektywiczny(historia_perspektywy);
    }

    public String znajdzProdukt(Robotnik robotnik) {
        Vector <Dzien> historia = robotnik.podajHistorieGieldy();
        Vector <Przedmiot> lista = ListaProduktow.stworz().podaj();
        double maxi = Double.MIN_VALUE;
        String produkt = "programy";
        for (int i = 0; i < lista.size(); i++) {
            String sprawdzanyprodukt = lista.get(i).podajNazwa();
            if (sprawdzanyprodukt != "diamenty") {
                double srednia_z_ostatniego_dnia = historia.get(historia.size()-1).srednia(lista.get(i).podajNazwa());
                double srednia_z_perspektywydni_temu = historia.get(Math.max(0, historia.size()-historia_perspektywy)).srednia(lista.get(i).podajNazwa());
                if (srednia_z_ostatniego_dnia - srednia_z_perspektywydni_temu >= maxi) {
                    maxi = srednia_z_ostatniego_dnia - srednia_z_perspektywydni_temu;
                    produkt = sprawdzanyprodukt;
                }
            }
        }
        return produkt;
    }

    public Map <String, Object> toMap() {
        Map <String, Object> map = new HashMap <String, Object>();
        map.put("typ", "perspektywiczny");
        map.put("historia_perspektywy", historia_perspektywy);
        return map;
    }
}
