package Game;
import java.util.*;
import java.awt.*;

import javax.swing.*;

class Player extends Draw
{
	private String name;
	private int pos = 1;		// the current position of the player piece
	private int escpt = 0;		// the current position of the player piece
	private int score;		// the current position of the player piece
	private int index;		// represents the player index 0, 1, 2 or 3 if 4 players
	private Dice dice;
	private Board bd;
	static Scanner scan = new Scanner(System.in);
	EscapePoint ep = new EscapePoint();
	public String getName()
	{
		return name;
	}
	public int getPos()
	{
		return pos;
	}
	public int getEscapePoint()
	{
		return escpt;
	}
/*	public int getScore()
	{
		return escpt;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
*/

	public Player(Board bd, Dice dice, int index, int pos, String name,int escpt)//, int score)
	{
		this.bd = bd;
		this.name = name;
		this.pos = pos;
		this.index = index;
		this.dice = dice;
	//	this.score = score;
		this.escpt = escpt;

	}


	public void computePos(int val)
	{


		pos = bd.newPos(pos);

	}
	public void Escapecalc(int val)
	{
		if ( pos + val <= 100)
		{
			pos += val;      
		}

		int type = bd.Escape(pos);
		ep.setEscapePoint(escpt);
		switch (type)
		{
		case 0 : 
			if(ep.getEscapePoint() > 0)
			{
				System.out.println(" Press 1 to use snake escape point");
				int  condition = scan.nextInt();
				if(condition == 1)
					ep.loseEscapePoint();

				else{			
					computePos(val);	// computes the new position based on the dice value  
					bd.repaint();		// causes the board and pieces to be redrawn

				}
			}
			else{			
				computePos(val);	// computes the new position based on the dice value  
				bd.repaint();		// causes the board and pieces to be redrawn

			}
			break;
		case 1 : 
			ep.gainEscapePoint() ;
			computePos(val);	// computes the new position based on the dice value  
			System.out.println(" you have gained an escape point");
			bd.repaint();		// causes the board and pieces to be redrawn

			break;

		case 2:
			computePos(val);	// computes the new position based on the dice value  
			bd.repaint();		// causes the board and pieces to be redrawn
			break;

		}
		escpt = ep.getEscapePoint();


	}



	// Causes the dice to be thrown and the new position to be computed
	public int move()
	{
		System.out.println("***** Turn of  " + name + " ******" );           
		String resp;
		int val;
		do {
			System.out.print("Press Enter to throw dice ");
			resp = scan.nextLine();
			System.out.println("Rolling the dice .... Please wait");
			val = dice.roll();
			System.out.println("You threw a " + val);
			Escapecalc(val);
			System.out.println(name + " is now at position " + pos);   
			if (pos == 100)
				return index;	// returns the index of the player winning the game
			if (val == 6)
				System.out.println("You get to throw again");
		} while (val == 6);
		return -1;
	}


	// Drawing the positions of the pieces using different colors 
	public void draw(Graphics g)
	{
		if (index == 0)
		{
			g.setColor(Color.WHITE);   
			g.fillOval((int)getX(pos)-10,getY(pos)-10,20,20); 
			g.setColor(Color.BLACK);   
			g.drawString("1",(int)getX(pos)-5,getY(pos)+5); 
		}
		if (index == 1)
		{
			g.setColor(Color.RED);   
			g.fillOval((int)getX(pos)+10,getY(pos)-10,20,20); 
			g.setColor(Color.BLACK);   
			g.drawString("2",(int)getX(pos)+15,getY(pos)+5); 
		}
		if (index == 2)
		{
			g.setColor(Color.BLUE);   
			g.fillOval((int)getX(pos)-10,getY(pos)+10,20,20); 
			g.setColor(Color.BLACK);   
			g.drawString("3",(int)getX(pos)-5,getY(pos)+25); 
		}
		if (index == 3)
		{
			g.setColor(Color.CYAN);   
			g.fillOval((int)getX(pos)+10,getY(pos)+10,20,20); 
			g.setColor(Color.BLACK);   
			g.drawString("4",(int)getX(pos)+15,getY(pos)+25); 
		}
	}

}
