import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.List;



/**
 *
 * @author Saana Lukka
 */
public class lotto {
    
    
    private Set numerot;
    private Set lisanumerot;
    private static int laskuri = 0;
    public final int Id;
    

    
    public lotto()  //kun kyseessä on ensimmäinen arvottu rivi (= virallinen voittorivi)
                    // arvotaan sekä päänumerot että lisänumerot, muussa tapauksessa vain päänumerot  
    {
        this.Id = laskuri++;
        
        if (Id == 0)
        {
        numerot = ArvoNumerot();
        lisanumerot = ArvoLisaNumerot(numerot);
        }
        
        else
        {
            numerot = ArvoNumerot();
        }   
        
    }

    private Set ArvoNumerot() //arpoo lotto-olion päänumerot
    {
        int numero;
        Set<Integer> arvotutNumerot = new HashSet<>();
        
        int min = 1;
        int max = 40;
        int laajuus;

        
        Random r = new Random();
        laajuus = max - min - 1; //määritellään sallittujen numeroiden väli
        

        for (int i=0; i < 7; i++)  //arvotaan 7 numeroa
        {
            numero = r.nextInt(laajuus) + min;
            

           if (arvotutNumerot.contains(numero)) //mikäli sama numero toistuu arvotaan uudelleen kunnes onnistuu
           {
               while (arvotutNumerot.contains(numero))
               {
                     numero = r.nextInt(laajuus) + min;
               }
           }
           arvotutNumerot.add(numero);
        }
        
        return arvotutNumerot;
    }

    
    private Set ArvoLisaNumerot(Set paaluvut) //kuin edellä, parametrina riviin arvotut päänumerot sen varmistukseksi ettei lisänumeroistakaan löydy duplikaatteja
    {
        
        int numero;
        Set<Integer> arvotutLisaNumerot = new HashSet<>();

        
        int min = 1;
        int max = 40;
        int laajuus;

        Random r = new Random();
        laajuus = max - min - 1; 
        
        for (int i=0; i < 3; i++)
        {
            numero = r.nextInt(laajuus) + min;

            
            if (paaluvut.contains(numero) || arvotutLisaNumerot.contains((numero)))
            {
                numero = r.nextInt(laajuus) + min;
            }
          arvotutLisaNumerot.add(numero);
            
        }
        
        return arvotutLisaNumerot;
    }
    
   private int VertaaVoittoRiviin(lotto voittorivi)
    {

        Object[] PaanumeroTaulukko = voittorivi.numerot.toArray();
        
        Object[] NumeroTaulukko = this.numerot.toArray();
        
        int Osumat = 0;
     
        
        for (int i = 0; i < 7; i++) //kaksi sisäkkäistä for-silmukkaa vertaavat jokaista numeroa keskenään
        {
            for (int k = 0; k < 7; k++)
            {
                if (NumeroTaulukko[i].equals(PaanumeroTaulukko[k]))
                {
                    Osumat++;
                }
            }
        }
        return Osumat; 
 }
    
   private int VertaaVoittorivinLisanumeroihin (lotto voittorivi) //Sama homma kuin edellä mutta lisänumeroiden kanssa
    {
       Object[] LisanumeroTaulukko = voittorivi.lisanumerot.toArray();
       Object[] NumeroTaulukko = this.numerot.toArray();
       
       int LisaOsumat = 0;
       
        for (int i = 0; i < NumeroTaulukko.length; i++)
        {
            for (int k = 0; k < LisanumeroTaulukko.length; k++)
            {
                if (NumeroTaulukko[i].equals(LisanumeroTaulukko[k]))
                {
                    LisaOsumat++;
                }
            }
        }
        return LisaOsumat;
        
    }
    
