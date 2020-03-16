package gui;

import java.util.Date;

public class timeUntil {
	private int hour;
	private int min;
	private int sec;
	
	public timeUntil(int hour, int min, int sec) {
		this.hour = hour;
		this.min = min;
		this.sec = sec;
	}
	
	@SuppressWarnings("deprecation")
	public long calculation() {
		Date now = new Date();
		Date until = new Date();
		
		until.setHours(hour);
		until.setMinutes(min);
		until.setSeconds(sec);
		System.out.println("now= "+ now.getHours() +":"+ now.getMinutes()+":"+now.getSeconds());
		System.out.println("until= " + until.getHours()+":"+until.getMinutes()+":"+until.getSeconds());

		long sleep = until.getTime() - now.getTime();
		System.out.println("sleep = " + sleep);
		return sleep;
	}
}
