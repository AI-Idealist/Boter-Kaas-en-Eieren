/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Scanner;

/**
 *
 * @author gerard
 */
public class User {
  
     public int[] askMove() {
         System.out.println("Welke kolom?");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
         System.out.println("Welke rij?");
          int col = scanner.nextInt();
          return new int[] {row, col};
    }
    
    
     
     
}
