package ai.puzzle;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * 
 * @author SONY VAIO
 */
public class PuzzlePanel extends JPanel implements MouseListener, Runnable {
	public final int SQUARE_SIZE = 100;
	public final int SPACE_SIZE = 5;

	public final int A_SEARCH = 1;
	public final int ITERATIVE_DEEP_SEARCNH = 2;

	public final int STATE_FREE = 0;
	public final int STATE_ENTER_PUZZLE = 1;
	public final int STATE_SHOW_SOLUTION = 2;

	public int height;
	public int width;

	private String image_name[] = { "u1.jpg", "u2.jpg", "u3.jpg", "u4.jpg",
			"u5.jpg", "u6.jpg", "u7.jpg", "u8.jpg" };
	private Image image[] = null;
	private Puzzle puzzle = null;
	private Stack<Puzzle> stack = null;
	private int game_state;
	private int setvalue;
	private int methodsearch = A_SEARCH;

	private JTextArea displayTxtAra = null;

	public PuzzlePanel() {
		image = new Image[image_name.length];
		for (int i = 0; i < image_name.length; i++)
			image[i] = Toolkit.getDefaultToolkit().getImage(
					getClass().getResource(image_name[i]));
		puzzle = new Puzzle();
		game_state = STATE_FREE;
		stack = new Stack<Puzzle>();
		height = width = puzzle.getSize() * (SQUARE_SIZE + SPACE_SIZE)
				- SPACE_SIZE + 1;
		this.setSize(width, height);
		this.addMouseListener(this);
	}

	public void Initial(JTextArea TxtAra) {
		this.displayTxtAra = TxtAra;
	}

	public void EnterPuzzle() {
		game_state = STATE_ENTER_PUZZLE;
		setvalue = 1;

		stack.add(puzzle);
		puzzle = new Puzzle();
		for (int i = 0; i < puzzle.getSize(); i++)
			for (int j = 0; j < puzzle.getSize(); j++)
				puzzle.setValue(j, i, 0);
		this.repaint();
		displayTxtAra.setText("!!Create new puzzle!!\nClick to put 1");
	}

	public void RandomPuzzle() {
		if (game_state != STATE_FREE && game_state != STATE_ENTER_PUZZLE) {
			Toolkit.getDefaultToolkit().beep();
			displayTxtAra.setText(displayTxtAra.getText()
					+ "\n!!Please wait for me!!");
			return;
		}
		stack.add(puzzle);
		puzzle = new Puzzle();
		game_state = STATE_FREE;
		this.repaint();
		displayTxtAra.setText(displayTxtAra.getText() + "\n!!Random puzzle!!");
	}

	public void SavePuzzle() {
		if (game_state != STATE_FREE) {
			Toolkit.getDefaultToolkit().beep();
			displayTxtAra.setText(displayTxtAra.getText()
					+ "\n!!Please wait for me!!");
			return;
		}
		Puzzle newpuzzle = new Puzzle(puzzle.getPuzzle());
		stack.add(newpuzzle);
		displayTxtAra.setText(displayTxtAra.getText() + "\n!!Saved puzzle!!");
	}

	public void RestorePuzzle() {
		if (game_state != STATE_FREE) {
			Toolkit.getDefaultToolkit().beep();
			displayTxtAra.setText(displayTxtAra.getText()
					+ "\n!!Please wait for me!!");
			return;
		}
		if (!stack.isEmpty()) {
			displayTxtAra.setText(displayTxtAra.getText()
					+ "\n!!Restore last puzzle!!");
			puzzle = stack.pop();
		} else {
			displayTxtAra.setText(displayTxtAra.getText()
					+ "\n!!No saved puzzle!!");
			Toolkit.getDefaultToolkit().beep();
		}
		this.repaint();
	}

	public void solve(int method) {
		if (game_state != STATE_FREE) {
			Toolkit.getDefaultToolkit().beep();
			displayTxtAra.setText(displayTxtAra.getText()
					+ "\n!!Please wait for me!!");
			return;
		}
		game_state = STATE_SHOW_SOLUTION;
		this.methodsearch = method;
		Thread thread = new Thread(this);
		thread.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < puzzle.getSize(); i++)
			for (int j = 0; j < puzzle.getSize(); j++) {
				int x = j * (SQUARE_SIZE + SPACE_SIZE);
				int y = i * (SQUARE_SIZE + SPACE_SIZE);
				if (puzzle.getValue(j, i) != 0) {
					// g.setColor(new Color(0xff, 0xcc, 0x66));
					// g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
					g.drawImage(image[puzzle.getValue(j, i) - 1], x, y,
							SQUARE_SIZE, SQUARE_SIZE, null);
					g.setColor(new Color(0x33, 0xff, 0x66));
					g.setFont(new Font("Viner Hand ITC",
							Font.BOLD | Font.PLAIN, 50));
					g.drawString("" + puzzle.getValue(j, i), x + 40, y + 60);
					g.setColor(Color.black);
					g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
				} else {
					g.setColor(Color.WHITE);
					g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
					g.setColor(Color.black);
					g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
				}
			}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX() / (SQUARE_SIZE + SPACE_SIZE);
		int y = e.getY() / (SQUARE_SIZE + SPACE_SIZE);
		if (x >= puzzle.getSize() || y >= puzzle.getSize())
			return;
		if (game_state == STATE_FREE) {
			Location Floc = puzzle.getPosition(0);
			Location Sloc = new Location(x, y);
			if (puzzle.getDistance(Floc, Sloc) == 1) {
				puzzle.setValue(Floc.x, Floc.y, puzzle.getValue(x, y));
				puzzle.setValue(x, y, 0);
				this.repaint();
			}
		} else if (game_state == STATE_ENTER_PUZZLE
				&& puzzle.getValue(x, y) == 0) {
			puzzle.setValue(x, y, setvalue++);
			this.repaint();
			if (setvalue > 8) {
				game_state = STATE_FREE;
				displayTxtAra
						.setText(displayTxtAra.getText() + "\n!!Finish!!!");
				return;
			}
			displayTxtAra.setText(displayTxtAra.getText() + "\nClick to put "
					+ setvalue);
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent e) {
		this.setCursor(Cursor.getDefaultCursor());
	}

	public void run() {
		String Text = "";
		if (methodsearch == A_SEARCH) {
			Text = "A* Search\n";
			puzzle.ASearch();
		} else {
			Text = "Iterative\n";
			puzzle.IterativeDeepingSearch();
		}
		Text += String.format("Solved Problem: %d\n", puzzle.queue.size());
		Text += String.format("Problem:\n%s\n", puzzle.arrToString());
		displayTxtAra.setText(Text);
		for (int i = 1; !puzzle.queue.isEmpty(); i++) {
			puzzle.moveBySolution();
			Text += String.format("STEP: %2d\n%s\n", i, puzzle.arrToString());
			displayTxtAra.setText(Text);
			repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException iEx) {
			}
		}
		game_state = STATE_FREE;
	}
}
