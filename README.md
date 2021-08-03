#IDP2-NativeApp-Android

项目包括三个模块
SDK    -集成的SDK
DEMO   -集成的示例
DOC    -集成文档

###IDPNativeAppSDK- Android - 开发文档
更新时间 2016年12月5号
SDK版本号 1.0.0

###一、介绍
北京九州云腾科技有限公司的IDP产品的口号是统一身份、安全便捷，而IDP单点登录Android SDK能够实现IDP身份管家到第三方开发者应用的身份管理和单点登录。


如果对于IDP不熟悉的话，可以联系我们info@idsmanager.com，或者去我们公司的网站http://www.idsmanager.com 了解详细。IDP产品针对的是企业级用户，单点登录SDK针对的也即企业内部开发者。IDP系统能够很安全便捷地统一管理企业人员在内部应用中的账号信息。

####已实现的功能：

用U+P(用户名+密码)的方式从IDP身份管家跳转到第三方应用，并且获取到由应用：IDP身份管家 传来的账号信息，以在本地应用中无缝连接单点登录，直接进入登录状态。

####将实现的功能：

除了U+P外，提供基于OAuth或OIDC的token验证方式，更加安全有效

####系统版本支持： 

目前SDK 支持 Android 4.0.3及以上版本的手机

如有问题，请联系info@idsmanager.com，或致电 010-58732285。


###二、如何安装
libs:
    idp2nativeapp1.0.jar
###三、使用IDPNativeAppSDK
使用 idp2nativeapp1.0.jar一共有以下步骤。

####1.为您的应用导包
    compile files('libs/idp2nativeapp1.0.jar')

    compile 'com.squareup.okhttp3:okhttp:3.3.1'

    compile 'com.squareup.okio:okio:1.8.0'

    compile 'com.google.code.gson:gson:2.3.1'

####2.在您的应用的AndroidManifest.xml中加

    <receiver android:name="com.idsmanager.idp2nativeapplibrary.receive.MyReceiver">

            <intent-filter>

                <action android:name="com.idsmanager.enterprisetwo.summer.receiver" />

            </intent-filter>

    </receiver>

UserReceiver这个广播接收器是创建的项目中需要自己写得，用来接收用户信息，看demo中的UserReceiver

    <receiver android:name=".receive.UserReceiver">

            <intent-filter>

                <action android:name="com.idsmanager.nativeappdemo.summer.userinfo" />

            </intent-filter>

        </receiver>

####3.在Application中

   1） IDP2NativeApp.init(getApplicationContext(), MainActivity.class);
    其中 MainActivity.class是您要实现免密码登录的Activity
   2） IDP2NativeApp.getFacetID(mContext)获取URL Schemes，该项代表着在app之间跳转的唯一标识，在之后的网页上的步骤中会需要填写。这行代码的目的只是为了获取URL Scheme，获取后可以删除，和SDK的集成逻辑无关。

####4.在ManiActivity中

    其中 MainActivity.class是您要实现免密码登录的Activity
    UserInfo info = IDP2NativeApp.getUser(this);
    获取用户信息
    info.getAccount(), info.getPassword()分别对应账号和密码

注意：Eclipse开发者导入相应的jar包，除第一步不同以外，其余步骤一致
###四、IDP单点登录设置

IDP(Identity Provider)产品市场名称为：IDP身份管家，支持iOS和Android，在App Store和应用宝上可以下载到。该产品是IDP产品线的重要组成部分，配合网页端可以做到安全、便捷地统一管理和使用自己的账号身份信息。IDP身份管家对于本地应用的身份管理支持，是移动端实现统一所有网络身份的重要模块。IDP提供的本SDK，目的是为企业开发者提供一个可以接入IDP的方法，能够实现从IDP应用到第三方开发应用的账号管理和单点登录。


想要实现IDP的统一账号管理和单点登录，您所在的公司必须要正在使用IDP产品，并且您必须拥有管理员权限。开发者权限是不能够添加Native App应用的，请注意。

