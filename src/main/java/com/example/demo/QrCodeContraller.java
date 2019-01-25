package com.example.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Random;
// import com.alibaba.druid.util.Base64;




@RequestMapping("/home")
@RestController
public class QrCodeContraller {
    private static final String CHARSET = "utf-8";
    private static final int QRCODE_SIZE = 300;
    private static final String FORMAT = "png";

    /**
     * 创建一个二维码，保存为图片
     * @return
     */
    @GetMapping(value = "/index")
    public  String createQRCodeSaveAsImg(){
        System.out.println("自定义输出=============");
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix=null;
        try {
            bitMatrix = new MultiFormatWriter().encode("www.66123123.com", BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                    hints);
        }catch (Exception ex){
            System.out.println("bitMatrix报异常");
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        String fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        System.out.println(fileName);
        try {
            ImageIO.write(image, FORMAT, new File("D:\\temp" + "/" + fileName));
        }catch (Exception ex){
            System.out.println("写图片报异常");
        }
        System.out.println("结束");
        return "ok";
    }


    /**
     * 创建一个二维码，输出图片流
     * @return
     */
    @GetMapping(value = "/streambyte")
    @ResponseBody
    public  byte[] createQRCodeOutpuStream(){
        System.out.println("自定义输出=============");
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix=null;
        try {
            bitMatrix = new MultiFormatWriter().encode("www.66123123.com", BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                    hints);
        }catch (Exception ex){
            System.out.println("bitMatrix报异常");
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        String fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        System.out.println(fileName);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, FORMAT, outStream);
        }catch (Exception ex){
            System.out.println("写图片报异常");
        }
        System.out.println("结束");
        byte[] bytes = outStream.toByteArray();
        return bytes;
    }


    /**
     * 创建一个二维码，输出图片base64的字符串
     * @return
     */
    @GetMapping(value = "/streambase64")
    @ResponseBody
    public  String createQRCodeOutpuBase64(){
        System.out.println("自定义输出=============");
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix=null;
        try {
            bitMatrix = new MultiFormatWriter().encode("www.66123123.com", BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                    hints);
        }catch (Exception ex){
            System.out.println("bitMatrix报异常");
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        String fileName = new Random().nextInt(99999999) + "." + FORMAT.toLowerCase();
        System.out.println(fileName);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, FORMAT, outStream);
        }catch (Exception ex){
            System.out.println("写图片报异常");
        }
        System.out.println("结束");
        byte[] bytes = outStream.toByteArray();
        BASE64Encoder encoder=new BASE64Encoder();

        //  String base64Str= Base64.byteArrayToBase64(bytes);

         return  encoder.encode(bytes);

        // return base64Str;
    }


    @GetMapping(value = "/test")
    @ResponseBody
    public ResponseEntity<?> test(HttpServletRequest request){
//        return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        return null;
    }
}
