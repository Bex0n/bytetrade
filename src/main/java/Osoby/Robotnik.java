package Osoby;

import Gielda.*;
import Osoby.Kupowanie.*;
import Osoby.Praca.*;
import Osoby.Nauka.*;
import Osoby.Kariera.*;
import Osoby.Produkcja.*;
import Przedmioty.*;
import Symulacja.*;
import java.util.*;
import Input.*;

public class Robotnik extends Agent{

    private TaktykaPracy taktykaPracy;
    private TaktykaNauki taktykaNauki;
    private TaktykaProdukcji taktykaProdukcji;
    private TaktykaKupowania taktykaKupowania;
    private Kariera kariera;
    private Produktywnosc produktywnosc;
    private Gielda gielda;
    private Symulacja symulacja;
    private int dniGlodu;

    private int produkcjaOstatniegoDnia = 0;

    private Robotnik(int id,
                     Zasoby zasoby,
                     TaktykaPracy taktykaPracy,
                     TaktykaNauki taktykaNauki,
                     TaktykaProdukcji taktykaProdukcji,
                     TaktykaKupowania taktykaKupowania,
                     Kariera kariera,
                     Produktywnosc produktywnosc,
                     Gielda gielda,
                     Symulacja symulacja,
                     int dniGlodu
    ) {
        super.id = id;
        super.zasoby = zasoby;
        this.taktykaPracy = taktykaPracy;
        this.taktykaNauki = taktykaNauki;
        this.taktykaProdukcji = taktykaProdukcji;
        this.taktykaKupowania = taktykaKupowania;
        this.kariera = kariera;
        this.produktywnosc = produktywnosc;
        this.gielda = gielda;
        this.symulacja = symulacja;
        this.dniGlodu = dniGlodu;
    }

    public static Robotnik stworz(RobotnikInput robotnikInput, Gielda gielda, Symulacja symulacja) {
        return new Robotnik(robotnikInput.id,
                            Zasoby.stworz(robotnikInput.zasoby),
                            TaktykaPracy.stworz(robotnikInput.uczenie),
                            TaktykaNauki.stworz(robotnikInput.zmiana),
                            TaktykaProdukcji.stworz(robotnikInput.produkcja),
                            TaktykaKupowania.stworz(robotnikInput.kupowanie),
                            Kariera.stworz(robotnikInput.kariera, robotnikInput.poziom),
                            Produktywnosc.stworz(robotnikInput.produktywnosc),
                            gielda,
                            symulacja,
                            0
        );
    }

    public Vector<Dzien> podajHistorieGieldy() {
        return gielda.podajHistorie();
    }

    public int karaZaBrakUbran() {
        return symulacja.karaPodaj();
    }

    public Produktywnosc podajProduktywnosc() {
        return produktywnosc;
    }

    public int podajProdukcjeOstatniegoDnia() { return this.produkcjaOstatniegoDnia; }

    public Kariera podajKariera() {
        return kariera;
    }

    public TaktykaProdukcji podajTaktykaProdukcji() {
        return taktykaProdukcji;
    }

    public TaktykaKupowania podajTaktykaKupowania() {return taktykaKupowania; }

    public TaktykaPracy podajTaktykaPracy() { return taktykaPracy; }

    public TaktykaNauki podajTaktykaNauki() { return taktykaNauki; }

    public int gloduje() {
        return dniGlodu;
    }

    public boolean czyPracuje() { return this.taktykaPracy.czyPracuje(this); }

    public void uczSie() { this.taktykaNauki.uczSie(this); }

    public int liczbaUbran() {
        return zasoby.liczbaUbran();
    }

    public int uzyjNarzedzia () {
        return this.podajZasoby().uzyjNarzedzia();
    }

    public int uzyjUbrania() { return this.podajZasoby().uzyjUbrania(); }

    public Vector <Przedmiot> nalozProgramyUbrania(Ubrania wyprodukowane) {
        zasoby.posortujProgramy();
        Vector <Przedmiot> noweubrania = new Vector <Przedmiot>();
        while (wyprodukowane.podajLiczba() > 0) {
            if (zasoby.podajProgramy().size() == 0) {
                if(wyprodukowane.podajLiczba() > 0)
                    noweubrania.add(wyprodukowane);
                break;
            }
            ProgramyKomputerowe program = zasoby.podajProgramy().get(0);
            double minimum = Math.min(program.podajLiczba(), wyprodukowane.podajLiczba());
            noweubrania.add(Ubrania.stworz((int) minimum, program.podajPoziom()));
            wyprodukowane.dodajLiczbe(-minimum);
            program.dodajLiczbe(-minimum);
            if(program.podajLiczba() == 0)
                zasoby.podajProgramy().remove(0);
        }
        return noweubrania;
    }

