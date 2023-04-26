package process;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
  
  public static double[][][] loadImage(String path) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }

    int width = img.getWidth();
    int height = img.getHeight();
    double[][][] pixels = new double[1][height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int gray = getGrayValue(img.getRGB(x, y));
        pixels[0][y][x] = gray / 255.0;
      }
    }

    return pixels;
  }

  private static int getGrayValue(int rgb) {
    int r = (rgb >> 16) & 0xff;
    int g = (rgb >> 8) & 0xff;
    int b = rgb & 0xff;
    return (r + g + b) / 3;
  }
}

