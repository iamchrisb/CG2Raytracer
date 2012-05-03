package cg2.system;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Generate an image to specification and save it to disk in PNG format.
 */
public class ImageGenerator {

  /**
   * Instantiate a generator for the specified painter object with supplied size
   * and filename.
   * 
   * @param painter
   *          The painter object.
   * @param resolutionX
   *          Image width in pixels.
   * @param resolutionY
   *          Image height in pixels.
   * @param filename
   *          File to store the image in.
   * @param imgformat
   *          String describing the desired image format. Supported formats: "png".
   */
  public ImageGenerator(Painter painter, int resolutionX, int resolutionY, String filename, String imgformat) {
	  generate(painter,resolutionX,resolutionY,filename,imgformat);
  }

  /**
   * Convenience function- try to open an appropriate viewer and show the image file
   */
  static public boolean showImage(String filename) {
	  try {
	      File file = new File(filename);
	      Desktop.getDesktop().open(file);
	  } catch (IOException e) {
		  e.printStackTrace();
	      return false;
	  }
	  return true;
 }

  private boolean generate(Painter painter, int resolutionX, int resolutionY, String filename, String imgformat) {
	  
	  // create a buffered image object using RGB+A pixel format 
    BufferedImage image = new BufferedImage(resolutionX, resolutionY, BufferedImage.TYPE_INT_ARGB);

    // Set a color for each pixel. Y pixel indices are flipped so that the origin (0,0)
    //   is at the bottom left of the image.
    for (int i = 0; i < resolutionX; i++)
      for (int j = 0; j < resolutionY; j++)
        image.setRGB( i, j, painter.pixelColorAt(i, resolutionY-j-1, resolutionX, resolutionY).toAwtColor() );

    // Write the image to disk.
    try {
      File file = new File(filename);
      ImageIO.write(image, imgformat, file);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    
    // success
    return true;
  }
  
} // class
