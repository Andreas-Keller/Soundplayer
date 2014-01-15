package ch.brownbag.soundplayer;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

public class SearchResultsActivity extends ListActivity{
	
//		Intent playbackServiceIntent = new Intent(this,BackgroundAudioService.class);
//		public final static String QUERY = "ch.brownbag.soundplayer.QUERY";
		
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
		    setContentView(R.layout.search_results_activity);

		    Intent intent = getIntent();
		    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
		      String query = intent.getStringExtra(SearchManager.QUERY);
		      doMySearch(query);
		    }
	 }
	
	 
//	 @Override
//	 public boolean onCreateOptionsMenu(Menu menu) {
//	     // Inflate the options menu from XML
//	     MenuInflater inflater = getMenuInflater();
//	     inflater.inflate(R.menu.options_menu, menu);
//
//	     // Get the SearchView and set the searchable configuration
//	     SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//	     SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//	     // Assumes current activity is the searchable activity
//	     searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//	     searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
//
//	     return true;
//	 }
	 
	 
	 
	 
	 
	 
		 
	 
	 public void doMySearch(String query){
		 		if (query=="3"){
		 			  popUp();
		 			
		 		}
		 
	 }
	 
	 
	 public void popUp(){
		 Intent popUp = new  Intent(this, DisplayMessageActivity.class);
         startActivity(popUp);
		 
	 }
		 
//		 handleIntent(getIntent());
//	    }
//
//	    @Override
//	    protected void onNewIntent(Intent intent) {
//	        handleIntent(intent);
//	    }

//	    private void handleIntent(Intent intent) {
//
//	        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//	            String query = intent.getStringExtra(SearchManager.QUERY);
//	            //use the query to search your data somehow
//	           
//	          	Intent popUp = new Intent(this, DisplayMessageActivity.class);
////	          	EditText editText = (EditText) findViewById(R.id.action_search);
//	          	popUp.putExtra(QUERY,query);
//	          	startActivity(popUp);
//	          } 
	            
	            
	            
	            
	            
	            
//	          	 	 String displayName = MediaStore.Audio.Media.TITLE;
//	           		 if (displayName.toLowerCase().contains(query.toLowerCase())){
	          
	            	
//	            	int fileColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
//	    			int mimeTypeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE);
//	    			
//	    			String audioFilePath = cursor.getString(fileColumn);
//	    			String mimeType = cursor.getString(mimeTypeColumn);
	            	
//	    			Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//	            	
//	            	          	
//	            	playbackServiceIntent.setData(uri);
//	    			
//	    			startService(playbackServiceIntent);
	          
	            
	        
	    }

	
	
	
	
	
	


