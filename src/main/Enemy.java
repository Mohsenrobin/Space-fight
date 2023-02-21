package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.imageio.ImageIO;

public class Enemy implements Serializable {

	private double x;
	private final double y;
	private transient Image enemySpaceShipImage;
	private final EnemyBullet[] enemyBullet;
	private int enemyBulletcounter;
	private final Rectangle enemyShape;
	private boolean enemyShoooted;
	private boolean dead;
	private transient Image enemyDeadImage;
	private long deadTime = 0;
	private long shootControlV2;
	private boolean canShooting;
	private double canGoingRight;
	private double canGoingLeft;
	private String kindOfEnemy;
	private int enemyHealth;

	public Enemy(double x, double y, String kindOfEnemy) {
		this.enemyBulletcounter = 0;
		this.setKindOfEnemy(kindOfEnemy);
		if (Objects.equals(kindOfEnemy, "Red"))
			this.enemyHealth = 2;
		else
			this.enemyHealth = 1;
		enemyShape = new Rectangle();
		canShooting = true;
		enemyShoooted = false;
		enemyBullet = new EnemyBullet[50];
		try {
			if (Objects.equals(kindOfEnemy, "Yellow"))
				enemySpaceShipImage = ImageIO.read(new File("images\\YellowPlane.PNG")).getScaledInstance(
						SpriteGame.getSpriteGame().getWidth() / 10, SpriteGame.getSpriteGame().getWidth() / 10,
						Image.SCALE_AREA_AVERAGING);
			else
				enemySpaceShipImage = ImageIO.read(new File("images\\RedPlane.PNG")).getScaledInstance(
						SpriteGame.getSpriteGame().getWidth() / 10, SpriteGame.getSpriteGame().getWidth() / 10,
						Image.SCALE_AREA_AVERAGING);
			enemyDeadImage = ImageIO.read(new File("images\\boom2.PNG")).getScaledInstance(
					SpriteGame.getSpriteGame().getWidth() / 10, SpriteGame.getSpriteGame().getWidth() / 10,
					Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {

		if (this.isDead()) {
			if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getDeadTime() <= 1000) {
				g.drawImage(enemyDeadImage, (int) this.getX(), (int) this.getY(), enemyDeadImage.getWidth(null),
						enemyDeadImage.getHeight(null), null);
			}
		} else {
			if (isEnemyShoooted()) {
				int value = (int) (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()
						- getShootControlV2());
				if (value <= 40)
					g.drawImage(enemySpaceShipImage, (int) this.getX(), (int) this.getY() - 3,
							enemySpaceShipImage.getWidth(null), enemySpaceShipImage.getHeight(null), null);
				else if (value <= 80)
					g.drawImage(enemySpaceShipImage, (int) this.getX(), (int) this.getY() - 6,
							enemySpaceShipImage.getWidth(null), enemySpaceShipImage.getHeight(null), null);
				else if (value <= 120)
					g.drawImage(enemySpaceShipImage, (int) this.getX(), (int) this.getY() - 9,
							enemySpaceShipImage.getWidth(null), enemySpaceShipImage.getHeight(null), null);
				else if (value <= 160)
					g.drawImage(enemySpaceShipImage, (int) this.getX(), (int) this.getY() - 6,
							enemySpaceShipImage.getWidth(null), enemySpaceShipImage.getHeight(null), null);
				else if (value <= 200)
					g.drawImage(enemySpaceShipImage, (int) this.getX(), (int) this.getY() - 3,
							enemySpaceShipImage.getWidth(null), enemySpaceShipImage.getHeight(null), null);

				if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() > 200) {
					g.drawImage(enemySpaceShipImage, (int) this.getX(), (int) this.getY(),
							enemySpaceShipImage.getWidth(null), enemySpaceShipImage.getHeight(null), null);
					setEnemyShoooted(false);
				}
			} else
				g.drawImage(enemySpaceShipImage, (int) this.getX(), (int) this.getY(),
						enemySpaceShipImage.getWidth(null), enemySpaceShipImage.getHeight(null), null);
		}
	}

	public void shoot() {
		setEnemyShoooted(true);
		if (!this.isDead() && isCanShooting()) {
			if (enemyBulletcounter == enemyBullet.length - 1)
				enemyBulletcounter = 0;
			enemyBulletcounter++;
			if (enemyBullet[enemyBulletcounter] != null) {
				enemyBullet[enemyBulletcounter].setX(this.getX());
				enemyBullet[enemyBulletcounter].setY(this.getY());
			}

			//for (int i = enemyBulletcounter; i <= enemyBullet.length;) {
				enemyBullet[enemyBulletcounter] = new EnemyBullet(
						(int) this.getX() + (enemySpaceShipImage.getWidth(null) / 2) - 5,
						(int) this.getY() + enemySpaceShipImage.getHeight(null), 10, 20);
			//	break;
			//}
			setShootControlV2(SpriteGame.getSpriteGame().getGame().getTimeCounter());
			setShootControlV2(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime());
			System.out.println(SpriteGame.getSpriteGame().getGame().getTimeCounter() + "sadadasdasdads");
			System.out.println(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() + "rhjretyerty");
		}
	}

	public void moveleft() {
		setX(getX() - 8);
	}

	public void moveRight() {
		setX(getX() + 8);
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
	public Rectangle getBounds() {
		enemyShape.setBounds((int) this.getX(), (int) this.getY(), enemySpaceShipImage.getWidth(null),
				enemySpaceShipImage.getHeight(null));
		return enemyShape;
	}

	public EnemyBullet[] getEnemyBullet() {
		return enemyBullet;
	}
	public void update() {
		if (!this.isDead()) {
			if (Math.random() > 0.99 && this.getX() > 0) {
				if (getCanGoingLeft() < (SpriteGame.getSpriteGame().getWidth()
						/(float) ((SpriteGame.getSpriteGame().getGame().getLevel().getNumberOfEnemies() + 1) / 2))) {
					moveleft();
					setCanGoingLeft(getCanGoingLeft() + getX());
				}
			}
			if (Math.random() < 0.01 && this.getX() < SpriteGame.getSpriteGame().getWidth()) {
				if (getCanGoingRight() < (SpriteGame.getSpriteGame().getWidth()
						/(float) ((SpriteGame.getSpriteGame().getGame().getLevel().getNumberOfEnemies() + 1) / 2))) {
					moveRight();
					setCanGoingRight(getCanGoingRight() + getX());
				}
			}
			if (0.99 > Math.random() && Math.random() > 0.98) {
				int waitTime;
				if (Objects.equals(this.kindOfEnemy, "Red"))
					waitTime = 2500;
				else
					waitTime = 5000;
				if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() > waitTime) {
					setCanShooting(true);
					shoot();
					setCanShooting(false);
				}
			}
		} else if (getDeadTime() == 0) {
			setDeadTime(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime());
			// System.out.println(getDeadTime() + " enemy/update");
		}
		for (EnemyBullet bullet : enemyBullet)
			if (bullet != null)
				bullet.update();

	}
	public boolean isEnemyShoooted() {
		return enemyShoooted;
	}

	public void setEnemyShoooted(boolean enemyShoooted) {
		this.enemyShoooted = enemyShoooted;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public long getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(long deadTime) {
		this.deadTime = deadTime;
	}

	public boolean isCanShooting() {
		return canShooting;
	}

	public void setCanShooting(boolean canShooting) {
		this.canShooting = canShooting;
	}

	public double getCanGoingRight() {
		return canGoingRight;
	}

	public void setCanGoingRight(double canGoingRight) {
		this.canGoingRight = canGoingRight;
	}

	public double getCanGoingLeft() {
		return canGoingLeft;
	}

	public void setCanGoingLeft(double canGoingLeft) {
		this.canGoingLeft = canGoingLeft;
	}

	public String getKindOfEnemy() {
		return kindOfEnemy;
	}

	public void setKindOfEnemy(String kindOfEnemy) {
		this.kindOfEnemy = kindOfEnemy;
	}

	public int getEnemyHealth() {
		return enemyHealth;
	}

	public void setEnemyHealth(int enemyHealth) {
		this.enemyHealth = enemyHealth;
	}
	public long getShootControlV2() {
		return shootControlV2;
	}

	public void setShootControlV2(long shootControlV2) {
		this.shootControlV2 = shootControlV2;
	}
}
