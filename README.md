# PermissionManager
Android权限申请

一个简易的权限申请工具

## 检查权限 
  `PermissionManager.with().check(permission)`

  只有当全部权限都被授权的时候才会返回`true`

  `PermissionManager.with().checks(permission)`

  返回没有被授权的权限，当全部权限都被授权的时候，返回一个长度为0的字符串数组

## 申请权限
  `PermissionManager.with().request(requestCode,permission)`
  `PermissionManager.start().request(requestCode,permission)`
    
  `with()`传入的可以是`Activity`、`Fragment`、`FragmentV4`、`Context`

  `start()`传入的可以是`Activity`、`Fragment`、`FragmentV4`、`Context`

  `with()` 由自己处理申请权限的返回结果，在 `Activity` 或者 `Fragment` 中判断 `onRequestPermissionsResult`

  `start()` 则开启一个透明的Activity申请，通过 `onActivityResult` 接收申请的结果，`data.getStringArrayListExtra("allow")` 是通过申请的权限,`data.getStringArrayListExtra("disallow")` 是没有通过的

  如果使用 `start()` 建议在自己项目的 `res/values/styles.xml` 的里面配置：
  ```xml
      <style name="PM_Theme" parent="AppTheme">
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowAnimationStyle">@android:style/Animation.Translucent</item>
    </style>
  ``` 
  这样可以达到透明的效果
  
## 权限弹框

  弹窗由 `title`标题、`msg`说明文本、`cancelView`取消按钮、`okView`去设置按钮 四个部分组成，提供取消按钮的回调，去设置按钮自动跳转到应用的设置界面

  `PermissionManager.with().requestRationale()` 

  `requestRationale(@NonNull String title, @NonNull String msg, DialogInterface.OnClickListener onClickListener, @NonNull int requestCode, @NonNull String... pers) `
  弹出一个系统默认样式的弹窗 


 `requestRationale(@NonNull View view, @NonNull View okView,@NonNull View cancelView, DialogInterface.OnClickListener onClickListener, @NonNull int requestCode, @NonNull String... pers) `
 弹出一个自定义`View`的弹窗，需要传入 `cancelView` 和 `okView` 

## 使用方法
参考 https://www.jitpack.io/#RunCross/PermissionManager