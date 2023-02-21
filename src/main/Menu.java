package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

	private Button[] buttons = new Button[3];
	private transient JLabel title = new JLabel();
	private transient Image background;

	public Menu() {

		SpriteGame.getSpriteGame().setGame(new Game());
		setLayout(null);
		setSize(SpriteGame.getSpriteGame().getWidth(), SpriteGame.getSpriteGame().getHeight());
		try {
			background = ImageIO.read(new File("images\\crab_lg1024.PNG"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		title.setText("SpcaeRunner");
		title.setForeground(this.getBackground());
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setBounds((getWidth() / 2) - (getWidth() / 3), getHeight() / 50, 350, 75);
		add(title);
		buttons[0] = new Button("New Game");
		buttons[0].setBounds(getWidth() / 2 - (getWidth() / 4) / 2, getHeight() / 4, 100, 50);
		buttons[0].setBackground(Color.MAGENTA);
		add(buttons[0]);
		buttons[1] = new Button("Load Game");
		buttons[1].setBounds(getWidth() / 2 - (getWidth() / 4) / 2, getHeight() / 4 * 2, 100, 50);
		buttons[1].setBackground(Color.MAGENTA);
		add(buttons[1]);
		buttons[2] = new Button("Exit");
		buttons[2].setBounds(getWidth() / 2 - (getWidth() / 4) / 2, getHeight() / 4 * 3, 100, 50);
		buttons[2].setBackground(Color.MAGENTA);
		add(buttons[2]);
		buttons[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SpriteGame.getSpriteGame().getGame().setTime(new Time());
				SpriteGame.getSpriteGame().getGame().setLevel(new Level("level 1", 4));
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

			}
		});
		buttons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SpriteGame.getSpriteGame().getGame().setTime(new Time());
				SpriteGame.getSpriteGame().getGame().setLevel(new Level("level 1", 4));
				try {
					ScoreSaver.getScoreSaver().input();
					Score.setScore((Score) ScoreSaver.getScoreSaver().getIn().readObject());
					ScoreSaver.getScoreSaver().getIn().close();
					ScoreSaver.getScoreSaver().getInFile().close();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				SpriteGame.getSpriteGame().getGame().setVisible(true);
				SpriteGame.getSpriteGame().getGame().repaint();
				SpriteGame.getSpriteGame().getGame().setEnabled(true);
				SpriteGame.getSpriteGame().getGame().gameRender();
				SpriteGame.getSpriteGame().getGame().gameUpdate();
				Menu.this.setVisible(false);

			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, SpriteGame.getSpriteGame().getWidth(), SpriteGame.getSpriteGame().getHeight(),
				null);
		repaint();
	}

}
