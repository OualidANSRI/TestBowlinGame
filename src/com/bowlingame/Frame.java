package com.bowlingame;


public class Frame {
	
	private int firstRoll; //score du 1st roll
	private int secondRoll; // score du 2nd roll
	private int temp; // score du frame
	private int points; //score temporaire  évolue avec le temps
	private boolean isStrike;
	private boolean isSpare;

	public Frame() {

		this.firstRoll = BowlingGame.EMPTY;
		this.secondRoll = BowlingGame.EMPTY;
		this.temp = 0;
		this.points = 0;
		this.isSpare = false;
		this.isStrike = false;
	}

	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}
	public boolean getStrike() {
		return isStrike;
	}

	public boolean isSpare() {
		return isSpare;
	}

	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}

	public int getTemp() {
		return temp;
	}

	public void addToTemp(int val) {
		this.temp += val;
	}

	public int getFirstRoll() {
		return firstRoll;
	}

	public void setFirstRoll(int firstRoll) {
		this.firstRoll = firstRoll;
	}

	public int getSecondRoll() {
		return secondRoll;
	}

	public void setSecondRoll(int secondRoll) {
		this.secondRoll = secondRoll;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}

