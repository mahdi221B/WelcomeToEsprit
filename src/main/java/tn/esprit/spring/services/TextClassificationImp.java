package tn.esprit.spring.services;


import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.ApplicationForm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class TextClassificationImp {
  /* private static final int VECTOR_SIZE = 100;
    private WordVectors wordVectors;

    public TextClassificationImp() {
        File wordVectorsFile = new File("C:/Users/ce pc/IdeaProjects/cc.fr.300.bin");
        wordVectors = WordVectorSerializer.readWord2VecModel(wordVectorsFile);
    }

    public ApplicationForm classifyCandidates(ApplicationForm candidate) {
        //for (ApplicationForm candidate : candidates) {
            INDArray vector = getVector(candidate.getMotivationLetter());
            double score = wordVectors.similarity("motivation", vector.toString());
            candidate.setScore(score);
        //}
        //candidates.sort((c1, c2) -> Double.compare(c2.getScore(), c1.getScore()));
        return candidate;
    }

    private INDArray getVector(String text) {
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());
        List<String> tokens = tokenizerFactory.create(text).getTokens();
        List<INDArray> vectors = new ArrayList<>();
        for (String token : tokens) {
            if (wordVectors.hasWord(token)) {
                INDArray vector = wordVectors.getWordVectorMatrix(token);
                vectors.add(vector);
            }
        }
        INDArray array = Nd4j.vstack(vectors);
        INDArray mean = array.mean(0);
        return Transforms.unitVec(mean);
    }*/
}