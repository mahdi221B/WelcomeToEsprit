package tn.esprit.spring.services;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.ApplicationForm;
import tn.esprit.spring.entity.JobOffer;
import tn.esprit.spring.repositories.ApplicationFormRepository;
import tn.esprit.spring.repositories.JobOfferRepository;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Paths;
import java.util.*;

@AllArgsConstructor
@Service
public class ApplicationFormImp implements IApplicationForm {


    private ApplicationFormRepository applicationFormRepository;

   // @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private JavaMailSender javaMailSender;


   /* public ApplicationForm createApplicationForm(ApplicationForm applicationForm, Long jobOfferId) {
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId).orElseThrow(() -> new EntityNotFoundException("Job offer not found with id " + jobOfferId));
        applicationForm.setJobOffer(jobOffer);
        return applicationFormRepository.save(applicationForm);
    }*/

    public ApplicationForm createApplicationForm(ApplicationForm applicationForm) {
        return applicationFormRepository.save(applicationForm);
    }

    public ApplicationForm getApplicationFormById(Long id) {
        return applicationFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ApplicationForm not found with id " + id));
    }


    public List<ApplicationForm> getAllApplicationForms() {
        return applicationFormRepository.findAll();
    }



    public void deleteApplicationFormById(Long id) {
        applicationFormRepository.deleteById(id);
    }

    public ApplicationForm updateApplicationForm(ApplicationForm applicationForm, Long id) {
        ApplicationForm existingApplicationForm = applicationFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ApplicationForm not found with id " + id));

        existingApplicationForm.setName(applicationForm.getName());
        existingApplicationForm.setEmail(applicationForm.getEmail());
        existingApplicationForm.setMotivationLetter(applicationForm.getMotivationLetter());

        return applicationFormRepository.save(existingApplicationForm);
    }


    /////////----------------------------------////////////////////////////////////////
    public ApplicationForm saveApplicationForm(ApplicationForm applicationForm) {
        return applicationFormRepository.save(applicationForm);
    }

    public JobOffer saveJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    public void assignApplicationFormToJobOffer(Long applicationFormId, Long jobOfferId) {
        ApplicationForm applicationForm = applicationFormRepository.findById(applicationFormId)
                .orElseThrow(() -> new EntityNotFoundException("ApplicationForm not found with id " + applicationFormId));

        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new EntityNotFoundException("JobOffer not found with id " + jobOfferId));

        applicationForm.getJobOffers().add(jobOffer);
        jobOffer.getApplicationForms().add(applicationForm);

