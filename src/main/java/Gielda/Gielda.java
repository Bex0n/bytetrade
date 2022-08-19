package Gielda;

import Input.FullInput;
import Input.RobotnikInput;
import Osoby.*;
import Przedmioty.*;
import Symulacja.*;
import Output.*;

import java.util.*;

public class Gielda {

    private transient taktykaGieldy taktykaGieldy;
    private transient Vector <Dzien> historia;
    private transient Vector <Robotnik> robotnicy;
    private transient Vector <Spekulant> spekulanci;

    private transient Vector <OfertaRobotnika> aktualneOferty;

    private transient Vector <OfertaRobotnika> poprzednieOferty;

    public Vector <Dzien> podajHistorie() {
        return historia;
    }

    public Vector <Robotnik> podajRobotnicy() { return robotnicy; }

    public Vector <Spekulant> podajSpekulanci() { return spekulanci; }

    public taktykaGieldy podajTaktykaGieldy() {return taktykaGieldy; }

    public Vector <OfertaRobotnika> podajAktualneOferty() { return aktualneOferty; }

    public Vector <OfertaRobotnika> podajPoprzednieOferty() { return poprzednieOferty; }

    public static double sredniaZOstatnichDni(Vector <Dzien> historia, int liczba, String produkt) {
        double suma = 0;
        int dni = Math.min(liczba, historia.size());
        for (int i = historia.size()-1; i > Math.max(0, historia.size()-1-liczba); i--)
            suma += historia.get(i).srednia(produkt);
        if (suma == 0)
            return historia.get(0).srednia(produkt);
        return suma / liczba;
    }

    public class OfertySpekulantaDrogoComparator implements Comparator<OfertaSpekulanta> {
        @Override
        public int compare(OfertaSpekulanta a, OfertaSpekulanta b) {
            if (a.podajCene() > b.podajCene())
                return -1;
            if (a.podajCene() == b.podajCene()) {
                if (a.podajPrzedmiot().podajPoziom() < b.podajPrzedmiot().podajPoziom())
                    return -1;
                if (a.podajPrzedmiot().podajPoziom() == b.podajPrzedmiot().podajPoziom())
                    return 0;
                return 0;
            }
            return 1;
        }
    }

    public class OfertySpekulantaTanioComparator implements Comparator<OfertaSpekulanta> {
        @Override
        public int compare(OfertaSpekulanta a, OfertaSpekulanta b) {
            if (a.podajCene() < b.podajCene())
                return -1;
            if (a.podajCene() == b.podajCene()) {
                if (a.podajPrzedmiot().podajPoziom() > b.podajPrzedmiot().podajPoziom())
                    return -1;
                if (a.podajPrzedmiot().podajPoziom() == b.podajPrzedmiot().podajPoziom())
                    return 0;
                return 0;
            }
            return 1;
        }
    }

    public static void wykonajOferteKupnaRobotnika(Vector <Wymiana> wymiany, Vector <OfertaSpekulanta> ofertySprzedazy, OfertaRobotnika ofertaRobotnika) {
        for (int i = 0; i < ofertySprzedazy.size(); i++) {
            if (ofertySprzedazy.get(i).podajPrzedmiot().podajNazwa().equals(ofertaRobotnika.podajPrzedmiot().podajNazwa())) {
                double mozliwa_ilosc = Math.min(ofertySprzedazy.get(i).podajPrzedmiot().podajLiczba(), ofertaRobotnika.podajPrzedmiot().podajLiczba());
                double cena = ofertySprzedazy.get(i).podajCene();
                double diamenty_robotnika = ofertaRobotnika.podajOsobe().podajZasoby().podajDiamenty().podajLiczba();
                mozliwa_ilosc = Math.min (mozliwa_ilosc, (int) (diamenty_robotnika / cena));

                double suma = (double) mozliwa_ilosc * cena;
                Przedmiot przedmiot1 = Diamenty.stworz(suma);
                Przedmiot przedmiot2 = Przedmiot.kopiuj(ofertaRobotnika.podajPrzedmiot());

                przedmiot2.ustawLiczbe(mozliwa_ilosc);
                wymiany.add(Wymiana.stworz(przedmiot1, przedmiot2));
                ofertaRobotnika.podajOsobe().podajZasoby().podajDiamenty().dodajLiczbe(-suma);
                ofertaRobotnika.podajOsobe().podajZasoby().dodaj(przedmiot2);
                przedmiot2.ustawLiczbe(-mozliwa_ilosc);
                ofertySprzedazy.get(i).podajOsobe().podajZasoby().podajDiamenty().dodajLiczbe(suma);

                ofertySprzedazy.get(i).podajPrzedmiot().dodajLiczbe(-mozliwa_ilosc);
                ofertaRobotnika.podajPrzedmiot().dodajLiczbe(-mozliwa_ilosc);

                if(ofertySprzedazy.get(i).podajPrzedmiot().podajLiczba() == 0) {
                    ofertySprzedazy.remove(i);
                    i--;
                    continue;
                }
            }
            if(ofertaRobotnika.podajPrzedmiot().podajLiczba() == 0)
                break;
        }
        return;
    }

