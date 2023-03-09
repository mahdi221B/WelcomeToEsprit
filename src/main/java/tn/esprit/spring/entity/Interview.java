package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Interview implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String interviewer;
    private String interviewee;
    @Enumerated(EnumType.STRING)
    private Format format;
    private String Feedback;
    private float score;
    private int communicationScore;
    private int technicalScore;
    private int professionalismScore;

    @Column(name = "emailaddress")
    String emailAddress;
    @Enumerated(EnumType.STRING)
    private CandidatType candidatType;


    public Interview(String interviewer, String interviewee, Format format, String feedback, float score, int communicationScore, int technicalScore, int professionalismScore, CandidatType candidatType) {
        this.interviewer = interviewer;
        this.interviewee = interviewee;
        this.format = format;
        Feedback = feedback;
        this.score = score;
        this.communicationScore = communicationScore;
        this.technicalScore = technicalScore;
        this.professionalismScore = professionalismScore;
        this.candidatType = candidatType;

    }
    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

