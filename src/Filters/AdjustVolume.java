package Filters;
import Driver.AudioClip;
import Sources.SinewaveGenerator;
import Sources.Source;

public class AdjustVolume implements Filter {

	private Source input;

	private int scale;

	public AdjustVolume(int pScale) {
		scale = pScale;
	}

	@Override
	public AudioClip generate() {
		AudioClip original = input.generate();
		AudioClip result = new AudioClip(original);

		for (int i = 0; i < result.getSampleRate(); i++) { // dividing by 100 to return volume range 0 to 100
			result.setSample(i, ((byte) scale * original.getSample(i)) / (byte) 100);
		}		
		return result;
	}

	@Override
	public void connectInput(Source pInput) {
		input = pInput;
	}
	

	public void setScale(int scale) {
		this.scale = scale;
	}

}
