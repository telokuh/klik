package mt;

import java.io.File;
import java.io.IOException;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟۟۟ۦ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0014 {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f35short = {1040, 1039, 1090, 1103, 1040};

    /* renamed from: ۟۟۟  reason: not valid java name and contains not printable characters */
    public static void m155(String str) {
        try {
            C0022.m223(C0000.m22(), C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0001.m89(m157(), 0, 5, 1122)), str)));
        } catch (IOException e) {
        }
    }

    /* renamed from: ۟۟۟۟  reason: not valid java name and contains not printable characters */
    public static void m156(String str) {
        File[] m53;
        for (File file : C0000.m53(new File(str))) {
            if (C0001.m95(file)) {
                C0001.m77(C0000.m30(file));
            } else {
                C0021.m199(C0000.m30(file));
            }
        }
    }

    /* renamed from: ۟ۧ۠ۢ۟  reason: not valid java name and contains not printable characters */
    public static short[] m157() {
        if (C0021.m196() <= 0) {
            return f35short;
        }
        return null;
    }
}
