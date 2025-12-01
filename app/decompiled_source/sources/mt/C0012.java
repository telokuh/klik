package mt;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mt.modder.hub.C0002;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟۟۟ۤ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0012 implements Thread.UncaughtExceptionHandler {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f28short = {1719, 1719, 1719, 1719, 1763, 1667, 1667, 1763, 1706, 1706, 1763, 1670, 1670, 1763, 1699, 1699, 1763, 1725, 1725, 1315, 1330, 1313, 1331, 1320, 1389, 1651, 2810, 2744, 2747, 2739, 549, 551, 573, 550, 572, 557, 556, 825, 869, 882, 885, 887, 868, 882, 825, 885, 868, 887, 869, 894, 825, 424, 500, 483, 484, 486, 501, 483, 424, 484, 501, 486, 500, 495, 424, 1103, 1340, 2264, 2281, 2298, 2280, 2291, 2259, 2298, 2293, 2303, 2295, 2302, 2281, 641, 654, 704, 645, 658, 658, 655, 658, 704, 655, 643, 643, 661, 658, 645, 644, 704, 663, 648, 649, 652, 645, 704, 663, 658, 649, 660, 649, 654, 647, 704, 646, 649, 652, 645, 718, 718, 718, 1264, 1217, 1234, 1216, 1243, 1275, 1234, 1245, 1239, 1247, 1238, 1217, 555, 572, 572, 545, 572, 622, 628, 622, 642, 665, 640, 640, 1580, 1599, 1576, 1577, 1587, 1589, 1588, 1556, 1595, 1591, 1599, 3067, 3048, 3071, 3070, 3044, 3042, 3043, 3022, 3042, 3049, 3048, 2921, 2918, 2924, 2938, 2919, 2913, 2924, 2854, 2919, 2939, 2854, 2890, 2941, 2913, 2916, 2924, 1963, 1946, 1929, 1947, 1920, 1952, 1929, 1926, 1932, 1924, 1933, 1946, 1495, 1496, 1430, 1491, 1476, 1476, 1497, 1476, 1430, 1497, 1493, 1493, 1475, 1476, 1491, 1490, 1430, 1473, 1502, 1491, 1496, 1430, 1493, 1497, 1498, 1498, 1491, 1493, 1474, 1430, 1478, 1495, 1493, 1501, 1495, 1489, 1491, 1430, 1503, 1496, 1488, 1497, 2966, 2983, 2996, 2982, 3005, 2973, 2996, 3003, 2993, 3001, 2992, 2983, 2603, 2609, 2603, 1940, 1957, 1974, 1956, 1983, 1951, 1974, 1977, 1971, 1979, 1970, 1957, 1973, 1978, 2036, 1969, 1958, 1958, 1979, 1958, 2036, 1979, 1975, 1975, 1953, 1958, 1969, 1968, 2036, 1955, 1980, 1969, 1978, 2036, 1975, 1979, 1976, 1976, 1969, 1975, 1952, 2036, 1975, 1958, 1973, 1959, 1980, 2036, 1981, 1978, 1970, 1979};

    /* renamed from: ۟۟۟  reason: not valid java name and contains not printable characters */
    private Context f29;

    /* renamed from: ۣ۟۟۟۟  reason: not valid java name and contains not printable characters */
    private Thread.UncaughtExceptionHandler f32;

    /* renamed from: ۟۟۟۟ۢ  reason: not valid java name and contains not printable characters */
    private Map<String, String> f31 = new HashMap();

    /* renamed from: ۟۟۟۟ۡ  reason: not valid java name and contains not printable characters */
    private DateFormat f30 = new SimpleDateFormat(C0001.m89(m142(), 0, 19, 1742));

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    private boolean m140(Throwable th) {
        if (th == null) {
            return false;
        }
        m149(new C0013(this));
        C0022.m231(this, m147(this));
        m146(this, th);
        return true;
    }

    /* renamed from: ۟۟  reason: not valid java name and contains not printable characters */
    private String m141(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator m254 = C0022.m254(C0001.m70(m145(this)));
        while (C0000.m35(m254)) {
            Map.Entry entry = (Map.Entry) C0021.m188(m254);
            C0022.m253(stringBuffer, C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), (String) C0021.m212(entry)), C0001.m89(m142(), 65, 1, 1138)))), (String) C0000.m33(entry)))), C0021.m205(m142(), 66, 1, 1334))));
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        C0021.m213(th, printWriter);
        for (Throwable m21 = C0000.m21(th); m21 != null; m21 = C0000.m21(m21)) {
            C0021.m213(m21, printWriter);
        }
        C0001.m69(printWriter);
        C0022.m253(stringBuffer, C0021.m179(stringWriter));
        try {
            String m181 = C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0021.m181(C0021.m191(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0000.m34(m142(), 19, 6, 1344)), C0001.m85(m148(this), new Date())))), C0001.m89(m142(), 25, 1, 1630)))), C0022.m248()))), C0022.m229(m142(), 26, 4, 2772)));
            if (C0000.m63(C0022.m252(), C0001.m89(m142(), 30, 7, 584))) {
                File file = new File(C0001.m89(m142(), 37, 14, 790));
                if (!C0021.m193(file)) {
                    C0000.m42(file);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0022.m229(m142(), 51, 14, 391)), m181)));
                C0000.m40(fileOutputStream, C0000.m37(C0021.m181(stringBuffer)));
                C0021.m219(fileOutputStream);
                return m181;
            }
            return m181;
        } catch (Exception e) {
            C0022.m258(C0021.m205(m142(), 67, 12, 2203), C0000.m34(m142(), 79, 38, 736), e);
            return null;
        }
    }

    /* renamed from: ۟۟ۨ۠ۦ  reason: not valid java name and contains not printable characters */
    public static short[] m142() {
        if (C0002.m110() < 0) {
            return f28short;
        }
        return null;
    }

    /* renamed from: ۣ۟۠ۤۢ  reason: not valid java name and contains not printable characters */
    public static Thread.UncaughtExceptionHandler m143(Object obj) {
        if (C0022.m235() > 0) {
            return ((C0012) obj).f32;
        }
        return null;
    }

    /* renamed from: ۣۣۣ۟۠  reason: not valid java name and contains not printable characters */
    public static boolean m144(Object obj, Object obj2) {
        if (C0022.m235() > 0) {
            return ((C0012) obj).m140((Throwable) obj2);
        }
        return false;
    }

    /* renamed from: ۣ۟ۧۧۨ  reason: not valid java name and contains not printable characters */
    public static Map m145(Object obj) {
        if (C0021.m196() < 0) {
            return ((C0012) obj).f31;
        }
        return null;
    }

    /* renamed from: ۠۠۠۠  reason: not valid java name and contains not printable characters */
    public static String m146(Object obj, Object obj2) {
        if (C0022.m235() >= 0) {
            return ((C0012) obj).m141((Throwable) obj2);
        }
        return null;
    }

    /* renamed from: ۣۡۢۦ  reason: not valid java name and contains not printable characters */
    public static Context m147(Object obj) {
        if (C0021.m196() < 0) {
            return ((C0012) obj).f29;
        }
        return null;
    }

    /* renamed from: ۥۤۦۡ  reason: contains not printable characters */
    public static DateFormat m148(Object obj) {
        if (C0021.m196() <= 0) {
            return ((C0012) obj).f30;
        }
        return null;
    }

    /* renamed from: ۥۣۧۨ  reason: contains not printable characters */
    public static void m149(Object obj) {
        if (C0000.m25() < 0) {
            ((C0013) obj).start();
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (!m144(this, th) && m143(this) != null) {
            C0021.m187(m143(this), thread, th);
            return;
        }
        try {
            C0021.m207(1000);
        } catch (InterruptedException e) {
            C0022.m258(C0001.m89(m142(), 117, 12, 1203), C0000.m34(m142(), 129, 8, 590), e);
        }
        C0000.m62(C0022.m271());
        C0001.m68(1);
    }

    /* renamed from: ۟۟  reason: not valid java name and contains not printable characters */
    public void m150(Context context) {
        try {
            PackageInfo m94 = C0001.m94(C0022.m230(context), C0021.m185(context), 1);
            if (m94 != null) {
                String m89 = C0001.m101(m94) == null ? C0001.m89(m142(), 137, 4, 748) : C0001.m101(m94);
                String m181 = C0021.m181(C0022.m253(C0021.m176(new StringBuffer(), C0001.m79(m94)), C0022.m236()));
                C0000.m43(m145(this), C0022.m229(m142(), 141, 11, 1626), m89);
                C0000.m43(m145(this), C0022.m229(m142(), 152, 11, 2957), m181);
            }
        } catch (PackageManager.NameNotFoundException e) {
            C0022.m258(C0000.m34(m142(), 179, 12, 2024), C0000.m34(m142(), 191, 42, 1462), e);
        }
        try {
            Field[] m56 = C0000.m56(C0000.m64(C0001.m89(m142(), 163, 16, 2824)));
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= m56.length) {
                    return;
                }
                Field field = m56[i2];
                try {
                    C0021.m214(field, true);
                    C0000.m43(m145(this), C0022.m266(field), C0001.m66(C0000.m20(field, null)));
                    C0000.m46(C0021.m205(m142(), 233, 12, 3029), C0021.m181(C0021.m204(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0022.m266(field)), C0021.m205(m142(), 245, 3, 2571)))), C0000.m20(field, null))));
                } catch (Exception e2) {
                    C0022.m258(C0021.m205(m142(), 248, 12, 2007), C0021.m205(m142(), 260, 40, 2004), e2);
                }
                i = i2 + 1;
            }
        } catch (ClassNotFoundException e3) {
            throw new NoClassDefFoundError(C0000.m32(e3));
        }
    }

    /* renamed from: ۟۟۟  reason: not valid java name and contains not printable characters */
    public void m151(Context context) {
        this.f29 = context;
        this.f32 = C0001.m81();
        C0001.m84(this);
    }
}
