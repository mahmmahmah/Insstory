package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aghajari.emojiview.*;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class LoginBottomdialogFragmentDialogFragmentActivity extends BottomSheetDialogFragment {
	
	private LinearLayout main;
	private LinearLayout body;
	private LinearLayout top_layout;
	private ImageView vector;
	private TextView text;
	private TextView continue_button;
	private TextView title;
	private ImageView cancel;
	
	private Intent intent = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.login_bottomdialog_fragment_dialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		main = _view.findViewById(R.id.main);
		body = _view.findViewById(R.id.body);
		top_layout = _view.findViewById(R.id.top_layout);
		vector = _view.findViewById(R.id.vector);
		text = _view.findViewById(R.id.text);
		continue_button = _view.findViewById(R.id.continue_button);
		title = _view.findViewById(R.id.title);
		cancel = _view.findViewById(R.id.cancel);
		
		continue_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getContext().getApplicationContext(), ChooselogintypeActivity.class);
				startActivity(intent);
				dismiss();
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dismiss();
			}
		});
	}
	
	private void initializeLogic() {
		_rippleRoundStroke(body, "#FFFFFF", "#FFFFFF", 20, 0, "#FFFFFF");
		_rippleRoundStroke(continue_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
	}
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		
	}
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
}