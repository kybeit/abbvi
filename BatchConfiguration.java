package com.example.batchprocessing;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer{
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @Nullable
    public JdbcCursorItemReader<Experiment> dbReader(@Qualifier("sourceDB") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Experiment>()
                .name("experimentReader")
                .dataSource(dataSource)
                .sql("SELECT Phaedra_Assay_Name, " +
                        "Experiment_ID, "  +
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
                        "Tabascode " +
                        "from experiment;")
                .rowMapper(new BeanPropertyRowMapper<>(Experiment.class))
                .build();
    }

    @Bean
    public ExperimentItemProcessor processor() {
        return new ExperimentItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Experiment> writer(@Qualifier("targetDB") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Experiment>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO experiment (Phaedra_Assay_Name, " +
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
                        "Tabascode )" +
                        "VALUES (:Phaedra_Assay_Name, " +
                        ":Experiment_ID, " +
                        ":Experiment_Name, " +
                        ":Plate_Approved_Status, " +
                        ":Plate_Barcode, " +
                        ":Plate_ID, " +
                        ":Well_Row, " +
                        ":Well_Column, " +
                        ":Phaedra_Feature_Name, " +
                        ":Phaedra_Feature_Short_Name, " +
                        ":Normalised_Value, " +
                        ":Concentration, " +
                        ":Well_Type, " +
                        ":Tabascode); ")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    @Qualifier("loadStep")
    public Step loadStep(JdbcCursorItemReader<Experiment> reader, JdbcBatchItemWriter<Experiment> writer) {
        return stepBuilderFactory.get("loadStep")
                .<Experiment, Experiment> chunk(10)
                .reader(reader)
                .processor(processor())
                .writer(writer)
                        .build();
    }

    @Bean
    @Qualifier("truncateStep")
    public Step truncateStep(@Qualifier("targetDB") DataSource dataSource) {
        return stepBuilderFactory
                .get("truncateStep")
                .tasklet((stepContribution, chunkContext) -> {
                    new JdbcTemplate(dataSource).execute("TRUNCATE TABLE experiment");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener,
                             @Qualifier("truncateStep")
                                     Step step0,
                             @Qualifier("loadStep")
                                     Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step0)
                .next(step1)
                .build();
    }


}