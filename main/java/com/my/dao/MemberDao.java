package com.my.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.my.model.Member;

@Transactional
public interface MemberDao  extends CrudRepository<Member, Long> {
	
	  /**
	   * Return the user having the passed email or null if no user is found.
	   * 
	   * @param email the user email.
	   */
	  public Member findByEmail(String email);

	  /**
	   * 
	   * @param id
	   * @return
	   */
      public Member findByMemberId(long id);
      

      /**
       * Return members by ids
       */
	  public List<Member> findByMemberIdIn(List<Long> ids);
	  
	  @Query(value="select v.memberId from Member v ")
	  	public List<Long> findMemberByMemId();
}
