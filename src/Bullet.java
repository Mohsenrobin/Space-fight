import java.awt.*;
import java.util.Random;

public abstract class Bullet {

    private double x;
    private double y;
    private final int width;
    private final int height;
    private final Rectangle bulletShape;
    private boolean hitEnemy;

    Bullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitEnemy = false;
        bulletShape = new Rectangle();
        Random random = new Random();
        random.nextInt(SpriteGame.getSpriteGame().getMenu().getGame().getLevel().getEnemy().length);
    }



    public Rectangle getBounds() {
        bulletShape.setBounds((int) this.getX(), (int) this.getY(), getWidth(), getHeight());
        return bulletShape;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public void moveDown() {
        setY(getY() + 6.8);
    }


    public double getX() {
        return x;
    }

    public boolean isHitEnemy() {
        return hitEnemy;
    }

    public void setHitEnemy(boolean hitEnemy) {
        this.hitEnemy = hitEnemy;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
