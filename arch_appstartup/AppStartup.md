## App Startup

[引用](https://juejin.im/post/6844904198392250382)

App Startup提供了一个ContentProvider来完成项目需要的一些组件的初始化，
避免每个第三方的库(比如友盟统计、埋点等)单独通过ContentProvider进行初始化。

我的理解是通过App Startup这个组件，
我们可以将所有第三方需要在Application中初始化的一些库都通过ContentProvider来初始化，
有点偏向于将第三方库初始化这个过程进行了封装，大概是这个意思。