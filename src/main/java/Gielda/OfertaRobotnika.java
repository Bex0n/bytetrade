package Gielda;

import Osoby.*;
import Przedmioty.*;

public class OfertaRobotnika {
    private Przedmiot przedmiot;
    private Robotnik robotnik;

    private OfertaRobotnika(Przedmiot przedmiot, Robotnik robotnik) {
        this.przedmiot = przedmiot;
        this.robotnik = robotnik;
    }

    public static OfertaRobotnika stworz(Przedmiot przedmiot, Robotnik robotnik) {
        return new OfertaRobotnika(przedmiot, robotnik);
    }

    public Przedmiot podajPrzedmiot() {
        return przedmiot;
    }

    public Robotnik podajOsobe() {
        return robotnik;
    }
}
