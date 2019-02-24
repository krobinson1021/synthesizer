package Sources;
import java.util.Random;

import Driver.AudioClip;

public class WhiteNoise implements Source {

	AudioClip ac;
	
	public WhiteNoise() {
		ac = new AudioClip();
	}

	@Override
	public AudioClip generate() {
		Random randomizer = new Random();

		for (int i = 0; i < ac.getSampleCount(); i++) {
			int randomValue = randomizer.nextInt(32767);
			ac.setSample(i, randomValue);				
		}
		return ac;
	}
}
