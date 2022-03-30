package com.example.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(@Qualifier("targetDB") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT Phaedra_Assay_Name, " +
                            "Experiment_ID, " +
                            "Experiment_Name, " +
                            "Plate_Approved_Status, " +
                            "Plate_Barcode, " +
                            "Plate_ID, " +
                            "Well_Row, " +
                            "Well_Column, " +
                            "Phaedra_Feature_Name, " +
                            "Phaedra_Feature_Short_Name, " +
                            "Normalised_Value, " +
                            "Concentration, " +
                            "Well_Type, " +
                            "Tabascode FROM experiment",
                    (rs, row) -> new Experiment(
                            rs.getString(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getInt(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getFloat(11),
                            rs.getFloat(12),
                            rs.getString(13),
                            rs.getString(14)
            )).forEach(experiment -> log.info("Found <" + experiment + "> in the database."));
        }
    }
}