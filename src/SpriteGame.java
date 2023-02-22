import javax.swing.JFrame;
import java.awt.*;

public class SpriteGame extends JFrame{

	
	private final Menu menu;
	private static SpriteGame spriteGame;

	public Menu getMenu() {
		return menu;
	}

	private SpriteGame(String s) {

		setLayout(null);
		setPreferredSize(new Dimension(400, 700));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(s);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setLocation(200, 40);
		menu = new Menu();
		getContentPane().add(menu);
		pack();
		setVisible(true);
	}

	public static SpriteGame getSpriteGame() {
		return spriteGame;

	}

	public static void main(String[] args) {
		spriteGame = new SpriteGame("Sprite Game");
			init();
	}

	private static void init() {


		//spriteGame.menu.repaint();


	}


}