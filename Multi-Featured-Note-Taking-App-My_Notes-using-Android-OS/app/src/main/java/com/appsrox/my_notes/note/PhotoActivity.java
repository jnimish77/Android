package com.appsrox.my_notes.note;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.appsrox.my_notes.R;
import com.appsrox.my_notes.my_notes;
import com.appsrox.my_notes.model.Attachment;

/**
 * @author appsrox.com
 *
 */
public class PhotoActivity extends Activity {
	
	//private static final String TAG = "PhotoActivity";
	
	private ImageView iv;
	private ImageButton backBtn;
	private ImageButton deleteBtn;
	
	private SQLiteDatabase db;
	private Attachment attachment;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        findViews();
        
        db = my_notes.db;
        
        long id = getIntent().getLongExtra(Attachment.COL_ID, 0);
        if (id > 0) {
        	attachment = new Attachment(id);
        	attachment.load(db);
        	iv.setImageURI(Uri.parse(attachment.getUri()));
        }
    }
    
	private void findViews() {
		iv = (ImageView) findViewById(R.id.image);
		backBtn = (ImageButton) findViewById(R.id.back_btn);
		deleteBtn = (ImageButton) findViewById(R.id.delete_btn);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_btn:
			finish();
			break;
			
		case R.id.delete_btn:
			attachment.delete(db);
			finish();
			break;			
		}
	}	

}
