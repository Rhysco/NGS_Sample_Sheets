package nhs.cardiff.genetics.ngssamplesheets;

public class User {
	
	private String user;
	
	/**
	 * 
	 * @param userName The username of the user currently running the program
	 */
	public User(String userName){
		user = userName(userName); 
	}

	/**
	 * 
	 * @param userName The username of the user currently running the program
	 * @return Returns userName, the actual name of the user based on their NADEX login code
	 */
	private static String userName(String userName){
		// Have to use Java 1.6 and case doesnt support string in 1.6, therefore else if
		if(userName.equalsIgnoreCase("rh086986")){
			userName = "Rhys Cooper";
		}else if(userName.equalsIgnoreCase("Ad093867")){
			userName = "Adam Poole";
		}else if(userName.equalsIgnoreCase("Ad090609")){
			userName = "Adrianne Amy Davies";
		}else if(userName.equalsIgnoreCase("Ai082790")){
			userName = "Aislinn Cooper";
		}else if(userName.equalsIgnoreCase("An085920")){
			userName = "Andrew Milne";
		}else if(userName.equalsIgnoreCase("an090758")){
			userName = "Andrew Roberts";
		}else if(userName.equalsIgnoreCase("an091966")){
			userName = "Andrew Senior";
		}else if(userName.equalsIgnoreCase("AN081300")){
			userName = "Angharad Williams";
		}else if(userName.equalsIgnoreCase("Ar085219")){
			userName = "Arfhan Rafiq";
		}else if(userName.equalsIgnoreCase("Br167075")){
			userName = "Brian Kitchen";
		}else if(userName.equalsIgnoreCase("Cl092500")){
			userName = "Clare Riley";
		}else if(userName.equalsIgnoreCase("Da094909")){
			userName = "David Bone";
		}else if(userName.equalsIgnoreCase("Da089800")){
			userName = "David Powell";
		}else if(userName.equalsIgnoreCase("Di083948")){
			userName = "Dilruba Meah";
		}else if(userName.equalsIgnoreCase("Ha094484")){
			userName = "Hannah Jones";
		}else if(userName.equalsIgnoreCase("Ha092681")){
			userName = "Hazel Ingram";
		}else if(userName.equalsIgnoreCase("Ho093117")){
			userName = "Hoi Ping Weeks";
		}else if(userName.equalsIgnoreCase("Ho086952")){
			userName = "Hood Mugalaasi";
		}else if(userName.equalsIgnoreCase("Hu093441")){
			userName = "Huw Mottram";
		}else if(userName.equalsIgnoreCase("Id089845")){
			userName = "Idowu Adebiyi";
		}else if(userName.equalsIgnoreCase("Ja083828")){
			userName = "Jaspreet Hothi";
		}else if(userName.equalsIgnoreCase("Je091739")){
			userName = "Jenny Waizeneker";
		}else if(userName.equalsIgnoreCase("Ju089315")){
			userName = "Justyna Tull";
		}else if(userName.equalsIgnoreCase("Ke093852")){
			userName = "Katie Evans";
		}else if(userName.equalsIgnoreCase("La086380")){
			userName = "Laura Ferguson";
		}else if(userName.equalsIgnoreCase("Ma092872")){
			userName = "Margarita CobrerosUgidos";
		}else if(userName.equalsIgnoreCase("Ma124406")){
			userName = "Maria Mendoza";
		}else if(userName.equalsIgnoreCase("Ma081714")){
			userName = "Matt Lyon";
		}else if(userName.equalsIgnoreCase("Me093338")){
			userName = "Megan Fealey";
		}else if(userName.equalsIgnoreCase("NA092767")){
			userName = "Natalia Ristic";
		}else if(userName.equalsIgnoreCase("Ne089375")){
			userName = "Nelson Cotrim";
		}else if(userName.equalsIgnoreCase("Ni092501")){
			userName = "Nia Haines";
		}else if(userName.equalsIgnoreCase("Ni086625")){
			userName = "Nicholas Lovis";
		}else if(userName.equalsIgnoreCase("Pa090746")){
			userName = "Pablo Reviriego";
		}else if(userName.equalsIgnoreCase("Pa094490")){
			userName = "Paula Moverley";
		}else if(userName.equalsIgnoreCase("Pe095220")){
			userName = "Peter Few";
		}else if(userName.equalsIgnoreCase("Re095323")){
			userName = "Rebecca Harris";
		}else if(userName.equalsIgnoreCase("Re093772")){
			userName = "Rebecca Weiser";
		}else if(userName.equalsIgnoreCase("Ri085869")){
			userName = "Richard Wheat";
		}else if(userName.equalsIgnoreCase("Ro094113")){
			userName = "Robert Charles Beer";
		}else if(userName.equalsIgnoreCase("Ro090332")){
			userName = "Rogan Vale";
		}else if(userName.equalsIgnoreCase("Sa084023")){
			userName = "Sara Rey";
		}else if(userName.equalsIgnoreCase("sc087700")){
			userName = "Scott Aldred";
		}else if(userName.equalsIgnoreCase("Vi092922")){
			userName = "Vicky Newsway";
		}
		return userName;
	}
	
	/**
	 * 
	 * @return
	 */

	public String getUser() {
		return user;
	}
	
	/**
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

}
