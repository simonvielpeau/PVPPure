package fr.noxi;

import fr.noxi.GameState;

public enum GameState {
	LOBBY(true), PVPF(false),GAME(false), FINISH(false);
	
	private static GameState current;
	private boolean canJoin;
	
	GameState(boolean b){
		canJoin = b;
	
	}
	public boolean canJoin(){
		return canJoin;
	}
	public static void setState(GameState state){
		current = state;
	}
	
	public static boolean isState(GameState state){
		return current == state;
	}

	public static GameState getState(){
		return current;
	}
}
