import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EnemyBullet extends Bullet {

	private Image bulletImage;
	private String enemyType;

	{

	}
	EnemyBullet(int x, int y, int width, int height,Enemy enemy) {
		super(x,y,width,height );

			if (enemy.getKindOfEnemy().equals("Yellow")) {
				try {
					bulletImage = ImageIO.read(new File("images\\YellowBullet.png")).getScaledInstance(10, 20,
							Image.SCALE_AREA_AVERAGING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					bulletImage = ImageIO.read(new File("images\\RedBullet.png")).getScaledInstance(10, 20,
							Image.SCALE_AREA_AVERAGING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	public void draw(Graphics g) {
		if (!super.isHitEnemy())
			g.drawImage(bulletImage, (int) this.getX(), (int) this.getY(), bulletImage.getWidth(null),
					bulletImage.getHeight(null), null);
	}
	public void update() {
		moveDown();
		if (this.getBounds().intersects(SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getPlayer().getBounds())
				&& !SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getPlayer().isDeadShooter()) {
			this.setHot();
			SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getPlayer().setDeadPLayer(true);
			System.out.println("Player dead");
			super.setHitEnemy(true);
		}
	}

	public void moveDown() {
		setY(getY() + 6.8);
	}

}
