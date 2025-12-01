package mt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟۟  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0005 {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f19short = {1625, 1654, 1658, 1651, 1659, 1599, 432, 510, 511, 484, 432, 502, 511, 485, 510, 500, 432, 505, 510, 432, 3031, 3071, 3054, 3058, 3061, 3070, 3002, 2547, 2468, 2490, 2471, 2491, 2547, 2467, 2482, 2465, 2482, 2494, 2486, 2471, 2486, 2465, 2464, 2547, 1280, 1358, 1359, 1364, 1280, 1350, 1359, 1365, 1358, 1348, 1280, 1353, 1358, 1280};

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public static Field m121(Object obj, String str) {
        for (Class m106 = C0001.m106(obj); m106 != null; m106 = C0001.m71(m106)) {
            try {
                Field m200 = C0021.m200(m106, str);
                if (!C0001.m92(m200)) {
                    C0021.m214(m200, true);
                }
                return m200;
            } catch (NoSuchFieldException e) {
            }
        }
        throw new NoSuchFieldException(C0021.m181(C0021.m204(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0021.m205(m123(), 0, 6, 1567)), str))), C0001.m89(m123(), 6, 14, 400)))), C0001.m106(obj))));
    }

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public static Method m122(Object obj, String str, Class... clsArr) {
        for (Class m106 = C0001.m106(obj); m106 != null; m106 = C0001.m71(m106)) {
            try {
                Method m215 = C0021.m215(m106, str, clsArr);
                if (!C0022.m244(m215)) {
                    C0022.m270(m215, true);
                }
                return m215;
            } catch (NoSuchMethodException e) {
            }
        }
        throw new NoSuchMethodException(C0021.m181(C0021.m204(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0021.m181(C0021.m204(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0022.m229(m123(), 20, 7, 2970)), str))), C0021.m205(m123(), 27, 17, 2515)))), C0022.m245(clsArr)))), C0001.m89(m123(), 44, 14, 1312)))), C0001.m106(obj))));
    }

    /* renamed from: ۢ۠ۢۥ  reason: not valid java name and contains not printable characters */
    public static short[] m123() {
        if (C0021.m196() < 0) {
            return f19short;
        }
        return null;
    }
}
