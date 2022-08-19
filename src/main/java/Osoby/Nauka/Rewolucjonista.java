package Osoby.Nauka;

import Osoby.*;
import Przedmioty.*;
import Gielda.*;
import java.util.*;

public class Rewolucjonista extends TaktykaNauki {

    private Rewolucjonista() {
        super.nazwa = "rewolucjonista";
    }

    public static Rewolucjonista stworz() {
        return new Rewolucjonista();
    }

    public String wybierzProdukt (Vector <Dzien> historiaGieldy, int n) {
        Vector <Przedmiot> lista = ListaProduktow.stworz().podaj();
        int najlepszy_produkt_suma = 0;
        Przedmiot najlepszy_produkt = Jedzenie.stworz(100);
        int ostatni_dzien = historiaGieldy.size()-1;

        for (int i = 0; i < lista.size(); i++) {
            int suma_produktu = 0;
            for (int j = ostatni_dzien; j > Math.max(0, ostatni_dzien - n); j--)
                suma_produktu += historiaGieldy.get(j).policz(lista.get(i).podajNazwa());
            if (suma_produktu >= najlepszy_produkt_suma) {
                najlepszy_produkt_suma = suma_produktu;
                najlepszy_produkt = lista.get(i);
            }
        }
        return najlepszy_produkt.podajNazwa();
    }

    public void uczSie(Robotnik robotnik) {
        int dzien = robotnik.podajHistorieGieldy().size();
        if (dzien % 7 != 0) {
            robotnik.podajKariera().aktualnakariera().podniesPoziom();
            return;
        }
        int n = robotnik.podajID() % 17;
        String produkt = this.wybierzProdukt(robotnik.podajHistorieGieldy(), n);
        robotnik.podajKariera().zmienNa(produkt);
        return;
    }

}
