package com.heima.tess4j;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

/**
 * ClassName: application
 * Package: com.heima.tess4j
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/1/25 13:51
 * @Version 1.0
 */
public class application {

//    D:\shizhansystem\heima-leadnews\heima-leadnews-test\tess4j-dome
    public static void main(String[] args) {
        try {
            //获取本地图片
            File file = new File("D:\\shizhansystem\\heima-leadnews\\heima-leadnews-test\\tess4j-dome\\2.png");
            //创建Tesseract对象
            ITesseract tesseract = new Tesseract();
            //设置字体库路径
            tesseract.setDatapath("D:\\shizhansystem\\heima-leadnews\\heima-leadnews-test\\tess4j-dome");
            //中文识别
            tesseract.setLanguage("chi_sim");
            //执行ocr识别
            String result = tesseract.doOCR(file);
            //替换回车和tal键  使结果为一行
            result = result.replaceAll("\\r|\\n","-").replaceAll(" ","");
            System.out.println("识别的结果为："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
