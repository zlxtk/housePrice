package com.zlxtk.housePrice.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zlxtk.utils.DateUtil;

/**
 * 城市dao处理
 *
 * @see com.zlxtk.housePrice.data.CityDaoImpl
 * @author ZLXTK
 * @date 2017年2月28日
 *
 */
public class CityDaoImpl extends DataBase {

	/**
	 * 获取所有城市code
	 * 
	 * @param datas
	 * @return
	 * @throws Exception
	 */
	public List<String> getCityCodes() throws Exception {
		Connection conn = null;
		List<String> codes = new ArrayList<String>();
		try {
			// 打开链接
			conn = getConnection();
			// 插入数据
			String sql = "SELECT `code` FROM `city`  WHERE state=1 ";
			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			// 结果集
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				codes.add(rs.getString("code"));
			}
			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return codes;
	}
	
	/**
	 * 判断今日是不是已运行过程序
	 * @return
	 * @throws Exception
	 */
	public boolean isRuned() throws Exception{
		Connection conn = null;
		String date="";
		try {
			// 打开链接
			conn = getConnection();
			// 插入数据
			String sql = " SELECT date FROM `meanprice` ORDER BY date DESC LIMIT 0,1 ";
			// statement用来执行SQL语句
			Statement statement = conn.createStatement();
			// 结果集
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				date=rs.getString("date");
			}
			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		
		String today=DateUtil.getDateString(new Date(), DateUtil.DATE_FORMT_004);
		if(today.equals(date)){
			return true;
		}
		return false;
	}

}
