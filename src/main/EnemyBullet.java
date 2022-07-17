package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class EnemyBullet implements Serializable {

	private double x;
	private double y;
	private int width;
	private int height;
	private boolean isHot;
	private transient Rectangle enemyBulletShape;
	private transient Image ebemyBulletImage;
	private boolean hittedToPlayer;

	EnemyBullet(int x, int y, int width, int height, boolean isHot) {
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
		this.setHot(isHot);
		hittedToPlayer = false;
		enemyBulletShape = new Rectangle();
		for (int i = 0; i < SpriteGame.getSpriteGame().getGame().getLevel().getEnemy().length; i++) {
			if (SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i] != null
					&& SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i].getKindOfEnemy() == "Yellow") {
				try {
					ebemyBulletImage = ImageIO.read(new File("YellowBullet.png")).getScaledInstance(10, 20,
							Image.SCALE_AREA_AVERAGING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					ebemyBulletImage = ImageIO.read(new File("RedBullet.png")).getScaledInstance(10, 20,
							Image.SCALE_AREA_AVERAGING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void draw(Graphics g) {
		if (!hittedToPlayer)
			g.drawImage(ebemyBulletImage, (int) this.getX(), (int) this.getY(), ebemyBulletImage.getWidth(null),
					ebemyBulletImage.getHeight(null), null);
	}

	public Rectangle getBounds() {
		enemyBulletShape.setBounds((int) this.getX(), (int) this.getY(), getWidth(), getHeight());
		return enemyBulletShape;
	}

	public void update() {
		moveDown();
		if (this.getBounds().intersects(SpriteGame.getSpriteGame().getGame().getLevel().getPlayer().getBounds())
				&& SpriteGame.getSpriteGame().getGame().getLevel().getPlayer().isDeadPLayer() != true) {
			this.setHot(false);
			SpriteGame.getSpriteGame().getGame().getLevel().getPlayer().setDeadPLayer(true);
			System.out.println("Player dead");
			hittedToPlayer = true;
		}
	}

	public void moveUp() {
		setY(getY() - 6.8);
	}

	public void moveDown() {
		setY(getY() + 6.8);
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
