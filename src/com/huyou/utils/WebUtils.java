package com.huyou.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.huyou.domain.NbaData;

public class WebUtils {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	//�����������KEY
	public static final String APPKEY ="4a99d4d7e1dd179d87d62c95a25dffe6";
	public static NbaData data;

	//1.NBA��������������
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static NbaData getRequest1(){

		String result =null;
		String url ="http://op.juhe.cn/onebox/basketball/nba";//����ӿڵ�ַ
		Map params = new HashMap();//�������
		params.put("key",APPKEY);//Ӧ��APPKEY(Ӧ����ϸҳ��ѯ)
		params.put("dtype","");//�������ݵĸ�ʽ,xml��json��Ĭ��json

		try {
			result =net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if(object.getInt("error_code")==0){
				System.out.println(object.get("result"));
			}else{
				System.out.println(object.get("error_code")+":"+object.get("reason"));
			}
			parseData(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	private static void parseData(String result) {

		Gson gson = new Gson();
		data = gson.fromJson(result, NbaData.class);
		//System.out.println(data);

	}



	/**
	 *
	 * @param strUrl �����ַ
	 * @param params �������
	 * @param method ���󷽷�
	 * @return  ���������ַ���
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String net(String strUrl, Map params,String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if(method==null || method.equals("GET")){
				strUrl = strUrl+"?"+urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if(method==null || method.equals("GET")){
				conn.setRequestMethod("GET");
			}else{
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params!= null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	//��map��תΪ���������
	@SuppressWarnings("rawtypes")
	public static String urlencode(Map<String,Object>data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String changName(String mPlayer) {
		String newName = "";
		if ("76��".equals(mPlayer)) {
			newName="�ѳ�76��";
		}else if ("������".equals(mPlayer)) {
			newName="ӡ�ڰ��ɲ�����";
		}else if ("�Ȼ�".equals(mPlayer)) {
			newName="�������Ȼ�";
		}else if ("ħ��".equals(mPlayer)) {
			newName="������ħ��";
		}else if ("Сţ".equals(mPlayer)) {
			newName="����˹Сţ";
		}else if ("����".equals(mPlayer)) {
			newName="��³��������";
		}else if ("��������".equals(mPlayer)) {
			newName="��ʿ�ٿ�������";
		}else if ("����".equals(mPlayer)) {
			newName="�����ɻ���";
		}else if ("����".equals(mPlayer)) {
			newName="ӡ�ڰ��ɲ�����";
		}else if ("������".equals(mPlayer)) {
			newName="���׶�����";
		}else if ("�Ʒ�".equals(mPlayer)) {
			newName="�����ػƷ�";
		}else if ("��¹".equals(mPlayer)) {
			newName="�ܶ��ֻ���¹";
		}else if ("ɭ����".equals(mPlayer)) {
			newName="�����մ�ɭ����";
		}else if ("����".equals(mPlayer)) {
			newName="�Ϸ�˹����";
		}else if ("���".equals(mPlayer)) {
			newName="������";
		}else if ("����".equals(mPlayer)) {
			newName="�°¶�������";
		}else if ("����".equals(mPlayer)) {
			newName="��������������";
		}else if ("����".equals(mPlayer)) {
			newName="���������й���";
		}else if ("̫��".equals(mPlayer)) {
			newName="ӡ�ڰ��ɲ�����";
		}else if ("���".equals(mPlayer)) {
			newName="��˹�ٻ��";
		}else if ("����".equals(mPlayer)) {
			newName="��ɼ����";
		}else if ("���".equals(mPlayer)) {
			newName="��ʢ�����";
		}else if ("���".equals(mPlayer)) {
			newName="ʥ����������";
		}else if ("������".equals(mPlayer)) {
			newName="��ɼ����";
		}else if ("��ʿ".equals(mPlayer)) {
			newName="������ʿ";
		}else if ("��ʿ".equals(mPlayer)) {
			newName="������ʿ";
		}else if ("���˹".equals(mPlayer)) {
			newName="ŦԼ���˹";
		}else if ("��ʿ".equals(mPlayer)) {
			newName="���������ʿ";
		}else if ("��ӥ".equals(mPlayer)) {
			newName="����������ӥ";
		}else if ("�촬".equals(mPlayer)) {
			newName="��ɼ�촬";
		}
		
		return newName;
	}
}
