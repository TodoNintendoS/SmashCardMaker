import java.awt.image.BufferedImage;

public class CreateNamespace 
{
	public static BufferedImage namespaceGenerator(int alpha, int red, int green, int blue)
	{
		BufferedImage bgTemp =
				new BufferedImage(ImagePrinter.width, 280, BufferedImage.TYPE_INT_ARGB);
		
  		for(int i=0; i<ImagePrinter.width; i++)
    		{
  			for(int j=0; j<280; j++) 
  			{
  				int finalcolor = alpha<<24 | (red<<16) | (green<<8) | blue;
  				bgTemp.setRGB(i, j, finalcolor);			
  			}
    		}
  		
  		return bgTemp;
	}
}
