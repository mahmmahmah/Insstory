package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aghajari.emojiview.*;
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
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;


public class AccountdeleteActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private String RadioString = "";
	private String key = "";
	private String DelAvatat = "";
	private String account_delete_reason = "";
	private String account_delete_deleting = "";
	private String account_delete_deleted = "";
	private String account_delete_error = "";
	private String delete_account_dialog_message = "";
	private String delete_account_dialog_ok_button = "";
	private String delete_account_dialog_no_button = "";
	private String suspend_account_dialog_message = "";
	private String suspend_account_dialog_ok_button = "";
	private String suspend_account_dialog_no_button = "";
	private HashMap<String, Object> map = new HashMap<>();
	
	private LinearLayout body;
	private CardView top_view;
	private LinearLayout main;
	private LinearLayout top;
	private TextView title;
	private LinearLayout up;
	private LinearLayout medium;
	private LinearLayout bottom;
	private LinearLayout body_;
	private ImageView warning_icon;
	private TextView warning_text;
	private TextView suspend_account_button;
	private TextView delete_account_button;
	
	private Intent intent = new Intent();
	private SharedPreferences lang;
	private SharedPreferences save;
	private TimerTask timer;
	private AlertDialog.Builder suspend;
	private AlertDialog.Builder delete;
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
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.accountdelete);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		top_view = findViewById(R.id.top_view);
		main = findViewById(R.id.main);
		top = findViewById(R.id.top);
		title = findViewById(R.id.title);
		up = findViewById(R.id.up);
		medium = findViewById(R.id.medium);
		bottom = findViewById(R.id.bottom);
		body_ = findViewById(R.id.body_);
		warning_icon = findViewById(R.id.warning_icon);
		warning_text = findViewById(R.id.warning_text);
		suspend_account_button = findViewById(R.id.suspend_account_button);
		delete_account_button = findViewById(R.id.delete_account_button);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		suspend = new AlertDialog.Builder(this);
		delete = new AlertDialog.Builder(this);
		auth = FirebaseAuth.getInstance();
		
		suspend_account_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_SuspendAccountDialog(suspend_account_dialog_ok_button, suspend_account_dialog_message, suspend_account_dialog_ok_button, suspend_account_dialog_no_button, true);
			}
		});
		
		delete_account_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_DeleteAccountDialog(delete_account_dialog_ok_button, delete_account_dialog_message, delete_account_dialog_ok_button, delete_account_dialog_no_button, true);
			}
		});
		
		_udb_child_listener = new ChildEventListener() {
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
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
		_rippleRoundStroke(suspend_account_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
		_rippleRoundStroke(delete_account_button, "#FFFFFF", "#CFD8DC", 6, 2, "#CFD8DC");
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
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
	
	
	public void _ThemeCustom() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w =AccountdeleteActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF673AB7);
		}
		top.setBackgroundColor(0xFF673AB7);
		body.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFFFFFFFF);
		warning_text.setTextColor(0xFF000000);
	}
	
	
	public void _TrLang() {
		title.setText("Hesabı Sil");
		warning_text.setText("Hesabınızı neden sildiğinizi bilmiyoruz ama size bir seçenek daha sunuyoruz! Dilerseniz hesabınızı askıya alabilirsiniz. Hesabınızı askıya aldığınızda gönderileriniz, konuştuğunuz kişiler ve daha fazlası silinmez, ancak hesabınızı aramalarda ve başka yerlerde göremezler.");
		suspend_account_button.setText("Hesabı Askıya Al");
		delete_account_button.setText("Hesabı Sil");
		account_delete_deleting = "Hesabınız Siliniyor...";
		account_delete_deleted = "Hesabın Silindi!";
		account_delete_error = "Bir Hata Oluştu!";
		delete_account_dialog_message = "Hesabını kalıcı olarak silmek istediğine emin misin? Bu işlem geri alınamaz!";
		delete_account_dialog_ok_button = "Hesabı Sil";
		delete_account_dialog_no_button = "İptal";
		suspend_account_dialog_message = "Hesabını askıya almak istediğine emin misin? Hesabınızı daha sonra yeniden aktif edebilirsiniz!";
		suspend_account_dialog_ok_button = "Hesabı Askıya Al";
		suspend_account_dialog_no_button = "İptal";
	}
	
	
	public void _EnLang() {
		title.setText("Delete Account");
		warning_text.setText("We don't know why you're deleting your account, but we're giving you one more option! You can suspend your account if you wish. When you suspend your account, your posts, people you've talked to, and more won't be deleted, but they won't be able to see your account in searches and elsewhere.");
		suspend_account_button.setText("Suspend Account");
		delete_account_button.setText("Delete Account");
		account_delete_deleting = "Your account is being deleted...";
		account_delete_deleted = "Your Account Has Been Deleted!";
		account_delete_error = "Something went wrong!";
		delete_account_dialog_message = "Are you sure you want to permanently delete your account? This action cannot be undone!";
		delete_account_dialog_ok_button = "Delete Account";
		delete_account_dialog_no_button = "Cancel";
		suspend_account_dialog_message = "Are you sure you want to suspend your account? You can reactivate your account later!";
		suspend_account_dialog_ok_button = "Suspend Account";
		suspend_account_dialog_no_button = "Cancel";
	}
	
	
	public void _SuspendAccountDialog(final String _Title, final String _Message, final String _YesButtonText, final String _NoButtonText, final boolean _MultiButton) {
		final AlertDialog SuspendAccountDialog = new AlertDialog.Builder(AccountdeleteActivity.this).create();
		LayoutInflater SuspendAccountDialogLI = getLayoutInflater();
		View SuspendAccountDialogCV = (View) SuspendAccountDialogLI.inflate(R.layout.new_custom_dialog, null);
		SuspendAccountDialog.setView(SuspendAccountDialogCV);
		SuspendAccountDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		final TextView dialog_title = (TextView)
		SuspendAccountDialogCV.findViewById(R.id.dialog_title);
		final TextView dialog_message = (TextView)
		SuspendAccountDialogCV.findViewById(R.id.dialog_message);
		final TextView dialog_no_button = (TextView)
		SuspendAccountDialogCV.findViewById(R.id.dialog_no_button);
		final TextView dialog_yes_button = (TextView)
		SuspendAccountDialogCV.findViewById(R.id.dialog_yes_button);
		_rippleRoundStroke(dialog_yes_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
		_rippleRoundStroke(dialog_no_button, "#FFFFFF", "#CFD8DC", 6, 2, "#CFD8DC");
		dialog_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
		dialog_title.setText(_Title);
		dialog_message.setText(_Message);
		if (_MultiButton) {
			dialog_no_button.setText(_NoButtonText);
			dialog_no_button.setVisibility(View.VISIBLE);
		}
		else {
			dialog_no_button.setVisibility(View.GONE);
		}
		dialog_yes_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SuspendAccountDialog.dismiss();
				map = new HashMap<>();
				map.put("account_status", "suspended");
				udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
				map.clear();
				
			}
		});
		dialog_no_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SuspendAccountDialog.dismiss();
				
			}
		});
		dialog_yes_button.setText(_YesButtonText);
		SuspendAccountDialog.setCancelable(true);
		SuspendAccountDialog.show();
	}
	
	
	public void _DeleteAccountDialog(final String _Title, final String _Message, final String _YesButtonText, final String _NoButtonText, final boolean _MultiButton) {
		final AlertDialog DeleteAccountDialog = new AlertDialog.Builder(AccountdeleteActivity.this).create();
		LayoutInflater DeleteAccountDialogLI = getLayoutInflater();
		View DeleteAccountDialogCV = (View) DeleteAccountDialogLI.inflate(R.layout.new_custom_dialog, null);
		DeleteAccountDialog.setView(DeleteAccountDialogCV);
		DeleteAccountDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		final TextView dialog_title = (TextView)
		DeleteAccountDialogCV.findViewById(R.id.dialog_title);
		final TextView dialog_message = (TextView)
		DeleteAccountDialogCV.findViewById(R.id.dialog_message);
		final TextView dialog_no_button = (TextView)
		DeleteAccountDialogCV.findViewById(R.id.dialog_no_button);
		final TextView dialog_yes_button = (TextView)
		DeleteAccountDialogCV.findViewById(R.id.dialog_yes_button);
		_rippleRoundStroke(dialog_yes_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
		_rippleRoundStroke(dialog_no_button, "#FFFFFF", "#CFD8DC", 6, 2, "#CFD8DC");
		dialog_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
		dialog_title.setText(_Title);
		dialog_message.setText(_Message);
		if (_MultiButton) {
			dialog_no_button.setText(_NoButtonText);
			dialog_no_button.setVisibility(View.VISIBLE);
		}
		else {
			dialog_no_button.setVisibility(View.GONE);
		}
		dialog_yes_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				DeleteAccountDialog.dismiss();
				map = new HashMap<>();
				map.put("account_status", "deleted");
				udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
				map.clear();
				
			}
		});
		dialog_no_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				DeleteAccountDialog.dismiss();
				
			}
		});
		dialog_yes_button.setText(_YesButtonText);
		DeleteAccountDialog.setCancelable(true);
		DeleteAccountDialog.show();
	}
	
	
	public void _InLang() {
		title.setText("Hapus Akun");
		warning_text.setText("Kami tidak tahu mengapa Anda menghapus akun Anda, tetapi kami memberi Anda satu opsi lagi! Anda dapat menangguhkan akun Anda jika Anda mau. Saat Anda menangguhkan akun, postingan Anda, orang yang Anda ajak bicara, dan lainnya tidak akan dihapus, tetapi mereka tidak akan dapat melihat akun Anda di penelusuran dan di tempat lain.");
		suspend_account_button.setText("Menangguhkan Akun");
		delete_account_button.setText("Hapus Akun");
		account_delete_deleting = "Akun Anda Sedang Dihapus...";
		account_delete_deleted = "Akun Anda Telah Dihapus!";
		account_delete_error = "Something went wrong!";
		delete_account_dialog_message = "Apakah Anda yakin ingin menghapus akun Anda secara permanen? Tindakan ini tidak bisa dibatalkan!";
		delete_account_dialog_ok_button = "Hapus Account";
		delete_account_dialog_no_button = "Kembali";
		suspend_account_dialog_message = "Apakah Anda yakin ingin menangguhkan akun Anda? Anda dapat mengaktifkan kembali akun Anda nanti!";
		suspend_account_dialog_ok_button = "Tangguhkan Akun";
		suspend_account_dialog_no_button = "Kembali";
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