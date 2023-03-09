package tn.esprit.spring.controllers;

import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.Interview;
import tn.esprit.spring.services.IInterviewService;
import tn.esprit.spring.services.QrcodeService;

import javax.annotation.Resource;
import java.io.IOException;
@RestController
@RequestMapping("/codeqr")
public class CodeQrController {

    private final IInterviewService iInterviewService;

    public CodeQrController(IInterviewService iInterviewService) {
        this.iInterviewService = iInterviewService;
    }

    @GetMapping("/{id}/qrcode")
    public ResponseEntity<Resource> getQRCode(@PathVariable Integer id) {
        Interview interview = iInterviewService.getInterviewById(id);
        if (interview == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Resource qrCodeResource = QrcodeService.generateQRCode(interview);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(qrCodeResource);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}