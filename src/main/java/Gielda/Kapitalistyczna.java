package Gielda;

import Osoby.*;

import java.util.*;

public class Kapitalistyczna extends taktykaGieldy implements Comparator <Robotnik> {
    @Override
    public int compare(Robotnik a, Robotnik b) {
        if(a.podajZasoby().podajDiamenty().podajLiczba() > b.podajZasoby().podajDiamenty().podajLiczba())
            return -1;
        if(a.podajZasoby().podajDiamenty().podajLiczba() == b.podajZasoby().podajDiamenty().podajLiczba())
            return a.podajID() < b.podajID() ? -1 : a.podajID()  == b.podajID() ? 0 : 1;
        return 1;
    }

    public Kapitalistyczna(String nazwa){
        super.nazwa = nazwa;
    }
}
