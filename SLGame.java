/* Written by Charles Theva as part of the Introductory Programming Course  */
/* You are free to refactor and modify the program 							*/
package Game;

import java.util.Scanner;

// The main system level class 
public class SLGame
{
   private Player players[] = new Player[4];
   private int pCount;
   private Board bd;
   private int turn = 0;

   static Scanner scan = new Scanner(System.in);

   public static void main(String args[])
   {    
      SLGame sg = new SLGame();
   }

   public char displayMenu()
   {
        System.out.println("******* Snakes Menu ********");
        System.out.println("Play Game       : 1");
        System.out.println("Customize Board : 2");
        System.out.println("Exit            : 3");
        System.out.println("Enter 1/2/3     : ");
        System.out.println("**************************");
        return scan.nextLine().charAt(0);
   }

   public void changeTurn()
   {
         turn++;
         if (turn == pCount)
            turn = 0;
   }

   public SLGame()
   {
      bd = new Board();
      char ch;
      do {
        ch = displayMenu();
        switch (ch)
        {
          case '1' : play(); break;
          case '2' : bd.customize(); break;
        }
      } while (ch != '3');
   }

    
   public void play()
   {
      System.out.print("Enter number of players : ");
      pCount = scan.nextInt();
      if ( pCount> 4 || pCount < 2)
      {
         System.out.println("Minimum 2 players and Maximum 4 players");
         System.exit(0);
      }
      scan.nextLine();


      Dice dice = new Dice(bd.getGraphics());   

      for ( int i=0; i <pCount; i++)
      {
   	   System.out.print("Enter name of player " + (i + 1) + " : ");
         String name = scan.nextLine();         
         players[i] = new Player(bd,dice,i,1,name);
      }
      bd.add(players,pCount);

      int again;

      while ( true ) 
      {
         int pos = players[turn].move();   // players get to move in turn
         if ( pos != -1)
         {
            System.out.println("**** GAME OVER " + players[turn].getName() + " is the winner ******");
            break;
         }
         else
           changeTurn();
      }
   }
}

