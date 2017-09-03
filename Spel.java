package tictactoe;

import java.util.Scanner;

public class Spel {
  Bord bord = new Bord();
  int[] zet= new int[] {-1,-1};
  AISpeler aispeler = new AISpeler(bord);
   
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
 
    // De gebruiker speelt met fiche o
    // De agent speelt met fiche x
    public int speel() {
            
        bord.printbord();
        Scanner scan = new Scanner(System.in);
        System.out.println("Wil jij beginnen? (J/N) ");
        String antwoord = scan.next().trim();
    
        if(antwoord.equalsIgnoreCase("N")) {
             zet[0] = 1;
             zet[1] = 1;
             bord.DoeZet(zet, "agent");  
             bord.printbord();
        }
        
        while(true){ 
            zet = GebruikerKiestZet();
            bord.DoeZet(zet,"gebruiker"); 
            bord.printbord();
            if (bord.WinnendeZet(zet,"gebruiker")) {System.out.println("Jij Wint!");break;}
            else if (bord.isGelijkSpel()) {System.out.println("Gelijk spel");break;}
            
            zet = aispeler.KiestZetIntelligent();
            bord.DoeZet(zet, "agent");
             bord.printbord();
            if (bord.WinnendeZet(zet,"agent")) {System.out.println("Agent wint!");break;}
            else if (bord.isGelijkSpel()) {System.out.println("Gelijk spel");break;}
        }
        return 1;
        }     
    
     public static void main(String[] args) {
               
        Spel spel = new Spel();
        spel.speel();
    }
   
}