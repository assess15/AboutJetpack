## MotionLayout

[引用](https://juejin.im/post/6844903889288822791)

> 使用 MotionLayout 管理运动和微件动画

[codelab](https://codelabs.developers.google.com/codelabs/motion-layout/index.html?index=..%2F..index#0)

### 上手指南

**1、添加依赖**

```Groovy
    implementation 'androidx.constraintlayout:constraintlayout:2.0.0-rc1'
```

**2、创建MotionLayout 文件**

MotionLayout 是 ConstraintLayout 的子类，因此您可以通过替换布局资源文件中的类名称，将任何现有的 ConstraintLayout 转换为 MotionLayout

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.motion.widget.MotionLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/motionLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layoutDescription="@xml/motionlayout_motion_scene">

    <Button
        android:id="@+id/button"
        android:layout_width="56dp"
        android:layout_height="56dp"
        android:background="@color/colorPrimary"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="1.0" />

</androidx.constraint
```

**3、创建MotionScene文件**

在之前的 `MotionLayout` 示例中，`app:layoutDescription` 属性引用一个 MotionScene。MotionScene 是一个 XML 资源文件，其中包含相应布局的所有运动描述。为了将布局信息与运动描述分开，每个 `MotionLayout` 都引用一个单独的 MotionScene。请注意，MotionScene 中的定义优先于 `MotionLayout` 中的任何类似定义。

```xml
<?xml version="1.0" encoding="utf-8"?>
<MotionScene xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <!-- 定义 Start 场景的属性约束集 -->
    <ConstraintSet android:id="@+id/activity_main_start">

        <!-- 定义布局中 id 为 button 的 View 的属性约束 -->
        <Constraint
            android:id="@id/button"
            android:layout_width="56dp"
            android:layout_height="56dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="1.0" />

    </ConstraintSet>

    <!-- 定义 End 场景的属性约束集 -->
    <ConstraintSet android:id="@+id/activity_main_end">

        <!-- 定义布局中 id 为 button 的 View 的属性约束 -->
        <Constraint
            android:id="@+id/button"
            android:layout_width="56dp"
            android:layout_height="56dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.0" />

    </ConstraintSet>

    <!-- Start 场景与 End 场景都是定义在 MotionScene 文件中的约束集 -->
    <Transition
        app:constraintSetEnd="@id/activity_main_end"
        app:constraintSetStart="@id/activity_main_start"
        app:duration="1000">

        <OnClick
            app:clickAction="toggle"
            app:targetId="@id/button" />

    </Transition>

</MotionScene>
```

请注意以下几点：

- `<Transition>` 包含运动的基本定义。
  - `motion:constraintSetStart` 和 `motion:constraintSetEnd` 指的是运动的端点。这些端点在 MotionScene 后面的 `<ConstraintSet>` 元素中定义。
  - `motion:duration` 指定完成运动所需的毫秒数。
- `<OnSwipe>` 可让您通过轻触控制运动。
  - `motion:touchAnchorId` 指的是您可以滑动并拖动的视图。
  - `motion:touchAnchorSide` 表示我们从右侧拖动视图。
  - `motion:dragDirection` 表示拖动的进度方向。例如，`motion:dragDirection="dragRight"` 表示当您向右拖动时，进度会增加。
- `<ConstraintSet>` 是定义描述您的运动的各种限制条件的位置。在此示例中，我们为运动的每个端点定义一个 `ConstraintSet`。这些端点垂直居中（通过 `app:layout_constraintTop_toTopOf="parent"` 和 `app:layout_constraintBottom_toBottomOf="parent"`）。在水平方向上，端点位于屏幕最左侧和最右侧。

---

## 插入的属性

在 MotionScene 文件中，`ConstraintSet` 元素可包含在转换期间插入的其他属性。除了位置和边界之外，`MotionLayout` 还插入以下属性：

- `alpha`
- `visibility`
- `elevation`
- `rotation`、`rotationX`、`rotationY`
- `translationX`、`translationY`、`translationZ`
- `scaleX`、`scaleY`

---

### 自定义属性

在 `<Constraint>` 中，您可以使用 `<CustomAttribute>` 元素为不仅仅与位置相关的属性或 `View` 属性指定转换。

```xml
    <Constraint
        android:id="@+id/button" ...>
        <CustomAttribute
            motion:attributeName="backgroundColor"
            motion:customColorValue="#D81B60"/>
    </Constraint>
```



一个 `<CustomAttribute>` 本身包含两个属性：

- `motion:attributeName` 是必需属性，并且必须与具有 getter 和 setter 方法的对象匹配。getter 和 setter 与特定模式非常匹配。例如，`backgroundColor` 受支持，因为我们的视图具有基本的 `getBackgroundColor()` 和 `setBackgroundColor()` 方法。
- 您必须提供的另一个属性基于值类型。从以下支持的类型中选择：
  - `motion:customColorValue` 适用于颜色
  - `motion:customIntegerValue` 适用于整数
  - `motion:customFloatValue` 适用于浮点值
  - `motion:customStringValue` 适用于字符串
  - `motion:customDimension` 适用于尺寸
  - `motion:customBoolean` 适用于布尔值

请注意，在指定自定义属性时，您必须定义开始和结束 `<ConstraintSet>` 元素中的端点值。