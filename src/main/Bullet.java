package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Bullet {

	private double x;
	private double y;
	private final int width;
	private final int height;
	private boolean isHot;
	private final Rectangle bulletShape;
	private boolean enemyDetector;
	private Image bulletImage;

	private boolean hittedToEnemy;
	Bullet(boolean enemyDetecter, int x, int y, int width, int height, boolean isHot) {
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
		this.setHot(isHot);
		hittedToEnemy = false;

		bulletShape = new Rectangle();
		Random random = new Random();
		this.setEnemyDetector(enemyDetecter);
		random.nextInt(SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy().length);
		try {
			bulletImage = ImageIO.read(new File("images\\BlueBullet.png")).getScaledInstance(15, 25,
					Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {

		if (!hittedToEnemy)
			g.drawImage(bulletImage, (int) this.getX(), (int) this.getY(), bulletImage.getWidth(null),
					bulletImage.getHeight(null), null);
	}

	public Rectangle getBounds() {
		bulletShape.setBounds((int) this.getX(), (int) this.getY(), getWidth(), getHeight());
		return bulletShape;
	}

	public void update() {
		if (isHot())
			moveUp();
		for (int i = 0; i < SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy().length; i++) {
			if (SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy()[i] != null) {
				if (this.getBounds()
						.intersects(SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy()[i].getBounds())
						&& !SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy()[i].isDead() && isHot()) {
					setHot(false);
					hittedToEnemy = true;
					SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy()[i].setEnemyHealth(
							SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy()[i].getEnemyHealth() - 1);
					if (SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy()[i].getEnemyHealth() == 0) {
						SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy()[i].setDead(true);
						System.out.println("ENEMY dead");
						if (Objects.equals(SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy()[i].getKindOfEnemy(), "Yellow"))
							Score.getScore().setPlayerScore(Score.getScore().getPlayerScore() + 100);
						else
							Score.getScore().setPlayerScore(Score.getScore().getPlayerScore() + 200);

					}
				}
			}
		}
	}

	public void moveUp() {
		if (this.isEnemyDetector())
			setY(getY() - 2.8);
		else
			setY(getY() - 6.8);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public boolean isEnemyDetector() {
		return enemyDetector;
	}

	public void setEnemyDetector(boolean enemyDetecable) {
		this.enemyDetector = enemyDetecable;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
