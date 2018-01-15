/**
 * Oope2017HT
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017
 *
 * Eetu Rinta-Jaskari (rinta-jaskari.eetu.m@student.uta.fi).
 *
 * Jatketaan LinkitettyLista-luokkaa tarvittavilla asioilla.
 */
package oope2017ht.lista;
import fi.uta.csjola.oope.lista.*;
import apulaiset.*;

public class OmaLista extends LinkitettyLista implements Ooperoiva {

    /*
     * Metodit
     */

    public Object[] alkiotTaulukkoon() {
        //Metodi siirtää viitteet listasta taulukkoon ja palauttaa sen.
        //Tarkistetaan tyhjyys
        if(!onkoTyhja()) {
            //Luodaan taulukko
            Object taulukko[] = new Object[koko()];

            //Siirretään arvot
            for (int i = 0; i < taulukko.length; i++) {
                taulukko[i] = alkio(i);
            }
            return taulukko;
        } else
            //Tyhjä
            return null;
    }

    public Object hae(Object haettava) {
        //Haetaan alkioista haettavaa oliota vastaavaa oliota
        //equals mielessä. Palautetaan viite, jos löytyy.
        Object[] alkiot = alkiotTaulukkoon();
        if(alkiot != null && haettava != null) {
            int laskuri = 0;
            for (Object kohde : alkiot) {
                if (haettava.equals(kohde))
                    return alkio(laskuri);
                laskuri++;
            }
            //Kohdetta ei löytynyt, palautetaan null.
            return null;
        } else
            return null;
    }

    public boolean lisaa(Object uusi) {
        //Lisää uuden alkion kohtaan listassa, jossa edeltävä arvo
        //on uutta suurempi tai yhtäsuuri ja seuraava on pienempi.
        Object[] alkiot = alkiotTaulukkoon();
        //Jos parametri tyhjä
        if(uusi != null && uusi instanceof Comparable) {
            Comparable lisattava = (Comparable)uusi;
            //Jos lista on tyhjä, lisätään uusi alkuun.
            if(alkiot != null) {
                //Etsitään oikea kohta.
                for(int i = 0; i < alkiot.length; i++) {
                    //Jos ei alussa taikka lopussa
                    if(i > 0 && i < alkiot.length-1) {
                        //Onko oikea paikka
                        if(lisattava.compareTo(alkiot[i]) < 0 && lisattava.compareTo(alkiot[i-1]) >= 0) {
                            //Paikka löytyi, lisätään suuremman perään
                            return lisaa(i, uusi);
                        }
                    }
                    //Listan alussa, tarkistetaan onko alussa oleva pienempää
                    else if(i == 0 && lisattava.compareTo(alkiot[i]) < 0) {
                        lisaaAlkuun(uusi);
                        return true;
                    }
                    //Listan lopussa
                    else if(i == alkiot.length-1) {
                        lisaaLoppuun(uusi);
                        return true;
                    }
                }
                //Ei pääse tänne asti ikinä
                return false;
            } else {
                lisaaAlkuun(uusi);
                return true;
            }
        } else {
            return false;
        }
    }

    public Object poista(Object poistettava) {
        //Haetaan alkioista haettavaa oliota vastaavaa oliota
        //equals mielessä. Poistetaan, jos löytyi ja palautetaan viite.
        Object[] alkiot = alkiotTaulukkoon();
        if(alkiot != null && poistettava != null) {
            int laskuri = 0;
            for (Object kohde : alkiot) {
                if (poistettava.equals(kohde))
                    return poista(laskuri);
                laskuri++;
            }
            //Kohdetta ei löytynyt, palautetaan null.
            return null;
        } else
            return null;
    }
}
