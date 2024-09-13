package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.EditText;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
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
import java.text.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;
import androidx.core.widget.NestedScrollView;

public class NewpostBottomdialogFragmentDialogFragmentActivity extends BottomSheetDialogFragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String ReelsPath = "";
	private String ccustom = "";
	private String object_clicked = "";
	private boolean sendComn = false;
	private double CommentsCount = 0;
	private HashMap<String, Object> UserName = new HashMap<>();
	private HashMap<String, Object> UserProfile = new HashMap<>();
	private HashMap<String, Object> UserVerified = new HashMap<>();
	private HashMap<String, Object> CommentsMap = new HashMap<>();
	private double CommentsLike = 0;
	private double n = 0;
	private HashMap<String, Object> lmap = new HashMap<>();
	private String CommentKey = "";
	private HashMap<String, Object> CommentSendMap = new HashMap<>();
	
	private ArrayList<String> CommentsChild = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> comments_rmap = new ArrayList<>();
	private ArrayList<String> CommentsKey = new ArrayList<>();
	
	private LinearLayout body;
	private CardView top_;
	private NestedScrollView nestedscrool;
	private LinearLayout no_comments;
	private CardView bottom_;
	private LinearLayout top;
	private ImageView back;
	private TextView title;
	private TextView comments_count;
	private TextView comment_count_title;
	private LinearLayout nestedbody;
	private RecyclerView comments_view;
	private TextView no_comments_title;
	private TextView no_comments_subtitle;
	private LinearLayout bottom;
	private CircleImageView avatar;
	private EditText comment;
	private ImageView send;
	
	private DatabaseReference pcdb = _firebase.getReference("posts");
	private ChildEventListener _pcdb_child_listener;
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
	
	private Calendar cc = Calendar.getInstance();
	private SharedPreferences save;
	private SharedPreferences lang;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.newpost_bottomdialog_fragment_dialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		body = _view.findViewById(R.id.body);
		top_ = _view.findViewById(R.id.top_);
		nestedscrool = _view.findViewById(R.id.nestedscrool);
		no_comments = _view.findViewById(R.id.no_comments);
		bottom_ = _view.findViewById(R.id.bottom_);
		top = _view.findViewById(R.id.top);
		back = _view.findViewById(R.id.back);
		title = _view.findViewById(R.id.title);
		comments_count = _view.findViewById(R.id.comments_count);
		comment_count_title = _view.findViewById(R.id.comment_count_title);
		nestedbody = _view.findViewById(R.id.nestedbody);
		comments_view = _view.findViewById(R.id.comments_view);
		no_comments_title = _view.findViewById(R.id.no_comments_title);
		no_comments_subtitle = _view.findViewById(R.id.no_comments_subtitle);
		bottom = _view.findViewById(R.id.bottom);
		avatar = _view.findViewById(R.id.avatar);
		comment = _view.findViewById(R.id.comment);
		send = _view.findViewById(R.id.send);
		auth = FirebaseAuth.getInstance();
		save = getContext().getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getContext().getSharedPreferences("lang", Activity.MODE_PRIVATE);
		
		comment.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (comment.getText().toString().equals("")) {
					send.setImageResource(R.drawable.send_comment_outline);
					sendComn = false;
				}
				else {
					send.setImageResource(R.drawable.send_comment_filled);
					sendComn = true;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (sendComn) {
					cc = Calendar.getInstance();
					CommentKey = pcdb.push().getKey();
					CommentSendMap = new HashMap<>();
					CommentSendMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					CommentSendMap.put("comment", comment.getText().toString());
					CommentSendMap.put("date", String.valueOf((long)(cc.getTimeInMillis())));
					CommentSendMap.put("comment_key", CommentKey);
					pcdb.child(CommentKey).updateChildren(CommentSendMap);
					CommentSendMap.clear();
					comment.setText("");
				}
			}
		});
		
		_pcdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				CommentsCount++;
				pcdb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						comments_rmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								comments_rmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						nestedscrool.setVisibility(View.VISIBLE);
						no_comments.setVisibility(View.GONE);
						CommentsChild.add(_childKey);
						comments_view.setAdapter(new Comments_viewAdapter(comments_rmap));
						_setCount(comments_count, CommentsCount);
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
				pcdb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						comments_rmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								comments_rmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						comments_view.setAdapter(new Comments_viewAdapter(comments_rmap));
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
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		pcdb.addChildEventListener(_pcdb_child_listener);
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("uid") && _childValue.containsKey("username")) {
					UserName.put(_childValue.get("uid").toString(), _childValue.get("username").toString());
				}
				if (_childValue.containsKey("uid") && _childValue.containsKey("avatar")) {
					UserProfile.put(_childValue.get("uid").toString(), _childValue.get("avatar").toString());
				}
				if (_childValue.containsKey("uid") && _childValue.containsKey("verify")) {
					UserVerified.put(_childValue.get("uid").toString(), _childValue.get("verify").toString());
				}
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(avatar);
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
		_DatabaseCustom();
		_language();
		sendComn = false;
		comments_view.setLayoutManager(new LinearLayoutManager(getContext()));
		no_comments.setVisibility(View.VISIBLE);
		nestedscrool.setVisibility(View.GONE);
		_edittext_mh(comment);
		title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		no_comments_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
	}
	
	public void _TrLang() {
		
		
		
		
		
		
	}
	
	
	public void _EnLang() {
		
		
		
		
		
		
	}
	
	
	public void _InLang() {
		
		
		
		
		
		
	}
	
	
	public void _language() {
		if ("".equals("")) {
			_TrLang();
		}
		if ("".equals("english")) {
			_EnLang();
		}
		if ("".equals("indonesia")) {
			_InLang();
		}
	}
	
	
	public void _DatabaseCustom() {
		pcdb.removeEventListener(_pcdb_child_listener);
		ccustom = "posts/".concat(getActivity().getIntent().getStringExtra("key").concat("/comments"));
		
		pcdb = _firebase.getReference(ccustom);
		pcdb.addChildEventListener(_pcdb_child_listener);
	}
	
	
	public void _textview_mh(final TextView _txt, final String _value) {
		_txt.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
		//_txt.setTextIsSelectable(true);
		updateSpan(_value, _txt);
	}
	private void updateSpan(String str, TextView _txt){
		SpannableString ssb = new SpannableString(str);
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(?<![^\\s])(([@]{1}|[#]{1})([A-Za-z0-9_-]\\.?)+)(?![^\\s,])");
		    java.util.regex.Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			ProfileSpan span = new ProfileSpan();
			ssb.setSpan(span, matcher.start() , matcher. end() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} 
		_txt.setText(ssb);
		
	}
	private class ProfileSpan extends android.text.style.ClickableSpan{
		
		
		@Override
		public void onClick(View view){
			
			if(view instanceof TextView){
				    TextView tv = (TextView)view;
				
				    if(tv.getText() instanceof Spannable){
					        Spannable sp = (Spannable)tv.getText();
					    
					        int start = sp.getSpanStart(this);
					        int end = sp.getSpanEnd(this);
					        object_clicked = sp.subSequence(start,end).toString();
					SketchwareUtil.showMessage(getContext().getApplicationContext(), object_clicked);
				}
			}
			
		}
		@Override
		    public void updateDrawState(TextPaint ds) {
			        ds.setUnderlineText(false);
			        ds.setColor(Color.parseColor("#2196F3"));
			        ds.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
			    }
	}
	
	
	public void _edittext_mh(final TextView _txt) {
		final TextView regex1 = new TextView(getActivity());
		
		
		regex1.setText("(?<![^\\s])(([@]{1}|[#]{1})([A-Za-z0-9_-]\\.?)+)(?![^\\s,])");
		final String mentionColor = "#2196F3";
		_txt.addTextChangedListener(new TextWatcher() {
			ColorScheme keywords1 = new ColorScheme(java.util.regex.Pattern.compile(regex1.getText().toString()), Color.parseColor(mentionColor));
			final ColorScheme[] schemes = {keywords1};
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				removeSpans(s, android.text.style.ForegroundColorSpan.class);
				for(ColorScheme scheme : schemes) {
					for(java.util.regex.Matcher m = scheme.pattern.matcher(s);
					m.find();) {
						s.setSpan(new android.text.style.ForegroundColorSpan(scheme.color), m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
						s.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						
					}
				}
			}
			void removeSpans(Editable e, Class type) {
				android.text.style.CharacterStyle[] spans = e.getSpans(0, e.length(), type);
				for (android.text.style.CharacterStyle span : spans) {
					e.removeSpan(span);
				}
			}
			class ColorScheme {
				final java.util.regex.Pattern pattern;
				final int color;
				ColorScheme(java.util.regex.Pattern pattern, int color) {
					this.pattern = pattern;
					this.color = color;
				}
			}
		});
	}
	
	
	public void _setCount(final TextView _txt, final double _number) {
		_txt.setText(String.valueOf((long)(_number)));
		if (_number < 1000) {
			_txt.setText(String.valueOf((long)(_number)));
		}
		else {
			if (_number < 10000) {
				_txt.setText(String.valueOf(_number / 1000).substring((int)(0), (int)(3)).concat("K"));
			}
			else {
				if (_number < 100000) {
					_txt.setText(String.valueOf(_number / 1000).substring((int)(0), (int)(4)).concat("K"));
				}
				else {
					if (_number < 1000000) {
						_txt.setText(String.valueOf(_number / 1000).substring((int)(0), (int)(3)).concat("K"));
					}
					else {
						if (_number < 10000000) {
							_txt.setText(String.valueOf(_number / 1000000).substring((int)(0), (int)(3)).concat("M"));
						}
						else {
							if (_number < 100000000) {
								_txt.setText(String.valueOf(_number / 1000000).substring((int)(0), (int)(2)).concat("M"));
							}
							else {
								if (_number < 1000000000) {
									_txt.setText(String.valueOf(_number / 1000000).substring((int)(0), (int)(3)).concat("M"));
								}
								else {
									if (_number < 10000000000d) {
										_txt.setText(String.valueOf(_number / 1000000000).substring((int)(0), (int)(3)).concat("B"));
									}
									else {
										if (_number < 100000000000d) {
											_txt.setText(String.valueOf(_number / 1000000000).substring((int)(0), (int)(2)).concat("B"));
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
	
	
	public void _ThemeCustom() {
		top.setBackgroundColor(0xFFFFFFFF);
		body.setBackgroundColor(0xFFFFFFFF);
		bottom.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFF000000);
		comments_count.setTextColor(0xFF000000);
		comment_count_title.setTextColor(0xFF000000);
		no_comments_title.setTextColor(0xFF000000);
		no_comments_subtitle.setTextColor(0xFF000000);
		comment.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFFF5F5F5));
	}
	
	public class Comments_viewAdapter extends RecyclerView.Adapter<Comments_viewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Comments_viewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.comments_list_custom_view, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final LinearLayout body = _view.findViewById(R.id.body);
			final de.hdodenhof.circleimageview.CircleImageView avatar = _view.findViewById(R.id.avatar);
			final LinearLayout middle = _view.findViewById(R.id.middle);
			final LinearLayout like_view = _view.findViewById(R.id.like_view);
			final LinearLayout uilyt = _view.findViewById(R.id.uilyt);
			final TextView comments = _view.findViewById(R.id.comments);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView verify = _view.findViewById(R.id.verify);
			final ImageView like = _view.findViewById(R.id.like);
			final TextView like_count = _view.findViewById(R.id.like_count);
			
			username.setTextColor(0xFF000000);
			like_count.setTextColor(0xFF607D8B);
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(UserProfile.get(_data.get((int)_position).get("uid").toString()).toString())).into(avatar);
			username.setText(UserName.get(_data.get((int)_position).get("uid").toString()).toString());
			comments.setText(_data.get((int)_position).get("comment").toString());
			if (UserVerified.get(_data.get((int)_position).get("uid").toString()).toString().equals("blue")) {
				verify.setImageResource(R.drawable.blue_verified);
				verify.setVisibility(View.VISIBLE);
			}
			else {
				if (UserVerified.get(_data.get((int)_position).get("uid").toString()).toString().equals("red")) {
					verify.setImageResource(R.drawable.red_verified);
					verify.setVisibility(View.VISIBLE);
				}
				else {
					if (UserVerified.get(_data.get((int)_position).get("uid").toString()).toString().equals("false")) {
						verify.setVisibility(View.GONE);
					}
				}
			}
			CommentsMap = comments_rmap.get((int)_position);
			SketchwareUtil.getAllKeysFromMap(CommentsMap, CommentsKey);
			n = 0;
			CommentsLike = 0;
			for(int _repeat54 = 0; _repeat54 < (int)(CommentsKey.size()); _repeat54++) {
				if (CommentsMap.get(CommentsKey.get((int)(n))).toString().equals("true")) {
					CommentsLike++;
				}
				n++;
			}
			/*Like Count Short System*/
			like_count.setText(String.valueOf((long)(CommentsLike)));
			if (CommentsLike < 1000) {
					like_count.setText(String.valueOf((long)(CommentsLike)));
			}
			else {
					if (CommentsLike < 10000) {
							like_count.setText(String.valueOf(CommentsLike / 1000).substring((int)(0), (int)(3)).concat("K"));
					}
					else {
							if (CommentsLike < 100000) {
									like_count.setText(String.valueOf(CommentsLike / 1000).substring((int)(0), (int)(4)).concat("K"));
							}
							else {
									if (CommentsLike < 1000000) {
											like_count.setText(String.valueOf(CommentsLike / 1000).substring((int)(0), (int)(3)).concat("K"));
									}
									else {
											if (CommentsLike < 10000000) {
													like_count.setText(String.valueOf(CommentsLike / 1000000).substring((int)(0), (int)(3)).concat("M"));
											}
											else {
													if (CommentsLike < 100000000) {
															like_count.setText(String.valueOf(CommentsLike / 1000000).substring((int)(0), (int)(2)).concat("M"));
													}
													else {
															if (CommentsLike < 1000000000) {
																	like_count.setText(String.valueOf(CommentsLike / 1000000).substring((int)(0), (int)(3)).concat("M"));
															}
															else {
																	if (CommentsLike < 10000000000d) {
																			like_count.setText(String.valueOf(CommentsLike / 1000000000).substring((int)(0), (int)(3)).concat("B"));
																	}
																	else {
																			if (CommentsLike < 100000000000d) {
																					like_count.setText(String.valueOf(CommentsLike / 1000000000).substring((int)(0), (int)(2)).concat("B"));
																			}
																	}
															}
													}
											}
									}
							}
					}
			}
			/*Like Count Short System End*/
			if (CommentsMap.containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
				if (CommentsMap.get(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString().equals("true")) {
					like.setImageResource(R.drawable.liked);
				}
				else {
					like.setImageResource(R.drawable.not_liked);
				}
			}
			else {
				like.setImageResource(R.drawable.not_liked);
			}
			like_view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (comments_rmap.get((int)_position).containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (comments_rmap.get((int)_position).get(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString().equals("true")) {
							lmap = new HashMap<>();
							lmap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "false");
							pcdb.child(CommentsChild.get((int)(_position))).updateChildren(lmap);
						}
						else {
							lmap = new HashMap<>();
							lmap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "true");
							pcdb.child(CommentsChild.get((int)(_position))).updateChildren(lmap);
						}
					}
					else {
						lmap = new HashMap<>();
						lmap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "true");
						pcdb.child(CommentsChild.get((int)(_position))).updateChildren(lmap);
					}
				}
			});
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