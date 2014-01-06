package ch.brownbag.soundplayer;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener
{	
	public final static String EXTRA_MESSAGE = "ch.brownbag.soundplayer.MESSAGE";
    
	Button playButton;
		
    /** Called when the activity is first created.  */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        playButton=(Button)this.findViewById(R.id.Button01);
        playButton.setOnClickListener(this);
	}
    @Override
   	public void onClick(View v) {
   		Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
   		
   		File sdcard = Environment.getExternalStorageDirectory();
   		
   		File audioFile = new File(sdcard.getPath() + "/Music/Emo_Mamma.mp3");
   		
   		intent.setDataAndType(Uri.fromFile(audioFile), "audio/mp3");
   		startActivity(intent);   		
   		
   	}
    
        
    /** Called when the user clicks the Send button */
    public void sendMessage(View view)
    {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
//                openSearch();
                return true;
            case R.id.action_settings:
//                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
}//end class MainActivity

// this is just a comment
// this is another comment from thursday 19 dec 16:00
