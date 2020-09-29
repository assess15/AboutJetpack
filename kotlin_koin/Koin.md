## Koin 依赖注入

适用于 Kotlin 开发人员的实用轻量级依赖注入框架。 用纯 Kotlin DSL编写，仅使用功能分辨率：无代理，无代码生成，无反射。

[koin](https://insert-koin.io/)
[android](https://start.insert-koin.io/#/getting-started/koin-for-android)
[github](https://github.com/InsertKoinIO/koin)

---

### Dependencies
Pick one of your Koin dependency:

Gradle Plugin
```Gradle
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath "org.koin:koin-gradle-plugin:$koin_version"
    }
}
```

```Gradle
apply plugin: 'koin'
```

Core features
```Gradle
// Koin for Kotlin
implementation "org.koin:koin-core:$koin_version"
// Koin extended & experimental features
implementation "org.koin:koin-core-ext:$koin_version"
// Koin for Unit tests
testImplementation "org.koin:koin-test:$koin_version"
// Koin for Java developers is now part of core
// implementation "org.koin:koin-java:$koin_version"
```

Android
```Gradle
// Koin for Android
implementation "org.koin:koin-android:$koin_version"
// Koin Android Scope features
implementation "org.koin:koin-android-scope:$koin_version"
// Koin Android ViewModel features
implementation "org.koin:koin-android-viewmodel:$koin_version"
// Koin Android Experimental features
implementation "org.koin:koin-android-ext:$koin_version"
```

AndroidX
```Gradle
// Koin AndroidX Scope features
implementation "org.koin:koin-androidx-scope:$koin_version"
// Koin AndroidX ViewModel features
implementation "org.koin:koin-androidx-viewmodel:$koin_version"
// Koin AndroidX Fragment features
implementation "org.koin:koin-androidx-fragment:$koin_version"
// Koin AndroidX WorkManager
implementation "org.koin:koin-androidx-workmanager:$koin_version"
// Koin AndroidX Jetpack COmpose
implementation "org.koin:koin-androidx-compose:$koin_version"
// Koin AndroidX Experimental features
implementation "org.koin:koin-androidx-ext:$koin_version"
```

Ktor
```Gradle
// Koin for Ktor Kotlin
implementation "org.koin:koin-ktor:$koin_version"
```

---

### Starting Koin
In a module
```Gradle
fun main(vararg args : String) {
  // start Koin!
  startKoin {
    // your modules
    modules(myModule)
  }
}
```

In Application
```Gradle
class MyApplication : Application() {
  override fun onCreate(){
    super.onCreate()
    // start Koin!
    startKoin {
      // Android context
      androidContext(this@MyApplication)
      // modules
      modules(myModule)
    }
  }
}
```

### 关键字

**module**

**factory**

**scope**

**get**
