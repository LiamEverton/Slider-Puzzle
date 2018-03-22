package puzzle.game;

import puzzle.Puzzle;
import puzzle.highscore.HighscoreFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameListener implements ActionListener {

	//The owning Puzzle.
	private final Puzzle puzzle;

	/**
	 * Constructs a new GameListener.
	 *
	 * @param puzzle - the owning Puzzle.
	 */
	public GameListener(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	/**
	 * Handles when a puzzle piece has been clicked.
	 *
	 * @param event - the associated ActionEvent.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();

		//Check if it is a puzzle piece
		if (event.getSource() instanceof JButton) {
			if (!event.getActionCommand().equalsIgnoreCase("bart0")) {
				//It's a puzzle piece click event.
				if (actionCommand.contains(Puzzle.IMAGE_PREFIX)) {
					//We can safely assume and cast to a JButton.
					JButton clickedButton = (JButton) event.getSource();

					//Move the Tile.
					if (puzzle.getPuzzleFrame().getGamePanel().moveTile(clickedButton)) {

						//Sets the score.
						puzzle.setCurrentScore(puzzle.getCurrentScore() + 1);
						//Displays the score.
						puzzle.getPuzzleFrame().getScoreLabel().setText("Score: " + puzzle.getCurrentScore());

						//Check to see if the Player has won.
						if (puzzle.isCompleted()) {

							//Hide the puzzle game, now the game is completed.
							puzzle.getPuzzleFrame().setVisible(false);

							//Loads the highscores and displays them.
							puzzle.getHighscoreManager().loadScores();
							puzzle.getHighscoreManager().setHighscoreFrame(new HighscoreFrame(puzzle.getHighscoreManager(), "Puzzle Highscores"));
						}
					}
				}
			}
		}
	}
}
