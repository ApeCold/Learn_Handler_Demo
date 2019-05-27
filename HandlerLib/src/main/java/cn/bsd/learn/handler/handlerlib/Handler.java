package cn.bsd.learn.handler.handlerlib;

public class Handler {
    private MessageQueue mQueue;
    private Looper mLooper;
    public Handler(){
        //获取主线程Looper对象
        mLooper = Looper.myLooper();
        this.mQueue = mLooper.mQueue;
    }
    /**
     * 发送消息，压入队列
     */
    public void sendMessage(Message msg){
        msg.target=this;//将Handler绑定到msg的Target
        mQueue.enqueueMessage(msg);
    }

    /**
     * Subclasses must implement this to receive messages.
     */
    public void handleMessage(Message msg) {
    }

    public void dispatchMessage(Message msg){
        handleMessage(msg);
    }
}
