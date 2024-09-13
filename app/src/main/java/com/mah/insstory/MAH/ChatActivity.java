package com.mah.insstory.MAH;

import android.Manifest;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.media.MediaPlayer;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aghajari.emojiview.*;
import com.aghajari.emojiview.emoji.iosprovider.*;
import com.aghajari.emojiview.view.*;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.File;
import java.io.InputStream;
import java.text.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.airbnb.lottie.LottieAnimationView;;

public class ChatActivity extends AppCompatActivity {
	
	public final int REQ_CD_IMG = 101;
	public final int REQ_CD_MUSIK = 102;
	public final int REQ_CD_CAM_ERA = 103;
	public final int REQ_CD_HR = 104;
	public final int REQ_CD_PD = 105;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private String key = "";
	private String chatroom = "";
	private String chatcopy = "";
	private String mkey = "";
	private String first = "";
	private double more_tools = 0;
	private String typingview = "";
	private String typingcopy = "";
	private String EditKey = "";
	private String ReplyedKey = "";
	private boolean ReplyedBoolean = false;
	private boolean ChildAdded = false;
	private double limit = 0;
	private double AudioTime = 0;
	private boolean AudioRecording = false;
	private String AudioPath = "";
	private String audioUrl = "";
	private double position_ = 0;
	private String audioTime = "";
	private String AudioName = "";
	private String AudioPath2 = "";
	private double tm_difference = 0;
	private String SetTimeAsecond = "";
	private String SetTimeSecond = "";
	private String SetTimeAMinute = "";
	private String SetTimeMinute = "";
	private String SetTimeHours = "";
	private String SetTimeDays = "";
	private String OnlineStatus = "";
	private String OfflineStatus = "";
	private String TypingStatus = "";
	private String AudioErrorL = "";
	private String AudioMinute = "";
	private String AudioSecond = "";
	private String videocall = "";
	private String messagecopyed = "";
	private String block_s = "";
	private String report_s = "";
	private String emptymessage = "";
	private String edited = "";
	private String uploading_audio = "";
	private String uploading_image = "";
	private String encTs = "";
	private HashMap<String, Object> UserProfile = new HashMap<>();
	private double ChatSize = 0;
	private String ReplyedMessage = "";
	private String ReplyedMessageUid = "";
	private String SecondName = "";
	private String FirstName = "";
	private HashMap<String, Object> UserName = new HashMap<>();
	private String RecordingString = "";
	private String YouReplyTo = "";
	private String YES = "";
	private String NO = "";
	private String DeleteMessageDialogTitle = "";
	private String DeleteMessageDialogMessage = "";
	private String MessagePopupEdit = "";
	private String MessagePopupReply = "";
	private String MessagePopupCopy = "";
	private String MessagePopupDelete = "";
	private String ddd = "";
	private String inboxx = "";
	private String count = "";
	private double pushAadT = 0;
	private boolean isPlaying = false;
	private double menit = 0;
	private double detik = 0;
	private double Audio = 0;
	private String push = "";
	private String rekam = "";
	private String waktu = "";
	private String DocName = "";
	private String DocPath = "";
	private String FilePath = "";
	private double Sizefile = 0;
	private double B = 0;
	private double KB = 0;
	private double MB = 0;
	private double GB = 0;
	private double TB = 0;
	private double PB = 0;
	private String returnedSize = "";
	private String format = "";
	private String ImagePath = "";
	private String ImageName = "";
	private boolean SendimgD = false;
	private boolean SendImgD = false;
	private HashMap<String, Object> maoblok = new HashMap<>();
	private boolean isShow = false;
	private String path_music = "";
	private String Name_mudic = "";
	private String cam_path = "";
	private String cam_name = "";
	private String path_vid = "";
	private String name_path = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	
	private LinearLayout body;
	private LinearLayout linear5;
	private LinearLayout top;
	private LinearLayout medium;
	private ImageView back;
	private LinearLayout profile;
	private LinearLayout linear3;
	private ImageView more_button;
	private CircleImageView avatar;
	private LinearLayout linear1_;
	private LinearLayout linear2;
	private TextView status;
	private TextView username;
	private ImageView verified;
	private ProgressBar loader;
	private TextView textview1;
	private ListView messages;
	private LinearLayout typing_layout;
	private LinearLayout bottom;
	private TextView typing_txt;
	private LinearLayout warning_layout;
	private LinearLayout message_body;
	private TextView warning;
	private LinearLayout message_body_in_reply;
	private LinearLayout category;
	private LinearLayout sound_record_body;
	private LinearLayout message_body_in_message;
	private ImageView message_body_in_reply_cancel;
	private LinearLayout message_body_in_reply_chld;
	private TextView message_body_in_reply_title;
	private TextView message_body_in_reply_text;
	private LinearLayout linear13;
	private LinearLayout linear8;
	private LinearLayout linear7;
	private LinearLayout linear10;
	private ImageView imageview5;
	private LinearLayout linear9;
	private LinearLayout music;
	private ImageView imageview2;
	private ImageView music_icon;
	private LinearLayout file;
	private LinearLayout image;
	private ImageView file_icon;
	private ImageView image_icon;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private ImageView imageview4;
	private ImageView imageview3;
	private ImageView sound_record_cancel;
	private TextView recording_text;
	private ImageView send_audio;
	private ImageView media;
	private ImageView imageview1;
	private LinearLayout msg_spc;
	private EditText message;
	private ImageView sound;
	private ImageView send;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear6;
	private AXEmojiPopupLayout linear1;
	
	private DatabaseReference chat1 = _firebase.getReference("+chatroom+");
	private ChildEventListener _chat1_child_listener;
	private DatabaseReference chat2 = _firebase.getReference("+chatcopy+");
	private ChildEventListener _chat2_child_listener;
	private Calendar cc = Calendar.getInstance();
	private Intent i = new Intent();
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
	
	private DatabaseReference ms = _firebase.getReference("inbox");
	private ChildEventListener _ms_child_listener;
	private SharedPreferences lang;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private SharedPreferences save;
	private MediaPlayer audio;
	private TimerTask rc_time;
	private Vibrator vibrate;
	private SpeechRecognizer speak;
	private TimerTask audiotimer;
	private Intent intent = new Intent();
	private ObjectAnimator o = new ObjectAnimator();
	private TimerTask poPup;
	private Calendar ct = Calendar.getInstance();
	private Calendar time2 = Calendar.getInstance();
	private DatabaseReference notification = _firebase.getReference("notification");
	private ChildEventListener _notification_child_listener;
	private DatabaseReference typing1 = _firebase.getReference("+typing+");
	private ChildEventListener _typing1_child_listener;
	private DatabaseReference typing2 = _firebase.getReference("+typingcopy+");
	private ChildEventListener _typing2_child_listener;
	private StorageReference sdb = _firebase_storage.getReference("chat/sound");
	private OnCompleteListener<Uri> _sdb_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _sdb_download_success_listener;
	private OnSuccessListener _sdb_delete_success_listener;
	private OnProgressListener _sdb_upload_progress_listener;
	private OnProgressListener _sdb_download_progress_listener;
	private OnFailureListener _sdb_failure_listener;
	
	private ProgressDialog progd;
	private StorageReference idb = _firebase_storage.getReference("chat/images");
	private OnCompleteListener<Uri> _idb_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _idb_download_success_listener;
	private OnSuccessListener _idb_delete_success_listener;
	private OnProgressListener _idb_upload_progress_listener;
	private OnProgressListener _idb_download_progress_listener;
	private OnFailureListener _idb_failure_listener;
	
	private RequestNetwork rq;
	private RequestNetwork.RequestListener _rq_request_listener;
	private Intent img = new Intent(Intent.ACTION_GET_CONTENT);
	private DatabaseReference inbox = _firebase.getReference("+inbox+");
	private ChildEventListener _inbox_child_listener;
	private Intent musik = new Intent(Intent.ACTION_GET_CONTENT);
	private AlertDialog.Builder dialog;
	private DatabaseReference t = _firebase.getReference("t");
	private ChildEventListener _t_child_listener;
	private float emoji;
	private Intent Cam_era = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_Cam_era;
	private StorageReference cam_s = _firebase_storage.getReference("cam_s");
	private OnCompleteListener<Uri> _cam_s_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _cam_s_download_success_listener;
	private OnSuccessListener _cam_s_delete_success_listener;
	private OnProgressListener _cam_s_upload_progress_listener;
	private OnProgressListener _cam_s_download_progress_listener;
	private OnFailureListener _cam_s_failure_listener;
	
	private Intent hr = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference s_vid_o = _firebase_storage.getReference("s_vid_o");
	private OnCompleteListener<Uri> _s_vid_o_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _s_vid_o_download_success_listener;
	private OnSuccessListener _s_vid_o_delete_success_listener;
	private OnProgressListener _s_vid_o_upload_progress_listener;
	private OnProgressListener _s_vid_o_download_progress_listener;
	private OnFailureListener _s_vid_o_failure_listener;
	
	private StorageReference musc_audio = _firebase_storage.getReference("musc_audio");
	private OnCompleteListener<Uri> _musc_audio_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _musc_audio_download_success_listener;
	private OnSuccessListener _musc_audio_delete_success_listener;
	private OnProgressListener _musc_audio_upload_progress_listener;
	private OnProgressListener _musc_audio_download_progress_listener;
	private OnFailureListener _musc_audio_failure_listener;
	
