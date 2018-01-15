package apulaiset;

/*
 * Scanner-luokan palveluita hyädyntävä apuluokka int-, double-, char-
 * ja String-tyyppisten syätteiden lukemiseen. Operaatiot lukevat syätettä
 * jääräpäisesti, kunnes käyttäjä suostuu antamaan oikean tyyppisen syätteen.
 * (Myäs pelkän Enter-näppäimen painaminen aiheuttaa virheilmoituksen.)
 *
 * Luokkaa käytetään Lausekielinen ohjelmointi I ja II ja Olio-ohjelmoinnin
 * perusteet -kursseilla.
 *
 * Jorma Laurikkala (jorma.laurikkala@uta.fi), Informaatiotieteiden yksikkä
 * (tietojenkäsittelytieteet), Tampereen yliopisto.
 *
 * Versio: 1.1.
 *
 * Viimeksi muutettu 8.9.2015 19.30.
 *
 */

import java.util.*;  // Scanner-luokka täällä.

final public class In {
   /*
    * Vakiomuotoiset luokka-attribuutit.
    *
    */

   // Oletussyätevirtaan liitetty syätteiden lukija.
   private static final Scanner READER = initializeREADER();

   // Virheilmoitus, joka tulostetaan, kun syäte on väärää tyyppiä.
   private static final String BARF = "Virheellinen syäte!";

   // Virheilmoitus, joka tulostetaan, kun on vakava ongelma.
   private static final String AARGH = "Virhe In-luokassa!";

   /*
    * Yksityiset luokkametodit.
    *
    */

   /* Metodi READER-attribuutin alustamiseen.
    */
   private static Scanner initializeREADER() {
      // Luodaan ja liitetään oletussyätevirtaan.
      Scanner sc = new Scanner(System.in);

      // Lokalisoidaan siten, että esimerkiksi desimaalimerkki on piste.
      Locale enLocale = new Locale("en");
      sc.useLocale(enLocale);

      // Palautetaan lukija.
      return sc;
   }

   /* Pysäytetään ohjelma, jos jokin meni pahasti pieleen.
    */
   private static void doNotSoGracefulExit(Exception e) {
      // Herjataan.
      System.out.println(AARGH);
      
      // Tulostetaan poikkuspino.
      e.printStackTrace();
      
      // Suljetaan virtuaalikone.
      System.exit(1);
   }

   /*
    * Julkiset luokkametodit.
    *
    */

   /* Luetaan käyttäjältä int-tyyppistä syätettä,
    * kunnes käyttäjä suostuu sellaisen antamaan.
    */
   public static int readInt() {
      // Luetaan, kunnes saadaan syäte.
      int intval = 0;
      boolean inputOK = false;
      do {
         // Luetaan rivi ja yritetään muuttaa se kokonaisluvuksi.
         try {
            intval = Integer.parseInt(READER.nextLine());
            inputOK = true;
         }

         // Siepataan väärän tyypin aiheuttama poikkeus.
         catch (NumberFormatException e) {
            // Herjataan.
            System.out.println(BARF);
            inputOK = false;
         }

         // Siepataan yllättävä poikkeus. (Jotain meni pahasti pieleen.)
         catch (Exception e) {
            doNotSoGracefulExit(e);
         }
      }
      while (!inputOK);

      // Palautetaan paluuarvo.
      return intval;
   }

   /* Luetaan käyttäjältä double-tyyppistä syätettä,
    * kunnes käyttäjä suostuu sellaisen antamaan.
    */
   public static double readDouble() {
      // Luetaan, kunnes saadaan syäte.
      double dblval = 0;
      boolean inputOK = false;
      do {
         // Luetaan rivi ja yritetään muuttaa se liukuluvuksi.
         try {
            dblval = Double.parseDouble(READER.nextLine());
            inputOK = true;
         }

         // Siepataan väärän tyypin aiheuttama poikkeus.
         catch (NumberFormatException e) {
            // Herjataan.
            System.out.println(BARF);
            inputOK = false;
         }

         // Siepataan yllättävä poikkeus. (Jotain meni pahasti pieleen.)
         catch (Exception e) {
            doNotSoGracefulExit(e);
         }
      }
      while (!inputOK);

      // Palautetaan paluuarvo.
      return dblval;
   }

   /* Luetaan käyttäjältä char-tyyppistä syätettä,
    * kunnes käyttäjä suostuu sellaisen antamaan.
    */
   public static char readChar() {
      // Luetaan, kunnes saadaan syäte.
      char chrval = 0;
      boolean inputOK = false;
      do {
         try {
             // Luetaan rivi.
            String strval = READER.nextLine();

            // Tarkastellaan syätettä.
            inputOK = strval.length() == 1;

            // Käyttäjä antoi yhden merkin, kuten piti.
            if (inputOK)
               chrval = strval.charAt(0);

            // Käyttäjä ei antanut merkkejä tai hän antoi useita merkkejä.
            else
               System.out.println(BARF);
         }

         // Siepataan yllättävä poikkeus. (Jotain meni pahasti pieleen.)
         catch (Exception e) {
            doNotSoGracefulExit(e);
         }

      }
      while (!inputOK);

      // Palautetaan paluuarvo.
      return chrval;
   }

   /* Luetaan käyttäjältä String-tyyppinen syäte,
    * joka ei voi olla tyhjä merkkijono ("").
    */
   public static String readString() {
      // Luetaan, kunnes saadaan syäte.
      String strval = "";
      boolean inputOK = false;
      do {
         try {
            // Luetaan rivi.
            strval = READER.nextLine();

            // Halutaan ainakin yksi merkki.
            inputOK = strval.length() > 0;

            // Herjat tarvittaessa.
            if (!inputOK)
               System.out.println(BARF);
         }

         // Siepataan yllättävä poikkeus. (Jotain meni pahasti pieleen.)
         catch (Exception e) {
            doNotSoGracefulExit(e);
         }
      }
      while (!inputOK);

      // Palautetaan paluuarvo.
      return strval;
   }
}
