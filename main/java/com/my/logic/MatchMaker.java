package com.my.logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



import com.my.model.Member;
import com.my.model.PersonalDetails;
import com.my.model.Preferences;
import com.my.model.MatchConstants;

public class MatchMaker {
	
	public Map<String,Boolean> makeMatch(Member m1, Member m2){
		
		PersonalDetails person1 = m1.getPersonalDetails();
		PersonalDetails person2 = m2.getPersonalDetails();
		Preferences pre1 = m1.getPreferences();
		Preferences pre2 = m2.getPreferences();
		
		Map<String,Boolean> matchMap = new HashMap<String,Boolean>();
		
		matchMap.put(MatchConstants.AGE, isAgeMatch(m1.getAge(),m2.getAge(),pre1,pre2));
		matchMap.put(MatchConstants.HEIGHT,isHeightMatch(person1,person2,pre1,pre2));
		matchMap.put(MatchConstants.MARITALSTATUS,isMaritalStatusMatch(person1.getMaritalStatus(),person2.getMaritalStatus(),pre1.getMaritalStatus(),pre2.getMaritalStatus()));
		matchMap.put(MatchConstants.EATING,isEatingHabitsMatch(person1.getEatingHabits(), person2.getEatingHabits(), pre1.getEatingHabits(), pre2.getEatingHabits()));
		matchMap.put(MatchConstants.DRINKING,isDrinkingHabitsMatch(person1.getDrinkingHabits(), person2.getDrinkingHabits(), pre1.getDrinkingHabits(), pre2.getDrinkingHabits()));
		matchMap.put(MatchConstants.SMOKING, isSmokingHabitsMatch(person1.getSmokingHabits(), person2.getSmokingHabits(), pre1.getSmokingHabits(), pre2.getSmokingHabits()));
		
	
		matchMap.put(MatchConstants.EDUCATION, isEducationMatch(person1.getEducation(), person2.getEducation(), pre1.getEducation(), pre2.getEducation()));
		matchMap.put(MatchConstants.OCCUPATION, isOccupationMatch(person1.getOccupation(), person2.getOccupation(), pre1.getOccupation(), pre2.getOccupation()));  
		matchMap.put(MatchConstants.COUNTRY, isCountryMatch(person1.getCountry(), person2.getCountry(), pre1.getCountry(), pre2.getCountry()));
		matchMap.put(MatchConstants.STATE, isStateMatch(person1.getResidingstate(), person2.getResidingstate(), pre1.getResidingCity(), pre2.getResidingCity()));
		matchMap.put(MatchConstants.CITY, isCityMatch(person1.getCitizenship(), person2.getCitizenship(), pre1.getCitizenship(), pre2.getCitizenship()));
		
		matchMap.put(MatchConstants.GOTHRA, isGothraMatch(person1.getGothra(), person2.getGothra(), pre1.getGothra(), pre2.getGothra()));
		matchMap.put(MatchConstants.STAR, isStarMatch(person1.getStarSign(), person2.getStarSign(), pre1.getStar(), pre2.getStar()));
		matchMap.put(MatchConstants.MANGLIK, isManglikMatch(person1.getManglik(), person2.getManglik(), pre1.getManglik(), pre2.getManglik()));
		return matchMap;
		
	}	 

		
	private boolean isMaritalStatusMatch(String maritalStatus1, String maritalStatus2,String preMartialStatus1,String preMartialStatus2) {
		boolean match = false;
		
		Set<String> mSet1 = new HashSet<String>(Arrays.asList(preMartialStatus1.split(",")));
		Set<String> mSet2 = new HashSet<String>(Arrays.asList(preMartialStatus2.split(",")));
		
		if(mSet1.contains("Any") && mSet2.contains("Any")){
			match = true;
		}else if((mSet1.contains(maritalStatus2) && mSet2.contains(maritalStatus1))){
			match = true;
		}else if((mSet1.contains("Any") && mSet2.contains(maritalStatus1)) ||
				(mSet2.contains("Any") && mSet1.contains(maritalStatus2))){
			match = true;
		}
		
		return match;
		
	}
	