        applicationFormRepository.save(applicationForm);
        jobOfferRepository.save(jobOffer);
    }

    public void createApplicationFormAndAssignToJobOffer(ApplicationForm applicationForm, Long jobOfferId) {
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new EntityNotFoundException("JobOffer not found with id " + jobOfferId));

        applicationForm.getJobOffers().add(jobOffer);
        jobOffer.getApplicationForms().add(applicationForm);

        applicationFormRepository.save(applicationForm);
        jobOfferRepository.save(jobOffer);
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////---- logique pour calculer la pertinence de la lettre de motivation---------////
    public int calculateMotivationRelevance(ApplicationForm af, String keyword) {
        String motivationLetter = af.getMotivationLetter();
        if (motivationLetter == null) {
            return 0;
        }
        int relevance = 0;
        for (String word : motivationLetter.split("\\s+")) {
            if (word.equalsIgnoreCase(keyword)) {
                relevance++;
            }
        }
        return relevance;
    }

    //----récupérer les candidatures triées par pertinence de la lettre de motivation------/////////////

    public List<ApplicationForm> findApplicationsByMotivationLetter(String keyword) {
        List<ApplicationForm> applications = applicationFormRepository.findAll();
        for (ApplicationForm af : applications) {
            int relevance = calculateMotivationRelevance(af, keyword);
            af.setMotivationRelevance(relevance);
        }
        return applicationFormRepository.findByMotivationLetterContainingOrderByMotivationRelevanceDesc(keyword);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///// méthode de préselection 'makthar'/////////////


/*
    private final String INDEX_DIRECTORY = "index";
    private final String[] SEARCH_FIELDS = {"motivationLetter"};

    public List<ApplicationForm> classifyMotivationLetters(List<ApplicationForm> applicationForms) throws Exception {
        Directory indexDirectory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
        IndexReader indexReader = DirectoryReader.open(indexDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        StandardAnalyzer analyzer = new StandardAnalyzer();

        for (ApplicationForm applicationForm : applicationForms) {
            String motivationLetter = applicationForm.getMotivationLetter();

            Map<String, Float> boostFactors = new HashMap<>();
            boostFactors.put("motivationLetter", 1.0f);

            BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();

            // Ajouter les mots clés pertinents dans les clauses de requête
            Query keywordQuery1 = new MultiFieldQueryParser(SEARCH_FIELDS, analyzer, boostFactors).parse("Java");
            queryBuilder.add(new BooleanClause(keywordQuery1, BooleanClause.Occur.SHOULD));

            Query keywordQuery2 = new MultiFieldQueryParser(SEARCH_FIELDS, analyzer, boostFactors).parse("Spring");
            queryBuilder.add(new BooleanClause(keywordQuery2, BooleanClause.Occur.SHOULD));

            Query keywordQuery3 = new MultiFieldQueryParser(SEARCH_FIELDS, analyzer, boostFactors).parse("Hibernate");
            queryBuilder.add(new BooleanClause(keywordQuery3, BooleanClause.Occur.SHOULD));

            Query keywordQuery4 = new MultiFieldQueryParser(SEARCH_FIELDS, analyzer, boostFactors).parse("SQL");
            queryBuilder.add(new BooleanClause(keywordQuery4, BooleanClause.Occur.SHOULD));

            Query keywordQuery5 = new MultiFieldQueryParser(SEARCH_FIELDS, analyzer, boostFactors).parse("agile");
            queryBuilder.add(new BooleanClause(keywordQuery5, BooleanClause.Occur.SHOULD));

            Query keywordQuery6 = new MultiFieldQueryParser(SEARCH_FIELDS, analyzer, boostFactors).parse("développement web");
            queryBuilder.add(new BooleanClause(keywordQuery6, BooleanClause.Occur.SHOULD));

            Query query = queryBuilder.build();
            TopDocs topDocs = indexSearcher.search(query, 1);

            if (topDocs.scoreDocs.length > 0) {
                ScoreDoc scoreDoc = topDocs.scoreDocs[0];
                applicationForm.setMotivationRelevance((int) scoreDoc.score);
            }
        }

        indexReader.close();
        indexDirectory.close();
        return applicationForms;
    }*/
    // System.out.println(applicationForms);
///////////////////////////----------------ca marche --------////////////////////////////////////////////////////
/*
    public List<ApplicationForm> classifyCandidates() {
        List<ApplicationForm> candidates = applicationFormRepository.findAll();
        List<ApplicationForm> classifiedCandidates = new ArrayList<>();

        for (ApplicationForm candidate : candidates) {
            int experience = candidate.getExperience();
            String noteMotivation = candidate.getMotivationLetter();

            if (experience >= 5 && noteMotivation.contains("Capacité")) {
                classifiedCandidates.add(candidate);
            } else if (experience >= 3 && noteMotivation.contains("Esprit")) {
                classifiedCandidates.add(candidate);
            } else if (experience >= 1 && noteMotivation.contains("Créativité ")) {
                classifiedCandidates.add(candidate);
            }
        }

        return classifiedCandidates;
    }
*/

    public List<ApplicationForm> classifyCandidates() {
        List<ApplicationForm> candidates = applicationFormRepository.findAll();
        List<ApplicationForm> classifiedCandidates = new ArrayList<>();

        // mots-clés positifs
        List<String> positiveKeywords = Arrays.asList("test", "positif");

        for (ApplicationForm candidate : candidates) {
            int experience = candidate.getExperience();
            String noteMotivation = candidate.getMotivationLetter().toLowerCase();

            // vérifie s'il contient des mots-clés positifs
            boolean containsPositiveKeyword = positiveKeywords.stream().anyMatch(noteMotivation::contains);

            if (experience >= 5 && containsPositiveKeyword) {
                classifiedCandidates.add(candidate);
            } else if (experience >= 3 && containsPositiveKeyword) {
                classifiedCandidates.add(candidate);
            }
        }
        sendEmails(classifiedCandidates);
        return classifiedCandidates;
    }
    private void sendEmails(List<ApplicationForm> classifiedCandidates) {
        String subject = "Congratulations! You have been classified!";
        String text = "Dear %s,\n\nCongratulations! Your application has been classified as a strong candidate.\n\nbook an appointment:\n\nhttp://localhost:8088/springMVC/reservations/send\n\nBest regards,\nThe HR team";

        for (ApplicationForm candidate : classifiedCandidates) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(candidate.getEmail());
            message.setSubject(subject);
            message.setText(String.format(text, candidate.getName()));

            javaMailSender.send(message);
        }
    }

}
