package tn.esprit.spring.services.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.esprit.spring.services.PhotoService;

import java.io.InputStream;
@Service
public class PhotoServiceImpl implements PhotoService {

    @Value("${flickr.apiKey}")
    private String apiKey;
    @Value("${flickr.apiSecret}")
    private String apiSecret;
    @Value("${flickr.appKey}")
    private String appKey;
    @Value("${flickr.appSecret}")
    private String appSecret;
    private Flickr flickr;
    @Autowired
    public PhotoServiceImpl(Flickr flickr){
        this.flickr=flickr;
    }
    @Override
    public String savePhoto(InputStream photo, String title) throws FlickrException {
        UploadMetaData uploadMetaData=new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId=flickr.getUploader().upload(photo,uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }
}
