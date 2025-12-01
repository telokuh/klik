package mt;

import android.content.Context;
import mt.modder.hub.base.C0000;
/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: mt.۟۟۟۟ۥ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0013 extends Thread {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f33short = {21614, 26967, 24751, -2838, 29165, 21865, 23068, 30806, 21732, 21982};

    /* renamed from: ۟۟۟۟ۤ  reason: not valid java name and contains not printable characters */
    private final C0012 f34;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0013(C0012 c0012) {
        this.f34 = c0012;
    }

    /* renamed from: ۟ۦۡۦ۠  reason: not valid java name and contains not printable characters */
    public static Context m152(Object obj) {
        Context m147;
        if (C0021.m196() <= 0) {
            m147 = C0012.m147((C0012) obj);
            return m147;
        }
        return null;
    }

    /* renamed from: ۟ۦۥۤۤ  reason: not valid java name and contains not printable characters */
    public static C0012 m153(Object obj) {
        if (C0000.m25() < 0) {
            return ((C0013) obj).f34;
        }
        return null;
    }

    /* renamed from: ۣ۟ۤۨ  reason: not valid java name and contains not printable characters */
    public static short[] m154() {
        if (C0021.m196() < 0) {
            return f33short;
        }
        return null;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        C0021.m182();
        C0021.m209(m152(m153(this)), C0000.m34(m154(), 0, 10, 3046));
        C0000.m45();
    }
}
