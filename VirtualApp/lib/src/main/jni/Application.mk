APP_ABI := armeabi-v7a arm64-v8a x86 x86_64
APP_PLATFORM := android-14
#APP_STL := c++_static
APP_STL := gnustl_static
APP_OPTIM := release
VA_ROOT          := $(call my-dir)
NDK_MODULE_PATH  := $(NDK_MODULE_PATH):$(VA_ROOT)