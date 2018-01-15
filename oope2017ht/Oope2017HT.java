package oope2017ht;
import oope2017ht.tiedot.*;
import oope2017ht.jarjestelma.*;

/**
 * Oope2017HT
 * <p>
 * Olio-ohjelmoinnin perusteet, kev�t 2017
 * <p>
 * @author Eetu Rinta-Jaskari.
 * <p>
 * Ohjelman k�ynnist�v� ajoluokka.
 */
public class Oope2017HT {
    /**
     * Luo ohjelman juurihakemiston ja k�ynnist�� varsinaisen ohjelman
     * luomalla Kayttoliittyma-olion.
     * @param args ohjelman main-metodin standardi parametri.
     */
    public static void main(String[] args) {
        Hakemisto juuri = new Hakemisto(new StringBuilder(""), null);
        Kayttoliittyma liittyma = new Kayttoliittyma(juuri);
        liittyma.suoritaOhjelma();
    }
}
