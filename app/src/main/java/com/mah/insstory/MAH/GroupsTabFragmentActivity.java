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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class GroupsTabFragmentActivity extends Fragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private ArrayList<HashMap<String, Object>> group = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> groups_map = new ArrayList<>();
	
	private RecyclerView groups;
	private LinearLayout no_groups_layout;
	private TextView no_groups_title;
	private TextView no_groups_subtitle;
	
	private SharedPreferences save;
	private SharedPreferences lang;
	private Intent intent = new Intent();
	private DatabaseReference gdb = _firebase.getReference("groups/group");
	private ChildEventListener _gdb_child_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.groups_tab_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		groups = _view.findViewById(R.id.groups);
		no_groups_layout = _view.findViewById(R.id.no_groups_layout);
		no_groups_title = _view.findViewById(R.id.no_groups_title);
		no_groups_subtitle = _view.findViewById(R.id.no_groups_subtitle);
		save = getContext().getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getContext().getSharedPreferences("lang", Activity.MODE_PRIVATE);
		
		_gdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				no_groups_layout.setVisibility(View.GONE);
				groups.setVisibility(View.VISIBLE);
				gdb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						groups_map = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								groups_map.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						groups.setAdapter(new GroupsAdapter(groups_map));
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
				gdb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						groups_map = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								groups_map.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						groups.setAdapter(new GroupsAdapter(groups_map));
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				gdb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						groups_map = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								groups_map.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						groups.setAdapter(new GroupsAdapter(groups_map));
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		gdb.addChildEventListener(_gdb_child_listener);
	}
	
	private void initializeLogic() {
		no_groups_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		groups.setLayoutManager(new LinearLayoutManager(getContext()));
		groups.setVisibility(View.GONE);
		no_groups_layout.setVisibility(View.VISIBLE);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
		_Language();
	}
	public void _Language() {
		if (lang.getString("language", "").equals("")) {
			_TrLang();
		}
		if (lang.getString("language", "").equals("english")) {
			_EnLang();
		}
		if (lang.getString("language", "").equals("indonesia")) {
			_InLang();
		}
	}
	
	
	public void _TrLang() {
		no_groups_title.setText("Henüz Grup Yok!");
		no_groups_subtitle.setText("Yeni grup oluşturun veya bir gruba katılın!");
	}
	
	
	public void _EnLang() {
		no_groups_title.setText("No Groups Yet!");
		no_groups_subtitle.setText("Create group or join new group!");
	}
	
	
	public void _ThemeCustom() {
		no_groups_title.setTextColor(0xFF000000);
		no_groups_subtitle.setTextColor(0xFF000000);
	}
	
	
	public void _InLang() {
		no_groups_title.setText("Tidak Ada Grup!");
		no_groups_subtitle.setText("Buat Grup Atau Bergabung dengan Grup Baru!");
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
	
	public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public GroupsAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.group_view, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout body = _view.findViewById(R.id.body);
			final de.hdodenhof.circleimageview.CircleImageView avatar = _view.findViewById(R.id.avatar);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView verified = _view.findViewById(R.id.verified);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView msg = _view.findViewById(R.id.msg);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout message_tools = _view.findViewById(R.id.message_tools);
			final LinearLayout tools_body = _view.findViewById(R.id.tools_body);
			final ImageView message_open_close_menu = _view.findViewById(R.id.message_open_close_menu);
			final ImageView message_delete = _view.findViewById(R.id.message_delete);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			tools_body.setVisibility(View.GONE);
			_rippleRoundStroke(body, "#FFFFFF", "#BDBDBD", 8, 0, "#FFFFFF");
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("group_avatar").toString())).into(avatar);
			username.setText(_data.get((int)_position).get("group_name").toString());
			msg.setText(_data.get((int)_position).get("group_bio").toString());
			body.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getContext().getApplicationContext(), GroupChatActivity.class);
					intent.putExtra("group_key", _data.get((int)_position).get("group_key").toString());
					startActivity(intent);
				}
			});
			if (_data.get((int)_position).containsKey("group_verified")) {
				if (_data.get((int)_position).get("group_verified").toString().equals("true")) {
					verified.setVisibility(View.VISIBLE);
				}
			}
			else {
				verified.setVisibility(View.GONE);
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