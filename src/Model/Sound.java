package Model;

import java.io.File;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import Controller.VolumeCtrl;
import javafx.scene.media.AudioClip;

public class Sound {

	static Clip clipbite;
	static Clip clip;
	static Clip clipdeath;
	static Clip clipMenu;
	static Clip clipMenuDeath;
	static FloatControl gainControlMenu;

	public static synchronized void playSound() {
		new Thread(new Runnable() {

			public void run() {
				try {
					if (clipdeath != null) {
						clipdeath.stop();
						clipdeath.close();
					}
					clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem
							.getAudioInputStream(new File("./Sound/backgroundMusic.wav"));
					clip.open(inputStream);

					// Volume controller
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					// Min 0.0 - Max 1.0

					double gain = (VolumeCtrl.getVolume() / 5) / 100.0;
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
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./Sound/deathsound.wav"));
					clipdeath.open(inputStream);

					FloatControl gainControl = (FloatControl) clipdeath.getControl(FloatControl.Type.MASTER_GAIN);
					// Min 0.0 - Max 1.0

					double gain = (VolumeCtrl.getVolume() / 100.0);
					float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
					gainControl.setValue(dB);

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
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./Sound/applebite.wav"));
					clipbite.open(inputStream);

					FloatControl gainControl = (FloatControl) clipbite.getControl(FloatControl.Type.MASTER_GAIN);
					// Min 0.0 - Max 1.0

					double gain = (VolumeCtrl.getVolume() / 100.0);
					float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
					gainControl.setValue(dB);

					clipbite.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public static synchronized void menuDeathSound() {
		new Thread(new Runnable() {
			public void run() {
				try {

					clipMenuDeath = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./Sound/MenuDeath.wav"));
					clipMenuDeath.open(inputStream);

					FloatControl gainControl = (FloatControl) clipMenuDeath.getControl(FloatControl.Type.MASTER_GAIN);
					// Min 0.0 - Max 1.0

					double gain = (VolumeCtrl.getVolume() / 3 / 100.0);
					float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
					gainControl.setValue(dB);

					clipMenuDeath.start();
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

	public static void stopMenuDeathAudio() {
		if (clipMenuDeath != null) {
			clipMenuDeath.stop();
			clipMenuDeath.close();
		}
	}

	public static void stopMenuAudio() {
		if (clipMenu != null) {
			clipMenu.stop();
			clipMenu.close();
		}
	}

	public static void changeGainValumeMenu() {
		double gain = (VolumeCtrl.getVolume() / 100.0);
		float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		gainControlMenu.setValue(dB);
	}

	public static synchronized void playMenuSound() {
		new Thread(new Runnable() {
			public void run() {
				try {
					clipMenu = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./Sound/MenuTrack.wav"));
					clipMenu.open(inputStream);

					gainControlMenu = (FloatControl) clipMenu.getControl(FloatControl.Type.MASTER_GAIN);

					changeGainValumeMenu();

					clipMenu.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public static void playMouseHover() {
		AudioClip clipMouseHover = new AudioClip(Paths.get("./Sound/scrollTick.mp3").toUri().toString());
		clipMouseHover.play();
	}

	public static void playMouseClick() {
		AudioClip clipMouseClicked = new AudioClip(Paths.get("./Sound/clickMouse.mp3").toUri().toString());
		clipMouseClicked.play();
	}

}
