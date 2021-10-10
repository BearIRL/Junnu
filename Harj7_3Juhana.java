
import java.util.Random;
import java.util.ArrayList;


public class Harj7_3Juhana
{
  // TEHTÄVÄ 3 METODIT
  
  // Arraylle
  public static ArrayList<Jalkapallonpelaaja> poimiJalkapalloilijat(Pelaaja[] pelaajat)
  {
    // Poimii kaikki jalkapallonpelaajat ja tallentaa ne uuteen ArrayList joka palautetaan
    
    ArrayList<Jalkapallonpelaaja> jpPelaajatLista = new ArrayList<Jalkapallonpelaaja>();
    for(int i = 0; i < pelaajat.length; i++)
    {
      if(!pelaajat[i].annaNimi().equals("") && pelaajat[i] instanceof Jalkapallonpelaaja)
      // JOS NIMI EI OLE TYHJÄ JA ON luokkaa Jalkapallonpelaaja
      {
        jpPelaajatLista.add((Jalkapallonpelaaja) pelaajat[i]);
      }
    }
    return(jpPelaajatLista);
  }
  
  // ArrayListille
  public static ArrayList<Jalkapallonpelaaja> poimiJalkapalloilijat(ArrayList<Pelaaja> pelaajat)
  {
    // Poimii kaikki jalkapallonpelaajat ja tallentaa ne uuteen ArrayList joka palautetaan
    
    ArrayList<Jalkapallonpelaaja> jpPelaajatLista = new ArrayList<Jalkapallonpelaaja>();
    for(int i = 0; i < pelaajat.size(); i++)
    {
      if(!pelaajat.get(i).annaNimi().equals("") && pelaajat.get(i) instanceof Jalkapallonpelaaja)
      // JOS NIMI EI OLE TYHJÄ JA ON luokkaa Jalkapallonpelaaja
      {
        jpPelaajatLista.add((Jalkapallonpelaaja) pelaajat.get(i));
      }
    }
    return(jpPelaajatLista);
  }
  
  
  private static Pelaaja[] luoSatunnainenJoukkue()
  {
    // Luodaan joukkueelle satunnaisia pelaajia
    Random rnd = new Random();
    
    Pelaaja[] tempPelaajat = new Pelaaja[22];
    for(int i = 0; i < tempPelaajat.length; i++)
    {
      // String nimi, int pelinumero, int syntymavuosi, double viikkopalkka, boolean onkoOikeajalkainen
      
      // Pelaajan nimi
      String tempNimi = "pelaaja" + i + "_" + rnd.nextInt(1000);
      // pelinumero
        // Valitaan numerkso i, jotta voidaan testata pelaajan poistamista pelinumerolla
      int tempPelinumero = /* rnd.nextInt(100)*/ i;
      // syntymävuosi
      int tempSyntymavuosi = 2005 - rnd.nextInt(20);
      // viikkopalkka
      double tempPalkka = 900 + 500 * rnd.nextDouble();
      
      // JOKA TOINEN PELAAJA, JOKA TOINEN JALKAPALLONPELAAJA
      if(i % 2 == 0)
      {
        tempPelaajat[i] = new Jalkapallonpelaaja(tempNimi, tempPelinumero, tempSyntymavuosi, tempPalkka, rnd.nextBoolean());
      } else
      {
        tempPelaajat[i] = new Pelaaja(tempNimi, tempPelinumero, tempSyntymavuosi);
      }
    }
    return(tempPelaajat);
  }
  
  
  public static void main(String[] args)
  {
    // Testataan
    
    // Muutettu edellisestä tehtävästä, joka toinen tässä listassa on Jalkapallonpelaaja
    // ja joka toinen vain Pelaaja
    Pelaaja[] pelaajat = luoSatunnainenJoukkue();
    
    // Luodaan uusi ArrayList, johon poimitaan pelaajat arrayn Jalkapalloilijat
    ArrayList<Jalkapallonpelaaja> jpPelaajatLista = poimiJalkapalloilijat(pelaajat);
    
    
    // Tulostetaan molempien pelaajat / jalkapallonpelaajat
    for(int i = 0; i < pelaajat.length; i++)
    {
      System.out.println(pelaajat[i].annaNimi());
    }
    
    System.out.println("");
    
    for(int i = 0; i < jpPelaajatLista.size(); i++)
    {
      // Vain jalkapallonpelaajilla voi olla viikkopalkka, joten kokeillaan tulostaa sitä
      System.out.println(jpPelaajatLista.get(i).annaNimi() + " " + jpPelaajatLista.get(i).annaViikkopalkka());
    }
  }
}




class Pelaaja
{
  // Attribuutit
  protected String nimi = "";
  protected int pelinumero;
  protected int syntymavuosi;
  
  // Konstruktorit
  public Pelaaja() {}
  public Pelaaja(String nimi, int pelinumero, int syntymavuosi)
  {
    this.nimi = nimi;
    this.pelinumero = pelinumero;
    this.syntymavuosi = syntymavuosi;
  }
  
  // Anna-metodit
  public String annaNimi()
  {return(nimi);}
  public int annaPelinumero()
  {return(pelinumero);}
  public int annaSyntymavuosi()
  {return(syntymavuosi);}
  
  // Aseta-metodit
  public void asetaNimi(String nimi)
  {this.nimi = nimi;}
  public void asetaPelinumero(int pelinumero)
  {this.pelinumero = pelinumero;}
  public void asetaSyntymavuosi(int syntymavuosi)
  {this.syntymavuosi = syntymavuosi;}
  
  
}
class Jalkapallonpelaaja extends Pelaaja
{
  // Attribuutit
  private double viikkopalkka;
  private boolean onkoOikeajalkainen;
    // true = oikeajalkainen, false = vasenjalkainen
  
  // Konstruktorit
  public Jalkapallonpelaaja()
  {
    super();
  }
  public Jalkapallonpelaaja(String nimi, int pelinumero, int syntymavuosi, double viikkopalkka, boolean onkoOikeajalkainen)
  {
    super(nimi, pelinumero, syntymavuosi);
    this.viikkopalkka = viikkopalkka;
    this.onkoOikeajalkainen = onkoOikeajalkainen;
  }
  
  // Anna-metodit
  public double annaViikkopalkka()
  {return(viikkopalkka);}
  public boolean annaOnkoOikeajalkainen()
  {return(onkoOikeajalkainen);}
  
  // Aseta-metodit
  public void asetaViikkopalkka(double viikkopalkka)
  {this.viikkopalkka = viikkopalkka;}
  public void asetaOnkoOikeajalkainen(boolean onkoOikeajalkainen)
  {this.onkoOikeajalkainen = onkoOikeajalkainen;}
}