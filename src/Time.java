public class Time {

	private long StartTime;

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
		return ((System.currentTimeMillis() - getStartTime()));
	}


}
