import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	static Clip clipbite;
	static Clip clip;
	static Clip clipdeath;

	public static synchronized void playSound() {
		new Thread(new Runnable() {

			public void run() {
				try {
					clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./backgroundMusic.wav"));
					clip.open(inputStream);
     
					// Volume controller
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					// Min 0.0 -  Max 1.0
					double gain = 0.15;
					float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
					gainControl.setValue(dB);

					clip.loop(Clip.LOOP_CONTINUOUSLY);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public static synchronized void playDeathSound() {
		new Thread(new Runnable() {
			public void run() {
				try {
					clipdeath = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./deathsound.wav"));
					clipdeath.open(inputStream);
					clipdeath.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public static synchronized void biteSound() {
		new Thread(new Runnable() {
			public void run() {
				try {
					clipbite = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./applebite.wav"));
					clipbite.open(inputStream);
					clipbite.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public static void stopAudio() {
		if (clip != null) {
			clip.stop();
			clip.close();
		}
		if (clipbite != null) {
			clipbite.stop();
			clipbite.close();
		}
	}
}
