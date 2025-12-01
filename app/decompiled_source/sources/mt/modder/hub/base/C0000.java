package mt.modder.hub.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import mt.C0020;
import mt.C0021;
import mt.C0022;
import mt.modder.hub.C0002;
import mt.modder.hub.MTApplication;
/* renamed from: mt.modder.hub.base.ۣ۟ۤۤۦ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0000 {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f10short = {427, 1724};

    /* renamed from: ۡ۠ۢ  reason: not valid java name and contains not printable characters */
    public static int f11 = -20;

    /* renamed from: ۟۟۟ۡۡ  reason: not valid java name and contains not printable characters */
    public static Object m20(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            return ((Field) obj).get(obj2);
        }
        return null;
    }

    /* renamed from: ۟۟ۢۡۨ  reason: not valid java name and contains not printable characters */
    public static Throwable m21(Object obj) {
        if (C0002.m110() < 0) {
            return ((Throwable) obj).getCause();
        }
        return null;
    }

    /* renamed from: ۣ۟۠ۢۦ  reason: not valid java name and contains not printable characters */
    public static Runtime m22() {
        if (C0022.m235() >= 0) {
            return Runtime.getRuntime();
        }
        return null;
    }

    /* renamed from: ۟ۡۥۣۡ  reason: not valid java name and contains not printable characters */
    public static Context m23(Object obj) {
        if (C0021.m196() < 0) {
            return ((MTApplication) obj).getBaseContext();
        }
        return null;
    }

    /* renamed from: ۟ۡۦۢۥ  reason: not valid java name and contains not printable characters */
    public static int m24() {
        if (C0021.m196() < 0) {
            return Build.VERSION.SDK_INT;
        }
        return 0;
    }

    /* renamed from: ۟ۡۧۦۡ  reason: not valid java name and contains not printable characters */
    public static int m25() {
        return (-1751803) ^ C0022.m238((Object) "ۤۦۢ");
    }

    /* renamed from: ۟ۢۡۥ۟  reason: not valid java name and contains not printable characters */
    public static byte[] m26(Object obj) {
        if (m25() < 0) {
            return ((ByteArrayOutputStream) obj).toByteArray();
        }
        return null;
    }

    /* renamed from: ۣۣ۟ۥۥ  reason: not valid java name and contains not printable characters */
    public static void m27(Object obj, int i, Object obj2, int i2, int i3) {
        if (C0021.m196() <= 0) {
            System.arraycopy(obj, i, obj2, i2, i3);
        }
    }

    /* renamed from: ۣ۟ۥۡۦ  reason: not valid java name and contains not printable characters */
    public static int m28(Object obj, int i) {
        if (C0022.m235() >= 0) {
            return ((Random) obj).nextInt(i);
        }
        return 0;
    }

    /* renamed from: ۣ۟ۨۡۤ  reason: not valid java name and contains not printable characters */
    public static int m29(Object obj, int i) {
        if (C0021.m196() < 0) {
            return ((String) obj).indexOf(i);
        }
        return 0;
    }

    /* renamed from: ۟ۤ۟۟ۥ  reason: not valid java name and contains not printable characters */
    public static String m30(Object obj) {
        if (m25() < 0) {
            return ((File) obj).getAbsolutePath();
        }
        return null;
    }

    /* renamed from: ۟ۤ۠ۦۨ  reason: not valid java name and contains not printable characters */
    public static Intent m31(Object obj, Object obj2) {
        if (C0021.m196() < 0) {
            return ((Intent) obj).setAction((String) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۤ۠ۧۤ  reason: not valid java name and contains not printable characters */
    public static String m32(Object obj) {
        if (m25() <= 0) {
            return ((Throwable) obj).getMessage();
        }
        return null;
    }

    /* renamed from: ۟ۥۣۡۢ  reason: not valid java name and contains not printable characters */
    public static Object m33(Object obj) {
        if (C0022.m235() > 0) {
            return ((Map.Entry) obj).getValue();
        }
        return null;
    }

    /* renamed from: ۟ۥۥۨۤ  reason: not valid java name and contains not printable characters */
    public static boolean m35(Object obj) {
        if (m25() < 0) {
            return ((Iterator) obj).hasNext();
        }
        return false;
    }

    /* renamed from: ۟ۦ۟ۤ  reason: not valid java name and contains not printable characters */
    public static Toast m36(Object obj, Object obj2, int i) {
        if (C0002.m110() < 0) {
            return Toast.makeText((Context) obj, (CharSequence) obj2, i);
        }
        return null;
    }

    /* renamed from: ۟ۦۢ۟ۡ  reason: not valid java name and contains not printable characters */
    public static byte[] m37(Object obj) {
        if (m25() < 0) {
            return ((String) obj).getBytes();
        }
        return null;
    }

    /* renamed from: ۟ۦۣۤ  reason: not valid java name and contains not printable characters */
    public static byte[] m38(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            return MTApplication.m4((byte[]) obj, (String) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۦۤۨ  reason: not valid java name and contains not printable characters */
    public static boolean m39(Object obj, Object obj2) {
        if (m25() < 0) {
            return ((ArrayList) obj).remove(obj2);
        }
        return false;
    }

    /* renamed from: ۟ۦۦۡ  reason: not valid java name and contains not printable characters */
    public static void m40(Object obj, Object obj2) {
        if (C0022.m235() > 0) {
            ((FileOutputStream) obj).write((byte[]) obj2);
        }
    }

    /* renamed from: ۟ۦۧ۟  reason: not valid java name and contains not printable characters */
    public static Object[] m41(Object obj, Object obj2, Object obj3, Object obj4) {
        if (m25() <= 0) {
            return C0020.m171(obj, (ArrayList) obj2, (File) obj3, (ArrayList) obj4);
        }
        return null;
    }

    /* renamed from: ۟ۧۢۦۨ  reason: not valid java name and contains not printable characters */
    public static boolean m42(Object obj) {
        if (C0021.m196() < 0) {
            return ((File) obj).mkdirs();
        }
        return false;
    }

    /* renamed from: ۟ۧۧ۠۠  reason: not valid java name and contains not printable characters */
    public static Object m43(Object obj, Object obj2, Object obj3) {
        if (C0002.m110() < 0) {
            return ((Map) obj).put(obj2, obj3);
        }
        return null;
    }

    /* renamed from: ۠ۢۡۥ  reason: not valid java name and contains not printable characters */
    public static InputStream m44(Object obj, Object obj2) {
        if (C0002.m110() <= 0) {
            return ((ZipFile) obj).getInputStream((ZipEntry) obj2);
        }
        return null;
    }

    /* renamed from: ۡ۠ۡۧ  reason: not valid java name and contains not printable characters */
    public static void m45() {
        if (C0002.m110() < 0) {
            Looper.loop();
        }
    }

    /* renamed from: ۡۤۦ۟  reason: not valid java name and contains not printable characters */
    public static int m46(Object obj, Object obj2) {
        if (m25() < 0) {
            return Log.d((String) obj, (String) obj2);
        }
        return 0;
    }

    /* renamed from: ۢ۠۠ۨ  reason: not valid java name and contains not printable characters */
    public static String[] m47(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            return ((String) obj).split((String) obj2);
        }
        return null;
    }

    /* renamed from: ۢ۠ۡۡ  reason: not valid java name and contains not printable characters */
    public static char[] m48(Object obj) {
        if (C0002.m110() < 0) {
            return ((String) obj).toCharArray();
        }
        return null;
    }

    /* renamed from: ۢۧۦۡ  reason: not valid java name and contains not printable characters */
    public static short[] m49() {
        if (C0022.m235() >= 0) {
            return f10short;
        }
        return null;
    }

    /* renamed from: ۢۧۨ  reason: not valid java name and contains not printable characters */
    public static boolean m50(char c) {
        if (m25() <= 0) {
            return Character.isLetter(c);
        }
        return false;
    }

    /* renamed from: ۢۨ۟ۧ  reason: not valid java name and contains not printable characters */
    public static Object m51(Object obj, int i) {
        if (m25() <= 0) {
            return ((List) obj).get(i);
        }
        return null;
    }

    /* renamed from: ۤ۠ۨ  reason: not valid java name and contains not printable characters */
    public static int m52(Object obj) {
        if (C0021.m196() < 0) {
            return ((String) obj).length();
        }
        return 0;
    }

    /* renamed from: ۤۨ۠۠  reason: not valid java name and contains not printable characters */
    public static File[] m53(Object obj) {
        if (C0022.m235() > 0) {
            return ((File) obj).listFiles();
        }
        return null;
    }

    /* renamed from: ۥۤ۠ۦ  reason: contains not printable characters */
    public static boolean m54(Object obj, Object obj2) {
        if (m25() <= 0) {
            return ((List) obj).add(obj2);
        }
        return false;
    }

    /* renamed from: ۥۤۤۨ  reason: contains not printable characters */
    public static StringBuilder m55(Object obj, char c) {
        if (C0022.m235() >= 0) {
            return ((StringBuilder) obj).append(c);
        }
        return null;
    }

    /* renamed from: ۥۥۢۧ  reason: contains not printable characters */
    public static Field[] m56(Object obj) {
        if (C0002.m110() <= 0) {
            return ((Class) obj).getDeclaredFields();
        }
        return null;
    }

    /* renamed from: ۥۨۥ  reason: contains not printable characters */
    public static String m57(Object obj, Object obj2, Object obj3) {
        if (m25() <= 0) {
            return ((String) obj).replace((CharSequence) obj2, (CharSequence) obj3);
        }
        return null;
    }

    /* renamed from: ۦۥۨۧ  reason: contains not printable characters */
    public static String m58(String str) {
        String m236 = C0022.m236();
        String m2362 = C0022.m236();
        for (int i = 0; i < 15; i++) {
            m236 = C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), m236), C0001.m100(i)));
            m2362 = C0021.m181(C0021.m176(C0022.m253(new StringBuffer(), m2362), ((int) (C0001.m67() * 10)) ^ i));
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(m52(str) / 2);
        for (int i2 = 0; i2 < m52(str); i2 += 2) {
            m59(byteArrayOutputStream, (m29(m236, C0022.m255(str, i2)) << 4) | m29(m236, C0022.m255(str, i2 + 1)));
        }
        byte[] m26 = m26(byteArrayOutputStream);
        String m89 = C0001.m89(m49(), 0, 1, 458);
        while (m52(m89) > 0) {
            m89 = C0022.m236();
            if (m52(m89) == 0) {
                m89 = C0022.m229(m49(), 1, 1, 1757);
            }
        }
        int m52 = m52(m89);
        int m522 = m52(m2362);
        for (int i3 = 0; i3 < m52; i3++) {
            m26[i3] = (byte) (m26[i3] ^ C0022.m255(m2362, i3 % m522));
        }
        for (int i4 = 0; i4 < m26.length; i4 = m52(C0022.m236()) + 1) {
        }
        return new String(m26);
    }

    /* renamed from: ۧ۠ۧ۠  reason: not valid java name and contains not printable characters */
    public static void m59(Object obj, int i) {
        if (C0002.m110() <= 0) {
            ((ByteArrayOutputStream) obj).write(i);
        }
    }

    /* renamed from: ۧۤ۟ۦ  reason: not valid java name and contains not printable characters */
    public static void m60(Object obj) {
        if (C0022.m235() > 0) {
            ((BufferedOutputStream) obj).close();
        }
    }

    /* renamed from: ۧۤۧۧ  reason: not valid java name and contains not printable characters */
    public static boolean m61(char c) {
        if (m25() < 0) {
            return Character.isUpperCase(c);
        }
        return false;
    }

    /* renamed from: ۧۦۤۨ  reason: not valid java name and contains not printable characters */
    public static void m62(int i) {
        if (C0021.m196() <= 0) {
            Process.killProcess(i);
        }
    }

    /* renamed from: ۨ۠ۡۨ  reason: not valid java name and contains not printable characters */
    public static boolean m63(Object obj, Object obj2) {
        if (C0002.m110() < 0) {
            return ((String) obj).equals(obj2);
        }
        return false;
    }

    /* renamed from: ۨۢ۠  reason: not valid java name and contains not printable characters */
    public static Class m64(Object obj) {
        if (C0022.m235() > 0) {
            return Class.forName((String) obj);
        }
        return null;
    }

    /* renamed from: ۣۨۨۢ  reason: not valid java name and contains not printable characters */
    public static String m65(Object obj, int i, int i2) {
        if (C0002.m110() < 0) {
            return ((String) obj).substring(i, i2);
        }
        return null;
    }

    /* renamed from: ۟ۥۥ۟  reason: not valid java name and contains not printable characters */
    public static String m34(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
