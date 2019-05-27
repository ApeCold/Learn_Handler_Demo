package cn.bsd.learn.handler.handlerlib;

public final class Looper {
    /**
     * 每一个主线程都会有一个Looper对象
     * Looper对象保存在ThreadLocal，保证子线程数据隔离
     */
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    //有一个对应消息队列
    MessageQueue mQueue;

    private Looper(){
        mQueue = new MessageQueue();
    }

    /**
     * Looper对象初始化
     */
    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * 获取当前Looper对象
     * @return
     */
    public static Looper myLooper(){
        return sThreadLocal.get();
    }

    /**
     * 轮询消息队列
     */
    public static void loop(){
        Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        MessageQueue queue = me.mQueue;
        for (;;){
            Message msg= queue.next();
            if(msg==null){
                continue;
            }
            //转发给Handler
            msg.target.dispatchMessage(msg);
        }
    }
}
