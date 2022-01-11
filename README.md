# LearnSunflower
学习 Google 官方教程 sunflower 时的一些笔记及对项目的理解和重构

## 学习笔记

### Java 相关

* **[依赖注入和 @Injection 注解](https://www.vogella.com/tutorials/DependencyInjection/article.html)**

  以依赖的方式(传参)来使用其他对象，而不是使用的时候直接 new 出来，方便测试和维护

  如果用到了 @Injection 注解，则由 Java 通过反射将对象 new 出来

### Kotlin 相关

* **Kotlin 关键字**

  apply：`xxx`.apply{ lambda } 直接返回的是运算后的对象，即返回的是 `xxx`

  let：无返回值的代码块操作

  with && run：最后一行作为返回值的代码块操作

  also：感觉和 apply 很像，咱未发现区别

  [companion object](https://blog.mindorks.com/companion-object-in-kotlin)：用来调用 static 方法
  
  [suspend fun](https://stackoverflow.com/questions/47871868/what-does-the-suspend-function-mean-in-a-kotlin-coroutine)：协程相关，新起线程，可以用来处理同步和异步操作，如：同步新线程的操作
  
  launch：协程相关，用来新起一个线程
  
  [init](https://stackoverflow.com/questions/55356837/what-is-the-difference-between-init-block-and-constructor-in-kotlin)：会在构造函数之前执行

* **Kotlin 符号**

  !! 自己可以确幸不会为 null，如果为 null 的话为抛 NPE
  ?  加在变量声明后面，表示可以接受 null 的赋值

  .? 如果不为 null 的话执行接下来的操作

  ?: 如果左边的为 null，则返回右边的

* **Kotlin 专有库**
  * MutableStateFlow 可变状态流
    * collect 函数，对数据流截取之后进行操作
    * 和 LiveData 的关系 ？

### MVVM 相关

* **常用组件**
  * @HiltViewModel
    * 如果不使用这个标签会怎样

### Android 基础

* **Manifest**

  `<activity>` 标签下的 `<intent-filter>` 标签的作用

  [intent-filter](https://developer.android.com/guide/topics/manifest/intent-filter-element)  对当前 activity 能接受的 `intent` 进行说明和描述，方便 `启动该 activity 的组件` (parent component) 进行挑选和执行相关的操作

  `<action>`标签表明了调用了当前的 intent 之后要做的事情，如：[ACTION_MAIN](https://developer.android.com/reference/android/content/Intent#ACTION_MAIN) 用来表明当前 Activity 是整个 APP 的入口；[ACTION_VIEW](https://developer.android.com/reference/android/content/Intent#ACTION_VIEW) 用来表明调用该 Activity 会展示一些信息

  `<category>` 标签用来标明 intent 的一些附加信息，绝大多数的 intent 都可以不需要 category 标签，常见的 category 有[这些](https://developer.android.com/guide/components/intents-filters)：[CATEGORY_BROWSABLE](https://developer.android.com/reference/android/content/Intent#CATEGORY_BROWSABLE) 和 [CATEGORY_LAUNCHER](https://developer.android.com/reference/android/content/Intent#CATEGORY_LAUNCHER)

* **Android 动画**

  * 动画资源的位置：`res/anim/*filename*.xml`

    动画 `<translate>` 标签的 [作用](https://developer.android.com/guide/topics/resources/animation-resource#View) ：用来表示一次动画在位置上的转换，fromXDelta 表示开始时的 x 坐标，toXDelta 表示结束时的 x 坐标。可以使用百分比表示，如 x 轴上的 -100% 表示窗口完全不显示且在左边进行隐藏，0% 表示正常显示，100% 表示窗口隐藏在右边。

  * res/animator 下的动画

* **Activity 和 Fragment 的区别和联系**

  看了这个[回答](https://stackoverflow.com/questions/25822656/what-are-the-differences-between-activity-and-fragment)，暂且有如下的理解：

  1. Fragment 包含在 Activity 中，并且 Fragment 可以复用，Activity 不可以复用，Fragment 可以作为一个界面的最小单位
  2. Fragment 和 Activity 具有不同的生命周期

* **Demo 中的 xml 用到的组件分别有什么作用？**
  * androidx.coordinatorlayout.widget.CoordinatorLayout
  * com.google.android.material.appbar.AppBarLayout
  * androidx.core.widget.NestedScrollView
  * com.google.android.material.appbar.CollapsingToolbarLayout
  * androidx.viewpager2.widget.ViewPager2
  * com.google.android.material.appbar.MaterialToolbar
  * com.google.android.material.tabs.TabLayout

* **/res 下各个文件的作用**
  
  * menu：存放右上角的菜单栏文件
  * anim：动画资源，仅对 view 对象使用
  * animator：动画资源，可以对所有的对象使用
  * navigation：导航栏
  * values/anim.xml：存放 anim 的值
  * values/dimens.xml：保存尺寸资源
  * values/colors.xml：保存图片资源
  
* **Res/layout/.xml 下的文件标签分别有什么作用**
  * xmlns:android="http://schemas.android.com/apk/res/android"
    * 用于 Android 系统定义的一些属性
    * android:fitsSystemWindows
    * android:layout_marginStarts
    * android:layout_marginEnd
    * android:onClick
  * xmlns:tools="http://schemas.android.com/tools"
    * 为IDE提供相关信息，打包时会将这部分信息过滤
  * xmlns:app="http://schemas.android.com/apk/res-auto"
    * 应用自定义的一些属性
  
* **Android 如何使用 xml 画图标**

  * vector 和 path
  * 

* **Gradle 相关**

  * 在代码中使用 BuildConfig.UNSPLASH_ACCESS_KEY

    需要在 build.gradle(:app) 中进行如下配置：

    ```groovy
    android {
        defaultConfig {
            buildConfigField("String", "UNSPLASH_ACCESS_KEY", "\"" + getUnsplashAccess() + "\"")
        }
    }
    
    def getUnsplashAccess() {
        return project.findProperty("unsplash_access_key")
    }
    ```


### Android 架构

* **Navigation**

  第一次看到 navigation，让我发觉世界是如此的清晰，从一个界面到另一个界面，都有可视化的图表进行展示，比之前使用 intent 进行跳转要方便多了，简直让人爱不释手。

  > 按照官方的[教程](https://developer.android.com/jetpack/androidx/releases/navigation)进行配置的话，应该不会有什么大问题

  Navigation 需要使用 FragmentContainerView 来承载，再在 `<navigation>` 里面定义一个又一个的 `<fragment>` 来实现跳转逻辑

* **DataBinding** [link](https://github.com/leavesC/DataBindingSamples)

  实现 layout 和 代码的数据绑定，使用时，在 build.gradle(:app) 中添加：

  ```groovy
  android {
      buildFeatures {
          dataBinding true
          viewBinding true
      }
  }
  ```

  1. 实现 xml 和 Activity/Fragment 之间的控件绑定，使用 DataBinding 之后，就不用 findViewById 了

  2. 实现 xml 和 ViewModel 的数据绑定

     在 xml 中使用 `<layout>` 作为根节点，其中可以加入` <data>` 标签来操作数据
  
     使用 `@{viewModel.data}` 可以直接使用 `ViewModel` 中的数据
  
     `<data>` 标签中的 `<import>` 标签可以用来导包，供` <variable>` 标签使用
  
  3. 实现数据更新的方式 ObservableField 和 LiveData，使数据在 viewModel 层就进行更新了
  4. 将数据和控件的操作分离，这十分关键
  5. 如何在不获取 view 的情况下，在 viewModel 层更新数据呢？

* **LiveData**

  是和生命周期共存亡的数据，不会有内存泄漏的风险

### Android 依赖

* **com.google.dagger**
  * 可以更好的处理依赖，如此理解，使用 dagger 的对象，都是通过反射创建出来的
* **androidx.room**
  * 官方的 ORM 框架，暂时没有什么好研究的
* **androidx.lifecycle**
  * ViewModel 中的 SavedStateHandle 
    * 可以用来存储 ViewModel 中的数据，以键值对的方式进行存储，可以进行持久化

## REFERENCE

[sunflower_source_code](https://github.com/android/sunflower)
