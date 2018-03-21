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

		this.setVisible(true);
	}

}
