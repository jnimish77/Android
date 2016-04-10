package com.appsrox.my_notes;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class MainActivity extends TabActivity implements OnTabChangeListener {
	
	//private static final String TAG = "MainActivity";
	
	public static final String TAB_MANAGE = "manage";
	public static final String TAB_BROWSE = "browse";
	
	private LayoutInflater inflater;
	private Resources res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        inflater = getLayoutInflater();
        res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        
        tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
        
        // Browse
        intent = new Intent().setClass(this, BrowseActivity.class);
        spec = tabHost.newTabSpec(TAB_BROWSE).setIndicator(createTabIndicator(res.getString(R.string.browse))).setContent(intent);
        tabHost.addTab(spec);
        
        // Manage
        intent = new Intent().setClass(this, ManageActivity.class);
        spec = tabHost.newTabSpec(TAB_MANAGE).setIndicator(createTabIndicator(res.getString(R.string.manage))).setContent(intent);
        tabHost.addTab(spec);        
        
        tabHost.setOnTabChangedListener(this);
    }
    
    private View createTabIndicator(String label) {
    	View tabIndicator = inflater.inflate(R.layout.tabindicator, null);
    	TextView tv = (TextView) tabIndicator.findViewById(R.id.label);
    	tv.setText(label);
    	return tabIndicator;
    }

	@Override
	public void onTabChanged(String tabId) {
	}
	
	@Override
	protected void onDestroy() {
		my_notes.clearAuth();
		super.onDestroy();
	}	

}