package com.example.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;


import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

import static org.springframework.util.ResourceUtils.getURL;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("自定义输出==========");
	// 	char[] cs = new char[26];
    //     for (int i = 0; i < cs.length; i++) {
	// 		cs[i]=(char) ('A'+i);			
	// 		String str=String.valueOf( (char) ('A'+i));
	// 		System.out.println(str);
    //     }
	//    System.out.println(cs);//输出
	
		// addLocation(6);
	

	}

	static void addLocation(int num){
		List<String> list=new ArrayList<>();
		char [] c=new char[2];
		c[0]='A';
		c[1]='B';

		// char[] full=getChar(26);
		char [] all=new  char[26];
		for (int i=0;i<c.length;i++) {
			all[((int)c[i])-65]=c[i];
		}
		int tip=1;
		for(int i=0;i<all.length;i++){
			if(tip<=num){			
				if((int)all[i]<=0){
					char temp=(char) (i+65);
					String stemp=String.valueOf(temp);
					list.add(stemp);
					tip++;
				}
			}else{
				break;
			}
		}
		System.out.println( list.size());	
		int ddd=0;
	}
	
	private int[] getArray(int length) {
        int[] array = new int[26];
        for (int i = 0; i < length; i++) {
            array[i] = i + 65;
        }
        return array;
	}
	static char[] getChar(int length) {
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = (char)('A' + i);
        }
        return array;
    }

	
}
