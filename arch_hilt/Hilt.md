## Jetpack Hilt 上手

>  Dagger Hilt (依赖注入) , Jetpack , 协程(Kotlin)

[资料参考](https://juejin.im/post/6845166891325997069)

[资料参考](https://juejin.im/post/6844904191920439303#heading-11)

### 1、 添加依赖

**build.gradle(:app)**

```groovy
apply plugin: 'kotlin-kapt'
apply plugin: 'dagger.hilt.android.plugin'
// for hilt
kapt {
    correctErrorTypes true
}
hilt {
    enableTransformForLocalTests = true
}
android {
    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation 'com.google.dagger:hilt-android:2.28-alpha'
    kapt 'com.google.dagger:hilt-android-compiler:2.28-alpha'

    def activity_version = "1.1.0"
    def fragment_version = "1.2.5"
    implementation "androidx.activity:activity-ktx:$activity_version"
    implementation "androidx.fragment:fragment-ktx:$fragment_version"

    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-savedstate:2.2.0'

    // hilt
    implementation 'androidx.hilt:hilt-common:1.0.0-SNAPSHOT'
    implementation 'androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-SNAPSHOT'
    kapt 'androidx.hilt:hilt-compiler:1.0.0-SNAPSHOT'

    // Networking
    implementation "com.squareup.retrofit2:converter-moshi:2.6.2"
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.okhttp3:okhttp:4.7.2"
    implementation "com.squareup.okhttp3:logging-interceptor:4.7.2"

    //Coroutine
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7"

    //
    implementation "androidx.recyclerview:recyclerview:1.1.0"
    implementation 'android.arch.lifecycle:extensions:1.1.1'
    implementation 'com.github.bumptech.glide:glide:4.11.0'
}
```

**build.gradle**

```Groovy
buildscript {
    dependencies {
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.28-alpha'
    }
}

allprojects {
    repositories {
        maven { url "https://androidx.dev/snapshots/builds/6543454/artifacts/repository/" }
    }
}
```

---

### 2、 注解含义

#### ① @HiltAndroidApp

> 使用在**Application**中,进行初始化Hilt

#### ② @AndroidEntryPoint

> 使用在**Activity、Fragment、View、Service、BroadcastReceiver**中
>
> **注意:**
>
> Activity：仅仅支持 ComponentActivity 的子类例如 FragmentActivity、AppCompatActivity 等等。
>
> Fragment：仅仅支持继承 androidx.Fragment 的 Fragment

**这里遇到的坑**

> - 如果使用 @AndroidEntryPoint 在非 ComponentActivity 子类上注解，例如 Activity 则会抛出以下异常。
>
>   ```Java
>   Activities annotated with @AndroidEntryPoint must be a subclass of androidx.activity.ComponentActivity. (e.g. FragmentActivity, AppCompatActivity, etc.)
>   ```
>
>   - **解决**: **使用AppCompatActivity,避免直接使用Activity**
>
> - 如果使用 @AndroidEntryPoint 注解 Android 类，必须在它依赖的 Android 类添加同样的注解，例如在 **Fragment 中添加 @AndroidEntryPoint 注解，必须在 Fragment 依赖的 Activity 上也添加 @AndroidEntryPoint 注解** , 否则会抛出以下异常。
>
>   ```Java
>   java.lang.IllegalStateException: Hilt Fragments must be attached to an @AndroidEntryPoint Activity. Found: class com.hi.dhl.hilt.MainActivity
>   ```
>
>

#### ③ @Inject

> 使用 @Inject 注解来告诉 Hilt 如何提供该类的实例，它常用于**构造函数、非私有字段、方法中。**

#### ④ @Module

> module的使用基本和dagger一样, 用来提供一些无法用构造`@Inject`的依赖, 比如接口, 第三方库类型, Builder模式构造的对象等.使用 @Module 注解的类，需要使用 @InstallIn 注解指定 module 的范围。

#### ⑤ @InstallIn

> 使用 @Module 注入的类，需要使用 @InstallIn 注解指定 module 的范围，例如使用 @InstallIn(ActivityComponent::class) 注解的 module 会绑定到 activity 的生命周期上。
>
> Hilt 提供了以下组件来绑定依赖与 对应的 Android 类的活动范围。
>
> | **Hilt 提供的组件**       | **对应的 Android 类的活动范围**           |
> | ------------------------- | ----------------------------------------- |
> | ApplicationComponent      | Application                               |
> | ActivityRetainedComponent | ViewModel                                 |
> | ActivityComponent         | Activity                                  |
> | FragmentComponent         | Fragment                                  |
> | ViewComponent             | View                                      |
> | ViewWithFragmentComponent | View annotated with @WithFragmentBindings |
> | ServiceComponent          | Service                                   |
>
> **注意：Hilt 没有为 broadcast receivers 提供组件，因为 Hilt 直接从 ApplicationComponent 注入 broadcast receivers。**

#### ⑥ @Provides

> 常用于被 @Module 注解标记类的内部的方法，并提供依赖项对象。
>
> 修饰的方法返回值不能为void

#### ⑦ @ViewModelInject

> 使用在ViewModel中

#### Hilt中组件的生命周期：

| Hilt提供的组件            | 创建对应的生命周期     | 结束对应的生命周期      | 作用范围               |
| ------------------------- | ---------------------- | ----------------------- | ---------------------- |
| ApplicationComponent      | Application#onCreate() | Application#onDestroy() | @Singleton             |
| ActivityRetainedComponent | Activity#onCreate()    | Activity#onDestroy()    | @ActivityRetainedScope |
| ActivityComponent         | Activity#onCreate()    | Activity#onDestroy()    | @ActivityScoped        |
| FragmentComponent         | Fragment#onAttach()    | Fragment#onDestroy()    | @FragmentScoped        |
| ViewComponent             | View#super()           | View destroyed          | @ViewScoped            |
| ViewWithFragmentComponent | View#super()           | View destroyed          | @ViewScoped            |
| ServiceComponent          | Service#onCreate()     | Service#onDestory()     | @ViewScoped            |

---

### 3、Hilt 使用

#### 简单使用

```Kotlin
class HiltTest @Inject constructor() {
    fun hiltTest() { Log.e("----------->", "hiltTest: ") }
}
```

```Kotlin
@HiltAndroidApp
class BaseApplication : Application() {
    // 通过@Inject注入到BaseApplication中
    @Inject
    lateinit var hiltTest: HiltTest
    override fun onCreate() {
        super.onCreate()
        hiltTest.hiltTest()
    }
}
```

```Kotlin
@AndroidEntryPoint
class HomeNavigationActivity : BaseLayoutActivity<TestViewModel>() {
    override fun setViewModel(): Class<TestViewModel> =TestViewModel::class.java
    override fun layout(): Int {
        return R.layout.home_navigation
    }
    override fun bindView() { }
}
```

```Kotlin
// fragment 中使用，需要本身所依赖的 activity 添加注解
@AndroidEntryPoint
class FragmentOne : BaseLayoutFragment<FragOneViewModel>() {
    //使用 @Inject 从组件中获取依赖进行注入
    @Inject
    lateinit var hiltTest: HiltTest
    override fun layout(): Int {
        return R.layout.frag_one
    }
    override fun bindView(rootView: View) {
        //对象已经注入，直接调用即可
        one.text = hiltTest.hiltTest()
    }
}
```

#### 在第三方组件的使用

```Kotlin
//对应的生命周期为 application
@Module
@InstallIn(ApplicationComponent::class)
object TestModule {
    /** 每次都是新的实例 **/
    @Provides
    fun bindHiltTest(): HiltTest {
        Log.e("--------bindHiltTest----")
        return HiltTest()
    }
    /** 全局复用同一个实例 **/
    @Provides
    @Singleton
    fun bindSingTest(): Test {
        Log.e("--------bindSingTest----")
        return Test()
    }
}

```

```Kotlin
@AndroidEntryPoint
class TestActivity :AppCompactActivity() {
    @Inject
    lateinit var hiltTest: HiltTest
    @Inject
    lateinit var hiltTest1: HiltTest
    @Inject
    lateinit var test1: Test
    @Inject
    lateinit var test2: Test
}
```

#### 在ViewModel 的使用

```Kotlin
class HomeContentViewModel @ViewModelInject  constructor(
    private val response: HomeContentRepository,
    @Assisted val  state: SavedStateHandle
) : ViewModel() {
    private val liveData by lazy { MutableLiveData<String>() }
    val testLiveData: LiveData<String> by lazy { liveData }
    fun requestBaiDu() {
        launchVmHttp {
            liveData.postValue(response.requestBaidu())
        }
    }
}
```

```Kotlin
@ActivityScoped
class HomeContentRepository @Inject constructor() : BaseRepository() {
    suspend fun requestBaidu(): String {
        return LvHttp.createApi(ApiServices::class.java).baidu()
    }
}
```

```Kotlin
@AndroidEntryPoint
class HomeContentActivity : AppCompatActivity() {
    //生成 ViewModel 的实例
    private val viewModel by viewModels<HomeContentViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_content)
        viewModel.requestBaiDu()
        viewModel.testLiveData.observe(this, Observer {
            ToastUtils.show(it)
        })
}

```

#### 在 Room 的使用

```Kotlin
@Module
@InstallIn(ApplicationComponent::class)
object RoomModel {
    /**
     * @Provides：常用于被 @Module 标记类的内部方法，并提供依赖对象
     * @Singleton：提供单例
     */
    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): AppDataBase {
        return Room
            .databaseBuilder(application, AppDataBase::class.java, "knif.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    @Provides
    @Singleton
    fun providerUserDao(appDataBase: AppDataBase): UserDao {
        return appDataBase.getUserDao()
    }
}
```

```Kotlin
class FragmentTwo : BaseLayoutFragment<FragTwoViewModel>() {
    @Inject
    lateinit var userDao: UserDao
}
```

#### @Binds 进行接口注入

> Binds：必须注释一个抽象函数，抽象函数的返回值是实现的接口。通过添加具有接口实现类型的唯一参数来指定实现。

```Kotlin
interface User {
    fun getName(): String
}
```

```Kotlin
@Module
@InstallIn(ApplicationComponent::class)
abstract class UserModule {
    @Binds
    abstract fun getUser(userImpl: UserImpl): User
}
```

```Kotlin
@Module
@InstallIn(ApplicationComponent::class)
abstract class UserModule {
    @Binds
    abstract fun getUser(userImpl: UserImpl): User
}
```

```Kotlin
@AndroidEntryPoint
class FragmentOne : BaseLayoutFragment<FragOneViewModel>() {
    @Inject
    lateinit var user: User
}
```

#### @Qualifier 提供同一接口，不同的实现，解决注解迷失问题

```Kotlin
class UserAImpl @Inject constructor() : User {
    override fun getName(): String {
        return "345"
    }
}
```

```Kotlin
class UserBImpl @Inject constructor() : User {
    override fun getName(): String {
        return "Lv"
    }
}
```

```Kotlin
@Qualifier
annotation class A
@Qualifier
annotation class B
```

```Kotlin
@Module
@InstallIn(ApplicationComponent::class)
abstract class UserAModule {
    @A
    @Singleton
    @Binds
    abstract fun getUserA(userImpl: UserAImpl): User
}
@Module
@InstallIn(ActivityComponent::class)
abstract class UserBModule {
    @B
    @ActivityScoped
    @Binds
    abstract fun getUserB(userImpl: UserBImpl): User
}
```

```Kotlin
@AndroidEntryPoint
class FragmentOne : BaseLayoutFragment<FragOneViewModel>() {
    @A
    @Inject
    lateinit var userA: User
    @B
    @Inject
    lateinit var userB: User
}
```

