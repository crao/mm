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
		
		matchMap.put(MatchConstants.EDUCATION, isEducationMatch(person1.getEducation(), pre1.getEducation()));
		matchMap.put(MatchConstants.OCCUPATION, isOccupationMatch(person1.getOccupation(), pre1.getOccupation()));
		matchMap.put(MatchConstants.INCOME, isIncomeMatch(person1.getIncome(), pre1.getIncome()));
		matchMap.put(MatchConstants.COUNTRY, isCountryMatch(person1.getCountry(),pre1.getCountry()));
		matchMap.put(MatchConstants.STATE, isStateMatch(person1.getResidingstate(),pre1.getState()));
		matchMap.put(MatchConstants.CITY, isCityMatch(person1.getResidingcityP(), pre1.getCitizenship()));
		
		matchMap.put(MatchConstants.GOTHRA, isGothraMatch(person1.getGothra(), pre1.getGothra()));
		matchMap.put(MatchConstants.STAR, isStarMatch(person1.getStarSign(), pre1.getStar()));
		matchMap.put(MatchConstants.MANGLIK, isManglikMatch(person1.getManglik(), pre1.getManglik()));
		return matchMap;
		
	}	 
	 
	 
	private  Boolean isEducationMatch(String person1,  String pre1) {
		// TODO Auto-generated method stub
		boolean match = false;		
		if(person1.equals(pre1))
		{
			match = true;
		}		
		return match;
	}
	
	 private Boolean isOccupationMatch(String person1, String pre1) {
			
			boolean match = false;		
			if(person1.equals(pre1))
			{
				match = true;
			}		
			return match;
		    }
	 
	 
	 private Boolean isIncomeMatch(int person1, int pre1) {
			// TODO Auto-generated method stub
			boolean match = false;		
			if(person1==pre1)
			{
				match = true;
			}		
			return match;
		}
	 
	 private Boolean isCountryMatch(String person1, String pre1) {
			
			boolean match = false;		
			if(person1.equals(pre1))
			{
				match = true;
			}		
			return match;
		}

	 private Boolean isStateMatch(String person1, String pre1)
		{
	
			boolean match = false;		
			
			if(person1.equals(pre1))
			{
				match = true;
			}		
			return match;
       }

	 private Boolean isCityMatch(String person1, String pre1) {
			
			boolean match = false;		
			if(person1.equals(pre1))
			{
				match = true;
			}		
			return match;
			
			
		}	 
	 
		private Boolean isGothraMatch(String person1, String pre1) {
			// TODO Auto-generated method stub
				boolean match = false;		
				if(person1.equals(pre1))
				{
					match = true;
				}		
				return match;
		    }

	 
		private Boolean isStarMatch(String person1, String pre1) {
			// TODO Auto-generated method stub
				boolean match = false;		
				if(person1.equals(pre1))
				{
					match = true;
				}		
				return match;
		    }

	 
		private Boolean isManglikMatch(String person1, String pre1) {
			// TODO Auto-generated method stub
				boolean match = false;		
				if(person1.equals(pre1))
				{
					match = true;
				}		
				return match;
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

}
