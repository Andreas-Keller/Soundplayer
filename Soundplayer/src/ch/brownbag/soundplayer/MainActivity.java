package ch.brownbag.soundplayer;

import android.app.*;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{	
	 public final static String EXTRA_MESSAGE = "ch.brownbag.soundplayer.MESSAGE";
    /** Called when the activity is first created.  */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
}

// this is just a comment
// this is another comment from thursday 19 dec 16:00