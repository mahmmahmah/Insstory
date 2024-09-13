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
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class MessageRequestsTabFragmentActivity extends Fragment {
	
	private RecyclerView requests;
	private LinearLayout no_requests_layout;
	private TextView no_requests_title;
	private TextView no_requests_subtitle;
	
	private SharedPreferences save;
	private SharedPreferences lang;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.message_requests_tab_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		requests = _view.findViewById(R.id.requests);
		no_requests_layout = _view.findViewById(R.id.no_requests_layout);
		no_requests_title = _view.findViewById(R.id.no_requests_title);
		no_requests_subtitle = _view.findViewById(R.id.no_requests_subtitle);
		save = getContext().getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getContext().getSharedPreferences("lang", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		no_requests_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		requests.setVisibility(View.GONE);
		no_requests_layout.setVisibility(View.VISIBLE);
		requests.setLayoutManager(new LinearLayoutManager(getContext()));
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
		no_requests_title.setText("Henüz Mesaj İsteği Yok!");
		no_requests_subtitle.setText("Arkadaş Eklemediğiniz Kişilerden Gelen\nMesajlar Bu Listede Görünür!");
	}
	
	
	public void _EnLang() {
		no_requests_title.setText("No Message Requests Yet!");
		no_requests_subtitle.setText("Messages From People You Haven't Added\nFriends Appear On This List!");
	}
	
	
	public void _ThemeCustom() {
		no_requests_title.setTextColor(0xFF000000);
		no_requests_subtitle.setTextColor(0xFF000000);
	}
	
	
	public void _InLang() {
		no_requests_title.setText("Tidak Ada Permintaan Pesan! ");
		no_requests_subtitle.setText("Pesan Dari Orang yang Belum Anda Tambahkan Teman Muncul Di Daftar Ini!");
	}
	
	public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public RequestsAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.message_list, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout body = _view.findViewById(R.id.body);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final LinearLayout status_body = _view.findViewById(R.id.status_body);
			final LinearLayout status_icon = _view.findViewById(R.id.status_icon);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView verified = _view.findViewById(R.id.verified);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView time = _view.findViewById(R.id.time);
			final ImageView tick = _view.findViewById(R.id.tick);
			final TextView msg = _view.findViewById(R.id.msg);
			final LinearLayout linear_count = _view.findViewById(R.id.linear_count);
			final TextView count = _view.findViewById(R.id.count);
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