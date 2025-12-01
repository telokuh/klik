package mt;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟۟۟ۡ  reason: contains not printable characters */
/* loaded from: classes.dex */
class DialogInterface$OnClickListenerC0009 implements DialogInterface.OnClickListener {

    /* renamed from: short  reason: not valid java name */
    private static final short[] f22short = {650, 645, 640, 665, 651, 646, 648, 667, 653};

    /* renamed from: ۟۟۟۟  reason: not valid java name and contains not printable characters */
    private final C0007 f23;

    /* renamed from: ۟۟۟۟۟  reason: not valid java name and contains not printable characters */
    private final Context f24;

    /* renamed from: ۟۟۟۟۠  reason: not valid java name and contains not printable characters */
    private final String f25;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0009(C0007 c0007, Context context, String str) {
        this.f23 = c0007;
        this.f24 = context;
        this.f25 = str;
    }

    /* renamed from: ۣ۟ۤۧۨ  reason: not valid java name and contains not printable characters */
    public static short[] m128() {
        if (C0022.m235() >= 0) {
            return f22short;
        }
        return null;
    }

    /* renamed from: ۟ۥۣۡۤ  reason: not valid java name and contains not printable characters */
    public static Context m129(Object obj) {
        if (C0022.m235() >= 0) {
            return ((DialogInterface$OnClickListenerC0009) obj).f24;
        }
        return null;
    }

    /* renamed from: ۟ۦۦۧ۟  reason: not valid java name and contains not printable characters */
    public static String m130(Object obj) {
        if (C0021.m196() < 0) {
            return ((DialogInterface$OnClickListenerC0009) obj).f25;
        }
        return null;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        ClipboardManager clipboardManager = (ClipboardManager) C0021.m201(m129(this), C0021.m205(m128(), 0, 9, 745));
        if (clipboardManager != null) {
            C0001.m88(clipboardManager, C0022.m234(null, m130(this)));
        }
        C0001.m99(dialogInterface);
    }
}
