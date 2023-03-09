package tn.esprit.spring.services;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
public class JobCompletionNotificationListener  implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // This method is called before the job starts
        System.out.println("Starting job: " + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // This method is called after the job ends
        System.out.println("Job completed with status: " + jobExecution.getStatus());
    }
}