	private boolean isEatingHabitsMatch(String eat1, String eat2,String preEat1,String preEat2) {
		boolean match = false;
		
		Set<String> mSet1 = new HashSet<String>(Arrays.asList(preEat1.split(",")));
		Set<String> mSet2 = new HashSet<String>(Arrays.asList(preEat2.split(",")));
		
		if(mSet1.contains("Any") && mSet2.contains("Any")){
			match = true;
		}else if((mSet1.contains(eat2) && mSet2.contains(eat1))){
			match = true;
		}else if((mSet1.contains("Any") && mSet2.contains(eat1)) ||
				(mSet2.contains("Any") && mSet1.contains(eat2))){
			match = true;
		}
		
		return match;
		
	}
	
	private boolean isDrinkingHabitsMatch(String drink1, String drink2,String preDrink1,String preDrink2) {
		boolean match = false;
		
		Set<String> mSet1 = new HashSet<String>(Arrays.asList(preDrink1.split(",")));
		Set<String> mSet2 = new HashSet<String>(Arrays.asList(preDrink2.split(",")));
		
		if(mSet1.contains("Any") && mSet2.contains("Any")){
			match = true;
		}else if((mSet1.contains(drink2) && mSet2.contains(drink1))){
			match = true;
		}else if((mSet1.contains("Any") && mSet2.contains(drink1)) ||
				(mSet2.contains("Any") && mSet1.contains(drink2))){
			match = true;
		}
		
		return match;
		
	}
	
	private boolean isSmokingHabitsMatch(String smoke1, String smoke2,String preSmoke1,String preSmoke2) {
		boolean match = false;
		
		Set<String> mSet1 = new HashSet<String>(Arrays.asList(preSmoke1.split(",")));
		Set<String> mSet2 = new HashSet<String>(Arrays.asList(preSmoke2.split(",")));
		
		if(mSet1.contains("Any") && mSet2.contains("Any")){
			match = true;
		}else if((mSet1.contains(smoke2) && mSet2.contains(smoke1))){
			match = true;
		}else if((mSet1.contains("Any") && mSet2.contains(smoke1)) ||
				(mSet2.contains("Any") && mSet1.contains(smoke2))){
			match = true;
		}
		
		return match;
		
	}

	private boolean isAgeMatch(int age1, int age2, Preferences pre1, Preferences pre2) {
		boolean match = false;
		if((age1>=pre2.getFromHeight() && age1<=pre2.getToHeight()) &&
				(age2>=pre1.getFromHeight() && age2<=pre1.getToHeight())){
			match = true;
		}
		return match;
	}
	

	private boolean isHeightMatch(PersonalDetails person1, PersonalDetails person2, Preferences pre1, Preferences pre2) {
		boolean match = false;
		int h1 = person1.getHeight();
		int h2 = person2.getHeight();
		if((h1>=pre2.getFromHeight() && h1<=pre2.getToHeight()) &&
				(h2>=pre1.getFromHeight() && h2<=pre1.getToHeight())){
			match = true;
		}
		return match;
	}

	 
	   //Starting of Method for Education Matches
		private boolean isEducationMatch(String perEducation1, String perEducation2,String preEducation1,String preEducation2) {
			boolean match = false;
			
			Set<String> mSet1 = new HashSet<String>(Arrays.asList(preEducation1.split(",")));
			Set<String> mSet2 = new HashSet<String>(Arrays.asList(preEducation2.split(",")));
			
			if(mSet1.contains("Any") && mSet2.contains("Any")){
				match = true;
			}else if((mSet1.contains(perEducation2) && mSet2.contains(perEducation1))){
				match = true;
			}else if((mSet1.contains("Any") && mSet2.contains(perEducation1)) ||
					(mSet2.contains("Any") && mSet1.contains(perEducation2))){
				match = true;
			}
			else if((mSet1.equals(preEducation2)) ||(mSet2.equals(preEducation1))){
				match = true;
			}
			return match;
			
		}	
		//Ending of Method for Education Matches
	
		   //Starting of Method for OCCUPATION Matches
		private boolean isOccupationMatch(String perOccupation1, String perOccupation2,String preOccupation1,String preOccupation2) {
			boolean match = false;
			
			Set<String> mSet1 = new HashSet<String>(Arrays.asList(preOccupation1.split(",")));
			Set<String> mSet2 = new HashSet<String>(Arrays.asList(preOccupation2.split(",")));
			
			if(mSet1.contains("Any") && mSet2.contains("Any")){
				match = true;
			}else if((mSet1.contains(perOccupation2) && mSet2.contains(perOccupation1))){
				match = true;
			}else if((mSet1.contains("Any") && mSet2.contains(perOccupation1)) ||
					(mSet2.contains("Any") && mSet1.contains(perOccupation2))){
				match = true;
			}
			else if((mSet1.equals(perOccupation2)) ||(mSet2.equals(perOccupation1))){
				match = true;
			}
			return match;
			
		}	
		//Ending of Method for OCCUPATION Matches
		
