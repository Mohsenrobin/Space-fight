import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Menu extends JPanel {

	private Image background;
	private Game game;


	public Menu() {

		game = new Game();
		setLayout(null);
		setSize(400, 700);
		try {
			background = ImageIO.read(new File("images\\tom-barrett-hgGplX3PFBg-unsplash.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JLabel title = new JLabel();
		title.setText("Space Runner");
		title.setForeground(this.getBackground());
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setBounds((getWidth() / 10) , getHeight() / 7, 350, 75);
		add(title);
		JButton[] buttons = new JButton[2];
		buttons[0] = new JButton("New Game");
		buttons[0].setFont(new Font("Serif", Font.BOLD, 21));
		buttons[0].setBounds(getWidth() / 2 - 100, getHeight() / 3, 200, 50);
		buttons[0].setBackground(Color.WHITE);
		buttons[0].setBorder(new LineBorder(Color.BLACK, 2));
		add(buttons[0]);
		buttons[1] = new JButton("Exit");
		buttons[1].setBounds(getWidth() / 2 - 100, getHeight() / 2 , 200, 50);
		buttons[1].setBackground(Color.WHITE);
		buttons[1].setBorder(new LineBorder(Color.BLACK, 2));
		buttons[1].setFont(new Font("Serif", Font.BOLD, 21));

		add(buttons[1]);
		buttons[0].addActionListener(e -> {
			SpriteGame.getSpriteGame().getContentPane().add(game);
			getGame().setTime(new Time());
			getGame().setLevel(new Level(4));
			Path saveData = Paths.get("save.txt");
			String data = "1";
			if (Files.exists(saveData)) {
				try {
					File myObj = new File("save.txt");
					Scanner myReader = new Scanner(myObj);
					while (myReader.hasNextLine()) {
						data = myReader.nextLine();
						System.out.println(data);
					}
					myReader.close();
					Score.getScore().setHighScore(Integer.valueOf(data));
				} catch (FileNotFoundException ee) {
					System.out.println("An error occurred.");
					ee.printStackTrace();
				}
			}
			Menu.this.setVisible(false);
			game.setVisible(true);
			game.repaint();
			game.setEnabled(true);
			game.gameRender();
			game.gameUpdate();

		});
		buttons[1].addActionListener(e -> System.exit(1));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, SpriteGame.getSpriteGame().getWidth(), SpriteGame.getSpriteGame().getHeight(),
				null);
		repaint();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
