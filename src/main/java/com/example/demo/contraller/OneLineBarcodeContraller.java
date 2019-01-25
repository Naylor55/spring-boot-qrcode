package com.example.demo.contraller;

import org.springframework.core.codec.ByteArrayEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.tomcat.util.codec.binary.Base64;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * 生成一维码
 */
@RequestMapping("/oneline/")
@RestController
public class OneLineBarcodeContraller {

    /**
     * 一维码内容
     */
    private String barcodeContent = "A-01-05-005-555";

    @GetMapping(value = "index")
    public String index() {
        return "生成一维码示例代码-控制器";
    }

    /**
     * 保存图片到图片文件
     * @return
     */
    @GetMapping(value="file")
    public String  saveAsFile(){
        int isok=1;
        String filePath="D:/temp/";
        Date dNow = new Date( );
        SimpleDateFormat ft =  new SimpleDateFormat ("yyyyMMddhhmmssS");
        String fileName=ft.format(new Date())+".png";
        byte[] bytes=saveAsByteArray();
        File file=new File(filePath+fileName);
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(file);
            imageOutput.write(bytes, 0, bytes.length);
            imageOutput.close();
        } catch (Exception e) {
            System.out.println("保存到文件失败，异常信息为："+e.getMessage());
            isok=0;
        }
        String str=isok==1?"成功":"失败";
        return "图片保存到文件【"+str+"】";
    }

    /**
     * 保存图片为base64的字符串
     * @return
     */
    @GetMapping(value="string")
    public String saveAsBase64() {
        byte[] bytes=saveAsByteArray();
        String str=Base64.encodeBase64String(bytes);
        return str;   
    }

    /**
     * 保存图片到字节数组
     * @return
     */
    private byte[] saveAsByteArray(){
       byte[] bytes=createBarcode().toByteArray();
       return bytes;
    }


    /**
     * 创建一维码
     * @param ous
     */
    private ByteArrayOutputStream  createBarcode(){
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        /**  精细度  */
        final  int dpi=150;
        /**  module宽度  */
        final double moduleWidth=UnitConv.in2mm(1.0f/dpi);
        Code39Bean bean=new Code39Bean();
        /**  配置对象  */
        bean.setModuleWidth(moduleWidth);
        bean.setWideFactor(3);
        bean.doQuietZone(false);
        String mime="image/png";
        try {
            /** 输出到流  */
            BitmapCanvasProvider canvas=new BitmapCanvasProvider(ous, mime, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            /** 生成一维码  */
            bean.generateBarcode(canvas, barcodeContent);
            /** 结束绘制 */
            canvas.finish();
        } catch (Exception e) {
            System.out.println("创建一维码失败，异常信息为："+e.getMessage());
        }
        return ous;
    }

    

}