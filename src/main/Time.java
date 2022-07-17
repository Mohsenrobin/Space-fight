package main;

public class Time {

	private long StartTime;
	private long currentTime;

	Time() {
		setStartTime(System.currentTimeMillis());
	}

	public long getStartTime() {
		return StartTime;
	}

	public void setStartTime(long startTime) {
		StartTime = startTime;
	}

	public long getCurrentTime() {
		currentTime = ((System.currentTimeMillis() - getStartTime()));
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

}
