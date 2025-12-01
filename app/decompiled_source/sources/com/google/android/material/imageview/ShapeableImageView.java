package com.google.android.material.imageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
/* loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements Shapeable {
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_ShapeableImageView;
    private final Paint borderPaint;
    private final Paint clearPaint;
    private final RectF destination;
    private Path maskPath;
    private final RectF maskRect;
    private final Path path;
    private final ShapeAppearancePathProvider pathProvider;
    private final MaterialShapeDrawable shadowDrawable;
    private ShapeAppearanceModel shapeAppearanceModel;
    private ColorStateList strokeColor;
    private float strokeWidth;

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public ShapeableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyle, DEF_STYLE_RES), attrs, defStyle);
        this.pathProvider = new ShapeAppearancePathProvider();
        this.path = new Path();
        Context context2 = getContext();
        this.clearPaint = new Paint();
        this.clearPaint.setAntiAlias(true);
        this.clearPaint.setColor(-1);
        this.clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.destination = new RectF();
        this.maskRect = new RectF();
        this.maskPath = new Path();
        TypedArray attributes = context2.obtainStyledAttributes(attrs, R.styleable.ShapeableImageView, defStyle, DEF_STYLE_RES);
        this.strokeColor = MaterialResources.getColorStateList(context2, attributes, R.styleable.ShapeableImageView_strokeColor);
        this.strokeWidth = attributes.getDimensionPixelSize(R.styleable.ShapeableImageView_strokeWidth, 0);
        this.borderPaint = new Paint();
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setAntiAlias(true);
        this.shapeAppearanceModel = ShapeAppearanceModel.builder(context2, attrs, defStyle, DEF_STYLE_RES).build();
        this.shadowDrawable = new MaterialShapeDrawable(this.shapeAppearanceModel);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new OutlineProvider());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        setLayerType(0, null);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setLayerType(2, null);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.maskPath, this.clearPaint);
        drawStroke(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        updateShapeMask(width, height);
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.shapeAppearanceModel = shapeAppearanceModel;
        this.shadowDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        updateShapeMask(getWidth(), getHeight());
        invalidate();
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    private void updateShapeMask(int width, int height) {
        this.destination.set(getPaddingLeft(), getPaddingTop(), width - getPaddingRight(), height - getPaddingBottom());
        this.pathProvider.calculatePath(this.shapeAppearanceModel, 1.0f, this.destination, this.path);
        this.maskPath.rewind();
        this.maskPath.addPath(this.path);
        this.maskRect.set(0.0f, 0.0f, width, height);
        this.maskPath.addRect(this.maskRect, Path.Direction.CCW);
    }

    private void drawStroke(Canvas canvas) {
        if (this.strokeColor == null) {
            return;
        }
        this.borderPaint.setStrokeWidth(this.strokeWidth);
        int colorForState = this.strokeColor.getColorForState(getDrawableState(), this.strokeColor.getDefaultColor());
        if (this.strokeWidth > 0.0f && colorForState != 0) {
            this.borderPaint.setColor(colorForState);
            canvas.drawPath(this.path, this.borderPaint);
        }
    }

    public void setStrokeColorResource(int strokeColorResourceId) {
        setStrokeColor(AppCompatResources.getColorStateList(getContext(), strokeColorResourceId));
    }

    public ColorStateList getStrokeColor() {
        return this.strokeColor;
    }

    public void setStrokeWidth(float strokeWidth) {
        if (this.strokeWidth != strokeWidth) {
            this.strokeWidth = strokeWidth;
            invalidate();
        }
    }

    public void setStrokeWidthResource(int strokeWidthResourceId) {
        setStrokeWidth(getResources().getDimensionPixelSize(strokeWidthResourceId));
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeColor(ColorStateList strokeColor) {
        this.strokeColor = strokeColor;
        invalidate();
    }

    /* loaded from: classes.dex */
    class OutlineProvider extends ViewOutlineProvider {
        private final Rect rect = new Rect();

        OutlineProvider() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (ShapeableImageView.this.shapeAppearanceModel != null) {
                ShapeableImageView.this.destination.round(this.rect);
                ShapeableImageView.this.shadowDrawable.setBounds(this.rect);
                ShapeableImageView.this.shadowDrawable.getOutline(outline);
            }
        }
    }
}
