## EventBus

![image](https://github.com/wjbd/ScreenShot/raw/master/EventBusDemo/EventBus-Publish-Subscribe.png)  
```
```


```
public class MessageEvent {
 
    public final String message;
 
    public MessageEvent(String message) {
        this.message = message;
    }
}
```
```
@Subscribe(threadMode = ThreadMode.MAIN)
public void onMessageEvent(MessageEvent event) {
    Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
}
 
@Subscribe
public void handleSomethingElse(SomeOtherEvent event) {
    doSomethingWith(event);
}
```
```
@Override
public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
}
 
@Override
public void onStop() {
    EventBus.getDefault().unregister(this);
    super.onStop();
}
```
```
EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
```

