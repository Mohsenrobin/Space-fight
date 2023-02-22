import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class PlayerBullet extends Bullet {

	private boolean isHot;
	private Image bulletImage;
	PlayerBullet(int x, int y, int width, int height, boolean isHot) {
		super(x,y,width,height);
		this.setHot(isHot);
		Random random = new Random();
		random.nextInt(SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy().length);
		try {
			bulletImage = ImageIO.read(new File("images\\BlueBullet.png")).getScaledInstance(15, 25,
					Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		if (!super.isHitEnemy())
			g.drawImage(bulletImage, (int) this.getX(), (int) this.getY(), bulletImage.getWidth(null),
					bulletImage.getHeight(null), null);
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
					super.setHitEnemy(true);
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
			setY(getY() - 6.8);
	}

	public boolean isHot() {
		return isHot;
	}
	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}


}
