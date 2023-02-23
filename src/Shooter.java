import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class Shooter {

    private double x;
    private final double y;
    private Image spaceShipImage;
    protected int bulletCounter = 0;
    private final Rectangle shape;
    private boolean deadShooter;
    private Image deadImage;
    private boolean shot;
    private long shootControl;
    private Bullet[] bullets;

    public Shooter(double x, double y, String spaceshipImage) {
        bulletCounter = 0;
        shape = new Rectangle();
        this.x = x;
        this.y = y;
        deadShooter = false;
        shot = false;
        shootControl = 0l;

        try {
            spaceShipImage = ImageIO.read(new File(spaceshipImage)).getScaledInstance(
                    SpriteGame.getSpriteGame().getWidth() / 7, SpriteGame.getSpriteGame().getWidth() / 7,
                    Image.SCALE_AREA_AVERAGING);
            deadImage = ImageIO.read(new File("images\\boom2.PNG")).getScaledInstance(
                    SpriteGame.getSpriteGame().getWidth() / 7, SpriteGame.getSpriteGame().getWidth() / 7,
                    Image.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void draw(Graphics g) {
        if (this.isDeadShooter()) {
            g.drawImage(deadImage, (int) this.getX(), (int) this.getY(), deadImage.getWidth(null),
                    deadImage.getHeight(null), null);
        } else if (isShot()) {
            long value = SpriteGame.getSpriteGame().getMenu().getGame().getTime().getCurrentTime() - getShootControl();
            if (value <= 40)
                g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 3, spaceShipImage.getWidth(null),
                        spaceShipImage.getHeight(null), null);
            else if (value <= 80)
                g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 6, spaceShipImage.getWidth(null),
                        spaceShipImage.getHeight(null), null);
            else if (value <= 120)
                g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 9, spaceShipImage.getWidth(null),
                        spaceShipImage.getHeight(null), null);
            else if (value <= 160)
                g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 6, spaceShipImage.getWidth(null),
                        spaceShipImage.getHeight(null), null);
            else if (value <= 200)
                g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY() + 3, spaceShipImage.getWidth(null),
                        spaceShipImage.getHeight(null), null);

            if (value > 200) {
                setShot(false);
                g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY(), spaceShipImage.getWidth(null),
                        spaceShipImage.getHeight(null), null);
            }

        } else {
            g.drawImage(spaceShipImage, (int) this.getX(), (int) this.getY(), spaceShipImage.getWidth(null),
                    spaceShipImage.getHeight(null), null);

        }
    }

    public Rectangle getBounds() {
        shape.setBounds((int) this.getX(), (int) this.getY(), spaceShipImage.getWidth(null),
                spaceShipImage.getHeight(null));
        return shape;
    }

    public abstract void update();

    public Image getSpaceShipImage() {
        return spaceShipImage;
    }

    public void setSpaceShipImage(Image spaceShipImage) {
        this.spaceShipImage = spaceShipImage;
    }

    public abstract void shoot();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public long getShootControl() {
        return shootControl;
    }

    public boolean isDeadShooter() {
        return deadShooter;
    }

    public void setDeadShooter(boolean deadShooter) {
        this.deadShooter = deadShooter;
    }

    public Bullet[] getBullets() {
        return bullets;
    }

    public void setBullets(Bullet[] bullets) {
        this.bullets = bullets;
    }

    public boolean isShot() {
        return shot;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public void setShootControl(long shootControl) {
        this.shootControl = shootControl;
    }
}
