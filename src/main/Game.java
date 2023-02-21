package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements Runnable, ActionListener {

	private Level level;
	private transient Thread thread;
	private transient Thread thread2;
	private boolean pause;
	private final transient CyclicBarrier gameThredsManager;
	private final transient JLabel scoreLabel;
	private final transient JLabel highScoreLabel;
	private final transient JLabel startEndGame;
	private String scoreTable;
	private String highScoreTable;
	private Time time;
	private final Timer timer;
	private long timeCounter;

	public long getTimeCounter() {
		return timeCounter;
	}

	public void setTimeCounter(long timeCounter) {
		this.timeCounter = timeCounter;
	}

	public Game() {

		timeCounter = 0;
		timer = new Timer(170, this);
		gameThredsManager = new CyclicBarrier(3);
		setLayout(null);
		Thread pauseThread = new Thread(this);
		pauseThread.start();
		setBounds(0, 0, SpriteGame.getSpriteGame().getWidth(), SpriteGame.getSpriteGame().getHeight());
		scoreLabel = new JLabel();
		scoreLabel.setBounds(0, 0, 200, 50);
		scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
		scoreLabel.setForeground(Color.ORANGE);
		add(scoreLabel);
		highScoreLabel = new JLabel();
		highScoreLabel.setBounds(0, 20, 200, 50);
		highScoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
		highScoreLabel.setForeground(Color.RED);
		add(highScoreLabel);
		startEndGame = new JLabel();
		startEndGame.setBounds(50, 250, 400, 60);
		startEndGame.setFont(new Font("Serif", Font.BOLD, 60));
		startEndGame.setForeground(Color.GREEN);
		add(startEndGame);
		startEndGame.setVisible(true);
		startEndGame.setText("     Start");
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_RIGHT ->
							level.getPlayer().setRightGoing(false);
					case KeyEvent.VK_LEFT ->
							level.getPlayer().setLeftGoing(false);
				}
			}

			@Override
			public synchronized void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					level.getPlayer().setRightGoing(true);
					break;
				case KeyEvent.VK_LEFT:
					level.getPlayer().setLeftGoing(true);
					break;
				case KeyEvent.VK_SPACE:
					level.getPlayer().shoot();
					break;
				case KeyEvent.VK_Z:
					break;
				case KeyEvent.VK_ENTER:
					pause = !pause;
					break;
					default:
						throw new IllegalStateException("Unexpected value: " + e.getKeyCode());
				}
			}
		});
		this.setFocusable(true);
		this.requestFocus();
		this.setFocusTraversalKeysEnabled(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (level != null)
			level.draw(g);
	}

	@Override
	public void run() {
		while (true) {
			if (!pause) {
				try {
					gameThredsManager.await();
				} catch (InterruptedException |
						 BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void gameUpdate() {
		timer.start();
		setThread(new Thread(new Runnable() {
			@Override
			public void run() {
				while (!getLevel().getPlayer().isDeadPLayer()) {
					level.update();


					scoreTable = Integer.toString(Score.getScore().getplayerScore());
					scoreLabel.setText("SCORE: " + scoreTable);
					if (Score.getScore().getplayerScore() > Score.getScore().getHighScore()) {
						scoreLabel.setForeground(Color.RED);
						Score.getScore().setHighScore(Score.getScore().getplayerScore());
					}
					highScoreTable = Integer.toString(Score.getScore().getHighScore());
					highScoreLabel.setText("HIGHSCORE: " + highScoreTable);
					if (getTime().getCurrentTime() > 1000)
						startEndGame.setVisible(false);
					if (getLevel().getPlayer().isDeadPLayer()) {
						ScoreSaver.getScoreSaver().output();
						try {
							ScoreSaver.getScoreSaver().getOut().writeObject(Score.getScore());
							ScoreSaver.getScoreSaver().getOut().close();
							ScoreSaver.getScoreSaver().getOutFile().close();
							System.out.println("saved");
						} catch (IOException e) {
							e.printStackTrace();
						}
						startEndGame.setText("Game Over");
						startEndGame.setForeground(Color.RED);
						startEndGame.setVisible(true);
					}
					try {
						gameThredsManager.await();
					} catch (InterruptedException |
							 BrokenBarrierException e1) {
						e1.printStackTrace();
					}
					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}));
		thread.start();
	}

	public void gameRender() {
		setThread2(new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					Game.this.repaint();
					try {
						gameThredsManager.await();
					} catch (InterruptedException | BrokenBarrierException e1) {
						e1.printStackTrace();
					}
					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}));
		thread2.start();
	}

	public void gameOver() {
		if (SpriteGame.getSpriteGame().getGame().level.getPlayer().isDeadPLayer())
			try {
				thread.join();
				System.out.println("are u there");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void setThread2(Thread thread2) {
		this.thread2 = thread2;
	}

	public void setThread(Thread thread1) {
		this.thread = thread1;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < getLevel().getEnemy().length; i++) {
			if (getLevel().getEnemy()[i] != null) {
				if (timeCounter % 2 == 0)
					getLevel().getEnemy()[i].setX(getLevel().getEnemy()[i].getX() + 1.5);
				else
					getLevel().getEnemy()[i].setX(getLevel().getEnemy()[i].getX() - 1.5);
			}
		}
		if (timeCounter % 2 == 0)
			getLevel().getPlayer().setX(getLevel().getPlayer().getX() + 1);
		else
			getLevel().getPlayer().setX(getLevel().getPlayer().getX() - 1);
		timeCounter++;
		System.out.println(timeCounter);
	}
}
