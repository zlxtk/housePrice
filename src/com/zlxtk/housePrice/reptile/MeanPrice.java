package com.zlxtk.housePrice.reptile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class MeanPrice {
	public WebClient webClient;

	public MeanPrice() {
		/** HtmlUnit请求web页面 */
		// webClient = new WebClient(BrowserVersion.CHROME);
		//
		// webClient.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		// webClient.getOptions().setCssEnabled(false); // 禁用css支持
		// webClient.getOptions().setThrowExceptionOnScriptError(false);//
		// // js运行错误时，是否抛出异常
		// webClient.getOptions().setTimeout(20000);
	}

	/**
	 * 根据城市的代码获取该城市每个区的房价
	 * 
	 * @param city
	 *            城市的代码，比如 zz:郑州，sh:上海
	 * @return [[name:金水区,price:12000],[name:二七区,price:14000]]
	 */
	public List<Map<String, String>> getMeanPrice(String city) {
		List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
		/** HtmlUnit请求web页面 */
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		try {

			webClient.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
			webClient.getOptions().setCssEnabled(false); // 禁用css支持
			webClient.getOptions().setThrowExceptionOnScriptError(false);//
			// js运行错误时，是否抛出异常
			webClient.getOptions().setTimeout(20000);
			HtmlPage page = webClient.getPage("http://fangjia.fang.com/pinggu/2015/Map.aspx?cityshort=" + city);

			webClient.waitForBackgroundJavaScript(1 * 1000);

			String pageXml = page.asXml(); // 以xml的形式获取响应文本

			/** jsoup解析文档 */
			Document doc = Jsoup.parse(pageXml);
			Elements links = doc.select("div.district_marker");

			// 如果没查到数据，返回空值
			if (links == null || links.size() <= 0) {
				return null;
			}

			for (Element e : links) {
				Map<String, String> map = new HashMap<>();
				map.put("name", e.select("h3").text());
				map.put("price", e.select("p").get(0).text());
				datas.add(map);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			webClient.close();

		}
		return datas;
	}

}
