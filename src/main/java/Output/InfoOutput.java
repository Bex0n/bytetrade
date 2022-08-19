package Output;

import Symulacja.*;

public class InfoOutput {
    private int dzien;
    private CenyOutput ceny_średnie;
    private CenyOutput ceny_max;
    private CenyOutput ceny_min;


    public InfoOutput(Symulacja symulacja) {
        this.dzien = symulacja.podajDlugosc();
        this.ceny_średnie = CenyOutput.cenySrednie(symulacja.podajGielda().podajHistorie());
        this.ceny_max = CenyOutput.cenyMax(symulacja.podajGielda().podajHistorie());
        this.ceny_min = CenyOutput.cenyMin(symulacja.podajGielda().podajHistorie());
    }
}
