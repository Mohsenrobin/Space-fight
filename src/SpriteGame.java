import javax.swing.JFrame;
import java.awt.*;

public class SpriteGame extends JFrame{

	// Declare a Menu object variable
	private final Menu menu;

	// Declare a static SpriteGame object variable
	private static SpriteGame spriteGame;

	// Getter method for the menu object
	public Menu getMenu() {
		return menu;
	}

	// Private constructor for the SpriteGame class
	private SpriteGame(String s) {

		// Set the preferred size of the frame
		setPreferredSize(new Dimension(400, 700));

		// Set the visibility of the frame to true
		setVisible(true);

		// Disable the ability to resize the frame
		setResizable(false);

		// Set the default close operation to exit the application
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the title of the frame
		setTitle(s);

		// Set the frame to always be on top of other windows
		setAlwaysOnTop(true);

		// Center the frame on the screen
		setLocationRelativeTo(null);

		// Set the location of the frame to (200, 40) on the screen
		setLocation(200, 40);

		// Initialize the Menu object and add it to the content pane of the frame
		menu = new Menu();
		getContentPane().add(menu);

		// Pack the frame to fit the content and set the visibility to true
		pack();
		setVisible(true);
	}

	// Getter method for the static SpriteGame object
	public static SpriteGame getSpriteGame() {
		return spriteGame;
	}

	// Main method for the application
	public static void main(String[] args) {
		// Create a new instance of the SpriteGame class with the title "Sprite Game"
		spriteGame = new SpriteGame("Sprite Game");
	}
}
