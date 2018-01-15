package oope2017ht.jarjestelma;
import apulaiset.In;

/**
 * Oope2017HT
 *
 * Olio-ohjelmoinnin perusteet, kev�t 2017
 *
 * @author Eetu Rinta-Jaskari.
 *
 * SOS-ohjelman k�ytt�liittym�, joka lukee sy�tteet ja tulostaa tulosteet.
 */
public class Kayttoliittyma {
    /*
     * Attribuutit
     */

    /**
     * Tulkki-attribuutti, jolla rakentujassa "k�ynnistet��n" tulkki ja jonka kautta
     * kutsutaan tulkin metodeita komentojen mukaisesti.
     */
    private Tulkki tulkki;


    /*
     * Luokkavakiot komennoille ja teksteille.
     */
    private final String TERVEHDYS = "Welcome to SOS.";
    private final String BYE = "Shell terminated.";
    private final String VIRHE = "Error!";

    private final String LISTDIR = "ls";
    private final String CHANGEDIR = "cd";
    private final String MAKEDIR = "md";
    private final String MAKEFILE = "mf";
    private final String CHANGENAME = "mv";
    private final String COPYFILE = "cp";
    private final String REMOVE = "rm";
    private final String RECURLIST = "find";
    private final String EXIT = "exit";

    /*
     * Rakentajat
     */

    public Kayttoliittyma(Object juuri) {
        tulkki = new Tulkki(juuri);
    }

    /*
     * Metodit
     */

    /**
     * Koko ohjelman "p��metodi", jossa on ohjelman p��silmukka.
     * Metodi kysyy sy�tteet k�ytt�j�lt�, k�sittelee ne ja kutsuu asianmukaisia metodeita
     * tarvittavilla tiedoilla. Lis�ksi hoidetaan l�hes kaikkien ohjelman tulosteiden tulostus.
     */
    public void suoritaOhjelma() {
        System.out.println(TERVEHDYS);

        //Luodaan lippumuuttuja silmukkaa varten
        boolean kaynnissa = true;
        //P��silmukka
        while(kaynnissa) {

            //Tulostetaan nykyinen polku ja luetaan k�ytt�j�n sy�te
            System.out.print(tulkki.sijainti(true) + "/>");
            String syote = In.readString();

            //Pilkotaan syote
            String[] komento = syote.split(" ");

            //Tarkistetaan, ettei sy�te tyhj�
            if(syote.length() > 0 && tarkistaSyote(syote)) {
                //Komentojen haku
                if(komento[0].equals(LISTDIR)) {
                    if(komento.length == 2)
                        System.out.print(tulkki.listaaHakemisto(komento[1]));
                    else if(komento.length == 1)
                        System.out.print(tulkki.listaaHakemisto(null));
                    else
                        sieppaaVirhe(false);
                }
                else if(komento[0].equals(CHANGEDIR)) {
                    if(komento.length == 2)
                        sieppaaVirhe(tulkki.vaihdaHakemistoa(komento[1]));
                    else if(komento.length == 1)
                        sieppaaVirhe(tulkki.vaihdaHakemistoa(null));
                    else
                        sieppaaVirhe(false);
                }
                else if(komento[0].equals(MAKEDIR) && komento.length == 2)
                    sieppaaVirhe(tulkki.luoHakemisto(komento[1]));

                else if(komento[0].equals(MAKEFILE)  && komento.length == 3)
                    sieppaaVirhe(tulkki.luoTiedosto(komento[1], komento[2]));

                else if(komento[0].equals(CHANGENAME) && komento.length == 3)
                    sieppaaVirhe(tulkki.vaihdaNimi(komento[1], komento[2]));

                else if(komento[0].equals(COPYFILE) && komento.length == 3)
                    sieppaaVirhe(tulkki.kopioiTiedosto(komento[1], komento[2]));

                else if(komento[0].equals(REMOVE) && komento.length == 2)
                    sieppaaVirhe(tulkki.poista(komento[1]));

                else if(komento[0].equals(RECURLIST) && komento.length == 1)
                    System.out.print(tulkki.hakemistonRekListaus(null, tulkki.sijainti(false)));

                else if(komento[0].equals(EXIT) && komento.length == 1) {
                    System.out.println(BYE);
                    kaynnissa = false;

                } else
                    System.out.println(VIRHE);
            }
        }
    }

    /**
     * Testaa onnistuiko tulkki suorittamaan metodin, tulostetaan virhe jos ei.
     * @param tulos suoritetun metodin tulos.
     */
    private void sieppaaVirhe(boolean tulos) {
        //Metodi saa tulkilta palautteena boolean-arvon,
        //joka kertoo onnistuiko komennon suoritus vai ei.
        //Jos suoritus ei onnistu, tulostetaan virheilmoitus.
        if(!tulos)
            System.out.println(VIRHE);
    }

    /**
     * Tarkistaa k�ytt�j�n sy�tteen oikeellisuuden (ylim��r�iset v�lily�nnit) ja tulostaa tarvittaessa virheen.
     * @param syote k�ytt�j�n sy�te
     * @return boolean, joka kertoo kelpaako sy�te vai ei
     */
    private boolean tarkistaSyote(String syote) {
        boolean kaikkiOK = true;
        //Pilkotaan taulukkoon
        String[] merkit = syote.split("");
        int valiLaskuri = 0;
        for(int i = 0; i < merkit.length; i++) {
            char merkki = merkit[i].charAt(0);

            //Alun v�li
            if(merkki == ' ' && i == 0)
                kaikkiOK = false;
            //Lopun v�li
            else if(merkki == ' ' && i == merkit.length-1)
                kaikkiOK = false;
            //Tuplav�lit eli ylim��r�iset v�lit
            else if(merkki == ' ' && i != merkit.length-1 && merkit[i+1].charAt(0) == ' ')
                kaikkiOK = false;
        }

        //Tulostetaan virhe jos kaikki ei ok
        if(!kaikkiOK)
            System.out.println(VIRHE);

        return kaikkiOK;
    }
}
