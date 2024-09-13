package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
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
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.aghajari.emojiview.*;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class WalletHomeFragmentActivity extends Fragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String WalletStr = "";
	private HashMap<String, Object> UserAvatar = new HashMap<>();
	private HashMap<String, Object> UserName = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> history = new ArrayList<>();
	
	private LinearLayout body;
	private ScrollView scroll;
	private LinearLayout scroll_in_body;
	private LinearLayout top;
	private LinearLayout top_spc;
	private LinearLayout cp_layout;
	private LinearLayout buttons;
	private LinearLayout inventory;
	private LinearLayout daily_missions;
	private LinearLayout purchase_history;
	private LinearLayout cp_history;
	private LinearLayout shop;
	private TextView title;
	private CircleImageView avatar;
	private TextView club_points;
	private TextView request_cp;
	private TextView send_cp;
	private TextView inventory_title;
	private ImageView inventory_icon;
	private TextView daily_missions_title;
	private ImageView daily_missions_icon;
	private TextView purchase_history_title;
	private ImageView purchase_history_icon;
	private TextView cp_history_title;
	private RecyclerView cp_history_view;
	private TextView shop_title;
	private ImageView shop_icon;
	
	private Intent intent = new Intent();
	private SharedPreferences save;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private DatabaseReference wdb = _firebase.getReference("wallet");
	private ChildEventListener _wdb_child_listener;
	private SharedPreferences lang;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.wallet_home_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		body = _view.findViewById(R.id.body);
		scroll = _view.findViewById(R.id.scroll);
		scroll_in_body = _view.findViewById(R.id.scroll_in_body);
		top = _view.findViewById(R.id.top);
		top_spc = _view.findViewById(R.id.top_spc);
		cp_layout = _view.findViewById(R.id.cp_layout);
		buttons = _view.findViewById(R.id.buttons);
		inventory = _view.findViewById(R.id.inventory);
		daily_missions = _view.findViewById(R.id.daily_missions);
		purchase_history = _view.findViewById(R.id.purchase_history);
		cp_history = _view.findViewById(R.id.cp_history);
		shop = _view.findViewById(R.id.shop);
		title = _view.findViewById(R.id.title);
		avatar = _view.findViewById(R.id.avatar);
		club_points = _view.findViewById(R.id.club_points);
		request_cp = _view.findViewById(R.id.request_cp);
		send_cp = _view.findViewById(R.id.send_cp);
		inventory_title = _view.findViewById(R.id.inventory_title);
		inventory_icon = _view.findViewById(R.id.inventory_icon);
		daily_missions_title = _view.findViewById(R.id.daily_missions_title);
		daily_missions_icon = _view.findViewById(R.id.daily_missions_icon);
		purchase_history_title = _view.findViewById(R.id.purchase_history_title);
		purchase_history_icon = _view.findViewById(R.id.purchase_history_icon);
		cp_history_title = _view.findViewById(R.id.cp_history_title);
		cp_history_view = _view.findViewById(R.id.cp_history_view);
		shop_title = _view.findViewById(R.id.shop_title);
		shop_icon = _view.findViewById(R.id.shop_icon);
		save = getContext().getSharedPreferences("save", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		lang = getContext().getSharedPreferences("lang", Activity.MODE_PRIVATE);
		
		shop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				startActivity(intent);
			}
		});
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("uid") && _childValue.containsKey("yourname")) {
					UserName.put(_childValue.get("uid").toString(), _childValue.get("yourname").toString());
				}
				if (_childValue.containsKey("uid") && _childValue.containsKey("avatar")) {
					UserAvatar.put(_childValue.get("uid").toString(), _childValue.get("avatar").toString());
				}
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(avatar);
					_setCount(club_points, Double.parseDouble(_childValue.get("club_points").toString()));
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
		udb.addChildEventListener(_udb_child_listener);
		
		_wdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				wdb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						history = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								history.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						cp_history_view.setAdapter(new Cp_history_viewAdapter(history));
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
		wdb.addChildEventListener(_wdb_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		wdb.removeEventListener(_wdb_child_listener);
		WalletStr = "wallet/history/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		wdb = _firebase.getReference(WalletStr);
		wdb.addChildEventListener(_wdb_child_listener);
		cp_history_view.setLayoutManager(new LinearLayoutManager(getContext()));
		title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		club_points.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		cp_history_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		inventory_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		daily_missions_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		purchase_history_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		shop_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		_rippleRoundStroke(request_cp, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
		_rippleRoundStroke(send_cp, "#FFFFFF", "#CFD8DC", 6, 2, "#CFD8DC");
		_rippleRoundStroke(inventory, "#FFFFFF", "#CFD8DC", 6, 0, "#CFD8DC");
		_rippleRoundStroke(daily_missions, "#FFFFFF", "#CFD8DC", 6, 0, "#CFD8DC");
		_rippleRoundStroke(purchase_history, "#FFFFFF", "#CFD8DC", 6, 0, "#CFD8DC");
		_rippleRoundStroke(shop, "#FFFFFF", "#CFD8DC", 6, 0, "#CFD8DC");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_Language();
	}
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _setCount(final TextView _txt, final double _number) {
		_txt.setText(String.valueOf((long)(_number)).concat(" CP"));
		if (_number < 1000) {
			_txt.setText(String.valueOf((long)(_number)).concat(" CP"));
		}
		else {
			if (_number < 10000) {
				_txt.setText(String.valueOf(_number / 1000).substring((int)(0), (int)(3)).concat("K CP"));
			}
			else {
				if (_number < 100000) {
					_txt.setText(String.valueOf(_number / 1000).substring((int)(0), (int)(4)).concat("K CP"));
				}
				else {
					if (_number < 1000000) {
						_txt.setText(String.valueOf(_number / 1000).substring((int)(0), (int)(3)).concat("K CP"));
					}
					else {
						if (_number < 10000000) {
							_txt.setText(String.valueOf(_number / 1000000).substring((int)(0), (int)(3)).concat("M CP"));
						}
						else {
							if (_number < 100000000) {
								_txt.setText(String.valueOf(_number / 1000000).substring((int)(0), (int)(2)).concat("M CP"));
							}
							else {
								if (_number < 1000000000) {
									_txt.setText(String.valueOf(_number / 1000000).substring((int)(0), (int)(3)).concat("M CP"));
								}
								else {
									if (_number < 10000000000d) {
										_txt.setText(String.valueOf(_number / 1000000000).substring((int)(0), (int)(3)).concat("B CP"));
									}
									else {
										if (_number < 100000000000d) {
											_txt.setText(String.valueOf(_number / 1000000000).substring((int)(0), (int)(2)).concat("B CP"));
										}
									}
								}
							}
						}
					}
				}
			}
		}
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
		title.setText("Cüzdanım");
		request_cp.setText("CP İste");
		send_cp.setText("CP Gönder");
		inventory_title.setText("Envanter");
		daily_missions_title.setText("Günlük Görevler");
		purchase_history_title.setText("Satın Alma Geçmişi");
		cp_history_title.setText("Geçmiş");
		shop_title.setText("Mağaza");
	}
	
	
	public void _EnLang() {
		title.setText("My Wallet");
		request_cp.setText("Request CP");
		send_cp.setText("Send CP");
		inventory_title.setText("Inventory");
		daily_missions_title.setText("Daily Missions");
		purchase_history_title.setText("Purchase History");
		cp_history_title.setText("History");
		shop_title.setText("Shop");
	}
	
	public class Cp_history_viewAdapter extends RecyclerView.Adapter<Cp_history_viewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Cp_history_viewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.wallet_history_custom, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout body = _view.findViewById(R.id.body);
			final de.hdodenhof.circleimageview.CircleImageView avatar = _view.findViewById(R.id.avatar);
			final LinearLayout users_inf = _view.findViewById(R.id.users_inf);
			final LinearLayout info = _view.findViewById(R.id.info);
			final TextView username = _view.findViewById(R.id.username);
			final TextView sent_info = _view.findViewById(R.id.sent_info);
			final TextView cp_count = _view.findViewById(R.id.cp_count);
			final ImageView sent_status = _view.findViewById(R.id.sent_status);
			
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(UserAvatar.get(_data.get((int)_position).get("first").toString()).toString())).into(avatar);
			username.setText(UserName.get(_data.get((int)_position).get("first").toString()).toString());
			_setCount(cp_count, Double.parseDouble(_data.get((int)_position).get("cp_count").toString()));
			if (_data.get((int)_position).get("send_status").toString().equals("first")) {
				if (lang.getString("language", "").equals("")) {
					sent_info.setText("Sen, ".concat(_data.get((int)_position).get("cp_count").toString()).concat(" CP".concat(" Gönderdin")));
				}
				else {
					if (lang.getString("language", "").equals("english")) {
						sent_info.setText("You Sent ".concat(_data.get((int)_position).get("cp_count").toString().concat(" CP")));
					}
				}
				sent_status.setImageResource(R.drawable.wallet_you_sent);
			}
			else {
				if (lang.getString("language", "").equals("")) {
					sent_info.setText("Sana, ".concat(_data.get((int)_position).get("cp_count").toString().concat(" CP Gönderdi")));
				}
				else {
					if (lang.getString("language", "").equals("english")) {
						sent_info.setText("Sent You ".concat(_data.get((int)_position).get("cp_count").toString().concat(" CP")));
					}
				}
				sent_status.setImageResource(R.drawable.wallet_seconduser_sent);
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
}