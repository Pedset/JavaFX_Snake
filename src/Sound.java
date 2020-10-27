import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	static Clip clipbite;
	static Clip clip;

	public static synchronized void playSound() {
		new Thread(new Runnable() {

			public void run() {
				try {
					clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./backgroundMusic.wav"));
					clip.open(inputStream);

					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

					double gain = 0.15;

					float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);

					gainControl.setValue(dB);

					clip.start();
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

	}
}
