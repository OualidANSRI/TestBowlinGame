package com.bowlingame;



public class Main {

	public static void main(String args[]) {

		BowlingGame game = new BowlingGame();

		System.out.print("\n***** GAME STARTED *****");
		System.out.println();
		System.out.println();
		//Jeu commencé
		for (int i = 0; i < 11; i++) {
			game.roll();
		}
		//panneau d'affichage de score
		for (int i = 0; i < game.frames.length - 1; i++) {
            
			System.out.print("Attempt "+ (i+1) + " - ");
			System.out.print("[" + game.frames[i].getFirstRoll() + " , ");
			if (game.frames[i].isSpare())
				System.out.print("\\");
			else if (game.frames[i].isStrike() && i != game.frames.length - 2)
				System.out.print("X");
			else
				System.out.print(game.frames[i].getSecondRoll());
			
			System.out.print("] ,	Temp(point of frame (" + (i+1) + ") : ["+game.frames[i].getTemp()+ "]");
			System.out.print(", ***Points**** :" + game.frames[i].getPoints());
			System.out.println();	
		}
		//last frame
		if(game.isFinished() == true) {
			System.out.println();
			System.out.println("***** GAME FINISHED *****  TotalScore : [* " +game.frames[game.frames.length - 2].getPoints() +" *]");
		}
		if(game.isFinished() == false) {
			System.out.print("Attempt Bonus "+ (game.frames.length - 1+1) + " - ");
			System.out.print("[" + game.frames[game.frames.length - 1].getFirstRoll() + "");
			System.out.print("]");
			System.out.println();
			System.out.println();
			System.out.println("***** GAME FINISHED *****  TotalScore : [* " +game.frames[game.frames.length - 2].getPoints() +" *]");		
		}
	}
}

