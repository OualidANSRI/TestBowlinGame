package com.bowlingame;

import java.util.Random;

public class BowlingGame {

	public static final int EMPTY = -1; // pour vérifier si on est dans le premier roll ou non
	public static final int LAST_FRAME = 10;
	public static final int BONUS_FRAME_1 = 11; 
	public static final int MAX_FRAMES = 11;
	public static final int MAX_PINS_ROLLED = 10;
	
	public Frame[] frames;	
	private int attempt; // n° of roll

	public BowlingGame() {

		this.attempt = 0;
		this.frames = new Frame[MAX_FRAMES];

		for (int i = 0; i < frames.length; i++) {

			this.frames[i] = new Frame();
		}
	}
	//algorithme du jeu
	public void roll() {
		int k;
		if (!isFinished()) {
			k = 0;
			if (frames[attempt].getFirstRoll() == EMPTY ) {
				Random r = new Random(); // lancement de la balle
				k = r.nextInt(11);
				frames[attempt].setFirstRoll(k); //score de 1st roll
				if (attempt <= (LAST_FRAME - 1)) {
					frames[attempt].addToTemp(k);
				}
				if (isStrike(attempt)) {
					frames[attempt].setStrike(true);
					attempt ++;
					return;
				}		
			}
			if(attempt != LAST_FRAME) {
				if ((frames[attempt].getFirstRoll() != EMPTY && isStrike(attempt)== false )|| frames[attempt-1].getStrike()== true){
					Random r = new Random();
					k = r.nextInt(11-k);
					frames[attempt].setSecondRoll(k);
					if ( attempt <= (LAST_FRAME - 1)  ) {
						frames[attempt].addToTemp(k);
					} 
					if (attempt == 0) {
						frames[attempt].setPoints(frames[attempt].getTemp());
					}
					if(attempt != 0){
						if (isStrike(attempt - 1)) {
							if((attempt -1) ==0) {
								frames[attempt -1].addToTemp(frames[attempt].getFirstRoll()+ frames[attempt].getSecondRoll());
								frames[attempt-1].setPoints(10 + frames[attempt].getFirstRoll()+ frames[attempt].getSecondRoll() );
								frames[attempt].setPoints( frames[attempt - 1].getPoints()+ frames[attempt].getFirstRoll()+ frames[attempt].getSecondRoll());
							}else {
								frames[attempt -1].addToTemp(frames[attempt].getFirstRoll()+ frames[attempt].getSecondRoll());
								frames[attempt-1].setPoints(10
								+ frames[attempt].getFirstRoll()+ frames[attempt].getSecondRoll() + frames[attempt-2].getPoints());
								frames[attempt].setPoints( frames[attempt - 1].getPoints()+ frames[attempt].getFirstRoll()+ frames[attempt].getSecondRoll());
							}
						}
						if (isSpare(attempt)) {
							frames[attempt].setSpare(true);
						}
					    if(attempt != 0) {
					    	if (isSpare(attempt - 1)) {
					    		if(attempt -1 == 0) {
					    			frames[attempt -1].addToTemp(frames[attempt].getFirstRoll());
					    			frames[attempt-1].setPoints(10 + frames[attempt].getFirstRoll());
					    			frames[attempt].setPoints( frames[attempt - 1].getPoints()+ frames[attempt].getFirstRoll()+ frames[attempt].getSecondRoll());
								} else {
					    			frames[attempt -1].addToTemp(frames[attempt].getFirstRoll());
					    			frames[attempt-1].setPoints(10 + frames[attempt].getFirstRoll()+ frames[attempt-2].getPoints());
					    			frames[attempt].setPoints( frames[attempt - 1].getPoints()+ frames[attempt].getFirstRoll()+ frames[attempt].getSecondRoll());
					    		}
					    	}
					    }
					    if (attempt - 1 >= 0) {
					    	frames[attempt].setPoints(frames[attempt - 1].getPoints() + frames[attempt].getTemp());
					    }
					} 
					attempt++;		
				}
			}else {
				if (isStrike(attempt-1)) {
					Random r = new Random();
					k = r.nextInt(10);
					frames[attempt-1].setSecondRoll(k);
					Random r1 = new Random();
					int k1 = r1.nextInt(10);
					frames[attempt].setFirstRoll(k1);
					frames[attempt -1].addToTemp(frames[attempt].getFirstRoll()+frames[attempt-1].getSecondRoll());
		    		frames[attempt-1].setPoints(frames[attempt-2].getPoints() + frames[attempt-1].getTemp());
		    	}
				if (isSpare(attempt - 1)) {
			    	frames[attempt -1].addToTemp(frames[attempt].getFirstRoll());
			    	frames[attempt-1].setPoints(10 + frames[attempt].getFirstRoll()+ frames[attempt-2].getPoints());
			    	frames[attempt].setPoints( frames[attempt - 1].getPoints()+ frames[attempt].getFirstRoll()+ frames[attempt].getSecondRoll());
				}
			}
		}
	}

	public int getScore() {

		int ptr = EMPTY;

		for (int i = frames.length - 1; i >= 0; i--) {
			if (frames[i].getPoints() > 0) {
				ptr = i;
				break;
			}
		}
        if (ptr == EMPTY)
			return 0;
		else
			return frames[ptr].getPoints();
	}

	public boolean isFinished() {
		
		if(attempt == LAST_FRAME && !isSpare(attempt -1) && !isStrike(attempt -1)) {
			return true;
		}
		if(attempt == LAST_FRAME && ( isSpare(attempt) || isStrike(attempt))) {
			return false;
		}
		if (attempt <= LAST_FRAME - 1) {
			return false;
		}
		return false;
	}

	public boolean isStrike(int attempt) {
		
		return frames[attempt].getFirstRoll() == 10;
	}

	public boolean isSpare(int attempt) {

		return (frames[attempt].getFirstRoll() + frames[attempt].getSecondRoll()) == 10;
	}
	
}
