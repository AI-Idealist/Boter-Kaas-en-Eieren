package boterkaaseieren;

import java.util.ArrayList;
import java.util.List;

import static boterkaaseieren.Spel.EMPTY;
import static boterkaaseieren.Spel.AGENT;
import static boterkaaseieren.Spel.USER;
import static boterkaaseieren.Spel.DIM;

public class AISpelerMinMax {
  private Bord bord;
  private int[][] lcellen = new int[DIM][DIM];
        
    public AISpelerMinMax(Bord pbord) {
      bord = pbord;
   } 
       
   // Agent kiest beste zet opbv van mogelijke opstellingen na n=2 stappen 
   public int[] KiestZetIntelligent() {
     int[] zet = new int[2];
    
     // kopieer bordcellen om neveneffecten te voorkomen.
     for(int i = 0; i <= DIM-1; i++) {
       for (int j = 0; j <=DIM-1 ; j++) {
          lcellen[j][i] = bord.velden[j][i];
       }
     }
        
    int[] result = minimax(2, AGENT);
     zet[0] = result[1]; 
     zet[1] = result[2];
    return zet; 
    }
   
   // @retourneert  bestscore, bestCOl, bestRow 
   private int[] minimax(int diepte, int speler) {
      
        List<int[]> mzetten = BepaalMogelijkeZetten();// mzetten = Mogelijke zetten
       
      // agent is maximizing; gebruiker is minimizing
      int bestscore = (speler == AGENT) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      int score;
      int bestRow = -1;
      int bestCol = -1;
       
       if (mzetten.isEmpty() || diepte==0) { 
          bestscore = EvalueerOpstelling();  
       }
       else {
        for (int[] mzet : mzetten) {
            
            // probeer zet voor de huidige "speler"
            lcellen[mzet[0]][mzet[1]] = speler;
                  
            if (speler==AGENT) {  // AGENT is maximizing speler
                                
                score = minimax(diepte - 1, USER)[0];
                System.out.println("************************");
                System.out.println("Cniveau :" + diepte + " " + "k: " + mzet[0] + " r: " + mzet[1] + " score : " + score + " hiscore : " + bestscore + " beste Kol : " + bestCol + "beste Rij " + bestRow);
                this.printbord();
                System.out.println("************************");
             
                if (score > bestscore) {
                  bestscore = score;
                  bestCol = mzet[0];
                  bestRow = mzet[1];
               }
            } else {  // USER is minimizing speler
             
                score = minimax(diepte - 1, AGENT)[0];
                System.out.println("************************");
                System.out.println("Aniveau :" + diepte + " " + "k: " + mzet[0] + " r: " + mzet[1] + " score : " + score + " hiscore : " + bestscore + " beste Kol : " + bestCol + "beste Rij " + bestRow);
                this.printbord();
                System.out.println("************************");
                
                if (score < bestscore) {
                  bestscore = score;
                  bestCol = mzet[0];
                  bestRow = mzet[1];
                }
            }
            
            // Undo zet
            lcellen[mzet[0]][mzet[1]] = EMPTY;
         }
       }
      return new int[] {bestscore, bestCol, bestRow};
   }
 
   private int EvalueerOpstelling() {
      int score = 0;
      // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
      score += evalueerLijn(0, 0, 0, 1, 0, 2);  // kol 0
      score += evalueerLijn(1, 0, 1, 1, 1, 2);  // kol 1
      score += evalueerLijn(2, 0, 2, 1, 2, 2);  // kol 2
      score += evalueerLijn(0, 0, 1, 0, 2, 0);  // row 0
      score += evalueerLijn(0, 1, 1, 1, 2, 1);  // row 1
      score += evalueerLijn(0, 2, 1, 2, 2, 2);  // row 2
      score += evalueerLijn(0, 0, 1, 1, 2, 2);  // diagonal
      score += evalueerLijn(0, 2, 1, 1, 2, 0);  // alternate diagonal
      return score;
   }
 
   // The heuristic evaluation function for the given line of 3 cells
   // @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
   // -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
   // 0 otherwise 
   private int evalueerLijn(int row1, int col1, int row2, int col2, int row3, int col3) {
      int score = 0; 
 
      // First cell
      if (lcellen[col1][row1] == AGENT) {
         score = 1;
      } else if (lcellen[col1][row1] == USER) {
         score = -1;
      }
 
      // Second cell
      if (lcellen[col2][row2] == AGENT) {
         if (score == 1) {   // cell1 is mySeed
            score = 10;
         } else if (score == -1) {  // cell1 is oppSeed
            return 0;
         } else {  // cell1 is empty
            score = 1;
         }
      } else if (lcellen[col2][row2] == USER) {
         if (score == -1) { // cell1 is oppSeed
            score = -10;
         } else if (score == 1) { // cell1 is mySeed
            return 0;
         } else {  // cell1 is empty
            score = -1;
         }
      }
 
      // Third cell
      if (lcellen[col3][row3] == AGENT) {
          
         if (score > 0) {  // cell1 and/or cell2 is mySeed
            score *= 10;
         } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
            return 0;
         } else {  // cell1 and cell2 are empty
            score = 1;
         }
      } else if (lcellen[col3][row3] == USER) {
          
         if (score < 0) {  // cell1 and/or cell2 is oppSeed
            score *= 10;
         } else if (score > 1) {  // cell1 and/or cell2 is mySeed
            return 0;
         } else {  // cell1 and cell2 are empty
            score = -1;
         }
      }
      return score;
   } 
   
    private List<int[]> BepaalMogelijkeZetten() {
      List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List
 
        
      // If gameover, i.e., no next move
      if (gewonnen(AGENT) || gewonnen(USER)) {
         return nextMoves;   // return empty list
      }
            
      // Search for empty cells and add to the List
      for (int i = 0; i <= 2; i++) {
         for (int j = 0; j <= 2; j++) {
            if (lcellen[j][i] == EMPTY) {
               nextMoves.add(new int[] {j, i});
            }
         }
      }
      return nextMoves;
   }
     
   public boolean gewonnen(int speler) {
    // drie in een kolom 
    for (int i = 0; i<=2; i++ ){
        int j = 0;
        if (lcellen[i][j] == speler && lcellen[i][j+1] == speler && lcellen[i][j+2] == speler ){
          return true;
        }
    }
    // drie op een rij
    for (int j=0;j<= 2;j++) {
      int i = 0;
      if (lcellen[i][j] == speler && lcellen[i+1][j] == speler && lcellen[i+2][j] == speler ){
        return true;
      }           
    }    
    // drie opgaande diagonaal
    int i = 2;
    int j = 0;
                     
    if (lcellen[i][j] == speler && lcellen[i-1][j+1] == speler && lcellen[i-2][j+2] == speler ) {
      return true;
    }
    // drie neergaande diagonaal
    i = 2;
    j = 2;
    if (lcellen[i][j] == speler && lcellen[i-1][j-1] == speler && lcellen[i-2][j-2] == speler ) {
      return true;
    }
    return false;
  }
       
     public void printcel(int i, int j) {
      if (lcellen[i][j] == EMPTY) System.out.print("-");
      if (lcellen[i][j] == USER) System.out.print("x");
      if (lcellen[i][j] == AGENT) System.out.print("o");
    }
  
        public void printbord() {
           for (int i=0;i <= 2;i++) {
                for (int j=0;j<=2;j++) {
                    printcel(j,i);
                }
               System.out.println(); 
          } 
         System.out.println();    
     }
 }