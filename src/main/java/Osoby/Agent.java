package Osoby;

import Przedmioty.*;

public abstract class Agent {

    protected int id;

    protected Zasoby zasoby;

    public Zasoby podajZasoby() {
        return zasoby;
    }

    public int podajID(){
        return id;
    }

}
