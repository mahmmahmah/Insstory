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
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
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
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class ManageUserBottomdialogFragmentDialogFragmentActivity extends BottomSheetDialogFragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	
	private LinearLayout body;
	private LinearLayout top_layout;
	private ScrollView scroll;
	private TextView save_button;
	private TextView title;
	private ImageView cancel;
	private LinearLayout scroll_in_body;
	private CircleImageView profile_image;
	private EditText name;
	private EditText username;
	private EditText biography;
	private LinearLayout red_verify;
	private LinearLayout blue_verify;
	private LinearLayout disable_verify;
	private LinearLayout ban_user;
	private LinearLayout premium;
	private LinearLayout reports;
	private LinearLayout live_support;
	private LinearLayout red_verify_check_layout;
	private TextView red_verify_subtext;
	private TextView red_verify_title;
	private CheckBox red_verify_check;
	private LinearLayout blue_verify_check_layout;
	private TextView blue_verify_subtext;
	private TextView blue_verify_title;
	private CheckBox blue_verify_check;
	private LinearLayout disable_verify_check_layout;
	private TextView disable_verify_subtext;
	private TextView disable_verify_title;
	private CheckBox disable_verify_check;
	private LinearLayout ban_user_check_layout;
	private TextView ban_user_subtext;
	private TextView ban_user_title;
	private CheckBox ban_user_check;
	private LinearLayout premium_check_layout;
	private TextView premium_subtext;
	private TextView premium_title;
	private CheckBox premium_check;
	private LinearLayout reports_check_layout;
	private TextView reports_subtext;
	private TextView reports_title;
	private ImageView reports_icon;
	private LinearLayout live_support_check_layout;
	private TextView live_support_subtext;
	private TextView live_support_title;
	private ImageView live_support_icon;
	
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
	
	private SharedPreferences save;
	private SharedPreferences lang;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.manage_user_bottomdialog_fragment_dialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		body = _view.findViewById(R.id.body);
		top_layout = _view.findViewById(R.id.top_layout);
		scroll = _view.findViewById(R.id.scroll);
		save_button = _view.findViewById(R.id.save_button);
		title = _view.findViewById(R.id.title);
		cancel = _view.findViewById(R.id.cancel);
		scroll_in_body = _view.findViewById(R.id.scroll_in_body);
		profile_image = _view.findViewById(R.id.profile_image);
		name = _view.findViewById(R.id.name);
		username = _view.findViewById(R.id.username);
		biography = _view.findViewById(R.id.biography);
		red_verify = _view.findViewById(R.id.red_verify);
		blue_verify = _view.findViewById(R.id.blue_verify);
		disable_verify = _view.findViewById(R.id.disable_verify);
		ban_user = _view.findViewById(R.id.ban_user);
		premium = _view.findViewById(R.id.premium);
		reports = _view.findViewById(R.id.reports);
		live_support = _view.findViewById(R.id.live_support);
		red_verify_check_layout = _view.findViewById(R.id.red_verify_check_layout);
		red_verify_subtext = _view.findViewById(R.id.red_verify_subtext);
		red_verify_title = _view.findViewById(R.id.red_verify_title);
		red_verify_check = _view.findViewById(R.id.red_verify_check);
		blue_verify_check_layout = _view.findViewById(R.id.blue_verify_check_layout);
		blue_verify_subtext = _view.findViewById(R.id.blue_verify_subtext);
		blue_verify_title = _view.findViewById(R.id.blue_verify_title);
		blue_verify_check = _view.findViewById(R.id.blue_verify_check);
		disable_verify_check_layout = _view.findViewById(R.id.disable_verify_check_layout);
		disable_verify_subtext = _view.findViewById(R.id.disable_verify_subtext);
		disable_verify_title = _view.findViewById(R.id.disable_verify_title);
		disable_verify_check = _view.findViewById(R.id.disable_verify_check);
		ban_user_check_layout = _view.findViewById(R.id.ban_user_check_layout);
		ban_user_subtext = _view.findViewById(R.id.ban_user_subtext);
		ban_user_title = _view.findViewById(R.id.ban_user_title);
		ban_user_check = _view.findViewById(R.id.ban_user_check);
		premium_check_layout = _view.findViewById(R.id.premium_check_layout);
		premium_subtext = _view.findViewById(R.id.premium_subtext);
		premium_title = _view.findViewById(R.id.premium_title);
		premium_check = _view.findViewById(R.id.premium_check);
		reports_check_layout = _view.findViewById(R.id.reports_check_layout);
		reports_subtext = _view.findViewById(R.id.reports_subtext);
		reports_title = _view.findViewById(R.id.reports_title);
		reports_icon = _view.findViewById(R.id.reports_icon);
		live_support_check_layout = _view.findViewById(R.id.live_support_check_layout);
		live_support_subtext = _view.findViewById(R.id.live_support_subtext);
		live_support_title = _view.findViewById(R.id.live_support_title);
		live_support_icon = _view.findViewById(R.id.live_support_icon);
		auth = FirebaseAuth.getInstance();
		save = getContext().getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getContext().getSharedPreferences("lang", Activity.MODE_PRIVATE);
		
		save_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (name.getText().toString().equals("") || (username.getText().toString().equals("") || biography.getText().toString().equals(""))) {
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "Fill All Items");
				}
				else {
					map = new HashMap<>();
					if (disable_verify_check.isChecked()) {
						map.put("verify", "false");
					}
					else {
						if (blue_verify_check.isChecked()) {
							map.put("verify", "blue");
						}
						else {
							if (red_verify_check.isChecked()) {
								map.put("verify", "red");
							}
						}
					}
					if (ban_user_check.isChecked()) {
						map.put("blocked", "true");
					}
					else {
						map.put("blocked", "false");
					}
					if (premium_check.isChecked()) {
						map.put("account_mode", "premium");
					}
					else {
						map.put("account_mode", "free");
					}
					map.put("username", username.getText().toString());
					map.put("yourname", name.getText().toString());
					map.put("bio", biography.getText().toString());
					udb.child(save.getString("LastUid", "")).updateChildren(map);
					map.clear();
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "Changes Saved!");
					dismiss();
				}
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dismiss();
			}
		});
		
		red_verify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				red_verify_check.performClick();
			}
		});
		
		blue_verify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				blue_verify_check.performClick();
			}
		});
		
		disable_verify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				disable_verify_check.performClick();
			}
		});
		
		ban_user.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ban_user_check.performClick();
			}
		});
		
		premium.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				premium_check.performClick();
			}
		});
		
		red_verify_check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				red_verify_check.setChecked(true);
				blue_verify_check.setChecked(false);
				disable_verify_check.setChecked(false);
			}
		});
		
		blue_verify_check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				red_verify_check.setChecked(false);
				blue_verify_check.setChecked(true);
				disable_verify_check.setChecked(false);
			}
		});
		
		disable_verify_check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				red_verify_check.setChecked(false);
				blue_verify_check.setChecked(false);
				disable_verify_check.setChecked(true);
			}
		});
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(save.getString("LastUid", ""))) {
					Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(profile_image);
					name.setText(_childValue.get("yourname").toString());
					username.setText(_childValue.get("username").toString());
					biography.setText(_childValue.get("bio").toString());
					if (_childValue.get("verify").toString().equals("false")) {
						red_verify_check.setChecked(false);
						blue_verify_check.setChecked(false);
						disable_verify_check.setChecked(true);
					}
					else {
						if (_childValue.get("verify").toString().equals("blue")) {
							red_verify_check.setChecked(false);
							blue_verify_check.setChecked(true);
							disable_verify_check.setChecked(false);
						}
						else {
							if (_childValue.get("verify").toString().equals("red")) {
								red_verify_check.setChecked(true);
								blue_verify_check.setChecked(false);
								disable_verify_check.setChecked(false);
							}
						}
					}
					if (_childValue.get("blocked").toString().equals("true")) {
						ban_user_check.setChecked(true);
					}
					else {
						ban_user_check.setChecked(false);
					}
					if (_childValue.get("account_mode").toString().equals("premium")) {
						premium_check.setChecked(true);
					}
					else {
						premium_check.setChecked(false);
					}
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
		title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		save_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange_gradient_button));
		profile_image.setElevation((float)20);
		name.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		name.setElevation((float)100);
		username.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		username.setElevation((float)100);
		biography.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		biography.setElevation((float)100);
		_rippleRoundStroke(red_verify, "#ECEFF1", "#CFD8DC", 20, 0, "#FFFFFF");
		_rippleRoundStroke(blue_verify, "#ECEFF1", "#CFD8DC", 20, 0, "#FFFFFF");
		_rippleRoundStroke(disable_verify, "#ECEFF1", "#CFD8DC", 20, 0, "#FFFFFF");
		_rippleRoundStroke(ban_user, "#ECEFF1", "#CFD8DC", 20, 0, "#FFFFFF");
		_rippleRoundStroke(premium, "#ECEFF1", "#CFD8DC", 20, 0, "#FFFFFF");
		_rippleRoundStroke(reports, "#ECEFF1", "#CFD8DC", 20, 0, "#FFFFFF");
		_rippleRoundStroke(live_support, "#ECEFF1", "#CFD8DC", 20, 0, "#FFFFFF");
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
		save_button.setText("KAYDET");
		title.setText("Kullanıcıyı Yönet");
		name.setHint("İsim");
		username.setHint("Kullanıcı Adı");
		biography.setHint("Biyografi");
		red_verify_title.setText("Kırmızı Onay Rozeti");
		red_verify_subtext.setText("Bunu etkinleştirmek mavi onay rozetini devre dışı bırakır! Kırmızı onay işaretli kişiler birkaç ekstra özelliğe erişebilir");
		blue_verify_title.setText("Mavi Onay Rozeti");
		blue_verify_subtext.setText("Bunu etkinleştirmek kırmızı onay rozetini devre dışı bırakır! Mavi onay işareti yalnızca hesabın doğrulandığını gösterir, bu nedenle herhangi bir ekstra özelliği etkinleştirmez");
		disable_verify_title.setText("Aktif Rozetleri Devre Dışı Bırak");
		disable_verify_subtext.setText("Bunu Etkinleştirmek Tüm Rozetleri Devre Dışı Bırakır");
		ban_user_title.setText("Kullanıcıyı Yasakla");
		ban_user_subtext.setText("Bu kullanıcı Topluluk kurallarını ihlal ediyorsa, bunu etkinleştirmek bu kullanıcıyı sonsuza kadar engeller");
		premium_title.setText("Premium Üyelik");
		premium_subtext.setText("Bu kullanıcı için premium özellikleri etkinleştirebilir veya devre dışı bırakabilirsiniz ancak premium paketi satın alarak aktifleştiren kişilerin premium paketini devre dışı bırakamazsınız");
		reports_title.setText("Kullanıcı Raporları");
		reports_subtext.setText("Bu kullanıcıyı bildiren kişilerden gelen şikayet mesajlarını görüntüleyin ve inceleyin");
		live_support_title.setText("Canlı Destek");
		live_support_subtext.setText("Bu kullanıcı için canlı desteğe gidin");
	}
	
	
	public void _EnLang() {
		save_button.setText("SAVE");
		title.setText("Manage User");
		name.setHint("Name");
		username.setHint("Username");
		biography.setHint("Biography");
		red_verify_title.setText("Red Verified Badge");
		red_verify_subtext.setText("Enabling this disables the blue check badge! People with red checkmarks can access a few extra features");
		blue_verify_title.setText("Blue Verified Badge");
		blue_verify_subtext.setText("Enabling this disables the red confirmation badge! The blue checkmark only indicates the account has been verified, so it won't enable any extra features");
		disable_verify_title.setText("Disable All Badges");
		disable_verify_subtext.setText("Enabling This Disables All Badges");
		ban_user_title.setText("Ban User");
		ban_user_subtext.setText("If this user violates Community guidelines, enabling it will block this user forever");
		premium_title.setText("Premium");
		premium_subtext.setText("You can enable or disable the premium features for this user, but you cannot disable the premium package for those who purchased the premium package and activated it");
		reports_title.setText("User Reports");
		reports_subtext.setText("View and review complaint messages from people who reported this user");
		live_support_title.setText("Live Support");
		live_support_subtext.setText("Go to live support for this user");
	}
	
	
	public void _InLang() {
		save_button.setText("SIMPAN");
		title.setText("Kelola Pengguna");
		name.setHint("Nama");
		username.setHint("Nama Pengguna");
		biography.setHint("Biografi");
		red_verify_title.setText("Lencana Verifikasi Merah");
		red_verify_subtext.setText("Mengaktifkan ini menonaktifkan lencana centang biru! Orang dengan tanda centang merah dapat mengakses beberapa fitur tambahan");
		blue_verify_title.setText("Lencana Verifikasi Biru");
		blue_verify_subtext.setText("Mengaktifkan ini menonaktifkan lencana konfirmasi merah! Tanda centang biru hanya menunjukkan bahwa akun telah diverifikasi, sehingga tidak akan mengaktifkan fitur tambahan apa pun");
		disable_verify_title.setText("Nonaktifkan Semua Lencana");
		disable_verify_subtext.setText("Mengaktifkan Ini Menonaktifkan Semua Lencana ");
		ban_user_title.setText("Blokir Pengguna");
		ban_user_subtext.setText("Jika pengguna ini melanggar pedoman komunitas, mengaktifkannya akan memblokir pengguna ini selamanya");
		premium_title.setText("Premium");
		premium_subtext.setText("Anda dapat mengaktifkan atau menonaktifkan fitur premium untuk pengguna ini, tetapi Anda tidak dapat menonaktifkan paket premium untuk mereka yang membeli paket premium dan mengaktifkannya");
		reports_title.setText("Laporan Pengguna");
		reports_subtext.setText("Lihat dan tinjau pesan keluhan dari orang yang melaporkan pengguna ini");
		live_support_title.setText("Dukungan Langsung");
		live_support_subtext.setText("Buka dukungan langsung untuk pengguna ini");
	}
	
}