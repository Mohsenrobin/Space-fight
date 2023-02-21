package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Menu extends JPanel {

	private transient Image background;

	public Menu() {

		SpriteGame.getSpriteGame().setGame(new Game());
		setLayout(null);
		setSize(SpriteGame.getSpriteGame().getWidth(), SpriteGame.getSpriteGame().getHeight());
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
		buttons[0].setBounds(getWidth() / 2 - 100, getHeight() / 3, 200, 50);
		buttons[0].setBackground(Color.WHITE);
		buttons[0].setBorder(new LineBorder(Color.BLACK, 2));
		add(buttons[0]);
		buttons[1] = new JButton("Exit");
		buttons[1].setBounds(getWidth() / 2 - 100, getHeight() / 2 , 200, 50);
		buttons[1].setBackground(Color.WHITE);
		buttons[1].setBorder(new LineBorder(Color.BLACK, 2));

		add(buttons[1]);
		buttons[0].addActionListener(e -> {
			SpriteGame.getSpriteGame().getGame().setTime(new Time());
			SpriteGame.getSpriteGame().getGame().setLevel(new Level(4));
			Path saveData = Paths.get("save.txt");
			if (Files.exists(saveData)) {
				try {
					ScoreSaver.getScoreSaver().input();
					Score.setScore((Score) ScoreSaver.getScoreSaver().getIn().readObject());
					ScoreSaver.getScoreSaver().getIn().close();
					ScoreSaver.getScoreSaver().getInFile().close();
				} catch (IOException | ClassNotFoundException e1) {
					Score.setScore(Score.getScore());
					System.out.println("New Score");
				}
			}
			SpriteGame.getSpriteGame().getGame().setVisible(true);
			SpriteGame.getSpriteGame().getGame().repaint();
			SpriteGame.getSpriteGame().getGame().setEnabled(true);
			SpriteGame.getSpriteGame().getGame().gameRender();
			SpriteGame.getSpriteGame().getGame().gameUpdate();
			Menu.this.setVisible(false);

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

}
