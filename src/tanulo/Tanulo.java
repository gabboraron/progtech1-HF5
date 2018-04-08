package tanulo;

import java.util.ArrayList;

public class Tanulo {

  private ArrayList<Integer> jegyek;
  private boolean bead;
  private String nev;

  /**
   * A jegyek tömb méretét adja
   * @return int, a jegyek számát 
   */
  public int jegy_db() {
    return jegyek.size();
  }

  /**
   * Átlagot visszaadja
   * @return int
   */
  public double atlag() {
      double osszeg = 0;
      int n = jegyek.size();
      for (int i = 0; i < n; i++){
          osszeg += jegyek.get(i);
      }
      if (n!=0) osszeg=osszeg / jegyek.size();
  return (osszeg);
  }

  /**
   * Teljesíti-e a diák a tárgyat
   * @return boolean
   */
  public boolean teljesit() {
    return (this.atlag() >= 2) && this.bead;
  }

  /**
   * Hozzáadja a dolgozatot.
   * @param jegy, int
   */
  public void jegy_ad(int jegy) {
      jegyek.add(jegy);
  }

  /**
   * Beadandó hozzáadása a diákhoz.
   * @param bol, boolean 
   */
  public void Setbead(boolean bol) {
      this.bead = bol;
  }

  /**
   * Tanuló inicializálása névvel.
   * @param nev, String
   */
  public Tanulo(String nev) {
      
      this.nev = nev;
      jegyek = new ArrayList();
      bead = false;
  }

  /**
   * A név lekérdezése
   * @return String 
   */
    String Getnev() {
        return this.nev;
    }
    
    /**
     * Megnézi hogy két diák név alapján egyezik-e
     * @param nev, String
     * @return boolean
     */
   public boolean equals(String nev){
       return this.nev.equals(nev);
   }

   
}