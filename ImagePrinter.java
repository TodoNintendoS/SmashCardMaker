import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

public class ImagePrinter 
{
	//CARD DATA
	final static int width = 1980;
	final static int height = 1400;
	final static Color nameRed = new Color(255, 0, 0, 150);
	final static Color nameGreen = new Color(0, 255, 0, 150);
	
	//PLAYER DATA
	final static String jugador = "KOLOG";
	final static String main = "Young Link";
	final static int skin = 2;
	
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
	
	public static void renderCard() throws IOException
	{
		long start = System.currentTimeMillis();
		
		//Background used for the image
		BufferedImage bgNonScale = 
				ImageIO.read(new File("./images/backgrounds/orangeTest.jpg"));
		
		//Resizes the background to fit the card dimensions
		BufferedImage bg = resize(bgNonScale, width, height, 1);
	    
		//Loads the render for the user's main and skin
	    BufferedImage render =
	    		ImageIO.read(new File("./images/renders/" + main + " (" + skin + ").png"));
	    
	    //Gets a list with the positioning variables of each render
	    List<Integer> modFactors = Positioning.mainScaler(main, skin);

	    //Resizes the render to fit within the card with the desired variables
	    render = resize(render, (int)((double)render.getWidth()*((double)modFactors.get(0)/100.0)), 
	    						(int)((double)render.getHeight()*((double)modFactors.get(1)/100.0)),
	    						(modFactors.get(2)));
	    
	    //Pastes the render in the background
	    Graphics paster = bg.getGraphics();
	    paster.drawImage(render, modFactors.get(3), modFactors.get(4), null);
	    
	    //Generates the layers for the name space
	    BufferedImage namespaceBottom = CreateNamespace.namespaceGenerator(100, 100, 0, 0);
	    BufferedImage namespaceMiddle = CreateNamespace.namespaceGenerator(100, 0, 100, 0);
	    BufferedImage namespaceTop = CreateNamespace.namespaceGenerator(133, 0, 0, 30);
	    
	    //Pastes those layers in the background
	    paster.drawImage(namespaceBottom, 0, 1035, null);
	    paster.drawImage(namespaceMiddle, 0, 1065, null);
	    paster.drawImage(namespaceTop, 0, 1050, null);
	    
	    //Generates the custom player name and pastes it over the name space
	    paster.setFont(new Font("Futura", Font.PLAIN, 300));
	    paster.setColor(nameRed);
	    paster.drawString(jugador, 24, 1295);
	    paster.setColor(nameGreen);
	    paster.drawString(jugador, 36, 1295);
	    paster.setColor(Color.WHITE);
	    paster.drawString(jugador, 30, 1295);
	    
		//Saves the file with the finished custom card
	    File cardOutput = null;
	    try
	    {
	    	cardOutput = new File("Output.png");
	    	ImageIO.write(bg, "png", cardOutput);
	    }
	    catch(IOException e){}
	    
	    //Opens the generated file automatically
	    Desktop openFile = Desktop.getDesktop();
	    openFile.open(cardOutput);
	    
	    System.out.print(((long)System.currentTimeMillis() - start));
	}
	
	public static void main(String[] args) throws IOException {renderCard();}
}
