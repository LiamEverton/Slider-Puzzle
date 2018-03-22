package puzzle.highscore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class HighscoreFrame extends JFrame {

	private final HighscoreManager highscoreManager;

	/**
	 * Constructs a new puzzle.PuzzleFrame.
	 *
	 * @param frameTitle - the specified title of the puzzle.PuzzleFrame.
	 */
	public HighscoreFrame(HighscoreManager highscoreManager, String frameTitle) {
		this.highscoreManager = highscoreManager;

		setTitle(frameTitle);
		setSize(300, 300);

		getContentPane().setLayout(new GridLayout(10, 1));

		retrieveUsername();

		this.setResizable(false);
		this.setVisible(false);
	}

	/**
	 * Creates the Swing objects to display the scoreboard for this frame.
	 */
	private void createScoreboard() {
		Map<String, Integer> nameToScore = highscoreManager.getNameToScore();

		for (String name : nameToScore.keySet()) {
			int score = nameToScore.get(name);

			JLabel label = new JLabel("Name: " + name + " Score: " + score);

			getContentPane().add(label);
		}
	}

	/**
	 * Retrieves the name of the user by prompting them with a GUI.
	 */
	private void retrieveUsername() {

		JFrame enterNameFrame = new JFrame();
		JPanel namePanel = new JPanel();

		JTextField enterNameField = new JTextField();
		JButton submitButton = new JButton("Submit");

		namePanel.setLayout(new GridLayout(2, 1));
		namePanel.add(enterNameField);
		namePanel.add(submitButton);

		enterNameFrame.setTitle("Enter your name");
		enterNameFrame.setSize(300, 300);
		enterNameFrame.setResizable(false);

		enterNameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		enterNameFrame.add(namePanel);
		enterNameFrame.setVisible(true);

		//Adds an ActionListener in order to see when the submit button has been clicked.
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == submitButton) {
					highscoreManager.getNameToScore().put(enterNameField.getText(), highscoreManager.getPuzzle().getCurrentScore());

					highscoreManager.saveScores();
					createScoreboard();

					highscoreManager.getHighscoreFrame().setVisible(true);

					enterNameFrame.setVisible(false);
					enterNameFrame.dispose();
				}
			}
		});
	}
}
