## 前言
>Brocast Receiver:广播接收者——Android系统自带的广播组件。  
EventBus:一个发布 / 订阅的事件总线。简单的说：就是两人约定好怎么通信，一人发布消息，另外一个约定好的人立马接收到你发的消息。EventBus可以帮减少很多事，不管你在任何地方任何位置发布一个事件，接收者都能立马接收到你的消息，不用你考虑android子线程操作UI线程的问题。

## EventBus
EventBus是GreenRobot的一个开源库。[EventBus](http://greenrobot.org/eventbus/)官网上是这么介绍EventBus的：  
>EventBus是一个开源库，用来让Android开发中订阅者/接收者降低耦合度的。EventBus使中央通信解耦类只有几行代码,简化代码,移除依赖,和加速应用程序开发。

![image](https://github.com/wjbd/ScreenShot/raw/master/EventBusDemo/EventBus-Publish-Subscribe.png)  
### EventBus的优点
- 简化了组件之间的通信
- 解耦事件发送者和接受者
- 在Activty，Fragment，和Service中表现良好
- 避免了复杂且容易出错的依赖关系和生命周期问题
- 快，专为高性能优化
- 很小（<50K Jar）

### EventBus的特点
- 基于注解的API：简答的使用只要把@Subscribe注解到方法上就可以。
- 可以在Android的主线程使用。
- 可以在Service中使用。
- EventBus的订阅者可以被继承订阅。
- 不需要任何初始化等配置。
- EventBus可以使用Builder模式构建你需要的EventBus。
### EventBus的使用
EventBus已经上传到JCenter仓库和Maven仓库，所以只需添加依赖关系到项目。
```
compile 'org.greenrobot:eventbus:3.0.0'//版本号是发文前最高版本号
```
### 为什么使用EventBus而不使用BrocastReceiver
广播作为Android组件间的通信方式，可以使用的场景如下：  
- 同一app内部的同一组件内的消息通信(单个或多个线程之间);  
- 同一app内部的不同组件之间的消息通信（单个进程)；    
- 同一app具有多个进程的不同组件之间的消息通信；  
- 不同app之间的组件之间消息通信；  
- Android系统在特定情况下与App之间的消息通信；  
>EventBus与BrocastReceiver两者都是观察者模式。BrocastReceiver能做到的EventBus都能做到。  
但是EventBus是为了同一进程的发送和接收而设计的。  
BrocastReceiver则可以使用在不同的进程中。  

相对之下，EventBus虽然少了进程间的通讯，但是针对同一进程来说，EventBus的使用方便，并且非常简单，在实际开发中使用EventBus能够节省不少的开发时间。

## EventBus的使用demo
### 第一步：定义POJO类
```
public class MessageEvent {
 
    public final String message;
 
    public MessageEvent(String message) {
        this.message = message;
    }
}
```
### 第二步:准备接收者
```
// 接收到的信息将回调到主线程
@Subscribe(threadMode = ThreadMode.MAIN)
public void onMessageEvent(MessageEvent event) {
    Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
}
 
// 这个方法只能接收到SomeOtherEvent类型的信息
@Subscribe
public void handleSomethingElse(SomeOtherEvent event) {
    doSomethingWith(event);
}
```
接收者需要注册和注销，通常是在Android中Activity，Fragemnt的生命周期里面进行注册
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
### 第三步：发送事件  
从代码的任何部分发送事件，所有注册的接收者，匹配到相同类型以后就可以得到它。
```
EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
```


#### 参考文献
- [EventBus官网](http://greenrobot.org/eventbus/)
- [Android的BroadcastReceiver和EventBus区别是什么](http://www.ituring.com.cn/article/198897?utm_source=tuicool)
- [BroadcastReciver和EventBus区别](http://www.jianshu.com/p/9bb5033840bc)
