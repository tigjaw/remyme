package my.sms.remyme;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

/*
 * THIS CLASS COULD JUST BE A REGULAR JAVA CLASS.
 */
public class TtsActivity extends Activity {
	/** Called when the activity is first created. */
	private static TextToSpeech myTts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		myTts = new TextToSpeech(this, ttsInitListener);
	}

	private TextToSpeech.OnInitListener ttsInitListener = new TextToSpeech.OnInitListener() {
		public void onInit(int version) {
			// myTts.speak(""+o, 0, null);
		}
	};

	public static void speakSMS(String sms) {
		myTts.speak(sms, TextToSpeech.QUEUE_ADD, null);
		// myTts.synthesizeToFile(sms,null, "/sdcard/myappsounds/mysms.wav");
	}

}