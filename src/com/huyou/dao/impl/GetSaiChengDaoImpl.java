package com.huyou.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huyou.dao.GetSaiChengDao;
import com.huyou.domain.NbaData;
import com.huyou.domain.TeamScore;
import com.huyou.utils.JdbcUtils;
import com.huyou.utils.WebUtils;

public class GetSaiChengDaoImpl implements GetSaiChengDao {


	String driver = "com.mysql.jdbc.Driver";
	String username = System.getenv("ACCESSKEY");
	String password = System.getenv("SECRETKEY");
	//System.getenv("MYSQL_HOST_S"); 为从库，只读
	String sql_url = "jdbc:mysql://"+System.getenv("MYSQL_HOST")+":"+System.getenv("MYSQL_PORT")+"/"+System.getenv("MYSQL_DB");

	@Override
	public List<TeamScore> getTeamLists() {
		ResultSet rs = null;
		List<TeamScore> list = new ArrayList<TeamScore>();
		// 拿到连接对象
		//Connection conn = JdbcUtils.getConnection();

		Connection conn = null;
		PreparedStatement pstmt =null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(sql_url,username,password);
			pstmt = conn.prepareStatement("select * from saicheng");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				TeamScore teamList = new TeamScore();

				teamList.setId(rs.getInt("id"));
				teamList.setTitle(rs.getString("title"));
				teamList.setPlayer1(rs.getString("player1"));
				teamList.setScore(rs.getString("score"));
				teamList.setPlayer2(rs.getString("player2"));
				teamList.setTime(rs.getString("time"));
				list.add(teamList);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(rs, pstmt, conn);
		}


		return list;
	}
/*
	@Override
	public boolean update(NbaData data) {
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();

		//ResultSet rs = null;
		//Connection conn =null;
		PreparedStatement pstmt = null;
		int n=0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < data.result.list.get(i).tr.size(); j++) {

				String status =data.result.list.get(i).tr.get(j).status;

				System.out.println(status);
				if ("0".equals(status)) {

					String player1 = data.result.list.get(i).tr.get(j).player1;
					String player2 = data.result.list.get(i).tr.get(j).player2;
					String title = data.result.list.get(i).title;
					String score = data.result.list.get(i).tr.get(j).score;
					String time = data.result.list.get(i).tr.get(j).time;

					try {
						// 加载Mysql驱动
						//Class.forName(driver).newInstance();
						//conn = DriverManager.getConnection(sql_url,username,password);
						String insertSql = "insert into saicheng (title,player1,score,player2,time) values(?,?,?,?,?)";
						pstmt = conn.prepareStatement(insertSql);
						pstmt.setString(1, title);
						pstmt.setString(2, player1);
						pstmt.setString(3, score);
						pstmt.setString(4, player2);
						pstmt.setString(5, time);
						n = pstmt.executeUpdate();

						if (n != 0) {
							System.out.println("插入数据成功");
						} else {
							System.out.println("插入数据失败");
						}

					} catch (Exception e) {
						System.out.print(e);
					}finally{
						JdbcUtils.release(null, pstmt, conn);
					}						
				}
			}
		}

		return  n >0 ? true : false;
	}
*/
	@Override
	public boolean findTeamDetail(String player1, String player2, String time) {
		ResultSet rs = null;
		//Connection conn = JdbcUtils.getConnection();
		Connection conn =null;
		PreparedStatement pstmt = null;
		int a=0;
		// 加载Mysql驱动
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(sql_url,username,password);
			String sqlString ="select * from saicheng where player1 = ? and player2 = ? and time = ?";
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, player1);
			pstmt.setString(2, player2);
			pstmt.setString(3, time);

			rs=pstmt.executeQuery();
			if (!rs.next()) {
			   a=0;
			} else {
			   a=1;
			}
		} catch (Exception e) {
			System.out.print(e);
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}		
		return a==0 ? true : false;
	}

	@Override
	public boolean update(String player1, String player2, String title,
			String time, String score) {
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();

		//ResultSet rs = null;
		//Connection conn =null;
		PreparedStatement pstmt = null;
		int n=0;
		try {
			// 加载Mysql驱动
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(sql_url,username,password);
			String insertSql = "insert into saicheng (title,player1,score,player2,time) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, title);
			pstmt.setString(2, player1);
			pstmt.setString(3, score);
			pstmt.setString(4, player2);
			pstmt.setString(5, time);
			n = pstmt.executeUpdate();

			if (n != 0) {
				System.out.println("插入数据成功");
			} else {
				System.out.println("插入数据失败");
			}

		} catch (Exception e) {
			System.out.print(e);
		}finally{
			JdbcUtils.release(null, pstmt, conn);
		}						

		return  n >0 ? true : false;
	}

}
