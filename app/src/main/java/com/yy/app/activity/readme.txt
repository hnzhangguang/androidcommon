


// xml横竖屏
   android:screenOrientation="portrait" 始终以竖屏显示
   android:screenOrientation="landscape" 始终以横屏显示
// 代码实现横竖屏
 Activity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);以竖屏显示

//什么时候Activity不执行onDestory()?
栈里面的第一个没有销毁的activity会执行ondestroy方法，其他的不会执行。
比如说：从mainactivity跳转到activity-A（或者继续从activity-A再跳转到activity-B），这时候，从后台强杀，只会执行mainactivity的onDestroy方法，activity-A（以及activity-B）的onDestroy方法都不会执行；

//进程的优先级
  前台>可见>服务>后台>空

  前台：与当前用户正在交互的Activity所在的进程。
  可见：Activity可见但是没有在前台所在的进程。
  服务：Activity在后台开启了Service服务所在的进程。
  后台：Activity完全处于后台所在的进程。
  空：没有任何Activity存在的进程，优先级也是最低的。

//Activity的启动模式有哪些？特性如何
  1, startand
     注意:ApplicationContext 去启动standard模式的Activity就会报错，这是因为standard模式的Actiivty默认会进入启动它的Activity所属的任务栈中，但是由于非Activity类型的Context(如ApplicationContext)并没有所谓的任务栈，所以这就会出现错误
  2,栈顶复用模式：SingleTop
  3,栈内复用模式：SingleTask
  4,单实例模式：SingleInstance

注意：
前台任务栈：就是指和用户正在交互的应用程序所在的任务栈。
后台任务栈：就是指处于后台的应用程序所在的任务栈。














