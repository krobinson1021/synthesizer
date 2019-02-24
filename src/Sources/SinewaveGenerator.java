package Sources;
import Driver.AudioClip;

public class SinewaveGenerator implements Source {

	AudioClip ac;

	int maxValue;

	int frequency;

	public SinewaveGenerator(int pFrequency) {
		maxValue = 32767; // max value you can store in two bytes
		frequency = pFrequency;
		ac = new AudioClip();
		ac.setFrequency(pFrequency);
	}

	@Override 
	public AudioClip generate() {
		for (int i = 0; i < ac.getSampleCount(); i++) {
			int sine = (int) (maxValue * Math.sin(2 * Math.PI * frequency * i / ac.getSampleRate()));
			ac.setSample(i, sine);
		}
		return ac;
	}

	public AudioClip getAc() {
		return ac;
	}

	public void setAc(AudioClip pAc) {
		this.ac = pAc;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int pMaxValue) {
		this.maxValue = pMaxValue;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int pFrequency) {
		this.frequency = pFrequency;
		ac.setFrequency(pFrequency);
	}

}
