package AiHex.gameMechanics;

public class Move {
	private int colour, x, y;

	public Move(int colour, int x, int y) {
		this.colour = colour;
		this.x = x;
		this.y = y;
	}

    public Move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	public int getColour() {
		return this.colour;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
