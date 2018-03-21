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

// ADDED GENEREAD POOLED TO DEAL WITH WORKSHEET HOOD MADE - MIGHT BE THIS WAY FROM NOW ON FOR TAM/WCB SAMPLES.
// MAKE A BIT BETTER AND GET ALL THE PIPELINE DATA COMING OUT OK.
// TEST ON WORKSHEET 17-6714 (Generead pooled)
// HOODS W/S


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ImportWorksheet {
	private ArrayList<String> worksheet = new ArrayList<String>();
	private ArrayList<String> selectGenes = new ArrayList<String>();
	private String selectGenesTemp;
	private String[] results;
	private String[] results2;
	private String db;
	private String url;
	private String user;
	private boolean go;
	private boolean goNGS;

	public ImportWorksheet() {
		results = new String[6];
		results2 = new String[2];
		go = false;
		goNGS = false;
		db = "M:\\Pyrosequencing\\Pyrosequencing Service\\PCR & PYRO spreadsheets\\Log\\IT\\SHIRE COPY FOR PYRO.MDB";
		url = ("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + db);
	}
	
	/**
	 * 
	 * @param worksheetInput The input from the user
	 * @param infoField JLabel field for feedback information
	 * @param inputField JTextField for user input
	 * @throws Exception Thrown is connection to Shire fails
	 */
	public void imports(String worksheetInput, JLabel infoField, JTextField inputField) throws Exception {
		
		ExportSampleSheet export = new ExportSampleSheet();
		user = System.getProperty("user.name");
		go = checkInputShire(worksheetInput);

		if (go) {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conn = DriverManager.getConnection(url);

			PreparedStatement st = conn.prepareStatement("SELECT DISTINCTROW DNA_Worksheet.*,"
					+ " DNA_WORKSHEET_DET.POSITION,"
					+ " DNA_WORKSHEET_DET.LABNO,"
					+ " DNA_Worksheet.WORKSHEET,"
					+ " DNA_Worksheet.UPDATEDBY,"
					+ " DNA_WORKSHEET_DET.WORKSHEET FROM"
					+ " (DNA_Worksheet LEFT JOIN DNA_TEST ON"
					+ " DNA_Worksheet.TEST = DNA_TEST.TEST)"
					+ " INNER JOIN DNA_WORKSHEET_DET ON"
					+ " DNA_Worksheet.WORKSHEET ="
					+ " DNA_WORKSHEET_DET.WORKSHEET"
					+ " WHERE (((DNA_Worksheet.WORKSHEET)"
					+ "=[DNA_WORKSHEET_DET].[WORKSHEET])"
					+ " AND ((DNA_WORKSHEET_DET.WORKSHEET)"
					+ "=?)) ORDER BY DNA_WORKSHEET_DET.POSITION ASC;");

			st.setString(1, worksheetInput);

			PreparedStatement st2 = conn.prepareStatement("SELECT DISTINCTROW DNALAB_TEST.*,"
					+ " DNALAB_TEST.TEST,"
					+ " DNALAB_TEST.COMMENTS FROM"
					+ " DNALAB_TEST WHERE DNALAB_TEST.LABNO =?");

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				results[0] = rs.getString("LABNO");
				results[1] = rs.getString("WORKSHEET");
				results[2] = rs.getString("POSITION");
				// Removed worksheet by and replaced by username from Nadex, see checkName method
				//results[3] = rs.getString("WORKSHEET_BY");
				results[3] = checkName(user);
				results[4] = rs.getString("TEST");
				// Take just the date and replace some characters.
				results[5] = rs.getString("UPDATEDDATE").substring(2, 10).replace("-", "/");

				goNGS = checkInputNGS(results[4]);

				if (goNGS) {
					if (results[0] != null) {

						st2.setString(1, results[0].toString());
						ResultSet rs2 = st2.executeQuery();
						while (rs2.next()) {
							results2[0] = rs2.getString("TEST");
							results2[1] = rs2.getString("COMMENTS");
							if ((results2[0].equalsIgnoreCase("Trusight Cancer"))
									|| (results2[0].equalsIgnoreCase("TruSight One CES panel"))
									|| (results2[0].equalsIgnoreCase("TAM panel"))
									|| (results2[0].equalsIgnoreCase("CRM panel"))
									|| (results2[0].equalsIgnoreCase("GeneRead pooled"))){
								selectGenesTemp = results2[1];
							}
						}

						for (int i = 0; i < results.length; i++) {
							worksheet.add(results[i]);
						}

						selectGenes.add(selectGenesTemp);
						selectGenesTemp = "";
					}
				} else if (!goNGS) {
					infoField.setVisible(true);
					infoField.setText("Not a Valid NGS worksheet, please try again");
					inputField.setText("");
				}
			}
		} else if (!go) {
			infoField.setVisible(true);
			infoField.setText("Not a valid shire worksheet, please try again");
			inputField.setText("");
		}

		if ((worksheet.get(4)).equalsIgnoreCase("NEXTERA NGS")) {
			export.exportCRUK(worksheet, infoField);
			export.exportCRUKAnalysis(worksheet, infoField);
		} else if ((worksheet.get(4)).equalsIgnoreCase("TruSight Cancer")){
			export.exportTrusight(worksheet, selectGenes, infoField);
		} else if ((worksheet.get(4)).equalsIgnoreCase("TruSight One CES panel")){
			export.exportTrusightOne(worksheet, selectGenes, infoField);
		} else if ((worksheet.get(4)).equalsIgnoreCase("TAM panel")) {
			export.exportTAM(worksheet, selectGenes, infoField);
		} else if ((worksheet.get(4)).equalsIgnoreCase("CRM panel") || worksheet.get(4).equalsIgnoreCase("GeneRead pooled")) {
			export.exportWCB(worksheet, selectGenes, infoField);
		} 
	}

	/**
	 * 
	 * @param worksheetInput The input from the user
	 * @return true if input valid Shire worksheet
	 */
	public boolean checkInputShire(String worksheetInput) {
		String filter = "(^\\d{1,2}[-]\\d{1,5})";
		Pattern pattern = Pattern.compile(filter, 2);
		Matcher matcher = pattern.matcher(worksheetInput);

		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param test String denoting the name of the test
	 * @return true if test a valid NGS test
	 */
	public boolean checkInputNGS(String test) {
		if ((test.equals("NEXTERA NGS"))
				|| (test.equals("TruSight Cancer"))
				|| (test.equals("TruSight One CES panel"))
				|| (test.equals("TAM panel"))
				|| (test.equals("CRM panel"))
				|| (test.equals("GeneRead pooled"))) {
			goNGS = true;
		} else {
			goNGS = false;
		}
		return goNGS;
	}
	
	/**
	 * 
	 * @param user String denoting Nadex computer logon name of the user
	 * @return String user based on the logon name - changed to initials
	 */
	public String checkName(String user){
		
			// Rhys
		if (user.equalsIgnoreCase("rh086986")){
			user = "RC";
			// JP
		} else if(user.equalsIgnoreCase("ja083828")){
			user = "JH";
			// Adrianne
		} else if(user.equalsIgnoreCase("ad090609")){
			user = "AD";
			// Dil
		} else if(user.equalsIgnoreCase("di083948")){
			user = "DM";
			// Becky Harris
		} else if(user.equalsIgnoreCase("re095323")){
			user = "RH";
			// Andrew
		} else if(user.equalsIgnoreCase("an090758")){
			user = "AR";
			// Jenny
		} else if(user.equalsIgnoreCase("Je091739")){
			user = "JHW";
			// Rogan
		} else if(user.equalsIgnoreCase("Ro090332")){
			user = "RV";
			// Megan
		} else if(user.equalsIgnoreCase("Me093338")){
			user = "MF";
			// Becky Weiser
		} else if(user.equalsIgnoreCase("Re093772")){
			user = "BW";
			// Everyone else
		} else{
			user = "Unknown";
		}
		return user;
	}
}
