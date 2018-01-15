package oope2017ht.tiedot;

/**
 * Oope2017HT
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017
 *
 * @author Eetu Rinta-Jaskari
 *
 * Mallinnetaan Tiedoston tietoja.
 */
public abstract class Tieto implements Comparable<Tieto> {

    /*
     * Attribuutit
     */
    /**
     * StringBuilder-muotoinen nimi-attribuutti säilöö hakemistojen ja tiedostojen nimet.
     */
    private StringBuilder nimi;


    /*
     * Rakentajat
     */

    public Tieto(StringBuilder nimi) throws IllegalArgumentException {
        nimi(nimi);
    }

    public Tieto(Tieto kopioitava) {
        nimi(new StringBuilder(kopioitava.nimi()));
    }


    /*
     * Aksessorit
     */

    public void nimi(StringBuilder uusi) throws IllegalArgumentException {
        if(nimiOK(uusi))
            this.nimi = uusi;
        else
            throw new IllegalArgumentException();
    }

    public StringBuilder nimi() {
        return nimi;
    }

    /*
     * Metodit
     */

    /**
     * Kuormittaa toString()-metodin. Muodostaa palautettavan merkkijonon nimi-attribuutista.
     * @return jono, String-muotoinen metodin tulos.
     */
    @Override
    public String toString() {
        String jono = nimi().toString();
        return jono;
    }

    /**
     * Verrataan kahden olion nimi-attribuuttia. Vertailu kertoo, kumpi on aakkosjärjestyksessä ensimmäisenä.
     * Hyödynnetään String-luokan compareTo-metodia.
     * @param o, Tieto-muotoinen olio, jota verrataan.
     * @return Kokonaisluku arvoltaan suurempi kuin 1, jos verrattava olio suurempaa, 0 jos yhtäsuuri
     * ja pienempi kuin 1 jos pienempää.
     */
    @Override
    public int compareTo(Tieto o) {
        String nimi = nimi().toString();
        String oa = o.nimi().toString();

        return nimi.compareTo(oa);
    }

    /**
     * Verrataan kahden Tieto-olion nimi-attribuutin yhtäläisyyttä. Hyödynnetään String-luokan equals-metodia.
     *
     * @param obj -olio, jota testataan.
     * @return boolean, joka kertoo olivatko oliot yhtäsuuria.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tieto) {
            Tieto apu = (Tieto)obj;
            String jono = apu.nimi().toString();
            String nimi = nimi().toString();

            return nimi.equals(jono);
        } else
            return false;
    }

    /**
     * Testaa nimen määriteltyjen kriteerien mukaisesti. Nimi täytyy olla väliltä
     * a-z, A-Z tai 0-9. Lisäksi hyväksytään alaviiva ja piste. Jos nimessä on piste,
     * niin se ei saa olla nimen ainoa merkki. Metodia ei tarvita tämän luokan ulkopuolella,
     * joten sen näkyvyys on private.
     * @param e -StringBuilder, joka on Tieto-olion nimi-attribuutti.
     * @return true, jos nimi läpäisi kriteerit, false jos ei.
     */
    private boolean nimiOK(StringBuilder e) {
        String nimi = e.toString();
        if(nimi.length() > 0){
            boolean kaikkiOK = true;
            //Pilkotaan taulukkoon
            String[] pilkottu = nimi.split("");

            int pisteidenLkm = 0;
            //Tarkistetaan joka merkki
            for(String merkki : pilkottu) {
                //Muunnetaan char-muotoon.
                char testattava = merkki.charAt(0);
                //Testataan merkki kriteerin mukaisesti
                if((testattava >= 'a' && testattava <= 'z')
                        || (testattava >= 'A' && testattava <= 'Z')
                        || (testattava >= '0' && testattava <= '9')
                        || testattava == '_' || testattava == '.') {
                    //Tarkistetaan että nimessä on muitakin merkkejä pisteen lisäksi (jos piste)
                    if(testattava == '.' && pilkottu.length <= 1) {
                        kaikkiOK = false;
                        //Lasketaan pisteiden määrä
                    } else if(testattava == '.')
                        pisteidenLkm++;
                } else
                    kaikkiOK = false;
            }
            //Pisteitä saa olla vain yksi.
            if(pisteidenLkm >= 2)
                kaikkiOK = false;

            return kaikkiOK;
        }
        else
            return true;
    }
}
