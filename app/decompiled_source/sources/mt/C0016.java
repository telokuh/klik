package mt;

import java.util.ArrayList;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟۟۟ۨ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0016 {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f36short = {2799, 2051};

    /* renamed from: ۟۟۟۟۠  reason: not valid java name and contains not printable characters */
    public static ArrayList<String> m162(String str) {
        if (C0001.m76(str, C0000.m34(m163(), 0, 1, 2755))) {
            return new ArrayList<>(C0022.m245(C0000.m47(str, C0000.m34(m163(), 1, 1, 2095))));
        }
        if (C0021.m206(str)) {
            ArrayList<String> arrayList = new ArrayList<>();
            C0001.m98(arrayList, C0022.m236());
            return arrayList;
        }
        ArrayList<String> arrayList2 = new ArrayList<>();
        C0001.m98(arrayList2, str);
        return arrayList2;
    }

    /* renamed from: ۟۟ۢۤۨ  reason: not valid java name and contains not printable characters */
    public static short[] m163() {
        if (C0000.m25() <= 0) {
            return f36short;
        }
        return null;
    }
}
