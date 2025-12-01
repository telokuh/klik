package mt;

import java.security.NoSuchAlgorithmException;
import mt.modder.hub.C0002;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟۟۠  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0017 {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f37short = {1661, 1652, 1541, 2947};

    /* renamed from: ۟۟۟۟ۡ  reason: not valid java name and contains not printable characters */
    public static String m164(String str) {
        if (C0022.m242(str)) {
            return C0022.m236();
        }
        try {
            byte[] m198 = C0021.m198(C0021.m220(C0001.m89(m166(), 0, 3, 1584)), C0000.m37(str));
            int i = 0;
            String m236 = C0022.m236();
            while (true) {
                int i2 = i;
                if (i2 >= m198.length) {
                    return C0022.m228(m236);
                }
                String m100 = C0001.m100(m198[i2] & 255);
                if (C0000.m52(m100) == 1) {
                    m100 = C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0000.m34(m166(), 3, 1, 2995)), m100));
                }
                m236 = C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), m236), m100));
                i = i2 + 1;
            }
        } catch (NoSuchAlgorithmException e) {
            C0021.m197(e);
            return C0022.m236();
        }
    }

    /* renamed from: ۟۟۟۟ۢ  reason: not valid java name and contains not printable characters */
    public static String m165(String str) {
        return C0022.m228(C0000.m65(C0022.m267(str), 8, 24));
    }

    /* renamed from: ۡ۟ۧۧ  reason: not valid java name and contains not printable characters */
    public static short[] m166() {
        if (C0002.m110() <= 0) {
            return f37short;
        }
        return null;
    }
}
