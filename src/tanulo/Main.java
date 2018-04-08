package tanulo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in);

  public Vector<Tanulo> tanulo;

  /**
   * Menu 
   * 1-8 konzol mveletek
   * 11-12 file műveletek
   * 9 kilépés
   */
  public void menu() {
          
      boolean vege = false;
      int sw;
      String nev;
      while (!vege){
        System.out.println();
        System.out.println("KONZOLMŰVELETEK");
        System.out.println();
        System.out.println("1. Tanulo felvetele");
        System.out.println("2. Jegy beirasa");
        System.out.println("3. Elfogadott Beadando beirasa");
        System.out.println("4. Dolgozat beirasa");
        System.out.println("5. Csoport atlag");
        System.out.println("6. Atlagok listazasa");
        System.out.println("7. Legjobb tanulo kiirasa");
        System.out.println("8. Hanyan kaphatnak gyak jegyet");
        System.out.println();
        System.out.println("FILEMŰVELETEK");
        System.out.println();
        System.out.println("11. Fájl betöltése");
        System.out.println("112 Fájl betöltéséhez segédlet");
        System.out.println("12. Összes adat fájlba mentése");
        System.out.println("9. Kilepes");
        System.out.println();
        sw = scan.nextInt();
        scan.nextLine();
        switch (sw){
            case 1:
                System.out.println();
                System.out.println();
                System.out.println("Kerem a tanulo nevet!");
                nev = scan.nextLine();
                boolean van;
                if(tanulo.isEmpty())
                    van = false;
                else
                    van = true;
                while(van){
                    for(int i = 0; i < tanulo.size(); i++){
                        if(tanulo.get(i).equals(nev)){
                            System.out.println("Van mar ilyen nevu tanulo! Kerem adjon masik nevet neki!");
                            nev = scan.nextLine();
                        }                   
                        else
                            van = false;
                    }
                }
                tanulo.add(new Tanulo(nev));
                break;
            case 2:
                System.out.println();
                System.out.println();
                if (tanulo.isEmpty()){
                    System.out.println("Meg nincs egy tanulo sem!");
                }else{
                    System.out.println("Valasszon diakot!");
                    for(int i = 0; i < tanulo.size(); i++){
                        System.out.println("  " + i + ". " + tanulo.get(i).Getnev());
                    }
                    sw = scan.nextInt();
                    scan.nextLine();
                    while(!ellenorzes(sw,tanulo.size(),0)){
                        System.out.println("Nincs ilyen valasztasi lehetoseg! (valasszon masikat)");
                        sw = scan.nextInt();
                        scan.nextLine();
                    }
                    System.out.println("Adja meg a jegyet!");
                    int jegy;
                    jegy = scan.nextInt();
                    scan.nextLine();
                    while(!ellenorzes(jegy,6,1)){
                        System.out.println("1 es 5 kozotti szamot adjon meg!");
                        jegy = scan.nextInt();
                        scan.nextLine();
                    }
                    tanulo.get(sw).jegy_ad(jegy);
                }
                break;
            case 3:
                System.out.println();
                System.out.println();
                if(tanulo.isEmpty()){
                    System.out.println("Meg nincs egy tanulo sem!");
                }else{
                System.out.println("Valassza ki hogy ki adta be a beadandot!");
                for(int i = 0; i < tanulo.size(); i++){
                    System.out.println(i + ". " + tanulo.get(i).Getnev());
                }
                sw = scan.nextInt();
                scan.nextLine();
                while(!ellenorzes(sw,tanulo.size(),0)){
                        System.out.println("Nincs ilyen valasztasi lehetoseg! (valasszon masikat)");
                        sw = scan.nextInt();
                        scan.nextLine();
                    }
                tanulo.get(sw).Setbead(true);
                }
                break;
            case 4:
                System.out.println();
                System.out.println();
                if(tanulo.isEmpty()){
                    System.out.println("Meg nincs egy tanulo sem!");
                }else{
                    for(int i = 0; i < tanulo.size(); i++){
                        System.out.println("Kerem a(z)" + tanulo.get(i).Getnev() + " jegyet!");
                        int jegy;
                        jegy = scan.nextInt();
                        scan.nextLine();
                        while(!ellenorzes(jegy,6,1)){
                            System.out.println("1 es 5 kozotti szamot adjon meg!");
                            jegy = scan.nextInt();
                            scan.nextLine();
                        }
                        tanulo.get(i).jegy_ad(jegy);                   
                    }
                }
                break;
            case 5:
                System.out.println();
                System.out.println();
                if(tanulo.isEmpty()){
                    System.out.println("Meg nincs egy tanulo sem!");
                }else{
                    double atlag = 0;
                    for(int i = 0; i < tanulo.size(); i++){
                        atlag += tanulo.get(i).atlag();
                    }
                    System.out.println("A csoport atlaga: " + (atlag / tanulo.size()));
                }
                break;
            case 6:
                System.out.println();
                System.out.println();
                if(tanulo.isEmpty()){
                    System.out.println("Meg nincs egy tanulo sem!");
                }else{
                    for(int i =0; i < tanulo.size(); i++){
                        System.out.println(tanulo.get(i).Getnev() + ": " + tanulo.get(i).atlag());
                    }
                }
                break;
            case 7:
                System.out.println();
                System.out.println();
                if(tanulo.isEmpty()){
                    System.out.println("Meg nincs egy tanulo sem!");
                }else{
                    int j = 0;
                    for(int i = 1; i < tanulo.size(); i++){
                        if(tanulo.get(j).atlag() < tanulo.get(i).atlag()){
                            j = i;
                        }
                    }
                    System.out.println(tanulo.get(j).Getnev() + " a legjobb tanulo  (" + tanulo.get(j).atlag() + ")");
                }
                break;
            case 8:
                System.out.println();
                System.out.println();
                if(tanulo.isEmpty()){
                    System.out.println("Meg nincs egy tanulo sem!");
                }else{
                    int osszeg = 0;
                    for(int i = 0; i < tanulo.size(); i++){
                        if(tanulo.get(i).teljesit()){
                            ++osszeg;
                        }  
                    }
                    System.out.println(osszeg + " tanulo kaphat gyakorlati jegyet.");
                }
                break;
            case 9:
                vege = true;
                break;
            case 11:
                try{
                    readFromFile();
                } catch(IOException e){
                    System.err.println("Hiba a fájlolvasásban!");
                    e.printStackTrace();
                } catch (NumberFormatException e){
                    System.err.println("Nem szám található a számnak kijelölt helyen! Olvasd el a fájl formátumáról szóló segítséget a 112es paranccsal!");
                }
                break;
            case 112:
                printHelpForfileReader();
                break;
            case 12:
                try{
                    printToFile();
                }  catch(IOException e){
                    System.err.println("Hiba a fájlolvasásban!");
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println();
                System.out.println();
                System.out.println("Nem letezo menupont!");
        }
      }
  }
  
  /**
   * Ellenőrzi, hogy sw az intervallumban van-e?
   * @param sw int alany
   * @param interf int felső
   * @param intera int alsó
   * @return 
   */
  public boolean ellenorzes(int sw, int interf, int intera){
      boolean bol;
      if(sw > (interf - 1) || sw < intera){
          bol = false;
      }else
          bol = true;
      return bol;
  }
  
  public Main(){
        tanulo = new Vector();
}
  
  public static void main(String[] args){
      Main main = new Main();
      main.menu();
  }
/**
 * Csak a fájl szerkezetének leírását írja ki a konzolra
 */
    private void printHelpForfileReader() {
        System.out.println();
        System.out.println("A fájl kiterjesztése: \t .txt");
        System.out.println("A sorok formátuma: \t DIÁK_NEVE \t KAPHAT-E_GYAKJEGYET \t DOLGOZAT_JEGYE \t JEGYEI");
        System.out.println("\t\t\t DIÁK_NEVE: string ");
        System.out.println("\t\t\t JEGYEI: intek, szóközzel elválasztva, legalább egynek kell lennie!");
        System.out.println("\t\t\t KAPHAT-E_GYAKJEGYET: logikai (true/false)");
        System.out.println("\t\t\t DOLGOZAT_JEGYE: logikai (1-5)");
        System.out.println("Elválasztó karakter: \t szóköz");
    }
/**
 * Fájlból beolvasó függvény
 * @throws IOException 
 */
    private void readFromFile() 
    throws IOException, NumberFormatException {
        System.out.print("Add meg a fájl nevét: ");
        String input = scan.nextLine();
        System.out.println();
        try (Scanner sc = new Scanner(new File(input));){
            while (sc.hasNextLine()) {
                String current = sc.nextLine();
                if (sc.hasNextLine()) {
                    String nextLine = sc.nextLine();

                    String[] res1 = current.split(" ");
                    String[] res2 = nextLine.split(" ");
                    
                    String currentName = res1[0];
                    String currentKaphatGyakjegyet = res1[1];
                    String currentDolgozatJegye = res1[2];
                    Vector<Integer> currentJegyek;
                    currentJegyek = new Vector();
                    for(int i = 3; i<res1.length; ++i){
                        currentJegyek.add(Integer.parseInt(res1[i]));
                    }
                    feltolt(currentName, currentKaphatGyakjegyet, currentDolgozatJegye, currentJegyek);
                    
                    String nextName = res2[0];
                    String nextKaphatGyakjegyet = res2[1];
                    String nextDolgozatJegye = res2[2];
                    Vector<Integer> nextJegyek;
                    nextJegyek = new Vector();
                    for(int i = 3; i<res2.length; ++i){
                        nextJegyek.add(Integer.parseInt(res1[i]));
                    }            
                    feltolt(nextName, nextKaphatGyakjegyet, nextDolgozatJegye, nextJegyek);
                }
            }
        }
    }
    
    /**
     * A fájlból beolvasó és feltöltő művelet
     * @param nev, String
     * @param gyakjegyetKaphatE, String
     * @param dolgozatJegye, String
     * @param jegyei Vector<Integer>
     */
    private void feltolt(String nev, String gyakjegyetKaphatE, String dolgozatJegye, Vector<Integer> jegyei){
       /*System.out.println("\t nev:"+nev);
       System.out.println("\t gyakjegyetKaphatE:"+gyakjegyetKaphatE);
       System.out.println("\t dolgozatJegye:"+dolgozatJegye);
       System.out.println("\t jegyei:");
       for(int i=0; i<jegyei.size(); ++i ){
           System.out.println("\t \t"+ jegyei.get(i) );
       }*/
        
        boolean van=true;
        
        int idx=0;
        while((van) && (idx<tanulo.size())){
        //for (int idx = 0; idx < tanulo.size(); idx++) {
            if (tanulo.get(idx).equals(nev)) {
                System.err.println("Van mar " + nev + " nevu tanulo! Kerem adjon masik nevet neki!");
                van = false;
            }
            ++idx;
        }

        if(van){
            Tanulo tmpTanulo = new Tanulo(nev);
            tmpTanulo.Setbead(Boolean.parseBoolean(gyakjegyetKaphatE));
            if(ellenorzes(Integer.parseInt(dolgozatJegye),6,0)){
                tmpTanulo.jegy_ad(Integer.parseInt(dolgozatJegye));
            } else {
                System.err.println("Ez nem jegy!");
            }
            for(idx=0; idx<jegyei.size(); ++idx){
                if (ellenorzes(jegyei.get(idx),6,0)) {
                    tmpTanulo.jegy_ad(jegyei.get(idx));
                } else {
                    System.err.println("Ez nem jegy!");
                }
            }
            tanulo.add(tmpTanulo);
        }
    }

    /**
     * Kiírja fájlba a diákok adatait
     * @throws IOException 
     */
    private void printToFile()
    throws IOException{
        String outName = "out.txt";
        File f = new File(outName);
        if (f.exists() && !f.isDirectory()) {
            int idx = 1;
            while(f.exists() && !f.isDirectory()){
                outName = "out" + idx + ".txt";
                ++idx;
                f = new File(outName);
            }
        }
        try (
            PrintWriter pw = new PrintWriter(outName);) {
            pw.println("NÉV\tÁTLAG\tTeljisíti-e a tárgyat");
            for (int idx=0; idx<tanulo.size(); ++idx) {
                    pw.println(tanulo.get(idx).Getnev()+"\t"+tanulo.get(idx).atlag()+"\t"+ tanulo.get(idx).teljesit() );
                }
            }
        System.out.println("A fájl mentve: "+outName);
    }
}