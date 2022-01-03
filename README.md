# LearnSunflower
学习 Google 官方教程 sunflower 时的一些笔记及对项目的理解和重构



## 学习笔记

### Java 相关

@Injection

### Kotlin 相关

### MVVM 相关

### Android 基础

**1、 Manifest**

`<activity>` 标签下的 `<intent-filter>` 标签的作用

[intent-filter](https://developer.android.com/guide/topics/manifest/intent-filter-element)  对当前 activity 能接受的 `intent` 进行说明和描述，方便 `启动该 activity 的组件` (parent component) 进行挑选和执行相关的操作

`<action>`标签表明了调用了当前的 intent 之后要做的事情，如：[ACTION_MAIN](https://developer.android.com/reference/android/content/Intent#ACTION_MAIN) 用来表明当前 Activity 是整个 APP 的入口；[ACTION_VIEW](https://developer.android.com/reference/android/content/Intent#ACTION_VIEW) 用来表明调用该 Activity 会展示一些信息

`<category>` 标签用来标明 intent 的一些附加信息，绝大多数的 intent 都可以不需要 category 标签，常见的 category 有[这些](https://developer.android.com/guide/components/intents-filters)：[CATEGORY_BROWSABLE](https://developer.android.com/reference/android/content/Intent#CATEGORY_BROWSABLE) 和 [CATEGORY_LAUNCHER](https://developer.android.com/reference/android/content/Intent#CATEGORY_LAUNCHER)

**2、Android 动画**

a. 动画资源的位置：`res/anim/*filename*.xml`

动画 `<translate>` 标签的 [作用](https://developer.android.com/guide/topics/resources/animation-resource#View) ：用来表示一次动画在位置上的转换，fromXDelta 表示开始时的 x 坐标，toXDelta 表示结束时的 x 坐标。可以使用百分比表示，如 x 轴上的 -100% 表示窗口完全不显示且在左边进行隐藏，0% 表示正常显示，100% 表示窗口隐藏在右边。

b. res/animator 下的动画

**3、Activity 和 Fragment 的区别和联系**

看了这个[回答](https://stackoverflow.com/questions/25822656/what-are-the-differences-between-activity-and-fragment)，暂且有如下的理解：

a. Fragment 包含在 Activity 中，并且 Fragment 可以复用，Activity 不可以复用，Fragment 可以作为一个界面的最小单位

b. Fragment 和 Activity 具有不同的生命周期

**4、 Demo 中的 fragment 用到的组件分别有什么用？**

**5、/values 下各个文件的作用**

### Android 架构

#### Navigation

第一次看到 navigation，让我发觉世界是如此的清晰，从一个界面到另一个界面，都有可视化的图表进行展示，比之前使用 intent 进行跳转要方便多了，简直让人爱不释手。

> 按照官方的[教程](https://developer.android.com/jetpack/androidx/releases/navigation)进行配置的话，应该不会有什么大问题

Navigation 需要使用 FragmentContainerView 来承载，再在 `<navigation>` 里面定义一个又一个的 `<fragment>` 来实现跳转逻辑

#### DataBinding

实现 layout 和 代码的数据绑定，使用时，在 build.gradle(:app) 中添加：

``` groovy
android {
	buildFeatures {
        dataBinding true
        viewBinding true
    }
}
```

在 xml 中使用 `<layout>` 作为根节点，其中可以加入` <data>` 标签来操作数据

`<data>` 标签中的 `<import>` 标签可以用来导包，供` <variable>` 标签使用

`<variable>` 定义的变量可以直接在相应的 fragment 和当前的 xml 下访问到



## REFERENCE

[sunflower_source_code](https://github.com/android/sunflower)
