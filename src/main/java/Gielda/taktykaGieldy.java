package Gielda;

import Osoby.*;
import Przedmioty.*;

import java.util.*;

public abstract class taktykaGieldy implements Comparator <Robotnik>{

    protected String nazwa;

    public abstract int compare(Robotnik a, Robotnik b);

    public static taktykaGieldy stworz(String typ) {
        switch(typ) {
            case "socjalistyczna":
                return new Socjalistyczna(typ);
            case "kapitalistyczna":
                return new Kapitalistyczna(typ);
            case "zrownowazona":
                return new Zrownowazona(typ);
        }
        return null;
    }

    public void posortuj(Vector <Robotnik> robotnicyProdukujacy) {
        Collections.sort(robotnicyProdukujacy, this);
        if (nazwa == "zrownowazona") {
            Vector <Robotnik> robotnicy = new Vector <Robotnik>();
            int l = 0;
            int p = robotnicyProdukujacy.size();
            while (l < p) {
                robotnicy.add(robotnicyProdukujacy.get(l));
                l++;
                if (l == p)
                    break;
                p--;
                robotnicy.add(robotnicyProdukujacy.get(p));
            }
            robotnicyProdukujacy = robotnicy;
        }
        return;
    }

    public String podajNazwa() { return nazwa; }
}
