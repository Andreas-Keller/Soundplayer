package ch.brownbag.soundplayer;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;


public class BackgroundAudioService extends Service implements OnCompletionListener{

	MediaPlayer mediaPlayer;
	
	@Override
	public IBinder onBind(Intent intent){
		return null;
	}
	
	@Override
	public void onCreate(){
		Log.v("PLAYERSERVICE", "onCreate");
	
				
		mediaPlayer = new MediaPlayer();
		
//		mediaPlayer = MediaPlayer.create(this, null);
		
		mediaPlayer.setOnCompletionListener(this);
	}
		
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v("PLAYERSERVICE","onStartCommand");
		if (!mediaPlayer.isPlaying()) {
			
			try {
				mediaPlayer.setDataSource(intent.getData().toString());
			} catch (IllegalArgumentException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
			try {
				mediaPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
			
			mediaPlayer.start();
		}
		return START_STICKY;
	}
		
	
	public void onDestroy(){
		if (mediaPlayer.isPlaying())
		{
			mediaPlayer.stop();
		}
		mediaPlayer.release();
		Log.v("SIMPLESERVICE", "onDestroy");	
	}
	
		
	
	public void onCompletion(MediaPlayer _mediaPlayer) {
		stopSelf();
	}
	
	
	
}//end class
