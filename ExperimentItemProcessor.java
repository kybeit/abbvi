package com.example.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class ExperimentItemProcessor implements ItemProcessor<Experiment,  Experiment> {

    private static final Logger log = LoggerFactory.getLogger(ExperimentItemProcessor.class);

    @Override
    public Experiment process(final Experiment experiment) throws Exception {
        final String Phaedra_Assay_Name = experiment.getPhaedra_Assay_Name().toUpperCase();
        final int Experiment_ID = experiment.getExperiment_ID();
        final String Experiment_Name = experiment.getExperiment_Name().toUpperCase();
        final int Plate_Approved_Status = experiment.getPlate_Approved_Status();
        final String Plate_Barcode = experiment.getPlate_Barcode().toUpperCase();
        final int Plate_ID = experiment.getPlate_ID();
        final int Well_Row = experiment.getWell_Row();
        final int Well_Column = experiment.getWell_Column();
        final String Phaedra_Feature_Name = experiment.getPhaedra_Feature_Name().toUpperCase();
        final String Phaedra_Feature_Short_Name = experiment.getPhaedra_Feature_Short_Name().toUpperCase();
        final float Normalised_Value = experiment.getNormalised_Value();
        final float Concentration = experiment.getConcentration();
        final String Well_Type = experiment.getWell_Type().toUpperCase();
        final String Tabascode = experiment.getTabascode().toUpperCase();

        final Experiment transformedExperiment = new Experiment(
                Phaedra_Assay_Name,
                Experiment_ID,
                Experiment_Name,
                Plate_Approved_Status,
                Plate_Barcode,
                Plate_ID,
                Well_Row,
                Well_Column,
                Phaedra_Feature_Name,
                Phaedra_Feature_Short_Name,
                Normalised_Value,
                Concentration,
                Well_Type,
                Tabascode
        );

        log.info("Converting (" + experiment + ") into (" + transformedExperiment + ")");

        return transformedExperiment;
    }

}