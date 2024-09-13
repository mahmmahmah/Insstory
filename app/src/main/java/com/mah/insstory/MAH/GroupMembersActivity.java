package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.aghajari.emojiview.*;
import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class GroupMembersActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private String UsersString = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout body;
	private CardView top;
	private RecyclerView members_list;
	private LinearLayout top_;
	private ImageView back;
	private TextView title;
	
	private SharedPreferences save;
	private SharedPreferences lang;
	private DatabaseReference gudb = _firebase.getReference("groups/group/users");
	private ChildEventListener _gudb_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.group_members);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		top = findViewById(R.id.top);
		members_list = findViewById(R.id.members_list);
		top_ = findViewById(R.id.top_);
		back = findViewById(R.id.back);
		title = findViewById(R.id.title);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		_gudb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("member_type").toString().equals("member")) {
					listmap.add(_childValue);
					members_list.setAdapter(new Members_listAdapter(listmap));
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
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
		gudb.addChildEventListener(_gudb_child_listener);
	}
	
	private void initializeLogic() {
		members_list.setLayoutManager(new LinearLayoutManager(this));
		gudb.removeEventListener(_gudb_child_listener);
		UsersString = "groups/group/".concat(getIntent().getStringExtra("group_key").concat("/".concat("users")));
		gudb =
		_firebase.getReference(UsersString);
		gudb.addChildEventListener(_gudb_child_listener);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
		_Language();
		_changeActivityFont("youtubesansregular");
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansdarkbold.otf"), 0);
	}
	public void _ThemeCustom() {
		if (save.getString("theme", "").equals("light")) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			getWindow().setStatusBarColor(0xFFFFFFFF);
			top_.setBackgroundColor(0xFFFFFFFF);
			body.setBackgroundColor(0xFFFFFFFF);
			_ImageColor(back, "#000000");
			title.setTextColor(0xFF000000);
		}
		else {
			if (save.getString("theme", "").equals("dark")) {
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
					Window w =GroupMembersActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
				}
				top_.setBackgroundColor(0xFF212121);
				body.setBackgroundColor(0xFF000000);
				_ImageColor(back, "#FFFFFF");
				title.setTextColor(0xFFFFFFFF);
			}
		}
	}
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
	
	public void _Language() {
		if (lang.getString("language", "").equals("")) {
			_TrLang();
		}
		if (lang.getString("language", "").equals("english")) {
			_EnLang();
		}
	}
	
	
	public void _TrLang() {
		title.setText("Üyeler");
	}
	
	
	public void _EnLang() {
		title.setText("Members");
	}
	
	
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".otf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}
			else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}
				else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}
					else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	public class Members_listAdapter extends RecyclerView.Adapter<Members_listAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Members_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.userlist, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout body = _view.findViewById(R.id.body);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final de.hdodenhof.circleimageview.CircleImageView avatar = _view.findViewById(R.id.avatar);
			final LinearLayout yourname_layout = _view.findViewById(R.id.yourname_layout);
			final LinearLayout username_body = _view.findViewById(R.id.username_body);
			final TextView yourname = _view.findViewById(R.id.yourname);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView verified = _view.findViewById(R.id.verified);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			if (_data.get((int)_position).containsKey("member_avatar")) {
				Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("member_avatar").toString())).into(avatar);
			}
			if (_data.get((int)_position).containsKey("member_username")) {
				username.setText(_data.get((int)_position).get("member_username").toString());
			}
			if (_data.get((int)_position).containsKey("member_verify")) {
				if (_data.get((int)_position).get("member_verify").toString().equals("blue")) {
					verified.setImageResource(R.drawable.blue_verified);
					verified.setVisibility(View.VISIBLE);
				}
				else {
					if (_data.get((int)_position).get("member_verify").toString().equals("red")) {
						verified.setImageResource(R.drawable.red_verified);
						verified.setVisibility(View.VISIBLE);
					}
					else {
						if (_data.get((int)_position).get("member_verify").toString().equals("false")) {
							verified.setVisibility(View.GONE);
						}
					}
				}
			}
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}