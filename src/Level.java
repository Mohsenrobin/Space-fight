import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class Level {

	private final Player player;
	private final Enemy[] enemy;
	private Image backgroundImage;
	private int scale;
	private final int numberOfEnemies;
	private int deadEnemies;
	private final String imageLocation;

	public Level(int numberOfEnemies) {
		imageLocation = "images\\ferenc-horvath-skcFiBu91AA-unsplash.jpg";
		player = new Player((SpriteGame.getSpriteGame().getWidth() / 2.0) - 40,
				SpriteGame.getSpriteGame().getHeight() - 150);
		this.scale = 1;
		this.numberOfEnemies = numberOfEnemies;
		enemy = new Enemy[numberOfEnemies];
		for (int i = 0; i < enemy.length; i++, scale++) {
			if (scale > 4)
				scale = 1;
			int enemyYPosition;
			int enemyXPosition;
			if (i - 4 < 0) {
				enemyYPosition = 100;
			} else {
				enemyYPosition = 140;
			}
			enemyXPosition = (SpriteGame.getSpriteGame().getWidth() / (numberOfEnemies + 1) * scale) - 20;
			enemy[i] = new Enemy(enemyXPosition, enemyYPosition, "Yellow");
		}
	}

	public void draw(Graphics g) {
		setImageLocation(imageLocation);
		g.drawImage(backgroundImage, 0, 0, SpriteGame.getSpriteGame().getWidth(),
				SpriteGame.getSpriteGame().getHeight(), null);

		for (Enemy value : enemy) {
			if (value != null) {
				for (int j = 0; j < value.getBullets().length; j++) {
					value.draw(g);
					// System.out.println(i + "Updated");
					if (value.getBullets()[j] != null
							&& value.getBullets()[j].getY() < SpriteGame.getSpriteGame().getHeight())
						value.getBullets()[j].draw(g);
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
				if (enemy[i].isDeadShooter()) {
					if (SpriteGame.getSpriteGame().getMenu().getGame().getTime().getCurrentTime()
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
							((SpriteGame.getSpriteGame().getWidth() / (float) (this.getNumberOfEnemies() + 1) * scale) - 20),
							100, "Yellow");
				else
					enemy[i] = new Enemy(
							((SpriteGame.getSpriteGame().getWidth() /(float) (this.getNumberOfEnemies() + 1) * scale) - 20),
							100, "Red");
				scale++;
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

	public int getNumberOfEnemies() {
		return numberOfEnemies;
	}

	public int getDeadEnemies() {
		return deadEnemies;
	}

	public void setDeadEnemies(int deadEnemies) {
		this.deadEnemies = deadEnemies;
	}

}
