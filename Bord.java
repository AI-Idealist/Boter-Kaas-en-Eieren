package boterkaaseieren;

import static boterkaaseieren.Spel.EMPTY;
import static boterkaaseieren.Spel.AGENT;
import static boterkaaseieren.Spel.USER;
import static boterkaaseieren.Spel.DIM;

public class Bord {
     
        public int[][] velden = new int[DIM][DIM];; 
  
        public Bord() {
         
          for (int i=0;i <= DIM-1;i++) {
             for (int j=0;j <= DIM-1;j++) {
                velden[j][i] = EMPTY;
             }
          }   
        }  
        
        public boolean isLegaleZet(int[] pzet) {
            boolean result = false;
            int pkolom = pzet[0];
            int prij = pzet[1];
            
            if ( (0 <= pkolom && pkolom <= DIM-1) && (0 <= prij && prij <= DIM-1)) {
               if ( velden[pkolom][prij] == EMPTY ) {
               result = true;}
            } 
                      
         return result;
        }      
        
        public void DoeZet(int[] zet, int speler){ 
           int kolom = zet[0];
           int rij = zet[1];
           velden[kolom][rij] = speler;
        }
             
      public boolean WinnendeZet(int[] zet, int speler) {
           int kolom = zet[0];
           int rij = zet[1];
                         
          return (velden[kolom][0] == speler// 3-in-the-row
                   && velden[kolom][1] == speler
                   && velden[kolom][2] == speler
              || velden[0][rij] == speler// 3-in-the-column
                   && velden[1][rij] == speler
                   && velden[2][rij] == speler
              || rij == kolom// 3-in-the-diagonal
                   && velden[0][0] == speler
                   && velden[1][1] == speler
                   && velden[2][2] == speler
              || rij + kolom == 2  // 3-in-the-opposite-diagonal
                   && velden[0][2] == speler
                   && velden[1][1] == speler
                   && velden[2][0] == speler);
   }
        
   public boolean isGelijkSpel() {
      for (int i = 0; i <= 2; i++) {
         for (int j = 0; j <= 2; j++) {
            if (velden[i][j] == EMPTY) {
               return false;  
            }
         }
      }
      return true; 
   }
      
  public boolean gewonnen(int speler) {
    // horizontalCheck 
    for (int i = 0; i<=2; i++ ){
        int j = 0;
        if (velden[i][j] == speler && velden[i][j+1] == speler && velden[i][j+2] == speler ){
          return true;
        }
    }
    // vertical check 
    for (int j=0;j<= 2;j++) {
      int i = 0;
      if (velden[i][j] == speler && velden[i+1][j] == speler && velden[i+2][j] == speler ){
        return true;
      }           
    }    
    // ascendingDiagonalCheck 
    int i = 2;
    int j = 0;
                     
    if (velden[i][j] == speler && velden[i-1][j+1] == speler && velden[i-2][j+2] == speler ) {
      return true;
    }
    // descendingDiagonalCheck
    i = 2;
    j = 2;
    if (velden[i][j] == speler && velden[i-1][j-1] == speler && velden[i-2][j-2] == speler ) {
      return true;
    }
    return false;
  }
   
    public void printcel(int i, int j) {
      if (velden[i][j] == EMPTY) System.out.print("-");
      if (velden[i][j] == USER) System.out.print("x");
      if (velden[i][j] == AGENT) System.out.print("o");
    }
        
   public void printbord() {
      System.out.println(" ");
      for (int i=0;i <= DIM-1;i++) {
           for (int j=0;j<=DIM-1;j++) {
                printcel(i,j);
           }
       System.out.println(); 
       } 
     System.out.println();    
   }
 }