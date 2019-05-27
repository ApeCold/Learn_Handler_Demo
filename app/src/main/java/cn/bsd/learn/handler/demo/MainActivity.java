package cn.bsd.learn.handler.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //处理返回的消息
            System.out.println(msg.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(){
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what=1;
                msg.arg1=666;
                mHandler.sendMessage(msg);
            }
        }.start();
    }
}
