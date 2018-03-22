package puzzle;

import puzzle.game.GameListener;
import puzzle.highscore.HighscoreManager;

import javax.swing.*;
import java.awt.*;

/**
 * Puzzle, which handles the Slider Game instance, encapsulating all the necessary data and functionality.
 *
 * @author Liam Everton
 */
public class Puzzle {

	//The file name constants.
	public static final String IMAGE_DIRECTORY = "resources/";
	public static final String IMAGE_PREFIX = "bart";
	public static final String IMAGE_TYPE = ".jpg";

	//Different components of the Game.
	private final PuzzleFrame puzzleFrame;
	private final GameListener gameListener;

	private final HighscoreManager highscoreManager;

	//The width and height of the puzzle.
	private final int puzzleHeight;
	private final int puzzleWidth;

	//The user's current score.
	private int currentScore;

	/**
	 * Constructs a new Puzzle.
	 *
	 * @param puzzleHeight - the specified height, which the Puzzle should be.
	 * @param puzzleWidth  - the specified width, which the Puzzle should be.
	 */
	public Puzzle(int puzzleHeight, int puzzleWidth) {
		this.puzzleHeight = puzzleHeight;
		this.puzzleWidth = puzzleWidth;

		this.currentScore = 0;

		this.gameListener = new GameListener(this);
		this.puzzleFrame = new PuzzleFrame(this, "Puzzle Game");
		this.highscoreManager = new HighscoreManager(this);
	}

	/**
	 * Checks whether the current puzzle is solvable.
	 *
	 * @return <code>true</code> if it is, otherwise <code>false</code>
	 */
	public boolean isSolvable() {
		//Checks if inversion count is even, in order for the puzzle to be solvable.
		return (calculateInversions() % 2 == 0);
	}

	/**
	 * Checks whether the current puzzle has been completed.
	 *
	 * @return <code>true</code> if it has, otherwise <code>false</code>
	 */
	public boolean isCompleted() {
		Component[] tiles = puzzleFrame.getGamePanel().getComponents();

		for (int pieceCounter = 0; pieceCounter <= 11; pieceCounter++) {
			Component tile = tiles[pieceCounter];

			if (!tile.getName().equalsIgnoreCase(IMAGE_PREFIX + pieceCounter)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Calculates the number of inversions required to complete the puzzle successfully.
	 *
	 * @return the amount of inversions taken.
	 */
	private int calculateInversions() {
		Component[] tiles = puzzleFrame.getGamePanel().getComponents();

		int inversionCount = 0;

		//We remove one, as we need space for our empty piece.
		//Iterate through every puzzle piece
		for (int currentCounter = 0; (currentCounter < 12 - 1); currentCounter++) {
			//Iterate through each puzzle piece for every puzzle piece, starting after the empty piece.
			for (int comparisonCounter = (currentCounter + 1); comparisonCounter < 12; comparisonCounter++) {
				JButton tile = (JButton) tiles[currentCounter];
				JButton comparedTile = (JButton) tiles[comparisonCounter];

				if (getValueFromPieceName(tile.getName()) > getValueFromPieceName(comparedTile.getName())) {
					inversionCount++;
				}
			}
		}
		return inversionCount;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public int getValueFromPieceName(String name) {
		return Integer.parseInt(name.replace("bart", ""));
	}

	public PuzzleFrame getPuzzleFrame() {
		return this.puzzleFrame;
	}

	public int getPuzzleHeight() {
		return puzzleHeight;
	}

	public int getPuzzleWidth() {
		return puzzleWidth;
	}

	public HighscoreManager getHighscoreManager() {
		return highscoreManager;
	}

	public GameListener getGameListener() {
		return gameListener;
	}

	public int getCurrentScore() {
		return currentScore;
	}

}
