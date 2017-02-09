package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.binding.SearchBinding;
import com.my.dao.SearchDao;
import com.my.model.Member;
import com.my.model.Result;

@Service
public class SearchService {
	
	@Autowired
	public SearchDao searchDao;

	public List<Result> getMatches(Member member, SearchBinding searchBinding) {
		return searchDao.findMatches(member,searchBinding);
		
	}

}
