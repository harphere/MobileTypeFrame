# MobileTypeFrame v1.2.0

Choose **Square**, **Waves**, **Open Corners**, or **Side Waves** from the app's
launcher screen. Square is the compact outline, Waves has two short arcs above
the glyph, Open Corners frames it with four broken corners, and Side Waves has
three arcs to the right like the supplied reference image. Circle was removed.
An existing Circle selection changes to Square on update. The preview shows the
current choice. The glyph still represents the actual network type reported
by Android.

## Build

Replace **all project files** in your GitHub repository with the contents of
this ZIP. The earlier versions do not include the new drawing styles. Run
**Actions → Build APK → Run workflow**, then download
`MobileTypeFrame-v1.2.0-debug` and extract the APK. Alternatively,
open the project with Android Studio, JDK 17 and Android SDK 36, then run
`assembleDebug` with Gradle 8.11.1.

## Install and use on Vector 2.2

1. Install the APK and open **MobileTypeFrame** from the launcher.
2. Choose a shape. In Vector, enable the module and scope **System UI
   (`com.android.systemui`) only**. Enable legacy resource hooks if that
   option exists in your build.
3. Reboot after changing a shape. The settings screen saves the selection,
   and System UI reads it as the visible mobile type icon is bound.

If the outline or waves do not appear, search the Vector logs for
`MobileTypeFrame:` and send the lines beginning `hooked IconViewBinder.bind`,
`bound mobile_type`, `mobile_type uses drawable`, or `view hook unavailable`.
The prior `registered ... resources` line alone only proves that a resource
replacement was installed; it does not prove that Android displayed it.

The module does not edit any system partition or SystemUI APK. Disabling the
module in Vector and rebooting restores the original status bar. The settings
provider exposes only the selected shape to System UI. The view hook targets
`mobile_type` in Android's modern mobile icon binder, which has not yet been
tested on this specific Infinity-X build.
