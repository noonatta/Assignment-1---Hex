package AiHex.players;

import java.util.ArrayList;

import AiHex.hexBoards.Board;
import AiHex.gameMechanics.Move;

public interface Player {

	public static final int CLICK_PLAYER = 3;
	public static final String CLICK_DEFAULT_ARGS = "n/a";

public static final int CLICK_PLAYER2 = 4;
	public static final String[] playerList = {"Human Player","Computer Player"};
  
	public static final int[] playerIndex = {  CLICK_PLAYER,CLICK_PLAYER2 };

  public static final String[] argsList = { CLICK_DEFAULT_ARGS};
	public Move getMove();
        public Move getMove2();

	public ArrayList<Board> getAuxBoards();
}
