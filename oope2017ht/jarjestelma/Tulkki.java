package oope2017ht.jarjestelma;
import oope2017ht.omalista.OmaLista;
import oope2017ht.tiedot.*;

/**
 * Oope2017HT
 * <p>
 * Olio-ohjelmoinnin perusteet, kevät 2017
 * <p>
 * @author Eetu Rinta-Jaskari (rinta-jaskari.eetu.m@student.uta.fi).
 * <p>
 * Ohjelman tulkki, joka hoitaa käyttöliittymän ja komentojen toiminnallisuuden.
 * Toimii käyttöliittymän ja järjestelmän välikätenä.
 */
public class Tulkki {
    /*
     * Attribuutit
     */

    /**
     * Hakemisto-muotoinen nykyinen-attribuutti, joka pitää kirjaa siitä,
     * mitä hakemistoa ollaan selaamassa komentorivillä.
     */
    Hakemisto nykyinen;

    /*
     * Rakentajat
     */

    public Tulkki(Object juuri) {
        nykyinen = (Hakemisto)juuri;
    }

    /*
     * Metodit
     */

    /**
     * Metodi muodostaa selattavan hakemiston polun eli sijainnin hakemistopuussa.
     * Tätä käytetään käyttöliittymässä tulostamalla jono juuri ennen käyttäjän syötettä.
     * @param lisataanNykyinen on lippumuuttuja, joka määrittää lisätäänkö myös nykyinen sijainti polkuun.
     * Tämän avulla metodia voidaan käyttää myös rekursiivisessa listauksessa.
     * @return hakemiston String-muotoinen polku, joka tulostetaan.
     */
    public String sijainti(boolean lisataanNykyinen) {
        Hakemisto ylihakemisto = nykyinen.ylihakemisto();
        Hakemisto paikka = ylihakemisto;
        //Jono johon muodostetaan polku, alustetaan nykyisen sijainnin polulla.
        String polku = "";

        if(lisataanNykyinen)
            polku += nykyinen.nimi().toString();

        //Siirrytään poluissa ylöspäin ja kerätään tiedot.
        while(ylihakemisto != null) {
            paikka = ylihakemisto;
            ylihakemisto = paikka.ylihakemisto();

            String nimi = paikka.nimi().toString();
            polku = nimi + "/" + polku;
        }

        return polku;
    }

    /**
     * Muodostaa listauksen hakemiston sisällöstä, jos parametrinä ei ole annettu nimeä.
     * Jos parametri on annettu (ei ole null), niin haetaan hakemistosta tietty tiedosto ja
     * tulostetaan vain sen tiedot toString()-metodia hyödyntäen.
     * @param nimi jos halutaan listata tietty hakemisto tai tiedosto.
     * @return String-muotoinen listaus hakemiston sisällöstä.
     */
    public String listaaHakemisto(String nimi) {
        //Listaa hakemiston sisällön, palauttaa valmin
        //sisältölistauksen (String).
        OmaLista hakemisto = (OmaLista) nykyinen.sisalto();
        String lista = "";

        //Listataan koko sisältö
        if (!hakemisto.onkoTyhja() && nimi == null) {
            Object[] sisalto = hakemisto.alkiotTaulukkoon();
            for (Object kohde : sisalto) {
                //Muunnetaan hakemistot hakemistoiksi ja tiedostot tiedostoiksi.
                //Ja lisätään ne listaan.
                if (kohde instanceof Hakemisto) {
                    Hakemisto kohdeHak = (Hakemisto) kohde;
                    lista += kohdeHak.toString() + "\n";
                } else if (kohde instanceof Tiedosto) {
                    Tiedosto kohdeTie = (Tiedosto) kohde;
                    lista += kohdeTie.toString() + "\n";
                }
            }
            return lista;
        //Haetaan parametrinä annettu hakemisto/nimi.
        } else if(!hakemisto.onkoTyhja() && nimi != null) {
            Tieto kohde = nykyinen.hae(nimi);
            //Tarkistetaan löytyikö vastaavaa kohdetta.
            if(kohde != null)
                lista += kohde.toString() + "\n";
            else
                lista = "Error!\n";

            return lista;
        } else if(hakemisto.onkoTyhja() && nimi == null)
            return "";
        else
            return "Error!\n";
    }

