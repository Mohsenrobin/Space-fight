package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class EnemyBullet implements Serializable {

	private double x;
	private double y;
	private final int width;
	private final int height;
	private final transient Rectangle enemyBulletShape;
	private transient Image ebemyBulletImage;
	private boolean hittedToPlayer;

	EnemyBullet(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
		this.setHot();
		hittedToPlayer = false;
		enemyBulletShape = new Rectangle();
		for (int i = 0; i < SpriteGame.getSpriteGame().getGame().getLevel().getEnemy().length; i++) {
			if (SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i] != null
					&& Objects.equals(SpriteGame.getSpriteGame().getGame().getLevel().getEnemy()[i].getKindOfEnemy(), "Yellow")) {
				try {
					ebemyBulletImage = ImageIO.read(new File("images\\YellowBullet.png")).getScaledInstance(10, 20,
							Image.SCALE_AREA_AVERAGING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					ebemyBulletImage = ImageIO.read(new File("images\\RedBullet.png")).getScaledInstance(10, 20,
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
				&& !SpriteGame.getSpriteGame().getGame().getLevel().getPlayer().isDeadPLayer()) {
			this.setHot();
			SpriteGame.getSpriteGame().getGame().getLevel().getPlayer().setDeadPLayer(true);
			System.out.println("Player dead");
			hittedToPlayer = true;
		}
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

	public void setHot() {
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
