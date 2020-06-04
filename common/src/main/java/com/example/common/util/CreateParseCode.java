package com.example.common.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ZXing工具类
 * 
 * @see ------------------------------------------------------------------------
 *      -----------------------------------------------
 * @see //首页--https://code.google.com/p/zxing
 * @see //介绍--用于解析多种格式条形码(EAN-13)和二维码(QRCode)的开源Java类库,其提供了多种应用的类库,如javase/jruby/
 *      cpp/csharp/android
 * @see //说明--下载到的ZXing-2.2.zip是它的源码,我们在JavaSE中使用时需用到其core和javase两部分
 * @see //可直接引入它俩的源码到项目中,或将它俩编译为jar再引入,这是我编译好的：http://download.csdn.net/detail/
 *      jadyer/6245849
 * @see ------------------------------------------------------------------------
 *      -----------------------------------------------
 * @see //经测试:用微信扫描GBK编码的中文二维码时出现乱码,用UTF-8编码时微信可正常识别
 * @see //并且MultiFormatWriter.encode()时若传入hints参数来指定UTF-8编码中文时,微信压根就不识别所生成的二维码
 * @see //所以这里使用的是这种方式new String(content.getBytes("UTF-8"), "ISO-8859-1")
 * @see ------------------------------------------------------------------------
 *      -----------------------------------------------
 * @see //将logo图片加入二维码中间时,需注意以下几点
 * @see 1)生成二维码的纠错级别建议采用最高等级H,这样可以增加二维码的正确识别能力(我测试过,不设置级别时,二维码工具无法读取生成的二维码图片)
 * @see 2)头像大小最好不要超过二维码本身大小的1/5,而且只能放在正中间部位,这是由于二维码本身结构造成的(你就把它理解成图片水印吧)
 * @see 3)在仿照腾讯微信在二维码四周增加装饰框,那么一定要在装饰框和二维码之间留出白边,这是为了二维码可被识别
 * @see ------------------------------------------------------------------------
 *      -----------------------------------------------
 * @version v1.0
 * @history v1.0-->方法新建,目前仅支持二维码的生成和解析,生成二维码时支持添加logo头像
 * @editor Sep 10, 2013 9:32:23 PM
 * @create Sep 10, 2013 2:08:16 PM
 * @author jiangshicun<http://blog.csdn.net/jadyer>
 */
public class CreateParseCode {
	
	public static void main(String[] args) throws IOException, WriterException {
		CreateParseCode cpCode = new CreateParseCode();
			String content = "http://192.168.1.179:8080/flowermanager/login/login.htm";
			int width = 300;
			int height = 300;
			 //二维码的图片格式
			String format = "png";
		// 生成二维码
		cpCode.createCodeNeedPath(content, width,height, format);
		//String logePath = session.getServletContext().getRealPath("/") + "img\\flower.png";  
		//cpCode.createCodeReturnFileHaveLoge(content, width, height, format, logePath);
		
		// 解析二维码
		//cpCode.parseCode(new File("C:/二维码生成/yizoooFlower.png"));
	}

	/**
	 * 二维码的生成  返回一个流（生成不带logo 的二维码）
	 * 
	 */
	public static void createCodeReturnStrem(HttpServletResponse response,String content,int width,int height,String format) {
		//String content = "http://192.168.1.179:8080/flowermanager/main.htm";
		//int width = 300;
		//int height = 300;
		// 二维码的图片格式
		//String format = "png";
		/**
		 * 设置二维码的参数
		 */
		HashMap hints = new HashMap();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			ServletOutputStream sos = response.getOutputStream();     
			// 生成二维码
			/*File outputFile = new File("C:" + File.separator + "二维码生成" + File.separator + "yizoooFlower.png");
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);*/
			MatrixToImageWriter.writeToStream(bitMatrix, format, sos);
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 二维码的生成  返回一个流（生成带loge的二维码）
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void createCodeReturnStremHaveLoge(HttpServletResponse response,String content,int width,int height,String format,String logePath) {

		HashMap<EncodeHintType, Object> hints = new HashMap();
		// 内容所使用编码
		 hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		 hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.MARGIN,0);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			bitMatrix = deleteWhite(bitMatrix);//删除白边
			ServletOutputStream sos = response.getOutputStream();
			// 生成二维码
			/*File outputFile = new File("C:" + File.separator + "二维码生成" + File.separator + "yizoooFlower.png");
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);*/
			MatrixToImageWriter.writeToStreamWithLogo(bitMatrix, format, sos,logePath);
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 二维码的生成  返回一个file（生成带loge的二维码）
	 * writeToFileWithLogo
	 */
	public static String createCodeReturnFileHaveLoge(String content,int width,int height,String format,String logePath,int type) {
		HashMap hints = new HashMap();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN,0);
		String url = "";
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			//bitMatrix = deleteWhite(bitMatrix);//删除白边
			// 生成二维码
			File file = new File(logePath);
			url = MatrixToImageWriter.writeToFile(bitMatrix, format,type);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return  url;
	}
	
	/**
	 * 二维码的生成 生成保存到服务器
	 * 
	 */
	public static void createCodeNeedPath(String content,int width,int height,String format) {
		//String content = "http://192.168.1.179:8080/flowermanager/main.htm";
		//int width = 300;
		//int height = 300;
		// 二维码的图片格式
		//String format = "png";
		/**
		 * 设置二维码的参数
		 */
		HashMap hints = new HashMap();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			// 生成二维码
			File outputFile = new File("C:" + File.separator + "二维码生成" + File.separator + "yizoooFlower.png");
			//MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 二维码的解析
	 * 
	 * @param file
	 */
	public static void parseCode(File file) {
		try {
			MultiFormatReader formatReader = new MultiFormatReader();

			if (!file.exists()) {
				return;
			}

			BufferedImage image = ImageIO.read(file);

			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			Result result = formatReader.decode(binaryBitmap, hints);

			System.out.println("解析结果 = " + result.toString());
			System.out.println("二维码格式类型 = " + result.getBarcodeFormat());
			System.out.println("二维码文本内容 = " + result.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * 打印二维码 
     * @param fileName 
     * @param count 
     */  
    public static void drawImage(String fileName, int count) {  
        try {  
            DocFlavor dof = null;  
  
            if (fileName.endsWith(".gif")) {  
                dof = DocFlavor.INPUT_STREAM.GIF;  
            } else if (fileName.endsWith(".jpg")) {  
                dof = DocFlavor.INPUT_STREAM.JPEG;  
            } else if (fileName.endsWith(".png")) {  
                dof = DocFlavor.INPUT_STREAM.PNG;  
            }  
            // 获取默认打印机  
            PrintService ps = PrintServiceLookup.lookupDefaultPrintService();  
  
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();  
//          pras.add(OrientationRequested.PORTRAIT);  
//          pras.add(PrintQuality.HIGH);  
            pras.add(new Copies(count));  
            pras.add(MediaSizeName.ISO_A10); // 设置打印的纸张  
  
            DocAttributeSet das = new HashDocAttributeSet();  
            das.add(new MediaPrintableArea(0, 0, 1, 1, MediaPrintableArea.INCH));
           
            FileInputStream fin = new FileInputStream(fileName); 
  
            Doc doc = new SimpleDoc(fin, dof, das);  
            DocPrintJob job = ps.createPrintJob();  
  
            job.print(doc, pras);  
            fin.close();  
        } catch (IOException ie) {  
            ie.printStackTrace();  
        } catch (PrintException pe) {  
            pe.printStackTrace();  
        }  
    }
	//删除二维码白边
	private static BitMatrix deleteWhite(BitMatrix matrix) {
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}
}