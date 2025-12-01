package mt;

import java.io.File;
import java.util.ArrayList;
import mt.modder.hub.base.C0000;
/* renamed from: mt.۟۟۟۠۠ */
/* loaded from: classes.dex */
public class C0019 {

    /* renamed from: short */
    private static final short[] f39short = {2927, 2916, 2931, 2916, 2859, 2928, 2929, 2924, 2921, 2859, 2884, 2935, 2935, 2916, 2940, 2889, 2924, 2934, 2929, 1489, 1498, 1485, 1498, 1429, 1490, 1492, 1429, 1533, 1490, 1495, 1502, 1423, 1411, 1417, 1415, 1446, 1415, 1434, 1447, 1422, 1415, 1423, 1415, 1420, 1430, 1425};

    /* renamed from: ۟۟ */
    private static Object[] m168(Object obj, ArrayList<File> arrayList, File file) {
        try {
            try {
                return (Object[]) C0022.m273(C0022.m265(obj, C0022.m229(m170(), 31, 15, 1506), new Class[]{C0000.m64(C0022.m229(m170(), 0, 19, 2821)), C0000.m64(C0022.m229(m170(), 19, 12, 1467))}), obj, new Object[]{arrayList, file});
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(C0000.m32(e));
            }
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(C0000.m32(e2));
        }
    }

    /* renamed from: ۟ۧۥۥ */
    public static Object[] m169(Object obj, Object obj2, Object obj3) {
        if (C0021.m196() < 0) {
            return m168(obj, (ArrayList) obj2, (File) obj3);
        }
        return null;
    }

    /* renamed from: ۠ۥۡۥ */
    public static short[] m170() {
        if (C0021.m196() <= 0) {
            return f39short;
        }
        return null;
    }
}
