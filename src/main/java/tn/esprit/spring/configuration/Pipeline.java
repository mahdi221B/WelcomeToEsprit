package tn.esprit.spring.configuration;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import java.util.Properties;

@Service
public class Pipeline {
    static StanfordCoreNLP pipeline;
    private static Properties properties;
    private static StanfordCoreNLP stanfordCoreNLP;
    //The private Pipeline() {} is a private constructor in the Pipeline class.
    //This constructor is used to ensure that no instances of the Pipeline class can be created from outside the class.
    private Pipeline() {}
    //The static block initializes the properties object with settings.
    static {
        properties = new Properties();
        //comma-separated string of annotator names that will be used in the pipeline.
        //properties.setProperty("annotators","tokenize,ssplit");
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma, parse, sentiment");
        //properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
        //properties.setProperty("coref.algorithm", "neural");
        properties.setProperty("tokenize.language", "en");
        //properties.setProperty("tokenize.options", "splitHyphenated=true, americanize=true, normalizeCurrency=true");
    }
    /*The Pipeline class contains a static method named getInstance() that returns a StanfordCoreNLP object,
     which is configured with a set of annotators specified by the propertiesName variable.*/
    @Bean(name = "stanfordCoreNLP")
    public static StanfordCoreNLP getInstance() {
        /* When the getInstance() method is called for the first time, it creates a new StanfordCoreNLP
        object using the properties object, and stores it in the stanfordCoreNLP variable.*/
        if(stanfordCoreNLP == null) {
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        // Subsequent calls to getInstance() will return the same StanfordCoreNLP object.
        return stanfordCoreNLP;
    }
}