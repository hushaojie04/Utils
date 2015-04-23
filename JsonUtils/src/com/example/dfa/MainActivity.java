package com.example.dfa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import flexjson.JSONException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("System.out", "main");
		System.out.println("###############################");
		try {
	        String json1 = "{\"ims\":[{\"name\":\"ÀºÇò\"},{\"name\":\"×ãÇò\"}],\"tm\":{\"number\":\"15896352635\",\"type\":\"ÊÖ»úºÅ\"},\"passWord\":\"123\",\"userName\":\"admin\"}";

			InputStream input = getAssets().open("data/data2.txt");
			StringBuilder mBuilder = new StringBuilder();
			byte[] a = new byte[1024];

			while ((input.read(a)) != -1) {
				mBuilder.append(new String(a));
			}
			Data1 um1 = new Data1();
			try {
				String mm = JSONConverter.fromJSon(json1,
						um1);
//				String dd = JSONConverter.toJSon(um1);
//				System.out.println(dd);

			}catch(JSONException e)
			{
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(mBuilder.toString());
			System.out.println(um1.passWord);
			System.out.println(um1.userName);
			System.out.println(um1.tm);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
