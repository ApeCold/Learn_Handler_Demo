package cn.bsd.learn.handler.handlerlib;

import java.util.UUID;

public class HandlerTest {
    public static void main(String[] args){
        //轮询器初始化
        Looper.prepare();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(Thread.currentThread().getName()+",received:"+msg.toString());
            }
        };

        //子线程发送消息
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        Message msg = new Message();
                        msg.what=1;
                        synchronized (UUID.class){
                            msg.obj=Thread.currentThread().getName()+",Send Message:"+ UUID.randomUUID().toString();
                        }
                        System.out.println(msg);
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        Looper.loop();
    }
}
