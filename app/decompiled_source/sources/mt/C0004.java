package mt;

import android.content.Context;
import android.content.Intent;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0004 {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f16short = {2957, 2945, 2947, 3008, 2959, 2954, 2972, 2970, 3008, 2978, 2977, 2985, 2989, 2991, 3002, 2993, 2987, 2976, 3002, 3004, 2983, 2987, 3005, 2609, 2612, 2611, 2616, 2606};

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    private static Context f17;

    /* renamed from: ۟۟  reason: not valid java name and contains not printable characters */
    private static String f18;

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public static void m116(Context context, String str) {
        f17 = context;
        f18 = str;
    }

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public static void m117(String[] strArr) {
        Intent intent = new Intent();
        C0022.m251(intent, m118());
        C0000.m31(intent, C0022.m229(m120(), 0, 23, 3054));
        C0022.m263(intent, C0021.m205(m120(), 23, 5, 2653), strArr);
        C0001.m102(m119(), intent);
    }

    /* renamed from: ۟۠ۡۡۡ  reason: not valid java name and contains not printable characters */
    public static String m118() {
        if (C0000.m25() <= 0) {
            return f18;
        }
        return null;
    }

    /* renamed from: ۟ۢۤۤ۠  reason: not valid java name and contains not printable characters */
    public static Context m119() {
        if (C0022.m235() >= 0) {
            return f17;
        }
        return null;
    }

    /* renamed from: ۟ۧۤۥۡ  reason: not valid java name and contains not printable characters */
    public static short[] m120() {
        if (C0000.m25() <= 0) {
            return f16short;
        }
        return null;
    }
}
