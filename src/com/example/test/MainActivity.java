package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			System.out.println("onCreate no saveInstanceState");
		} else {
			System.out.println("onCreate with saveInstanceState " + savedInstanceState.describeContents());
		}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate");
    }

	@Override
	protected void onStart() {
		System.out.println("onStart");
		super.onStart();
	}

	@Override
	protected void onDestroy() {
		System.out.println("onDestroy");
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		System.out.println("onPause");
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		System.out.println("onResume");
		super.onPostResume();
	}
	
	@Override
	protected void onRestart() {
		System.out.println("onRestart");
		super.onRestart();
	}
	
	@Override
	protected void onStop() {
		System.out.println("onStop");
		super.onStop();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
