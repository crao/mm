package com.my.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;

import com.my.binding.SearchBinding;
import com.my.constants.Constants;
import com.my.model.Member;
import com.my.model.Result;

@Repository
public class SearchDao {
	
	@PersistenceContext
	EntityManager em;
	
	private static final String BASE_QUERY = "select m.memberId,m.firstName,m.lastName,m.email,m.dob,"
			+ "pd.height,pd.weight,pd.maritalStatus from Member m join m.personalDetails pd where m.gender =:gender";

	public List<Result> findMatches(Member member, SearchBinding searchBinding) {
		
		StringBuilder strbuilder = new StringBuilder(BASE_QUERY);
		String genderParam = null;
		if(Constants.GENDER_MALE.equals(member.getGender())){
			genderParam = Constants.GENDER_FEMALE;
		}else if(Constants.GENDER_FEMALE.equals(member.getGender())){
			genderParam = Constants.GENDER_MALE;
		}
		
		//strbuilder.append(" and m.age >=:"+Constants.MIN_AGE+" and m.age <=:"+Constants.MAX_AGE);
		//strbuilder.append(" and m.age between :minAge and :maxAge");
		//strbuilder.append(" and pd.height >=:"+Constants.FROM_HEIGHT+" and pd.height <=:"+Constants.TO_HEIGHT);
		//strbuilder.append(" and pd.maritalStatus in :"+Constants.MARITAL_STATUS);
		
		String[] mArray = searchBinding.getMartialStatus().split(Constants.COMMA);
		List<String> maritalStatuses = Arrays.asList(mArray);

		//Integer fromHeight = Integer.parseInt(searchBinding.getFromHeight());
		//Integer toHeight = Integer.parseInt(searchBinding.getToHeight());
		Query q = em.createQuery(strbuilder.toString());
		q.setParameter(Constants.GENDER, genderParam);
		//q.setParameter(Constants.MIN_AGE, searchBinding.getFromAge());
		//q.setParameter(Constants.MAX_AGE, searchBinding.getToAge());
		//q.setParameter(Constants.FROM_HEIGHT,searchBinding.getFromHeight());
		//q.setParameter(Constants.TO_HEIGHT, searchBinding.getToHeight());
		//q.setParameter(Constants.MARITAL_STATUS, maritalStatuses);
		
		
		 List<Object[]> resultsObj = q.getResultList();
		 List<Result> results = new ArrayList<>();
		 
		
		 for(Object[] obj:resultsObj){
			 Result result = new Result();
			 result.setMemberId((Long)obj[0]);
			 result.setFirstName((String)obj[1]);
			 result.setLastName((String)obj[2]);
			 result.setEmail((String)obj[3]);
			results.add(result);	 
		 }
	
		
		return results;
		
	}

}
