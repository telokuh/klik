package mt.modder.hub;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipFile;
import mt.C0012;
import mt.C0018;
import mt.C0021;
import mt.C0022;
import mt.modder.hub.base.BaseApplication;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class MTApplication extends BaseApplication {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f0short = {2432, 2457, 2499, 2432, 2434, 2441, 2441, 2440, 2463, 2499, 2437, 2456, 2447, 2499, 2464, 2489, 2476, 2461, 2461, 2433, 2436, 2446, 2444, 2457, 2436, 2434, 2435, 2744, 2747, 2746, 2749, 2748, 2751, 2750, 2737, 2736, 2739, 2738, 2741, 2740, 2743, 2742, 2729, 2728, 2731, 2730, 2733, 2732, 2735, 2734, 2721, 2720, 2723, 2712, 2715, 2714, 2717, 2716, 2719, 2718, 2705, 2704, 2707, 2706, 2709, 2708, 2711, 2710, 2697, 2696, 2699, 2698, 2701, 2700, 2703, 2702, 2689, 2688, 2691, 2793, 2792, 2795, 2794, 2797, 2796, 2799, 2798, 2785, 2784, 1927, 1928, 1922, 1940, 1929, 1935, 1922, 1992, 1927, 1942, 1942, 1992, 1959, 1942, 1942, 1930, 1935, 1925, 1927, 1938, 1935, 1929, 1928, 1934, 1921, 1931, 1949, 1920, 1926, 1931, 1985, 1932, 1920, 1921, 1947, 1930, 1921, 1947, 1985, 1964, 1920, 1921, 1947, 1930, 1943, 1947, 3308, 3321, 3321, 3308, 3310, 3301, 1408, 1423, 1413, 1427, 1422, 1416, 1413, 1487, 1408, 1425, 1425, 1487, 1442, 1422, 1423, 1429, 1412, 1433, 1429, 1448, 1420, 1425, 1421, 407, 437, 399, 398, 415, 392, 441, 405, 404, 398, 415, 386, 398, 2542, 2510, 2530, 2538, 2541, 2519, 2539, 2545, 2534, 2530, 2535, 1715, 1724, 1718, 1696, 1725, 1723, 1718, 1788, 1715, 1698, 1698, 1788, 1683, 1713, 1702, 1723, 1700, 1723, 1702, 1707, 1670, 1722, 1696, 1719, 1715, 1718, 1543, 1571, 1540, 1539, 1566, 1539, 1547, 1542, 1579, 1562, 1562, 1542, 1539, 1545, 1547, 1566, 1539, 1541, 1540, 737, 717, 736, 736, 717, 764, 764, 736, 741, 751, 749, 760, 741, 739, 738, 767, 1381, 1368, 1385, 1387, 1379, 1385, 1391, 1389, 1345, 1382, 1390, 1383, 2487, 2488, 2482, 2468, 2489, 2495, 2482, 2552, 2487, 2470, 2470, 2552, 2458, 2489, 2487, 2482, 2483, 2482, 2455, 2470, 2493, 878, 834, 883, 883, 879, 874, 864, 866, 887, 874, 876, 877, 646, 682, 667, 667, 647, 642, 648, 650, 671, 642, 644, 645, 674, 645, 653, 644, 1533, 1516, 1529, 1509, 1473, 1508, 1534, 1529, 3026, 3027, 3022, 3059, 3034, 3027, 3035, 3027, 3032, 3010, 3013, 1687, 1692, 1675, 1692, 1747, 1672, 1673, 1684, 1681, 1747, 1713, 1684, 1678, 1673, 2953, 2946, 2965, 2946, 3021, 2954, 2956, 3021, 2981, 2954, 2959, 2950, 2071, 2076, 2059, 2076, 2131, 2056, 2057, 2068, 2065, 2131, 2097, 2068, 2062, 2057, 2746, 2742, 2748, 2738, 2695, 2742, 2723, 2751, 2706, 2747, 2738, 2746, 2738, 2745, 2723, 2724, 1712, 1697, 1716, 1704, 1676, 1705, 1715, 1716, 1377, 1376, 1405, 1344, 1385, 1376, 1384, 1376, 1387, 1393, 1398, 1856, 1857, 1884, 1889, 1864, 1857, 1865, 1857, 1866, 1872, 1879, 1911, 1873, 1876, 1876, 1878, 1857, 1879, 1879, 1857, 1856, 1889, 1884, 1863, 1857, 1876, 1872, 1869, 1867, 1866, 1879, 1409, 1468, 1447, 1441, 1460, 1456, 1453, 1451, 1450, 1508, 1453, 1450, 1508, 1449, 1445, 1455, 1441, 1408, 1441, 1468, 1409, 1448, 1441, 1449, 1441, 1450, 1456, 2188, 2205, 2184, 2196, 2224, 2197, 2191, 2184, 1893, 1892, 1913, 1860, 1901, 1892, 1900, 1892, 1903, 1909, 1906, 1906, 1907, 1902, 1875, 1914, 1907, 1915, 1907, 1912, 1890, 1893, 1861, 1891, 1894, 1894, 1892, 1907, 1893, 1893, 1907, 1906, 1875, 1902, 1909, 1907, 1894, 1890, 1919, 1913, 1912, 1893, 952, 901, 926, 920, 909, 905, 916, 914, 915, 989, 916, 915, 989, 912, 924, 918, 920, 953, 920, 901, 952, 913, 920, 912, 920, 915, 905, 264, 281, 268, 272, 308, 273, 267, 268, 2288, 2289, 2284, 2257, 2296, 2289, 2297, 2289, 2298, 2272, 2279, 3016, 3017, 3016, 786, 781, 777, 2622, 2601, 2623, 2659, 2607, 2595, 2592, 2595, 2622, 2659, 2336, 2353, 2353, 2349, 2344, 2338, 2336, 2357, 2344, 2350, 2351, 2641, 2648, 2607, 2685, 2603, 2655, 2597, 2635, 2648, 2646, 2639, 2607, 2648, 2646, 2639, 2607, 905, 904, 922, 1009, 996, 2127, 2142, 2142, 2114, 2119, 2125, 2127, 2138, 2119, 2113, 2112, 2747, 2746, 2727, 2477, 2490, 2476, 2544, 2492, 2480, 2483, 2480, 2477, 2544, 3060, 3006, 3007, 2978, 3705, 3696, 3591, 3669, 3587, 3703, 3597, 3683, 3696, 3710, 3687, 3591, 3696, 3710, 3687, 3591, 1643, 1639, 1637, 1574, 1641, 1633, 1644, 1645, 1574, 1661, 1633};

    /* renamed from: ۟۟۟۟ۥ  reason: not valid java name and contains not printable characters */
    public static final String f1;

    /* renamed from: ۟۟۟۟ۦ  reason: not valid java name and contains not printable characters */
    String f2;

    /* renamed from: ۟۟۟۟ۧ  reason: not valid java name and contains not printable characters */
    Application f3;

    /* renamed from: ۟۟۟۟ۨ  reason: not valid java name and contains not printable characters */
    File f4;

    /* renamed from: ۟۟۟۠  reason: not valid java name and contains not printable characters */
    List<File> f5;

    /* renamed from: ۟۟۟۠۟  reason: not valid java name and contains not printable characters */
    boolean f6;

    /* renamed from: ۟۟۟۠۠  reason: not valid java name and contains not printable characters */
    File f7;

    static {
        try {
            f1 = C0022.m269(C0000.m64(C0021.m205(m15(), 0, 27, 2541)));
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(C0000.m32(e));
        }
    }

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public static String m0(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            C0001.m90(stringBuffer, C0022.m255(C0001.m89(m15(), 27, 62, 2777), C0000.m28(random, 62)));
        }
        return C0021.m181(stringBuffer);
    }

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    private static void m1(Object obj, String str, Object[] objArr) {
        Field m264 = C0022.m264(obj, str);
        Object[] objArr2 = (Object[]) C0000.m20(m264, obj);
        Object[] objArr3 = (Object[]) C0022.m256(C0001.m75(C0021.m211(objArr2)), objArr2.length + objArr.length);
        C0000.m27(objArr2, 0, objArr3, 0, objArr2.length);
        C0000.m27(objArr, 0, objArr3, objArr2.length, objArr.length);
        C0022.m259(m264, obj, objArr3);
    }

    /* renamed from: ۟۟  reason: not valid java name and contains not printable characters */
    private void m2() {
        if (m5(this) || C0022.m242(m10(this))) {
            return;
        }
        Context m23 = C0000.m23(this);
        this.f3 = (Application) m7(C0000.m64(m10(this)));
        try {
            try {
                Method m215 = C0021.m215(C0000.m64(C0021.m205(m15(), 89, 23, 2022)), C0022.m229(m15(), 135, 6, 3213), new Class[]{C0000.m64(C0001.m89(m15(), 112, 23, 2031))});
                C0022.m270(m215, true);
                C0022.m273(m215, m14(this), new Object[]{m23});
                Class m64 = C0000.m64(C0022.m229(m15(), 141, 23, 1505));
                Field m200 = C0021.m200(m64, C0001.m89(m15(), 164, 13, 506));
                C0021.m214(m200, true);
                C0022.m259(m200, m23, m14(this));
                Field m2002 = C0021.m200(m64, C0022.m229(m15(), 177, 11, 2435));
                C0021.m214(m2002, true);
                Object m20 = C0000.m20(m2002, m23);
                Class m642 = C0000.m64(C0022.m229(m15(), 188, 26, 1746));
                Field m2003 = C0021.m200(m642, C0021.m205(m15(), 214, 19, 1642));
                C0021.m214(m2003, true);
                C0022.m259(m2003, m20, m14(this));
                Field m2004 = C0021.m200(m642, C0000.m34(m15(), 233, 16, 652));
                C0021.m214(m2004, true);
                ArrayList arrayList = (ArrayList) C0000.m20(m2004, m20);
                C0000.m39(arrayList, this);
                C0001.m98(arrayList, m14(this));
                Field m2005 = C0021.m200(m64, C0022.m229(m15(), 249, 12, 1288));
                C0021.m214(m2005, true);
                Object m202 = C0000.m20(m2005, m23);
                Class m643 = C0000.m64(C0001.m89(m15(), 261, 21, 2518));
                Field m2006 = C0021.m200(m643, C0001.m89(m15(), 282, 12, 771));
                C0021.m214(m2006, true);
                C0022.m259(m2006, m202, m14(this));
                Field m2007 = C0021.m200(m643, C0021.m205(m15(), 294, 16, 747));
                C0021.m214(m2007, true);
                ((ApplicationInfo) C0000.m20(m2007, m202)).className = m10(this);
                switch (1) {
                    case 1:
                    default:
                        C0021.m189(m14(this));
                        this.f6 = true;
                        C0001.m80(new C0018(this));
                        C0022.m257(this);
                        return;
                }
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(C0000.m32(e));
            }
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(C0000.m32(e2));
        }
    }

    /* renamed from: ۟۟۟  reason: not valid java name and contains not printable characters */
    private void m3() {
        IOException[] iOExceptionArr;
        IOException[] iOExceptionArr2;
        ClassLoader m194 = C0021.m194(this);
        if (C0000.m24() >= 23) {
            Object m20 = C0000.m20(C0022.m264(m194, C0000.m34(m15(), 310, 8, 1421)), m194);
            Field m264 = C0022.m264(m20, C0001.m89(m15(), 318, 11, 2998));
            Object[] objArr = (Object[]) C0000.m20(m264, m20);
            try {
                try {
                    try {
                        Object[] objArr2 = (Object[]) C0022.m273(C0022.m265(m20, C0001.m89(m15(), 369, 16, 2775), new Class[]{C0000.m64(C0022.m229(m15(), 329, 14, 1789)), C0000.m64(C0000.m34(m15(), 343, 12, 3043)), C0000.m64(C0000.m34(m15(), 355, 14, 2173))}), m20, new Object[]{m6(this), m8(this), new ArrayList()});
                        Object[] objArr3 = (Object[]) C0022.m256(C0001.m75(C0021.m211(objArr)), objArr.length + objArr2.length);
                        C0000.m27(objArr, 0, objArr3, 0, objArr.length);
                        C0000.m27(objArr2, 0, objArr3, objArr.length, objArr2.length);
                        C0022.m259(m264, m20, objArr3);
                    } catch (ClassNotFoundException e) {
                        throw new NoClassDefFoundError(C0000.m32(e));
                    }
                } catch (ClassNotFoundException e2) {
                    throw new NoClassDefFoundError(C0000.m32(e2));
                }
            } catch (ClassNotFoundException e3) {
                throw new NoClassDefFoundError(C0000.m32(e3));
            }
        } else if (C0000.m24() >= 21 && C0000.m24() < 23) {
            Object m202 = C0000.m20(C0022.m264(m194, C0022.m229(m15(), 385, 8, 1728)), m194);
            ArrayList arrayList = new ArrayList();
            m16(m202, C0001.m89(m15(), 393, 11, 1285), C0000.m41(m202, new ArrayList(m6(this)), m8(this), arrayList));
            if (C0022.m226(arrayList) > 0) {
                Iterator m254 = C0022.m254(arrayList);
                while (C0000.m35(m254)) {
                    C0022.m246(C0022.m225(), C0000.m34(m15(), 435, 27, 1476), (IOException) C0021.m188(m254));
                }
                Field m2642 = C0022.m264(m194, C0001.m89(m15(), 404, 31, 1828));
                IOException[] iOExceptionArr3 = (IOException[]) C0000.m20(m2642, m194);
                if (iOExceptionArr3 == null) {
                    iOExceptionArr2 = (IOException[]) C0001.m74(arrayList, new IOException[C0022.m226(arrayList)]);
                } else {
                    IOException[] iOExceptionArr4 = new IOException[C0022.m226(arrayList) + iOExceptionArr3.length];
                    C0001.m74(arrayList, iOExceptionArr4);
                    C0000.m27(iOExceptionArr3, 0, iOExceptionArr4, C0022.m226(arrayList), iOExceptionArr3.length);
                    iOExceptionArr2 = iOExceptionArr4;
                }
                C0022.m259(m2642, m194, iOExceptionArr2);
            }
        } else if (C0000.m24() != 19) {
            if (C0000.m24() < 14 || C0000.m24() > 18) {
                return;
            }
            Object m203 = C0000.m20(C0022.m264(m194, C0022.m229(m15(), 539, 8, 376)), m194);
            m16(m203, C0022.m229(m15(), 547, 11, 2196), C0001.m93(m203, new ArrayList(m6(this)), m8(this)));
        } else {
            Object m204 = C0000.m20(C0022.m264(m194, C0021.m205(m15(), 462, 8, 2300)), m194);
            ArrayList arrayList2 = new ArrayList();
            m16(m204, C0021.m205(m15(), 470, 11, 1793), C0000.m41(m204, new ArrayList(m6(this)), m8(this), arrayList2));
            if (C0022.m226(arrayList2) > 0) {
                Iterator m2542 = C0022.m254(arrayList2);
                while (C0000.m35(m2542)) {
                    C0022.m246(C0022.m225(), C0001.m89(m15(), 512, 27, 1021), (IOException) C0021.m188(m2542));
                }
                Field m2643 = C0022.m264(m194, C0022.m229(m15(), 481, 31, 1814));
                IOException[] iOExceptionArr5 = (IOException[]) C0000.m20(m2643, m194);
                if (iOExceptionArr5 == null) {
                    iOExceptionArr = (IOException[]) C0001.m74(arrayList2, new IOException[C0022.m226(arrayList2)]);
                } else {
                    IOException[] iOExceptionArr6 = new IOException[C0022.m226(arrayList2) + iOExceptionArr5.length];
                    C0001.m74(arrayList2, iOExceptionArr6);
                    C0000.m27(iOExceptionArr5, 0, iOExceptionArr6, C0022.m226(arrayList2), iOExceptionArr5.length);
                    iOExceptionArr = iOExceptionArr6;
                }
                C0022.m259(m2643, m194, iOExceptionArr);
            }
        }
    }

    /* renamed from: ۟۟۟  reason: not valid java name and contains not printable characters */
    public static byte[] m4(byte[] bArr, String str) {
        byte[] m37 = C0000.m37(str);
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (bArr[i] ^ m37[i % m37.length]);
        }
        return bArr2;
    }

    /* renamed from: ۟۟ۡ۠۟  reason: not valid java name and contains not printable characters */
    public static boolean m5(Object obj) {
        if (C0021.m196() <= 0) {
            return ((MTApplication) obj).f6;
        }
        return false;
    }

    /* renamed from: ۟ۡۧۧۥ  reason: not valid java name and contains not printable characters */
    public static List m6(Object obj) {
        if (C0002.m110() < 0) {
            return ((MTApplication) obj).f5;
        }
        return null;
    }

    /* renamed from: ۣۣۣ۟ۦ  reason: not valid java name and contains not printable characters */
    public static Object m7(Object obj) {
        if (C0002.m110() <= 0) {
            return ((Class) obj).newInstance();
        }
        return null;
    }

    /* renamed from: ۣۣ۟ۥۢ  reason: not valid java name and contains not printable characters */
    public static File m8(Object obj) {
        if (C0000.m25() < 0) {
            return ((MTApplication) obj).f7;
        }
        return null;
    }

    /* renamed from: ۣ۟ۧۥ۠  reason: not valid java name and contains not printable characters */
    public static void m9(Object obj) {
        if (C0000.m25() < 0) {
            ((MTApplication) obj).m3();
        }
    }

    /* renamed from: ۣ۟ۨ۠۟  reason: not valid java name and contains not printable characters */
    public static String m10(Object obj) {
        if (C0000.m25() < 0) {
            return ((MTApplication) obj).f2;
        }
        return null;
    }

    /* renamed from: ۟ۤۧۧ۠  reason: not valid java name and contains not printable characters */
    public static void m11(Object obj) {
        if (C0021.m196() <= 0) {
            ((MTApplication) obj).m2();
        }
    }

    /* renamed from: ۡۨ۟ۨ  reason: not valid java name and contains not printable characters */
    public static File m12(Object obj) {
        if (C0021.m196() <= 0) {
            return ((MTApplication) obj).f4;
        }
        return null;
    }

    /* renamed from: ۢۢۢۦ  reason: not valid java name and contains not printable characters */
    public static String m13(Object obj, Object obj2) {
        if (C0000.m25() < 0) {
            return ((MTApplication) obj).m19((Context) obj2);
        }
        return null;
    }

    /* renamed from: ۣۢۦۦ  reason: not valid java name and contains not printable characters */
    public static Application m14(Object obj) {
        if (C0022.m235() > 0) {
            return ((MTApplication) obj).f3;
        }
        return null;
    }

    /* renamed from: ۧۡۢ۟  reason: not valid java name and contains not printable characters */
    public static short[] m15() {
        if (C0021.m196() <= 0) {
            return f0short;
        }
        return null;
    }

    /* renamed from: ۨۡۢ۟  reason: not valid java name and contains not printable characters */
    public static void m16(Object obj, Object obj2, Object obj3) {
        if (C0021.m196() < 0) {
            m1(obj, (String) obj2, (Object[]) obj3);
        }
    }

    @Override // mt.modder.hub.base.BaseApplication, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        int i = 0;
        this.f5 = new ArrayList();
        this.f4 = C0001.m96(context, C0000.m34(m15(), 558, 3, 2988), 0);
        this.f7 = new File(C0021.m195(context), C0001.m89(m15(), 561, 3, 893));
        C0021.m199(C0000.m30(m12(this)));
        C0022.m274(C0000.m30(m12(this)));
        C0022.m274(C0000.m30(m8(this)));
        try {
            ZipFile zipFile = new ZipFile(m13(this, context));
            String str = new String(C0021.m175(C0022.m262(context, C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0000.m34(m15(), 564, 10, 2636)), C0021.m190(C0021.m205(m15(), 574, 11, 2369))))), C0022.m229(m15(), 585, 16, 360)), C0001.m89(m15(), 601, 5, 988));
            C0022.m274(C0000.m30(m12(this)));
            JSONObject jSONObject = new JSONObject(str);
            this.f2 = C0021.m180(jSONObject, C0000.m34(m15(), 606, 11, 2094));
            ArrayList m73 = C0001.m73(C0021.m180(jSONObject, C0022.m229(m15(), 617, 3, 2783)));
            while (true) {
                int i2 = i;
                if (i2 >= C0022.m268(m73)) {
                    break;
                }
                InputStream m44 = C0000.m44(zipFile, C0021.m203(zipFile, C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0000.m34(m15(), 620, 10, 2527)), (String) C0000.m51(m73, i2)))));
                File file = new File(m12(this), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0022.m272(32)), C0001.m89(m15(), 630, 4, 3034))));
                C0021.m202(C0000.m38(C0001.m72(m44), C0001.m89(m15(), 634, 16, 1344)), C0000.m30(file));
                C0000.m54(m6(this), file);
                i = i2 + 1;
            }
        } catch (Exception e) {
            C0001.m83(e);
        }
        super.attachBaseContext(context);
        switch (1) {
            case 1:
            default:
                switch (1) {
                    case 1:
                    default:
                        switch (1) {
                            case 1:
                            default:
                                switch (1) {
                                    case 1:
                                    default:
                                        switch (1) {
                                            case 1:
                                            default:
                                                switch (1) {
                                                    case 1:
                                                    default:
                                                        switch (1) {
                                                            case 1:
                                                                try {
                                                                    m9(this);
                                                                    try {
                                                                        C0021.m199(C0000.m30(m12(this)));
                                                                        Iterator m254 = C0022.m254(m6(this));
                                                                        while (C0000.m35(m254)) {
                                                                            C0021.m177((File) C0021.m188(m254));
                                                                        }
                                                                        return;
                                                                    } catch (Exception e2) {
                                                                        C0001.m83(e2);
                                                                        return;
                                                                    }
                                                                } catch (Exception e3) {
                                                                    C0001.m83(e3);
                                                                    return;
                                                                }
                                                            default:
                                                                return;
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createPackageContext(String str, int i) {
        if (C0022.m242(m10(this))) {
            return super.createPackageContext(str, i);
        }
        try {
            m11(this);
        } catch (Exception e) {
            C0001.m83(e);
        }
        return m14(this);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageName() {
        return !C0022.m242(m10(this)) ? C0022.m236() : super.getPackageName();
    }

    @Override // mt.modder.hub.base.BaseApplication, android.app.Application
    public void onCreate() {
        C0001.m103(this, C0022.m229(m15(), 650, 11, 1544));
        super.onCreate();
        try {
            m11(this);
        } catch (Exception e) {
            C0001.m83(e);
        }
    }

    /* renamed from: ۟۟۟۟  reason: not valid java name and contains not printable characters */
    public void m17() {
        C0001.m108(new C0012(), C0021.m208(this));
    }
}
