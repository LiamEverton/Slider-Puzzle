package puzzle;

import puzzle.game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PuzzleFrame, used to hold the contents of the game's GUI.
 *
 * @author Liam
 */
public class PuzzleFrame extends JFrame {

	private final Puzzle puzzle;

	private final GamePanel gamePanel;

	private JLabel scoreLabel;

	/**
	 * Constructs a new puzzle.PuzzleFrame.
	 *
	 * @param puzzle     - the specified Puzzle game which this PuzzleFrame is associated with.
	 * @param frameTitle - the specified title of this PuzzleFrame.
	 */
	public PuzzleFrame(Puzzle puzzle, String frameTitle) {
		this.puzzle = puzzle;
		this.gamePanel = new GamePanel(puzzle);

		setTitle(frameTitle);
		setSize(465, 475);
		setLayout(new BorderLayout());

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane);

		splitPane.setTopComponent(gamePanel);
		splitPane.setBottomComponent(createButtonPanel());

		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Creates a new panel which contains the buttons such randomize and the score label.
	 *
	 * @return jPanel - the populated JPanel.
	 */
	private JPanel createButtonPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());

		JButton randomizeButton = new JButton("Randomize");

		//Adds the randomize button action listener.
		randomizeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do {
					gamePanel.randomizeTiles();
					System.out.println(puzzle.isSolvable());
				}
				while (!puzzle.isSolvable());
			}
		});

		this.scoreLabel = new JLabel();
		scoreLabel.setText("Score: " + puzzle.getCurrentScore());

		jPanel.add(randomizeButton, BorderLayout.EAST);
		jPanel.add(scoreLabel, BorderLayout.WEST);

		return jPanel;
	}

	/**
	 * Gets the associated GamePanel for this PuzzleFrame.
	 *
	 * @return gamePanel - the associated GamePanel.
	 */
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

	/**
	 * Gets the associated score label for this PuzzleFrame.
	 *
	 * @return scoreLabel - the associated score label.
	 */
	public JLabel getScoreLabel() {
		return this.scoreLabel;
	}
}
