package Output;

import java.util.*;
import Osoby.*;

public class RobotnikOutput {
    public int id;
    public int poziom;
    public String kariera;
    public Map <String, Object> kupowanie;
    public Map <String, Object> produkcja;
    public Map <String, Object> uczenie;
    public String zmiana;
    public Map <String, Object> produktywnosc;
    public ZasobyOutput zasoby;

    public RobotnikOutput(Robotnik robotnik) {
        this.id = robotnik.podajID();
        this.poziom = robotnik.podajKariera().aktualnakariera().podajPoziom();
        this.kariera = robotnik.podajKariera().aktualnakariera().podajNazwa();
        this.kupowanie = robotnik.podajTaktykaKupowania().toMap();
        this.produkcja = robotnik.podajTaktykaProdukcji().toMap();
        this.uczenie = robotnik.podajTaktykaPracy().toMap();
        this.zmiana = robotnik.podajTaktykaNauki().podajNazwa();
        this.produktywnosc = robotnik.podajProduktywnosc().toMap();
        this.zasoby = new ZasobyOutput(robotnik.podajZasoby());
    }
}
