package puzzle.frame;

import puzzle.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class PuzzleFrameListener implements ActionListener, WindowListener {

	private PuzzleFrame puzzleFrame;

	public PuzzleFrameListener(PuzzleFrame puzzleFrame) {
		this.puzzleFrame = puzzleFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		//Check if it is a puzzle piece
		if (actionCommand.contains(Main.IMAGE_PREFIX)) {
			//Check if it isn't an empty puzzle piece
			if (!actionCommand.contains("0")) {

				Component[] components = puzzleFrame.getComponents();

				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 4; y++) {
					}
				}
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	public PuzzleFrame getPuzzleFrame() {
		return puzzleFrame;
	}
}
