package sound;

import java.io.File;

import javax.sound.sampled.*;

public class SoundManager {
		
		private Clip clip;
		
		/*
		public static Sound sound1 = new Sound("/sound1.wav");
		public static Sound sound2 = new Sound("/sound2.wav");
		public static Sound sound3 = new Sound("/sound3.wav");
		*/
		
		public static File music;
		
		public SoundManager (String fileName) {
			try {
				music = new File (fileName);
				AudioInputStream ais = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, AudioSystem.getAudioInputStream(music));
				clip = AudioSystem.getClip();
				clip.open(ais);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void play() {
			try {
				if (clip != null) {
					new Thread() {
						public void run() {
							synchronized (clip) {
								clip.stop();
								clip.setFramePosition(0);
								clip.start();
							}
						}
					}.start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void stop(){
			if(clip == null) return;
			clip.stop();
		}
		
		public void loop() {
			try {
				if (clip != null) {
					new Thread() {
						public void run() {
							synchronized (clip) {
								clip.stop();
								clip.setFramePosition(0);
								clip.loop(Clip.LOOP_CONTINUOUSLY);
							}
						}
					}.start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public boolean isActive(){
			return clip.isActive();
		}


}
