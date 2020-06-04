package com.example.common.util;

import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


public final class MatrixToImageWriter {  
  
  private static final int BLACK = 0xFF000000;  
  private static final int WHITE = 0xFFFFFFFF;  
  
  private MatrixToImageWriter() {}  
  
    
  public static BufferedImage toBufferedImage(BitMatrix matrix) {  
    int width = matrix.getWidth();  
    int height = matrix.getHeight();  
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
    for (int x = 0; x < width; x++) {  
      for (int y = 0; y < height; y++) {  
        image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);  
      }  
    }  
    return image;  
  }  
  
    
  public static String writeToFile(BitMatrix matrix, String format,int type)
      throws Exception {
    BufferedImage image = toBufferedImage(matrix);
      String path = DatePathUtil.getDatePath() +  "/"+ System.currentTimeMillis()+"."+format;
      File file = new File(ConfigUtil.getString("qrcode_url_prefix")+path);
      if (!file.getParentFile().exists()) {
          boolean mkBack = file.getParentFile().mkdirs();
          if (!mkBack) {
              throw new Exception("createPathFialed");
          }
      }
      ImageIO.write(image,format,file);
      String url = null;
      if(type ==1){
          url = ConfigUtil.getString("qrcode_url_prefix")+ path;
      }else {
          url = ConfigUtil.getString("show_qrcode")+path;
      }
      if (!ImageIO.write(image, format, file)) {
          throw new IOException("Could not write an image of format " + format);
      }
      return  url;
  }  
  
    
  public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)  
      throws IOException {  
    BufferedImage image = toBufferedImage(matrix);
    if (!ImageIO.write(image, format, stream)) {
      throw new IOException("Could not write an image of format " + format);
    }
  }  
  public static void writeToStreamWithLogo(BitMatrix matrix, String format, OutputStream stream,String logoPath)  
		  throws IOException {  
	  BufferedImage image = toBufferedImage(matrix);

	   int logoWidth = image.getWidth()/8;   //设置logo图片宽度为二维码图片的五分之一    
	   int logoHeight = image.getHeight()/8; //设置logo图片高度为二维码图片的五分之一    
	   int logoX = (image.getWidth()-logoWidth)/2;   //设置logo图片的位置,这里令其居中    
	   int logoY = (image.getHeight()-logoHeight)/2; //设置logo图片的位置,这里令其居中    
	   Graphics2D graphics = image.createGraphics();    
	   graphics.drawImage(ImageIO.read(new File(logoPath)), logoX, logoY, logoWidth, logoHeight, null);    
	   graphics.dispose();  
	   ImageIO.write(image, format, stream);
	  if (!ImageIO.write(image, format, stream)) {  
		  throw new IOException("Could not write an image of format " + format);  
	  }  
  }  
  public static String writeToFileWithLogo(BitMatrix matrix, String format, File file,String logoPath)
		  throws Exception {
	  BufferedImage image = toBufferedImage(matrix);
	  
	  int logoWidth = image.getWidth()/3;   //设置logo图片宽度为二维码图片的五分之一    
	  int logoHeight = image.getHeight()/3; //设置logo图片高度为二维码图片的五分之一    
	  int logoX = (image.getWidth()-logoWidth)/2;   //设置logo图片的位置,这里令其居中    
	  int logoY = (image.getHeight()-logoHeight)/2; //设置logo图片的位置,这里令其居中    
	  Graphics2D graphics = image.createGraphics();    
	  graphics.drawImage(ImageIO.read(new File(logoPath)), logoX, logoY, logoWidth, logoHeight, null);    
	  graphics.dispose();
      String path = DatePathUtil.getDatePath() +  "/"+ System.currentTimeMillis()+"."+format;
	  file = new File(ConfigUtil.getString("qrcode_url_prefix")+path);
      if (!file.getParentFile().exists()) {
          boolean mkBack = file.getParentFile().mkdirs();
          if (!mkBack) {
              throw new Exception("createPathFialed");
          }
      }
	  ImageIO.write(image,format,file);
      String url = ConfigUtil.getString("qrcode_url_prefix")+ path;
	  if (!ImageIO.write(image, format, file)) {  
		  throw new IOException("Could not write an image of format " + format);  
	  }
	  return  url;
  }  
  
}  

