package cn.com.emindsoft.util;


import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * 滑块验证工具类
 *
 */
public class VerifyImageUtil {
    private static int ORI_WIDTH = 350;
    private static int ORI_HEIGHT = 200;
    private static int X;
    private static int Y;
    private static int WIDTH;
    private static int HEIGHT;
    private static float xPercent;
    private static float yPercent;


    public static int getX() {
        return X;
    }

    public static int getY() {
        return Y;
    }

    public static float getxPercent() {
        return xPercent;
    }

    public static float getyPercent() {
        return yPercent;
    }


    public static Map<String, byte[]> pictureTemplatesCut(File templateFile, File targetFile, String templateType, String targetType) throws Exception {
        Map<String, byte[]> pictureMap = new HashMap<>();

        String templateFiletype = templateType;
        String oriFiletype = targetType;
        if (StringUtils.isEmpty(templateFiletype) || StringUtils.isEmpty(oriFiletype)) {
            throw new RuntimeException("file type is empty");
        }

        File Orifile = targetFile;
        InputStream oriis = new FileInputStream(Orifile);

        BufferedImage imageTemplate = ImageIO.read(templateFile);
        WIDTH = imageTemplate.getWidth();
        HEIGHT = imageTemplate.getHeight();
        generateCutoutCoordinates();

        BufferedImage newImage = new BufferedImage(WIDTH, HEIGHT, imageTemplate.getType());
        Graphics2D graphics = newImage.createGraphics();
        graphics.setBackground(Color.WHITE);

        int bold = 5;

        BufferedImage targetImageNoDeal = getTargetArea(X, Y, WIDTH, HEIGHT, oriis, oriFiletype);

        newImage = DealCutPictureByTemplate(targetImageNoDeal, imageTemplate, newImage);

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(bold, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        graphics.drawImage(newImage, 0, 0, null);
        graphics.dispose();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(newImage, "png", os);
        byte[] newImages = os.toByteArray();
        pictureMap.put("slideImg", newImages);


        BufferedImage oriImage = ImageIO.read(Orifile);
        byte[] oriCopyImages = DealOriPictureByTemplate(oriImage, imageTemplate, X, Y);
        pictureMap.put("backImg", oriCopyImages);
        return pictureMap;
    }


    private static byte[] DealOriPictureByTemplate(BufferedImage oriImage, BufferedImage templateImage, int x,
                                                   int y) throws Exception {

        BufferedImage ori_copy_image = new BufferedImage(oriImage.getWidth(), oriImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        int[][] oriImageData = getData(oriImage);

        int[][] templateImageData = getData(templateImage);

        for (int i = 0; i < oriImageData.length; i++) {
            for (int j = 0; j < oriImageData[0].length; j++) {
                int rgb = oriImage.getRGB(i, j);
                int r = (0xff & rgb);
                int g = (0xff & (rgb >> 8));
                int b = (0xff & (rgb >> 16));

                rgb = r + (g << 8) + (b << 16) + (255 << 24);
                ori_copy_image.setRGB(i, j, rgb);
            }
        }

        for (int i = 0; i < templateImageData.length; i++) {
            for (int j = 0; j < templateImageData[0].length - 5; j++) {
                int rgb = templateImage.getRGB(i, j);

                if (rgb != 16777215 && rgb <= 0) {
                    int rgb_ori = ori_copy_image.getRGB(x + i, y + j);
                    int r = (0xff & rgb_ori);
                    int g = (0xff & (rgb_ori >> 8));
                    int b = (0xff & (rgb_ori >> 16));
                    rgb_ori = r + (g << 8) + (b << 16) + (10 << 24);
                    ori_copy_image.setRGB(x + i, y + j, rgb_ori);
                } else {
                    //do nothing
                }
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(ori_copy_image, "png", os);
        byte b[] = os.toByteArray();
        return b;
    }

    private static BufferedImage DealCutPictureByTemplate(BufferedImage oriImage, BufferedImage templateImage,
                                                          BufferedImage targetImage) throws Exception {

        int[][] oriImageData = getData(oriImage);

        int[][] templateImageData = getData(templateImage);

        for (int i = 0; i < templateImageData.length; i++) {

            for (int j = 0; j < templateImageData[0].length; j++) {

                int rgb = templateImageData[i][j];
                if (rgb != 16777215 && rgb <= 0) {
                    targetImage.setRGB(i, j, oriImageData[i][j]);
                }
            }
        }
        return targetImage;
    }


    private static BufferedImage getTargetArea(int x, int y, int targetWidth, int targetHeight, InputStream ois,
                                               String filetype) throws Exception {
        Iterator<ImageReader> imageReaderList = ImageIO.getImageReadersByFormatName(filetype);
        ImageReader imageReader = imageReaderList.next();

        ImageInputStream iis = ImageIO.createImageInputStream(ois);

        imageReader.setInput(iis, true);

        ImageReadParam param = imageReader.getDefaultReadParam();
        Rectangle rec = new Rectangle(x, y, targetWidth, targetHeight);
        param.setSourceRegion(rec);
        BufferedImage targetImage = imageReader.read(0, param);
        return targetImage;
    }


    private static int[][] getData(BufferedImage bimg) throws Exception {
        int[][] data = new int[bimg.getWidth()][bimg.getHeight()];
        for (int i = 0; i < bimg.getWidth(); i++) {
            for (int j = 0; j < bimg.getHeight(); j++) {
                data[i][j] = bimg.getRGB(i, j);
            }
        }
        return data;
    }


    private static void generateCutoutCoordinates() {
        Random random = new Random();
        int widthDifference = ORI_WIDTH - WIDTH;
        int heightDifference = ORI_HEIGHT - HEIGHT;

        if (widthDifference <= 0) {
            X = 50;

        } else {
            X = random.nextInt(ORI_WIDTH - WIDTH) + 50;
        }

        if (heightDifference <= 0) {
            Y = 5;
        } else {
            Y = random.nextInt(ORI_HEIGHT - HEIGHT) + 5;
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);

        xPercent = Float.parseFloat(numberFormat.format((float) X / (float) ORI_WIDTH));
        yPercent = Float.parseFloat(numberFormat.format((float) Y / (float) ORI_HEIGHT));
    }
}