    public static void wykonajOferteSprzedazyRobotnika(Vector <Wymiana> wymiany, Vector <OfertaSpekulanta> ofertyKupna, OfertaRobotnika ofertaRobotnika) {
        for (int i = 0; i < ofertyKupna.size(); i++) {
            if (ofertyKupna.get(i).podajPrzedmiot().podajNazwa().equals(ofertaRobotnika.podajPrzedmiot().podajNazwa())) {
                double mozliwa_ilosc = Math.min(ofertyKupna.get(i).podajPrzedmiot().podajLiczba(), ofertaRobotnika.podajPrzedmiot().podajLiczba());
                double cena = ofertyKupna.get(i).podajCene();
                double diamenty_spekulanta = ofertyKupna.get(i).podajOsobe().podajZasoby().podajDiamenty().podajLiczba();
                mozliwa_ilosc = Math.min (mozliwa_ilosc, (int) (diamenty_spekulanta / cena));

                double suma = (double) mozliwa_ilosc * cena;
                Przedmiot przedmiot1 = Diamenty.stworz(suma);
                Przedmiot przedmiot2 = Przedmiot.kopiuj(ofertaRobotnika.podajPrzedmiot());
                przedmiot2.ustawLiczbe(mozliwa_ilosc);
                wymiany.add(Wymiana.stworz(przedmiot1, przedmiot2));
                ofertaRobotnika.podajOsobe().podajZasoby().podajDiamenty().dodajLiczbe(suma);
                ofertyKupna.get(i).podajOsobe().podajZasoby().podajDiamenty().dodajLiczbe(-suma);
                ofertyKupna.get(i).podajOsobe().podajZasoby().dodaj(przedmiot2);

                ofertyKupna.get(i).podajPrzedmiot().dodajLiczbe(-mozliwa_ilosc);
                ofertaRobotnika.podajPrzedmiot().dodajLiczbe(-mozliwa_ilosc);

                if(ofertyKupna.get(i).podajPrzedmiot().podajLiczba() == 0) {
                    ofertyKupna.remove(i);
                    i--;
                    continue;
                }
            }
            if(ofertaRobotnika.podajPrzedmiot().podajLiczba() == 0)
                break;
        }
        return;
    }

    protected void sprzedajGieldzie(OfertaRobotnika oferta) {
        double cena = this.podajHistorie().get(this.podajHistorie().size()-1).minimum(oferta.podajPrzedmiot().podajNazwa());
        if (cena == 0)
            cena = this.podajHistorie().get(0).srednia(oferta.podajPrzedmiot().podajNazwa());
        double liczba = oferta.podajPrzedmiot().podajLiczba();
        oferta.podajOsobe().podajZasoby().podajDiamenty().dodajLiczbe(liczba * cena);
    }

