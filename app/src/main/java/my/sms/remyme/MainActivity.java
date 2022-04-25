package my.sms.remyme;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener {
	private ToggleButton tb;
	private final BroadcastReceiver receiver = new SmsReceiver();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tb = (ToggleButton) findViewById(R.id.toggleButton1);
		tb.setOnClickListener(this);
		myTts = new TextToSpeech(this, ttsInitListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if ((tb.isChecked())) {
			System.out.println("checked");
			IntentFilter filter = new IntentFilter();
			filter.addAction("android.provider.Telephony.SMS_RECEIVED");
			registerReceiver(receiver, filter);
		} else {
			System.out.println("unchecked");
			/*
			 PackageManager pm = getPackageManager();
			 pm.setComponentEnabledSetting(new ComponentName( "my.sms.remyme",
			 ".SmsReceiver"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
			 // or ENABLED PackageManager.DONT_KILL_APP);
			 */
			unregisterReceiver(receiver);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/** Called when the activity is first created. */
	private static TextToSpeech myTts;
	private TextToSpeech.OnInitListener ttsInitListener = new TextToSpeech.OnInitListener() {
		public void onInit(int version) {
			// myTts.speak(""+o, 0, null);
		}
	};

	public static void speakSMS(String sms) {
		myTts.speak(sms, 0, null);
		// myTts.synthesizeToFile(sms,null, "/sdcard/myappsounds/mysms.wav");
	}

}