package mt.modder.hub.base;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import mt.C0012;
import mt.C0014;
import mt.C0015;
import mt.C0016;
import mt.C0018;
import mt.C0019;
import mt.C0021;
import mt.C0022;
import mt.RunnableC0003;
import mt.modder.hub.C0002;
/* renamed from: mt.modder.hub.base.ۨۤۥۤ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0001 {

    /* renamed from: ۨۧۥۨ  reason: not valid java name and contains not printable characters */
    public static boolean f12 = true;

    /* renamed from: ۟۟۠ۡۨ  reason: not valid java name and contains not printable characters */
    public static String m66(Object obj) {
        if (C0022.m235() >= 0) {
            return obj.toString();
        }
        return null;
    }

    /* renamed from: ۟۠ۥۦۥ  reason: not valid java name and contains not printable characters */
    public static double m67() {
        if (C0021.m196() <= 0) {
            return Math.random();
        }
        return 0.0d;
    }

    /* renamed from: ۟ۡ۠ۧۥ  reason: not valid java name and contains not printable characters */
    public static void m68(int i) {
        if (C0021.m196() < 0) {
            System.exit(i);
        }
    }

    /* renamed from: ۣ۟ۡۤۢ  reason: not valid java name and contains not printable characters */
    public static void m69(Object obj) {
        if (C0000.m25() <= 0) {
            ((PrintWriter) obj).close();
        }
    }

    /* renamed from: ۣ۟ۡۥۡ  reason: not valid java name and contains not printable characters */
    public static Set m70(Object obj) {
        if (C0021.m196() <= 0) {
            return ((Map) obj).entrySet();
        }
        return null;
    }

    /* renamed from: ۟ۡۦۧۤ  reason: not valid java name and contains not printable characters */
    public static Class m71(Object obj) {
        if (C0022.m235() > 0) {
            return ((Class) obj).getSuperclass();
        }
        return null;
    }

    /* renamed from: ۣ۟ۡۧ۠  reason: not valid java name and contains not printable characters */
    public static byte[] m72(Object obj) {
        if (C0022.m235() > 0) {
            return C0015.m158((InputStream) obj);
        }
        return null;
    }

    /* renamed from: ۟ۡۨۥۤ  reason: not valid java name and contains not printable characters */
    public static ArrayList m73(Object obj) {
        if (C0000.m25() < 0) {
            return C0016.m162((String) obj);
        }
        return null;
    }

    /* renamed from: ۟ۢ۟ۤۢ  reason: not valid java name and contains not printable characters */
    public static Object[] m74(Object obj, Object obj2) {
        if (C0002.m110() <= 0) {
            return ((ArrayList) obj).toArray((Object[]) obj2);
        }
        return null;
    }

    /* renamed from: ۣ۟ۢۧۦ  reason: not valid java name and contains not printable characters */
    public static Class m75(Object obj) {
        if (C0002.m110() <= 0) {
            return ((Class) obj).getComponentType();
        }
        return null;
    }

    /* renamed from: ۟ۢۧ۠۟  reason: not valid java name and contains not printable characters */
    public static boolean m76(Object obj, Object obj2) {
        if (C0000.m25() <= 0) {
            return ((String) obj).contains((CharSequence) obj2);
        }
        return false;
    }

    /* renamed from: ۣۣ۟ۢ۠  reason: not valid java name and contains not printable characters */
    public static void m77(Object obj) {
        if (C0000.m25() < 0) {
            C0014.m155((String) obj);
        }
    }

    /* renamed from: ۣ۟ۧۢ۠  reason: not valid java name and contains not printable characters */
    public static Cipher m78(Object obj) {
        if (C0002.m110() < 0) {
            return Cipher.getInstance((String) obj);
        }
        return null;
    }

    /* renamed from: ۟ۤۤۨۨ  reason: not valid java name and contains not printable characters */
    public static int m79(Object obj) {
        if (C0002.m110() < 0) {
            return ((PackageInfo) obj).versionCode;
        }
        return 0;
    }

    /* renamed from: ۟ۤۦۢۧ  reason: not valid java name and contains not printable characters */
    public static void m80(Object obj) {
        if (C0021.m196() < 0) {
            ((C0018) obj).start();
        }
    }

    /* renamed from: ۟ۥۢۦ۟  reason: not valid java name and contains not printable characters */
    public static Thread.UncaughtExceptionHandler m81() {
        if (C0022.m235() >= 0) {
            return Thread.getDefaultUncaughtExceptionHandler();
        }
        return null;
    }

    /* renamed from: ۟ۥۤۤۢ  reason: not valid java name and contains not printable characters */
    public static void m82(Object obj) {
        if (C0022.m235() > 0) {
            ((Thread) obj).start();
        }
    }

    /* renamed from: ۟ۥۥۥ۠  reason: not valid java name and contains not printable characters */
    public static void m83(Object obj) {
        if (C0022.m235() >= 0) {
            ((Exception) obj).printStackTrace();
        }
    }

    /* renamed from: ۟ۦۣۣۤ  reason: not valid java name and contains not printable characters */
    public static void m84(Object obj) {
        if (C0002.m110() < 0) {
            Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler) obj);
        }
    }

    /* renamed from: ۟ۦۥۢ  reason: not valid java name and contains not printable characters */
    public static String m85(Object obj, Object obj2) {
        if (C0002.m110() <= 0) {
            return ((DateFormat) obj).format((Date) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۦۨۨ۟  reason: not valid java name and contains not printable characters */
    public static ApplicationInfo m86(Object obj) {
        if (C0022.m235() > 0) {
            return ((Context) obj).getApplicationInfo();
        }
        return null;
    }

    /* renamed from: ۟ۧۦۨ۟  reason: not valid java name and contains not printable characters */
    public static AlertDialog.Builder m87(Object obj, boolean z) {
        if (C0021.m196() <= 0) {
            return ((AlertDialog.Builder) obj).setCancelable(z);
        }
        return null;
    }

    /* renamed from: ۠ۢ۟ۥ  reason: not valid java name and contains not printable characters */
    public static void m88(Object obj, Object obj2) {
        if (C0000.m25() < 0) {
            ((ClipboardManager) obj).setPrimaryClip((ClipData) obj2);
        }
    }

    /* renamed from: ۡ۟۟ۡ  reason: not valid java name and contains not printable characters */
    public static StringBuffer m90(Object obj, char c) {
        if (C0002.m110() <= 0) {
            return ((StringBuffer) obj).append(c);
        }
        return null;
    }

    /* renamed from: ۡ۠ۢ۠  reason: not valid java name and contains not printable characters */
    public static Context m91(Object obj) {
        if (C0022.m235() >= 0) {
            return ((BaseApplication) obj).getApplicationContext();
        }
        return null;
    }

    /* renamed from: ۣۡ۠۠  reason: not valid java name and contains not printable characters */
    public static boolean m92(Object obj) {
        if (C0021.m196() < 0) {
            return ((Field) obj).isAccessible();
        }
        return false;
    }

    /* renamed from: ۣۡۧۧ  reason: not valid java name and contains not printable characters */
    public static Object[] m93(Object obj, Object obj2, Object obj3) {
        if (C0021.m196() < 0) {
            return C0019.m167(obj, (ArrayList) obj2, (File) obj3);
        }
        return null;
    }

    /* renamed from: ۡۤ۟  reason: not valid java name and contains not printable characters */
    public static PackageInfo m94(Object obj, Object obj2, int i) {
        if (C0022.m235() > 0) {
            return ((PackageManager) obj).getPackageInfo((String) obj2, i);
        }
        return null;
    }

    /* renamed from: ۡۥ  reason: not valid java name and contains not printable characters */
    public static boolean m95(Object obj) {
        if (C0022.m235() >= 0) {
            return ((File) obj).isFile();
        }
        return false;
    }

    /* renamed from: ۢۥۢۨ  reason: not valid java name and contains not printable characters */
    public static File m96(Object obj, Object obj2, int i) {
        if (C0022.m235() > 0) {
            return ((Context) obj).getDir((String) obj2, i);
        }
        return null;
    }

    /* renamed from: ۢۧ۟ۦ  reason: not valid java name and contains not printable characters */
    public static byte[] m97(Object obj, Object obj2) {
        if (C0000.m25() <= 0) {
            return ((Cipher) obj).doFinal((byte[]) obj2);
        }
        return null;
    }

    /* renamed from: ۣۢۦۦ  reason: not valid java name and contains not printable characters */
    public static boolean m98(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            return ((ArrayList) obj).add(obj2);
        }
        return false;
    }

    /* renamed from: ۤ۟ۧۡ  reason: not valid java name and contains not printable characters */
    public static void m99(Object obj) {
        if (C0022.m235() > 0) {
            ((DialogInterface) obj).dismiss();
        }
    }

    /* renamed from: ۤۡ۠۟  reason: not valid java name and contains not printable characters */
    public static String m100(int i) {
        if (C0000.m25() <= 0) {
            return Integer.toHexString(i);
        }
        return null;
    }

    /* renamed from: ۥۢۢۡ  reason: contains not printable characters */
    public static String m101(Object obj) {
        if (C0000.m25() < 0) {
            return ((PackageInfo) obj).versionName;
        }
        return null;
    }

    /* renamed from: ۥۣۨۡ  reason: contains not printable characters */
    public static void m102(Object obj, Object obj2) {
        if (C0021.m196() <= 0) {
            ((Context) obj).sendBroadcast((Intent) obj2);
        }
    }

    /* renamed from: ۦ۟ۥۤ  reason: contains not printable characters */
    public static void m103(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            RunnableC0003.m113((Context) obj, (String) obj2);
        }
    }

    /* renamed from: ۦۣۡۨ  reason: contains not printable characters */
    public static void m104(Object obj, Object obj2, int i, int i2) {
        if (C0002.m110() < 0) {
            ((ByteArrayOutputStream) obj).write((byte[]) obj2, i, i2);
        }
    }

    /* renamed from: ۦۥۢۢ  reason: contains not printable characters */
    public static int m105() {
        return (-1750634) ^ C0022.m238((Object) "ۣ۠۠");
    }

    /* renamed from: ۨ۟ۢ۠  reason: not valid java name and contains not printable characters */
    public static Class m106(Object obj) {
        if (C0002.m110() < 0) {
            return obj.getClass();
        }
        return null;
    }

    /* renamed from: ۨ۠۟  reason: not valid java name and contains not printable characters */
    public static String m107(String str) {
        String m236 = C0022.m236();
        String m2362 = C0022.m236();
        for (int i = 0; i < 15; i++) {
            m236 = C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), m236), m100(i)));
            m2362 = C0021.m181(C0021.m176(C0022.m253(new StringBuffer(), m2362), ((int) (m67() * 10)) ^ i));
        }
        do {
        } while (C0000.m52(m236) > 0);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(C0000.m52(str) / 2);
        for (int i2 = 0; i2 < C0000.m52(str); i2 += 2) {
            C0000.m59(byteArrayOutputStream, (C0000.m29(m236, C0022.m255(str, i2)) << 4) | C0000.m29(m236, C0022.m255(str, i2 + 1)));
        }
        byte[] m26 = C0000.m26(byteArrayOutputStream);
        int length = m26.length;
        int m52 = C0000.m52(m2362);
        for (int i3 = 0; i3 < length; i3++) {
            m26[i3] = (byte) (m26[i3] ^ C0022.m255(m2362, i3 % m52));
        }
        return new String(m26);
    }

    /* renamed from: ۨۥۣۧ  reason: not valid java name and contains not printable characters */
    public static void m108(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            ((C0012) obj).m151((Context) obj2);
        }
    }

    /* renamed from: ۨۧۨۤ  reason: not valid java name and contains not printable characters */
    public static AlertDialog.Builder m109(Object obj, Object obj2) {
        if (C0000.m25() < 0) {
            return ((AlertDialog.Builder) obj).setMessage((CharSequence) obj2);
        }
        return null;
    }

    /* renamed from: ۣ۠ۤۤ  reason: not valid java name and contains not printable characters */
    public static String m89(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
