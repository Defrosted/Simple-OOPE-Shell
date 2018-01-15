package oope2017ht.tiedot;

/**
 * Oope2017HT
 *
 * Olio-ohjelmoinnin perusteet, kev�t 2017
 *
 * @author Eetu Rinta-Jaskari
 *
 * Mallinnetaan Tiedostojen tarvittavat asiat.
 */
public class Tiedosto extends Tieto {
    /*
     * Attribuutit
     */
    
    private int tiedostokoko;


    /*
     * Rakentajat
     */

    public Tiedosto(StringBuilder nimi, int koko) throws IllegalArgumentException {
        super(nimi);
        tiedostokoko(koko);
    }

    public Tiedosto(Tiedosto kopioitava) {
        super(kopioitava);
        tiedostokoko(kopioitava.tiedostokoko());
    }


    /*
     * Aksessorit
     */

    /**
     * Tarkistaa tiedostokoon oikeellisuuden (ett� se on suurempi tai yht�suuri kuin 0), jonka j�lkeen asettaa arvon
     * attribuuttiin.
     *
     * @param koko asetettava tiedostokoko.
     * @throws IllegalArgumentException heitet��n virhe, jos tiedostokoko virheellinen.
     */
    public void tiedostokoko(int koko) throws IllegalArgumentException {
        if(koko >= 0) {
            tiedostokoko = koko;
        } else
            throw new IllegalArgumentException();
    }

    public int tiedostokoko() {
        return tiedostokoko;
    }

    /*
     * Metodit
     */

    /**
     * Kuormittaa yliluokan toString()-metoodin. Merkkijono muodostetaan kutsumalla
     * yliluokan vastaavaa metodia ja lis��m�ll� siihen tiedostokoko.
     * @return toString -tulos, muotoa String.
     */
    @Override
    public String toString() {
        return super.toString() + " " + tiedostokoko();
    }
}
