package tn.esprit.spring.services;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface PhotoService {
    public String savePhoto(InputStream photo, String title) throws FlickrException;
}
