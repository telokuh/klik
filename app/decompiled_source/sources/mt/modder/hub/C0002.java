package mt.modder.hub;

import java.io.ByteArrayOutputStream;
import mt.C0021;
import mt.C0022;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.modder.hub.ۣ۟۠ۥۢ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0002 {

    /* renamed from: ۣ۟ۡۧۨ  reason: not valid java name and contains not printable characters */
    public static boolean f13;

    /* renamed from: ۟۠۟ۡۥ  reason: not valid java name and contains not printable characters */
    public static int m110() {
        return (-1751456) ^ C0022.m238((Object) "ۤ۟۟");
    }

    /* renamed from: ۟ۡۡۧۢ  reason: not valid java name and contains not printable characters */
    public static String m111(String str) {
        String m236 = C0022.m236();
        String m2362 = C0022.m236();
        for (int i = 0; i < 15; i++) {
            m236 = C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), m236), C0001.m100(i)));
            m2362 = C0021.m181(C0021.m176(C0022.m253(new StringBuffer(), m2362), ((int) (C0001.m67() * 10)) ^ i));
        }
        do {
        } while (C0000.m52(m236) > 0);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(C0000.m52(str) / 2);
        for (int i2 = 0; i2 < C0000.m52(str); i2 += 2) {
            C0000.m59(byteArrayOutputStream, (C0000.m29(m236, C0022.m255(str, i2)) << 4) | C0000.m29(m236, C0022.m255(str, i2 + 1)));
        }
        byte[] m26 = C0000.m26(byteArrayOutputStream);
        int length = m26.length;
        int m52 = C0000.m52(m2362);
        for (int i3 = 0; i3 < length; i3++) {
            m26[i3] = (byte) (m26[i3] ^ C0022.m255(m2362, i3 % m52));
        }
        return new String(m26);
    }

    /* renamed from: ۣ۟ۢۦ۠  reason: not valid java name and contains not printable characters */
    public static String m112(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
