package tw.edu.tp.cksh.minimap;

import java.io.IOException;
import java.util.List;

import android.location.*;
import android.os.Bundle;
import android.widget.TextView;

public class OurGPSListener implements LocationListener {
	
	public MainActivity referenceToMainActivity;

	@Override
	public void onLocationChanged(Location loc) {
		TextView label = (TextView) 
					referenceToMainActivity.findViewById(R.id.coord_text);
		
		Geocoder geocoder = new Geocoder(referenceToMainActivity);
		try {
			List<Address> addresses = 
					geocoder.getFromLocation(loc.getLatitude(), 
											 loc.getLongitude(), 
											 1);
			if (addresses.size() > 0) {
				label.setText(addresses.get(0).toString());
			}
		} catch (IOException e) {
			label.setText("沒有連上網QAQ");
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO 自動產生的方法 Stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO 自動產生的方法 Stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO 自動產生的方法 Stub

	}

}
