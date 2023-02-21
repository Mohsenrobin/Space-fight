package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable {

	private double x;
	private double y;
	private transient Image spaceShipImage;
	private final Bullet[] bullets;
	private int bulletCounter = 0;
	private boolean rightGoing;
	private boolean leftGoing;

	private boolean canShooting;
	private final transient Rectangle playerShape;
	private boolean deadPLayer;
	private boolean shot;
	private transient Image deadImage;
	private long shootControl;
	private long shootControlV2;
	private long animating;

	public Player(double x, double y) {
		bullets = new Bullet[10];
		playerShape = new Rectangle();
		rightGoing = false;
		leftGoing = false;
		deadPLayer = false;
		shot = false;
		try {
			spaceShipImage = ImageIO.read(new File("images\\BluePlane.PNG")).getScaledInstance(
					SpriteGame.getSpriteGame().getWidth() / 7, SpriteGame.getSpriteGame().getWidth() / 7,
					Image.SCALE_AREA_AVERAGING);
			deadImage = ImageIO.read(new File("images\\boom2.PNG")).getScaledInstance(
					SpriteGame.getSpriteGame().getWidth() / 7, SpriteGame.getSpriteGame().getWidth() / 7,
					Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
		setAnimating(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime());

	}

	public void draw(Graphics g) {
		if (this.isDeadPLayer()) {
			g.drawImage(deadImage, (int) this.getX(), (int) this.getY(), deadImage.getWidth(null),
					deadImage.getHeight(null), null);
		} else if (isShot()) {
			System.out.println(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime()/100);
			if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() <= 40)
				g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 3, spaceShipImage.getWidth(null),
						spaceShipImage.getHeight(null), null);
			else if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() <= 80)
				g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 6, spaceShipImage.getWidth(null),
						spaceShipImage.getHeight(null), null);
			else if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() <= 120)
				g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 9, spaceShipImage.getWidth(null),
						spaceShipImage.getHeight(null), null);
			else if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() <= 160)
				g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 6, spaceShipImage.getWidth(null),
						spaceShipImage.getHeight(null), null);
			else if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() <= 200)
				g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 3, spaceShipImage.getWidth(null),
						spaceShipImage.getHeight(null), null);

			if (SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() > 200) {
				System.out.println("PLAYER/draw");
				setShot(false);
				g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY(), spaceShipImage.getWidth(null),
						spaceShipImage.getHeight(null), null);
			}

		} else {
			g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY(), spaceShipImage.getWidth(null),
					spaceShipImage.getHeight(null), null);

		}
	}
	public boolean isShot() {
		return shot;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}

	public void update() {
		if (isRightGoing())
			moveRight();
		if (isLeftGoing())
			moveleft();
		for (Bullet value : bullets)
			if (value != null)
				value.update();
	}

	public Bullet[] getBullets() {
		return bullets;
	}

	public void shoot() {

		if(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime() - getShootControlV2() > 500) {
			setShot(true);
			bulletCounter++;
			if (bulletCounter == bullets.length - 1)
				bulletCounter = 0;

			bullets[bulletCounter] = new Bullet(false, (int) this.getX() + (spaceShipImage.getWidth(null) / 15),
					(int) this.getY(), 15, 25, true);
			if (bulletCounter % 2 == 0)
				this.getBullets()[bulletCounter].setX(this.getBullets()[bulletCounter].getX() + 40);

			setShootControl(SpriteGame.getSpriteGame().getGame().getTimeCounter());
			setShootControlV2(SpriteGame.getSpriteGame().getGame().getTime().getCurrentTime());
		}
	}

	public Rectangle getBounds() {
		playerShape.setBounds((int) this.getX(), (int) this.getY(), spaceShipImage.getWidth(null),
				spaceShipImage.getHeight(null));
		return playerShape;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public boolean isRightGoing() {
		return rightGoing;
	}

	public void setRightGoing(boolean rightGoing) {
		this.rightGoing = rightGoing;
	}

	public boolean isLeftGoing() {
		return leftGoing;
	}

	public void setLeftGoing(boolean leftGoing) {
		this.leftGoing = leftGoing;
	}
	public void moveleft() {
		if (this.getX() > 0)
			setX(getX() - 3.8);
	}

	public void moveRight() {
		if (this.getX() < SpriteGame.getSpriteGame().getWidth() - spaceShipImage.getWidth(null))
			setX(getX() + 3.8);
	}

	public boolean isDeadPLayer() {
		return deadPLayer;
	}

	public void setDeadPLayer(boolean deadPLayer) {
		this.deadPLayer = deadPLayer;
	}

	public void setShootControl(long shootControl) {
		this.shootControl = shootControl;
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