		   //Starting of Method for COUNTRY Matches
		private boolean isCountryMatch(String perCountry1, String perCountry2,String preCountry1,String preCountry2) {
			boolean match = false;
			
			Set<String> mSet1 = new HashSet<String>(Arrays.asList(preCountry1.split(",")));
			Set<String> mSet2 = new HashSet<String>(Arrays.asList(preCountry2.split(",")));
			
			if(mSet1.contains("Any") && mSet2.contains("Any")){
				match = true;
			}else if((mSet1.contains(perCountry2) && mSet2.contains(perCountry1))){
				match = true;
			}else if((mSet1.contains("Any") && mSet2.contains(perCountry1)) ||
					(mSet2.contains("Any") && mSet1.contains(perCountry2))){
				match = true;
			}
			else if((mSet1.equals(perCountry2)) ||(mSet2.equals(perCountry1))){
				match = true;
			}
			return match;
			
		}	
		//Ending of Method for COUNTRY Matches
		
		 //Starting of Method for STATE  Matches
		private boolean isStateMatch(String perState1, String perState2,String preState1,String preState2) {
			boolean match = false;
			
			Set<String> mSet1 = new HashSet<String>(Arrays.asList(preState1.split(",")));
			Set<String> mSet2 = new HashSet<String>(Arrays.asList(preState2.split(",")));
			
			if(mSet1.contains("Any") && mSet2.contains("Any")){
				match = true;
			}else if((mSet1.contains(perState2) && mSet2.contains(perState1))){
				match = true;
			}else if((mSet1.contains("Any") && mSet2.contains(perState1)) ||
					(mSet2.contains("Any") && mSet1.contains(perState2))){
				match = true;
			}
			else if((mSet1.equals(perState2)) ||(mSet2.equals(perState1))){
				match = true;
			}
			return match;
			
		}	
		//Ending of Method for STATE  Matches
		
		//Starting of Method for CITY  Matches
		private boolean isCityMatch(String perCity1, String perCity2,String preCity1,String preCity2) {
			boolean match = false;
			
			Set<String> mSet1 = new HashSet<String>(Arrays.asList(preCity1.split(",")));
			Set<String> mSet2 = new HashSet<String>(Arrays.asList(preCity2.split(",")));
			
			if(mSet1.contains("Any") && mSet2.contains("Any")){
				match = true;
			}else if((mSet1.contains(perCity2) && mSet2.contains(perCity1))){
				match = true;
			}else if((mSet1.contains("Any") && mSet2.contains(perCity1)) ||
					(mSet2.contains("Any") && mSet1.contains(perCity2))){
				match = true;
			}
			else if((mSet1.equals(perCity2)) ||(mSet2.equals(perCity1))){
				match = true;
			}
			return match;
			
		}	
		//Ending of Method for CITY  Matches
		
		
		  //Starting of Method for GOTHRA  Matches
		private boolean isGothraMatch(String perGothra1, String perGothra2,String preGothra1,String preGothra2) {
			boolean match = false;
			
			Set<String> mSet1 = new HashSet<String>(Arrays.asList(preGothra1.split(",")));
			Set<String> mSet2 = new HashSet<String>(Arrays.asList(preGothra2.split(",")));
			
			if(mSet1.contains("Any") && mSet2.contains("Any")){
				match = true;
			}else if((mSet1.contains(perGothra2) && mSet2.contains(perGothra1))){
				match = true;
			}
			else if((mSet1.equals(perGothra2)) ||(mSet2.equals(perGothra1))){
				match = true;
			}
			return match;
			
		}	
		//Ending of Method for GOTHRA  Matches
		
		//Starting of Method for STAR  Matches
		private boolean isStarMatch(String perStarSign1, String perStarSign2,String preStarSign1,String preStarSign2) {
			boolean match = false;
			
			Set<String> mSet1 = new HashSet<String>(Arrays.asList(preStarSign1.split(",")));
			Set<String> mSet2 = new HashSet<String>(Arrays.asList(preStarSign2.split(",")));
			
			if(mSet1.contains("Any") && mSet2.contains("Any")){
				match = true;
			}else if((mSet1.contains(perStarSign2) && mSet2.contains(perStarSign1))){
				match = true;
			}
			else if((mSet1.equals(perStarSign2)) ||(mSet2.equals(perStarSign1))){
				match = true;
			}
			return match;
			
		}	
		//Ending of Method for STAR  Matches
		
