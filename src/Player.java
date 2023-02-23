import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends Shooter{

	private boolean rightGoing;
	private boolean leftGoing;


	public Player(double x, double y) {
		super(x,y,"images\\BluePlane.PNG");

		setBullets( new PlayerBullet[10]);
		rightGoing = false;
		leftGoing = false;
	}

	public void update() {
		if (isRightGoing())
			moveRight();
		if (isLeftGoing())
			moveleft();
		for (Bullet value : getBullets())
			if (value != null)
				value.update();
	}

	public void shoot() {

		if(SpriteGame.getSpriteGame().getMenu().getGame().getTime().getCurrentTime() - getShootControl() > 500) {
			setShot(true);
			bulletCounter++;
			if (bulletCounter == getBullets().length - 1)
				bulletCounter = 0;

			getBullets()[bulletCounter] = new PlayerBullet((int) this.getX() + (getSpaceShipImage().getWidth(null) / 15),
					(int) this.getY(), 15, 25, true);
			if (bulletCounter % 2 == 0)
				this.getBullets()[bulletCounter].setX(this.getBullets()[bulletCounter].getX() + 40);
			super.setShootControl(SpriteGame.getSpriteGame().getMenu().getGame().getTime().getCurrentTime());
		}
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
		if (this.getX() < SpriteGame.getSpriteGame().getWidth() - getSpaceShipImage().getWidth(null))
			setX(getX() + 3.8);
	}

	public void setDeadPLayer(boolean b){
		super.setDeadShooter(b);
	}

}