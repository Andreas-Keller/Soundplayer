package ch.brownbag.soundplayer;

import java.io.File;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity implements OnClickListener
{	
	
	private TextView mTextView;
	
	
	public final static String EXTRA_MESSAGE = "ch.brownbag.soundplayer.MESSAGE";
    

	
	
	Cursor cursor;
	public static int STATE_SELECT_ALBUM = 0;
	public static int STATE_SELECT_SONG = 1;
	int currentState = STATE_SELECT_ALBUM;
	
	Button startPlaybackButton, stopPlaybackButton;
	Intent playbackServiceIntent;
	
	
    /** Called when the activity is first created.  */
    @Override
    public void onCreate(Bundle savedInstanceState) {
 System.out.println("--------------------------------onCreate()");
    	
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
    	
    	startPlaybackButton = (Button) this.findViewById(R.id.StartPlaybackButton);
    	stopPlaybackButton = (Button) this.findViewById(R.id.StopPlaybackButton);
    	startPlaybackButton.setOnClickListener(this);
    	stopPlaybackButton.setOnClickListener(this);
    	playbackServiceIntent = new Intent(this, BackgroundAudioService.class);

    	handleIntent(getIntent());

    	mTextView = (TextView) findViewById(R.id.text);
    	mTextView.setText("Zebra");
    	}

    @Override
    public void onStop(){
    	super.onStop();
   	 System.out.println("--------------------------------onStop()");
	 System.out.println("--------------------------------mText: " + this.mTextView.toString());

    }

    @Override
    public void onDestroy(){
    	super.onDestroy();
    	 System.out.println("--------------------------------onDestroy()");

    }

    @Override
    public void onStart(){
    	super.onStart();
    	 System.out.println("--------------------------------onStart()");

    }

    @Override
    public void onResume(){
    	super.onResume();
    	 System.out.println("--------------------------------onResume()");

    }

    @Override
    public void onRestart(){
    	super.onRestart();
    	 System.out.println("--------------------------------onRestart()");

    }

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

    		 			
    			int fileColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
    			int mimeTypeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE);
    			
    			String audioFilePath = cursor.getString(fileColumn);
    			String mimeType = cursor.getString(mimeTypeColumn);
    			
		
//    			Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
    			File newFile = new File(audioFilePath);
//    			intent.setDataAndType(Uri.fromFile(newFile), mimeType);
    			
    			
    			playbackServiceIntent.setDataAndType(Uri.fromFile(newFile), mimeType);
    			
    			startService(playbackServiceIntent);
    			
//    			startActivity(intent);
    			    		
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
        
        
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

       
        
//        return super.onCreateOptionsMenu(menu);
        
        return true;
    }
    
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
             	            	
            	openSearch();
                return true;
            case R.id.action_settings:
            	
            	
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


	@Override
	public void onClick(View v) {
	
		if (v == startPlaybackButton) {
//			startService(playbackServiceIntent);
			finish();
			startActivity(getIntent());
			} else if (v == stopPlaybackButton) {
			stopService(playbackServiceIntent);
			finish();
			}
		}
	
	
	
	public void openSearch(){
		
		
   	 System.out.println("--------------------------------openSearch()");
		
	      Intent popUp = new  Intent(this, DisplayMessageActivity.class);
          startActivity(popUp);
		
		
		
		
	}
	
	
	public void openSettings(){
		
		  Intent popUp = new  Intent(this, DisplayMessageActivity.class);
          startActivity(popUp);
		
	}
	
	
	
	@Override
    protected void onNewIntent(Intent intent) {
       
     	 System.out.println("--------------------------------onNewIntent(): " + intent.getAction());
        handleIntent(intent);

    }

    private void handleIntent(Intent intent) {
    	
     	 System.out.println("--------------------------------handleIntent(): " + intent.getAction());
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            
           showResults(query);
            	
            }
    	  System.out.println("---------------------------------handleIntent() ENDE");
          
        }
    

	
private void showResults(String query) {
	          System.out.println("showResults()   :"+query +"\n\n\n\n");
//	          mTextView = (TextView) findViewById(R.id.text);
	          if(mTextView == null)
	          {
	        	  System.out.println("--------------------------------mTextView ist null");
	          }
	          else
	          {
	        	  System.out.println("---------------------------------mTextView ist NICHT null");
	          }
			
    Cursor cursor = managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                            new String[] {query}, null);

    if (cursor == null) {
        //There are no results
        mTextView.setText(getString(R.string.no_results, new Object[] {query}));
//				mTextView.setText(query);

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
      	  
//			Intent results = new Intent(this, SearchResultsActivity.class);
//			startActivity(results);
			
			
			
      	  System.out.println("---------------------------------showResult() ENDE");
			
	
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public void onBackPressed(){
//		finish();
//		startActivity(getIntent());
//	}
	
	
	
//	public void addFooterView(View v, Object data, boolean isSelectable){
//		startPlaybackButton = (Button) this.findViewById(R.id.StartPlaybackButton);
//    	stopPlaybackButton = (Button) this.findViewById(R.id.StopPlaybackButton);
//    	startPlaybackButton.setOnClickListener(this);
//    	stopPlaybackButton.setOnClickListener(this);
//		
//	}
//	
		
}//end class MainActivity

