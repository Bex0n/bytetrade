package Osoby.Produkcja;

import Osoby.*;
import java.util.*;

public abstract class TaktykaProdukcji {
    public static TaktykaProdukcji stworz(Map<String, Object> map) {
        switch((String) map.get("typ")) {
            case "krotkowzroczny":
                return Krótkowzroczny.stworz();
            case "chciwy":
                return Chciwy.stworz();
            case "sredniak":
                return Średniak.stworz((int) map.get("historia_sredniej_produkcji"));
            case "perspektywiczny":
                return Perspektywiczny.stworz((int) map.get("historia_perspektywy"));
            case "losowy":
                return Losowy.stworz();
        }
        return null;
    }
    public abstract String znajdzProdukt(Robotnik robotnik);

    public abstract Map <String, Object> toMap();
}
