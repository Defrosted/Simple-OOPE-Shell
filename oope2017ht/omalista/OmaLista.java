package oope2017ht.omalista;
import fi.uta.csjola.oope.lista.*;
import apulaiset.*;

/**
 * Oope2017HT
 *
 * Olio-ohjelmoinnin perusteet, kev‰t 2017
 *
 * @author Eetu Rinta-Jaskari.
 *
 * Jatketaan LinkitettyLista-luokkaa tarvittavilla asioilla.
 */
public class OmaLista extends LinkitettyLista implements Ooperoiva {

    /*
     * Metodit
     */

    /**
     * @return Object[] sis‰lt‰en listan alkiot jos muunnos onnistui, muuten null.
     *
     * Metodi siirt‰‰ listassa olevat alkiot taulukkoon ja palauttaa sen.
     * L‰hinn‰ mukavuutta varten, voidaan k‰ytt‰‰ for-each-rakennetta.
     */
    public Object[] alkiotTaulukkoon() {
        //Metodi siirt‰‰ viitteet listasta taulukkoon ja palauttaa sen.
        if(!onkoTyhja()) {
            //Luodaan taulukko
            Object taulukko[] = new Object[koko()];

            //Siirret‰‰n arvot
            for (int i = 0; i < taulukko.length; i++) {
                taulukko[i] = alkio(i);
            }
            return taulukko;
        } else
            //Tyhj‰
            return null;
    }

    /**
     * @param haettava listalta haettava alkio, jonka luokan tai luokan esivanhemman
     * oletetaan korvanneen Object-luokan equals-metodin.
     * @return viite haettuun olioon jos onnistui, ep‰onnistuessa null.
     *
     * Hakee listalta alkion, joka vastaa (equals-mieless‰) parametrin‰ annettuna
     * oliota.
     */
    public Object hae(Object haettava) {
        //Haetaan alkioista haettavaa oliota vastaavaa oliota
        //equals mieless‰. Palautetaan viite, jos lˆytyy.
        Object[] alkiot = alkiotTaulukkoon();
        if(alkiot != null && haettava != null) {
            int laskuri = 0;
            for (Object kohde : alkiot) {
                if (haettava.equals(kohde))
                    return alkio(laskuri);
                laskuri++;
            }
            //Kohdetta ei lˆytynyt, palautetaan null.
            return null;
        } else
            return null;
    }

    /**
     * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
     * toteuttaneen Comparable-rajapinnan.
     * @return true, jos lis‰ys onnistui, false jos ep‰onnistui.
     *
     * Lis‰‰ olion listalle oikeaan j‰rjestykseen compareTo-metodin avulla.
     * Metodia edelt‰‰ SuppressWarnings-lippu, jolla v‰ltet‰‰n k‰‰nt‰j‰n valitus
     * Comparable-rajapinnan generalisoinnista. Virheet metodissa v‰ltet‰‰n tarkistamalla,
     * ett‰ olio on toteuttanut kyseisen rajapinnan.
     */
    @SuppressWarnings("unchecked")
    public boolean lisaa(Object uusi) {
        //Lis‰‰ uuden alkion kohtaan listassa, jossa edelt‰v‰ arvo
        //on uutta suurempi tai yht‰suuri ja seuraava on pienempi.
        Object[] alkiot = alkiotTaulukkoon();
        //Jos parametri tyhj‰
        if(uusi != null && uusi instanceof Comparable) {
            Comparable lisattava = (Comparable)uusi;
            //Jos lista on tyhj‰, lis‰t‰‰n uusi alkuun.
            if(alkiot != null) {
                //Etsit‰‰n oikea kohta.
                for(int i = 0; i < alkiot.length; i++) {
                    //Onko oikea paikka
                    if (lisattava.compareTo(alkiot[i]) < 0) {
                        //Paikka lˆytyi, lis‰t‰‰n suuremman per‰‰n
                        return lisaa(i, uusi);
                    } else if(i == alkiot.length-1 && lisattava.compareTo(alkiot[i]) >= 0) {
                        lisaaLoppuun(uusi);
                        return true;
                    }
                }
                //Ei pit‰isi p‰‰st‰ t‰nne asti ikin‰
                return false;
            } else {
                lisaaAlkuun(uusi);
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Metodi hakee listasta halutun olion ja poistaa sen.
     * @param poistettava viite poistettavaan tietoalkioon, jonka luokan
     * tai luokan esivanhemman oletetaan korvanneen Object-luokan equals-metodin.
     * @return viite poistettuun olioon, tai null jos poisto ep‰onnistui.
     */
    public Object poista(Object poistettava) {
        //Haetaan alkioista haettavaa oliota vastaavaa oliota
        //equals mieless‰. Poistetaan, jos lˆytyi ja palautetaan viite.
        Object[] alkiot = alkiotTaulukkoon();
        if(alkiot != null && poistettava != null) {
            int laskuri = 0;
            for (Object kohde : alkiot) {
                if (poistettava.equals(kohde))
                    return poista(laskuri);
                laskuri++;
            }
            //Kohdetta ei lˆytynyt, palautetaan null.
            return null;
        } else
            return null;
    }
}