    protected Dzien dokonajWymian(Vector <Robotnik> robotnicyProdukujacy,
                                  Vector <OfertaRobotnika> ofertyRobotnikow,
                                  Vector <OfertaSpekulanta> ofertyKupnaSpekulantow,
                                  Vector <OfertaSpekulanta> ofertySprzedazySpekulantow)
    {
        Vector <Wymiana> wymiany = new Vector <Wymiana>();

        this.taktykaGieldy.posortuj(robotnicyProdukujacy);
        for (int i = 0; i < robotnicyProdukujacy.size(); i++) {
            Robotnik obslugiwany = robotnicyProdukujacy.get(i);
            for (int j = 0; j < ofertyRobotnikow.size(); j++) {
                if (ofertyRobotnikow.get(j).podajOsobe().podajID() == obslugiwany.podajID()) {
                    OfertaRobotnika obslugiwanaOferta = ofertyRobotnikow.get(j);
                    this.wykonajOferteSprzedazyRobotnika(wymiany, ofertyKupnaSpekulantow, obslugiwanaOferta);
                }
            }

            obslugiwany.podajTaktykaKupowania().zrobZakupy(wymiany,obslugiwany, ofertySprzedazySpekulantow);

            for (int j = 0; j < ofertyRobotnikow.size(); j++) {
                if (ofertyRobotnikow.get(j).podajOsobe().podajID() == obslugiwany.podajID()) {
                    OfertaRobotnika obslugiwanaOferta = ofertyRobotnikow.get(j);
                    this.sprzedajGieldzie(obslugiwanaOferta);
                }
            }
        }
        return Dzien.stworz(wymiany);
    }

    public void symulujDzien() {
        Vector <Robotnik> robotnicy_produkujacy = new Vector <Robotnik>();
        Vector <OfertaRobotnika> ofertyRobotnikow = new Vector <OfertaRobotnika>();
        for (int i = 0; i < robotnicy.size(); i++)
            robotnicy.get(i).wykonajRuch(ofertyRobotnikow, robotnicy_produkujacy);
        this.poprzednieOferty = aktualneOferty;
        this.aktualneOferty = ofertyRobotnikow;
        Vector <OfertaSpekulanta> ofertyKupnaSpekulantow = new Vector <OfertaSpekulanta>();
        Vector <OfertaSpekulanta> ofertySprzedazySpekulantow = new Vector <OfertaSpekulanta>();
        for (int i = 0; i < spekulanci.size(); i++)
            spekulanci.get(i).wykonajRuch(ofertyKupnaSpekulantow, ofertySprzedazySpekulantow);
        Collections.sort(ofertyKupnaSpekulantow, new OfertySpekulantaDrogoComparator());
        Collections.sort(ofertySprzedazySpekulantow, new OfertySpekulantaTanioComparator());
        this.dokonajWymian(robotnicy_produkujacy, ofertyRobotnikow, ofertyKupnaSpekulantow, ofertySprzedazySpekulantow);
        for (int i = 0; i < robotnicy.size(); i++)
            robotnicy.get(i).jedz();
        for (int i = 0; i < robotnicy.size(); i++)
            if (robotnicy.get(i).gloduje() >= 3) {
                robotnicy.get(i).podajZasoby().podajDiamenty().ustawLiczbe(0);
            }
    }

    private Gielda(FullInput map, Symulacja symulacja) {
        this.taktykaGieldy = taktykaGieldy.stworz(map.info.gielda);

        this.historia = new Vector <Dzien>();
        this.historia.add(Dzien.zerowyStworz(map.info.ceny));

        this.robotnicy = new Vector <Robotnik>();
        for (int i = 0; i < map.robotnicy.size(); i++)
            this.robotnicy.add(Robotnik.stworz(map.robotnicy.get(i), this, symulacja));

        this.spekulanci = new Vector <Spekulant>();
        for (int i = 0; i < map.spekulanci.size(); i++)
            this.spekulanci.add(Spekulant.stworz(map.spekulanci.get(i), this));
    }

    public static Gielda stworz(FullInput map, Symulacja symulacja) { return new Gielda(map, symulacja); }

}
