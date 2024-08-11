import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.lang.Thread;

public class CovertToGrey
{
  public static void main(String[] args)
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Escolha uma imagem");
    fileChooser.setMultiSelectionEnabled(true);
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens", "jpg", "png", "jpeg", "bmp", "gif"));
    int userSelection = fileChooser.showOpenDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION)
    {
        File[] files = fileChooser.getSelectedFiles();
        for(File file : files)
        {
          ImageGrey img = new ImageGrey(file);
          Thread t = new Thread(img);
          t.start();
        }
    }
    else
    {
      System.out.println("Nenhuma imagem foi selecionada.");
    }
  }
}
