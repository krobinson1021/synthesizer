package Driver;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void testGetSetAudioClip() {
		AudioClip ac1 = new AudioClip();
		ac1.setSample(15, 0x10);
		assertEquals(0x10, ac1.getSample(15));

		AudioClip ac2 = new AudioClip();
		ac2.setSample(7000, 0x1111);
		assertEquals(0x1111, ac2.getSample(7000));

		AudioClip ac3 = new AudioClip();
		ac3.setSample(0, -0x32);
		assertEquals(-0x32, ac3.getSample(0));

		AudioClip ac4 = new AudioClip();
		ac4.setSample(44099, -0x1);
		assertEquals(-0x1, ac4.getSample(44099));

		AudioClip ac5 = new AudioClip();
		ac5.setSample(101, 0x0);
		assertEquals(0x0, ac5.getSample(101));

		AudioClip ac6 = new AudioClip();
		ac6.setSample(0, -0x41);
		assertEquals(-0x41, ac6.getSample(0));

		AudioClip ac7 = new AudioClip();
		ac7.setSample(3999, 0x1000);
		assertEquals(0x1000, ac7.getSample(3999));

		AudioClip ac8 = new AudioClip();
		ac8.setSample(107, 0x0101);
		assertEquals(0x0101, ac8.getSample(107));
	}
}
