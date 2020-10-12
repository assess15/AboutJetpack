## Android Jetpack 组件

![jetpack](./png/android-jetpack.jpg)



| Foundation        | Architecture | Behavior         | UI                      |
| ----------------- | ------------ | ---------------- | ----------------------- |
| Android KTX       | DataBinding  | CameraX          | Animation & transitions |
| AppCompat         | Lifecycles   | Media & playback | Emoji                   |
| Car               | LiveData     | Notifications    | Fragment                |
| Benchmark         | Navigation   | Permissions      | Layout                  |
| MultiDex          | Paging       | Preferences      | Palette                 |
| Security          | Room         | Sharing          | ViewPager2              |
| Test              | ViewModel    | Slices           | WebView                 |
| TV                | WorkManager  | -                | -                       |
| Wear OS by Google | -            | -                | -                       |



```
版本说明:
- alpha:内测版(代码改动频繁)
- beta :公测版(代码改动较小)
- rc   :候选版本(和发布版本一致)
- stable:稳定版本
```



---

### 架构

#### Lifecycle

![Lifecycle](./png/lifecycle-states.png)

---

#### Room

[Room总结](./arch_room/Room.md)

![Room架构](./png/room_architecture.png)

---

#### Paging3

[Paging3总结](./arch_paging3/Paging3.md)

![Paging3架构](./png/paging3-library-architecture.png)

![data-flow](./png/paging-library-data-flow.webp)

---

#### [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

[Hilt总结](./arch_hilt/Hilt.md)




---

#### LiveData

[LiveData总结](./arch_livedata/LiveData.md)



---

#### ViewModel

[ViewModel总结](./arch_viewmodel/ViewModel.md)



---

### WorkManager

[codelab](https://codelabs.developers.google.com/codelabs/android-workmanager/#0)

---



---

### 界面

#### ViewPager2

[ViewPager2总结](./ui_viewpager2/ViewPager2.md)

---

#### MotionLayout

[MotionLayout2总结](./ui_motionlayout/MotionLayout.md)

---

#### AutoFill

[codelab](https://codelabs.developers.google.com/codelabs/optimize-autofill/#0)

---



### Kotlin

#### [Koin](https://insert-koin.io/)

#### [Koin总结](./kotlin_koin/Koin.md)



---

### 其他库

#### [1、Stetho](http://facebook.github.io/stetho/)

Download

```Groovy
api  'com.facebook.stetho:stetho:1.5.1'
api  'com.facebook.stetho:stetho-okhttp3:1.5.1'
```

Setup

```Kotlin
Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(
                    Stetho.defaultDumperPluginsProvider(this)
                )
                .enableWebKitInspector(
                    Stetho.defaultInspectorModulesProvider(this)
                )
                .build()
        )
```

Network

```Kotlin
OkHttpClient.Builder()
    .addNetworkInterceptor(new StethoInterceptor())
    .build()
```

---