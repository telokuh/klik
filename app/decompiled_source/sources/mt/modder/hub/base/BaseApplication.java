package mt.modder.hub.base;

import android.app.Application;
import android.content.Context;
import java.io.IOException;
import java.util.zip.ZipFile;
import mt.C0012;
import mt.C0021;
import mt.C0022;
/* loaded from: classes.dex */
public class BaseApplication extends Application {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f8short = {2474, 2493, 2475, 2551, 2491, 2487, 2484, 2487, 2474, 2551, 958, 943, 943, 947, 950, 956, 958, 939, 950, 944, 945, 2157, 2145, 2147, 2080, 2159, 2151, 2154, 2155, 2080, 2171, 2151};

    /* renamed from: ۟۟۟  reason: not valid java name and contains not printable characters */
    static Context f9;

    /* renamed from: ۡۦ۠ۡ  reason: not valid java name and contains not printable characters */
    public static short[] m18() {
        if (C0000.m25() < 0) {
            return f8short;
        }
        return null;
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        try {
            ZipFile zipFile = new ZipFile(C0021.m217(this, context));
            if (C0021.m203(zipFile, C0021.m181(C0022.m253(C0022.m253(new StringBuffer(), C0022.m229(m18(), 0, 10, 2520)), C0021.m190(C0001.m89(m18(), 10, 11, 991))))) == null) {
            }
            C0021.m178(zipFile);
        } catch (IOException e) {
            C0022.m224(e);
        }
        super.attachBaseContext(context);
        f9 = context;
    }

    @Override // android.app.Application
    public void onCreate() {
        C0001.m103(this, C0022.m229(m18(), 21, 11, 2062));
        super.onCreate();
        C0001.m108(new C0012(), C0001.m91(this));
        f9 = C0001.m91(this);
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        C0001.m68(0);
    }

    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public String m19(Context context) {
        return C0022.m227(context);
    }
}
