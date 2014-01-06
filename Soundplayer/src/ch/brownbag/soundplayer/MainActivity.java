package ch.brownbag.soundplayer;

import java.io.File;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity
{	
	public final static String EXTRA_MESSAGE = "ch.brownbag.soundplayer.MESSAGE";
    
//	Button playButton;
	Cursor cursor;
	public static int STATE_SELECT_ALBUM = 0;
	public static int STATE_SELECT_SONG = 1;
	int currentState = STATE_SELECT_ALBUM;
	
	
    /** Called when the activity is first created.  */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);
    	String[] columns = { android.provider.MediaStore.Audio.Albums._ID,
    			android.provider.MediaStore.Audio.Albums.ALBUM };
    	cursor = managedQuery(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
    			columns, null, null, null);
    	String[] displayFields = new String[] { MediaStore.Audio.Albums.ALBUM };
    	int[] displayViews = new int[] { android.R.id.text1 };
    	setListAdapter(new SimpleCursorAdapter(this,
    			android.R.layout.simple_list_item_1, cursor, displayFields,
    			displayViews));

    	// playButton=(Button)this.findViewById(R.id.Button01);
    	// playButton.setOnClickListener(this);
    	// String[] columns = {
    	// MediaStore.Audio.Media.DATA,
    	// MediaStore.Audio.Media._ID,
    	// MediaStore.Audio.Media.TITLE,
    	// MediaStore.Audio.Media.DISPLAY_NAME,
    	// MediaStore.Audio.Media.MIME_TYPE,
    	// MediaStore.Audio.Media.ARTIST,
    	// MediaStore.Audio.Media.ALBUM,
    	// MediaStore.Audio.Media.IS_RINGTONE,
    	// MediaStore.Audio.Media.IS_ALARM,
    	// MediaStore.Audio.Media.IS_MUSIC,
    	// MediaStore.Audio.Media.IS_NOTIFICATION
    	// };
    	// Cursor cursor =
    	// managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, columns,
    	// null, null, null);
    	// int fileColumn = cursor.getColumnIndex (MediaStore.Audio.Media.DATA);
    	// int titleColumn = cursor.getColumnIndex
    	// (MediaStore.Audio.Media.TITLE);
    	// int displayColumn = cursor.getColumnIndex
    	// (MediaStore.Audio.Media.DISPLAY_NAME);
    	// int mimeTypeColumn = cursor.getColumnIndex
    	// (MediaStore.Audio.Media.MIME_TYPE);
    	// if (cursor.moveToFirst()) {
    	// String audioFilePath = cursor.getString(fileColumn);
    	// String mimeType = cursor.getString(mimeTypeColumn);
    	// Log.v("AUDIOPLAYER",audioFilePath);
    	// Log.v("AUDIOPLAYER",mimeType);
    	// Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
    	// File newFile = new File(audioFilePath);
    	// intent.setDataAndType(Uri.fromFile(newFile), mimeType);
    	// startActivity(intent);
    	// }
    }

    // @Override
    // public void onClick(View v) {
    // Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
    //
    // File sdcard = Environment.getExternalStorageDirectory();
    //
    // File audioFile = new File(sdcard.getPath() + "/Music/Emo_Mamma.mp3");
    //
    // intent.setDataAndType(Uri.fromFile(audioFile), "audio/mp3");
    // startActivity(intent);
    //
    // }
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	if (currentState == STATE_SELECT_ALBUM) {
    		if (cursor.moveToPosition(position)) {

    			String[] columns = { MediaStore.Audio.Media.DATA,
    					MediaStore.Audio.Media._ID,
    					MediaStore.Audio.Media.TITLE,
    					MediaStore.Audio.Media.DISPLAY_NAME,
    					MediaStore.Audio.Media.MIME_TYPE, };
    			String where = android.provider.MediaStore.Audio.Media.ALBUM
    					+ "=?";
    			String whereVal[] = { cursor.getString(cursor
    					.getColumnIndex(MediaStore.Audio.Albums.ALBUM)) };
    			String orderBy = android.provider.MediaStore.Audio.Media.TITLE;
    			cursor = managedQuery(
    					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, columns,
    					where, whereVal, orderBy);
    			String[] displayFields = new String[] { MediaStore.Audio.Media.DISPLAY_NAME };
    			int[] displayViews = new int[] { android.R.id.text1 };
    			setListAdapter(new SimpleCursorAdapter(this,
    					android.R.layout.simple_list_item_1, cursor,
    					displayFields, displayViews));
    			currentState = STATE_SELECT_SONG;
    		}
    	} else if (currentState == STATE_SELECT_SONG) {
    		if (cursor.moveToPosition(position)) {

    			int fileColumn = cursor
    					.getColumnIndex(MediaStore.Audio.Media.DATA);
    			int mimeTypeColumn = cursor
    					.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE);
    			String audioFilePath = cursor.getString(fileColumn);
    			String mimeType = cursor.getString(mimeTypeColumn);
    			Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
    			File newFile = new File(audioFilePath);
    			intent.setDataAndType(Uri.fromFile(newFile), mimeType);
    			startActivity(intent);
    			}
    		}
    	}
    			
    			
    			
        
    /** Called when the user clicks the Send button */
//    public void sendMessage(View view)
//    {
//    	Intent intent = new Intent(this, DisplayMessageActivity.class);
//    	EditText editText = (EditText) findViewById(R.id.edit_message);
//    	String message = editText.getText().toString();
//    	intent.putExtra(EXTRA_MESSAGE, message);
//    	startActivity(intent);
//    }
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

