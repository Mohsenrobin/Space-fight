package main;


import javax.swing.JFrame;

public class SpriteGame extends JFrame{

	
	private Menu menu;
	private static SpriteGame spriteGame;
	private Game game;


	private SpriteGame(String s) {

		setLayout(null);
		setSize(400, 700);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(s);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setLocation(200, 40);
		
	}

	public static SpriteGame getSpriteGame() {
		return spriteGame;

	}

	public static void main(String[] args) {
		spriteGame = new SpriteGame("Sprite Game");
			init();
	}

	private static void init() {

		spriteGame.menu = new Menu();
		spriteGame.getContentPane().add(spriteGame.menu);
		spriteGame.menu.repaint();
		spriteGame.getContentPane().add(spriteGame.getGame());
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}