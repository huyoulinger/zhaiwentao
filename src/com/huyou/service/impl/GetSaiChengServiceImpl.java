package com.huyou.service.impl;

import java.util.List;

import com.huyou.dao.GetSaiChengDao;
import com.huyou.dao.impl.GetSaiChengDaoImpl;
import com.huyou.domain.NbaData;
import com.huyou.domain.TeamScore;
import com.huyou.service.GetSaiChengService;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class GetSaiChengServiceImpl implements GetSaiChengService {
	GetSaiChengDao dao = new GetSaiChengDaoImpl();
	@Override
	public List<TeamScore> geTeamLists() {
		// TODO Auto-generated method stub
		return dao.getTeamLists();
	}
	/*
	@Override
	public boolean update(NbaData data) {
		// TODO Auto-generated method stub
		return dao.update(data);
	}
	*/
	@Override
	public boolean findTeamDetail(String player1, String player2, String time) {
		// TODO Auto-generated method stub
		return dao.findTeamDetail(player1,player2,time);
	}
	@Override
	public boolean update(String player1, String player2, String title,
			String time, String score) {
		// TODO Auto-generated method stub
		return dao.update(player1,player2,title,time,score);
	}
		

}
