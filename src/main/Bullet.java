package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;

public class Bullet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double x;
	private double y;
	private int width;
	private int height;
	private boolean isHot;
	private Color c;
	private Rectangle bulletShape;
	private boolean enemyDetector;
	private Random random;
	private transient Image bulletImage;

	public boolean isHittedToEnemy() {
		return hittedToEnemy;
	}

	public void setHittedToEnemy(boolean hittedToEnemy) {
		this.hittedToEnemy = hittedToEnemy;
	}

	private boolean hittedToEnemy;
	Bullet(boolean enemyDetecter, int x, int y, int width, int height, Color c, boolean isHot) {
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
		this.c = c;
		this.setHot(isHot);
		hittedToEnemy = false;

		bulletShape = new Rectangle();
		random = new Random();
		this.setEnemyDetector(enemyDetecter);
		random.nextInt(SpriteGame.getSpriteGame().getGame().getLevel().getEnemy().length);
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
		for (int i = 0; i < SpriteGame.getSpriteGame().getGame().getLevel().getEnemy().length; i++) {
			if (SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i] != null) {
				if (this.getBounds()
						.intersects(SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i].getBounds())
						&& SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i].isDead() == false && isHot()) {
					setHot(false);
					hittedToEnemy = true;
					SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i].setEnemyHealth(
							SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i].getEnemyHealth() - 1);
					if (SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i].getEnemyHealth() == 0) {
						SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i].setDead(true);
						System.out.println("ENEMY dead");
						if (SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i].getKindOfEnemy() == "Yellow")
							Score.getScore().setplayerScore(Score.getScore().getplayerScore() + 100);
						else
							Score.getScore().setplayerScore(Score.getScore().getplayerScore() + 200);

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

	public void smartBulllet(Enemy enemy) {
		if (this.getX() > enemy.getX() && Math.abs(this.getX() - enemy.getX()) > 20)
			setX(getX() - 2.8);

		else if (this.getX() < enemy.getX() && Math.abs(this.getX() - enemy.getX()) > 20)
			setX(getX() + 2.8);

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

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
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

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
