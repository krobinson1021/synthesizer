package Sources;
import Driver.AudioClip;

public class SquareWave implements Source {

	AudioClip ac;
	
	int frequency;

	public SquareWave(int pFrequency) {
		frequency = pFrequency;
		ac = new AudioClip();
		ac.setFrequency(pFrequency);
	}

	@Override
	public AudioClip generate() {
		
		for (int i = 0; i < ac.getSampleCount(); i++) {
			int sine = (int) (32767 * Math.sin(2 * Math.PI * frequency * i / ac.getSampleRate()));
			if (sine > 0.5) {
				sine = 32767;
			} else {
				sine = -32767;
			}
			ac.setSample(i, sine);
		}
		return ac;
		
	}

	public void setFrequency(int pFrequency) {
		frequency = pFrequency;
		ac.setFrequency(pFrequency);
	}
}

