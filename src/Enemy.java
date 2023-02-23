import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends Shooter{

	private long deadTime = 0;
	private boolean canShooting;
	private double canGoingRight;
	private double canGoingLeft;
	private String kindOfEnemy;
	private int enemyHealth;

	public Enemy(double x, double y, String kindOfEnemy) {
		super(x,y,"images\\RedPlane.PNG");

		setBullets( new EnemyBullet[10]);
		canShooting = true;


		this.kindOfEnemy = kindOfEnemy;
		if (Objects.equals(kindOfEnemy, "Red"))
			this.enemyHealth = 2;
		else
			this.enemyHealth = 1;
		try {
			if (Objects.equals(kindOfEnemy, "Yellow"))
				setSpaceShipImage(ImageIO.read(new File("images\\YellowPlane.PNG")).getScaledInstance(
						SpriteGame.getSpriteGame().getWidth() / 10, SpriteGame.getSpriteGame().getWidth() / 10,
						Image.SCALE_AREA_AVERAGING));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void shoot() {
		setShot(true);
		if (!this.isDeadShooter() && isCanShooting()) {
			if (bulletCounter == getBullets().length - 1)
				bulletCounter = 0;
			bulletCounter++;
			if (getBullets()[bulletCounter] != null) {
				getBullets()[bulletCounter].setX(this.getX());
				getBullets()[bulletCounter].setY(this.getY());
			}

			//for (int i = enemyBulletcounter; i <= enemyBullet.length;) {
			getBullets()[bulletCounter] = new EnemyBullet(
						(int) this.getX() + (getSpaceShipImage().getWidth(null) / 2) - 5,
						(int) this.getY() + getSpaceShipImage().getHeight(null), 10, 20,this);
			//	break;
			//}
			//setShootControlV2(SpriteGame.getSpriteGame().getMenu().getGame().getTimeCounter());
			setShootControl(SpriteGame.getSpriteGame().getMenu().getGame().getTime().getCurrentTime());
			//System.out.println(SpriteGame.getSpriteGame().getMenu().getGame().getTimeCounter() + "sadadasdasdads");
			//System.out.println(SpriteGame.getSpriteGame().getMenu().getGame().getTime().getCurrentTime() + "rhjretyerty");
		}
	}

	public void moveLeft() {
		setX(getX() - 8);
	}

	public void moveRight() {
		setX(getX() + 8);
	}

	public void update() {
		if (!this.isDeadShooter()) {
			if (Math.random() > 0.99 && this.getX() > 0) {
				if (getCanGoingLeft() < SpriteGame.getSpriteGame().getWidth()
						/(float) (SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getNumberOfEnemies() + 1) / 2
				) {
					moveLeft();
					setCanGoingLeft(getCanGoingLeft() + getX());
					System.out.println(canGoingLeft + " CAN GOING LEFT " + getX() + " CRAZY "  + SpriteGame.getSpriteGame().getWidth()
							/(float) (SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getNumberOfEnemies() + 1) / 2);
				}
			}
			if (Math.random() < 0.01 && this.getX() < SpriteGame.getSpriteGame().getWidth()) {
				if (getCanGoingRight() < (SpriteGame.getSpriteGame().getWidth()
						/(float) ((SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getNumberOfEnemies() + 1) / 2))) {
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
				if (SpriteGame.getSpriteGame().getMenu().getGame().getTime().getCurrentTime() - getShootControl() > waitTime) {
					setCanShooting(true);
					shoot();
					setCanShooting(false);
				}
			}
		} else if (getDeadTime() == 0) {
			setDeadTime(SpriteGame.getSpriteGame().getMenu().getGame().getTime().getCurrentTime());
		}
		for (Bullet bullet : getBullets())
			if (bullet != null)
				bullet.update();

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

	public int getEnemyHealth() {
		return enemyHealth;
	}

	public void setEnemyHealth(int enemyHealth) {
		this.enemyHealth = enemyHealth;
	}
}
