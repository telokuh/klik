package mt;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import mt.modder.hub.C0002;
import mt.modder.hub.MTApplication;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.ۥۨۦۣ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0022 {

    /* renamed from: ۣ۟ۧ۠  reason: not valid java name and contains not printable characters */
    public static int f42 = 2;

    /* renamed from: ۟۟ۡ۟ۨ  reason: not valid java name and contains not printable characters */
    public static void m222(Object obj, int i, Object obj2) {
        if (C0002.m110() <= 0) {
            ((Cipher) obj).init(i, (Key) obj2);
        }
    }

    /* renamed from: ۣ۟۟ۥۦ  reason: not valid java name and contains not printable characters */
    public static Process m223(Object obj, Object obj2) {
        if (m235() > 0) {
            return ((Runtime) obj).exec((String) obj2);
        }
        return null;
    }

    /* renamed from: ۟۟ۦۣ۠  reason: not valid java name and contains not printable characters */
    public static void m224(Object obj) {
        if (C0002.m110() < 0) {
            ((IOException) obj).printStackTrace();
        }
    }

    /* renamed from: ۟۠ۥ۠ۡ  reason: not valid java name and contains not printable characters */
    public static String m225() {
        if (C0000.m25() < 0) {
            return MTApplication.f1;
        }
        return null;
    }

    /* renamed from: ۟۠ۧۡۧ  reason: not valid java name and contains not printable characters */
    public static int m226(Object obj) {
        if (C0002.m110() < 0) {
            return ((ArrayList) obj).size();
        }
        return 0;
    }

    /* renamed from: ۟ۡۦۥۦ  reason: not valid java name and contains not printable characters */
    public static String m227(Object obj) {
        if (m235() >= 0) {
            return ((Context) obj).getPackageResourcePath();
        }
        return null;
    }

    /* renamed from: ۟ۡۨۨۤ  reason: not valid java name and contains not printable characters */
    public static String m228(Object obj) {
        if (C0002.m110() <= 0) {
            return ((String) obj).toUpperCase();
        }
        return null;
    }

    /* renamed from: ۣ۟ۢ۠ۢ  reason: not valid java name and contains not printable characters */
    public static PackageManager m230(Object obj) {
        if (C0021.m196() < 0) {
            return ((Context) obj).getPackageManager();
        }
        return null;
    }

    /* renamed from: ۟ۢۦۢۤ  reason: not valid java name and contains not printable characters */
    public static void m231(Object obj, Object obj2) {
        if (C0021.m196() < 0) {
            ((C0012) obj).m150((Context) obj2);
        }
    }

    /* renamed from: ۣ۟۠ۨۦ  reason: not valid java name and contains not printable characters */
    public static void m232(Object obj, Object obj2) {
        if (C0002.m110() <= 0) {
            ((BufferedOutputStream) obj).write((byte[]) obj2);
        }
    }

    /* renamed from: ۣ۟ۡۤ۟  reason: not valid java name and contains not printable characters */
    public static void m233(Object obj, Object obj2) {
        if (C0021.m196() <= 0) {
            C0004.m116((Context) obj, (String) obj2);
        }
    }

    /* renamed from: ۣ۟ۤۧۤ  reason: not valid java name and contains not printable characters */
    public static ClipData m234(Object obj, Object obj2) {
        if (C0000.m25() <= 0) {
            return ClipData.newPlainText((CharSequence) obj, (CharSequence) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۤ۟۟ۧ  reason: not valid java name and contains not printable characters */
    public static int m235() {
        return 1748907 ^ m238((Object) "ۡۨۡ");
    }

    /* renamed from: ۟ۤ۟ۦۧ  reason: not valid java name and contains not printable characters */
    public static String m236() {
        if (m235() > 0) {
            return "";
        }
        return null;
    }

    /* renamed from: ۟ۤ۠ۥ  reason: not valid java name and contains not printable characters */
    public static String m237(String str) {
        String m236 = m236();
        String m2362 = m236();
        for (int i = 0; i < 15; i++) {
            m236 = C0021.m181(m253(m253(new StringBuffer(), m236), C0001.m100(i)));
            m2362 = C0021.m181(C0021.m176(m253(new StringBuffer(), m2362), ((int) (C0001.m67() * 10)) ^ i));
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(C0000.m52(str) / 2);
        while (C0000.m52(str) > 0) {
            C0000.m59(byteArrayOutputStream, (C0000.m29(m236, m255(str, -2)) << 4) | C0000.m29(m236, m255(str, -1)));
        }
        byte[] m26 = C0000.m26(byteArrayOutputStream);
        int length = m26.length;
        int m52 = C0000.m52(m2362);
        for (int i2 = 0; i2 < length; i2++) {
            m26[i2] = (byte) (m26[i2] ^ m255(m2362, i2 % m52));
        }
        return new String(m26);
    }

    /* renamed from: ۟ۥۥ۟ۨ  reason: not valid java name and contains not printable characters */
    public static int m238(Object obj) {
        return obj.hashCode();
    }

    /* renamed from: ۟ۥۦۦۢ  reason: not valid java name and contains not printable characters */
    public static int m241(Object obj) {
        if (m235() >= 0) {
            return ((ApplicationInfo) obj).flags;
        }
        return 0;
    }

    /* renamed from: ۟ۥۨۢۧ  reason: not valid java name and contains not printable characters */
    public static boolean m242(Object obj) {
        if (C0002.m110() < 0) {
            return TextUtils.isEmpty((CharSequence) obj);
        }
        return false;
    }

    /* renamed from: ۟ۦ۠۟ۧ  reason: not valid java name and contains not printable characters */
    public static String m243(Object obj) {
        if (C0021.m196() <= 0) {
            return ((StringBuilder) obj).toString();
        }
        return null;
    }

    /* renamed from: ۟ۦۡ۟ۥ  reason: not valid java name and contains not printable characters */
    public static boolean m244(Object obj) {
        if (C0002.m110() <= 0) {
            return ((Method) obj).isAccessible();
        }
        return false;
    }

    /* renamed from: ۟ۦۣۥۣ  reason: not valid java name and contains not printable characters */
    public static List m245(Object obj) {
        if (m235() > 0) {
            return Arrays.asList((Object[]) obj);
        }
        return null;
    }

    /* renamed from: ۟ۦۤۧۧ  reason: not valid java name and contains not printable characters */
    public static int m246(Object obj, Object obj2, Object obj3) {
        if (C0002.m110() <= 0) {
            return Log.w((String) obj, (String) obj2, (Throwable) obj3);
        }
        return 0;
    }

    /* renamed from: ۟ۧۢۦۦ  reason: not valid java name and contains not printable characters */
    public static String m247(Object obj) {
        if (C0000.m25() <= 0) {
            return ((BufferedReader) obj).readLine();
        }
        return null;
    }

    /* renamed from: ۣ۟ۧۢۦ  reason: not valid java name and contains not printable characters */
    public static long m248() {
        if (C0021.m196() <= 0) {
            return System.currentTimeMillis();
        }
        return 0L;
    }

    /* renamed from: ۣ۟ۧۨۦ  reason: not valid java name and contains not printable characters */
    public static AlertDialog m249(Object obj) {
        if (C0000.m25() <= 0) {
            return ((AlertDialog.Builder) obj).create();
        }
        return null;
    }

    /* renamed from: ۣ۠۟۠  reason: not valid java name and contains not printable characters */
    public static InputStream m250(Object obj) {
        if (C0002.m110() < 0) {
            return ((Process) obj).getInputStream();
        }
        return null;
    }

    /* renamed from: ۠ۤ۟ۧ  reason: not valid java name and contains not printable characters */
    public static Intent m251(Object obj, Object obj2) {
        if (m235() > 0) {
            return ((Intent) obj).setPackage((String) obj2);
        }
        return null;
    }

    /* renamed from: ۠ۥۡۧ  reason: not valid java name and contains not printable characters */
    public static String m252() {
        if (C0021.m196() <= 0) {
            return Environment.getExternalStorageState();
        }
        return null;
    }

    /* renamed from: ۠ۧۥۧ  reason: not valid java name and contains not printable characters */
    public static StringBuffer m253(Object obj, Object obj2) {
        if (m235() > 0) {
            return ((StringBuffer) obj).append((String) obj2);
        }
        return null;
    }

    /* renamed from: ۡ۟ۧۨ  reason: not valid java name and contains not printable characters */
    public static Iterator m254(Object obj) {
        if (C0000.m25() < 0) {
            return ((Collection) obj).iterator();
        }
        return null;
    }

    /* renamed from: ۡۢۧۥ  reason: not valid java name and contains not printable characters */
    public static char m255(Object obj, int i) {
        if (C0021.m196() < 0) {
            return ((String) obj).charAt(i);
        }
        return (char) 0;
    }

    /* renamed from: ۡۥۡۡ  reason: not valid java name and contains not printable characters */
    public static Object m256(Object obj, int i) {
        if (C0021.m196() <= 0) {
            return Array.newInstance((Class) obj, i);
        }
        return null;
    }

    /* renamed from: ۡۥۨۡ  reason: not valid java name and contains not printable characters */
    public static void m257(Object obj) {
        if (C0000.m25() < 0) {
            ((MTApplication) obj).m17();
        }
    }

    /* renamed from: ۡۨۨ۠  reason: not valid java name and contains not printable characters */
    public static int m258(Object obj, Object obj2, Object obj3) {
        if (C0021.m196() < 0) {
            return Log.e((String) obj, (String) obj2, (Throwable) obj3);
        }
        return 0;
    }

    /* renamed from: ۣۢۢۧ  reason: not valid java name and contains not printable characters */
    public static void m259(Object obj, Object obj2, Object obj3) {
        if (C0000.m25() <= 0) {
            ((Field) obj).set(obj2, obj3);
        }
    }

    /* renamed from: ۢۤ۠۟  reason: not valid java name and contains not printable characters */
    public static Context m260(Object obj) {
        if (C0021.m196() <= 0) {
            return ((Context) obj).getApplicationContext();
        }
        return null;
    }

    /* renamed from: ۢۥۣۧ  reason: not valid java name and contains not printable characters */
    public static int m261(Object obj, Object obj2) {
        if (C0021.m196() < 0) {
            return ((InputStream) obj).read((byte[]) obj2);
        }
        return 0;
    }

    /* renamed from: ۣ۟ۡۡ  reason: not valid java name and contains not printable characters */
    public static byte[] m262(Object obj, Object obj2) {
        if (C0002.m110() < 0) {
            return C0015.m160((Context) obj, (String) obj2);
        }
        return null;
    }

    /* renamed from: ۣۣ۠۠  reason: not valid java name and contains not printable characters */
    public static Intent m263(Object obj, Object obj2, Object obj3) {
        if (m235() > 0) {
            return ((Intent) obj).putExtra((String) obj2, (String[]) obj3);
        }
        return null;
    }

    /* renamed from: ۤۥ۟۟  reason: not valid java name and contains not printable characters */
    public static Field m264(Object obj, Object obj2) {
        if (m235() > 0) {
            return C0005.m121(obj, (String) obj2);
        }
        return null;
    }

    /* renamed from: ۥۣ۟۟  reason: contains not printable characters */
    public static Method m265(Object obj, Object obj2, Object obj3) {
        if (C0000.m25() <= 0) {
            return C0005.m122(obj, (String) obj2, (Class[]) obj3);
        }
        return null;
    }

    /* renamed from: ۥ۟ۦۥ  reason: contains not printable characters */
    public static String m266(Object obj) {
        if (C0021.m196() <= 0) {
            return ((Field) obj).getName();
        }
        return null;
    }

    /* renamed from: ۥۣۢۧ  reason: contains not printable characters */
    public static String m267(Object obj) {
        if (C0000.m25() < 0) {
            return C0017.m164((String) obj);
        }
        return null;
    }

    /* renamed from: ۥۦۤۦ  reason: contains not printable characters */
    public static int m268(Object obj) {
        if (C0021.m196() <= 0) {
            return ((List) obj).size();
        }
        return 0;
    }

    /* renamed from: ۦۢ۟۠  reason: contains not printable characters */
    public static String m269(Object obj) {
        if (m235() > 0) {
            return ((Class) obj).getName();
        }
        return null;
    }

    /* renamed from: ۧۢۤۧ  reason: not valid java name and contains not printable characters */
    public static void m270(Object obj, boolean z) {
        if (C0021.m196() <= 0) {
            ((Method) obj).setAccessible(z);
        }
    }

    /* renamed from: ۧۧۧۡ  reason: not valid java name and contains not printable characters */
    public static int m271() {
        if (m235() > 0) {
            return Process.myPid();
        }
        return 0;
    }

    /* renamed from: ۣۧۨ۠  reason: not valid java name and contains not printable characters */
    public static String m272(int i) {
        if (C0000.m25() <= 0) {
            return MTApplication.m0(i);
        }
        return null;
    }

    /* renamed from: ۨ۟ۢۨ  reason: not valid java name and contains not printable characters */
    public static Object m273(Object obj, Object obj2, Object obj3) {
        if (C0002.m110() <= 0) {
            return ((Method) obj).invoke(obj2, (Object[]) obj3);
        }
        return null;
    }

    /* renamed from: ۨ۠ۦۥ  reason: not valid java name and contains not printable characters */
    public static void m274(Object obj) {
        if (C0002.m110() <= 0) {
            C0015.m161((String) obj);
        }
    }

    /* renamed from: ۟ۥۥ۟ۨ  reason: not valid java name and contains not printable characters */
    public static Class<?> m239(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    /* renamed from: ۟ۢ۠ۥ  reason: not valid java name and contains not printable characters */
    public static String m229(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }

    /* renamed from: ۟ۥۥ۟ۨ  reason: not valid java name and contains not printable characters */
    public static String m240(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
