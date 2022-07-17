package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Level implements Serializable {

	private final Player player;
	private final Enemy[] enemy;
	private transient Image backgroundImage;
	private int scale;
	private int numberOfenemies;
	private int deadEnemies;
	private final String imageLocation;

	public Level(String name, int numberOfenemies) {
		imageLocation = "images\\space.JPG";
		player = new Player((SpriteGame.getSpriteGame().getWidth() / 2) - 40,
				SpriteGame.getSpriteGame().getHeight() - 150);
		this.scale = 1;
		this.numberOfenemies = numberOfenemies;
		enemy = new Enemy[numberOfenemies];
		for (int i = 0; i < enemy.length; i++, scale++) {
			if (scale > 4)
				scale = 1;
			int enemyYposition;
			int enemyXposition;
			if (i - 4 < 0) {
				enemyYposition = 100;
				enemyXposition = (SpriteGame.getSpriteGame().getWidth() / (numberOfenemies + 1) * scale) - 20;
			} else {
				enemyYposition = 140;
				enemyXposition = (SpriteGame.getSpriteGame().getWidth() / (numberOfenemies + 1) * scale) - 20;
			}
			enemy[i] = new Enemy(enemyXposition, enemyYposition, "Yellow");
		}
	}

	public void draw(Graphics g) {
		setImageLocation(imageLocation);
		g.drawImage(backgroundImage, 0, 0, SpriteGame.getSpriteGame().getWidth(),
				SpriteGame.getSpriteGame().getHeight(), null);

		for (Enemy value : enemy) {
			if (value != null) {
				for (int j = 0; j < value.getEnemyBullet().length; j++) {
					value.draw(g);
					// System.out.println(i + "Updated");
					if (value.getEnemyBullet()[j] != null
							&& value.getEnemyBullet()[j].getY() < SpriteGame.getSpriteGame().getHeight())
						value.getEnemyBullet()[j].draw(g);
				}
			}
		}
		player.draw(g);
		for (int i = 0; i < player.getBullets().length; i++) {
			if (player.getBullets()[i] != null && player.getBullets()[i].getY() > 0) {
				player.getBullets()[i].draw(g);
			}
		}
	}

	public void update() {
		player.update();
		for (int i = 0; i < enemy.length; i++) {
			if (enemy[i] != null) {
				enemy[i].update();
				if (enemy[i].isDead()) {
					if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()
							- enemy[i].getDeadTime() > 1001) {
						enemy[i] = null;
						setDeadEnemies(getDeadEnemies() + 1);
					}
				}

			} else if (getDeadEnemies() % 4 == 0) {

				if (scale > 4)
					scale = 1;
				if (getDeadEnemies() <= 4)
					enemy[i] = new Enemy(
							((SpriteGame.getSpriteGame().getWidth() / (this.getNumberOfenemies() + 1) * scale) - 20),
							100, "Yellow");
				else
					enemy[i] = new Enemy(
							((SpriteGame.getSpriteGame().getWidth() / (this.getNumberOfenemies() + 1) * scale) - 20),
							100, "Red");
				scale++;
				System.out.println(i + " created again         level/update   " + numberOfenemies + scale);
			}
		}
	}

	public void setImageLocation(String imageLocation) {
		try {
			backgroundImage = ImageIO.read(new File(imageLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Player getPlayer() {
		return player;
	}

	public Enemy[] getEnemy() {
		return enemy;
	}

	public int getNumberOfenemies() {
		return numberOfenemies;
	}

	public int getDeadEnemies() {
		return deadEnemies;
	}

	public void setDeadEnemies(int deadEnemies) {
		this.deadEnemies = deadEnemies;
	}

}
