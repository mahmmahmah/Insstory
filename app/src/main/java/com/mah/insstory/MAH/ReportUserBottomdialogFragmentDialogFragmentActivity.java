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
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class ReportUserBottomdialogFragmentDialogFragmentActivity extends BottomSheetDialogFragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String ReportUid = "";
	private HashMap<String, Object> map = new HashMap<>();
	
	private LinearLayout body;
	private LinearLayout top_layout;
	private CardView profile_card;
	private TextView username;
	private TextView top_title;
	private RadioButton spam;
	private RadioButton inappropriate;
	private RadioButton sexuality;
	private RadioButton imitation;
	private EditText more_text;
	private TextView warning;
	private TextView report_button;
	private TextView title;
	private ImageView cancel;
	private ImageView avatar;
	
	private Intent intent = new Intent();
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private DatabaseReference rdb = _firebase.getReference("admin/reports");
	private ChildEventListener _rdb_child_listener;
	private Calendar cc = Calendar.getInstance();
	private SharedPreferences save;
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
	
	private SharedPreferences lang;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.report_user_bottomdialog_fragment_dialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		body = _view.findViewById(R.id.body);
		top_layout = _view.findViewById(R.id.top_layout);
		profile_card = _view.findViewById(R.id.profile_card);
		username = _view.findViewById(R.id.username);
		top_title = _view.findViewById(R.id.top_title);
		spam = _view.findViewById(R.id.spam);
		inappropriate = _view.findViewById(R.id.inappropriate);
		sexuality = _view.findViewById(R.id.sexuality);
		imitation = _view.findViewById(R.id.imitation);
		more_text = _view.findViewById(R.id.more_text);
		warning = _view.findViewById(R.id.warning);
		report_button = _view.findViewById(R.id.report_button);
		title = _view.findViewById(R.id.title);
		cancel = _view.findViewById(R.id.cancel);
		avatar = _view.findViewById(R.id.avatar);
		save = getContext().getSharedPreferences("save", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		lang = getContext().getSharedPreferences("lang", Activity.MODE_PRIVATE);
		
		spam.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				spam.setChecked(true);
				inappropriate.setChecked(false);
				sexuality.setChecked(false);
				imitation.setChecked(false);
			}
		});
		
		inappropriate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				spam.setChecked(false);
				inappropriate.setChecked(true);
				sexuality.setChecked(false);
				imitation.setChecked(false);
			}
		});
		
		sexuality.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				spam.setChecked(false);
				inappropriate.setChecked(false);
				sexuality.setChecked(true);
				imitation.setChecked(false);
			}
		});
		
		imitation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				spam.setChecked(false);
				inappropriate.setChecked(false);
				sexuality.setChecked(false);
				imitation.setChecked(true);
			}
		});
		
		report_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cc = Calendar.getInstance();
				map = new HashMap<>();
				map.put("reported_uid", ReportUid);
				if (spam.isChecked()) {
					map.put("reported_category", "spam");
				}
				else {
					if (inappropriate.isChecked()) {
						map.put("reported_category", "inappropriate");
					}
					else {
						if (sexuality.isChecked()) {
							map.put("reported_category", "sexuality");
						}
						else {
							if (imitation.isChecked()) {
								map.put("reported_category", "imitation");
							}
						}
					}
				}
				map.put("reported_more_text", more_text.getText().toString());
				map.put("reported_push_date", (int)(cc.getTimeInMillis()));
				map.put("report_uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				rdb.child(ReportUid.concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).updateChildren(map);
				map.clear();
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Reported! We Will Check Your Report.");
				dismiss();
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dismiss();
			}
		});
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(save.getString("LastReportUid", ""))) {
					Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(avatar);
					username.setText(_childValue.get("username").toString());
					ReportUid = _childValue.get("uid").toString();
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
		
		_rdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		rdb.addChildEventListener(_rdb_child_listener);
		
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
		title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		more_text.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)8, (int)2, 0xFF607D8B, Color.TRANSPARENT));
		report_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange_gradient_button));
		spam.setChecked(true);
	}
	
	@Override
	public void onStart() {
		super.onStart();
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
		title.setText("Kullanıcıyı Bildir");
		top_title.setText("Bu kullanıcının topluluk kurallarımızı ihlal ettiğini düşünüyorsanız bize bildirin!");
		spam.setText("Spam");
		inappropriate.setText("Uygunsuz Sohbet, Ses veya Görüntüler");
		sexuality.setText("Cinsellik/Taciz");
		imitation.setText("Beni veya başkasını taklit etme");
		more_text.setHint("Bu kullanıcıyı daha uzun tanımlayın ( İsteğe bağlı )");
		warning.setText("Uyarı: Masum kullanıcıları sürekli olarak bildirmek, uygulamadan yasaklanmanıza neden olur");
		report_button.setText("BİLDİR");
	}
	
	
	public void _EnLang() {
		title.setText("Report User");
		top_title.setText("Let us know if you think this user is violating our community guidelines!");
		spam.setText("Spam");
		inappropriate.setText("Inappropriate Chat, Sound or Images");
		sexuality.setText("Sexuality/Harassment");
		imitation.setText("Imitating me or someone else");
		more_text.setHint("Define this user longer (Optional)");
		warning.setText("Warning: Constantly reporting innocent users will get you banned from the app");
		report_button.setText("REPORT");
	}
	
	
	public void _InLang() {
		title.setText("Laporkan Pengguna");
		top_title.setText("Beri tahu kami jika menurut Anda pengguna ini melanggar pedoman komunitas kami!");
		spam.setText("Spam");
		inappropriate.setText("Obrolan, Suara, atau Gambar yang Tidak Pantas");
		sexuality.setText("Seksual/Pelecehan");
		imitation.setText("Meniru saya atau orang lain");
		more_text.setHint("Define this user longer (Optional)");
		warning.setText("Peringatan: Terus-menerus melaporkan pengguna yang tidak bersalah akan membuat Anda diblokir dari aplikasi");
		report_button.setText("LAPORKAN");
	}
	
}