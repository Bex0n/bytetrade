package Symulacja;

import java.util.*;
import Gielda.*;
import Input.FullInput;

public class Symulacja {
    private Gielda gielda;
    private int karaZaBrakUbran;

    private int dlugosc;

    public Symulacja (FullInput map) {
        this.karaZaBrakUbran = (Integer) map.info.kara_za_brak_ubrań;
        this.dlugosc = map.info.dlugosc;
        Gielda gielda = Gielda.stworz(map, this);
        for (int i = 0; i < this.dlugosc; i++)
            gielda.symulujDzien();
        this.gielda = gielda;
    }

    public static Symulacja stworz(FullInput map) {
        return new Symulacja(map);
    }

    public Gielda podajGielda() {
        return gielda;
    }

    public int karaPodaj() {
        return karaZaBrakUbran;
    }

    public int podajDlugosc() { return dlugosc; }
}
