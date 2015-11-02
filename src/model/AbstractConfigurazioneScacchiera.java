package model;

public abstract class AbstractConfigurazioneScacchiera implements ConfigurazioneScacchiera{
	
	@Override
	public String toString() {
		String result = "";
		for (int y = 0; y < 8; y++)
			result += ""+getPezzo(0, y) + getPezzo(1, y) + getPezzo(2, y) + getPezzo(3, y) + getPezzo(4, y) + getPezzo(5, y) + getPezzo(6, y) + getPezzo(7, y)+"\n";

		return result;
	}

	
	@Override
	public boolean equals(Object other) {
		if (other instanceof ConfigurazioneScacchiera) {
			ConfigurazioneScacchiera altraConfigurazione = (ConfigurazioneScacchiera) other;
			for (int y = 0; y < 8; y++)
				for (int x = 0; x < 8; x++)
					if (getPezzo(x, y) != altraConfigurazione.getPezzo(x, y))
						return false;

			return true;
		}
		else
			return false;
	}
	
	
	
}
