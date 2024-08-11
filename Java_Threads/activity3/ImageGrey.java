import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import java.lang.Thread;
import java.awt.Color;

public class ImageGrey implements Runnable
{
  private File file;
  public ImageGrey(File file)
  {
    this.file = file;
  }
  private static String getNameWithoutExtension(String fileName)
  {
     int dotIndex = fileName.lastIndexOf('.');
     if (dotIndex > 0 && dotIndex < fileName.length() - 1)
     {
         return fileName.substring(0, dotIndex);
     } else {
         return fileName;
     }
   }
   private static String getFileExtension(File file)
  {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1)
        {
            return fileName.substring(dotIndex + 1).toLowerCase();
        } else {
            return "";
        }
    }
  public void run(){
    try
    {
      BufferedImage image = ImageIO.read(this.file);
      int width = image.getWidth();
      int height = image.getHeight();

      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
           Color pixelColor = new Color(image.getRGB(x, y));
           int blackAndWhiteNumber = (int) (pixelColor.getRed() * 0.3 + pixelColor.getGreen() * 0.59 + pixelColor.getBlue() * 0.11);
           Color greyscalePixel = new Color(blackAndWhiteNumber, blackAndWhiteNumber, blackAndWhiteNumber);
           image.setRGB(x, y, greyscalePixel.getRGB());
         }
     }
      String newFileName = getNameWithoutExtension(this.file.getName()) + "_greyVersion." + getFileExtension(this.file);
      File outputFile = new File(this.file.getParent(), newFileName);
      ImageIO.write(image,getFileExtension(this.file),outputFile);
      System.out.println("Imagem Editada Com Sucesso: " + this.file.getName());
     } catch (IOException ex) {
      ex.printStackTrace();
      System.err.println("Erro ao carregar a imagem.");
     }
  }
}
