package player;

import java.io.File;

import javazoom.jlgui.basicplayer.*;

public class MusicPlayer {
	
	// Attributes
	private BasicPlayer basicPlayer = null;

	public MusicPlayer() {
		basicPlayer = new BasicPlayer();
	}

	public void play(File file) {
		try {
			basicPlayer.open(file);
			basicPlayer.play();
		} catch (Exception e) {
			System.err.println("Something went wrong");
		}
	}

	public void stop() {
		try {
			basicPlayer.stop();
		} catch (BasicPlayerException e) {
			System.err.println("Something went wrong");
		}
	}

	public void setVolume(double vol, double volMax) {
		try {
			basicPlayer.setGain(vol / volMax);
		} catch (BasicPlayerException e) {
			// Ignore
		}
	}
}
