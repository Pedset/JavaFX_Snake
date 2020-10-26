import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	
	static Clip clipbite;
	
	
	
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
