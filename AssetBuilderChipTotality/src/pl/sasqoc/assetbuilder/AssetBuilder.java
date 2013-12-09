package pl.sasqoc.assetbuilder;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;



public class AssetBuilder {
		
	private final static Logger log = Logger.getLogger(AssetBuilder.class .getName()); 
	
    public static void main (String [] args) throws Exception
    {       
    	log.setLevel(Level.INFO);
    	log.setUseParentHandlers(false);
    	Handler ch = new ConsoleHandler();
    	ch.setFormatter(new LogFormatter());
    	log.addHandler(ch);
    	
		Settings set = new Settings();
		set.paddingX = 2;
		set.paddingY = 2;
		set.maxHeight = 2048;
		set.maxWidth = 2048;
		set.pot = true;
		set.stripWhitespaceX = false;
		set.stripWhitespaceY = false;
		set.ignoreBlankImages = true;
		set.rotation = false;		
		set.filterMag = Texture.TextureFilter.Linear;
		set.filterMin = Texture.TextureFilter.Linear;     
    	
    	String externalPatchContent = "../Content/";
    	
      	
    	log.info("Start przygotowania Assetów dla ChipTotality");
    	
		log.info("Przygotowanie budynków");	    
		TexturePacker2.process(set, externalPatchContent + "Buildings/", externalPatchContent + "BuildingsPack/" , "buildingsPack");
	    
		log.info("Przygotowanie ikonek");	    
		TexturePacker2.process(set, externalPatchContent + "Icons/", externalPatchContent + "IconsPack/" , "iconsPack");

		log.info("Przygotowanie wybuchów");	    
		TexturePacker2.process(set, externalPatchContent + "Explosions/", externalPatchContent + "ExplosionsPack/" , "explosionsPack");
		
    	log.info("Zakoñczono przygotowania Assetów dla ChipTotality");     	
    	
    	//Runtime.getRuntime().exec("cmd /c start " + "../Content/copyAssets.bat");
    }
        
    private static BufferedImage createResizedCopy(BufferedImage in, int scaleWidth, int scaleHeight, String fileToWritePath) throws Exception
	{
    	
		BufferedImage newImg = new BufferedImage(scaleWidth, scaleHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gg = newImg.createGraphics();    		
		gg.setComposite(AlphaComposite.Src);
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gg.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		gg.drawImage(in, 0, 0, scaleWidth, scaleHeight, null);
		gg.setComposite(AlphaComposite.Src);
		gg.dispose();    		
		
		File fileNewImg = new File(fileToWritePath);		
		fileNewImg.mkdirs();
		ImageIO.write(newImg, "png", fileNewImg);
							
		return newImg;
	}
    
}