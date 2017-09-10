package boterkaaseieren;

import java.util.Scanner;

public class Spel {
    static final int EMPTY = -1;
    static final int USER = 0;
    static final int AGENT = 1;
    static final int DIM = 3; 
   
  Bord bord = new Bord();
  int[] zet= new int[] {-1,-1};
  AISpelerMinMax aispeler = new AISpelerMinMax(bord);
   
public int[] GebruikerKiestZet(){
  int[] zet = new int[] {-1,-1};

  Scanner scan = new Scanner(System.in);
      
  while (!bord.isLegaleZet(zet)){
    System.out.print("Welke kolom? ");
     int rij = scan.nextInt();
    System.out.print("Welke rij? ");
    int kol = scan.nextInt(); 
    zet[0] = kol;
    zet[1] = rij;
 }
  return zet;
}
 
    // De gebruiker speelt met fiche x
    // De agent speelt met fiche o
    public int speel() {
            
        bord.printbord();
        Scanner scan = new Scanner(System.in);
        System.out.println("Wil jij beginnen? (J/N) ");
        String antwoord = scan.next().trim();
    
        if(antwoord.equalsIgnoreCase("N")) {
             zet[0] = 1;
             zet[1] = 1;
             bord.DoeZet(zet, AGENT);  
             bord.printbord();
        }
        
        while(true){ 
            zet = GebruikerKiestZet();
            bord.DoeZet(zet,USER); 
            bord.printbord();
            if (this.bord.gewonnen(USER)) {System.out.println("U wint!");break;}
             else if (bord.isGelijkSpel()) {System.out.println("Gelijk spel");break;}
            
            zet = aispeler.KiestZetIntelligent();
            bord.DoeZet(zet, AGENT);
            bord.printbord();
            if (this.bord.gewonnen(AGENT)) {System.out.println("Agent Wint!");break;}
            else if (bord.isGelijkSpel()) {System.out.println("Gelijk spel");break;}
        }
        return 1;
        }     
    
     public static void main(String[] args) {
               
        Spel spel = new Spel();
        spel.speel();
    }
   
}