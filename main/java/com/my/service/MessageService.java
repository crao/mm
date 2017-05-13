
package com.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.MessageDao;
import com.my.dao.ShortListDao;
import com.my.model.Message;
import com.my.model.ShortList;

@Service
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;
	
	
	private List<Message> getMessagesTo(long memId){
		return null;
	}
	
	public Long save(Message message){
		return messageDao.save(message).getId();
	}
	
	
	public Long findShortListByToMemIdAndFromMemId(long toMemId, long fromMemId){
		return messageDao.findSlIdByToMemIdAndFromMemId(toMemId, fromMemId);
	}
	
	public List<Long> shortListsfromMemId(long fromMemId){
		return messageDao.findfromMemIdBytoMemId(fromMemId);
	}
	
	public List<Long> shortListstoMemId(long toMemId){
		return messageDao.findtoMemIdByfromMemId(toMemId);
	}
	
}
