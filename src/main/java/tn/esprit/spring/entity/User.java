package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import tn.esprit.spring.utils.QrcodeGenerated;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity{
    @Column(name = "lastname")
    String lastName;
    @Column(name = "firstname")
    String firstName;
    @Column(name = "emailAddress")
    String emailAddress;
    @Column(name = "identifier")
    @JsonIgnore
    String identifier;
    @Column(name = "identityCardNumber")
    String nci;
    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    Date birthDate;
    @Column(name = "password")
    String password;

    @Embedded
    Address address;
    @Column(name = "active")
    @JsonIgnore
    boolean active;
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Role> roles;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Picture> picture;

    @PrePersist
    void generateIdentifier(){
        identifier=firstName.substring(0,1).toUpperCase() + lastName.substring(0,2).toLowerCase() + nci;
        //generateQrCode(emailAddress);
        //generateToken();
    }

    /**  public void generateQrCode(String text) {
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        try {
            BitMatrix bitMatrix=qrCodeWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            Qrcode= QrcodeGenerated.matrixToString(bitMatrix);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }*/

   /** public void generateToken(){
        UUID uuid=UUID.randomUUID();
        token=uuid.toString();
    }*/
}
