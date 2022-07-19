# MiraRecycleView

##### Description

MiraRecycleView is a RecyclerView(advanced and flexible version of ListView)
with pulling to refresh, loading more, swiping to dismiss, dragging and drop, animations ,sticky
header, show or hide toolbar and FAB when scrolling and many other features.You can use
it ```just like RecyclerView```. Support AndroidX now.

Notice that MiraRecycleView is a project under development.

##### Version number:

[![](https://jitpack.io/v/yehiareda4/MiraRecycleView.svg)](https://jitpack.io/#yehiareda4/MiraRecycleView)

##### Features:

* Swipe to refresh(SwipeRefreshLayout)
* Many kinds of animations
* Loading more when reach the last item(infinite scrolling)
* Custom views in loading more
* Support different layout in adapter
* Loading adapter with animation
* Fixable ErrorView For any situation

######### Quick Setup (Basic Usage)

######### 1.Using Gradle:

```groovy
    allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    ...
    implementation 'com.github.yehiareda4:MiraRecycleView:{version}'
}
```

#### Usage:

--> in xml

``` 
    <com.yehia.reda.mira_recycle_view_tools.lib.MiraRecycleViewV4
        android:id="@+id/mrv_list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:mira_attrs_enabled="true"
        app:mira_attrs_error_enabled="true"
        app:mira_error_action_back_ground="Your drawable"
        app:mira_error_message="test"
        app:mira_refreshing="true"
        app:mira_shimmer_layout="your shimmer layout"
        app:mira_toggle_show_error="gone"
        app:mira_reversed_layout="not_reversed"
        app:mira_toggle_show_shimmer="visible" />

```

--> in kotlin

``` kotlin

   
        mrvList.setUp(LinearLayoutManager(this), object : CallBack {
            override fun onLoadMore(current_page: Int) {
                //Used To Custemize Pagination in  MiraRecycleView If Enabled
            }

            override fun onRefresh() {
                //Used To Custemize Refresh in MiraRecycleView If Enabled
            }

            override fun onReset() {
                //Used To Reset MiraRecycleView
            }

            override fun onInit() {
                //Used To Init MiraRecycleView
            }

            override fun onErrorClick() {
                //For Error View Click If Enabled
            }
        })
```

#### Configuration:

Property | Type | Description
--- | --- | ---
mira_attrs_enabled | boolean | Boolean for enable attrs of mira recycle view
mira_toggle_show_shimmer | integer | Option for enable shimmer of mira recycle view (visible, gone)
mira_refreshing | boolean | Boolean for enable Refreshing
mira_progress_txt_color | reference | Color for progress Txt Color
mira_reversed_layout | integer | Option If Layout of mira recycle view reversed (reversed, not_reversed)
--- | MIRA_SHIMMER_CREATOR | ATTRS AND REFERENCE
mira_shimmer_layout | reference | Layout of shimmer
mira_shimmerColor | reference | Color for shimmer animation Color
mira_maskColor | reference | Color for Layout Component BackGround
mira_duration | integer | integer for duration animation move
mira_countItem | integer | integer for count items
--- | MIRA_ERROR_VIEW | ---
mira_toggle_show_error | boolean | Boolean For Enable Attrs Of Mira Error View
mira_error_back_ground_color | reference | Color for Layout BackGround Color
mira_error_image | reference | Image For Error Image View (Accept Gif mira_error_image_type Is gif)
mira_error_image_type | integer | Option For Image Type (other, gif)
mira_error_title | string | String for title
mira_error_title_txt_color | reference | Color for  Txt Color
mira_error_message | string | String for message
mira_error_message_txt_color | reference | Color for message Txt Color
mira_error_action | string | String for action
mira_error_action_txt_color | reference | Color for action Txt Color
mira_error_action_back_ground | reference | Color for action BackGround Color
mira_error_font | reference | Font of Error View

#### Version Log:

* ***v1.0.0*** support Java and Kotlin and migrate to AndroidX

#### Upcoming features:

* Add Custom RecycleView Adapter

> Notice that it might not be the latest version

#### Welcome to fork and PR (pull request):

If you have some good ideas, please tell us. My email is cymcsg # gmail.com.And it is a good idea to
put your idea on the issue. If you want to use a rapid development framework for developing apps,
you can try [MiraAndroid Framework](https://github.com/yehiareda4/MiraRecycleView).

#### Screenshot:

> We Will Add New Screenshot Soon