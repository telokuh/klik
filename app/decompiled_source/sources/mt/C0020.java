package mt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟۟۠ۡ */
/* loaded from: classes.dex */
public class C0020 {

    /* renamed from: short */
    private static final short[] f40short = {2074, 2065, 2054, 2065, 2142, 2053, 2052, 2073, 2076, 2142, 2097, 2050, 2050, 2065, 2057, 2108, 2073, 2051, 2052, 2497, 2506, 2525, 2506, 2437, 2498, 2500, 2437, 2541, 2498, 2503, 2510, 521, 514, 533, 514, 589, 534, 535, 522, 527, 589, 546, 529, 529, 514, 538, 559, 522, 528, 535, 1891, 1903, 1893, 1899, 1866, 1899, 1910, 1867, 1890, 1899, 1891, 1899, 1888, 1914, 1917};

    /* renamed from: ۟۟ */
    private static Object[] m172(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) {
        try {
            try {
                try {
                    return (Object[]) C0022.m273(C0022.m265(obj, C0022.m229(m174(), 50, 15, 1806), new Class[]{C0000.m64(C0001.m89(m174(), 0, 19, 2160)), C0000.m64(C0001.m89(m174(), 19, 12, 2475)), C0000.m64(C0000.m34(m174(), 31, 19, 611))}), obj, new Object[]{arrayList, file, arrayList2});
                } catch (ClassNotFoundException e) {
                    throw new NoClassDefFoundError(C0000.m32(e));
                }
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(C0000.m32(e2));
            }
        } catch (ClassNotFoundException e3) {
            throw new NoClassDefFoundError(C0000.m32(e3));
        }
    }

    /* renamed from: ۟ۡۧۥۤ */
    public static Object[] m173(Object obj, Object obj2, Object obj3, Object obj4) {
        if (C0021.m196() < 0) {
            return m172(obj, (ArrayList) obj2, (File) obj3, (ArrayList) obj4);
        }
        return null;
    }

    /* renamed from: ۟ۤۦۨۦ */
    public static short[] m174() {
        if (C0000.m25() < 0) {
            return f40short;
        }
        return null;
    }
}