   public static List<lotto> ArvoRivit(int lukumaara) //Palauttaa listan jossa on käyttäjän määrittelemä määrä satunnaisia rivejä
    {
        List<lotto> lottorivit = new ArrayList();

        for (int i = 1; i <= lukumaara; i++)
        {
            lotto rivi = new lotto();
            lottorivit.add(rivi);
        }
        
        return lottorivit;
    }
    
    
   public static List<lotto> JarjestaRivit (lotto voittorivi, List<lotto> pelattukuponki)
    {
        
            List<lotto> JarjestettyKuponki = new ArrayList(); //Palautettava lista
        
            List<lotto> taysosumat = new ArrayList(); //Yksittäisten osumien listat, jonka sisältö järjestetään lopuksi palautettavaan listaan
            List<lotto> kuusjayks = new ArrayList();
            List<lotto> kuus = new ArrayList();
            List<lotto> viis = new ArrayList();
            List<lotto> nelja = new ArrayList();
            List<lotto> kolme = new ArrayList();
            List<lotto> kaks = new ArrayList();
            List<lotto> yks = new ArrayList();
            List<lotto> eiosumia = new ArrayList();
            
            for (int i = 0; i < pelattukuponki.size(); i++ ) //Osumat lajitellaan omiin listoihinsa
            {
                
                if (pelattukuponki.get(i).VertaaVoittoRiviin(voittorivi) == 7)
            {
                     taysosumat.add(pelattukuponki.get(i));
            }
            
                if (pelattukuponki.get(i).VertaaVoittoRiviin(voittorivi) == 6 && pelattukuponki.get(i).VertaaVoittorivinLisanumeroihin(voittorivi) == 1)
            {
                     kuusjayks.add(pelattukuponki.get(i));
            }
                
                if (pelattukuponki.get(i).VertaaVoittoRiviin(voittorivi) == 6)
             
            {
                   kuus.add(pelattukuponki.get(i));
            
            }
                
                if (pelattukuponki.get(i).VertaaVoittoRiviin(voittorivi) == 5)
             
            {
                   viis.add(pelattukuponki.get(i));
            
            }
                
              if (pelattukuponki.get(i).VertaaVoittoRiviin(voittorivi) == 4)
             
            {
                   nelja.add(pelattukuponki.get(i));
            
            }
              if (pelattukuponki.get(i).VertaaVoittoRiviin(voittorivi) == 3)
             
            {
                   kolme.add(pelattukuponki.get(i));
            
            }
              
              if (pelattukuponki.get(i).VertaaVoittoRiviin(voittorivi) == 2)
             
            {
                   kaks.add(pelattukuponki.get(i));
            
            }
              
             if (pelattukuponki.get(i).VertaaVoittoRiviin(voittorivi) == 1)
             
            {
                   yks.add(pelattukuponki.get(i));
            
            }
             
            if (pelattukuponki.get(i).VertaaVoittoRiviin(voittorivi) == 0)
             
            {
                   eiosumia.add(pelattukuponki.get(i));
            
            }
            }

           
            //Erillisten listojen sisältö ajetaan järjestyksessä palautettavaan listaan, mikäli niissä on sisältöä
            if (!taysosumat.isEmpty())
            {
            for (int i = 0; i < taysosumat.size(); i++)
            {
                
                JarjestettyKuponki.add(taysosumat.get(i));

            }
            }

            if (!kuusjayks.isEmpty())
            {
            for (int i = 0; i < kuusjayks.size(); i++)
            {
                JarjestettyKuponki.add(kuusjayks.get(i));
                
            } 
            }
            

            if (!kuus.isEmpty())
            {
            for (int i = 0; i < kuus.size(); i++)
            {
                JarjestettyKuponki.add(kuus.get(i));
                
            }
            }
            

            if (!viis.isEmpty())
            {
                for (int i = 0; i < viis.size(); i++)
            {
                 JarjestettyKuponki.add(viis.get(i));
            }
            }
            

            if (!nelja.isEmpty())
            {
            for (int i = 0; i < nelja.size(); i++)
            {
                JarjestettyKuponki.add(nelja.get(i));
                
            }
            }
            

            if (!kolme.isEmpty())
            {    
            for (int i = 0; i < kolme.size(); i++)
            {
                JarjestettyKuponki.add(kolme.get(i));
                
            }
            }

            
            if (!kaks.isEmpty())
            {    
            for (int i = 0; i < kaks.size(); i++)
            {
                JarjestettyKuponki.add(kaks.get(i));
                
            }
            }
                     
            if (!yks.isEmpty())  
            {    
            for (int i = 0; i < yks.size(); i++)
            {
                JarjestettyKuponki.add(yks.get(i));
                
            }
            }
                                    
          if (!eiosumia.isEmpty())  
           {    
           for (int i = 0; i < eiosumia.size(); i++)
           {
                JarjestettyKuponki.add(eiosumia.get(i));
                
            }
           }
            
            return JarjestettyKuponki;
       }
    
   public int[] Tarkista (lotto voittorivi) //tarkistaa yksittäisen rivin vastaavuuden viralliseen voittoriviin, järjestää tulokset taulukkoon
    {
        int[] tulokset = new int[5];
        
          if (this.VertaaVoittoRiviin(voittorivi) == 4)
             
            {
               tulokset[4]++;
            
            }
         
        if (this.VertaaVoittoRiviin(voittorivi) == 5)
             
            {
               tulokset[3]++;
            
            }
        
        if (this.VertaaVoittoRiviin(voittorivi) == 6)
            
           {
             tulokset[2]++;
            
           }
        
        if (this.VertaaVoittoRiviin(voittorivi) == 6 && this.VertaaVoittorivinLisanumeroihin(voittorivi) == 1)
            {
               tulokset[1]++;
            }
        
        if (this.VertaaVoittoRiviin(voittorivi) == 7)
            {
               tulokset[0]++;
            }
                
                return tulokset;
    }
    
   public static int[] TarkistaKuponki (lotto voittorivi, List<lotto> kuponki) //tarkistaa kaikki käyttäjän arpomat rivit ja kokoaa tulokset taulukkoon
                    
        {
            
            int[] tuloslistaus = new int[6];
        
                 for (int i = 0; i < kuponki.size(); i++)
                   {   
          
                     tuloslistaus[0] = tuloslistaus[0] + kuponki.get(i).Tarkista(voittorivi)[0];
                     tuloslistaus[1] = tuloslistaus[1] + kuponki.get(i).Tarkista(voittorivi)[1];
                     tuloslistaus[2] = tuloslistaus[2] + kuponki.get(i).Tarkista(voittorivi)[2];
                     tuloslistaus[3] = tuloslistaus[3] + kuponki.get(i).Tarkista(voittorivi)[3];
                     tuloslistaus[4] = tuloslistaus[4] + kuponki.get(i).Tarkista(voittorivi)[4];

                    }
       
       
             tuloslistaus[5] = kuponki.size();
             
             return tuloslistaus;
            
        }
            
