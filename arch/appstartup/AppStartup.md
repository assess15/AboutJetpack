## App Startup

[参考](https://juejin.im/post/6844904198392250382)

![](../png/arch_startup.png)

App Startup提供了一个ContentProvider来完成项目需要的一些组件的初始化，
避免每个第三方的库(比如友盟统计、埋点等)单独通过ContentProvider进行初始化。

将所有用于初始化的ContentProvider合并成一个，从而使App的启动速度变得更快。

---

**Download**

```kotlin
api "androidx.startup:startup-runtime:1.0.0-beta01"
```

- 自动初始化

  ```Kotlin
  class AppInitializer : Initializer<Unit> {
  
      override fun create(context: Context) {
          if (!BuildConfig.DEBUG) {
              return
          }
          StrictMode.setThreadPolicy(
              StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build()
          )
          StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
          Timber.plant(Timber.DebugTree())
          return
      }
  
      override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
  }
  ```

  ```xml
  <application >
      <provider
          android:name="androidx.startup.InitializationProvider"
          android:authorities="${applicationId}.androidx-startup"
          android:exported="false">
  		<meta-data
           	android:name="com.laychv.arch_startup.AppInitializer"
              android:value="androidx.startup" />
      </provider>
  </application>
  ```

  

- 手动初始化

```xml
<application >
	<provider
        android:name="androidx.startup.InitializationProvider"
        android:authorities="${applicationId}.androidx-startup"
        android:exported="false"
        tools:node="merge">
        
            <meta-data
                android:name="com.laychv.arch_startup. "
                tools:node="remove" />
	</provider>
</application>
```

手动初始化:

通过`tools:node="remove"`先移除, 再手动添加,需要延迟初始化的代码

```Kotlin
AppInitializer.getInstance(this)
.initializeComponent(ExampleLoggerInitializer::class.java)
```

`tools:node="merge"`属性是为了确保清单合并工具可能造成的冲突问题

`tools:node="remove"`属性是为了确保清单合并工具能够移除所有合并文件的这个标签

