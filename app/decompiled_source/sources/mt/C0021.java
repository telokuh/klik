package mt;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import mt.modder.hub.C0002;
import mt.modder.hub.MTApplication;
import mt.modder.hub.base.BaseApplication;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
import org.json.JSONObject;
/* renamed from: mt.ۤۨۢ۟  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0021 {

    /* renamed from: ۨۢۨ۠  reason: not valid java name and contains not printable characters */
    public static boolean f41;

    /* renamed from: ۟۟۟۟ۢ  reason: not valid java name and contains not printable characters */
    public static byte[] m175(Object obj, Object obj2) {
        if (C0022.m235() > 0) {
            return C0011.m135((byte[]) obj, (String) obj2);
        }
        return null;
    }

    /* renamed from: ۟۟ۥۥۢ  reason: not valid java name and contains not printable characters */
    public static StringBuffer m176(Object obj, int i) {
        if (C0022.m235() >= 0) {
            return ((StringBuffer) obj).append(i);
        }
        return null;
    }

    /* renamed from: ۣ۟۟ۨۧ  reason: not valid java name and contains not printable characters */
    public static boolean m177(Object obj) {
        if (C0002.m110() <= 0) {
            return ((File) obj).delete();
        }
        return false;
    }

    /* renamed from: ۟ۡۤ۠ۥ  reason: not valid java name and contains not printable characters */
    public static void m178(Object obj) {
        if (C0000.m25() < 0) {
            ((ZipFile) obj).close();
        }
    }

    /* renamed from: ۣ۟ۡۤۨ  reason: not valid java name and contains not printable characters */
    public static String m179(Object obj) {
        if (m196() <= 0) {
            return ((Writer) obj).toString();
        }
        return null;
    }

    /* renamed from: ۟ۡۥۡ۠  reason: not valid java name and contains not printable characters */
    public static String m180(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            return ((JSONObject) obj).getString((String) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۢ۟۟۠  reason: not valid java name and contains not printable characters */
    public static String m181(Object obj) {
        if (m196() <= 0) {
            return ((StringBuffer) obj).toString();
        }
        return null;
    }

    /* renamed from: ۟ۢۢۧۨ  reason: not valid java name and contains not printable characters */
    public static void m182() {
        if (C0002.m110() <= 0) {
            Looper.prepare();
        }
    }

    /* renamed from: ۟ۢۢۨۨ  reason: not valid java name and contains not printable characters */
    public static AlertDialog.Builder m183(Object obj, Object obj2, Object obj3) {
        if (C0002.m110() <= 0) {
            return ((AlertDialog.Builder) obj).setNeutralButton((CharSequence) obj2, (DialogInterface.OnClickListener) obj3);
        }
        return null;
    }

    /* renamed from: ۣ۟ۢۨۡ  reason: not valid java name and contains not printable characters */
    public static void m184(Object obj) {
        if (C0022.m235() > 0) {
            ((Toast) obj).show();
        }
    }

    /* renamed from: ۟ۢۥۨ۠  reason: not valid java name and contains not printable characters */
    public static String m185(Object obj) {
        if (C0002.m110() <= 0) {
            return ((Context) obj).getPackageName();
        }
        return null;
    }

    /* renamed from: ۣ۟ۢۨۨ  reason: not valid java name and contains not printable characters */
    public static void m186(Object obj) {
        if (m196() < 0) {
            C0004.m117((String[]) obj);
        }
    }

    /* renamed from: ۣ۟۟ۤ۠  reason: not valid java name and contains not printable characters */
    public static void m187(Object obj, Object obj2, Object obj3) {
        if (C0002.m110() < 0) {
            ((Thread.UncaughtExceptionHandler) obj).uncaughtException((Thread) obj2, (Throwable) obj3);
        }
    }

    /* renamed from: ۣ۟ۢۧ۠  reason: not valid java name and contains not printable characters */
    public static Object m188(Object obj) {
        if (C0000.m25() < 0) {
            return ((Iterator) obj).next();
        }
        return null;
    }

    /* renamed from: ۣ۟ۥۢۥ  reason: not valid java name and contains not printable characters */
    public static void m189(Object obj) {
        if (C0002.m110() < 0) {
            ((Application) obj).onCreate();
        }
    }

    /* renamed from: ۣۣ۟ۨۢ  reason: not valid java name and contains not printable characters */
    public static String m190(Object obj) {
        if (C0022.m235() > 0) {
            return C0010.m131((String) obj);
        }
        return null;
    }

    /* renamed from: ۟ۤۡۤۡ  reason: not valid java name and contains not printable characters */
    public static StringBuffer m191(Object obj, long j) {
        if (C0022.m235() > 0) {
            return ((StringBuffer) obj).append(j);
        }
        return null;
    }

    /* renamed from: ۣ۟ۤۢۨ  reason: not valid java name and contains not printable characters */
    public static void m192(Object obj) {
        if (C0002.m110() < 0) {
            ((AlertDialog) obj).show();
        }
    }

    /* renamed from: ۟ۤۤۨ۟  reason: not valid java name and contains not printable characters */
    public static boolean m193(Object obj) {
        if (C0002.m110() <= 0) {
            return ((File) obj).exists();
        }
        return false;
    }

    /* renamed from: ۣ۟ۤۧۥ  reason: not valid java name and contains not printable characters */
    public static ClassLoader m194(Object obj) {
        if (C0000.m25() < 0) {
            return ((MTApplication) obj).getClassLoader();
        }
        return null;
    }

    /* renamed from: ۟ۤۧۦ۠  reason: not valid java name and contains not printable characters */
    public static File m195(Object obj) {
        if (C0022.m235() > 0) {
            return ((Context) obj).getFilesDir();
        }
        return null;
    }

    /* renamed from: ۟ۥۡ۠  reason: not valid java name and contains not printable characters */
    public static int m196() {
        return (-1747736) ^ C0022.m238((Object) "۠ۤۢ");
    }

    /* renamed from: ۟ۥۡۤۦ  reason: not valid java name and contains not printable characters */
    public static void m197(Object obj) {
        if (C0022.m235() > 0) {
            ((NoSuchAlgorithmException) obj).printStackTrace();
        }
    }

    /* renamed from: ۟ۥۡۧۥ  reason: not valid java name and contains not printable characters */
    public static byte[] m198(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            return ((MessageDigest) obj).digest((byte[]) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۥۡۨۡ  reason: not valid java name and contains not printable characters */
    public static void m199(Object obj) {
        if (C0022.m235() > 0) {
            C0014.m156((String) obj);
        }
    }

    /* renamed from: ۟ۦ۟ۡۧ  reason: not valid java name and contains not printable characters */
    public static Field m200(Object obj, Object obj2) {
        if (m196() <= 0) {
            return ((Class) obj).getDeclaredField((String) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۦ۠ۥ۟  reason: not valid java name and contains not printable characters */
    public static Object m201(Object obj, Object obj2) {
        if (C0000.m25() <= 0) {
            return ((Context) obj).getSystemService((String) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۦۥۡۡ  reason: not valid java name and contains not printable characters */
    public static void m202(Object obj, Object obj2) {
        if (C0002.m110() < 0) {
            C0015.m159((byte[]) obj, (String) obj2);
        }
    }

    /* renamed from: ۟ۦۧۧ  reason: not valid java name and contains not printable characters */
    public static ZipEntry m203(Object obj, Object obj2) {
        if (m196() <= 0) {
            return ((ZipFile) obj).getEntry((String) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۧ۠ۥۧ  reason: not valid java name and contains not printable characters */
    public static StringBuffer m204(Object obj, Object obj2) {
        if (m196() < 0) {
            return ((StringBuffer) obj).append(obj2);
        }
        return null;
    }

    /* renamed from: ۠ۧۤ  reason: not valid java name and contains not printable characters */
    public static boolean m206(Object obj) {
        if (C0002.m110() <= 0) {
            return ((String) obj).isEmpty();
        }
        return false;
    }

    /* renamed from: ۠ۨۧ۠  reason: not valid java name and contains not printable characters */
    public static void m207(long j) {
        if (C0022.m235() > 0) {
            Thread.sleep(j);
        }
    }

    /* renamed from: ۠ۨۨۤ  reason: not valid java name and contains not printable characters */
    public static Context m208(Object obj) {
        if (C0002.m110() < 0) {
            return ((MTApplication) obj).getApplicationContext();
        }
        return null;
    }

    /* renamed from: ۣۡ۠  reason: not valid java name and contains not printable characters */
    public static void m209(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            C0006.m125((Context) obj, (String) obj2);
        }
    }

    /* renamed from: ۡۤۢۥ  reason: not valid java name and contains not printable characters */
    public static AlertDialog.Builder m210(Object obj, Object obj2, Object obj3) {
        if (C0022.m235() > 0) {
            return ((AlertDialog.Builder) obj).setPositiveButton((CharSequence) obj2, (DialogInterface.OnClickListener) obj3);
        }
        return null;
    }

    /* renamed from: ۢ۠  reason: not valid java name and contains not printable characters */
    public static Class m211(Object obj) {
        if (C0022.m235() > 0) {
            return ((Object[]) obj).getClass();
        }
        return null;
    }

    /* renamed from: ۢۢۡۢ  reason: not valid java name and contains not printable characters */
    public static Object m212(Object obj) {
        if (m196() <= 0) {
            return ((Map.Entry) obj).getKey();
        }
        return null;
    }

    /* renamed from: ۢۢۤۧ  reason: not valid java name and contains not printable characters */
    public static void m213(Object obj, Object obj2) {
        if (C0002.m110() <= 0) {
            ((Throwable) obj).printStackTrace((PrintWriter) obj2);
        }
    }

    /* renamed from: ۢۦ۟۠  reason: not valid java name and contains not printable characters */
    public static void m214(Object obj, boolean z) {
        if (C0022.m235() >= 0) {
            ((Field) obj).setAccessible(z);
        }
    }

    /* renamed from: ۣۢ۠ۧ  reason: not valid java name and contains not printable characters */
    public static Method m215(Object obj, Object obj2, Object obj3) {
        if (m196() < 0) {
            return ((Class) obj).getDeclaredMethod((String) obj2, (Class[]) obj3);
        }
        return null;
    }

    /* renamed from: ۣۥۡۤ  reason: not valid java name and contains not printable characters */
    public static boolean m216(Object obj) {
        if (C0000.m25() < 0) {
            return ((File) obj).isDirectory();
        }
        return false;
    }

    /* renamed from: ۣۣۨ  reason: not valid java name and contains not printable characters */
    public static String m217(Object obj, Object obj2) {
        if (m196() < 0) {
            return ((BaseApplication) obj).m19((Context) obj2);
        }
        return null;
    }

    /* renamed from: ۤۥ۟ۨ  reason: not valid java name and contains not printable characters */
    public static String m218(Object obj) {
        if (m196() < 0) {
            return C0017.m165((String) obj);
        }
        return null;
    }

    /* renamed from: ۦۡۤ۠  reason: contains not printable characters */
    public static void m219(Object obj) {
        if (C0022.m235() >= 0) {
            ((FileOutputStream) obj).close();
        }
    }

    /* renamed from: ۧۨۥ۟  reason: not valid java name and contains not printable characters */
    public static MessageDigest m220(Object obj) {
        if (C0000.m25() <= 0) {
            return MessageDigest.getInstance((String) obj);
        }
        return null;
    }

    /* renamed from: ۨۤۢۢ  reason: not valid java name and contains not printable characters */
    public static String m221(String str) {
        String m236 = C0022.m236();
        String m2362 = C0022.m236();
        for (int i = 0; i < 15; i++) {
            m236 = m181(C0022.m253(C0022.m253(new StringBuffer(), m236), C0001.m100(i)));
            m2362 = m181(m176(C0022.m253(new StringBuffer(), m2362), ((int) (C0001.m67() * 10)) ^ i));
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(C0000.m52(str) / 2);
        for (int i2 = 0; i2 < C0000.m52(str); i2 += 2) {
            C0000.m59(byteArrayOutputStream, (C0000.m29(m236, C0022.m255(str, i2)) << 4) | C0000.m29(m236, C0022.m255(str, i2 + 1)));
        }
        byte[] m26 = C0000.m26(byteArrayOutputStream);
        int length = m26.length;
        int m52 = C0000.m52(m2362);
        while (length > 0) {
            m26[-1] = (byte) (m26[-1] ^ C0022.m255(m2362, (-1) % m52));
        }
        for (int i3 = 0; i3 < m26.length; i3 = C0000.m52(C0022.m236()) + 1) {
        }
        return new String(m26);
    }

    /* renamed from: ۣ۟ۨ۠  reason: not valid java name and contains not printable characters */
    public static String m205(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
