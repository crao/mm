package com.my.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.my.model.Member;
import com.my.model.PersonalDetails;
import com.my.model.Preferences;

@Repository
public class MatchDao {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Object[]> matches(Member member,Preferences preferences){
		
		Query q = em.createQuery("select m.memberId,m.firstName,m.lastName,m.email,m.dob,"
				+ "pd.height,pd.weight,pd.maritalStatus from Member m join m.personalDetails pd where "
				 +  "m.age between :minAge and :maxAge "
				 + "and m.gender !=:gender "			
				+ "and pd.height between :fromHeight and :toHeight "
				+ "and pd.maritalStatus =:maritalStatus "
				+ "and pd.physicalStatus =:physicalStatus "
				+ "and pd.eatingHabits =:eatinghabits "
				+ "and pd.drinkingHabits =:drinkinghabits "
				+ "and pd.smokingHabits =:smokinghabits "
				+ "and pd.education in :education "
				+ "and pd.occupation in :occupation "
				+ "and pd.income > :income "
				+ "and pd.residingstate =:residingstate "
				+ "and pd.residingcity =:residingcity "
				+ "and pd.country =:country "
				+ "and pd.citizenship =:citizenship"				
				);
		
		q.setParameter("minAge", preferences.getFromAge());
		q.setParameter("maxAge", preferences.getToAge());
		q.setParameter("gender", member.getGender());
		q.setParameter("fromHeight", preferences.getFromHeight());
		q.setParameter("toHeight", preferences.getToHeight());
		q.setParameter("maritalStatus", preferences.getMaritalStatus());
		q.setParameter("physicalStatus", preferences.getPhysicalStatus());
		q.setParameter("eatinghabits", preferences.getEatingHabits());
		q.setParameter("drinkinghabits", preferences.getDrinkingHabits());
		q.setParameter("smokinghabits", preferences.getSmokingHabits());
		q.setParameter("education", preferences.getEducation());
		q.setParameter("occupation", preferences.getOccupation());
		q.setParameter("income", Integer.toString(preferences.getIncome()));
		q.setParameter("residingstate", preferences.getResidingCity());
		q.setParameter("residingcity", preferences.getResidingCity());
		q.setParameter("country", preferences.getCountry());
		q.setParameter("citizenship", preferences.getCitizenship());
		
		
		List<Object[]> results = q.getResultList();
		


		
		return results;
	}
	

	
	/*
	 * 
	 * 
	 * CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PersonalDetails> query = cb.createQuery(PersonalDetails.class);
		Root<PersonalDetails> personalDetailsRoot = query.from(PersonalDetails.class);
		
		query.select(personalDetailsRoot).where(cb.and(cb.equal(personalDetailsRoot.get("maritalStatus"), preferences.getMaritalStatus()), 
				cb.equal(personalDetailsRoot.get("familyStatus"), preferences.getFamilystatus())));
		
		TypedQuery<PersonalDetails> tQuery = em.createQuery(query);
		List<PersonalDetails> personalDetailsMatch = tQuery.getResultList();
	 */
	
	
}
