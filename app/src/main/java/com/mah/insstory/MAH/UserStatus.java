package com.mah.insstory.MAH;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.content.*;
import android.content.res.*;
import android.widget.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.util.HashMap;
import android.content.SharedPreferences;

public class UserStatus extends Service {
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private DatabaseReference Database = _firebase.getReference("users");
	private ChildEventListener _Database_child_listener;
	private FirebaseAuth auth;
	private HashMap<String, Object> map = new HashMap<>();
	private SharedPreferences lang;
	private SharedPreferences save;
	private Calendar cc = Calendar.getInstance();
	
	public IBinder onBind(Intent arg0) {
		return null;}
	
	
	public void onCreate() {
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		com.google.firebase.FirebaseApp.initializeApp(this);
		_Database_child_listener = new ChildEventListener() {
			
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				Database = FirebaseDatabase.getInstance().getReference(".info/connected");
				Database.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(@NonNull DataSnapshot snapshot) {
						boolean connected = snapshot.getValue(Boolean.class);
						if (connected) {
							Database = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
							if (_childValue.get("blocked").toString().equals("true")) {
								Database.child("online").setValue("false");
							}
							else {
								if (_childValue.get("hide_status").toString().equals("true")) {
									Database.child("online").setValue("true");	
									Database.child("online").onDisconnect().setValue("false");
								}
								else {
									cc = Calendar.getInstance();
									Database.child("online").setValue("true");
									Database.child("online").onDisconnect().setValue(cc.getTimeInMillis());
								}
							}
						}
					}
					
					@Override
					public void onCancelled(@NonNull DatabaseError error) {
						
					}
				});
			};
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			};
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
			}
			
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
			}
			
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
			}
			
		};
		Database.addChildEventListener(_Database_child_listener);
	}
}
