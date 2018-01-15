package oope2017ht.tiedot;
import apulaiset.Komennettava;
import oope2017ht.omalista.OmaLista;
import fi.uta.csjola.oope.lista.*;

/**
 * Oope2017HT
 *
 * Olio-ohjelmoinnin perusteet, kev�t 2017
 *
 * @author Eetu Rinta-Jaskari
 *
 * Mallinnetaan tietoja hakemistosta.
 */
public class Hakemisto extends Tieto implements Komennettava<Tieto> {
    /*
     * Attribuutit
     */

    /**
     * OmaLista-muotoinen hakemisto-attribuutti, joka varastoi hakemiston sis�ll�n listaan.
     */
    private OmaLista hakemisto;
    /**
     * Hakemisto-muotoinen ylihakemisto-attribuutti, joka s�il�� viitett� t�m�n hakemiston yl�hakemistoon
     * hakemistopuussa. Viite on null, jos kyseess� oleva hakemisto on koko j�rjestelm�n juuri.
     */
    private Hakemisto ylihakemisto;

    /*
     * Rakentajat
     */

    public Hakemisto(StringBuilder nimi, Hakemisto yli) throws IllegalArgumentException {
        super(nimi);
        ylihakemisto(yli);
        //Luodaan uusi sis�ll�n tallentava lista aksessorilla.
        hakemisto(new OmaLista());
    }



    /*
     * Aksessorit
     */

    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }

    public void ylihakemisto(Hakemisto uusi) {
        ylihakemisto = uusi;
    }

    public void hakemisto(OmaLista hakemisto) {
        this.hakemisto = hakemisto;
    }

    //Rajapinnan aksessori
    public LinkitettyLista sisalto() {
        //Palauttaa hakemiston
        LinkitettyLista sisalto = hakemisto;
        return sisalto;
    }

    /*
     * Metodit
     */

    /**
     * Korvaa yliluokan toString-metodin(). Merkkijono muodostetaan kutsumalla yliluokan
     * vastaavaa metodia ja lis��m�ll� siihen hakemiston koon.
     * @return String-muotoinen metodin tulos.
     */
    @Override
    public String toString() {
        return super.toString() + "/ " + hakemisto.koko();
    }

    //Rajapinnan metodit

    /**
     * Rajapinnan t�ytt�v� metodi, joka etsii hakemistosta oliota (tiedosto tai hakemisto) nimen perusteella.
     * K�ytt�� hyvin pitk�lti OmaLista-luokan hakumetodia.
     * @param nimi haettavan tiedon nimi.
     * @return viite nimell� l�ydettyyn olioon, null jos ei l�ytynyt.
     */
    public Tieto hae(String nimi) {
        //Muuntaa nimen sallittuun muotoon, luo apuolion
        //ja kutsuu OmaLista-luokan hakumetodia, jonka tulos palautetaan.
        if(nimi != null) {
            try {
                StringBuilder muunnettuNimi = new StringBuilder(nimi);
                Tiedosto apu = new Tiedosto(muunnettuNimi, 0);
                return (Tieto)hakemisto.hae(apu);
            } catch (IllegalArgumentException e) {
                return null;
            }
        } else
            return null;
    }

    /**
     * Rajapinnan toteuttava metodi, joka yritt�� lis�t� hakemistoja tai tiedostoja hakemistoon k�ytt�en
     * OmaLista-luokan lis�ysmetodia. T�ss� l�hinn� muunnetaan oliot oikeisiin
     * muotoihin.
     * @param lisattava lis�tt�v�n olion viite.
     * @return true, jos lis�ys onnistui. False, jos ep�onnistui.
     */
    public boolean lisaa(Tieto lisattava) {
        //Lis�� hakemistoon uuden tiedoston tai hakemiston
        //hy�dynt�en OmaLista-luokkaa. Palautetaan onnistuttaessa
        //true, muuten false.
        if(lisattava != null && hae(lisattava.nimi().toString()) == null) {
            if(lisattava instanceof Hakemisto) {
                Hakemisto uusi = (Hakemisto)lisattava;
                return hakemisto.lisaa(uusi);
            } else if(lisattava instanceof Tiedosto) {
                Tiedosto uusi = (Tiedosto)lisattava;
                return hakemisto.lisaa(uusi);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Rajapinnan toteuttava metodi, joka hakee poistettavan olion nimen perusteella
     * (k�ytt�en hae()-metodia) ja poistaa sen k�ytt�en OmaLista-luokan metodia.
     * @param nimi poistettavan tiedon nimi (String).
     * @return viite poistettuun olioon, null jos poisto ep�onnistui.
     */
    public Tieto poista(String nimi) {
        //Poistaa tiedoston tai alihakemiston hy�dynt�en
        //OmaLista-luokkaa. Palautetaan viite poistettuun tietoon.
        Tieto poistettava = hae(nimi);
        if(nimi != null && poistettava != null) {
            return (Tieto)hakemisto.poista(poistettava);
        } else
            return null;
    }
}