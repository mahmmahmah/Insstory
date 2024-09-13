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
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class BlockActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	private String MoreInfoTitle = "";
	private String MoreInfoMessage = "";
	private String YES = "";
	private String NO = "";
	
	private LinearLayout body;
	private LinearLayout medium;
	private LinearLayout bottom;
	private LinearLayout body_;
	private ImageView image;
	private TextView title;
	private TextView text;
	private TextView create_request_button;
	private TextView more_info_button;
	
	private SharedPreferences lang;
	private SharedPreferences save;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.block);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		medium = findViewById(R.id.medium);
		bottom = findViewById(R.id.bottom);
		body_ = findViewById(R.id.body_);
		image = findViewById(R.id.image);
		title = findViewById(R.id.title);
		text = findViewById(R.id.text);
		create_request_button = findViewById(R.id.create_request_button);
		more_info_button = findViewById(R.id.more_info_button);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		create_request_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Successful");
			}
		});
		
		more_info_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_NewCustomDialog(MoreInfoTitle, MoreInfoMessage, YES, NO, false);
			}
		});
	}
	
	private void initializeLogic() {
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
		_rippleRoundStroke(create_request_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
		_rippleRoundStroke(more_info_button, "#FFFFFF", "#CFD8DC", 6, 2, "#CFD8DC");
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		body.setBackgroundColor(0xFFFFFFFF);
		text.setTextColor(0xFF000000);
		_ImageColor(image, "#F44336");
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
		title.setText("Engellendin");
		text.setText("Sayın Kullanıcı, sizin hakkınızda bir çok rapor aldık! Biz ise bu raporları kontrol ettik ve topluluk kurallarımıza uymadığını doğruladık. Herkes için güvenilir bir ortam sunmaya çalışıyoruz!");
		create_request_button.setText("Talep Oluştur");
		more_info_button.setText("Daha Fazla Bilgi");
		MoreInfoTitle = "Daha Fazla Bilgi";
		MoreInfoMessage = "Uygulamamız bir sosyal medya uygulamasıdır! Haberleşmek,bilgilendirmek,sohbet özellikleri ve diğer özellikleri barındırır. Uygulamamızın her platformun olduğu gibi samimi ve güvenli bir ortam sunmak için Topluluk kuralları bulunuyor! bu kurallara bazı kişiler uymaz sürekli kurallara ters bakarlar ve yanlış hareket ederler bu kullanıcılara karşı çok sert önlemlerimiz var sürekli olarak bildirilen kullanıcıları kontrol ederiz daha sonra gerekenleri uygularız ancak engellenen kullanıcılar sürekli yeni hesaplar açarak yine kurallara uymaz ise 3 kez banlandıktan sonra cihazı engellenir ve yeni hesap dahi oluşturamaz! masum kullanıcıları taciz eden,cinselliğe sürükleyen,hatalı rapor gönderen,hile yapan,yalan bilgi paylaşan,dolandırıcılık ve taklit yapan kişilerin göz yaşına bakmayız ne kadar hesap açarlarsa açsınlar yine engelleyeceğiz ve bu uygulamadan uzak tutacağız. Kullanıcı memnuniyeti tamamen önceliğimizdir ve bu kuralları kullanıcılarımızı memnun edecek seviyede tutmak için sürekli güncelleyeceğiz.\n\nTopluluk kurallarımızın bazı maddeleri şu şekilde;\n\n- Uygunsuz profil resimleri, kullanıcı adları, sesler ve mesajlar kesinlikle yasak.\n- Yalan bilgi içeren gönderiler,yorumlar ve diğer paylaşımlar.\n- Kullanıcıları taciz etmek, cinselliğe teşvik etmek, özgürlüğü kasıtlamak, kişinin kararlarına itiraz etmek, tehdit etmek.\n- Masum kullanıcıları kuralları ihmal etmemesine rağmen sürekli olarak bildirmek.\n- İnsanlarla dalga geçmek, kötüleyen paylaşımda bulunmak, İzni olmadan başka bir insanın sesini, görüntüsünü veya onu içeren herhangi bir medyayı paylaşmak.\n- Irkçılık yapmak, Din'ler ve inançlar ile dalga geçmek veya kötülemek.\n\nYukarıda belirttiğimiz kurallara uymayan kişilerin göz yaşına bakılmaksızın kalıcı olarak engellenir ve yeni hesap açarak yine tekrarlarsa cihazı ile birlikte hesabı silinerek o kişi sonsuza dek kalıcı olarak yasaklanır.\n\nBir kaç küçük kural ihlali yapan kişiler ise aşağıdaki engelleri alır;\n\n- Her ihlal edildiğinde CP Puanın'dan 50 CP Puanı düşürülür.\n- Paylaşım yapması engellenir.\n- Sohbete medya gönderemez.\n- Markete erişimi engellenir.\n\nBu kurallar topluluk kurallarının ilkkez uyulmaması sonucu oluşur! Ancak tekrarlanırsa kalıcı engel sebebidir.\n\nDiğer bir konuda eğer ki sürekli olarak topluluk kurallarına uyuyorsa aşağıdaki maddeler gercekleşir;\n\n- Bir hafta boyunca kural ihlali bulunmuyorsa 25 CP Ödül verilir.\n- Kullanıcı istediğinde 2 günde bir 2 saat destek süresi\n- İyimser Kullanıcı Rozeti (Topluluk kuralı ihlali alırsa bu rozet geri alınır!).\n\nTopluluk kurallarımız her zaman masum kullanıcıları bu tarz kötü kişilerden korumak amaçlı onlara karşı sert kurallar eklemeye çalışıyoruz. Ancak her zaman biz yetişemeyebiliriz bu yüzden kötü kullanıcıları bize gerektiğince maddeleri ile birlikte ne yaptığını tam olarak açıklamayı unutmayın :).";
		YES = "Tamam";
		NO = "Hayır";
	}
	
	
	public void _EnLang() {
		title.setText("You are banned");
		text.setText("Dear User, we have received many reports about you! We've checked these reports and confirmed that they don't follow our community guidelines. We strive to provide a safe environment for everyone!");
		more_info_button.setText("More Info");
		create_request_button.setText("Create Request");
		MoreInfoTitle = "More Info";
		MoreInfoMessage = "Our app is a social media app! It contains communication, informing, chat features and other features. Our app has Community guidelines to provide a friendly and safe environment like any platform! Some people do not follow these rules, they constantly look at the rules and act wrong. We have very strict measures against these users, we constantly check the reported users, then we apply the necessary, but if the blocked users do not follow the rules by constantly opening new accounts, their device will be blocked after 3 times and the new account will be blocked. Can't even create! We will not look at the tears of people who harass innocent users, drag them into sex, send false reports, cheat, share false information, fraud and imitation, no matter how many accounts they open, we will block them and keep them away from this application. User satisfaction is our absolute priority and we will constantly update these rules to keep our users satisfied.\n\nSome of our community rules are as follows:\n\n- Inappropriate profile pictures, usernames, sounds and messages are strictly prohibited.\n- Posts, comments and other posts containing false information.\n- Harassing users, encouraging sexuality, intentional freedom, objecting to one's decisions, threatening.\n- To report innocent users constantly, even if they do not neglect the rules.\n- Making fun of people, posting disparaging, sharing another person's voice, image or any media containing them without their permission.\n- Making racism, making fun of religions and beliefs or vilifying them.\n\nPeople who do not comply with the rules mentioned above will be permanently blocked regardless of their tears and if they open a new account and repeat it again, their account will be deleted along with their device, and that person will be permanently banned.\n\nPeople who make a few minor rule violations get the following hurdles;\n\n- 50 CP Points are deducted from your CP Points each time it is violated.\n- Sharing is prohibited.\n- Can't send media to chat.\n- Access to the market is blocked.\n\nThese rules are the result of first time non-compliance with community rules! However, if it is repeated, it is a permanent obstacle.\n\nOn the other hand, if it always complies with the community rules, the following items will take place;\n\n- 25 CP Reward is given if there are no rules violations for a week.\n- 2 hours of support every 2 days when the user requests it\n- Optimistic User Badge (This badge will be withdrawn if the Community receives a rule violation!).\n\nOur community guidelines are always trying to add tough rules against innocent users to protect them from such bad guys. However, we may not always be able to catch up, so don't forget to explain exactly what the bad users are doing with the items as necessary :).";
		YES = "Okay";
		NO = "No";
	}
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
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
	
	
	public void _NewCustomDialog(final String _Title, final String _Message, final String _YesButtonText, final String _NoButtonText, final boolean _MultiButton) {
		final AlertDialog NewCustomDialog = new AlertDialog.Builder(BlockActivity.this).create();
		LayoutInflater NewCustomDialogLI = getLayoutInflater();
		View NewCustomDialogCV = (View) NewCustomDialogLI.inflate(R.layout.new_custom_dialog, null);
		NewCustomDialog.setView(NewCustomDialogCV);
		NewCustomDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		final TextView dialog_title = (TextView)
		NewCustomDialogCV.findViewById(R.id.dialog_title);
		final TextView dialog_message = (TextView)
		NewCustomDialogCV.findViewById(R.id.dialog_message);
		final TextView dialog_no_button = (TextView)
		NewCustomDialogCV.findViewById(R.id.dialog_no_button);
		final TextView dialog_yes_button = (TextView)
		NewCustomDialogCV.findViewById(R.id.dialog_yes_button);
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
				NewCustomDialog.dismiss();
				
			}
		});
		dialog_no_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		dialog_yes_button.setText(_YesButtonText);
		NewCustomDialog.setCancelable(true);
		NewCustomDialog.show();
	}
	
	
	public void _InLang() {
		title.setText("Kamu Telah DiBlokir");
		text.setText("Pengguna yang terhormat, kami telah menerima banyak laporan tentang Anda! Kami telah memeriksa laporan ini dan mengonfirmasi bahwa laporan tersebut tidak mengikuti pedoman komunitas kami. Kami berusaha untuk menyediakan lingkungan yang aman untuk semua orang!");
		more_info_button.setText("Info Lanjutan");
		create_request_button.setText("Ajukan Banding");
		MoreInfoTitle = "Info Lanjutan";
		MoreInfoMessage = "Aplikasi kami adalah aplikasi media sosial! Ini berisi komunikasi, informasi, fitur obrolan dan fitur lainnya. Aplikasi kami memiliki pedoman Komunitas untuk menyediakan lingkungan yang ramah dan aman seperti platform apa pun! Beberapa orang tidak mengikuti aturan ini, mereka terus-menerus melihat aturan dan bertindak salah. Kami memiliki tindakan yang sangat ketat terhadap pengguna ini, kami terus-menerus memeriksa pengguna yang dilaporkan, kemudian kami menerapkan yang diperlukan, tetapi jika pengguna yang diblokir tidak mengikuti aturan dengan terus-menerus membuka akun baru, perangkat mereka akan diblokir setelah 3 kali dan akun baru akan diblokir. Bahkan tidak bisa membuat! Kami tidak akan melihat air mata orang yang melecehkan pengguna yang tidak bersalah, menyeret mereka ke dalam hubungan seks, mengirim laporan palsu, menipu, membagikan informasi palsu, penipuan dan peniruan, tidak peduli berapa banyak akun yang mereka buka, kami akan memblokir mereka dan menjauhkan mereka dari aplikasi ini. Kepuasan pengguna adalah prioritas mutlak kami dan kami akan terus memperbarui aturan ini untuk membuat pengguna kami puas. Beberapa aturan komunitas kami adalah sebagai berikut: - Gambar profil, nama pengguna, suara, dan pesan yang tidak pantas sangat dilarang. - Postingan, komentar dan postingan lainnya yang mengandung informasi palsu. Melecehkan pengguna, mendorong seksualitas, kebebasan yang disengaja, menolak keputusan seseorang, mengancam. - Untuk melaporkan pengguna yang tidak bersalah terus-menerus, bahkan jika mereka tidak mengabaikan aturan. - Mengolok-olok orang, memposting penghinaan, membagikan suara, gambar, atau media orang lain yang memuatnya tanpa izin mereka. - Membuat rasisme, mengolok-olok agama dan kepercayaan atau menjelekkan mereka. Orang yang tidak mematuhi aturan yang disebutkan di atas akan diblokir secara permanen terlepas dari air mata mereka dan jika mereka membuka akun baru dan mengulanginya lagi, akun mereka akan dihapus bersama dengan perangkat mereka, dan orang itu akan diblokir secara permanen. Orang-orang yang melakukan beberapa pelanggaran aturan kecil mendapatkan rintangan berikut; - 50 Poin CP dikurangi dari Poin CP Anda setiap kali dilanggar. - Dilarang berbagi. - Tidak dapat mengirim media untuk mengobrol. - Akses ke pasar diblokir. Aturan ini adalah hasil dari ketidakpatuhan pertama kali terhadap aturan komunitas! Namun, jika diulangi, itu menjadi kendala permanen. Di sisi lain, jika selalu mematuhi aturan komunitas, hal-hal berikut akan terjadi; - 25 CP Reward diberikan jika tidak ada pelanggaran selama seminggu. - Dukungan 2 jam setiap 2 hari ketika pengguna memintanya - Lencana Pengguna Optimis (Lencana ini akan ditarik jika Komunitas menerima pelanggaran aturan!). Pedoman komunitas kami selalu berusaha menambahkan aturan keras terhadap pengguna yang tidak bersalah untuk melindungi mereka dari orang jahat tersebut. Namun, kami mungkin tidak selalu dapat mengejar ketinggalan, jadi jangan lupa untuk menjelaskan dengan tepat apa yang dilakukan pengguna jahat dengan item tersebut seperlunya :).";
		YES = "Oke";
		NO = "No";
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