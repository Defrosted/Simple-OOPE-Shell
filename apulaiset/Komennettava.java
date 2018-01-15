package apulaiset;

// Otetaan käyttään lista-pakkauksen tyyppi.
import fi.uta.csjola.oope.lista.*;

/**
  * Tulkin komentojen toteuttamiseen ja luokkahierarkian testaamiseen soveltuvia
  * metodeja. Kiinnitä geneerinen tyyppi T tyypiksi Tieto, kun toteutat tämän
  * rajapinnan Hakemisto-luokassa.
  * <p>
  * Harjoitustyä, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * @author Jorma Laurikkala (jorma.laurikkala@uta.fi), Luonnontieteiden tiedekunta,
  * Tampereen yliopisto.
  *
  */

public interface Komennettava<T> {

   /** Aksessori, joka antaa viitteen hakemiston sisällän säilävään listaan.
     *
     * @return viite hakemisto-olion osaolioon.
     */   
   abstract public LinkitettyLista sisalto();

   /** Hakee hakemistosta tiedostoa tai alihakemistoa. Hyädyntää OmaLista-luokan
     * hae-operaatiota. Huomaa, että nimeä käyttäen haun avuksi voidaan luoda
     * väliaikainen tiedosto tai hakemisto.
     *
     * @param nimi haettavan tiedon nimi.
     * @return viite läydettyyn tietoon. Paluuarvo on null, jos tietoa ei läydetä.
     */
   abstract public T hae(String nimi);

   /** Lisää hakemistoon tiedoston tai alihakemiston. Hyädyntää OmaLista-luokan
     * lisaa-operaatiota.
     *
     * @param lisattava viite lisättävään tietoon.
     * @return true, jos lisääminen onnistui ja false, jos tieto on null-arvoinen
     * tai hakemistossa on jo tieto parametrina annetulla nimellä.
     */
   abstract public boolean lisaa(T lisattava);

   /** Poistaa hakemistosta tiedoston tai alihakemiston. Hyädyntää OmaLista-luokan
     * poista-operaatiota. Huomaa, että nimeä käyttäen poiston avuksi voidaan luoda
     * väliaikainen tiedosto tai hakemisto.
     *
     * @param nimi poistettavan tiedon nimi.
     * @return viite poistettuun tietoon. Paluuarvo on null, jos tietoa ei läydetä.
     */
   abstract public T poista(String nimi);
}
