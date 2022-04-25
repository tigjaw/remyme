package my.sms.remyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
	public final static String DISPLAY_MESSAGE = "ReMyMe.SmsReceiver.MESSAGE";
    @Override
    public void onReceive(Context context, Intent intent)
    {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";            
        if (bundle != null) {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                str += "remyme says - Message from " + msgs[i].getOriginatingAddress();
                str += " : ";
                str += msgs[i].getMessageBody().toString();
                str += "\n";
            }
            //---display the new SMS message---
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    		try {
				// Text to Speech
				MainActivity.speakSMS(str);
				/*
				 Intent intentTts = new Intent();
				 intentTts.putExtra(DISPLAY_MESSAGE, str);
				 context.startActivity(intentTts);
				 */
			} catch (Exception e) {//do nothing
				e.printStackTrace();
			}
        }                         
    }

}// End of class SmsReceiver