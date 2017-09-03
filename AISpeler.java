package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AISpeler {
     private Bord bord;
     private char[][] lcellen = new char[3][3];
      
    public AISpeler(Bord pbord) {
      bord = pbord;
   } 
 
    // Agent kiest zet alleen obv huidige opstelling. Dus kijkt NIET vooruit
    public int[] KiestZetNaief() {
    int[] zet = new int[2];
     // kopieer bordcellen om neveneffecten te voorkomen.
       for(int i = 0; i <= 2; i++) {
         for (int j = 0; j <=2 ; j++) {
          lcellen[i][j] = bord.cellen[i][j];
         }
       }
    List<int[]> mzetten = BepaalMogelijkeZetten();
    zet = BepaalBesteZet(mzetten);
  
    return zet; 
    }
 
    // Agent kiest steeds een wilekeurige zet 
    public int[] KiestZetWill() {
    int[] zet = new int[2];
     
    List<int[]> mzetten = BepaalMogelijkeZetten();     
         
    int min = 0;
    int max = mzetten.size();
    Random random = new Random();
    random.setSeed(1);
    int rij = random.nextInt(max - min + 1) + min;
    zet = mzetten.get(rij);
    return zet;
    }
    
   private List<int[]> BepaalMogelijkeZetten() {
      List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List
 
      // Search for empty cells and add to the List
      for (int i = 0; i <= 2; i++) {
         for (int j = 0; j <= 2; j++) {
            if (lcellen[j][i] == bord.e) {
               nextMoves.add(new int[] {j, i});
            }
         }
      }
      return nextMoves;
   }
     
   public int[] BepaalBesteZet(List<int[]>  opties) {
      int hiscore = Integer.MIN_VALUE;
      int index = -1; //op welke plaats in de lijst van mogelijke zetten staat de beste zet.
        
        for (int i = 0; i < opties.size(); i++) { // probeer ieder mogelijke zet
            int[] zet = opties.get(i);
            int kolom = zet[0];
            int rij = zet[1];
            char tmp = lcellen[kolom][rij];
            lcellen[kolom][rij] = bord.o;
            
            int score = EvalueerOpstelling(); // bepaal hoe goed die is. 
            if (score >= hiscore) {
                   hiscore = score;
                   index = i;
            } 
            System.out.println("i :"+ i + " kolom : " + zet[0] + " rij : " + zet[1] + " score : " + score + " hiscore : " + hiscore + " index : " + index);
            
             for (int k=0;k <= 2;k++) {
                for (int j=0;j<=2;j++) {
                   System.out.print(lcellen[k][j]);
                }
               System.out.println(); 
              } 
             System.out.println(" ");    
             lcellen[kolom][rij] = tmp; // zet cellen terug naar de uitgangssituatie.
        }
     int[] zet = opties.get(index);
     return zet;
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
      if (lcellen[col1][row1] == bord.o) {
         score = 1;
      } else if (lcellen[col1][row1] == bord.x) {
         score = -1;
      }
 
      // Second cell
      if (lcellen[col2][row2] == bord.o) {
         if (score == 1) {   // cell1 is mySeed
            score = 10;
         } else if (score == -1) {  // cell1 is oppSeed
            return 0;
         } else {  // cell1 is empty
            score = 1;
         }
      } else if (lcellen[col2][row2] == bord.x) {
         if (score == -1) { // cell1 is oppSeed
            score = -10;
         } else if (score == 1) { // cell1 is mySeed
            return 0;
         } else {  // cell1 is empty
            score = -1;
         }
      }
 
      // Third cell
      if (lcellen[col3][row3] == bord.o) {
          
         if (score > 0) {  // cell1 and/or cell2 is mySeed
            score *= 10;
         } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
            return 0;
         } else {  // cell1 and cell2 are empty
            score = 1;
         }
      } else if (lcellen[col3][row3] == bord.x) {
          
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
   
    public void printcel(int i, int j) {
            System.out.print(lcellen[i][j]);
        }
        
        public void printbord() {
           for (int i=0;i <= 2;i++) {
                for (int j=0;j<=2;j++) {
                    printcel(i,j);
                }
               System.out.println(); 
          } 
         System.out.println();    
     }
 
}
