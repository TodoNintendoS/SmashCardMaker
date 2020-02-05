import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Positioning 
{
	//Scales each main properly so it fits accordingly in the card
	public static List<Integer> mainScaler(String main, int skin)
	{
		List<Integer> values = new Vector<Integer>();
		
		switch(main)
		{
			default: values = Arrays.asList(150, 150, 1, -450, -120);
			return values;
		}
	}
}
