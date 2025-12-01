package mt;

import mt.modder.hub.C0002;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟۟۟ۢ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0010 {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f26short = {3100, 3157};

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public static String m131(String str) {
        String[] m47;
        String str2 = str;
        if (C0001.m76(str2, C0022.m229(m133(), 0, 1, 3123))) {
            for (String str3 : C0000.m47(str2, C0000.m34(m133(), 1, 1, 3194))) {
                str2 = C0000.m57(str2, str3, m134(str3, 1));
            }
            return str2;
        }
        return m134(str2, 1);
    }

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    private static String m132(String str, int i) {
        char[] m48;
        int i2 = (i % 26) + 26;
        StringBuilder sb = new StringBuilder();
        for (char c : C0000.m48(str)) {
            if (!C0000.m50(c)) {
                C0000.m55(sb, c);
            } else if (C0000.m61(c)) {
                C0000.m55(sb, (char) ((((c - 'A') + i2) % 26) + 65));
            } else {
                C0000.m55(sb, (char) ((((c - 'a') + i2) % 26) + 97));
            }
        }
        return C0022.m243(sb);
    }

    /* renamed from: ۟ۦ۟ۥۤ  reason: not valid java name and contains not printable characters */
    public static short[] m133() {
        if (C0021.m196() < 0) {
            return f26short;
        }
        return null;
    }

    /* renamed from: ۣۡۡۢ  reason: not valid java name and contains not printable characters */
    public static String m134(Object obj, int i) {
        if (C0002.m110() < 0) {
            return m132((String) obj, i);
        }
        return null;
    }
}
