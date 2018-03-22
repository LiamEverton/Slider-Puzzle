package puzzle.game;

import puzzle.Puzzle;
import puzzle.type.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

	//The owning Puzzle responsible for this GamePanel.
	private final Puzzle puzzle;

	/**
	 * Constructs a new PuzzleFrame.
	 *
	 * @param puzzle - the owning Puzzle, responsible for this GamePanel.
	 */
	public GamePanel(Puzzle puzzle) {
		this.puzzle = puzzle;

		setSize(465, 400);
		setLayout(new GridLayout(3, 4));

		//Loads the button's icons to represent the puzzle pieces.
		loadIcons();

		this.setVisible(true);
	}

	/**
	 * Randomizes the Puzzle's tiles.
	 */
	public void randomizeTiles() {
		Random random = new Random();

		Component[] puzzlePieces = getComponents();

		for (Component component : puzzlePieces) {
			swapTiles((JButton) component, (JButton) puzzlePieces[random.nextInt(puzzlePieces.length - 1)]);
		}
	}

	/**
	 * Moves the specified tile with the adjacent empty tile, if there is one.
	 *
	 * @param puzzlePiece - the specified JButton (puzzle piece) to attempt to move.
	 *
	 * @return <code>true</code> if successful, <code>false</code> if not.
	 */
	public boolean moveTile(JButton puzzlePiece) {

		int currentIndex = this.getPuzzlePieceIndex(puzzlePiece);

		//Convert number into coordinates.
		int pieceX = currentIndex / puzzle.getPuzzleWidth();
		int pieceY = currentIndex - (pieceX * puzzle.getPuzzleWidth());

		//Iterate over each possible direction to get the adjacent blank tile if it exists.
		for (Direction direction : Direction.values()) {
			//Get the piece that is in the direction relative to the original piece.
			int comparedPieceX = pieceX + direction.getOffsetX();
			int comparedPieceY = pieceY + direction.getOffsetY();

			//Check if the comparison will go off the board.
			if ((comparedPieceX >= 0) && comparedPieceX < puzzle.getPuzzleHeight()) {
				if ((comparedPieceY >= 0) && comparedPieceY < puzzle.getPuzzleWidth()) {
					//Get the puzzle piece's index.
					int comparedIndex = this.getPuzzlePieceIndex(comparedPieceX, comparedPieceY);

					//Get the component from the piece's index.
					Component component = this.getComponents()[comparedIndex];

					//Cast component to JButton
					JButton comparedPiece = (JButton) component;

					//Check if the piece we are moving to is the empty piece.
					if (comparedPiece.getName().equalsIgnoreCase("bart0")) {
						this.swapTiles(puzzlePiece, comparedPiece);
						return true;
					}
				}
			}

		}
		return false;
	}

	/***
	 * Loads the required puzzle icons.
	 */
	private void loadIcons() {

		//Iterate through
		for (int imageCounter = 0; imageCounter <= 11; imageCounter++) {
			//Create new button with an empty text, since we can't hide text easily.
			JButton button = new JButton("");
			//Example: resources/bart0.jpg
			ImageIcon icon = new ImageIcon(Puzzle.IMAGE_DIRECTORY + Puzzle.IMAGE_PREFIX + imageCounter + Puzzle.IMAGE_TYPE);

			button.setName(Puzzle.IMAGE_PREFIX + imageCounter);
			//Bind the icon to the button.
			button.setIcon(icon);

			//Sets the event's action name for when an event fires, to identify each different button.
			button.setActionCommand(Puzzle.IMAGE_PREFIX + imageCounter + " clicked.");

			//Add the same listener for each button to allow for moving functionality.
			button.addActionListener(puzzle.getGameListener());

			//Add the button the game.
			this.add(button);
		}
	}

	/**
	 * Swaps the two specified pieces around with each other.
	 *
	 * @param oldPiece     - the specified JButton to move.
	 * @param updatedPiece = the specified JButton to be moved.
	 */
	private void swapTiles(JButton oldPiece, JButton updatedPiece) {
		Component[] tiles = getComponents();

		int firstTileIndex = getPuzzlePieceIndex(oldPiece);
		int secondTileIndex = getPuzzlePieceIndex(updatedPiece);

		String tileName1 = tiles[firstTileIndex].getName();
		Icon tileImage1 = ((JButton) tiles[firstTileIndex]).getIcon();

		String tileName2 = tiles[secondTileIndex].getName();
		Icon tileImage2 = ((JButton) tiles[secondTileIndex]).getIcon();

		tiles[firstTileIndex].setName(tileName2);
		((JButton) tiles[firstTileIndex]).setIcon(tileImage2);

		tiles[secondTileIndex].setName(tileName1);
		((JButton) tiles[secondTileIndex]).setIcon(tileImage1);
	}

	/**
	 * Calculates the one dimensional index from the specified two dimensional coordinates.
	 *
	 * @param x - the specified X coordinate.
	 * @param y - the specified Y coordinate.
	 *
	 * @return the one dimensional index.
	 */
	public int getPuzzlePieceIndex(int x, int y) {
		return puzzle.getPuzzleWidth() * x + y;
	}

	/**
	 * Retrieves the one dimensional index from the specified puzzle piece.
	 *
	 * @param puzzlePiece - the specified Component (puzzle piece)
	 *
	 * @return the one dimensional index.
	 */
	public int getPuzzlePieceIndex(Component puzzlePiece) {
		Component[] otherPieces = getComponents();

		for (int i = 0; i < otherPieces.length; i++) {
			if (otherPieces[i].equals(puzzlePiece)) {
				return i;
			}
		}

		return -1;
	}
}
