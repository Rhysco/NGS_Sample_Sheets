/**
 * 
 */
package nhs.cardiff.genetics.ngssamplesheets;

/**
 * @author Rhys Cooper
 * @Date 17/02/2017
 * @version 1.2
 * 
 */
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JLabel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportSampleSheet {
	private int rowNum, step;
	private String filepath;
	private String worksheetName;
	private String fileSave;
	//private String fileClusterSave;
	@SuppressWarnings("unused")
	private String assay;
	private String trusightPipeline;
	private String trusightOnePipeline;
	private String focus4Pipeline;
	private String wcbPipeline;
	private String brcaPipeline;
	private String tamPipeline;
	
	public ExportSampleSheet() {
		rowNum = 0;
		// step for skipping other data in the array
		step = 6;
		filepath = "";
	}
	
	/**
	 * @category Loads pipeline properties for the generator
	 */
	public void properties(){
		Properties properties = new Properties();
		try {
		  properties.load(new FileInputStream("L:\\Auto NGS Sample sheets\\pipelines.properties"));
		  trusightPipeline = properties.getProperty("TRUSIGHT");
		  trusightOnePipeline = properties.getProperty("TRUSIGHTONE");
		  focus4Pipeline = properties.getProperty("FOCUS4");
		  wcbPipeline = properties.getProperty("WCB");
		  brcaPipeline = properties.getProperty("BRCA");
		  tamPipeline = properties.getProperty("TAM");
		  
		} catch (IOException e) {
			//
		}
	}

	/**
	 * 
	 * @param workbook HSSFWorkBook Object
	 * @param type String denoting type of file to be exported - Analysis or not
	 * @param assay String denoting assay type
	 * @throws IOException Thrown if file cannot be saved
	 */
	public void save(HSSFWorkbook workbook, String type, String assay) throws IOException {
		if (assay.equals("CRUK")) {
			filepath = "L:\\Auto NGS Sample sheets\\CRUK\\";
		} else if (assay.equals("Trusight")) {
			filepath = "L:\\Auto NGS Sample sheets\\Trusight\\";
		} else if (assay.equals("TAM")) {
			filepath = "L:\\Auto NGS Sample sheets\\TAM\\";
		} else if (assay.equals("WCB")) {
			filepath = "L:\\Auto NGS Sample sheets\\WCB\\";
		} else if (assay.equals("Trusightone")) {
			filepath = "L:\\Auto NGS Sample sheets\\Trusight One\\";
		}
		
		if (type.equals("analysis")) {
			fileSave = ("L:\\CRUK\\Nextera\\worksheets\\Analysis SampleSheets\\" + worksheetName + "_analysis" + ".xls");
			// save copy to cluster
			//fileClusterSave = ("Y:\\samplesheet\\" + worksheetName + "_analysis" + ".xls");
		} else {
			fileSave = (filepath + worksheetName + ".xls");
			// save copy to cluster
			//fileClusterSave = ("Y:\\samplesheet\\" + worksheetName + ".xls");
		}
		// Save to shared drive
		workbook.setSheetName(0, worksheetName);
		FileOutputStream fileOut = new FileOutputStream(fileSave);
		workbook.write(fileOut);
		workbook.close();
		fileOut.flush();
		fileOut.close();
		
		// Save to cluster
//		workbook.setSheetName(0, worksheetName);
//		FileOutputStream fileOutCluster = new FileOutputStream(fileClusterSave);
//		workbook.write(fileOutCluster);
//		workbook.close();
//		fileOutCluster.flush();
//		fileOutCluster.close();
		
		// open file
		Desktop dt = Desktop.getDesktop();
		dt.open(new File(fileSave));


	}

	/**
	 * 
	 * @param results ArrayList<String> containing results of the Shire database search for samples on worksheet
	 * @param infoField JLabel field for feedback information 
	 */
	public void exportCRUK(ArrayList<String> results, JLabel infoField) {
		try {
			properties();
			FileInputStream fileIn = new FileInputStream("L:\\SampleSheetTemplates\\TemplatesForAuto\\CRUK-SeqOnly.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
			HSSFSheet worksheet = workbook.getSheet("Sheet1");

			HSSFRow row = worksheet.getRow(2);
			HSSFCell cell = row.createCell(1);
			cell.setCellValue(results.get(3) + "-NHS");

			row = worksheet.getRow(3);
			cell = row.createCell(1);
			cell.setCellValue(results.get(1));
			worksheetName = (results.get(1));
			
			row = worksheet.getRow(4);
			cell = row.createCell(1);
			cell.setCellValue(results.get(5));

			rowNum = 17;
			for (int i = 0; i < results.size(); i += step) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue(results.get(0 + i));
				cell = row.createCell(1);
				cell.setCellValue(results.get(0 + i));
				cell = row.createCell(2);
				cell.setCellValue(results.get(1 + i));
				rowNum += 1;
			}

			save(workbook, "", "CRUK");
			
		} catch (Exception e) {
			infoField.setVisible(true);
			infoField.setText("Could not find a valid template sheet");
			System.out.print(e);
		}
	}

	/**
	 * 
	 * @param results ArrayList<String> containing results of the Shire database search for samples on worksheet
	 * @param infoField JLabel field for feedback information 
	 */
	public void exportCRUKAnalysis(ArrayList<String> results, JLabel infoField) {
		try {
			properties();
			FileInputStream fileIn = new FileInputStream("L:\\SampleSheetTemplates\\TemplatesForAuto\\CRUK-analysis.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
			HSSFSheet worksheet = workbook.getSheet("Sheet1");

			HSSFRow row = worksheet.getRow(2);
			HSSFCell cell = row.createCell(1);
			cell.setCellValue((String) results.get(3) + "-NHS");

			row = worksheet.getRow(3);
			cell = row.createCell(1);
			cell.setCellValue((String) results.get(1));
			this.worksheetName = ((String) results.get(1));
			
			row = worksheet.getRow(4);
			cell = row.createCell(1);
			cell.setCellValue((String) results.get(5));

			rowNum = 28;
			for (int i = 0; i < results.size(); i += step) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue((String) results.get(0 + i));
				cell = row.createCell(1);
				cell.setCellValue((String) results.get(0 + i));
				cell = row.createCell(2);
				cell.setCellValue((String) results.get(1 + i));
				rowNum += 1;
			}

			save(workbook, "analysis", "CRUK");
			
		} catch (Exception e) {
			infoField.setVisible(true);
			infoField.setText("Could not find a valid template sheet");
		}
	}

	/**
	 * 
	 * @param results ArrayList<String> containing results of the Shire database search for samples on worksheet
	 * @param selectGenes ArrayList<String> containing results of the Shire database search for genes to test for
	 * @param infoField JLabel field for feedback information 
	 */
	public void exportTrusight(ArrayList<String> results,
			ArrayList<String> selectGenes, JLabel infoField) {
		try {
			properties();
			FileInputStream fileIn = new FileInputStream("L:\\SampleSheetTemplates\\TemplatesForAuto\\Trusight.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
			HSSFSheet worksheet = workbook.getSheet("Sheet1");

			HSSFRow row = worksheet.getRow(2);
			HSSFCell cell = row.createCell(1);
			cell.setCellValue((String) results.get(3) + "-NHS");

			row = worksheet.getRow(3);
			cell = row.createCell(1);
			cell.setCellValue((String) results.get(1));
			worksheetName = ((String) results.get(1));

			rowNum = 14;
			for (int i = 0; i < results.size(); i += step) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue((String) results.get(0 + i));
				cell = row.createCell(1);
				cell.setCellValue((String) results.get(1 + i));
				rowNum += 1;
			}

			rowNum = 14;
			for (int i = 0; i < selectGenes.size(); i++) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(7);
				cell.setCellValue((String) selectGenes.get(0 + i));
				rowNum += 1;
			}
			
			rowNum = 14;
			for (int i = 0; i < results.size(); i++) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(8);
				cell.setCellValue(trusightPipeline);
				rowNum += 1;
			}

			save(workbook, "", "Trusight");
			
		} catch (Exception e) {
			infoField.setVisible(true);
			infoField.setText("Could not find a valid template sheet");
		}
	}
	
	/**
	 * 
	 * @param results ArrayList<String> containing results of the Shire database search for samples on worksheet
	 * @param selectGenes ArrayList<String> containing results of the Shire database search for genes to test for
	 * @param infoField JLabel field for feedback information 
	 */
	public void exportTrusightOne(ArrayList<String> results,
			ArrayList<String> selectGenes, JLabel infoField) {
		try {
			properties();
			FileInputStream fileIn = new FileInputStream("L:\\SampleSheetTemplates\\TemplatesForAuto\\TrusightOne.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
			HSSFSheet worksheet = workbook.getSheet("Sheet1");

			HSSFRow row = worksheet.getRow(2);
			HSSFCell cell = row.createCell(1);
			cell.setCellValue((String) results.get(3) + "-NHS");

			row = worksheet.getRow(3);
			cell = row.createCell(1);
			cell.setCellValue((String) results.get(1));
			worksheetName = ((String) results.get(1));

			rowNum = 17;
			for (int i = 0; i < results.size(); i += step) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue((String) results.get(0 + i));
				cell = row.createCell(1);
				cell.setCellValue((String) results.get(1 + i));
				rowNum += 1;
			}

			rowNum = 17;
			for (int i = 0; i < selectGenes.size(); i++) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(7);
				cell.setCellValue((String) selectGenes.get(0 + i));
				rowNum += 1;
			}
			
			rowNum = 17;
			for (int i = 0; i < results.size(); i++) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(8);
				cell.setCellValue(trusightOnePipeline);
				rowNum += 1;
			}
			

			save(workbook, "", "Trusightone");
			
		} catch (Exception e) {
			infoField.setVisible(true);
			infoField.setText("Could not find a valid template sheet");
		}
	}

	/**
	 * 
	 * @param results ArrayList<String> containing results of the Shire database search for samples on worksheet
	 * @param selectGenes ArrayList<String> containing results of the Shire database search for genes to test for
	 * @param infoField JLabel field for feedback information 
	 */
	public void exportTAM(ArrayList<String> results,
			ArrayList<String> selectGenes, JLabel infoField) {
		try {
			properties();
			FileInputStream fileIn = new FileInputStream("L:\\SampleSheetTemplates\\TemplatesForAuto\\TAMGeneRead.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
			HSSFSheet worksheet = workbook.getSheet("Sheet1");

			HSSFRow row = worksheet.getRow(2);
			HSSFCell cell = row.createCell(1);
			cell.setCellValue((String) results.get(3) + "-NHS");

			row = worksheet.getRow(3);
			cell = row.createCell(1);
			cell.setCellValue((String) results.get(1));
			worksheetName = ((String) results.get(1));

			rowNum = 14;
			for (int i = 0; i < results.size(); i += step) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue((String) results.get(0 + i));
				cell = row.createCell(1);
				cell.setCellValue((String) results.get(1 + i));
				rowNum += 1;
			}
			
			rowNum = 14;
			for (int i = 0; i < results.size(); i++) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(6);
				cell.setCellValue(tamPipeline);
				rowNum += 1;
			}
			
			

			save(workbook, "", "TAM");
			
		} catch (Exception e) {
			infoField.setVisible(true);
			infoField.setText("Could not find a valid template sheet");
		}
	}

	/**
	 * 
	 * @param results ArrayList<String> containing results of the Shire database search for samples on worksheet
	 * @param selectGenes ArrayList<String> containing results of the Shire database search for genes to test for
	 * @param infoField JLabel field for feedback information 
	 */
	public void exportWCB(ArrayList<String> results, ArrayList<String> selectGenes, JLabel infoField) {
		try {
			properties();
			FileInputStream fileIn = new FileInputStream("L:\\SampleSheetTemplates\\TemplatesForAuto\\WCB.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
			HSSFSheet worksheet = workbook.getSheet("Sheet1");

			HSSFRow row = worksheet.getRow(2);
			HSSFCell cell = row.createCell(1);
			cell.setCellValue((String) results.get(3) + "-NHS");

			row = worksheet.getRow(3);
			cell = row.createCell(1);
			cell.setCellValue((String) results.get(1));
			worksheetName = ((String) results.get(1));

			rowNum = 14;
			for (int i = 0; i < results.size(); i += step) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue((String) results.get(0 + i));
				cell = row.createCell(1);
				cell.setCellValue((String) results.get(1 + i));
				rowNum += 1;
			}
			
			rowNum = 14;
			for (int i = 0; i < selectGenes.size(); i++) {
				row = worksheet.getRow(rowNum);
				cell = row.createCell(6);
				if (selectGenes.get(0 + i) == null){
					cell.setCellValue(focus4Pipeline);
				}else if(selectGenes.get(0 + i).equalsIgnoreCase("FOCUS4") || selectGenes.get(0 + i).equalsIgnoreCase("FOCUS 4")){
					cell.setCellValue(focus4Pipeline);
				}else if(selectGenes.get(0 + i).equalsIgnoreCase("WCB")){
					cell.setCellValue(wcbPipeline);
				}else if(selectGenes.get(0 + i).equalsIgnoreCase("BRCA")){
					cell.setCellValue(brcaPipeline);
				}
				rowNum += 1;
			}
			
			for (int j = 0; j < selectGenes.size(); j++) {
				
				// 13 is for other rows
				rowNum = selectGenes.size() + 13;
				row = worksheet.getRow(rowNum);
				cell = row.createCell(6);
				if(results.get(j).equalsIgnoreCase("NTC-WCB")){
					cell.setCellValue(wcbPipeline);
				}else if(results.get(j).equalsIgnoreCase("NTC-FOCUS4")){
					cell.setCellValue(focus4Pipeline);
				}else if(results.get(j).equalsIgnoreCase("NTC-BRCA")){
					cell.setCellValue(brcaPipeline);
				}
				
			}

			save(workbook, "", "WCB");
			
		} catch (Exception e) {
			infoField.setVisible(true);
			infoField.setText("Could not find a valid template sheet");
		}
	}
}