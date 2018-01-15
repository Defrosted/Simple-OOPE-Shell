package oope2017ht.tiedot;

/**
 * Oope2017HT
 *
 * Olio-ohjelmoinnin perusteet, kev�t 2017
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
     * StringBuilder-muotoinen nimi-attribuutti s�il�� hakemistojen ja tiedostojen nimet.
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
     * Verrataan kahden olion nimi-attribuuttia. Vertailu kertoo, kumpi on aakkosj�rjestyksess� ensimm�isen�.
     * Hy�dynnet��n String-luokan compareTo-metodia.
     * @param o, Tieto-muotoinen olio, jota verrataan.
     * @return Kokonaisluku arvoltaan suurempi kuin 1, jos verrattava olio suurempaa, 0 jos yht�suuri
     * ja pienempi kuin 1 jos pienemp��.
     */
    @Override
    public int compareTo(Tieto o) {
        String nimi = nimi().toString();
        String oa = o.nimi().toString();

        return nimi.compareTo(oa);
    }

    /**
     * Verrataan kahden Tieto-olion nimi-attribuutin yht�l�isyytt�. Hy�dynnet��n String-luokan equals-metodia.
     *
     * @param obj -olio, jota testataan.
     * @return boolean, joka kertoo olivatko oliot yht�suuria.
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
     * Testaa nimen m��riteltyjen kriteerien mukaisesti. Nimi t�ytyy olla v�lilt�
     * a-z, A-Z tai 0-9. Lis�ksi hyv�ksyt��n alaviiva ja piste. Jos nimess� on piste,
     * niin se ei saa olla nimen ainoa merkki. Metodia ei tarvita t�m�n luokan ulkopuolella,
     * joten sen n�kyvyys on private.
     * @param e -StringBuilder, joka on Tieto-olion nimi-attribuutti.
     * @return true, jos nimi l�p�isi kriteerit, false jos ei.
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
                    //Tarkistetaan ett� nimess� on muitakin merkkej� pisteen lis�ksi (jos piste)
                    if(testattava == '.' && pilkottu.length <= 1) {
                        kaikkiOK = false;
                        //Lasketaan pisteiden m��r�
                    } else if(testattava == '.')
                        pisteidenLkm++;
                } else
                    kaikkiOK = false;
            }
            //Pisteit� saa olla vain yksi.
            if(pisteidenLkm >= 2)
                kaikkiOK = false;

            return kaikkiOK;
        }
        else
            return true;
    }
}