![输入图片说明](http://git.oschina.net/uploads/images/2016/1223/161718_c856384b_1034121.gif "在这里输入图片标题")




####1 . 在管理员的IDP界面中，点击添加应用，搜索Native App，这里看到的使我们提供的统一模板，我们使用这个模板，点击添加应用。（如果没有找到该模板，请与九州云腾联系）

![输入图片说明](http://git.oschina.net/uploads/images/2016/1223/161930_1ac1483a_1034121.png "在这里输入图片标题")


####2 . 继续填写需要添加的应用内容。

所属领域：请根据情况选择最合适的，这里的选项不会影响到应用的实现
iOS/Android Scheme URL
Android的Scheme Url填写您在前一节中通过 IDP2NativeApp.getFaceID(mContext)创建的URL Scheme
 **注意：如果这里填写debug运行的FaceID,正式发布时要改为正式签名后apk生成的faceID** 
账号关联方式：这里选择您希望通过什么方式从IDP身份管家把用户的身份信息传递给你的应用，我们目前只支持账号密码的方式，以后会提供基于OIDC或OAuth的token方式。
![输入图片说明](http://git.oschina.net/uploads/images/2016/1223/162044_774f4a0e_1034121.png "在这里输入图片标题")



####3 . 请为应用授权用户，以便能让公司用户开始使用。如果您知道如何授权应用，请自行授权并跳过本节讲解。
如何授权：授权指的是将一个企业添加进来的应用交给用户使用的过程。IDP系统中，应用添加好以后，需要授权给用户组，如果应用实在开启（默认）的状态的话，那么该用户组的人就可以在他们的IDPs侯爷看到新添加进来的应用。
从左侧进入授权菜单
从列表中选择刚创建的应用
添加新授权
在新页面中选择想要授权的用户组，比如测试组，管理组等。
如果未创建用户组，可以自行创建。如果未添加用户，请先添加用户，并将其归入一个用户组内。

![输入图片说明](http://git.oschina.net/uploads/images/2016/1223/162137_388930bb_1034121.png "在这里输入图片标题")

####4 . 应用到这步已经添加完成，但是我们还需要为授权的用户组中的用户添加该用户在该应用的账号信息，这样才能一一对应将这些信息在跳转时传入第三方应用。如果您知道如何授权添加应用子账号，请自行授权并跳过本节讲解。

#####a. 退出登录，并以刚才授权过的用户组中某个用户的身份重新登录系统。如果您刚才的管理员是授权成员组成员之一，那么您不需要登出，只要在右上方导航条下拉菜单中选择用户界面即可跳转到用户身份。

#####b. 进入应用子账号菜单，会看到你的所有被授权的应用所添加的账号信息的列表。点击右上角的添加应用子账号按钮。
![输入图片说明](http://git.oschina.net/uploads/images/2016/1223/162207_2a2ca90b_1034121.png "在这里输入图片标题")

#####c. 在添加界面中选择正确的应用，并且填入对应您第三方应用有效身份信息的用户名密码。比如我开发了应用叫做「微信改」，我在这里天填写的就是我希望IDP身份管家能帮我管理，并实现单点登录的对应「微信改」账号。这个账号应该在您应用自己的系统中注册并可以使用。
如果您在尝试使用IDPNativeApp这个Demo应用，请在运行IDPNativeApp后点击注册按钮，并将注册好的信息填写在本步骤中。
您可以为同一个应用添加多个需要管理的账号，IDP身份管家会在跳转前询问您需要使用哪一个

![输入图片说明](http://git.oschina.net/uploads/images/2016/1223/162241_ccffc1a9_1034121.png "在这里输入图片标题")
我们从前到后添加进了IDPNativeAppSDK，配置好了URL Scheme，在IDP网页端创建好了应用对应了URL Scheme，在授权给目标群组之后，在用户界面给该用户自己添加了目标应用的账号信息。


到此，您的应用应该能够使用IDP身份管家来管理和登录您的应用了！



如果您有宝贵的意见请随时与我们联系info@idsmanager.com 。我们会在未来为IDP使用者和开发者提供更完备的SDK功能，敬请期待！


 **北京九州云腾团队** 

2016年12月