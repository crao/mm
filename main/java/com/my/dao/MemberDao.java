package com.my.dao;

import java.util.Collection;

import javax.transaction.Transactional;

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
      

}
