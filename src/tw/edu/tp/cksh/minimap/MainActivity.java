package tw.edu.tp.cksh.minimap;

import android.os.Bundle;
import android.location.*;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;

public class MainActivity extends Activity {
	
	private LocationManager manager;
	private OurGPSListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = (LocationManager) 
				  this.getSystemService(LOCATION_SERVICE);
		
		Location coordinate = 
				manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		TextView label = (TextView) findViewById(R.id.coord_text);
		if (coordinate != null) {
			label.setText(coordinate.toString());
		} else {
			label.setText("沒有已知的座標，需要偵測GPS");
		}
		
		listener = new OurGPSListener();
		listener.referenceToMainActivity = this;
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 
									 5 * 1000 /*毫秒*/, 10 /*m*/, listener);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
									10 * 1000 /*毫秒*/, 30 /*m*/, listener);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		manager.removeUpdates(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
