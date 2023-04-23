package tn.esprit.spring.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.persistence.Column;

public class QrcodeGenerated {


    String qrcode;
    public static String matrixToString(BitMatrix matrix) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < matrix.getHeight(); y++) {
            for (int x = 0; x < matrix.getWidth(); x++) {
                if (matrix.get(x, y)) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
        }
        return sb.toString();
    }
    public static BitMatrix stringToMatrix(String qrCodeString) {
        int width = (int) Math.sqrt(qrCodeString.length());
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(qrCodeString, BarcodeFormat.QR_CODE, width, width);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        for (int y = 0; y < bitMatrix.getHeight(); y++) {
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                char bit = qrCodeString.charAt(y * width + x);
                if (bit == '1') {
                    bitMatrix.set(x, y);
                }
            }
        }
        return bitMatrix;
    }
}
