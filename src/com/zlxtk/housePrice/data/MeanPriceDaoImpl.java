package com.zlxtk.housePrice.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zlxtk.utils.DateUtil;

/**
 * 平均房价dao处理
 *
 * @see com.zlxtk.housePrice.data.MeanPriceDaoImpl
 * @author ZLXTK
 * @date 2017年2月28日
 *
 */
public class MeanPriceDaoImpl extends DataBase {

	/**
	 * 保存各区平均房价数据
	 * 
	 * @param datas
	 * @return
	 * @throws Exception
	 */
	public int save(List<Map<String, String>> datas,String city) throws Exception {
		Connection conn = null;
		try {
			// 打开链接
			conn = getConnection();
			String date=DateUtil.getDateString(new Date(), DateUtil.DATE_FORMT_004);
			for(Map<String, String> map:datas){
				// 插入数据
				String sql = "INSERT INTO `meanPrice` ( `name`, `price`, `city`, `date`) VALUES ( ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, map.get("name"));
				ps.setInt(2, Integer.parseInt( map.get("price")));
				ps.setString(3, city);
				ps.setString(4, date);
				ps.executeUpdate();
				// 完成后关闭
				ps.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return 1;
	}

}
