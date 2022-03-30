package com.example.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Experiment {
    private String Phaedra_Assay_Name;
    private int    Experiment_ID;
    private String Experiment_Name;
    private int    Plate_Approved_Status;
    private String Plate_Barcode;
    private int    Plate_ID;
    private int    Well_Row;
    private int    Well_Column;
    private String Phaedra_Feature_Name;
    private String Phaedra_Feature_Short_Name;
    private float  Normalised_Value;
    private float  Concentration;
    private String Well_Type;
    private String Tabascode;
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    public Experiment() {
    }

    public Experiment(String Phaedra_Assay_Name,
                      int    Experiment_ID,
                      String Experiment_Name,
                      int    Plate_Approved_Status,
                      String Plate_Barcode,
                      int    Plate_ID,
                      int    Well_Row,
                      int    Well_Column,
                      String Phaedra_Feature_Name,
                      String Phaedra_Feature_Short_Name,
                      float  Normalised_Value,
                      float  Concentration,
                      String Well_Type,
                      String Tabascode) {
        this.Phaedra_Assay_Name = Phaedra_Assay_Name;
        this.Experiment_ID =  Experiment_ID;
        this.Experiment_Name = Experiment_Name;
        this.Plate_Approved_Status = Plate_Approved_Status;
        this.Plate_Barcode = Plate_Barcode;
        this.Plate_ID = Plate_ID;
        this.Well_Row = Well_Row;
        this.Well_Column = Well_Column;
        this.Phaedra_Feature_Name = Phaedra_Feature_Name;
        this.Phaedra_Feature_Short_Name = Phaedra_Feature_Short_Name;
        this.Normalised_Value = Normalised_Value;
        this.Concentration = Concentration;
        this.Well_Type = Well_Type;
        this.Tabascode = Tabascode;
    }

    public void setPhaedra_Assay_Name(String Phaedra_Assay_Name) {
        //log.info("-set-Phaedra_Assay_Name: " + Phaedra_Assay_Name);
        this.Phaedra_Assay_Name = Phaedra_Assay_Name;
    }
    public String getPhaedra_Assay_Name() {
        return Phaedra_Assay_Name;
    }

    public void setExperiment_ID(int Experiment_ID) {
        this.Experiment_ID = Experiment_ID;
    }

    public int getExperiment_ID() {
        return Experiment_ID;
    }

    public void setExperiment_Name(String Experiment_Name) {
        this.Experiment_Name = Experiment_Name;
    }

    public String getExperiment_Name() {
        return Experiment_Name;
    }

    public void setPlate_Approved_Status(int Plate_Approved_Status) {
        this.Plate_Approved_Status = Plate_Approved_Status;
    }

    public int getPlate_Approved_Status() {
        return Plate_Approved_Status;
    }

    public void setPlate_Barcode(String Plate_Barcode) {
        //log.info("-set-Plate_Barcode: " + Plate_Barcode);
        this.Plate_Barcode = Plate_Barcode;
    }

    public String getPlate_Barcode() {
        return Plate_Barcode;
    }

    public void setPlate_ID(int Plate_ID) {
        this.Plate_ID = Plate_ID;
    }

    public int getPlate_ID() {
        return Plate_ID;
    }

    public void setWell_Row(int Well_Row) {
        this.Well_Row = Well_Row;
    }

    public int getWell_Row() {
        return Well_Row;
    }

    public void setWell_Column(int Well_Column) {
        this.Well_Column = Well_Column;
    }

    public int getWell_Column() {
        return Well_Column;
    }

    public void setPhaedra_Feature_Name(String Phaedra_Feature_Name) {
        this.Phaedra_Feature_Name = Phaedra_Feature_Name;
    }

    public String getPhaedra_Feature_Name() {
        return Phaedra_Feature_Name;
    }

    public void setPhaedra_Feature_Short_Name(String Phaedra_Feature_Short_Name) {
        this.Phaedra_Feature_Short_Name = Phaedra_Feature_Short_Name;
    }

    public String getPhaedra_Feature_Short_Name() {
        return Phaedra_Feature_Short_Name;
    }

    public void setNormalised_Value(float Normalised_Value) {
        //log.info("-set-Normalised_Value: " + Normalised_Value);
        this.Normalised_Value = Normalised_Value;
    }

    public float getNormalised_Value() {
        //log.info("-get-ThirdCount: " + Normalised_Value);
        return Normalised_Value;
    }

    public void setConcentration(float Concentration) {
        //log.info("-set-Concentration: " + Concentration);
        this.Concentration = Concentration;
    }

    public float getConcentration() {
        //log.info("-get-Concentration: " + Concentration);
        return Concentration;
    }

    public void setWell_Type(String Well_Type) {
        this.Well_Type = Well_Type;
    }

    public String getWell_Type() {
        return Well_Type;
    }

    public void setTabascode(String Tabascode) {
        this.Tabascode = Tabascode;
    }

    public String getTabascode() {
        return Tabascode;
    }

    @Override
    public String toString() {
        return " Phaedra_Assay_Name: " + Phaedra_Assay_Name + ", Experiment_ID: " + Experiment_ID + ", Experiment_Name: " + Experiment_Name +
                ", Plate_Approved_Status: " + Plate_Approved_Status + ", Plate_Approved_Status: " + Plate_Approved_Status + ", Plate_Barcode: " + Plate_Barcode +
        ", Plate_ID: " + Plate_ID + ", Well_Row: " + Well_Row + ", Well_Column: " + Well_Column + ", Phaedra_Feature_Name: " + Phaedra_Feature_Name +
                ", Phaedra_Feature_Short_Name: " + Phaedra_Feature_Short_Name + ", Normalised_Value: " + Normalised_Value +
                ", Concentration: " + Concentration + ", Well_Type:" + Well_Type + ", Tabascode: " + Tabascode;

    }
}