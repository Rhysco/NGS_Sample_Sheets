# NGS_Sample_Sheets
Sample sheet generator to run samples on NGS machines

## Requirements
uses Java version 1.6.0_45

## Dependencies: 
Apache POI 3.15 for excel exporting
JavaMail (v1.5.1)

## URL:
http://poi.apache.org

## Note when updating code
* The Jar file created is launched using a Windows batch script located in L:\Auto NGS Sample sheets. If the jar file is renamed then this script will require updating to launch the correct jar file.
* The path to the jar file is specified in the batch script.

## When updating to support a new panel
* The pipelines.properties file in Y:\samplesheet-templates must be updated to include the properties used by the new pipeline to be included in the sample sheet.
