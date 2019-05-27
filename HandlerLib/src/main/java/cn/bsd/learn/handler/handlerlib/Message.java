package cn.bsd.learn.handler.handlerlib;

public class Message {
    Handler target;
    public int what;
    public Object obj;

    @Override
    public String toString() {
        return obj.toString();
    }
}
