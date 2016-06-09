# A ViewGroup visibility demo

What's inside
-------------

Just a simple demo of how Android ViewGroups work in certain situations.

Details
-------

If you set the visibility flag on any ViewGroup in Android, that visibility flag will not be propagated to that ViewGroup's children.
This *demo* app does the XML setup for `GONE`, `INVISIBLE`, and `VISIBLE`, as well as Java tests for the same set of flags.

If you want to quickly test the APK, [download it from here](https://github.com/milosmns/demo-child-view-visibility/blob/master/resources/Visibility-Demo.apk?raw=true).

![Screenshot](https://raw.githubusercontent.com/milosmns/demo-child-view-visibility/master/resources/Screenshot-Mobile.png)

More information
----------------

People already asked questions, like here: http://stackoverflow.com/q/19775407/2102748

Some useful links:
- Children inflation (recursive): https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/LayoutInflater.java#L796
- Interesting snapshot method: https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/ViewGroup.java#L3160
- Visibility setter: https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/View.java#L7430

Support
-------

If you found an error while using the app, please [file an issue](https://github.com/milosmns/demo-child-view-visibility/issues/new).
All patches are encouraged, and may be submitted by [forking this project](https://github.com/milosmns/demo-child-view-visibility/fork) and
submitting a pull request through GitHub.
Some more help can be found here:
- [Stack Overflow](http://stackoverflow.com/questions/tagged/demo-child-view-visibility)
- [On my blog](http://angrybyte.me)
