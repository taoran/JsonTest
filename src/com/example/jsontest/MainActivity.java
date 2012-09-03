package com.example.jsontest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
		// TODO Auto-generated method stub
    	  //在测试过程中，经常是用本机做测试服务器，访问本机的IP地址要设置为10.0.2.2   
    	   try{  
	        String url = "http://kaixin.goufen.com/Interface/ProductList?Type=2&KeyWord=&categoryId=162116&Sort=&bp=&ep=&commrate=&ismall=0&area=&PageIndex=1";   
	        String body = getContent(url); 
	        JSONArray array = new JSONArray(body); 
	        for(int i=0; i<array.length(); i++){   
	            JSONObject obj = array.getJSONObject(i);   
 				System.out.println(obj.getString("Title"));
	        }
    	   }catch(Exception e){}   
	        
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

///////////////////////////获取网址内容 开始////////////////////////////////////////////////
/**  
* 获取网址内容  
* @param url  
* @return  
* @throws Exception  
*/  
private String getContent(String url) throws Exception{   

StringBuilder sb = new StringBuilder();        
HttpClient client = new DefaultHttpClient();   
HttpParams httpParams = client.getParams();   
//设置网络超时参数   
HttpConnectionParams.setConnectionTimeout(httpParams, 3000);   
HttpConnectionParams.setSoTimeout(httpParams, 5000);   
HttpResponse response = client.execute(new HttpGet(url));  

HttpEntity entity = response.getEntity();   
if (entity != null) {   
BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));   

String line = null;   
while ((line = reader.readLine())!= null){   
sb.append(line + "\n");   
} 

reader.close();   
}   
else
{

Toast toast = Toast.makeText(MainActivity.this
	, "请检查上网是否开启！", Toast.LENGTH_SHORT);
toast.show();
//   textView1.setText("提示信息");  
}
return sb.toString();   
} 

///////////////////////////获取网址内容 结束////////////////////////////////////////////////

}
