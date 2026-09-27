# MobileTypeFrame v1.4.0

Choose **Square**, **Filled**, **Waves**, **Open Corners**, **Side Waves**,
**Italic**, or **Bold Italic** from the app's launcher screen. Square is the
compact outline. Filled draws a solid badge with transparent lettering so the
status-bar background shows through the letters. Waves has two short arcs above
the glyph, Open Corners frames it with four broken corners, and Side Waves has
three arcs to the right like the supplied reference image. Italic and Bold
Italic are plain lettering with no frame. Circle was removed.
An existing Circle selection changes to Square on update. The preview shows the
current choice. The glyph still represents the actual network type reported
by Android. The five framed and wave styles use the same bold, condensed
typeface; Side Waves has extra width so its `5G` stays the same size as the
unboxed Waves glyph. Italic and Bold Italic use condensed italic typefaces.
Choose **Normal (100%)**, **Large (125%)**, or **Extra large (150%)** for the
letter size. Extra large is the default, including after an update from 1.2.1;
Normal restores the original font size. The drawable keeps its status-bar
height while making room horizontally for larger letters.

The same shape and font size apply to 5G, 5G+, 4G, 4G+, LTE, and LTE+ when
System UI presents a recognizable mobile-type icon through the hooked path.
Which types actually appear depends on your ROM and network; 4G and LTE have
not been verified on this particular device.

## Build

Replace **all project files** in your GitHub repository with the contents of
this ZIP. The earlier versions do not include the new drawing styles. Run
**Actions → Build APK → Run workflow**, then download
`MobileTypeFrame-v1.4.0-debug` and extract the APK. Alternatively,
open the project with Android Studio, JDK 17 and Android SDK 36, then run
`assembleDebug` with Gradle 8.11.1.

## Install and use on Vector 2.2

1. Install the APK and open **MobileTypeFrame** from the launcher.
2. Choose a shape and font size. In Vector, enable the module and scope **System UI
   (`com.android.systemui`) only**. Enable legacy resource hooks if that
   option exists in your build.
3. Reboot after changing either setting. The settings screen saves the selection,
   and System UI reads it as the visible mobile type icon is bound.

If the outline or waves do not appear, search the Vector logs for
`MobileTypeFrame:` and send the lines beginning `hooked IconViewBinder.bind`,
`bound mobile_type`, `mobile_type uses drawable`, or `view hook unavailable`.
The prior `registered ... resources` line alone only proves that a resource
replacement was installed; it does not prove that Android displayed it.

The module does not edit any system partition or SystemUI APK. Disabling the
module in Vector and rebooting restores the original status bar. The settings
provider exposes only the selected appearance settings to System UI. The view hook targets
`mobile_type` in Android's modern mobile icon binder, which has not yet been
tested on this specific Infinity-X build.
