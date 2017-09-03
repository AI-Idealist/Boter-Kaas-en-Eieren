package tictactoe;

public class Bord {
        public char[][] cellen;          
        public char x = 'x';       
        public char o = 'o';  
        public char e = '-';  
        public int kolommen = 3;
        public int rijen = 3; 

        public Bord() {
         
          cellen =new char[kolommen][rijen];
          for (int i=0;i <= 2;i++) {
               for (int j=0;j <= 2;j++) {
                    cellen[i][j] = e;
               }
          }   
        }  
        
        public boolean isLegaleZet(int[] pzet) {
            boolean result = false;
            int pkolom = pzet[0];
            int prij = pzet[1];
            
            if ( (0 <= pkolom && pkolom < 3) && (0 <= prij && prij < 3)) {
               if ( cellen[pkolom][prij] == e ) {
               result = true;}
            } 
                      
         return result;
        }
        
        
        public void DoeZet(int[] zet, String speler){ 
           int kolom = zet[0];
           int rij = zet[1];
                     
           if (speler.equalsIgnoreCase("gebruiker")) { cellen[kolom][rij] = this.x;}
           else cellen[kolom][rij] = this.o;

        }
             
      public boolean WinnendeZet(int[] zet, String speler) {
           int kolom = zet[0];
           int rij = zet[1];
           char symbool;
            
         if (speler.equalsIgnoreCase("gebruiker")) { symbool = this.x;}
           else symbool = this.o;    
           
           
          return (cellen[kolom][0] == symbool// 3-in-the-row
                   && cellen[kolom][1] == symbool
                   && cellen[kolom][2] == symbool
              || cellen[0][rij] == symbool// 3-in-the-column
                   && cellen[1][rij] == symbool
                   && cellen[2][rij] == symbool
              || rij == kolom// 3-in-the-diagonal
                   && cellen[0][0] == symbool
                   && cellen[1][1] == symbool
                   && cellen[2][2] == symbool
              || rij + kolom == 2  // 3-in-the-opposite-diagonal
                   && cellen[0][2] == symbool
                   && cellen[1][1] == symbool
                   && cellen[2][0] == symbool);
   }
        
   public boolean isGelijkSpel() {
      for (int i = 0; i <= 2; i++) {
         for (int j = 0; j <= 2; j++) {
            if (cellen[i][j] == e) {
               return false;  
            }
         }
      }
      return true; 
   }
      
    public void printcel(int i, int j) {
      System.out.print(cellen[i][j]);
    }
        
   public void printbord() {
      System.out.println(" ");
      for (int i=0;i <= 2;i++) {
           for (int j=0;j<=2;j++) {
                printcel(i,j);
           }
       System.out.println(); 
       } 
     System.out.println();    
   }
 }