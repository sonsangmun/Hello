package com.example.smson.hello.location;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * 지오펜스 및 트리거를 사용하여 지리적 위치 내부 또는 외부에 머물거나 들어오거나 나가는 사용자를 식별할 수 있습니다.
 * 지오펜스는 원형 또는 다각형으로 정의된 지리적 영역
 * Created by sonsangmun on 2015-05-13.
 */
public class GeofenceTransitionsIntentService extends IntentService{

    public GeofenceTransitionsIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            List triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                Toast.makeText(getApplicationContext(), "학원에 들어왔다", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "학원을 나갔다", Toast.LENGTH_LONG).show();
            }
        }
    }
}