   public static int[] AjaSimulaatio (lotto voittorivi, int taysosumat) //Simulaatiometodi arpoo uusia rivejä ja vertaa niitä voittoriviin
                                                                         //kunnes 7 oikein-osumia on löytynyt parametrin määrittelemä määrä
    {

        List<lotto> kuponki;
        
        int[] tuloslistaus = new int[6];
        int kierros = 0;
        
       do //jokainen osuma nostaa tuloslistaus-taulukon vastavaa lukua yhdellä, kunnes vaadittava määrä 7 oikein-osumia on löytynyt
        {   
          kuponki = lotto.ArvoRivit(1);
          
          tuloslistaus[0] = tuloslistaus[0] + kuponki.get(0).Tarkista(voittorivi)[0];
          tuloslistaus[1] = tuloslistaus[1] + kuponki.get(0).Tarkista(voittorivi)[1];
          tuloslistaus[2] = tuloslistaus[2] + kuponki.get(0).Tarkista(voittorivi)[2];
          tuloslistaus[3] = tuloslistaus[3] + kuponki.get(0).Tarkista(voittorivi)[3];
          tuloslistaus[4] = tuloslistaus[4] + kuponki.get(0).Tarkista(voittorivi)[4];
          
          kierros++;
          
        }
       
       while (tuloslistaus[0] < taysosumat);
       
       tuloslistaus[5] = kierros; //taulukon viimeinen lokero sisältää pelattujen rivien kokonaismäärän
       
       return tuloslistaus;
    }
    
   public static void Tulosta(int[] tulokset) //Tulostetaan ajon tulokset
       {

                System.out.println("Rivejä pelattiin yhteensä " + tulokset[5]);
                //Tarkistetaan mistä voittoluokista löytyi tuloksia ja tulostetaan vastaava rivi
                if (tulokset[0] > 0)
                {    
                     System.out.print("7 oikein-tuloksia: " + tulokset[0]);
                     System.out.println(", Todennäköisyys 7 oikein-riviin: 1/" + tulokset[5] / tulokset[0]);
                }
                
                if (tulokset[1] > 0)
                {    
                    System.out.print("6+1 oikein-tuloksia: " + tulokset[1]);
                    System.out.println(", Todennäköisyys 6+1 oikein-riviin: 1/" + tulokset[5] / tulokset[1]);
                }
                
                if (tulokset[2] > 0)
                {    
                    System.out.print("6 oikein-tuloksia: " + tulokset[2]);
                    System.out.println(", Todennäköisyys 6 oikein-riviin: 1/" + tulokset[5] / tulokset[2]);
                }
                if (tulokset[3] > 0)
                {    
                    System.out.print("5 oikein-tuloksia: " + tulokset[3]);
                    System.out.println(", Todennäköisyys 5 oikein-riviin: 1/" + tulokset[5] / tulokset[3]);
                }
                if (tulokset[4] > 0)
                {    
                    System.out.print("4 oikein-tuloksia: " + tulokset[4]);
                    System.out.println(", Todennäköisyys 4 oikein-riviin: 1/" + tulokset[5] / tulokset[4]);
                }
                //Mikäli osumia ei löytynyt yhtään
                if (tulokset[0] == 0 && tulokset[1] == 0 && tulokset[2] == 0 && tulokset[3] == 0 && tulokset[4] == 0)
                {
                    System.out.println("Pelatut rivit eivät sisältäneet yhtään vähintään 4 oikein-osumaa.");
                }    
        }
            
    
   @Override
   public String toString()
    {
        if (Id == 0)
        {
            return numerot.toString()+lisanumerot.toString();
        }
        
        else
        {
            return numerot.toString()+ "ID:"+Id;
        }
        

    }
        

        
    
    
    
    
    public static void main ( String[] args ) {
        
        
        lotto voittorivi = new lotto(); 
        
        //Tehtävä 1: Käyttäjä pystyy pelaamaan useampia rivejä
        List<lotto> kuponki = lotto.ArvoRivit(10);
        
        System.out.println("Virallinen voittorivi: " + voittorivi.toString());
        lotto.Tulosta(lotto.TarkistaKuponki(voittorivi, kuponki));
        
        
         //Tehtävä 2: eri tulosten todennäköisyyksien selvittäminen ohjelman avulla
        
         lotto.Tulosta(lotto.AjaSimulaatio(voittorivi,5));
         
         
         //Tehtävä 3: pelattujen rivien lajittelija
        
        List<lotto> jarjestettykuponki = lotto.JarjestaRivit(voittorivi, kuponki);
      
        for (int i = 0; i < jarjestettykuponki.size(); i++)
         {
             System.out.println(jarjestettykuponki.get(i).toString());
         }
              
  
    }
}
