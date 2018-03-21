package puzzle.frame;

import puzzle.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Liam on 3/21/2018.
 */
public class PuzzleFrame extends JFrame {

	/**
	 * Constructs a new puzzle.frame.PuzzleFrame.
	 *
	 * @param frameTitle - the specified title of the puzzle.frame.PuzzleFrame.
	 */
	public PuzzleFrame(String frameTitle) {
		setTitle(frameTitle);

		setSize(500, 500);
		setLayout(new GridLayout(3, 4));

		PuzzleFrameListener puzzleFrameListener = new PuzzleFrameListener(this);

		//Adds a WindowListener to listen for when the frame has been closed by the user.
		this.addWindowListener(puzzleFrameListener);

		loadIcons(puzzleFrameListener);

		this.setVisible(true);
	}

	/***
	 * Loads the required puzzle icons.
	 */
	private void loadIcons(PuzzleFrameListener puzzleFrameListener) {

		//Iterate through
		for (int imageCounter = 0; imageCounter <= 11; imageCounter++) {
			//Create new button with an empty text, since we can't hide text easily.
			JButton button = new JButton("");
			//Example: resources/bart0.jpg
			ImageIcon icon = new ImageIcon(Main.IMAGE_DIRECTOY + Main.IMAGE_PREFIX + imageCounter + Main.IMAGE_TYPE);

			//Bind the icon to the button.
			button.setIcon(icon);

			//Sets the event's action name for when an event fires.
			button.setActionCommand(Main.IMAGE_PREFIX + imageCounter + " clicked.");

			button.addActionListener(puzzleFrameListener);

			//Add the button the frame.
			this.add(button);
		}
	}

}
