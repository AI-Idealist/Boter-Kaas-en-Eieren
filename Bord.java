package boterkaaseieren;

public class Bord {
        static final int EMPTY = -1;
        static final int USER = 0;
        static final int AGENT = 1;
        static final int DIM = 3;
           public int[][] cellen = new int[DIM][DIM];; 
  

        public Bord() {
         
          for (int i=0;i <= DIM-1;i++) {
             for (int j=0;j <= DIM-1;j++) {
                cellen[j][i] = EMPTY;
             }
          }   
        }  
        
        public boolean isLegaleZet(int[] pzet) {
            boolean result = false;
            int pkolom = pzet[0];
            int prij = pzet[1];
            
            if ( (0 <= pkolom && pkolom <= DIM-1) && (0 <= prij && prij <= DIM-1)) {
               if ( cellen[pkolom][prij] == EMPTY ) {
               result = true;}
            } 
                      
         return result;
        }      
        
        public void DoeZet(int[] zet, int speler){ 
           int kolom = zet[0];
           int rij = zet[1];
           cellen[kolom][rij] = speler;
        }
             
      public boolean WinnendeZet(int[] zet, int speler) {
           int kolom = zet[0];
           int rij = zet[1];
                         
          return (cellen[kolom][0] == speler// 3-in-the-row
                   && cellen[kolom][1] == speler
                   && cellen[kolom][2] == speler
              || cellen[0][rij] == speler// 3-in-the-column
                   && cellen[1][rij] == speler
                   && cellen[2][rij] == speler
              || rij == kolom// 3-in-the-diagonal
                   && cellen[0][0] == speler
                   && cellen[1][1] == speler
                   && cellen[2][2] == speler
              || rij + kolom == 2  // 3-in-the-opposite-diagonal
                   && cellen[0][2] == speler
                   && cellen[1][1] == speler
                   && cellen[2][0] == speler);
   }
        
   public boolean isGelijkSpel() {
      for (int i = 0; i <= 2; i++) {
         for (int j = 0; j <= 2; j++) {
            if (cellen[i][j] == EMPTY) {
               return false;  
            }
         }
      }
      return true; 
   }
      
    public void printcel(int i, int j) {
      if (cellen[i][j] == EMPTY) System.out.print("-");
      if (cellen[i][j] == USER) System.out.print("x");
      if (cellen[i][j] == AGENT) System.out.print("o");
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