<uses-permission android:name="moe.shizuku.manager.permission.API_V23" />
<provider
    android:name="rikka.shizuku.ShizukuProvider"
    android:authorities="${applicationId}.shizuku"
    android:enabled="true"
    android:exported="true"
    android:multiprocess="false"
    android:permission="android.permission.INTERACT_ACROSS_USERS_FULL" />
    val alive = runCatching { Shizuku.pingBinder() }.getOrDefault(false)
val granted = Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
val baseBinder = SystemServiceHelper.getSystemService("input")
val wrappedBinder = ShizukuBinderWrapper(baseBinder)
val stubClass = Class.forName("android.hardware.input.IInputManager\$Stub")
val asInterface = stubClass.getDeclaredMethod("asInterface", IBinder::class.java)
val inputManager = asInterface.invoke(null, wrappedBinder)

val injectMethod = inputManager.javaClass.getMethod(
    "injectInputEvent", InputEvent::class.java, Int::class.javaPrimitiveType
)
injectMethod.invoke(inputManager, motionEvent, 0) // 0 = MODE_ASYNC