		 //Starting of Method for MANGLIK  Matches
		private boolean isManglikMatch(String perManglik1, String perManglik2,String preManglik1,String preManglik2) {
			boolean match = false;
			
			Set<String> mSet1 = new HashSet<String>(Arrays.asList(preManglik1.split(",")));
			Set<String> mSet2 = new HashSet<String>(Arrays.asList(preManglik2.split(",")));
			
			if(mSet1.contains("Don't know") && mSet2.contains("Don't know")){
				match = true;
			}else if((mSet1.contains(perManglik2) && mSet2.contains(perManglik1))){
				match = true;
			}
			else if((mSet1.equals(perManglik2)) ||(mSet2.equals(perManglik1))){
				match = true;
			}
			return match;
			
		}	
		//Ending of Method for MANGLIK  Matches
		
		
		
	public static void main(String args[])
	{
		
		MatchMaker matchMaker = new MatchMaker();
		
		//for testing purpose called isEducationMatch Method		
	    Boolean Edu  = matchMaker. isEducationMatch("B.Sc", "M.Com", "Any", "Any");//Ans true
	    Boolean Edu1  = matchMaker.isEducationMatch("B.Sc", "M.Com", "B.Sc,B.com,M.Com", "M.Com,B.Sc");//Ans true
	    Boolean Edu2  = matchMaker.isEducationMatch("B.Sc", "M.Com", "Any,B.Sc,B.com,M.Com", "Any,M.Com,B.Sc");//Ans true
	    Boolean Edu3  = matchMaker.isEducationMatch("B.Sc", "B.Sc", "B.Sc", "B.Sc");  //Ans true
		Boolean Edu4  = matchMaker.isEducationMatch("B.Sc", "B.Sc", "M.Sc", "M.Sc");//Ans false		 
	    System.out.println(Edu);
	    System.out.println(Edu1);
	    System.out.println(Edu2);
	    System.out.println(Edu3);
	    System.out.println(Edu4);
	    
		//for testing purpose called isOccupationMatch  Method		
	    Boolean Occ  = matchMaker.isOccupationMatch("Engineer", "Pilot", "Any", "Any");//Ans true
	    Boolean Occ1  = matchMaker.isOccupationMatch("Engineer", "Pilot", "Engineer,Pilot", "Engineer,Pilot");//Ans true
	    Boolean Occ2  = matchMaker.isOccupationMatch("Engineer", "Pilot","Any,Architect,Charted Accountant", "Any,Company Secretary");//Ans true
	    Boolean Occ3  = matchMaker.isOccupationMatch("Engineer", "Engineer", "Engineer", "Engineer");  //Ans true
		Boolean Occ4  = matchMaker.isOccupationMatch("Engineer", "Engineer", "Polit", "Polit");//Ans false
	    System.out.println(Occ);
	    System.out.println(Occ1);
	    System.out.println(Occ2);
	    System.out.println(Occ3);
	    System.out.println(Occ4);
	    
	     //for testing purpose called isCountryMatch  Method		
	     Boolean coun  = matchMaker.isCountryMatch("Indian", "Nepal", "Any", "Any");//Ans true
	     Boolean coun1  = matchMaker.isCountryMatch("Indian", "Nepal", "Indian,Nepal,Bhutan","Indian,Nepal");//Ans true
	     Boolean coun2  = matchMaker.isCountryMatch("Indian", "Nepal","Any,Indian,Nepal,Bhutan", "Any,Indian,Nepal");//Ans true
	     Boolean coun3  = matchMaker.isCountryMatch("Indian", "Indian", "Indian", "Indian");  //Ans true
		 Boolean coun4  = matchMaker.isCountryMatch("Indian", "Indian", "Nepal", "Nepal");//Ans false		 
	     System.out.println(coun);
	     System.out.println(coun1);
	     System.out.println(coun2);
	     System.out.println(coun3);
	     System.out.println(coun4);
	     
	   //for testing purpose called isStateMatch  Method		
	     Boolean Sta  = matchMaker.isStateMatch("Maharashtra", "Panjab", "Any", "Any");//Ans true
	     Boolean Sta1  = matchMaker.isStateMatch("Maharashtra", "Panjab", "Maharashtra,Assam,Bihar","Telangana,Panjab,Nepal");//Ans false
	     Boolean Sta2  = matchMaker.isStateMatch("Maharashtra", "Panjab","Any,Maharashtra,Assam,Bihar", "Any,Telangana,Panjab,Nepal");//Ans true
	     Boolean Sta3  = matchMaker.isStateMatch("Maharashtra", "Maharashtra", "Maharashtra", "Maharashtra");  //Ans true
		 Boolean Sta4  = matchMaker.isStateMatch("Maharashtra", "Maharashtra", "Panjab", "Panjab");//Ans false		 
	     System.out.println(Sta);
	     System.out.println(Sta1);
	     System.out.println(Sta2);
	     System.out.println(Sta3);
	     System.out.println(Sta4);
		
	   //for testing purpose called isCityMatch  Method		
	     Boolean city  = matchMaker.isCityMatch("Aurangabad", "Pune", "Any", "Any");//Ans true
	     Boolean city1  = matchMaker.isCityMatch("Aurangabad", "Pune", "Aurangabad,Pune,Jalna","Pune,Aurangabad,Mumbai");//Ans true
	     Boolean city2  = matchMaker.isCityMatch("Aurangabad", "Pune","Any,Aurangabad,Pune,Jalna", "Any,Pune,Aurangabad,Mumbai");//Ans true
	     Boolean city3  = matchMaker.isCityMatch("Aurangabad", "Aurangabad", "Aurangabad", "Aurangabad");  //Ans true
		 Boolean city4  = matchMaker.isCityMatch("Aurangabad", "Pune", "Aurangabad", "Pune");//Ans false		 
	     System.out.println(city);
	     System.out.println(city1);
	     System.out.println(city2);
	     System.out.println(city3);
	     System.out.println(city4);
	     
	 	//for testing purpose called isGothraMatch  Method		
	     Boolean gothra  = matchMaker.isGothraMatch("Aamat", "Aashta", "Any", "Any");//Ans true
	     Boolean gothra1  = matchMaker.isGothraMatch("Adishya", "Aashta", "Adishya,Aashta,Agastya","Aashta,Adishya,Ainakula");//Ans true	 
	     Boolean gothra2  = matchMaker.isGothraMatch("Adishya", "Adishya", "Adishya", "Adishya");  //Ans true
		 Boolean gothra3  = matchMaker.isGothraMatch("Adishya", "Aashta", "Adishya", "Aashta");//Ans false		 
	     System.out.println(gothra);
	     System.out.println(gothra1);
	     System.out.println(gothra2);
	     System.out.println(gothra3);
	     
	     
	   //for testing purpose called isStarMatch  Method		
	     Boolean Star  = matchMaker.isStarMatch("Aswini", "Bharani", "Any", "Any");//Ans true
	     Boolean Star1  = matchMaker.isStarMatch("Aswini", "Bharani", "Aswini,Bharani,Agastya","Bharani,Aswini,Ainakula");//Ans true	 
	     Boolean Star2  = matchMaker.isStarMatch("Adishya", "Adishya", "Adishya", "Adishya");  //Ans true
		 Boolean Star3  = matchMaker.isStarMatch("Adishya", "Aashta", "Adishya", "Aashta");//Ans false		 
	     System.out.println(Star);
	     System.out.println(Star1);
	     System.out.println(Star2);
	     System.out.println(Star3);
	     
	   //for testing purpose called isManglikMatch  Method		
	     Boolean manglik  = matchMaker.isManglikMatch("Yes", "Don't know", "Don't know", "Don't know");//Ans true
	     Boolean manglik1  = matchMaker.isManglikMatch("Yes", "Don't know", "Don't know,Yes,No","Don't know,Yes,No");//Ans true	 
	     Boolean manglik2  = matchMaker.isManglikMatch("No", "No", "No", "No");  //Ans true
		 Boolean manglik3  = matchMaker.isManglikMatch("Adishya", "Aashta", "Adishya", "Aashta");//Ans false		 
	     System.out.println(manglik);
	     System.out.println(manglik1);
	     System.out.println(manglik2);
	     System.out.println(manglik3);
		
	}
}
