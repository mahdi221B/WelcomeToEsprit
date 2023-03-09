package tn.esprit.spring.services;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerModel;
import tn.esprit.spring.entity.Mcq;
import tn.esprit.spring.entity.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Mcq;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.repositories.McqRepository;
import tn.esprit.spring.repositories.QuestionRepository;


import opennlp.tools.tokenize.TokenizerME;



@Slf4j
@AllArgsConstructor
@Service
public class McqService implements IMcqService{
    private final QuestionRepository questionRepository;
    private final McqRepository mcqRepository;



    @Override
    public Mcq generateMcq(String diplomaTitle) throws IOException {
        // Tokenize the diploma title
        InputStream modelIn = new FileInputStream("C:\\en-token.bin");
        TokenizerModel model = new TokenizerModel(modelIn);
        Tokenizer tokenizer = new TokenizerME(model);
        String[] tokens = tokenizer.tokenize("software");
        System.out.println(tokens);
        // Retrieve questions containing any of the keywords
        List<Question> matchingQuestions   =new ArrayList<>();
        //= questionRepository.findByKeywords(tokens);
        for(String s : tokens) {
            List<Question> listeQuestionsFromS = questionRepository.findByKeywords(s) ;
            for (Question q : listeQuestionsFromS) {
                if(!((matchingQuestions.contains(q)))){
                    matchingQuestions.add(q) ;
                }
            }
        }
        // Randomly select up to 3 questions from the matching questions
        Collections.shuffle(matchingQuestions);
        int numQuestions = Math.min(3, matchingQuestions.size());
        List<Question> selectedQuestions = matchingQuestions.subList(0, numQuestions);
        // Create a new MCQ
        Mcq mcq = new Mcq();
        mcq.setMcqTitle(diplomaTitle);


        // Add the selected questions to the MCQ
        mcq.setQuestions(selectedQuestions);
        // Set the MCQ for each selected question
        for (Question question : selectedQuestions) {
            question.getMcqs().add(mcq);
        }
        System.out.println("*********** " +selectedQuestions.size());
        mcqRepository.save(mcq) ;
        // Save the MCQ to the database
        return mcq;


    }


}

