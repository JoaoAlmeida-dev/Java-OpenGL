package game;

import engine.GameEngine;
import engine.IGameLogic;

public class MainApp {
	public static void main(String[] args) throws InterruptedException {

		try {
			boolean vSync = true;
			IGameLogic gameLogic = new DummyGame();
			GameEngine gameEng = new GameEngine("Game", 600, 480, vSync, gameLogic);
			gameEng.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

}
