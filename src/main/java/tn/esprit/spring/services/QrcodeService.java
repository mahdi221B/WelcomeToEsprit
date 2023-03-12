package tn.esprit.spring.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.var;
import tn.esprit.spring.entity.Interview;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QrcodeService {


    public static Resource generateQRCode(Interview interview) throws IOException, WriterException {
        String qrCodePath = "C:\\Users\\ThinkPad\\Desktop\\QRCODE";
        String qrCodeName = qrCodePath+interview.getInterviewee ()+"-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(


                "Hello  : "+interview.getInterviewee ()+ "\n" +
                        "Interviwer : "+interview.getInterviewer ()+ "\n" +
                        "Your interview will be : "+interview.getFormat ()+ "\n" +
                        "Contact : : "+interview.getEmailAddress ()+ "\n" , BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        return null;
    }
}
