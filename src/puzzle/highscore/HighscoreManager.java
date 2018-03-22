package puzzle.highscore;

import puzzle.Puzzle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HighscoreManager {

	//The file name where to store the high score.
	public static final String FILE_NAME = "highscores.txt";

	//The owning Puzzle
	private final Puzzle puzzle;

	private HighscoreFrame highscoreFrame;

	//Maps the user's name and their score.
	private final Map<String, Integer> nameToScore = new HashMap<>();

	/**
	 * Constructs a new HighscoreManager.
	 *
	 * @param puzzle - the owning Puzzle for this HighscoreManager.
	 */
	public HighscoreManager(Puzzle puzzle) {
		this.puzzle = puzzle;
		this.loadScores();
	}

	/**
	 * Loads the scores which are stored within the highscores file.
	 */
	public void loadScores() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));

			String line = null;
			try {
				while ((line = bufferedReader.readLine()) != null) {
					String[] nameAndScore = line.split(":");

					if (nameAndScore.length > 0) {
						nameToScore.put(nameAndScore[0], Integer.valueOf(nameAndScore[1]));
					}
				}

				bufferedReader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) {
			try {
				new File(FILE_NAME).createNewFile();
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Writes the stored scores back into the highscores file.
	 */
	public void saveScores() {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));

			for (String name : nameToScore.keySet()) {
				int score = nameToScore.get(name);

				bufferedWriter.write(name + ":" + score + System.lineSeparator());
			}

			bufferedWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setHighscoreFrame(HighscoreFrame highscoreFrame) {
		this.highscoreFrame = highscoreFrame;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public HighscoreFrame getHighscoreFrame() {
		return highscoreFrame;
	}

	public Map<String, Integer> getNameToScore() {
		return this.nameToScore;
	}
}