	private Intent pd = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference upfile = _firebase_storage.getReference("upfile");
	private OnCompleteListener<Uri> _upfile_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _upfile_download_success_listener;
	private OnSuccessListener _upfile_delete_success_listener;
	private OnProgressListener _upfile_upload_progress_listener;
	private OnProgressListener _upfile_download_progress_listener;
	private OnFailureListener _upfile_failure_listener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.chat);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		linear5 = findViewById(R.id.linear5);
		top = findViewById(R.id.top);
		medium = findViewById(R.id.medium);
		back = findViewById(R.id.back);
		profile = findViewById(R.id.profile);
		linear3 = findViewById(R.id.linear3);
		more_button = findViewById(R.id.more_button);
		avatar = findViewById(R.id.avatar);
		linear1_ = findViewById(R.id.linear1_);
		linear2 = findViewById(R.id.linear2);
		status = findViewById(R.id.status);
		username = findViewById(R.id.username);
		verified = findViewById(R.id.verified);
		loader = findViewById(R.id.loader);
		textview1 = findViewById(R.id.textview1);
		messages = findViewById(R.id.messages);
		typing_layout = findViewById(R.id.typing_layout);
		bottom = findViewById(R.id.bottom);
		typing_txt = findViewById(R.id.typing_txt);
		warning_layout = findViewById(R.id.warning_layout);
		message_body = findViewById(R.id.message_body);
		warning = findViewById(R.id.warning);
		message_body_in_reply = findViewById(R.id.message_body_in_reply);
		category = findViewById(R.id.category);
		sound_record_body = findViewById(R.id.sound_record_body);
		message_body_in_message = findViewById(R.id.message_body_in_message);
		message_body_in_reply_cancel = findViewById(R.id.message_body_in_reply_cancel);
		message_body_in_reply_chld = findViewById(R.id.message_body_in_reply_chld);
		message_body_in_reply_title = findViewById(R.id.message_body_in_reply_title);
		message_body_in_reply_text = findViewById(R.id.message_body_in_reply_text);
		linear13 = findViewById(R.id.linear13);
		linear8 = findViewById(R.id.linear8);
		linear7 = findViewById(R.id.linear7);
		linear10 = findViewById(R.id.linear10);
		imageview5 = findViewById(R.id.imageview5);
		linear9 = findViewById(R.id.linear9);
		music = findViewById(R.id.music);
		imageview2 = findViewById(R.id.imageview2);
		music_icon = findViewById(R.id.music_icon);
		file = findViewById(R.id.file);
		image = findViewById(R.id.image);
		file_icon = findViewById(R.id.file_icon);
		image_icon = findViewById(R.id.image_icon);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		imageview4 = findViewById(R.id.imageview4);
		imageview3 = findViewById(R.id.imageview3);
		sound_record_cancel = findViewById(R.id.sound_record_cancel);
		recording_text = findViewById(R.id.recording_text);
		send_audio = findViewById(R.id.send_audio);
		media = findViewById(R.id.media);
		imageview1 = findViewById(R.id.imageview1);
		msg_spc = findViewById(R.id.msg_spc);
		message = findViewById(R.id.message);
		sound = findViewById(R.id.sound);
		send = findViewById(R.id.send);
		hscroll1 = findViewById(R.id.hscroll1);
		linear6 = findViewById(R.id.linear6);
		linear1 = findViewById(R.id.linear1);
		auth = FirebaseAuth.getInstance();
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		speak = SpeechRecognizer.createSpeechRecognizer(this);
		rq = new RequestNetwork(this);
		img.setType("image/*");
		img.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		musik.setType("audio/*");
		musik.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		dialog = new AlertDialog.Builder(this);
		_file_Cam_era = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_Cam_era;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_Cam_era = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_Cam_era);
		} else {
			_uri_Cam_era = Uri.fromFile(_file_Cam_era);
		}
		Cam_era.putExtra(MediaStore.EXTRA_OUTPUT, _uri_Cam_era);
		Cam_era.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		hr.setType("video/*");
		hr.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		pd.setType("*/*");
		pd.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				map = new HashMap<>();
				map.put("typing", "false");
				typing2.child("typing_status").updateChildren(map);
				map.clear();
				chat1.removeEventListener(_chat1_child_listener);
				chat2.removeEventListener(_chat2_child_listener);
				finish();
			}
		});
		
		profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				message.setText("");
				chat1.removeEventListener(_chat1_child_listener);
				chat2.removeEventListener(_chat2_child_listener);
				i.setClass(getApplicationContext(), ProfileActivity.class);
				i.putExtra("uid", getIntent().getStringExtra("seconduser"));
				startActivity(i);
			}
		});
		
		more_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final com.google.android.material.bottomsheet.BottomSheetDialog ChatMenu = new com.google.android.material.bottomsheet.BottomSheetDialog(ChatActivity.this);
				View ChatMenuV = getLayoutInflater().inflate(R.layout.chat_more_button_custom, null);
				ChatMenu.setContentView(ChatMenuV);
				
				final LinearLayout linear  = (LinearLayout)ChatMenuV.findViewById(R.id.body);
				
				ChatMenu.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				
				ChatMenu.show();
				android.graphics.drawable.GradientDrawable CM = new android.graphics.drawable.GradientDrawable();
				int dc = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				CM.setColor(0xFFFFFFFF);
				CM.setCornerRadii(new float[]{
						dc*15,dc*15,dc*15 ,dc*15,dc*0,dc*0 ,dc*0,dc*0});
				linear.setElevation(dc*5);
				android.graphics.drawable.RippleDrawable ChatMenuDG = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), CM, null);
				linear.setBackground(ChatMenuDG);
				LinearLayout slider = (LinearLayout) ChatMenuV.findViewById(R.id.slider);
				TextView block_text = ChatMenuV.findViewById(R.id.block_text);
				TextView report_text = ChatMenuV.findViewById(R.id.report_text);
				LinearLayout block = ChatMenuV.findViewById(R.id.block);
				LinearLayout report = ChatMenuV.findViewById(R.id.report);
				LinearLayout body = ChatMenuV.findViewById(R.id.body);
				block_text.setText(block_s);
				report_text.setText(report_s);
				slider.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF424242));
				_rippleRoundStroke(block, "#FFFFFF", "#EEEEEE", 0, 0, "#FFFFFF");
				_rippleRoundStroke(report, "#FFFFFF", "#EEEEEE", 0, 0, "#FFFFFF");
				block.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						dialog.setTitle("Are you sure?");
						dialog.setMessage("want to block ".concat("\"".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().concat("\" ?"))));
						dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								SketchwareUtil.showMessage(getApplicationContext(), "successful");
								maoblok = new HashMap<>();
								maoblok.put("hzr", "true");
								t.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(maoblok);
								maoblok.clear();
							}
						});
						dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						dialog.create().show();
						ChatMenu.dismiss();
					}
				});
				report.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						save.edit().putString("LastReportUid", getIntent().getStringExtra("seconduser")).commit();
						ReportUserBottomdialogFragmentDialogFragmentActivityN = new ReportUserBottomdialogFragmentDialogFragmentActivity();
						ReportUserBottomdialogFragmentDialogFragmentActivityN.show(getSupportFragmentManager(),"1");
						ChatMenu.dismiss();
					}
				});
			}
		});
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("Are you sure?");
				dialog.setMessage("want to block ".concat("\"".concat(username.getText().toString().concat("\" ?"))));
				dialog.setMessage("want to block ".concat("\"".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().concat("\" ?"))));
				dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						SketchwareUtil.showMessage(getApplicationContext(), "successful");
						maoblok = new HashMap<>();
						maoblok.put("hzr", "false");
						t.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(maoblok);
						maoblok.clear();
					}
				});
				dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		messages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				final com.google.android.material.bottomsheet.BottomSheetDialog MessageMenu = new com.google.android.material.bottomsheet.BottomSheetDialog(ChatActivity.this);
				View MessageMenuV = getLayoutInflater().inflate(R.layout.message_setting_popup, null);
				MessageMenu.setContentView(MessageMenuV);
				
				final LinearLayout linear  = (LinearLayout)MessageMenuV.findViewById(R.id.body);
				
				MessageMenu.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				
				MessageMenu.show();
				
				android.graphics.drawable.GradientDrawable MM = new android.graphics.drawable.GradientDrawable();
				int dec = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				MM.setColor(0xFFFFFFFF);
				MM.setCornerRadii(new float[]{
						dec*15,dec*15,dec*15 ,dec*15,dec*0,dec*0 ,dec*0,dec*0});
				linear.setElevation(dec*5);
				android.graphics.drawable.RippleDrawable MessageMenuDG = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), MM, null);
				linear.setBackground(MessageMenuDG);
				TextView edit_title = (TextView) MessageMenuV.findViewById(R.id.edit_title);
				TextView reply_title = (TextView) MessageMenuV.findViewById(R.id.reply_title);
				TextView copy_title = (TextView) MessageMenuV.findViewById(R.id.copy_title);
				TextView delete_title = (TextView) MessageMenuV.findViewById(R.id.delete_title);
				LinearLayout slider = (LinearLayout) MessageMenuV.findViewById(R.id.slider);
				LinearLayout edit = (LinearLayout) MessageMenuV.findViewById(R.id.edit);
				LinearLayout reply = (LinearLayout) MessageMenuV.findViewById(R.id.reply);
				LinearLayout copy = (LinearLayout) MessageMenuV.findViewById(R.id.copy);
				LinearLayout delete = (LinearLayout) MessageMenuV.findViewById(R.id.delete);
				edit_title.setText(MessagePopupEdit);
				reply_title.setText(MessagePopupReply);
				copy_title.setText(MessagePopupCopy);
				delete_title.setText(MessagePopupDelete);
				slider.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF757575));
				_rippleRoundStroke(edit, "#FFFFFF", "#EEEEEE", 0, 0, "#FFFFFF");
				_rippleRoundStroke(reply, "#FFFFFF", "#EEEEEE", 0, 0, "#FFFFFF");
				_rippleRoundStroke(copy, "#FFFFFF", "#EEEEEE", 0, 0, "#FFFFFF");
				_rippleRoundStroke(delete, "#FFFFFF", "#EEEEEE", 0, 0, "#FFFFFF");
				if (save.getString("mypremium", "").equals("free")) {
					if (listmap.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (listmap.get((int)_position).get("message_type").toString().equals("MSG")) {
							edit.setVisibility(View.GONE);
							reply.setVisibility(View.VISIBLE);
							copy.setVisibility(View.VISIBLE);
							delete.setVisibility(View.VISIBLE);
						}
						else {
							if (listmap.get((int)_position).get("message_type").toString().equals("AUDIO")) {
								edit.setVisibility(View.GONE);
								reply.setVisibility(View.VISIBLE);
								copy.setVisibility(View.GONE);
								delete.setVisibility(View.VISIBLE);
							}
							else {
								if (listmap.get((int)_position).get("message_type").toString().equals("IMG")) {
									edit.setVisibility(View.GONE);
									reply.setVisibility(View.VISIBLE);
									copy.setVisibility(View.GONE);
									delete.setVisibility(View.VISIBLE);
								}
							}
						}
					}
					else {
						if (listmap.get((int)_position).get("message_type").toString().equals("MSG")) {
							edit.setVisibility(View.GONE);
							reply.setVisibility(View.VISIBLE);
							copy.setVisibility(View.VISIBLE);
							delete.setVisibility(View.GONE);
						}
						else {
							if (listmap.get((int)_position).get("message_type").toString().equals("AUDIO")) {
								edit.setVisibility(View.GONE);
								reply.setVisibility(View.VISIBLE);
								copy.setVisibility(View.GONE);
								delete.setVisibility(View.GONE);
							}
							else {
								if (listmap.get((int)_position).get("message_type").toString().equals("IMG")) {
									edit.setVisibility(View.GONE);
									reply.setVisibility(View.VISIBLE);
									copy.setVisibility(View.GONE);
									delete.setVisibility(View.GONE);
								}
							}
						}
					}
				}
				else {
					if (listmap.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (listmap.get((int)_position).get("message_type").toString().equals("MSG")) {
							edit.setVisibility(View.GONE);
							reply.setVisibility(View.VISIBLE);
							copy.setVisibility(View.VISIBLE);
							delete.setVisibility(View.VISIBLE);
						}
						else {
							if (listmap.get((int)_position).get("message_type").toString().equals("AUDIO")) {
								edit.setVisibility(View.GONE);
								reply.setVisibility(View.VISIBLE);
								copy.setVisibility(View.GONE);
								delete.setVisibility(View.VISIBLE);
							}
							else {
								if (listmap.get((int)_position).get("message_type").toString().equals("IMG")) {
									edit.setVisibility(View.GONE);
									reply.setVisibility(View.VISIBLE);
									copy.setVisibility(View.GONE);
									delete.setVisibility(View.VISIBLE);
								}
							}
						}
					}
					else {
						if (listmap.get((int)_position).get("message_type").toString().equals("MSG")) {
							edit.setVisibility(View.GONE);
							reply.setVisibility(View.VISIBLE);
							copy.setVisibility(View.VISIBLE);
							delete.setVisibility(View.VISIBLE);
						}
						else {
							if (listmap.get((int)_position).get("message_type").toString().equals("AUDIO")) {
								edit.setVisibility(View.GONE);
								reply.setVisibility(View.VISIBLE);
								copy.setVisibility(View.GONE);
								delete.setVisibility(View.VISIBLE);
							}
							else {
								if (listmap.get((int)_position).get("message_type").toString().equals("IMG")) {
									edit.setVisibility(View.GONE);
									reply.setVisibility(View.VISIBLE);
									copy.setVisibility(View.GONE);
									delete.setVisibility(View.VISIBLE);
								}
							}
						}
					}
				}
				reply.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						ReplyedBoolean = true;
						ReplyedMessageUid = listmap.get((int)_position).get("uid").toString();
						if (listmap.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							message_body_in_reply_title.setText(YouReplyTo.concat(UserName.get(getIntent().getStringExtra("firstuser")).toString()));
						}
						else {
							message_body_in_reply_title.setText(YouReplyTo.concat(UserName.get(getIntent().getStringExtra("seconduser")).toString()));
						}
						message_body_in_reply.setVisibility(View.VISIBLE);
						MessageMenu.dismiss();
						if (listmap.get((int)_position).get("message_type").toString().equals("MSG")) {
							ReplyedMessage = listmap.get((int)_position).get("message").toString();
							message_body_in_reply_text.setText(ReplyedMessage);
						}
						else {
							if (listmap.get((int)_position).get("message_type").toString().equals("AUDIO")) {
								ReplyedMessage = "AUDIO";
								message_body_in_reply_text.setText("AUDIO");
							}
							else {
								if (listmap.get((int)_position).get("message_type").toString().equals("IMG")) {
									ReplyedMessage = "IMAGE";
									message_body_in_reply_text.setText("IMAGE");
								}
							}
						}
					}
				});
				copy.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", listmap.get((int)_position).get("message").toString()));
						SketchwareUtil.showMessage(getApplicationContext(), messagecopyed);
						MessageMenu.dismiss();
					}
				});
				delete.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						save.edit().putString("LastDeleteMessageKey", listmap.get((int)_position).get("mkey").toString()).commit();
						_DeleteMessageDialog(DeleteMessageDialogTitle, DeleteMessageDialogMessage, YES, NO, true);
						MessageMenu.dismiss();
					}
				});
				edit.setVisibility(View.GONE);
			}
		});
		
		message_body_in_reply_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ReplyedBoolean = false;
				message_body_in_reply.setVisibility(View.GONE);
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				category.setVisibility(View.GONE);
			}
		});
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(musik, REQ_CD_MUSIK);
			}
		});
		
		music.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(img, REQ_CD_IMG);
			}
		});
		
		file.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent hr_image = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI); startActivityForResult(hr_image, REQ_CD_HR);
			}
		});
		
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(Cam_era, REQ_CD_CAM_ERA);
			}
		});
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(pd, REQ_CD_PD);
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_telegramLoaderDialog(true);
			}
		});
		
		sound_record_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!AudioRecording) {
					rc_time.cancel();
					try {
						
						myAudioRecorder.stop();
						myAudioRecorder.release();
						myAudioRecorder = null;
						
					} catch(Exception e) {}
					AudioRecording = true;
					message_body_in_reply.setVisibility(View.GONE);
					message_body_in_message.setVisibility(View.VISIBLE);
					sound_record_body.setVisibility(View.GONE);
					AudioTime = 0;
				}
			}
		});
		
		send_audio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				rc_time.cancel();
				try {
					
					myAudioRecorder.stop();
					myAudioRecorder.release();
					myAudioRecorder = null;
					
				} catch(Exception e) {}
				message_body_in_reply.setVisibility(View.GONE);
				sound_record_body.setVisibility(View.GONE);
				message_body_in_message.setVisibility(View.VISIBLE);
				pushAadT = AudioTime;
				AudioName = udb.push().getKey().concat(".mp3");
				sdb.child(AudioName).putFile(Uri.fromFile(new File(AudioPath))).addOnFailureListener(_sdb_failure_listener).addOnProgressListener(_sdb_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return sdb.child(AudioName).getDownloadUrl();
					}}).addOnCompleteListener(_sdb_upload_success_listener);
				progd = new ProgressDialog(ChatActivity.this);
				progd.setMessage("Uploading Audio to server");
				progd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progd.show();
			}
		});
		
		media.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (linear5.getVisibility() == View.VISIBLE) {
					category.setVisibility(View.VISIBLE);
					linear5.setVisibility(View.GONE);
				}
				else {
					category.setVisibility(View.VISIBLE);
				}
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (category.getVisibility() == View.VISIBLE) {
					linear5.setVisibility(View.VISIBLE);
					category.setVisibility(View.VISIBLE);
				}
				else {
					linear5.setVisibility(View.VISIBLE);
				}
				AXEmojiManager.install(ChatActivity.this,new com.aghajari.emojiview.emoji.iosprovider.AXIOSEmojiProvider(ChatActivity.this));
				
				AXEmojiManager.getEmojiViewTheme().setFooterEnabled(true);
				
				AXEmojiPager emoojiPage = new AXEmojiPager(ChatActivity.this);
				
				AXEmojiView emoojiView = new AXEmojiView(ChatActivity.this);
				emoojiPage.addPage(emoojiView, R.drawable.ic_edit_white);
				
				emoojiPage.setEditText(message);
				
				emoojiPage.setLeftIcon(R.drawable.ic_edit_white);
				linear1.setPopupAnimationEnabled(true);
				linear1.setPopupAnimationDuration(250);
				linear1.initPopupView(emoojiPage);
				AXEmojiManager.getEmojiViewTheme().setSelectionColor(0xFFBDBDBD);
				AXEmojiManager.getEmojiViewTheme().setFooterBackgroundColor(0xFFFFFFFF);
				linear1.show();
			}
		});
		
		message.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear1.hideAndOpenKeyboard();
			}
		});
		
		message.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (message.getText().toString().equals("")) {
					sound.setVisibility(View.VISIBLE);
					sound.setVisibility(View.GONE);
					map = new HashMap<>();
					map.put("typing", "false");
					typing2.child("typing_status").updateChildren(map);
					map.clear();
				}
				else {
					sound.setVisibility(View.GONE);
					sound.setVisibility(View.VISIBLE);
					map = new HashMap<>();
					map.put("typing", "true");
					typing2.child("typing_status").updateChildren(map);
					map.clear();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		sound.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (AudioRecording) {
					if (AudioTime < 10) {
						rekam = " 0.0";
					}
					else {
						rekam = " 0.";
					}
					rc_time = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									AudioTime++;
									recording_text.setText(RecordingString.concat(rekam.concat(String.valueOf((long)(AudioTime)))));
								}
							});
						}
					};
					_timer.scheduleAtFixedRate(rc_time, (int)(0), (int)(1000));
					sound_record_body.setVisibility(View.VISIBLE);
					message_body_in_reply.setVisibility(View.GONE);
					message_body_in_message.setVisibility(View.GONE);
					vibrate.vibrate((long)(100));
					myAudioRecorder = new MediaRecorder();
					
					myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					
					myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					
					myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
					
					myAudioRecorder.setOutputFile(AudioPath);
					try {
						
						myAudioRecorder.prepare();
						myAudioRecorder.start();
						
					} catch (Exception e) {}
					AudioRecording = false;
				}
			}
		});
		
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (message.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), emptymessage);
				}
				else {
					mkey = chat1.push().getKey();
					cc = Calendar.getInstance();
					map = new HashMap<>();
					map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("message", message.getText().toString());
					map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
					map.put("message_type", "MSG");
					map.put("edited", "false");
					if (ReplyedBoolean) {
						map.put("replyed_message", ReplyedMessage);
						map.put("replyed_message_uid", ReplyedMessageUid);
					}
					map.put("mkey", mkey);
					map.put("message_status", "forwarded");
					chat1.child(mkey).updateChildren(map);
					chat2.child(mkey).updateChildren(map);
					map.clear();
					map = new HashMap<>();
					map.put("uid", getIntent().getStringExtra("seconduser"));
					map.put("lastMessage", message.getText().toString());
					map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
					map.put("count", String.valueOf((long)(Double.parseDouble(count) + 1)));
					map.put("read", "null");
					ms.child(getIntent().getStringExtra("firstuser").concat("/".concat(getIntent().getStringExtra("seconduser")))).updateChildren(map);
					map.clear();
					map = new HashMap<>();
					map.put("uid", getIntent().getStringExtra("firstuser"));
					map.put("lastMessage", message.getText().toString());
					map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
					map.put("count", String.valueOf((long)(Double.parseDouble(count) + 1)));
					map.put("read", "null");
					ms.child(getIntent().getStringExtra("seconduser").concat("/".concat(getIntent().getStringExtra("firstuser")))).updateChildren(map);
					map.clear();
					map = new HashMap<>();
					map.put("typing", "false");
					typing2.child("typing_status").updateChildren(map);
					map.clear();
					message.setText("");
					message_body_in_reply.setVisibility(View.GONE);
					ReplyedBoolean = false;
				}
			}
		});
		
		_chat1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				ChildAdded = true;
				map = new HashMap<>();
				map.put("message_status", "seen");
				chat2.child(_childValue.get("mkey").toString()).updateChildren(map);
				map.clear();
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
		chat1.addChildEventListener(_chat1_child_listener);
		
		_chat2_child_listener = new ChildEventListener() {
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
		chat2.addChildEventListener(_chat2_child_listener);
		
		_ms_child_listener = new ChildEventListener() {
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
		ms.addChildEventListener(_ms_child_listener);
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				loader.setVisibility(View.GONE);
				if (_childValue.containsKey("uid") && _childValue.containsKey("avatar")) {
					UserProfile.put(_childValue.get("uid").toString(), _childValue.get("avatar").toString());
				}
				if (_childValue.containsKey("uid") && _childValue.containsKey("yourname")) {
					UserName.put(_childValue.get("uid").toString(), _childValue.get("yourname").toString());
				}
				if (_childKey.equals(getIntent().getStringExtra("seconduser"))) {
					typing_txt.setText("●●●    ".concat(_childValue.get("username").toString().concat(TypingStatus)));
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(avatar);
					username.setText(_childValue.get("username").toString());
					if (_childValue.get("verify").toString().equals("blue")) {
						verified.setImageResource(R.drawable.blue_verified);
						verified.setVisibility(View.VISIBLE);
					}
					else {
						if (_childValue.get("verify").toString().equals("red")) {
							verified.setImageResource(R.drawable.red_verified);
							verified.setVisibility(View.VISIBLE);
						}
						else {
							if (_childValue.get("verify").toString().equals("false")) {
								verified.setVisibility(View.GONE);
							}
						}
					}
					if (_childValue.get("blocked").toString().equals("true")) {
						warning_layout.setVisibility(View.VISIBLE);
						message_body.setVisibility(View.GONE);
					}
					else {
						if (_childValue.get("blocked").toString().equals("false")) {
							warning_layout.setVisibility(View.GONE);
							message_body.setVisibility(View.VISIBLE);
						}
					}
					if (_childValue.get("online").toString().equals("true")) {
						status.setText(OnlineStatus);
						status.setTextColor(0xFF4CAF50);
					}
					else {
						if (_childValue.get("online").toString().equals("false")) {
							status.setText(OfflineStatus);
							status.setTextColor(0xFFF44336);
						}
						else {
							_setTime(Double.parseDouble(_childValue.get("online").toString()), status);
							status.setTextColor(0xFFF44336);
						}
					}
					if (_childValue.get("story").toString().equals("true")) {
						avatar.setBackgroundResource(R.drawable.story_profile);
						avatar.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								i.setClass(getApplicationContext(), StoryviewActivity.class);
								i.putExtra("uid", getIntent().getStringExtra("seconduser"));
								startActivity(i);
							}
						});
					}
					else {
						if (_childValue.get("story").toString().equals("false")) {
							avatar.setBackgroundResource(0);
							avatar.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									i.setClass(getApplicationContext(), ProfileActivity.class);
									i.putExtra("uid", getIntent().getStringExtra("seconduser"));
									startActivity(i);
								}
							});
						}
					}
				}
				ChatSize = 40;
				query = chat1.limitToLast((int)ChatSize);
				query.addValueEventListener(valueEventListener1);
			}
			com.google.firebase.database.Query query;
			
			ValueEventListener valueEventListener1 = new ValueEventListener() {
				@Override public void onDataChange(DataSnapshot _param1) {
					try {
						str.clear();
						listmap.clear();
						GenericTypeIndicator < HashMap< String, Object>> _ind = new GenericTypeIndicator<HashMap< String, Object>>() {};
						
						for (DataSnapshot _data : _param1.getChildren()) {
							str.add(_data.getKey());
							HashMap <String, Object> _map= _data.getValue(_ind);
							listmap.add(_map); }
						messages.setAdapter(new MessagesAdapter(listmap));
						((BaseAdapter)messages.getAdapter()).notifyDataSetChanged();
					} catch (Exception e) { showMessage(e.toString()); } }
				@Override public void onCancelled(DatabaseError databaseError) { } }; {
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("blocked").toString().equals("true")) {
						intent.setClass(getApplicationContext(), BlockActivity.class);
						startActivity(intent);
					}
				}
				if (_childKey.equals(getIntent().getStringExtra("seconduser"))) {
					if (_childValue.get("online").toString().equals("true")) {
						status.setText(OnlineStatus);
						status.setTextColor(0xFF4CAF50);
					}
					else {
						typing_layout.setVisibility(View.GONE);
						if (_childValue.get("online").toString().equals("false")) {
							status.setText(OfflineStatus);
							status.setTextColor(0xFFF44336);
						}
						else {
							_setTime(Double.parseDouble(_childValue.get("online").toString()), status);
							status.setTextColor(0xFFF44336);
						}
					}
				}
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
		
		_notification_child_listener = new ChildEventListener() {
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
		notification.addChildEventListener(_notification_child_listener);
		
		_typing1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("typing").toString().equals("true")) {
					typing_layout.setVisibility(View.VISIBLE);
				}
				else {
					typing_layout.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("typing").toString().equals("true")) {
					typing_layout.setVisibility(View.VISIBLE);
				}
				else {
					typing_layout.setVisibility(View.GONE);
				}
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
		typing1.addChildEventListener(_typing1_child_listener);
		
		_typing2_child_listener = new ChildEventListener() {
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
		typing2.addChildEventListener(_typing2_child_listener);
		
		_sdb_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_sdb_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_sdb_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				_com();
				mkey = chat1.push().getKey();
				cc = Calendar.getInstance();
				map = new HashMap<>();
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				map.put("message_type", "AUDIO");
				map.put("audio_url", _downloadUrl);
				map.put("audio_played", "false");
				map.put("audio_time", (int)(pushAadT));
				map.put("mkey", mkey);
				map.put("message_status", "forwarded");
				chat1.child(mkey).updateChildren(map);
				chat2.child(mkey).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("seconduser"));
				map.put("lastMessage", "Voice 🔊 ".concat(waktu.concat(String.valueOf((long)(pushAadT)))));
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				map.put("count", String.valueOf((long)(Double.parseDouble(count) + 1)));
				map.put("read", "");
				ms.child(getIntent().getStringExtra("firstuser").concat("/".concat(getIntent().getStringExtra("seconduser")))).updateChildren(map);
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("firstuser"));
				map.put("lastMessage", "Voice 🔊 ".concat(waktu.concat(String.valueOf((long)(pushAadT)))));
				map.put("typing", "false");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				map.put("count", String.valueOf((long)(Double.parseDouble(count) + 1)));
				map.put("read", "");
				ms.child(getIntent().getStringExtra("seconduser").concat("/".concat(getIntent().getStringExtra("firstuser")))).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("typing", "false");
				typing2.child("typing_status").updateChildren(map);
				map.clear();
				progd.dismiss();
				AudioTime = 0;
				pushAadT = 0;
			}
		};
		
		_sdb_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_sdb_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_sdb_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_idb_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), String.valueOf((long)(_progressValue)).concat("%"));
			}
		};
		
		_idb_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_idb_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				mkey = chat1.push().getKey();
				cc = Calendar.getInstance();
				map = new HashMap<>();
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				map.put("message_type", "IMG");
				map.put("image_url", _downloadUrl);
				map.put("mkey", mkey);
				map.put("message_status", "forwarded");
				chat1.child(mkey).updateChildren(map);
				chat2.child(mkey).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("seconduser"));
				map.put("lastMessage", "🎑 IMAGE");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("firstuser").concat("/".concat(getIntent().getStringExtra("seconduser")))).updateChildren(map);
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("firstuser"));
				map.put("lastMessage", "🎑 IMAGE");
				map.put("typing", "false");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("seconduser").concat("/".concat(getIntent().getStringExtra("firstuser")))).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("typing", "false");
				typing2.child("typing_status").updateChildren(map);
				map.clear();
				_telegramLoaderDialog(false);
			}
		};
		
		_idb_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_idb_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_idb_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_rq_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_inbox_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(getIntent().getStringExtra("seconduser"))) {
					if (_childValue.containsKey("count")) {
						count = _childValue.get("count").toString();
					}
					else {
						count = "0";
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(getIntent().getStringExtra("seconduser"))) {
					if (_childValue.containsKey("count")) {
						count = _childValue.get("count").toString();
					}
					else {
						count = "0";
					}
				}
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
		inbox.addChildEventListener(_inbox_child_listener);
		
		_t_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("hzr").toString().equals("true")) {
					warning_layout.setVisibility(View.VISIBLE);
					message_body.setVisibility(View.GONE);
					messages.setVisibility(View.GONE);
					textview1.setVisibility(View.VISIBLE);
				}
				else {
					if (_childValue.get("hzr").toString().equals("false")) {
						message_body.setVisibility(View.VISIBLE);
						warning_layout.setVisibility(View.GONE);
						messages.setVisibility(View.VISIBLE);
						textview1.setVisibility(View.GONE);
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
		t.addChildEventListener(_t_child_listener);
		
		_cam_s_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), String.valueOf((long)(_progressValue)).concat("%"));
			}
		};
		
		_cam_s_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_cam_s_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				mkey = chat1.push().getKey();
				cc = Calendar.getInstance();
				map = new HashMap<>();
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				map.put("message_type", "CAM");
				map.put("can_url", _downloadUrl);
				map.put("mkey", mkey);
				map.put("message_status", "forwarded");
				chat1.child(mkey).updateChildren(map);
				chat2.child(mkey).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("seconduser"));
				map.put("lastMessage", "🎑 IMAGE");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("firstuser").concat("/".concat(getIntent().getStringExtra("seconduser")))).updateChildren(map);
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("firstuser"));
				map.put("lastMessage", "🎑 IMAGE");
				map.put("typing", "false");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("seconduser").concat("/".concat(getIntent().getStringExtra("firstuser")))).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("typing", "false");
				typing2.child("typing_status").updateChildren(map);
				map.clear();
				_telegramLoaderDialog(false);
			}
		};
		
		_cam_s_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_cam_s_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_cam_s_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_s_vid_o_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), String.valueOf((long)(_progressValue)).concat("%"));
			}
		};
		
		_s_vid_o_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_s_vid_o_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				mkey = chat1.push().getKey();
				cc = Calendar.getInstance();
				map = new HashMap<>();
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				map.put("message_type", "VID");
				map.put("vidoie_url", _downloadUrl);
				map.put("message", name_path);
				map.put("file_size", returnedSize);
				map.put("file_format", format);
				map.put("mkey", mkey);
				map.put("message_status", "forwarded");
				chat1.child(mkey).updateChildren(map);
				chat2.child(mkey).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("seconduser"));
				map.put("lastMessage", "Video 📷");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("firstuser").concat("/".concat(getIntent().getStringExtra("seconduser")))).updateChildren(map);
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("firstuser"));
				map.put("lastMessage", "Video 📷");
				map.put("typing", "false");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("seconduser").concat("/".concat(getIntent().getStringExtra("firstuser")))).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("typing", "false");
				typing2.child("typing_status").updateChildren(map);
				map.clear();
				_telegramLoaderDialog(false);
			}
		};
		
		_s_vid_o_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_s_vid_o_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_s_vid_o_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_musc_audio_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), String.valueOf((long)(_progressValue)).concat("%"));
			}
		};
		
		_musc_audio_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_musc_audio_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				mkey = chat1.push().getKey();
				cc = Calendar.getInstance();
				map = new HashMap<>();
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				map.put("message_type", "MUSIC");
				map.put("music_url", _downloadUrl);
				map.put("message", name_path);
				map.put("file_size", returnedSize);
				map.put("file_format", format);
				map.put("mkey", mkey);
				map.put("message_status", "forwarded");
				chat1.child(mkey).updateChildren(map);
				chat2.child(mkey).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("seconduser"));
				map.put("lastMessage", "Music 🎶");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("firstuser").concat("/".concat(getIntent().getStringExtra("seconduser")))).updateChildren(map);
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("firstuser"));
				map.put("lastMessage", "Music 🎶");
				map.put("typing", "false");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("seconduser").concat("/".concat(getIntent().getStringExtra("firstuser")))).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("typing", "false");
				typing2.child("typing_status").updateChildren(map);
				map.clear();
				_telegramLoaderDialog(false);
			}
		};
		
		_musc_audio_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_musc_audio_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_musc_audio_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_upfile_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_upfile_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_upfile_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				mkey = chat1.push().getKey();
				cc = Calendar.getInstance();
				map = new HashMap<>();
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				map.put("message_type", "FILE");
				map.put("file_url", _downloadUrl);
				map.put("message", DocPath);
				map.put("file_size", returnedSize);
				map.put("mkey", mkey);
				map.put("message_status", "forwarded");
				chat1.child(mkey).updateChildren(map);
				chat2.child(mkey).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("seconduser"));
				map.put("lastMessage", "Files 🗃️");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("firstuser").concat("/".concat(getIntent().getStringExtra("seconduser")))).updateChildren(map);
				map = new HashMap<>();
				map.put("uid", getIntent().getStringExtra("firstuser"));
				map.put("lastMessage", "Files 🗃️");
				map.put("typing", "false");
				map.put("myuid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(cc.getTimeInMillis())));
				ms.child(getIntent().getStringExtra("seconduser").concat("/".concat(getIntent().getStringExtra("firstuser")))).updateChildren(map);
				map.clear();
				map = new HashMap<>();
				map.put("typing", "false");
				typing2.child("typing_status").updateChildren(map);
				map.clear();
				_telegramLoaderDialog(false);
			}
		};
		
		_upfile_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_upfile_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_upfile_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
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
		FilePath = FileUtil.getExternalStorageDir().concat("/Unilove/Files/");
		if (!FileUtil.isExistFile(FilePath)) {
			FileUtil.makeDir(FilePath);
		}
		count = "0";
		_DataBaseCustom();
		
		AudioRecording = true;
		ReplyedBoolean = false;
		textview1.setVisibility(View.GONE);
		loader.setVisibility(View.VISIBLE);
		sound_record_body.setVisibility(View.GONE);
		category.setVisibility(View.GONE);
		typing_layout.setVisibility(View.GONE);
		messages.setStackFromBottom(true);
		verified.setVisibility(View.GONE);
		message_body_in_reply.setVisibility(View.GONE);
		AudioPath = FileUtil.getPackageDataDir(getApplicationContext()).concat("/".concat("record.mp3"));
	}
	
	private MediaRecorder myAudioRecorder;
	
	private void fo4o() {
		username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
		if (linear5.getVisibility() == View.VISIBLE) {
			category.setVisibility(View.GONE);
		}
		else {
			if (category.getVisibility() == View.VISIBLE) {
				linear5.setVisibility(View.GONE);
			}
			else {
				linear5.setVisibility(View.GONE);
				category.setVisibility(View.GONE);
			}
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_IMG:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				ImagePath = _filePath.get((int)(0));
				ImageName = Uri.parse(ImagePath).getLastPathSegment();
				idb.child(ImageName).putFile(Uri.fromFile(new File(ImagePath))).addOnFailureListener(_idb_failure_listener).addOnProgressListener(_idb_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return idb.child(ImageName).getDownloadUrl();
					}}).addOnCompleteListener(_idb_upload_success_listener);
				_telegramLoaderDialog(true);
			}
			else {
				
			}
			break;
			
			case REQ_CD_MUSIK:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				path_music = _filePath.get((int)(0));
				Name_mudic = Uri.parse(path_music).getLastPathSegment();
				_fromatmusic();
				_CalculateSize(FileUtil.getFileLength(_filePath.get((int)(0))));
				musc_audio.child(Name_mudic).putFile(Uri.fromFile(new File(path_music))).addOnFailureListener(_musc_audio_failure_listener).addOnProgressListener(_musc_audio_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return musc_audio.child(Name_mudic).getDownloadUrl();
					}}).addOnCompleteListener(_musc_audio_upload_success_listener);
				_telegramLoaderDialog(true);
			}
			else {
				
			}
			break;
			
			case REQ_CD_CAM_ERA:
			if (_resultCode == Activity.RESULT_OK) {
				 String _filePath = _file_Cam_era.getAbsolutePath();
				
				cam_path = _filePath;
				cam_name = Uri.parse(cam_path).getLastPathSegment();
				cam_s.child(cam_name).putFile(Uri.fromFile(new File(cam_path))).addOnFailureListener(_cam_s_failure_listener).addOnProgressListener(_cam_s_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return cam_s.child(cam_name).getDownloadUrl();
					}}).addOnCompleteListener(_cam_s_upload_success_listener);
			}
			else {
				
			}
			break;
			
			case REQ_CD_HR:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				path_vid = _filePath.get((int)(0));
				name_path = Uri.parse(path_vid).getLastPathSegment();
				_fromatvideo();
				_CalculateSize(FileUtil.getFileLength(_filePath.get((int)(0))));
				s_vid_o.child(name_path).putFile(Uri.fromFile(new File(path_vid))).addOnFailureListener(_s_vid_o_failure_listener).addOnProgressListener(_s_vid_o_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return s_vid_o.child(name_path).getDownloadUrl();
					}}).addOnCompleteListener(_s_vid_o_upload_success_listener);
				_telegramLoaderDialog(true);
			}
			else {
				
			}
			break;
			
			case REQ_CD_PD:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				DocPath = _filePath.get((int)(0));
				DocName = Uri.parse(DocPath).getLastPathSegment();
				_CalculateSize(FileUtil.getFileLength(_filePath.get((int)(0))));
				upfile.child(DocName).putFile(Uri.fromFile(new File(DocPath))).addOnFailureListener(_upfile_failure_listener).addOnProgressListener(_upfile_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return upfile.child(DocName).getDownloadUrl();
					}}).addOnCompleteListener(_upfile_upload_success_listener);
				_telegramLoaderDialog(true);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		map = new HashMap<>();
		map.put("typing", "false");
		typing2.child("typing_status").updateChildren(map);
		map.clear();
		chat1.removeEventListener(_chat1_child_listener);
		chat2.removeEventListener(_chat2_child_listener);
		finish();
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		_ImageColor(back, "#000000");
		_ImageColor(more_button, "#000000");
		_ImageColor(media, "#000000");
		_ImageColor(sound, "#000000");
		username.setTextColor(0xFF000000);
		message.setTextColor(0xFF000000);
		warning_layout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFEEEEEE));
		message_body.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)45, 0xFFEEEEEE));
		_rippleRoundStroke(send, "#673AB7", "#82B1FF", 100, 0, "#FFFFFF");
		_CustomView();
		_RippleRoundCustomView();
		_Language();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		map = new HashMap<>();
		map.put("typing", "false");
		typing2.child("typing_status").updateChildren(map);
		map.clear();
		chat1.removeEventListener(_chat1_child_listener);
		chat2.removeEventListener(_chat2_child_listener);
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
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
	
	public void _RippleRoundCustomView() {
		_rippleRoundStroke(profile, "#FFFFFF", "#BDBDBD", 8, 0, "#FFFFFF");
		_rippleRoundStroke(more_button, "#FFFFFF", "#BDBDBD", 100, 0, "#FFFFFF");
		_rippleRoundStroke(back, "#FFFFFF", "#BDBDBD", 100, 0, "#FFFFFF");
	}
	
	
	public void _DataBaseCustom() {
		chat1.removeEventListener(_chat1_child_listener);
		chat2.removeEventListener(_chat2_child_listener);
		chatroom = "chat/".concat(getIntent().getStringExtra("firstuser").concat("/".concat(getIntent().getStringExtra("seconduser"))));
		chatcopy = "chat/".concat(getIntent().getStringExtra("seconduser").concat("/".concat(getIntent().getStringExtra("firstuser"))));
		chat1 =
		_firebase.getReference(chatroom);
		chat2 =
		_firebase.getReference(chatcopy);
		chat1.addChildEventListener(_chat1_child_listener);
		chat2.addChildEventListener(_chat2_child_listener);
		typing1.removeEventListener(_typing1_child_listener);
		typing2.removeEventListener(_typing2_child_listener);
		typingview = "typing/".concat(getIntent().getStringExtra("firstuser").concat("/".concat(getIntent().getStringExtra("seconduser"))));
		typingcopy = "typing/".concat(getIntent().getStringExtra("seconduser").concat("/".concat(getIntent().getStringExtra("firstuser"))));
		typing1 =
		_firebase.getReference(typingview);
		typing2 =
		_firebase.getReference(typingcopy);
		typing1.addChildEventListener(_typing1_child_listener);
		typing2.addChildEventListener(_typing2_child_listener);
		inbox.removeEventListener(_inbox_child_listener);
		inboxx = "inbox/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		inbox =
		_firebase.getReference(inboxx);
		inbox.addChildEventListener(_inbox_child_listener);
	}
	
	
	public void _CustomView() {
		ReplyedBoolean = false;
		ChildAdded = false;
		top.setElevation((float)8);
		_ImageColor(send, "#FFFFFF");
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
	
	
	public void _setTime(final double _currentTime, final TextView _txt) {
		tm_difference = ct.getTimeInMillis() - _currentTime;
		if (tm_difference < 60000) {
			if ((tm_difference / 1000) < 2) {
				_txt.setText(SetTimeAsecond);
			}
			else {
				_txt.setText(String.valueOf((long)(tm_difference / 1000)).concat(SetTimeSecond));
			}
		}
		else {
			if (tm_difference < (60 * 60000)) {
				if ((tm_difference / 60000) < 2) {
					_txt.setText(SetTimeAMinute);
				}
				else {
					_txt.setText(String.valueOf((long)(tm_difference / 60000)).concat(SetTimeMinute));
				}
			}
			else {
				if (tm_difference < (24 * (60 * 60000))) {
					if ((tm_difference / (60 * 60000)) < 2) {
						_txt.setText(String.valueOf((long)(tm_difference / (60 * 60000))).concat(SetTimeHours));
					}
					else {
						_txt.setText(String.valueOf((long)(tm_difference / (60 * 60000))).concat(SetTimeHours));
					}
				}
				else {
					if (tm_difference < (7 * (24 * (60 * 60000)))) {
						if ((tm_difference / (24 * (60 * 60000))) < 2) {
							_txt.setText(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(SetTimeDays));
						}
						else {
							_txt.setText(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(SetTimeDays));
						}
					}
					else {
						time2.setTimeInMillis((long)(_currentTime));
						_txt.setText(new SimpleDateFormat("dd MMM yyyy hh:mm").format(time2.getTime()));
					}
				}
			}
		}
	}
	
	
	public void _TrLang() {
		message.setHint("Mesaj Yaz");
		warning.setText("⛔ Bu kullanıcı engellendi!");
		
		
		
		SetTimeAsecond = "Bir saniye önce";
		SetTimeSecond = " saniye önce";
		SetTimeAMinute = "Bir dakika önce";
		SetTimeMinute = " dakika önce";
		SetTimeHours = " saat önce";
		SetTimeDays = " gün önce";
		OnlineStatus = "Şu an aktif";
		OfflineStatus = "Çevrimdışı";
		TypingStatus = " Yazıyor...";
		AudioErrorL = "Hata!";
		AudioMinute = " Dakika";
		AudioSecond = " Saniye";
		videocall = "Görüntülü Ara";
		block_s = "Engelle";
		report_s = "Bildir";
		messagecopyed = "Mesaj Kopyalandı";
		emptymessage = "Boş Mesaj Gönderilemez";
		edited = "Düzenlendi!";
		uploading_audio = "Ses sunucuya yükleniyor...";
		uploading_image = "Resim sunucuya yükleniyor...";
		RecordingString = "●●●   Kaydediliyor...    ";
		YouReplyTo = "Yanıt Veriyorsun : ";
		YES = "Evet";
		NO = "Hayır";
		DeleteMessageDialogTitle = "BİLGİ";
		DeleteMessageDialogMessage = "Mesajı gerçekten silmek istediğinizden emin misiniz?";
		MessagePopupEdit = "Düzenle";
		MessagePopupReply = "Yanıtla";
		MessagePopupCopy = "Kopyala";
		MessagePopupDelete = "Sil";
	}
	
	
	public void _EnLang() {
		message.setHint("Write message");
		warning.setText("⛔ This user has been blocked!");
		
		
		
		SetTimeAsecond = "One second ago";
		SetTimeSecond = " seconds ago";
		SetTimeAMinute = "One minute ago";
		SetTimeMinute = " minute ago";
		SetTimeHours = " hours ago";
		SetTimeDays = " days ago";
		OnlineStatus = "Active now";
		OfflineStatus = "Offline";
		TypingStatus = " is Typing...";
		AudioErrorL = "Error!";
		AudioMinute = " Minute";
		AudioSecond = " Second";
		videocall = "Video Call";
		block_s = "Block";
		report_s = "Report";
		messagecopyed = "Message Copied";
		emptymessage = "Empty Message Cannot Be Sent";
		edited = "Edited!";
		uploading_audio = "Uploading audio to server...";
		uploading_image = "The image is uploading to the server...";
		RecordingString = "●●●   Recording...    ";
		YouReplyTo = "You Reply To : ";
		YES = "Yes";
		NO = "No";
		DeleteMessageDialogTitle = "INFO";
		DeleteMessageDialogMessage = "Are you sure you really want to delete the message?";
		MessagePopupEdit = "Edit";
		MessagePopupReply = "Reply";
		MessagePopupCopy = "Copy";
		MessagePopupDelete = "Delete";
	}
	
	
	public void _SX_CornerRadius_4(final View _view, final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color1));
		
		gd.setStroke((int)_str, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});
		
		_view.setBackground(gd);
		
		_view.setElevation(2);
	}
	
	
	public void _lib() {
	}
	private ReportUserBottomdialogFragmentDialogFragmentActivity ReportUserBottomdialogFragmentDialogFragmentActivityN;
	private FragmentManager ReportUserBottomdialogFragmentDialogFragmentActivityFM;
	public void test_ReportUserBottomdialogFragmentDialogFragmentActivity () {
	}
	
	
	public void _DeleteMessageDialog(final String _Title, final String _Message, final String _YesButtonText, final String _NoButtonText, final boolean _MultiButton) {
		final AlertDialog DeleteMessageDialog = new AlertDialog.Builder(ChatActivity.this).create();
		LayoutInflater DeleteMessageDialogLI = getLayoutInflater();
		View DeleteMessageDialogCV = (View) DeleteMessageDialogLI.inflate(R.layout.new_custom_dialog, null);
		DeleteMessageDialog.setView(DeleteMessageDialogCV);
		DeleteMessageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		final TextView dialog_title = (TextView)
		DeleteMessageDialogCV.findViewById(R.id.dialog_title);
		final TextView dialog_message = (TextView)
		DeleteMessageDialogCV.findViewById(R.id.dialog_message);
		final TextView dialog_no_button = (TextView)
		DeleteMessageDialogCV.findViewById(R.id.dialog_no_button);
		final TextView dialog_yes_button = (TextView)
		DeleteMessageDialogCV.findViewById(R.id.dialog_yes_button);
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
				chat1.child(save.getString("LastDeleteMessageKey", "")).removeValue();
				chat2.child(save.getString("LastDeleteMessageKey", "")).removeValue();
				DeleteMessageDialog.dismiss();
				
			}
		});
		dialog_no_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				DeleteMessageDialog.dismiss();
				
			}
		});
		dialog_yes_button.setText(_YesButtonText);
		DeleteMessageDialog.setCancelable(true);
		DeleteMessageDialog.show();
	}
	
	
	public void _InLang() {
		message.setHint("Tulis Pesan");
		warning.setText("⛔ Pengguna ini telah diblokir!");
		
		
		
		SetTimeAsecond = "Satu detik lalu";
		SetTimeSecond = " detik lalu";
		SetTimeAMinute = "Satu menit lalu";
		SetTimeMinute = " menit lalu";
		SetTimeHours = " jam lalu";
		SetTimeDays = " Hari lalu";
		OnlineStatus = "Sedang Aktif";
		OfflineStatus = "Offline";
		TypingStatus = " Mengetik...";
		AudioErrorL = "Error!";
		AudioMinute = " Menit";
		AudioSecond = " Detik";
		videocall = "Video Call";
		block_s = "Blokir";
		report_s = "Laporkan";
		messagecopyed = "Pesan Disalin";
		emptymessage = "Tidak dapat mengirim pesan kosong";
		edited = "Diedit!";
		uploading_audio = "Mengupload audio ke server...";
		uploading_image = "Gambar sedang di upload ke  server...";
		RecordingString = "●●●   Merekam...    ";
		YouReplyTo = "Kamu Membalas ke : ";
		YES = "Iya";
		NO = "Tidak";
		DeleteMessageDialogTitle = "INFO";
		DeleteMessageDialogMessage = "Kamu yakin ingin menghapus pesan?";
		MessagePopupEdit = "Edit";
		MessagePopupReply = "Balas";
		MessagePopupCopy = "Salin";
		MessagePopupDelete = "Hapus";
	}
	
	
	public void _com() {
		if (pushAadT < 10) {
			waktu = "0.0";
		}
		else {
			waktu = "0.";
		}
	}
	
	
	public void _CalculateSize(final double _fileSize) {
		B = 1024;
		KB = B * B;
		MB = B * (B * B);
		GB = B * (B * (B * B));
		TB = B * (B * (B * (B * B)));
		PB = B * (B * (B * (B * (B * B))));
		if (_fileSize < B) {
			returnedSize = String.valueOf((long)(_fileSize)).concat(" B");
		}
		else {
			if (_fileSize < KB) {
				returnedSize = new DecimalFormat("0.00").format(_fileSize / B).concat(" KB");
			}
			else {
				if (_fileSize < MB) {
					returnedSize = new DecimalFormat("0.00").format(_fileSize / KB).concat(" MB");
				}
				else {
					if (_fileSize < GB) {
						returnedSize = new DecimalFormat("0.00").format(_fileSize / MB).concat(" GB");
					}
					else {
						if (_fileSize < TB) {
							returnedSize = new DecimalFormat("0.00").format(_fileSize / GB).concat(" TB");
						}
						else {
							if (_fileSize < PB) {
								returnedSize = new DecimalFormat("0.00").format(_fileSize / TB).concat(" PB");
							}
						}
					}
				}
			}
		}
	}
	
	
	public void _Popup() {
		View popupView = getLayoutInflater().inflate(R.layout.custom3, null);
		final PopupWindow popup = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		LinearLayout ay = popupView.findViewById(R.id.bg);
		
		LottieAnimationView re = popupView.findViewById(R.id.re);
		re.setAnimationFromJson("{\"v\":\"4.8.0\",\"meta\":{\"g\":\"LottieFiles AE 1.1.0\",\"a\":\"\",\"k\":\"\",\"d\":\"\",\"tc\":\"#1a1a1a\"},\"fr\":60,\"ip\":0,\"op\":296,\"w\":88,\"h\":88,\"nm\":\"Loop\",\"ddd\":0,\"assets\":[],\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":4,\"nm\":\"Group 1522 Outlines\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"t\":0,\"s\":[139]},{\"t\":295,\"s\":[2299]}],\"ix\":10},\"p\":{\"a\":0,\"k\":[44,44,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[44,44,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"shapes\":[{\"ty\":\"gr\",\"it\":[{\"ind\":0,\"ty\":\"sh\",\"ix\":1,\"ks\":{\"a\":0,\"k\":{\"i\":[[0.028,-0.026],[0.552,0.607],[-1.775,11.035],[-12.156,3.838],[-6.332,-1.757],[-1.687,-0.746],[0.828,-2.778],[3.437,0.818],[0.663,0.264],[8.067,-3.763],[2.679,-4.374],[-2.723,-7.972],[-3.281,-3.227],[-0.077,-0.078]],\"o\":[[-0.582,-0.58],[-7.475,-8.217],[2.028,-12.606],[6.286,-1.985],[1.775,0.492],[2.599,1.149],[-1.015,3.404],[-0.689,-0.164],[-8.195,-3.261],[-4.62,2.155],[-4.441,7.251],[1.494,4.376],[0.078,0.077],[-0.028,0.026]],\"v\":[[-14.317,29.751],[-16.06,28.009],[-24.791,-0.88],[-1.585,-27.622],[17.366,-27.994],[22.563,-26.074],[25.738,-18.803],[18.087,-14.327],[16.083,-15.08],[-8.34,-14.579],[-19.37,-4.796],[-21.757,18.096],[-14.467,29.439],[-14.234,29.673]],\"c\":true},\"ix\":2},\"nm\":\"Path 1\",\"mn\":\"ADBE Vector Shape - Group\",\"hd\":false},{\"ty\":\"gf\",\"o\":{\"a\":0,\"k\":100,\"ix\":10},\"r\":1,\"bm\":0,\"g\":{\"p\":2,\"k\":{\"a\":0,\"k\":[0,0.4745098039215686,0.34901960784313724,0.7372549019607844,1,0.8745098039215686,0.5647058823529412,0.18823529411764706],\"ix\":9}},\"s\":{\"a\":0,\"k\":[46.813,-21.663],\"ix\":5},\"e\":{\"a\":0,\"k\":[-40.927,-12.884],\"ix\":6},\"t\":1,\"nm\":\"Gradient Fill 1\",\"mn\":\"ADBE Vector Graphic - G-Fill\",\"hd\":false},{\"ty\":\"tr\",\"p\":{\"a\":0,\"k\":[35.462,39.957],\"ix\":2},\"a\":{\"a\":0,\"k\":[0,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100],\"ix\":3},\"r\":{\"a\":0,\"k\":0,\"ix\":6},\"o\":{\"a\":0,\"k\":100,\"ix\":7},\"sk\":{\"a\":0,\"k\":0,\"ix\":4},\"sa\":{\"a\":0,\"k\":0,\"ix\":5},\"nm\":\"Transform\"}],\"nm\":\"Group 1\",\"np\":2,\"cix\":2,\"bm\":0,\"ix\":1,\"mn\":\"ADBE Vector Group\",\"hd\":false},{\"ty\":\"gr\",\"it\":[{\"ind\":0,\"ty\":\"sh\",\"ix\":1,\"ks\":{\"a\":0,\"k\":{\"i\":[[-0.028,0.026],[-0.552,-0.607],[1.775,-11.035],[12.156,-3.838],[6.332,1.757],[1.687,0.746],[-0.828,2.778],[-3.437,-0.818],[-0.663,-0.264],[-8.067,3.763],[-2.679,4.375],[2.723,7.972],[3.281,3.227],[0.078,0.078]],\"o\":[[0.582,0.58],[7.475,8.217],[-2.028,12.606],[-6.286,1.985],[-1.775,-0.492],[-2.599,-1.149],[1.015,-3.404],[0.689,0.164],[8.195,3.261],[4.62,-2.155],[4.441,-7.251],[-1.494,-4.376],[-0.078,-0.077],[0.028,-0.026]],\"v\":[[14.317,-29.751],[16.06,-28.009],[24.791,0.88],[1.585,27.622],[-17.366,27.994],[-22.563,26.074],[-25.738,18.803],[-18.087,14.327],[-16.083,15.08],[8.34,14.579],[19.37,4.796],[21.757,-18.096],[14.467,-29.439],[14.234,-29.673]],\"c\":true},\"ix\":2},\"nm\":\"Path 1\",\"mn\":\"ADBE Vector Shape - Group\",\"hd\":false},{\"ty\":\"gf\",\"o\":{\"a\":0,\"k\":100,\"ix\":10},\"r\":1,\"bm\":0,\"g\":{\"p\":2,\"k\":{\"a\":0,\"k\":[0,0.4745098039215686,0.34901960784313724,0.7372549019607844,1,0.8745098039215686,0.5647058823529412,0.18823529411764706],\"ix\":9}},\"s\":{\"a\":0,\"k\":[-13.683,31.061],\"ix\":5},\"e\":{\"a\":0,\"k\":[21.634,-16.316],\"ix\":6},\"t\":1,\"nm\":\"Gradient Fill 1\",\"mn\":\"ADBE Vector Graphic - G-Fill\",\"hd\":false},{\"ty\":\"tr\",\"p\":{\"a\":0,\"k\":[51.676,47.182],\"ix\":2},\"a\":{\"a\":0,\"k\":[0,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100],\"ix\":3},\"r\":{\"a\":0,\"k\":0,\"ix\":6},\"o\":{\"a\":0,\"k\":100,\"ix\":7},\"sk\":{\"a\":0,\"k\":0,\"ix\":4},\"sa\":{\"a\":0,\"k\":0,\"ix\":5},\"nm\":\"Transform\"}],\"nm\":\"Group 2\",\"np\":2,\"cix\":2,\"bm\":0,\"ix\":2,\"mn\":\"ADBE Vector Group\",\"hd\":false}],\"ip\":0,\"op\":296,\"st\":0,\"bm\":0}],\"markers\":[]}");
	}
	
	
	public void _telegramLoaderDialog(final boolean _visibility) {
		if (_visibility) {
			if (coreprog == null){
					coreprog = new ProgressDialog(this);
					coreprog.setCancelable(false);
					coreprog.setCanceledOnTouchOutside(false);
					
					coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
					
			}
			coreprog.show();
			coreprog.setContentView(R.layout.loading);
			
			
			LinearLayout linear2 = (LinearLayout)coreprog.findViewById(R.id.linear2);
			
			LinearLayout back = (LinearLayout)coreprog.findViewById(R.id.background);
			
			LinearLayout layout_progress = (LinearLayout)coreprog.findViewById(R.id.layout_progress);
			
			if (save.getString("theme", "").equals("light")) {
					android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
					gd.setColor(Color.parseColor("#FFFFFF")); /* color */
					gd.setCornerRadius(45); /* radius */
					gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
					linear2.setBackground(gd);
			}
			else {
					if (save.getString("theme", "").equals("dark")) {
							android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
							gd.setColor(Color.parseColor("#212121")); /* color */
							gd.setCornerRadius(45); /* radius */
							gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
							linear2.setBackground(gd);
					}
			}
			
			RadialProgressView progress = new RadialProgressView(this);
			layout_progress.addView(progress);
			
		}
		else {
			if (coreprog != null){
				coreprog.dismiss();
			}
		}
	}
	private ProgressDialog coreprog;
	{
	}
	
	
	public void _fromatmusic() {
		if (Uri.parse(path_music).getLastPathSegment().substring((int)(Uri.parse(path_music).getLastPathSegment().length() - ".".concat("mp3").length()), (int)(Uri.parse(path_music).getLastPathSegment().length())).equals(".mp3")) {
			format = "MP3";
		}
	}
	
	
	public void _fromatvideo() {
		if (Uri.parse(path_vid).getLastPathSegment().substring((int)(Uri.parse(path_vid).getLastPathSegment().length() - ".".concat("mp4").length()), (int)(Uri.parse(path_vid).getLastPathSegment().length())).equals(".mp4")) {
			format = "MP4";
		}
	}
	
	
	public void _AdvancedView(final View _v, final String _colour1, final String _colour2, final String _borderColour, final double _cornerRadius, final double _borderThickness, final String _eColour, final double _elevation) {
		int[] colors = {Color.parseColor(_colour1),Color.parseColor(_colour2)};
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.BR_TL, colors); //Made by XenonDry
		gd.setCornerRadius((float)_cornerRadius); 
		gd.setStroke((int)_borderThickness, (Color.parseColor(_borderColour)));
		_v.setBackground(gd);
		//Made by XenonDry
		_v.setElevation((float)_elevation);
		
		_v.setOutlineAmbientShadowColor(Color.parseColor(_eColour));
		_v.setOutlineSpotShadowColor(Color.parseColor(_eColour));
	}
	
	public class MessagesAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public MessagesAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.message, null);
			}
			
			final LinearLayout body = _view.findViewById(R.id.body);
			final LinearLayout message_mn = _view.findViewById(R.id.message_mn);
			final LinearLayout message_layout = _view.findViewById(R.id.message_layout);
			final LinearLayout reply = _view.findViewById(R.id.reply);
			final TextView message_text = _view.findViewById(R.id.message_text);
			final LinearLayout linear_file = _view.findViewById(R.id.linear_file);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout audio_play_layout = _view.findViewById(R.id.audio_play_layout);
			final androidx.cardview.widget.CardView cardview2 = _view.findViewById(R.id.cardview2);
			final LinearLayout linear_vidro = _view.findViewById(R.id.linear_vidro);
			final androidx.cardview.widget.CardView image_card = _view.findViewById(R.id.image_card);
			final LinearLayout date_time_seen = _view.findViewById(R.id.date_time_seen);
			final LinearLayout reply_wt_box = _view.findViewById(R.id.reply_wt_box);
			final LinearLayout reply_in_info = _view.findViewById(R.id.reply_in_info);
			final TextView reply_in_username = _view.findViewById(R.id.reply_in_username);
			final TextView reply_in_message = _view.findViewById(R.id.reply_in_message);
			final ImageView im_file = _view.findViewById(R.id.im_file);
			final TextView tx_file_name = _view.findViewById(R.id.tx_file_name);
			final ImageView download = _view.findViewById(R.id.download);
			final androidx.cardview.widget.CardView cardview_auido = _view.findViewById(R.id.cardview_auido);
			final TextView textview_audio = _view.findViewById(R.id.textview_audio);
			final ImageView download_music = _view.findViewById(R.id.download_music);
			final ImageView imageview_audio = _view.findViewById(R.id.imageview_audio);
			final ImageView play_pause = _view.findViewById(R.id.play_pause);
			final TextView audio_time = _view.findViewById(R.id.audio_time);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final androidx.cardview.widget.CardView vi_dio_cradview = _view.findViewById(R.id.vi_dio_cradview);
			final TextView textview_video = _view.findViewById(R.id.textview_video);
			final ImageView download_vidro = _view.findViewById(R.id.download_vidro);
			final ImageView imageview_video = _view.findViewById(R.id.imageview_video);
			final ImageView image_ = _view.findViewById(R.id.image_);
			final TextView format_file = _view.findViewById(R.id.format_file);
			final TextView date = _view.findViewById(R.id.date);
			final ImageView status = _view.findViewById(R.id.status);
			
			format_file.setVisibility(View.GONE);
			if (_data.get((int)_position).containsKey("message_type")) {
				if (_data.get((int)_position).containsKey("replyed_message")) {
					reply.setVisibility(View.VISIBLE);
					reply_in_username.setText(UserName.get(_data.get((int)_position).get("replyed_message_uid").toString()).toString());
					reply_in_message.setText(_data.get((int)_position).get("replyed_message").toString());
				}
				else {
					reply.setVisibility(View.GONE);
				}
				if (_data.get((int)_position).get("message_type").toString().equals("MSG")) {
					image_card.setVisibility(View.GONE);
					audio_play_layout.setVisibility(View.GONE);
					message_text.setVisibility(View.VISIBLE);
					linear_file.setVisibility(View.GONE);
					linear2.setVisibility(View.GONE);
					linear_vidro.setVisibility(View.GONE);
					cardview2.setVisibility(View.GONE);
					message_text.setText(_data.get((int)_position).get("message").toString());
				}
				if (_data.get((int)_position).get("message_type").toString().equals("CAM")) {
					linear_vidro.setVisibility(View.GONE);
					linear2.setVisibility(View.GONE);
					image_card.setVisibility(View.GONE);
					cardview2.setVisibility(View.VISIBLE);
					audio_play_layout.setVisibility(View.GONE);
					message_text.setVisibility(View.GONE);
					linear_file.setVisibility(View.GONE);
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("can_url").toString())).into(imageview2);
				}
				if (_data.get((int)_position).get("message_type").toString().equals("VID")) {
					linear2.setVisibility(View.GONE);
					cardview2.setVisibility(View.GONE);
					linear_vidro.setVisibility(View.VISIBLE);
					image_card.setVisibility(View.GONE);
					audio_play_layout.setVisibility(View.GONE);
					message_text.setVisibility(View.GONE);
					linear_file.setVisibility(View.GONE);
					format_file.setVisibility(View.VISIBLE);
					textview_video.setText(_data.get((int)_position).get("message").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("vidoie_url").toString())).into(imageview_video);
					format_file.setText(_data.get((int)_position).get("file_size").toString().concat(" • ".concat(Uri.parse(_data.get((int)_position).get("file_format").toString()).getLastPathSegment())));
					if (FileUtil.isExistFile(path_vid.concat(_data.get((int)_position).get("message").toString()))) {
						download_vidro.setVisibility(View.GONE);
					}
					else {
						download_vidro.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								download_vidro.setVisibility(View.GONE);
								_firebase_storage.getReferenceFromUrl(_data.get((int)_position).get("vidoie_url").toString()).getFile(new File(FilePath.concat(_data.get((int)_position).get("message").toString()))).addOnSuccessListener(_s_vid_o_download_success_listener).addOnFailureListener(_s_vid_o_failure_listener).addOnProgressListener(_s_vid_o_download_progress_listener);
							}
						});
					}
					imageview_video.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							i.setClass(getApplicationContext(), VDieoViewActivity.class);
							i.putExtra("v_d", _data.get((int)_position).get("vidoie_url").toString());
							startActivity(i);
						}
					});
				}
				if (_data.get((int)_position).get("message_type").toString().equals("MUSIC")) {
					linear2.setVisibility(View.VISIBLE);
					cardview2.setVisibility(View.GONE);
					linear_vidro.setVisibility(View.GONE);
					image_card.setVisibility(View.GONE);
					audio_play_layout.setVisibility(View.GONE);
					message_text.setVisibility(View.GONE);
					format_file.setVisibility(View.VISIBLE);
					linear_file.setVisibility(View.GONE);
					imageview_audio.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
									try {
											imageview_audio.setImageResource(R.drawable.round_pause_circle_white_48dp);
											audioUrl = _data.get((int)_position).get("music_url").toString();
											if (isPlaying) {
													if (audio.isPlaying()) {
															audio.stop();
													}
											}
											else {
													isPlaying = true;
											}
											audio = new MediaPlayer();
											audio.setAudioStreamType(3);
											try {
													audio.setDataSource(audioUrl);
											}catch (IllegalArgumentException e) {
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show();
											} catch (SecurityException e2) {
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show(); }
											catch (IllegalStateException e3) {
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show(); }
											catch (java.io.IOException e4)
											{
													e4.printStackTrace();
											}
											try
											{
													audio.prepare();
											}
											catch (IllegalStateException e5)
											{
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show();
											}
											catch (java.io.IOException e6)
											{
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show();
											}
											audio.start();
											audiotimer = new TimerTask() {
													@Override
													public void run() {
															runOnUiThread(new Runnable() {
																	@Override
																	public void run() {
																			if (audio.isPlaying()) {
																					imageview_audio.setImageResource(R.drawable.round_pause_circle_white_48dp);
																					isPlaying = true;
																			}
																			else {
																					imageview_audio.setImageResource(R.drawable.round_play_circle_white_48dp);
																					audiotimer.cancel();
																			}
																	}
															});
													}
											};
											_timer.scheduleAtFixedRate(audiotimer, (int)(0), (int)(100));
											limit = _position;
											audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
													
													public void onCompletion(MediaPlayer theMediaPlayer){
															position_++;
													}
											});
									} catch (Exception e) {
											
									}
							}
					});
					
					textview_audio.setText(_data.get((int)_position).get("message").toString());
					format_file.setText(_data.get((int)_position).get("file_size").toString().concat(" • ".concat(Uri.parse(_data.get((int)_position).get("file_format").toString()).getLastPathSegment())));
					if (FileUtil.isExistFile(path_music.concat(_data.get((int)_position).get("message").toString()))) {
						download_music.setVisibility(View.GONE);
					}
					else {
						download_music.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								download_music.setVisibility(View.GONE);
								_firebase_storage.getReferenceFromUrl(_data.get((int)_position).get("music_url").toString()).getFile(new File(path_music.concat(_data.get((int)_position).get("message").toString()))).addOnSuccessListener(_musc_audio_download_success_listener).addOnFailureListener(_musc_audio_failure_listener).addOnProgressListener(_musc_audio_download_progress_listener);
							}
						});
					}
				}
				if (_data.get((int)_position).get("message_type").toString().equals("IMG")) {
					linear2.setVisibility(View.GONE);
					cardview2.setVisibility(View.GONE);
					linear_vidro.setVisibility(View.GONE);
					image_card.setVisibility(View.VISIBLE);
					audio_play_layout.setVisibility(View.GONE);
					message_text.setVisibility(View.GONE);
					linear_file.setVisibility(View.GONE);
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("image_url").toString())).into(image_);
					image_card.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							intent.putExtra("image", _data.get((int)_position).get("image_url").toString());
							intent.putExtra("username", UserName.get(getIntent().getStringExtra("seconduser")).toString());
							intent.putExtra("uid", getIntent().getStringExtra("seconduser"));
							intent.putExtra("date", _data.get((int)_position).get("timestamp").toString());
							intent.setClass(getApplicationContext(), StoryviewActivity.class);
							startActivity(intent);
						}
					});
				}
				if (_data.get((int)_position).get("message_type").toString().equals("FILE")) {
					linear_vidro.setVisibility(View.GONE);
					cardview2.setVisibility(View.GONE);
					image_card.setVisibility(View.GONE);
					audio_play_layout.setVisibility(View.GONE);
					message_text.setVisibility(View.GONE);
					linear_file.setVisibility(View.VISIBLE);
					format_file.setVisibility(View.VISIBLE);
					linear2.setVisibility(View.GONE);
					tx_file_name.setText(_data.get((int)_position).get("message").toString());
					format_file.setText(_data.get((int)_position).get("file_size").toString());
					if (FileUtil.isExistFile(FilePath.concat(_data.get((int)_position).get("message").toString()))) {
						download.setVisibility(View.GONE);
					}
					else {
						download.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								download.setVisibility(View.GONE);
								_firebase_storage.getReferenceFromUrl(_data.get((int)_position).get("file_url").toString()).getFile(new File(FilePath.concat(_data.get((int)_position).get("message").toString()))).addOnSuccessListener(_upfile_download_success_listener).addOnFailureListener(_upfile_failure_listener).addOnProgressListener(_upfile_download_progress_listener);
							}
						});
					}
				}
				if (_data.get((int)_position).get("message_type").toString().equals("AUDIO")) {
					linear2.setVisibility(View.GONE);
					linear_vidro.setVisibility(View.GONE);
					cardview2.setVisibility(View.GONE);
					image_card.setVisibility(View.GONE);
					audio_play_layout.setVisibility(View.VISIBLE);
					message_text.setVisibility(View.GONE);
					linear_file.setVisibility(View.GONE);
					play_pause.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
									try {
											play_pause.setImageResource(R.drawable.round_pause_circle_white_48dp);
											audioUrl = _data.get((int)_position).get("audio_url").toString();
											if (isPlaying) {
													if (audio.isPlaying()) {
															audio.stop();
													}
											}
											else {
													isPlaying = true;
											}
											audio = new MediaPlayer();
											audio.setAudioStreamType(3);
											try {
													audio.setDataSource(audioUrl);
											}catch (IllegalArgumentException e) {
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show();
											} catch (SecurityException e2) {
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show(); }
											catch (IllegalStateException e3) {
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show(); }
											catch (java.io.IOException e4)
											{
													e4.printStackTrace();
											}
											try
											{
													audio.prepare();
											}
											catch (IllegalStateException e5)
											{
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show();
											}
											catch (java.io.IOException e6)
											{
													Toast.makeText(getApplicationContext(), "Unknown URL!", 1).show();
											}
											audio.start();
											audiotimer = new TimerTask() {
													@Override
													public void run() {
															runOnUiThread(new Runnable() {
																	@Override
																	public void run() {
																			if (audio.isPlaying()) {
																					play_pause.setImageResource(R.drawable.round_pause_circle_white_48dp);
																					isPlaying = true;
																			}
																			else {
																					play_pause.setImageResource(R.drawable.round_play_circle_white_48dp);
																					audiotimer.cancel();
																			}
																	}
															});
													}
											};
											_timer.scheduleAtFixedRate(audiotimer, (int)(0), (int)(100));
											limit = _position;
											audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
													
													public void onCompletion(MediaPlayer theMediaPlayer){
															position_++;
													}
											});
									} catch (Exception e) {
											
									}
							}
					});
					
					if (_data.get((int)_position).containsKey("audio_time")) {
						if (Double.parseDouble(_data.get((int)_position).get("audio_time").toString()) < 60) {
								audio_time.setText(_data.get((int)_position).get("audio_time").toString().concat(AudioSecond));
						}
						else {
								if (Double.parseDouble(_data.get((int)_position).get("audio_time").toString()) == 60) {
										audio_time.setText(String.valueOf(Double.parseDouble(_data.get((int)_position).get("audio_time").toString()) / 60).concat(AudioMinute));
								}
								else {
										if (Double.parseDouble(_data.get((int)_position).get("audio_time").toString()) > 60) {
												audio_time.setText(String.valueOf(Double.parseDouble(_data.get((int)_position).get("audio_time").toString()) / 60).concat(AudioMinute));
										}
										else {
												audio_time.setText(AudioErrorL);
										}
								}
						}
						
					}
					else {
						audio_time.setText(AudioErrorL);
					}
				}
				if (_data.get((int)_position).containsKey("timestamp")) {
					cc.setTimeInMillis((long)(Double.parseDouble(_data.get((int)_position).get("timestamp").toString())));
					date.setText(new SimpleDateFormat("HH:mm").format(cc.getTime()));
				}
				if (_data.get((int)_position).get("message_status").toString().equals("forwarded")) {
					status.setImageResource(R.drawable.ic_done_black);
				}
				else {
					if (_data.get((int)_position).get("message_status").toString().equals("seen")) {
						status.setImageResource(R.drawable.ic_done_all_black);
					}
				}
				if (_data.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					status.setVisibility(View.VISIBLE);
					body.setGravity(Gravity.TOP | Gravity.RIGHT);
					message_mn.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
					date_time_seen.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
					_ImageColor(play_pause, "#FFFFFF");
					_ImageColor(status, "#FFFFFF");
					audio_time.setTextColor(0xFFFFFFFF);
					message_text.setTextColor(0xFFFFFFFF);
					date.setTextColor(0xFFFFFFFF);
					reply_wt_box.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)12, 0xFFEEEEEE));
					reply_in_username.setTextColor(0xFFFFFFFF);
					reply_in_message.setTextColor(0xFFFFFFFF);
					format_file.setTextColor(0xFFFFFFFF);
					tx_file_name.setTextColor(0xFFFFFFFF);
					_ImageColor(download, "#FFFFFF");
					_AdvancedView(message_layout, "#FFAB91", "#F4511E", "#F4511E", 20, 10, "#FFAB91", 10);
				}
				else {
					status.setVisibility(View.GONE);
					body.setGravity(Gravity.TOP | Gravity.LEFT);
					message_mn.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
					date_time_seen.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
					_ImageColor(play_pause, "#000000");
					audio_time.setTextColor(0xFF000000);
					message_text.setTextColor(0xFF000000);
					date.setTextColor(0xFF000000);
					reply_in_message.setTextColor(0xFF000000);
					linear_file.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)12, 0xFFE0E0E0));
					format_file.setTextColor(0xFF000000);
					tx_file_name.setTextColor(0xFF000000);
					_ImageColor(download, "#000000");
					reply_wt_box.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)12, 0xFFF4511E));
				}
			}
			
			return _view;
		}
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