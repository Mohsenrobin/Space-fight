package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Enemy implements Serializable {

	private double x;
	private double y;
	private transient Image enemySpaceShipImage;
	private EnemyBullet[] enemyBullet;
	private int enemyBulletcounter;
	private Rectangle enemyShape;
	private boolean enemyShoooted;
	private boolean dead;
	private transient Image enemyDeadImage;
	private long deadTime = 0;
	private long shootControl;
	private long shootControlV2;
	private boolean canShooting;
	private double canGoingRight;
	private double canGoingLeft;
	private String kindOfEnemy;
	private int enemyHealth;
	private long animating;

	public Enemy(double x, double y, String kindOfEnemy) {
		this.enemyBulletcounter = 0;
		this.setKindOfEnemy(kindOfEnemy);
		if (kindOfEnemy == "Red")
			this.enemyHealth = 2;
		else
			this.enemyHealth = 1;
		enemyShape = new Rectangle();
		canShooting = true;
		enemyShoooted = false;
		enemyBullet = new EnemyBullet[50];
		try {
			if (kindOfEnemy == "Yellow")
				enemySpaceShipImage = ImageIO.read(new File("YellowPlane.PNG")).getScaledInstance(
						SpriteGame.getSpriteGame().getWidth() / 10, SpriteGame.getSpriteGame().getWidth() / 10,
						Image.SCALE_AREA_AVERAGING);
			else
				enemySpaceShipImage = ImageIO.read(new File("RedPlane.PNG")).getScaledInstance(
						SpriteGame.getSpriteGame().getWidth() / 10, SpriteGame.getSpriteGame().getWidth() / 10,
						Image.SCALE_AREA_AVERAGING);
			enemyDeadImage = ImageIO.read(new File("boom2.PNG")).getScaledInstance(
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
			} else
				return;
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
			// System.out.println(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()
			// - getAnimating());
			// if
			// (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()
			// - getAnimating() <= 100)
			// g.drawImage(enemySpaceShipImage, (int) this.getX() + 1, (int)
			// this.getY(),
			// enemySpaceShipImage.getWidth(null),
			// enemySpaceShipImage.getHeight(null), null);
			// else if
			// (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()
			// - getAnimating() <= 200)
			// g.drawImage(enemySpaceShipImage, (int) this.getX() + 2, (int)
			// this.getY(),
			// enemySpaceShipImage.getWidth(null),
			// enemySpaceShipImage.getHeight(null), null);
			// else if
			// (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()
			// - getAnimating() <= 300)
			// g.drawImage(enemySpaceShipImage, (int) this.getX() + 3, (int)
			// this.getY(),
			// enemySpaceShipImage.getWidth(null),
			// enemySpaceShipImage.getHeight(null), null);
			// else if
			// (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()
			// - getAnimating() <= 400)
			// g.drawImage(enemySpaceShipImage, (int) this.getX() + 2, (int)
			// this.getY(),
			// enemySpaceShipImage.getWidth(null),
			// enemySpaceShipImage.getHeight(null), null);
			// else if
			// (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()
			// - getAnimating() <= 500)
			// g.drawImage(enemySpaceShipImage, (int) this.getX() + 1, (int)
			// this.getY(),
			// enemySpaceShipImage.getWidth(null),
			// enemySpaceShipImage.getHeight(null), null);
			// if
			// (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()
			// - getAnimating() >= 500) {
			// g.drawImage(enemySpaceShipImage, (int) this.getX(), (int)
			// this.getY(),
			// enemySpaceShipImage.getWidth(null),
			// enemySpaceShipImage.getHeight(null), null);
			// setAnimating(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime());
			// }
			// g.drawImage(enemySpaceShipImage, (int) this.getX(), (int)
			// this.getY(),
			// enemySpaceShipImage.getWidth(null),
			// enemySpaceShipImage.getHeight(null), null);
		}
	}

	public Rectangle getEnemyShape() {
		return enemyShape;
	}

	public void setEnemyShape(Rectangle enemyShape) {
		this.enemyShape = enemyShape;
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

			for (int i = enemyBulletcounter; i <= enemyBullet.length;) {
				enemyBullet[enemyBulletcounter] = new EnemyBullet(
						(int) this.getX() + (enemySpaceShipImage.getWidth(null) / 2) - 5,
						(int) this.getY() + enemySpaceShipImage.getHeight(null), 10, 20, true);
				break;
			}
			setShootControlV2(SpriteGame.getSpriteGame().getGame().getTimeCounter());
			setShootControlV2(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime());
			System.out.println(SpriteGame.getSpriteGame().getGame().getTimeCounter() + "sadadasdasdads");
			System.out.println(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() + "rhjretyerty");
		}
	}

	public void moveUp() {
		setY(getY() - 6);
	}

	public void moveDown() {
		setY(getY() + 6);
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

	public void setY(double y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		enemyShape.setBounds((int) this.getX(), (int) this.getY(), enemySpaceShipImage.getWidth(null),
				enemySpaceShipImage.getHeight(null));
		return enemyShape;
	}

	public EnemyBullet[] getEnemyBullet() {
		return enemyBullet;
	}

	public void setEnemyBullet(EnemyBullet[] enemyBullet) {
		this.enemyBullet = enemyBullet;
	}

	public void update() {
		if (!this.isDead()) {
			if (Math.random() > 0.99 && this.getX() > 0) {
				if (getCanGoingLeft() < (SpriteGame.getSpriteGame().getWidth()
						/ ((SpriteGame.getSpriteGame().getGame().getLevel().getNumberOfenemies() + 1) / 2) * 1)) {
					moveleft();
					setCanGoingLeft(getCanGoingLeft() + getX());
				}
			}
			if (Math.random() < 0.01 && this.getX() < SpriteGame.getSpriteGame().getWidth()) {
				if (getCanGoingRight() < (SpriteGame.getSpriteGame().getWidth()
						/ ((SpriteGame.getSpriteGame().getGame().getLevel().getNumberOfenemies() + 1) / 2) * 1)) {
					moveRight();
					setCanGoingRight(getCanGoingRight() + getX());
				}
			}
			if (0.99 > Math.random() && Math.random() > 0.98) {
				int waitTime;
				if (this.kindOfEnemy == "Red")
					waitTime = 2500;
				else
					waitTime = 5000;
				if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() > waitTime) {
					setCanShooting(true);
					shoot();
					// System.out.println(SpriteGame.getSpriteGame().getGame().currentTime
					// + "enemy/update");
					setCanShooting(false);
				}
			}
		} else if (getDeadTime() == 0) {
			setDeadTime(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime());
			// System.out.println(getDeadTime() + " enemy/update");
		}
		for (int i = 0; i < enemyBullet.length; i++)
			if (enemyBullet[i] != null)
				enemyBullet[i].update();

	}

	public long getShootControl() {
		return shootControl;
	}

	public void setShootControl(long shootControl) {
		this.shootControl = shootControl;
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

	public long getAnimating() {
		return animating;
	}

	public void setAnimating(long animating) {
		this.animating = animating;
	}

	public long getShootControlV2() {
		return shootControlV2;
	}

	public void setShootControlV2(long shootControlV2) {
		this.shootControlV2 = shootControlV2;
	}
}
