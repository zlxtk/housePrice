package com.zlxtk.housePrice.startup;

import java.util.List;
import java.util.Map;

import com.zlxtk.housePrice.data.CityDaoImpl;
import com.zlxtk.housePrice.data.MeanPriceDaoImpl;
import com.zlxtk.housePrice.reptile.MeanPrice;

public class StartUp {

	public static void main(String[] args) {
		// // 城市代码
		// String city="zz";
		// try {
		// //得到数据
		// MeanPrice mp=new MeanPrice();
		// List<Map<String,String>> datas=mp.getMeanPrice(city);
		//
		// //保存数据到数据库
		// MeanPriceDaoImpl mpd=new MeanPriceDaoImpl();
		// mpd.save(datas,city);
		//
		// //发送邮件
		// String title=DateUtil.getDateString(new Date(),
		// DateUtil.DATE_FORMT_005)+"--"+city+"--各区平均房价";
		// StringBuffer message=new StringBuffer();
		// for(Map<String,String> map:datas){
		// message.append(map.get("name")+"\t");
		// message.append(map.get("price")+"\t");
		// message.append("元/每平方米\n");
		// }
		//
		// SendEmail se=new SendEmail();
		// se.sendEmail(title, message.toString());
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		// ---------------------------------------------------------------------------------------
		try {
			CityDaoImpl cd = new CityDaoImpl();
			//判断今日是否已执行过
			if(!cd.isRuned()){
				// 得到城市代码
				List<String> codes = cd.getCityCodes();
				StartUp su=new StartUp();
				for(String code:codes){
					System.out.println(code+" ...");
					su.saveMeanPrice(code);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 保存可查询到的所有城市的各区的平均房价
	 * 
	 * @throws Exception
	 */
	public void saveMeanPrice(String city)  {
			// 爬虫类
			MeanPrice mp = new MeanPrice();
			// 得到数据
			List<Map<String, String>> datas = mp.getMeanPrice(city);
			// 如果返回值为空，则跳过保存
			if (datas == null) {
				return;
			}
		try {
			// 数据库处理类
			MeanPriceDaoImpl mpd = new MeanPriceDaoImpl();
			// 保存数据到数据库
			mpd.save(datas, city);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