    /**
     * Metodi vaihtaa komentorivin selattavaa hakemistoa komennon mukaisesti.
     * Parametrinä voidaan antaa joko hakemiston nimi, ".." tai null.
     * Kun annetaan nimi, niin haetaan sen nimistä hakemistoa ja sen löytyessä vaihdetaan selattavaa hakemistoa.
     * Kun parametri "nimi" on ".." siirrytään yksi hakemisto ylöspäin hakemistopuussa.
     * Parametrillä null siirrytään hakemistopuun juureen.
     * @param nimi määrittää, minne siirrytään.
     * @return true, jos vaihto onnistui, false jos epäonnistui.
     */
    public boolean vaihdaHakemistoa(String nimi) {
        //Haetaan tiettyä hakemistoa ja yritetään siirtyä sinne.
        if(nimi != null && !nimi.equals("..")) {
            Tieto haettu = nykyinen.hae(nimi);
            if (haettu instanceof Hakemisto) {
                nykyinen = (Hakemisto)haettu;
                return true;
            } else
                return false;
        //Siirrytään yksi hakemisto ylöspäin
        } else if(nimi != null && nimi.equals("..")) {
            if(nykyinen.ylihakemisto() != null) {
                nykyinen = nykyinen.ylihakemisto();
                return true;
            } else
                return false;
        //Siirrytään juureen
        } else {
            while(nykyinen.ylihakemisto() != null) {
                nykyinen = nykyinen.ylihakemisto();
            }
            return true;
        }
    }

