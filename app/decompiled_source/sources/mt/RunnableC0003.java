package mt;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟  reason: contains not printable characters */
/* loaded from: classes.dex */
public class RunnableC0003 implements Runnable {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f14short = {1849, 1818, 1810, 1846, 1812, 1793, 1678, 1677, 1669, 1665, 1667, 1686, 1730, 1743, 1684, 1730, 1686, 1674, 1680, 1671, 1667, 1670, 1686, 1675, 1679, 1671};

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    private static Context f15;

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public static void m113(Context context, String str) {
        if (m114() != null) {
            return;
        }
        f15 = C0022.m260(context);
        if ((C0022.m241(C0001.m86(context)) & 2) != 0) {
            try {
                C0001.m94(C0022.m230(context), str, 128);
                C0022.m233(m114(), str);
                C0001.m82(new Thread(new RunnableC0003(), C0001.m89(m115(), 0, 6, 1909)));
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
    }

    /* renamed from: ۟ۤۥ۠ۤ  reason: not valid java name and contains not printable characters */
    public static Context m114() {
        if (C0000.m25() < 0) {
            return f15;
        }
        return null;
    }

    /* renamed from: ۟ۤۧۧ۠  reason: not valid java name and contains not printable characters */
    public static short[] m115() {
        if (C0000.m25() <= 0) {
            return f14short;
        }
        return null;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(C0022.m250(C0022.m223(C0000.m22(), C0001.m89(m115(), 6, 20, 1762)))), 20);
            while (true) {
                String m247 = C0022.m247(bufferedReader);
                if (m247 == null) {
                    return;
                }
                C0021.m186(new String[]{m247});
            }
        } catch (IOException e) {
        }
    }
}
