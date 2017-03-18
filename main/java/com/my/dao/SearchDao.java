package com.my.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.binding.HomeSearch;
import com.my.binding.SearchBinding;
import com.my.constants.Constants;
import com.my.model.Member;
import com.my.model.PersonalDetails;
import com.my.model.Result;

@Repository
public class SearchDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	MemberDao memberDao;
	
	private static final String BASE_QUERY = "select m.memberId,m.firstName,m.lastName,m.email,m.dob,"
			+ "pd.height,pd.weight,pd.maritalStatus from Member m join m.personalDetails pd where m.gender =:gender";

	public List<Member> findMatches(Member member, SearchBinding searchBinding) {
		
		 CriteriaBuilder cb = em.getCriteriaBuilder();		 
		 CriteriaQuery<Member> q = cb.createQuery(Member.class);
		 Root<Member> m = q.from(Member.class);
		 Join<Member, PersonalDetails> pds = m.join("personalDetails");
		 ParameterExpression<String> gender = cb.parameter(String.class);
		 ParameterExpression<Integer> age = cb.parameter(Integer.class);
		 
		 q.select(m).where(cb.notEqual(m.get("gender"), member.getGender()),
				 cb.between(m.get("age"), searchBinding.getFromAge(), searchBinding.getToAge()),cb.equal(pds.get("maritalStatus"), searchBinding.getMaritalStatus()),
				 cb.between(pds.get("height"), searchBinding.getFromHeight(), searchBinding.getToHeight())
				 );
		 
		 TypedQuery<Member> tq = em.createQuery(q);
		 
		 
		List<Member> results = em.createQuery(q).getResultList();
		return results;
		
	}
	
	/**
	 * TODO : OPTIMIZE
	 * @param homeSearch
	 * @return
	 */
	public List<Member> basicSearch(HomeSearch homeSearch){
		
		StringBuilder strbuilder = new StringBuilder(BASE_QUERY);
		
		Query q = em.createQuery(strbuilder.toString());
		q.setParameter(Constants.GENDER, homeSearch.getBrideGroom());
		
		 List<Object[]> resultsObj = q.getResultList();
		 List<Result> results = new ArrayList<>();
		 List<Member> members = new ArrayList<>();
		 
		
		 for(Object[] obj:resultsObj){
			 Result result = new Result();
			 result.setMemberId((Long)obj[0]);
			 result.setFirstName((String)obj[1]);
			 result.setLastName((String)obj[2]);
			 result.setEmail((String)obj[3]);
			results.add(result);	
			Member member = memberDao.findByMemberId((Long)obj[0]);
			memberDao.findByMemberId((Long)obj[0]);
			members.add(member);
		 }
		
		 return members;
	}

}