    public Vector <Przedmiot> nalozProgramyNarzedzia(Narzedzia wyprodukowane) {
        zasoby.posortujProgramy();
        Vector <Przedmiot> nowenarzedzia = new Vector <Przedmiot>();
        while (wyprodukowane.podajLiczba() > 0) {
            if (zasoby.podajProgramy().size() == 0) {
                if(wyprodukowane.podajLiczba() > 0)
                    nowenarzedzia.add(wyprodukowane);
                break;
            }
            ProgramyKomputerowe program = zasoby.podajProgramy().get(0);
            int minimum = Math.min((int) program.podajLiczba(), (int) wyprodukowane.podajLiczba());
            nowenarzedzia.add(Narzedzia.stworz(minimum, program.podajPoziom()));
            wyprodukowane.dodajLiczbe(-minimum);
            program.dodajLiczbe(-minimum);
            if(program.podajLiczba() == 0)
                zasoby.podajProgramy().remove(0);
        }
        return nowenarzedzia;
    }

    public int policzPremie(String produkt, int bazowaProduktywnosc){
        int premia = 0;
        premia += this.podajKariera().podajPremie(produkt);
        if (this.gloduje() == 1)
            premia -= 100;
        if (this.gloduje() == 2)
            premia -= 300;
        premia -= this.uzyjUbrania() * this.symulacja.karaPodaj();
        premia += this.uzyjNarzedzia();

        return premia;
    }

    public String wybierzProdukt() {
        return this.podajTaktykaProdukcji().znajdzProdukt(this);
    }

    public Vector <Przedmiot> produkuj() {
        String produkt = this.wybierzProdukt();

        int bazowaProduktywnosc = podajProduktywnosc().produktywnoscPrzedmiotu(produkt);
        int premia = policzPremie(produkt, bazowaProduktywnosc);
        int produktywnosc = Math.max(0, bazowaProduktywnosc * (100 + premia) / 100);
        this.produkcjaOstatniegoDnia = produktywnosc;
        int poziom = 1;
        if (this.podajKariera().aktualnakariera().podajProdukt().equals(produkt))
            poziom = this.podajKariera().aktualnakariera().podajPoziom();

        switch(produkt) {
            case "jedzenie":
                Vector <Przedmiot> jedzenieTmp = new Vector <Przedmiot>();
                jedzenieTmp.add(Jedzenie.stworz(produktywnosc));
                return jedzenieTmp;
            case "programy":
                Vector <Przedmiot> programyTmp = new Vector <Przedmiot>();
                if (this.podajKariera().aktualnakariera().podajProdukt().equals("programy"))
                    programyTmp.add(ProgramyKomputerowe.stworz(produktywnosc, this.podajKariera().aktualnakariera().podajPoziom()));
                else
                    programyTmp.add(ProgramyKomputerowe.stworz(produktywnosc, 1));
            case "ubrania":
                Ubrania stworzoneUbrania = Ubrania.stworz(produktywnosc, 1);
                return nalozProgramyUbrania(stworzoneUbrania);
            case "narzedzia":
                Narzedzia stworzoneNarzedzia = Narzedzia.stworz(produktywnosc, 1);
                return nalozProgramyNarzedzia(stworzoneNarzedzia);
            case "diamenty":
                this.podajZasoby().podajDiamenty().dodajLiczbe(produktywnosc);
                return new Vector <Przedmiot> ();
        }

        return null;
    }

    public void jedz() {
        if (this.podajZasoby().podajJedzenie().podajLiczba() >= 100)
            this.podajZasoby().podajJedzenie().dodajLiczbe(-100);
        else {
            this.podajZasoby().podajJedzenie().ustawLiczbe(0);
            dniGlodu++;
        }
    }

    public void wykonajRuch (Vector <OfertaRobotnika> oferty, Vector <Robotnik> robotnicy_produkujacy) {
        if (this.dniGlodu >= 3)
            return;
        if (this.czyPracuje()) {
            robotnicy_produkujacy.add(this);
            Vector<Przedmiot> przedmioty = this.produkuj();
            for (int j = 0; j < przedmioty.size(); j++)
                oferty.add(OfertaRobotnika.stworz(przedmioty.get(j), this));
        }
        else {
            this.uczSie();
            dniGlodu = -1;
        }
        return;
    }
}
