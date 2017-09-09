package boterkaaseieren;

import java.util.ArrayList;
import java.util.List;

public class AISpelerMinMax {
      static final int EMPTY = -1;
     static final int USER = 0;
     static final int AGENT = 1;
     static final int DIM = 3;
     private Bord bord;
     private int[][] lcellen = new int[DIM][DIM];
       
     private int teller=0;
      
    public AISpelerMinMax(Bord pbord) {
      bord = pbord;
   } 
       
   // Agent kiest beste zet opbv van mogelijke opstellingen na n stappen 
   public int[] KiestZetIntelligent() {
     int[] zet = new int[2];
    
     // kopieer bordcellen om neveneffecten te voorkomen.
     for(int i = 0; i <= DIM-1; i++) {
       for (int j = 0; j <=DIM-1 ; j++) {
          lcellen[j][i] = bord.cellen[j][i];
       }
     }
        
    int[] result = minimax(2, AGENT);
     zet[0] = result[1];
     zet[1] = result[2];
    return zet; 
    }
   
   // retorneert score, bestCOl, bestRow 
   private int[] minimax(int diepte, int speler) {
      
        List<int[]> mzetten = BepaalMogelijkeZetten();
       
      // agent is maximizing; while gebruiker is minimizing
      int bestscore = (speler == AGENT) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      int score;
      int bestRow = -1;
      int bestCol = -1;
       
       if (mzetten.isEmpty() || diepte==0) { 
          bestscore = EvalueerOpstelling();  
       }
       else {
        for (int[] mzet : mzetten) {
            // Try this mzet for the current "speler"
            lcellen[mzet[0]][mzet[1]] = speler;
                  
            if (speler==AGENT) {  // mySeed (computer) is maximizing speler
                                
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
            } else {  // oppSeed is minimizing speler
             
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
            // Undo mzet
            
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
 
   /** The heuristic evaluation function for the given line of 3 cells
       @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
               -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
               0 otherwise */
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
      if (hasWon(AGENT) || hasWon(USER)) {
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
     
   private int[] winningPatterns = {
         0b111000000, 0b000111000, 0b000000111, // rows
         0b100100100, 0b010010010, 0b001001001, // cols
         0b100010001, 0b001010100               // diagonals
   };
 
   /** Returns true if thePlayer wins */
   private boolean hasWon(int thePlayer) {
      int pattern = 0b000000000;  // 9-bit pattern for the 9 cells
      for (int row = 0; row < 3; ++row) {
         for (int col = 0; col < 3; ++col) {
            if (lcellen[row][col] == thePlayer) {
               pattern |= (1 << (row * 3 + col));
            }
         }
      }
      for (int winningPattern : winningPatterns) {
         if ((pattern & winningPattern) == winningPattern) return true;
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