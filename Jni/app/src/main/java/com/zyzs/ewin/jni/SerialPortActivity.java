package com.zyzs.ewin.jni;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zyzs.ewin.jni.serialport.SerialPortUtil;

/*
 * @author guoxiao
 * 
 */
public class SerialPortActivity extends Activity {

    private static final String TAG = "SerialPortActivity";

	SerialPortUtil mSerialPortUtil;

    private Button btn_send_message,btn_receive_message;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_port);
        //单模式
        mSerialPortUtil = SerialPortUtil.getInstance();

        initView();
        addEventListener();

    }

    private void initView(){
        btn_send_message = (Button) findViewById(R.id.btn_send_message);
        btn_receive_message = (Button) findViewById(R.id.btn_receive_message);
    }

    private void addEventListener(){
        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSerialPortUtil.sendCmds("send");
            }
        });

        mSerialPortUtil.setOnDataReceiveListener(new SerialPortUtil.OnDataReceiveListener() {

            @Override
            public void onDataReceive(byte[] buffer, int size) {
                // TODO Auto-generated method stub
                Log.d(TAG, " DataReceive:" + new String(buffer,0,size));
            }
        });
    }
    
}
