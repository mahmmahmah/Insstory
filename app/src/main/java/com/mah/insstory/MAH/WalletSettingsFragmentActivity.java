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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aghajari.emojiview.*;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class WalletSettingsFragmentActivity extends Fragment {
	
	private LinearLayout body;
	private ScrollView scroll;
	private LinearLayout scroll_in_body;
	private LinearLayout top;
	private LinearLayout top_spc;
	private TextView title;
	
	private SharedPreferences lang;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.wallet_settings_fragment, _container, false);
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
		title = _view.findViewById(R.id.title);
		lang = getContext().getSharedPreferences("lang", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
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
	}
	
	
	public void _TrLang() {
		title.setText("Cüzdan Ayarları");
	}
	
	
	public void _EnLang() {
		title.setText("Wallet Settings");
	}
	
}