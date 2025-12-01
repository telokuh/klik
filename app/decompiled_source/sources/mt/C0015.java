package mt;

import android.content.Context;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipFile;
import mt.modder.hub.base.C0000;
import mt.modder.hub.base.C0001;
/* renamed from: mt.۟۟۟۟ۧ  reason: contains not printable characters */
/* loaded from: classes.dex */
public class C0015 {
    /* renamed from: ۟  reason: not valid java name and contains not printable characters */
    public static byte[] m158(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int m261 = C0022.m261(inputStream, bArr);
            if (m261 == -1) {
                return C0000.m26(byteArrayOutputStream);
            }
            C0001.m104(byteArrayOutputStream, bArr, 0, m261);
        }
    }

    /* renamed from: ۟۟  reason: not valid java name and contains not printable characters */
    public static void m159(byte[] bArr, String str) {
        File file = new File(str);
        if (!C0021.m193(file) && C0021.m216(file)) {
            C0000.m42(file);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        C0022.m232(bufferedOutputStream, bArr);
        if (bufferedOutputStream != null) {
            C0000.m60(bufferedOutputStream);
        }
        if (fileOutputStream != null) {
            C0021.m219(fileOutputStream);
        }
    }

    /* renamed from: ۟۟۟  reason: not valid java name and contains not printable characters */
    public static byte[] m160(Context context, String str) {
        try {
            ZipFile zipFile = new ZipFile(C0022.m227(context));
            return C0001.m72(C0000.m44(zipFile, C0021.m203(zipFile, str)));
        } catch (IOException e) {
            C0022.m224(e);
            return null;
        }
    }

    /* renamed from: ۟۟۟۟۟  reason: not valid java name and contains not printable characters */
    public static void m161(String str) {
        try {
            File file = new File(str);
            if (C0021.m193(file)) {
                return;
            }
            C0000.m42(file);
        } catch (Exception e) {
        }
    }
}
