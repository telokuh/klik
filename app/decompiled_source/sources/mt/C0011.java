package mt;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import mt.modder.hub.C0002;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.ۣ۟۟۟۟  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0011 {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f27short = {1533, 1529, 1519, 1427, 1529, 1535, 1534, 1427, 1516, 1527, 1535, 1519, 1417, 1516, 1501, 1496, 1496, 1493, 1490, 1499, 423, 419, 437};

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public static byte[] m135(byte[] bArr, String str) {
        byte[] bArr2 = null;
        try {
            Key m137 = m137(C0021.m218(str));
            Cipher m78 = C0001.m78(C0000.m34(m138(), 0, 20, 1468));
            C0022.m222(m78, 2, m137);
            return C0001.m97(m78, bArr);
        } catch (Exception e) {
            C0001.m83(e);
            return bArr2;
        }
    }

    /* renamed from: ۟۟  reason: not valid java name and contains not printable characters */
    private static Key m136(String str) {
        try {
            return new SecretKeySpec(C0000.m37(str), C0001.m89(m138(), 20, 3, 486));
        } catch (Exception e) {
            C0001.m83(e);
            throw e;
        }
    }

    /* renamed from: ۟ۤۡۥ۠  reason: not valid java name and contains not printable characters */
    public static Key m137(Object obj) {
        if (C0000.m25() <= 0) {
            return m136((String) obj);
        }
        return null;
    }

    /* renamed from: ۡۥۨۨ  reason: not valid java name and contains not printable characters */
    public static short[] m138() {
        if (C0002.m110() < 0) {
            return f27short;
        }
        return null;
    }
}
