package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.R;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.view.GravityCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.badge.BadgeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = BadgeDrawable.TOP_START;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.LinearLayoutCompat, defStyleAttr, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, R.styleable.LinearLayoutCompat, attrs, a.getWrappedTypeArray(), defStyleAttr, 0);
        int index = a.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (index >= 0) {
            setOrientation(index);
        }
        int index2 = a.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (index2 >= 0) {
            setGravity(index2);
        }
        boolean baselineAligned = a.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!baselineAligned) {
            setBaselineAligned(baselineAligned);
        }
        this.mWeightSum = a.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = a.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = a.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = a.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = a.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        a.recycle();
    }

    public void setShowDividers(int showDividers) {
        if (showDividers != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = showDividers;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable divider) {
        if (divider == this.mDivider) {
            return;
        }
        this.mDivider = divider;
        if (divider != null) {
            this.mDividerWidth = divider.getIntrinsicWidth();
            this.mDividerHeight = divider.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(divider == null);
        requestLayout();
    }

    public void setDividerPadding(int padding) {
        this.mDividerPadding = padding;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int bottom;
        int count = getVirtualChildCount();
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child != null && child.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int top = (child.getTop() - lp.topMargin) - this.mDividerHeight;
                drawHorizontalDivider(canvas, top);
            }
        }
        if (hasDividerBeforeChildAt(count)) {
            View child2 = getVirtualChildAt(count - 1);
            if (child2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                bottom = child2.getBottom() + lp2.bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int position;
        int position2;
        int count = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child != null && child.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (isLayoutRtl) {
                    position2 = child.getRight() + lp.rightMargin;
                } else {
                    int position3 = child.getLeft();
                    position2 = (position3 - lp.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, position2);
            }
        }
        if (hasDividerBeforeChildAt(count)) {
            View child2 = getVirtualChildAt(count - 1);
            if (child2 == null) {
                if (isLayoutRtl) {
                    position = getPaddingLeft();
                } else {
                    int position4 = getWidth();
                    position = (position4 - getPaddingRight()) - this.mDividerWidth;
                }
            } else {
                LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                if (isLayoutRtl) {
                    position = (child2.getLeft() - lp2.leftMargin) - this.mDividerWidth;
                } else {
                    int position5 = child2.getRight();
                    position = position5 + lp2.rightMargin;
                }
            }
            drawVerticalDivider(canvas, position);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int top) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, top, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + top);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int left) {
        this.mDivider.setBounds(left, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + left, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean baselineAligned) {
        this.mBaselineAligned = baselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean enabled) {
        this.mUseLargestChild = enabled;
    }

    @Override // android.view.View
    public int getBaseline() {
        int majorGravity;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i = this.mBaselineAlignedChildIndex;
        if (childCount <= i) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View child = getChildAt(i);
        int childBaseline = child.getBaseline();
        if (childBaseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int childTop = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (majorGravity = this.mGravity & 112) != 48) {
            if (majorGravity == 16) {
                childTop += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
            } else if (majorGravity == 80) {
                childTop = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
            }
        }
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        return lp.topMargin + childTop + childBaseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int index) {
        return getChildAt(index);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float weightSum) {
        this.mWeightSum = Math.max(0.0f, weightSum);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mOrientation == 1) {
            measureVertical(widthMeasureSpec, heightMeasureSpec);
        } else {
            measureHorizontal(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasDividerBeforeChildAt(int childIndex) {
        if (childIndex == 0) {
            return (this.mShowDividers & 1) != 0;
        } else if (childIndex == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (int i = childIndex - 1; i >= 0; i--) {
                if (getChildAt(i).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:176:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:194:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
        int count;
        int heightMode;
        int maxWidth;
        float totalWeight;
        int delta;
        int maxWidth2;
        int alternativeMaxWidth;
        int largestChildHeight;
        int heightMode2;
        int baselineChildIndex;
        float weightSum;
        int delta2;
        int maxWidth3;
        boolean allFillParent;
        int delta3;
        int maxWidth4;
        float totalWeight2;
        int delta4;
        int i;
        int i2;
        int oldHeight;
        LayoutParams lp;
        int childState;
        int maxWidth5;
        int heightMode3;
        int heightMode4;
        int weightedMaxWidth;
        int weightedMaxWidth2;
        int alternativeMaxWidth2;
        View child;
        int alternativeMaxWidth3;
        int weightedMaxWidth3;
        this.mTotalLength = 0;
        int childState2 = 0;
        float totalWeight3 = 0.0f;
        int count2 = getVirtualChildCount();
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode5 = View.MeasureSpec.getMode(heightMeasureSpec);
        int baselineChildIndex2 = this.mBaselineAlignedChildIndex;
        boolean useLargestChild = this.mUseLargestChild;
        boolean matchWidth = false;
        int alternativeMaxWidth4 = 0;
        int alternativeMaxWidth5 = 0;
        int maxWidth6 = 0;
        int weightedMaxWidth4 = 0;
        int largestChildHeight2 = 0;
        int i3 = 0;
        boolean allFillParent2 = true;
        while (true) {
            int weightedMaxWidth5 = maxWidth6;
            if (i3 < count2) {
                View child2 = getVirtualChildAt(i3);
                if (child2 == null) {
                    this.mTotalLength += measureNullChild(i3);
                    heightMode3 = heightMode5;
                    maxWidth6 = weightedMaxWidth5;
                    weightedMaxWidth2 = count2;
                } else {
                    int childState3 = childState2;
                    int childState4 = child2.getVisibility();
                    if (childState4 == 8) {
                        i3 += getChildrenSkipCount(child2, i3);
                        heightMode3 = heightMode5;
                        maxWidth6 = weightedMaxWidth5;
                        childState2 = childState3;
                        weightedMaxWidth2 = count2;
                    } else {
                        if (hasDividerBeforeChildAt(i3)) {
                            this.mTotalLength += this.mDividerHeight;
                        }
                        LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
                        float totalWeight4 = totalWeight3 + lp2.weight;
                        if (heightMode5 != 1073741824 || lp2.height != 0 || lp2.weight <= 0.0f) {
                            int maxWidth7 = alternativeMaxWidth5;
                            if (lp2.height == 0 && lp2.weight > 0.0f) {
                                lp2.height = -2;
                                oldHeight = 0;
                            } else {
                                oldHeight = Integer.MIN_VALUE;
                            }
                            lp = lp2;
                            childState = childState3;
                            maxWidth5 = maxWidth7;
                            heightMode3 = heightMode5;
                            heightMode4 = weightedMaxWidth4;
                            weightedMaxWidth = weightedMaxWidth5;
                            weightedMaxWidth2 = count2;
                            int oldHeight2 = oldHeight;
                            alternativeMaxWidth2 = alternativeMaxWidth4;
                            int alternativeMaxWidth6 = totalWeight4 == 0.0f ? this.mTotalLength : 0;
                            measureChildBeforeLayout(child2, i3, widthMeasureSpec, 0, heightMeasureSpec, alternativeMaxWidth6);
                            if (oldHeight2 != Integer.MIN_VALUE) {
                                lp.height = oldHeight2;
                            }
                            int childHeight = child2.getMeasuredHeight();
                            int totalLength = this.mTotalLength;
                            child = child2;
                            this.mTotalLength = Math.max(totalLength, totalLength + childHeight + lp.topMargin + lp.bottomMargin + getNextLocationOffset(child));
                            if (useLargestChild) {
                                heightMode4 = Math.max(childHeight, heightMode4);
                            }
                        } else {
                            int totalLength2 = this.mTotalLength;
                            int maxWidth8 = alternativeMaxWidth5;
                            int maxWidth9 = lp2.bottomMargin;
                            this.mTotalLength = Math.max(totalLength2, lp2.topMargin + totalLength2 + maxWidth9);
                            largestChildHeight2 = 1;
                            lp = lp2;
                            alternativeMaxWidth2 = alternativeMaxWidth4;
                            heightMode3 = heightMode5;
                            weightedMaxWidth = weightedMaxWidth5;
                            childState = childState3;
                            maxWidth5 = maxWidth8;
                            heightMode4 = weightedMaxWidth4;
                            child = child2;
                            weightedMaxWidth2 = count2;
                        }
                        if (baselineChildIndex2 >= 0 && baselineChildIndex2 == i3 + 1) {
                            this.mBaselineChildTop = this.mTotalLength;
                        }
                        if (i3 < baselineChildIndex2 && lp.weight > 0.0f) {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                        boolean matchWidthLocally = false;
                        if (widthMode != 1073741824 && lp.width == -1) {
                            matchWidth = true;
                            matchWidthLocally = true;
                        }
                        int margin = lp.leftMargin + lp.rightMargin;
                        int measuredWidth = child.getMeasuredWidth() + margin;
                        int maxWidth10 = Math.max(maxWidth5, measuredWidth);
                        int childState5 = View.combineMeasuredStates(childState, child.getMeasuredState());
                        boolean allFillParent3 = allFillParent2 && lp.width == -1;
                        if (lp.weight > 0.0f) {
                            weightedMaxWidth3 = Math.max(weightedMaxWidth, matchWidthLocally ? margin : measuredWidth);
                            alternativeMaxWidth3 = alternativeMaxWidth2;
                        } else {
                            int weightedMaxWidth6 = weightedMaxWidth;
                            alternativeMaxWidth3 = Math.max(alternativeMaxWidth2, matchWidthLocally ? margin : measuredWidth);
                            weightedMaxWidth3 = weightedMaxWidth6;
                        }
                        int weightedMaxWidth7 = getChildrenSkipCount(child, i3);
                        i3 += weightedMaxWidth7;
                        alternativeMaxWidth5 = maxWidth10;
                        childState2 = childState5;
                        allFillParent2 = allFillParent3;
                        maxWidth6 = weightedMaxWidth3;
                        weightedMaxWidth4 = heightMode4;
                        totalWeight3 = totalWeight4;
                        alternativeMaxWidth4 = alternativeMaxWidth3;
                    }
                }
                i3++;
                count2 = weightedMaxWidth2;
                heightMode5 = heightMode3;
            } else {
                int childState6 = childState2;
                int maxWidth11 = alternativeMaxWidth5;
                int heightMode6 = heightMode5;
                int maxWidth12 = 8;
                int largestChildHeight3 = weightedMaxWidth4;
                int alternativeMaxWidth7 = alternativeMaxWidth4;
                int weightedMaxWidth8 = count2;
                int weightedMaxWidth9 = weightedMaxWidth5;
                int largestChildHeight4 = this.mTotalLength;
                if (largestChildHeight4 > 0) {
                    count = weightedMaxWidth8;
                    if (hasDividerBeforeChildAt(count)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    count = weightedMaxWidth8;
                }
                if (useLargestChild) {
                    heightMode = heightMode6;
                    if (heightMode == Integer.MIN_VALUE || heightMode == 0) {
                        this.mTotalLength = 0;
                        int i4 = 0;
                        while (i4 < count) {
                            View child3 = getVirtualChildAt(i4);
                            if (child3 == null) {
                                this.mTotalLength += measureNullChild(i4);
                                i = i4;
                            } else if (child3.getVisibility() == maxWidth12) {
                                i2 = i4 + getChildrenSkipCount(child3, i4);
                                i4 = i2 + 1;
                                maxWidth12 = 8;
                            } else {
                                LayoutParams lp3 = (LayoutParams) child3.getLayoutParams();
                                int totalLength3 = this.mTotalLength;
                                i = i4;
                                int i5 = lp3.topMargin;
                                this.mTotalLength = Math.max(totalLength3, totalLength3 + largestChildHeight3 + i5 + lp3.bottomMargin + getNextLocationOffset(child3));
                            }
                            i2 = i;
                            i4 = i2 + 1;
                            maxWidth12 = 8;
                        }
                    }
                } else {
                    heightMode = heightMode6;
                }
                this.mTotalLength += getPaddingTop() + getPaddingBottom();
                int heightSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), heightMeasureSpec, 0);
                int heightSize = heightSizeAndState & ViewCompat.MEASURED_SIZE_MASK;
                int delta5 = heightSize - this.mTotalLength;
                if (largestChildHeight2 != 0) {
                    maxWidth = maxWidth11;
                    totalWeight = totalWeight3;
                    delta = delta5;
                } else if (delta5 == 0 || totalWeight3 <= 0.0f) {
                    int alternativeMaxWidth8 = Math.max(alternativeMaxWidth7, weightedMaxWidth9);
                    if (useLargestChild) {
                        alternativeMaxWidth = alternativeMaxWidth8;
                        if (heightMode != 1073741824) {
                            int i6 = 0;
                            while (i6 < count) {
                                int heightSize2 = heightSize;
                                View child4 = getVirtualChildAt(i6);
                                if (child4 != null) {
                                    maxWidth4 = maxWidth11;
                                    int maxWidth13 = child4.getVisibility();
                                    totalWeight2 = totalWeight3;
                                    if (maxWidth13 == 8) {
                                        delta4 = delta5;
                                    } else if (((LayoutParams) child4.getLayoutParams()).weight > 0.0f) {
                                        delta4 = delta5;
                                        child4.measure(View.MeasureSpec.makeMeasureSpec(child4.getMeasuredWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(largestChildHeight3, BasicMeasure.EXACTLY));
                                    } else {
                                        delta4 = delta5;
                                    }
                                } else {
                                    maxWidth4 = maxWidth11;
                                    totalWeight2 = totalWeight3;
                                    delta4 = delta5;
                                }
                                i6++;
                                heightSize = heightSize2;
                                totalWeight3 = totalWeight2;
                                delta5 = delta4;
                                maxWidth11 = maxWidth4;
                            }
                            maxWidth2 = maxWidth11;
                            delta3 = delta5;
                        } else {
                            maxWidth2 = maxWidth11;
                            delta3 = delta5;
                        }
                    } else {
                        alternativeMaxWidth = alternativeMaxWidth8;
                        maxWidth2 = maxWidth11;
                        delta3 = delta5;
                    }
                    largestChildHeight = widthMeasureSpec;
                    if (!allFillParent2 && widthMode != 1073741824) {
                        maxWidth2 = alternativeMaxWidth;
                    }
                    setMeasuredDimension(View.resolveSizeAndState(Math.max(maxWidth2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), largestChildHeight, childState6), heightSizeAndState);
                    if (!matchWidth) {
                        forceUniformWidth(count, heightMeasureSpec);
                        return;
                    }
                    return;
                } else {
                    maxWidth = maxWidth11;
                    totalWeight = totalWeight3;
                    delta = delta5;
                }
                float totalWeight5 = this.mWeightSum;
                if (totalWeight5 <= 0.0f) {
                    totalWeight5 = totalWeight;
                }
                float weightSum2 = totalWeight5;
                this.mTotalLength = 0;
                int i7 = 0;
                int alternativeMaxWidth9 = alternativeMaxWidth7;
                int delta6 = delta;
                int alternativeMaxWidth10 = maxWidth;
                while (i7 < count) {
                    boolean useLargestChild2 = useLargestChild;
                    View child5 = getVirtualChildAt(i7);
                    int weightedMaxWidth10 = weightedMaxWidth9;
                    int weightedMaxWidth11 = child5.getVisibility();
                    int largestChildHeight5 = largestChildHeight3;
                    if (weightedMaxWidth11 == 8) {
                        heightMode2 = heightMode;
                        baselineChildIndex = baselineChildIndex2;
                    } else {
                        LayoutParams lp4 = (LayoutParams) child5.getLayoutParams();
                        float childExtra = lp4.weight;
                        if (childExtra <= 0.0f) {
                            heightMode2 = heightMode;
                            baselineChildIndex = baselineChildIndex2;
                            weightSum = weightSum2;
                            delta2 = delta6;
                        } else {
                            baselineChildIndex = baselineChildIndex2;
                            int share = (int) ((delta6 * childExtra) / weightSum2);
                            weightSum = weightSum2 - childExtra;
                            delta2 = delta6 - share;
                            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp4.leftMargin + lp4.rightMargin, lp4.width);
                            if (lp4.height != 0) {
                                heightMode2 = heightMode;
                            } else if (heightMode != 1073741824) {
                                heightMode2 = heightMode;
                            } else {
                                heightMode2 = heightMode;
                                int heightMode7 = share > 0 ? share : 0;
                                child5.measure(childWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(heightMode7, BasicMeasure.EXACTLY));
                                childState6 = View.combineMeasuredStates(childState6, child5.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                            }
                            int childHeight2 = child5.getMeasuredHeight() + share;
                            if (childHeight2 < 0) {
                                childHeight2 = 0;
                            }
                            child5.measure(childWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(childHeight2, BasicMeasure.EXACTLY));
                            childState6 = View.combineMeasuredStates(childState6, child5.getMeasuredState() & InputDeviceCompat.SOURCE_ANY);
                        }
                        int margin2 = lp4.leftMargin + lp4.rightMargin;
                        int measuredWidth2 = child5.getMeasuredWidth() + margin2;
                        int maxWidth14 = Math.max(alternativeMaxWidth10, measuredWidth2);
                        alternativeMaxWidth9 = Math.max(alternativeMaxWidth9, widthMode != 1073741824 && lp4.width == -1 ? margin2 : measuredWidth2);
                        if (allFillParent2) {
                            maxWidth3 = maxWidth14;
                            if (lp4.width == -1) {
                                allFillParent = true;
                                int totalLength4 = this.mTotalLength;
                                this.mTotalLength = Math.max(totalLength4, totalLength4 + child5.getMeasuredHeight() + lp4.topMargin + lp4.bottomMargin + getNextLocationOffset(child5));
                                allFillParent2 = allFillParent;
                                delta6 = delta2;
                                weightSum2 = weightSum;
                                alternativeMaxWidth10 = maxWidth3;
                            }
                        } else {
                            maxWidth3 = maxWidth14;
                        }
                        allFillParent = false;
                        int totalLength42 = this.mTotalLength;
                        this.mTotalLength = Math.max(totalLength42, totalLength42 + child5.getMeasuredHeight() + lp4.topMargin + lp4.bottomMargin + getNextLocationOffset(child5));
                        allFillParent2 = allFillParent;
                        delta6 = delta2;
                        weightSum2 = weightSum;
                        alternativeMaxWidth10 = maxWidth3;
                    }
                    i7++;
                    useLargestChild = useLargestChild2;
                    weightedMaxWidth9 = weightedMaxWidth10;
                    largestChildHeight3 = largestChildHeight5;
                    baselineChildIndex2 = baselineChildIndex;
                    heightMode = heightMode2;
                }
                maxWidth2 = alternativeMaxWidth10;
                largestChildHeight = widthMeasureSpec;
                int maxWidth15 = this.mTotalLength;
                this.mTotalLength = maxWidth15 + getPaddingTop() + getPaddingBottom();
                alternativeMaxWidth = alternativeMaxWidth9;
                if (!allFillParent2) {
                    maxWidth2 = alternativeMaxWidth;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(maxWidth2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), largestChildHeight, childState6), heightSizeAndState);
                if (!matchWidth) {
                }
            }
        }
    }

    private void forceUniformWidth(int count, int heightMeasureSpec) {
        int uniformMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), BasicMeasure.EXACTLY);
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.width == -1) {
                    int oldHeight = lp.height;
                    lp.height = child.getMeasuredHeight();
                    measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
                    lp.height = oldHeight;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:205:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0641  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {
        int childState;
        int maxHeight;
        int widthMode;
        float totalWeight;
        int widthSize;
        int childState2;
        int widthSizeAndState;
        int alternativeMaxHeight;
        int delta;
        int maxHeight2;
        int childState3;
        int widthMode2;
        int widthMode3;
        int widthSizeAndState2;
        int count;
        boolean useLargestChild;
        int alternativeMaxHeight2;
        boolean allFillParent;
        int childState4;
        int alternativeMaxHeight3;
        int widthSize2;
        int largestChildWidth;
        int maxHeight3;
        int i;
        int oldWidth;
        int weightedMaxHeight;
        int alternativeMaxHeight4;
        int maxHeight4;
        int i2;
        boolean baselineAligned;
        int largestChildWidth2;
        int widthMode4;
        LayoutParams lp;
        int largestChildWidth3;
        int margin;
        int largestChildWidth4;
        int alternativeMaxHeight5;
        int weightedMaxHeight2;
        int childState5;
        int alternativeMaxHeight6;
        this.mTotalLength = 0;
        int count2 = getVirtualChildCount();
        int widthMode5 = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] maxAscent = this.mMaxAscent;
        int[] maxDescent = this.mMaxDescent;
        maxAscent[3] = -1;
        maxAscent[2] = -1;
        maxAscent[1] = -1;
        maxAscent[0] = -1;
        maxDescent[3] = -1;
        maxDescent[2] = -1;
        maxDescent[1] = -1;
        maxDescent[0] = -1;
        boolean baselineAligned2 = this.mBaselineAligned;
        boolean skippedMeasure = false;
        boolean useLargestChild2 = this.mUseLargestChild;
        boolean isExactly = widthMode5 == 1073741824;
        int childState6 = 0;
        int alternativeMaxHeight7 = 0;
        int largestChildWidth5 = 0;
        boolean matchHeight = true;
        int childHeight = 0;
        float totalWeight2 = 0.0f;
        int i3 = 0;
        int weightedMaxHeight3 = 0;
        int alternativeMaxHeight8 = 0;
        while (i3 < count2) {
            View child = getVirtualChildAt(i3);
            if (child == null) {
                int largestChildWidth6 = alternativeMaxHeight7;
                int largestChildWidth7 = this.mTotalLength;
                this.mTotalLength = largestChildWidth7 + measureNullChild(i3);
                baselineAligned = baselineAligned2;
                childState5 = childState6;
                alternativeMaxHeight7 = largestChildWidth6;
                largestChildWidth2 = widthMode5;
            } else {
                int largestChildWidth8 = alternativeMaxHeight7;
                int largestChildWidth9 = child.getVisibility();
                int weightedMaxHeight4 = alternativeMaxHeight8;
                if (largestChildWidth9 == 8) {
                    i3 += getChildrenSkipCount(child, i3);
                    baselineAligned = baselineAligned2;
                    childState5 = childState6;
                    alternativeMaxHeight7 = largestChildWidth8;
                    alternativeMaxHeight8 = weightedMaxHeight4;
                    largestChildWidth2 = widthMode5;
                } else {
                    if (hasDividerBeforeChildAt(i3)) {
                        this.mTotalLength += this.mDividerWidth;
                    }
                    LayoutParams lp2 = (LayoutParams) child.getLayoutParams();
                    float totalWeight3 = totalWeight2 + lp2.weight;
                    if (widthMode5 != 1073741824 || lp2.width != 0 || lp2.weight <= 0.0f) {
                        int alternativeMaxHeight9 = weightedMaxHeight3;
                        if (lp2.width == 0 && lp2.weight > 0.0f) {
                            lp2.width = -2;
                            oldWidth = 0;
                        } else {
                            oldWidth = Integer.MIN_VALUE;
                        }
                        weightedMaxHeight = weightedMaxHeight4;
                        int oldWidth2 = oldWidth;
                        alternativeMaxHeight4 = alternativeMaxHeight9;
                        maxHeight4 = childHeight;
                        i2 = i3;
                        baselineAligned = baselineAligned2;
                        largestChildWidth2 = widthMode5;
                        widthMode4 = -1;
                        measureChildBeforeLayout(child, i3, widthMeasureSpec, totalWeight3 == 0.0f ? this.mTotalLength : 0, heightMeasureSpec, 0);
                        if (oldWidth2 == Integer.MIN_VALUE) {
                            lp = lp2;
                        } else {
                            lp = lp2;
                            lp.width = oldWidth2;
                        }
                        int childWidth = child.getMeasuredWidth();
                        if (isExactly) {
                            this.mTotalLength += lp.leftMargin + childWidth + lp.rightMargin + getNextLocationOffset(child);
                        } else {
                            int totalLength = this.mTotalLength;
                            this.mTotalLength = Math.max(totalLength, totalLength + childWidth + lp.leftMargin + lp.rightMargin + getNextLocationOffset(child));
                        }
                        if (!useLargestChild2) {
                            largestChildWidth3 = largestChildWidth8;
                        } else {
                            largestChildWidth3 = Math.max(childWidth, largestChildWidth8);
                        }
                    } else {
                        if (isExactly) {
                            int i4 = this.mTotalLength;
                            int i5 = lp2.leftMargin;
                            alternativeMaxHeight6 = weightedMaxHeight3;
                            int alternativeMaxHeight10 = lp2.rightMargin;
                            this.mTotalLength = i4 + i5 + alternativeMaxHeight10;
                        } else {
                            alternativeMaxHeight6 = weightedMaxHeight3;
                            int totalLength2 = this.mTotalLength;
                            this.mTotalLength = Math.max(totalLength2, lp2.leftMargin + totalLength2 + lp2.rightMargin);
                        }
                        if (baselineAligned2) {
                            int freeSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                            child.measure(freeSpec, freeSpec);
                            lp = lp2;
                            maxHeight4 = childHeight;
                            i2 = i3;
                            baselineAligned = baselineAligned2;
                            largestChildWidth3 = largestChildWidth8;
                            weightedMaxHeight = weightedMaxHeight4;
                            alternativeMaxHeight4 = alternativeMaxHeight6;
                            largestChildWidth2 = widthMode5;
                            widthMode4 = -1;
                        } else {
                            skippedMeasure = true;
                            lp = lp2;
                            maxHeight4 = childHeight;
                            i2 = i3;
                            baselineAligned = baselineAligned2;
                            largestChildWidth3 = largestChildWidth8;
                            weightedMaxHeight = weightedMaxHeight4;
                            alternativeMaxHeight4 = alternativeMaxHeight6;
                            largestChildWidth2 = widthMode5;
                            widthMode4 = -1;
                        }
                    }
                    boolean matchHeightLocally = false;
                    if (heightMode != 1073741824 && lp.height == widthMode4) {
                        largestChildWidth5 = 1;
                        matchHeightLocally = true;
                    }
                    int margin2 = lp.topMargin + lp.bottomMargin;
                    int childHeight2 = child.getMeasuredHeight() + margin2;
                    int childState7 = View.combineMeasuredStates(childState6, child.getMeasuredState());
                    if (!baselineAligned) {
                        margin = margin2;
                        largestChildWidth4 = largestChildWidth3;
                    } else {
                        int childBaseline = child.getBaseline();
                        if (childBaseline == widthMode4) {
                            margin = margin2;
                            largestChildWidth4 = largestChildWidth3;
                        } else {
                            int gravity = (lp.gravity < 0 ? this.mGravity : lp.gravity) & 112;
                            int index = ((gravity >> 4) & (-2)) >> 1;
                            margin = margin2;
                            maxAscent[index] = Math.max(maxAscent[index], childBaseline);
                            largestChildWidth4 = largestChildWidth3;
                            int largestChildWidth10 = childHeight2 - childBaseline;
                            maxDescent[index] = Math.max(maxDescent[index], largestChildWidth10);
                        }
                    }
                    int maxHeight5 = Math.max(maxHeight4, childHeight2);
                    boolean allFillParent2 = matchHeight && lp.height == -1;
                    if (lp.weight > 0.0f) {
                        weightedMaxHeight2 = Math.max(weightedMaxHeight, matchHeightLocally ? margin : childHeight2);
                        alternativeMaxHeight5 = alternativeMaxHeight4;
                    } else {
                        int weightedMaxHeight5 = weightedMaxHeight;
                        alternativeMaxHeight5 = Math.max(alternativeMaxHeight4, matchHeightLocally ? margin : childHeight2);
                        weightedMaxHeight2 = weightedMaxHeight5;
                    }
                    int weightedMaxHeight6 = getChildrenSkipCount(child, i2);
                    childHeight = maxHeight5;
                    matchHeight = allFillParent2;
                    alternativeMaxHeight8 = weightedMaxHeight2;
                    totalWeight2 = totalWeight3;
                    weightedMaxHeight3 = alternativeMaxHeight5;
                    childState5 = childState7;
                    i3 = i2 + weightedMaxHeight6;
                    alternativeMaxHeight7 = largestChildWidth4;
                }
            }
            i3++;
            childState6 = childState5;
            baselineAligned2 = baselineAligned;
            widthMode5 = largestChildWidth2;
        }
        boolean baselineAligned3 = baselineAligned2;
        int widthMode6 = widthMode5;
        int childState8 = childState6;
        int weightedMaxHeight7 = alternativeMaxHeight8;
        int maxHeight6 = childHeight;
        int alternativeMaxHeight11 = weightedMaxHeight3;
        int largestChildWidth11 = alternativeMaxHeight7;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(count2)) {
            this.mTotalLength += this.mDividerWidth;
        }
        if (maxAscent[1] == -1 && maxAscent[0] == -1 && maxAscent[2] == -1 && maxAscent[3] == -1) {
            childState = childState8;
        } else {
            int ascent = Math.max(maxAscent[3], Math.max(maxAscent[0], Math.max(maxAscent[1], maxAscent[2])));
            int i6 = maxDescent[3];
            int i7 = maxDescent[0];
            int i8 = maxDescent[1];
            childState = childState8;
            int childState9 = maxDescent[2];
            int descent = Math.max(i6, Math.max(i7, Math.max(i8, childState9)));
            maxHeight6 = Math.max(maxHeight6, ascent + descent);
        }
        if (useLargestChild2) {
            widthMode = widthMode6;
            if (widthMode == Integer.MIN_VALUE || widthMode == 0) {
                this.mTotalLength = 0;
                int i9 = 0;
                while (i9 < count2) {
                    View child2 = getVirtualChildAt(i9);
                    if (child2 == null) {
                        this.mTotalLength += measureNullChild(i9);
                        maxHeight3 = maxHeight6;
                        i = i9;
                    } else if (child2.getVisibility() == 8) {
                        maxHeight3 = maxHeight6;
                        i = i9 + getChildrenSkipCount(child2, i9);
                    } else {
                        LayoutParams lp3 = (LayoutParams) child2.getLayoutParams();
                        if (isExactly) {
                            int i10 = this.mTotalLength;
                            maxHeight3 = maxHeight6;
                            i = i9;
                            int i11 = lp3.rightMargin;
                            this.mTotalLength = i10 + lp3.leftMargin + largestChildWidth11 + i11 + getNextLocationOffset(child2);
                        } else {
                            maxHeight3 = maxHeight6;
                            i = i9;
                            int maxHeight7 = this.mTotalLength;
                            this.mTotalLength = Math.max(maxHeight7, maxHeight7 + largestChildWidth11 + lp3.leftMargin + lp3.rightMargin + getNextLocationOffset(child2));
                        }
                    }
                    i9 = i + 1;
                    maxHeight6 = maxHeight3;
                }
                maxHeight = maxHeight6;
            } else {
                maxHeight = maxHeight6;
            }
        } else {
            maxHeight = maxHeight6;
            widthMode = widthMode6;
        }
        this.mTotalLength += getPaddingLeft() + getPaddingRight();
        int widthSizeAndState3 = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumWidth()), widthMeasureSpec, 0);
        int widthSize3 = widthSizeAndState3 & ViewCompat.MEASURED_SIZE_MASK;
        int delta2 = widthSize3 - this.mTotalLength;
        if (skippedMeasure) {
            totalWeight = totalWeight2;
            widthSize = alternativeMaxHeight11;
        } else if (delta2 == 0 || totalWeight2 <= 0.0f) {
            int alternativeMaxHeight12 = Math.max(alternativeMaxHeight11, weightedMaxHeight7);
            if (!useLargestChild2) {
                alternativeMaxHeight = alternativeMaxHeight12;
            } else if (widthMode != 1073741824) {
                int i12 = 0;
                while (i12 < count2) {
                    float totalWeight4 = totalWeight2;
                    View child3 = getVirtualChildAt(i12);
                    if (child3 != null) {
                        alternativeMaxHeight3 = alternativeMaxHeight12;
                        int alternativeMaxHeight13 = child3.getVisibility();
                        widthSize2 = widthSize3;
                        if (alternativeMaxHeight13 == 8) {
                            largestChildWidth = largestChildWidth11;
                        } else if (((LayoutParams) child3.getLayoutParams()).weight > 0.0f) {
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(largestChildWidth11, BasicMeasure.EXACTLY);
                            largestChildWidth = largestChildWidth11;
                            int largestChildWidth12 = child3.getMeasuredHeight();
                            child3.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(largestChildWidth12, BasicMeasure.EXACTLY));
                        } else {
                            largestChildWidth = largestChildWidth11;
                        }
                    } else {
                        alternativeMaxHeight3 = alternativeMaxHeight12;
                        widthSize2 = widthSize3;
                        largestChildWidth = largestChildWidth11;
                    }
                    i12++;
                    alternativeMaxHeight12 = alternativeMaxHeight3;
                    totalWeight2 = totalWeight4;
                    widthSize3 = widthSize2;
                    largestChildWidth11 = largestChildWidth;
                }
                alternativeMaxHeight = alternativeMaxHeight12;
            } else {
                alternativeMaxHeight = alternativeMaxHeight12;
            }
            widthSizeAndState = widthSizeAndState3;
            maxHeight2 = maxHeight;
            childState3 = childState;
            delta = heightMeasureSpec;
            childState2 = count2;
            if (!matchHeight && heightMode != 1073741824) {
                maxHeight2 = alternativeMaxHeight;
            }
            setMeasuredDimension(widthSizeAndState | ((-16777216) & childState3), View.resolveSizeAndState(Math.max(maxHeight2 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), delta, childState3 << 16));
            if (largestChildWidth5 == 0) {
                forceUniformHeight(childState2, widthMeasureSpec);
                return;
            }
            return;
        } else {
            totalWeight = totalWeight2;
            widthSize = alternativeMaxHeight11;
        }
        float weightSum = this.mWeightSum;
        if (weightSum <= 0.0f) {
            weightSum = totalWeight;
        }
        maxAscent[3] = -1;
        maxAscent[2] = -1;
        maxAscent[1] = -1;
        maxAscent[0] = -1;
        maxDescent[3] = -1;
        maxDescent[2] = -1;
        maxDescent[1] = -1;
        maxDescent[0] = -1;
        maxHeight2 = -1;
        this.mTotalLength = 0;
        int i13 = 0;
        int alternativeMaxHeight14 = widthSize;
        childState3 = childState;
        while (i13 < count2) {
            int weightedMaxHeight8 = weightedMaxHeight7;
            View child4 = getVirtualChildAt(i13);
            if (child4 != null) {
                useLargestChild = useLargestChild2;
                if (child4.getVisibility() == 8) {
                    widthMode2 = widthMode;
                    widthMode3 = delta2;
                    widthSizeAndState2 = widthSizeAndState3;
                    count = count2;
                } else {
                    LayoutParams lp4 = (LayoutParams) child4.getLayoutParams();
                    float childExtra = lp4.weight;
                    if (childExtra <= 0.0f) {
                        widthMode2 = widthMode;
                        widthMode3 = delta2;
                        widthSizeAndState2 = widthSizeAndState3;
                        count = count2;
                    } else {
                        count = count2;
                        int share = (int) ((delta2 * childExtra) / weightSum);
                        float weightSum2 = weightSum - childExtra;
                        int delta3 = delta2 - share;
                        widthSizeAndState2 = widthSizeAndState3;
                        int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp4.topMargin + lp4.bottomMargin, lp4.height);
                        if (lp4.width != 0 || widthMode != 1073741824) {
                            int childWidth2 = child4.getMeasuredWidth() + share;
                            if (childWidth2 < 0) {
                                childWidth2 = 0;
                            }
                            widthMode2 = widthMode;
                            child4.measure(View.MeasureSpec.makeMeasureSpec(childWidth2, BasicMeasure.EXACTLY), childHeightMeasureSpec);
                        } else {
                            child4.measure(View.MeasureSpec.makeMeasureSpec(share > 0 ? share : 0, BasicMeasure.EXACTLY), childHeightMeasureSpec);
                            widthMode2 = widthMode;
                        }
                        childState3 = View.combineMeasuredStates(childState3, child4.getMeasuredState() & ViewCompat.MEASURED_STATE_MASK);
                        weightSum = weightSum2;
                        widthMode3 = delta3;
                    }
                    if (isExactly) {
                        this.mTotalLength += child4.getMeasuredWidth() + lp4.leftMargin + lp4.rightMargin + getNextLocationOffset(child4);
                    } else {
                        int totalLength3 = this.mTotalLength;
                        this.mTotalLength = Math.max(totalLength3, child4.getMeasuredWidth() + totalLength3 + lp4.leftMargin + lp4.rightMargin + getNextLocationOffset(child4));
                    }
                    boolean matchHeightLocally2 = heightMode != 1073741824 && lp4.height == -1;
                    int margin3 = lp4.topMargin + lp4.bottomMargin;
                    int childHeight3 = child4.getMeasuredHeight() + margin3;
                    maxHeight2 = Math.max(maxHeight2, childHeight3);
                    float weightSum3 = weightSum;
                    int alternativeMaxHeight15 = Math.max(alternativeMaxHeight14, matchHeightLocally2 ? margin3 : childHeight3);
                    if (matchHeight) {
                        alternativeMaxHeight2 = alternativeMaxHeight15;
                        if (lp4.height == -1) {
                            allFillParent = true;
                            if (baselineAligned3) {
                                matchHeight = allFillParent;
                                childState4 = childState3;
                            } else {
                                int childBaseline2 = child4.getBaseline();
                                matchHeight = allFillParent;
                                if (childBaseline2 == -1) {
                                    childState4 = childState3;
                                } else {
                                    int gravity2 = (lp4.gravity < 0 ? this.mGravity : lp4.gravity) & 112;
                                    int index2 = ((gravity2 >> 4) & (-2)) >> 1;
                                    int gravity3 = maxAscent[index2];
                                    maxAscent[index2] = Math.max(gravity3, childBaseline2);
                                    childState4 = childState3;
                                    int childState10 = childHeight3 - childBaseline2;
                                    maxDescent[index2] = Math.max(maxDescent[index2], childState10);
                                }
                            }
                            weightSum = weightSum3;
                            alternativeMaxHeight14 = alternativeMaxHeight2;
                            childState3 = childState4;
                        }
                    } else {
                        alternativeMaxHeight2 = alternativeMaxHeight15;
                    }
                    allFillParent = false;
                    if (baselineAligned3) {
                    }
                    weightSum = weightSum3;
                    alternativeMaxHeight14 = alternativeMaxHeight2;
                    childState3 = childState4;
                }
            } else {
                widthMode2 = widthMode;
                widthMode3 = delta2;
                widthSizeAndState2 = widthSizeAndState3;
                count = count2;
                useLargestChild = useLargestChild2;
            }
            i13++;
            delta2 = widthMode3;
            widthSizeAndState3 = widthSizeAndState2;
            useLargestChild2 = useLargestChild;
            count2 = count;
            weightedMaxHeight7 = weightedMaxHeight8;
            widthMode = widthMode2;
        }
        widthSizeAndState = widthSizeAndState3;
        childState2 = count2;
        delta = heightMeasureSpec;
        this.mTotalLength += getPaddingLeft() + getPaddingRight();
        if (maxAscent[1] != -1 || maxAscent[0] != -1 || maxAscent[2] != -1 || maxAscent[3] != -1) {
            int ascent2 = Math.max(maxAscent[3], Math.max(maxAscent[0], Math.max(maxAscent[1], maxAscent[2])));
            int descent2 = Math.max(maxDescent[3], Math.max(maxDescent[0], Math.max(maxDescent[1], maxDescent[2])));
            maxHeight2 = Math.max(maxHeight2, ascent2 + descent2);
        }
        alternativeMaxHeight = alternativeMaxHeight14;
        if (!matchHeight) {
            maxHeight2 = alternativeMaxHeight;
        }
        setMeasuredDimension(widthSizeAndState | ((-16777216) & childState3), View.resolveSizeAndState(Math.max(maxHeight2 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), delta, childState3 << 16));
        if (largestChildWidth5 == 0) {
        }
    }

    private void forceUniformHeight(int count, int widthMeasureSpec) {
        int uniformMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), BasicMeasure.EXACTLY);
        for (int i = 0; i < count; i++) {
            View child = getVirtualChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.height == -1) {
                    int oldWidth = lp.width;
                    lp.width = child.getMeasuredWidth();
                    measureChildWithMargins(child, widthMeasureSpec, 0, uniformMeasureSpec, 0);
                    lp.width = oldWidth;
                }
            }
        }
    }

    int getChildrenSkipCount(View child, int index) {
        return 0;
    }

    int measureNullChild(int childIndex) {
        return 0;
    }

    void measureChildBeforeLayout(View child, int childIndex, int widthMeasureSpec, int totalWidth, int heightMeasureSpec, int totalHeight) {
        measureChildWithMargins(child, widthMeasureSpec, totalWidth, heightMeasureSpec, totalHeight);
    }

    int getLocationOffset(View child) {
        return 0;
    }

    int getNextLocationOffset(View child) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        if (this.mOrientation == 1) {
            layoutVertical(l, t, r, b);
        } else {
            layoutHorizontal(l, t, r, b);
        }
    }

    void layoutVertical(int left, int top, int right, int bottom) {
        int childTop;
        int paddingLeft;
        int gravity;
        int childLeft;
        int paddingLeft2 = getPaddingLeft();
        int width = right - left;
        int childRight = width - getPaddingRight();
        int childSpace = (width - paddingLeft2) - getPaddingRight();
        int count = getVirtualChildCount();
        int i = this.mGravity;
        int majorGravity = i & 112;
        int minorGravity = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (majorGravity == 16) {
            int childTop2 = getPaddingTop();
            childTop = childTop2 + (((bottom - top) - this.mTotalLength) / 2);
        } else if (majorGravity == 80) {
            int childTop3 = getPaddingTop();
            childTop = ((childTop3 + bottom) - top) - this.mTotalLength;
        } else {
            childTop = getPaddingTop();
        }
        int i2 = 0;
        while (i2 < count) {
            View child = getVirtualChildAt(i2);
            if (child == null) {
                childTop += measureNullChild(i2);
                paddingLeft = paddingLeft2;
            } else if (child.getVisibility() != 8) {
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int gravity2 = lp.gravity;
                if (gravity2 >= 0) {
                    gravity = gravity2;
                } else {
                    gravity = minorGravity;
                }
                int layoutDirection = ViewCompat.getLayoutDirection(this);
                int absoluteGravity = GravityCompat.getAbsoluteGravity(gravity, layoutDirection);
                int i3 = absoluteGravity & 7;
                if (i3 == 1) {
                    int childLeft2 = childSpace - childWidth;
                    childLeft = (((childLeft2 / 2) + paddingLeft2) + lp.leftMargin) - lp.rightMargin;
                } else if (i3 == 5) {
                    int childLeft3 = childRight - childWidth;
                    childLeft = childLeft3 - lp.rightMargin;
                } else {
                    childLeft = lp.leftMargin + paddingLeft2;
                }
                if (hasDividerBeforeChildAt(i2)) {
                    childTop += this.mDividerHeight;
                }
                int childTop4 = childTop + lp.topMargin;
                int childTop5 = getLocationOffset(child);
                int layoutDirection2 = childLeft;
                paddingLeft = paddingLeft2;
                setChildFrame(child, layoutDirection2, childTop4 + childTop5, childWidth, childHeight);
                int childTop6 = childTop4 + childHeight + lp.bottomMargin + getNextLocationOffset(child);
                i2 += getChildrenSkipCount(child, i2);
                childTop = childTop6;
            } else {
                paddingLeft = paddingLeft2;
            }
            i2++;
            paddingLeft2 = paddingLeft;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void layoutHorizontal(int left, int top, int right, int bottom) {
        int childLeft;
        int start;
        int dir;
        int layoutDirection;
        int[] maxDescent;
        int[] maxAscent;
        int paddingTop;
        int height;
        int count;
        int childBaseline;
        int gravity;
        int gravity2;
        int gravity3;
        int childTop;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop2 = getPaddingTop();
        int height2 = bottom - top;
        int childBottom = height2 - getPaddingBottom();
        int childSpace = (height2 - paddingTop2) - getPaddingBottom();
        int count2 = getVirtualChildCount();
        int i = this.mGravity;
        int majorGravity = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int minorGravity = i & 112;
        boolean baselineAligned = this.mBaselineAligned;
        int[] maxAscent2 = this.mMaxAscent;
        int[] maxDescent2 = this.mMaxDescent;
        int layoutDirection2 = ViewCompat.getLayoutDirection(this);
        int absoluteGravity = GravityCompat.getAbsoluteGravity(majorGravity, layoutDirection2);
        if (absoluteGravity == 1) {
            int childLeft2 = getPaddingLeft();
            childLeft = childLeft2 + (((right - left) - this.mTotalLength) / 2);
        } else if (absoluteGravity == 5) {
            int childLeft3 = getPaddingLeft();
            childLeft = ((childLeft3 + right) - left) - this.mTotalLength;
        } else {
            childLeft = getPaddingLeft();
        }
        if (!isLayoutRtl) {
            start = 0;
            dir = 1;
        } else {
            int start2 = count2 - 1;
            start = start2;
            dir = -1;
        }
        int i2 = 0;
        while (i2 < count2) {
            int childIndex = start + (dir * i2);
            boolean isLayoutRtl2 = isLayoutRtl;
            View child = getVirtualChildAt(childIndex);
            if (child == null) {
                childLeft += measureNullChild(childIndex);
                layoutDirection = layoutDirection2;
                maxDescent = maxDescent2;
                maxAscent = maxAscent2;
                paddingTop = paddingTop2;
                height = height2;
                count = count2;
            } else {
                int i3 = i2;
                int i4 = child.getVisibility();
                layoutDirection = layoutDirection2;
                if (i4 == 8) {
                    maxDescent = maxDescent2;
                    maxAscent = maxAscent2;
                    paddingTop = paddingTop2;
                    height = height2;
                    count = count2;
                    i2 = i3;
                } else {
                    int childWidth = child.getMeasuredWidth();
                    int childHeight = child.getMeasuredHeight();
                    LayoutParams lp = (LayoutParams) child.getLayoutParams();
                    if (baselineAligned) {
                        height = height2;
                        if (lp.height != -1) {
                            childBaseline = child.getBaseline();
                            gravity = lp.gravity;
                            if (gravity < 0) {
                                gravity2 = gravity;
                            } else {
                                gravity2 = minorGravity;
                            }
                            gravity3 = gravity2 & 112;
                            count = count2;
                            if (gravity3 != 16) {
                                int childTop2 = ((((childSpace - childHeight) / 2) + paddingTop2) + lp.topMargin) - lp.bottomMargin;
                                childTop = childTop2;
                            } else if (gravity3 == 48) {
                                int childTop3 = lp.topMargin;
                                int childTop4 = childTop3 + paddingTop2;
                                childTop = childBaseline != -1 ? childTop4 + (maxAscent2[1] - childBaseline) : childTop4;
                            } else if (gravity3 == 80) {
                                int childTop5 = childBottom - childHeight;
                                int childTop6 = childTop5 - lp.bottomMargin;
                                if (childBaseline == -1) {
                                    childTop = childTop6;
                                } else {
                                    int descent = child.getMeasuredHeight() - childBaseline;
                                    childTop = childTop6 - (maxDescent2[2] - descent);
                                }
                            } else {
                                childTop = paddingTop2;
                            }
                            if (hasDividerBeforeChildAt(childIndex)) {
                                childLeft += this.mDividerWidth;
                            }
                            int childLeft4 = childLeft + lp.leftMargin;
                            int childLeft5 = getLocationOffset(child);
                            paddingTop = paddingTop2;
                            maxDescent = maxDescent2;
                            maxAscent = maxAscent2;
                            setChildFrame(child, childLeft4 + childLeft5, childTop, childWidth, childHeight);
                            int childLeft6 = childLeft4 + childWidth + lp.rightMargin + getNextLocationOffset(child);
                            i2 = i3 + getChildrenSkipCount(child, childIndex);
                            childLeft = childLeft6;
                        }
                    } else {
                        height = height2;
                    }
                    childBaseline = -1;
                    gravity = lp.gravity;
                    if (gravity < 0) {
                    }
                    gravity3 = gravity2 & 112;
                    count = count2;
                    if (gravity3 != 16) {
                    }
                    if (hasDividerBeforeChildAt(childIndex)) {
                    }
                    int childLeft42 = childLeft + lp.leftMargin;
                    int childLeft52 = getLocationOffset(child);
                    paddingTop = paddingTop2;
                    maxDescent = maxDescent2;
                    maxAscent = maxAscent2;
                    setChildFrame(child, childLeft42 + childLeft52, childTop, childWidth, childHeight);
                    int childLeft62 = childLeft42 + childWidth + lp.rightMargin + getNextLocationOffset(child);
                    i2 = i3 + getChildrenSkipCount(child, childIndex);
                    childLeft = childLeft62;
                }
            }
            i2++;
            isLayoutRtl = isLayoutRtl2;
            layoutDirection2 = layoutDirection;
            height2 = height;
            count2 = count;
            paddingTop2 = paddingTop;
            maxDescent2 = maxDescent;
            maxAscent2 = maxAscent;
        }
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }

    public void setOrientation(int orientation) {
        if (this.mOrientation != orientation) {
            this.mOrientation = orientation;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int gravity) {
        if (this.mGravity != gravity) {
            if ((8388615 & gravity) == 0) {
                gravity |= GravityCompat.START;
            }
            if ((gravity & 112) == 0) {
                gravity |= 48;
            }
            this.mGravity = gravity;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int horizontalGravity) {
        int gravity = horizontalGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i = this.mGravity;
        if ((8388615 & i) != gravity) {
            this.mGravity = ((-8388616) & i) | gravity;
            requestLayout();
        }
    }

    public void setVerticalGravity(int verticalGravity) {
        int gravity = verticalGravity & 112;
        int i = this.mGravity;
        if ((i & 112) != gravity) {
            this.mGravity = (i & (-113)) | gravity;
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.gravity = -1;
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.LinearLayoutCompat_Layout);
            this.weight = a.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = a.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int width, int height, float weight) {
            super(width, height);
            this.gravity = -1;
            this.weight = weight;
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
            this.gravity = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams source) {
            super((ViewGroup.MarginLayoutParams) source);
            this.gravity = -1;
            this.weight = source.weight;
            this.gravity = source.gravity;
        }
    }
}
