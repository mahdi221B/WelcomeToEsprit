package tn.esprit.spring.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.swagger.annotations.Api;
import javafx.scene.transform.MatrixType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.UserService;
import tn.esprit.spring.utils.QrcodeGenerated;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static tn.esprit.spring.utils.Constants.APP_ROOT;

@RestController
@Api
public class QRCodeController {

    private UserRepository userRepository;
    private UserService userService;
    @Autowired
    public QRCodeController(UserRepository userRepository,UserService userService) {
        this.userRepository = userRepository;
        this.userService=userService;
    }

    @GetMapping(APP_ROOT + "/qrcode")
    public void generateQrcode(HttpServletResponse response, @RequestParam("data") String identifier) throws IOException, WriterException {
        response.setContentType("image/png");
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        User user=userRepository.findUserByIdentifierContains(identifier);
        BitMatrix bitMatrix=qrCodeWriter.encode(user.getEmailAddress(), BarcodeFormat.QR_CODE,200,200);
        MatrixToImageWriter.writeToStream(bitMatrix,"png", response.getOutputStream());

        //user.setQrcode(QrcodeGenerated.matrixToString(bitMatrix));
        //userService.updateUser(user);
    }
}
