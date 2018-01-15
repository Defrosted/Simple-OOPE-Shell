package oope2017ht.tiedot;
import apulaiset.Komennettava;
import oope2017ht.omalista.OmaLista;
import fi.uta.csjola.oope.lista.*;

/**
 * Oope2017HT
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017
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
     * OmaLista-muotoinen hakemisto-attribuutti, joka varastoi hakemiston sisällön listaan.
     */
    private OmaLista hakemisto;
    /**
     * Hakemisto-muotoinen ylihakemisto-attribuutti, joka säilöö viitettä tämän hakemiston ylähakemistoon
     * hakemistopuussa. Viite on null, jos kyseessä oleva hakemisto on koko järjestelmän juuri.
     */
    private Hakemisto ylihakemisto;

    /*
     * Rakentajat
     */

    public Hakemisto(StringBuilder nimi, Hakemisto yli) throws IllegalArgumentException {
        super(nimi);
        ylihakemisto(yli);
        //Luodaan uusi sisällön tallentava lista aksessorilla.
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
     * vastaavaa metodia ja lisäämällä siihen hakemiston koon.
     * @return String-muotoinen metodin tulos.
     */
    @Override
    public String toString() {
        return super.toString() + "/ " + hakemisto.koko();
    }

    //Rajapinnan metodit

    /**
     * Rajapinnan täyttävä metodi, joka etsii hakemistosta oliota (tiedosto tai hakemisto) nimen perusteella.
     * Käyttää hyvin pitkälti OmaLista-luokan hakumetodia.
     * @param nimi haettavan tiedon nimi.
     * @return viite nimellä löydettyyn olioon, null jos ei löytynyt.
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
     * Rajapinnan toteuttava metodi, joka yrittää lisätä hakemistoja tai tiedostoja hakemistoon käyttäen
     * OmaLista-luokan lisäysmetodia. Tässä lähinnä muunnetaan oliot oikeisiin
     * muotoihin.
     * @param lisattava lisättävän olion viite.
     * @return true, jos lisäys onnistui. False, jos epäonnistui.
     */
    public boolean lisaa(Tieto lisattava) {
        //Lisää hakemistoon uuden tiedoston tai hakemiston
        //hyödyntäen OmaLista-luokkaa. Palautetaan onnistuttaessa
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
     * (käyttäen hae()-metodia) ja poistaa sen käyttäen OmaLista-luokan metodia.
     * @param nimi poistettavan tiedon nimi (String).
     * @return viite poistettuun olioon, null jos poisto epäonnistui.
     */
    public Tieto poista(String nimi) {
        //Poistaa tiedoston tai alihakemiston hyödyntäen
        //OmaLista-luokkaa. Palautetaan viite poistettuun tietoon.
        Tieto poistettava = hae(nimi);
        if(nimi != null && poistettava != null) {
            return (Tieto)hakemisto.poista(poistettava);
        } else
            return null;
    }
}