    /**
     * Yrittää luoda uuden alihakemiston nykyiseen selattavaan hakemistoon.
     * @param nimi lisättävän hakemiston nimi.
     * @return true jos luominen onnistui, false jos epäonnistui.
     */
    public boolean luoHakemisto(String nimi) {
        //Luo hakemiston järjestelmään
        if(nimi != null) {
            try {
                Hakemisto uusi = new Hakemisto(new StringBuilder(nimi), nykyinen);
                return nykyinen.lisaa(uusi);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Yrittää luoda uuden tiedoston nykyiseen selattavaan hakemistoon.
     * @param nimi lisättävän tiedoston nimi.
     * @param luku lisättävän tiedoston tiedostokoko.
     * @return true jos luominen onnistui, false jos epäonnistui.
     */
    public boolean luoTiedosto(String nimi, String luku) {
        //Luo tiedoston järjestelmään
        if(nimi != null) {
            try {
                //Muunnetaan koko kokonaisluvuksi
                int koko = Integer.parseInt(luku);
                Tiedosto uusi = new Tiedosto(new StringBuilder(nimi), koko);
                return nykyinen.lisaa(uusi);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else
            return false;
    }

    /**
     * Metodi yrittää vaihtaa selattavassa hakemistossa sijaitsevan tiedoston nimeä.
     * @param vaihdettava tiedostonimi tiedostolle, jonka nimeä halutaan vaihtaa.
     * @param uusi tiedostolle annettava uusi nimi.
     * @return true jos onnistui, false jos epäonnistui.
     */
    public boolean vaihdaNimi(String vaihdettava, String uusi) {
        if(vaihdettava != null && uusi != null && nykyinen.hae(uusi) == null) {
            Tieto kohde = nykyinen.hae(vaihdettava);
            if(kohde instanceof Tiedosto) {
                if (kopioiTiedosto(vaihdettava, uusi) && poista(vaihdettava))
                    return true;
                else
                    return false;
            } else if(kohde instanceof Hakemisto) {
                Hakemisto kopio = (Hakemisto)kohde;
                boolean poisto = poista(vaihdettava);
                kopio.nimi(new StringBuilder(uusi));

                if(poisto && nykyinen.lisaa(kopio))
                    return true;
                else
                    return false;
            } else
                return false;
        } else
            return false;
    }

    /**
     * Metodi yrittää kopioida toista tiedostoa, jose kopioitava tiedosto löytyi.
     * Kopioinnissä käytetään kopiorakentajaa.
     * @param kopioitava tiedostonimi tiedostolle, joka halutaan kopioida.
     * @param uusi kopion tuleva nimi.
     * @return true jos onnistui, false jos epäonnistui.
     */
    public boolean kopioiTiedosto(String kopioitava, String uusi) {
        //Tarkistetaan, että parametrit ok, eikä yritetä luoda samannimistä kopiota.
        if(kopioitava != null && uusi != null
                && !kopioitava.equals(uusi)) {
            //Haetaan lähde
            Tieto kohde = nykyinen.hae(kopioitava);
            if(kohde != null && kohde instanceof Tiedosto) {
                try {
                    //Muunnetaan tiedostoksi
                    Tiedosto kohdeTiedosto = (Tiedosto) kohde;
                    //Luodaan kopio kopiorakentajilla
                    Tiedosto kopio = new Tiedosto(kohdeTiedosto);
                    //Muutetaan kopioidun nimi
                    kopio.nimi(new StringBuilder(uusi));
                    //Lisätään kopio
                    return nykyinen.lisaa(kopio);
                } catch (IllegalArgumentException e) {
                    return false;
                }
            } else
                return false;
        } else
            return false;
    }

    /**
     * Metodi yrittää poistaa parametrinä annettua nimeä vastaavaa kohdetta.
     * @param nimi poistettavan tiedoston nimi.
     * @return true jos onnistui, false jos epäonnistui.
     */
    public boolean poista(String nimi) {
        //Poiston "välikäsi", käsittelee poistoprosessin tuloksen
        //käyttöliittymälle.
        if(nimi != null) {
            Tieto poisto = nykyinen.poista(nimi);
            if(poisto != null)
                return true;
            else
                return false;
        } else
            return false;
    }

    /**
     * Muodostaa rekursiivisesti listauksen tiedostoista ja hakemistoista alkaen siitä hakemistosta, josta
     * metodia kutsuttiin.
     * @param hakemisto jota "selataan" metodissa sillä suorituskerralla.
     * @param polku (merkkijono), joka lisätään täydentäen ennen toString()-kutsua merkkijonomuotoiseen listaukseen.
     * @return String-muotoinen listaus kaikista selattavan hakemiston ja sen kaikkien alihakemistojen
     * hakemistoista ja tiedostoista.
     */
    public String hakemistonRekListaus(Hakemisto hakemisto, String polku) {
        //Metodia kutsuttaessa käyttöliittymästä annettavat arvot ovat
        //null, "/" ja true.

        //Aloitetaan listaus rivinvaihdolla, sillä tulostus
        //hoidetaan pelkällä print-metodilla, koska hakemisto
        //voi olla tyhjä
        String listaus = "";

        //Hakemiston valinta, jos parametri null, niin käytetään
        //nykyistä, muuten parametrin hakemisto. Rekursiivisuutta
        //varten.
        Hakemisto listattava = nykyinen;
        if(hakemisto != null) {
            listattava = hakemisto;
        }

        //Muodostetaan polkua, joka lisätään kohteiden eteen
        //Jätetään juuri pois polusta.
        if(listattava.ylihakemisto() != null) {
            polku = polku + listattava.nimi().toString() + "/";
        }

        //Tarkistetaan ettei hakemisto ole tyhjä
        if(!listattava.sisalto().onkoTyhja()) {
            //Aloitetaan listaus
            for(int i = 0; i < listattava.sisalto().koko(); i++) {
                Object kohde = listattava.sisalto().alkio(i);

                //Lisätään polku alkuun, lisätään myös / alkuun, jos ei ole
                if(polku.length() > 0 && polku.charAt(0) == '/')
                    listaus += polku;
                else
                    listaus += "/" + polku;

                //Tarkistetaan onko Tiedosto vai Hakemisto
                if(kohde instanceof Hakemisto) {
                    //Muunnetaan hakemistoksi
                    Hakemisto kohdeHak = (Hakemisto)kohde;
                    //Lisätään listaukseen
                    listaus += kohdeHak.toString() + "\n";

                    //Koska hakemisto, käydään se läpi rekursiivisesti
                    listaus += hakemistonRekListaus(kohdeHak, polku);
                } else if (kohde instanceof Tiedosto) {
                    //Muunnetaan tiedostoksi
                    Tiedosto kohdeTie = (Tiedosto)kohde;
                    //Lisätään listaukseen
                    listaus += kohdeTie.toString() + "\n";
                }
            }

            return listaus;
        } else
            return "";
    }
}
