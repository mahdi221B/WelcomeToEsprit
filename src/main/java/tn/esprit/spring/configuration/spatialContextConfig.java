package tn.esprit.spring.configuration;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.context.SpatialContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class spatialContextConfig {
    @Bean
    public SpatialContext spatialContext() {
        SpatialContextFactory spatialContextFactory = new SpatialContextFactory();
        spatialContextFactory.geo = true;
        return spatialContextFactory.newSpatialContext();
    }
}