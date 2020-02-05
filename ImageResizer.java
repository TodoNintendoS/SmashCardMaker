import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageResizer 
{
	/**
	 * @param bgNonScale 	Image that needs to be modified
	 * @param newWidth 		New width value for said image
	 * @param newHeight 	New height value for said image
	 * @param flip 			Flip factor. -1 flips the image entirely
	 * @param isRender 		Because only renders can be flipped.
	 * @returns 			Image properly scaled
	 */
	public static BufferedImage resize(Image bgNonScale, int newWidth, int newHeight, int flip)
	{
		BufferedImage bgTemp =
				new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		
		int displacement = 0;
		if(flip == -1) displacement = newWidth;
		
		Graphics2D g2d = bgTemp.createGraphics();
		g2d.drawImage(bgNonScale, displacement, 0, newWidth*flip, newHeight, null);
		g2d.dispose();
		
		return bgTemp;
	}
}
