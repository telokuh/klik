package androidx.constraintlayout.solver.widgets;

import androidx.appcompat.widget.ActivityChooserView;
import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.analyzer.ChainRun;
import androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import com.b.b.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ConstraintWidget {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int BOTH = 2;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    private static final boolean USE_WRAP_DIMENSION_FOR_SPREAD = false;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    private boolean OPTIMIZE_WRAP;
    private boolean OPTIMIZE_WRAP_ON_RESOLVED;
    private boolean hasBaseline;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public HorizontalWidgetRun horizontalRun;
    private boolean inPlaceholder;
    public boolean[] isTerminalWidget;
    protected ArrayList<ConstraintAnchor> mAnchors;
    public ConstraintAnchor mBaseline;
    int mBaselineDistance;
    public ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    public ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    boolean mGroupsToSolver;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    private boolean mInVirtuaLayout;
    public boolean mIsHeightWrapContent;
    private boolean[] mIsInBarrier;
    public boolean mIsWidthWrapContent;
    private int mLastHorizontalMeasureSpec;
    private int mLastVerticalMeasureSpec;
    public ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    private boolean mMeasureRequested;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    boolean mResolvedHasRatio;
    public int[] mResolvedMatchConstraintDefault;
    public ConstraintAnchor mRight;
    boolean mRightHasCentered;
    public ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;
    protected int mX;
    protected int mY;
    public boolean measured;
    private boolean resolvedHorizontal;
    private boolean resolvedVertical;
    public WidgetRun[] run;
    public ChainRun verticalChainRun;
    public int verticalGroup;
    public VerticalWidgetRun verticalRun;

    /* loaded from: classes.dex */
    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public WidgetRun getRun(int orientation) {
        if (orientation == 0) {
            return this.horizontalRun;
        }
        if (orientation == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public void setFinalFrame(int left, int top, int right, int bottom, int baseline, int orientation) {
        setFrame(left, top, right, bottom);
        setBaselineDistance(baseline);
        if (orientation == 0) {
            this.resolvedHorizontal = true;
            this.resolvedVertical = false;
        } else if (orientation == 1) {
            this.resolvedHorizontal = false;
            this.resolvedVertical = true;
        } else if (orientation == 2) {
            this.resolvedHorizontal = true;
            this.resolvedVertical = true;
        } else {
            this.resolvedHorizontal = false;
            this.resolvedVertical = false;
        }
    }

    public void setFinalLeft(int x1) {
        this.mLeft.setFinalValue(x1);
        this.mX = x1;
    }

    public void setFinalTop(int y1) {
        this.mTop.setFinalValue(y1);
        this.mY = y1;
    }

    public void setFinalHorizontal(int x1, int x2) {
        this.mLeft.setFinalValue(x1);
        this.mRight.setFinalValue(x2);
        this.mX = x1;
        this.mWidth = x2 - x1;
        this.resolvedHorizontal = true;
    }

    public void setFinalVertical(int y1, int y2) {
        this.mTop.setFinalValue(y1);
        this.mBottom.setFinalValue(y2);
        this.mY = y1;
        this.mHeight = y2 - y1;
        if (this.hasBaseline) {
            this.mBaseline.setFinalValue(this.mBaselineDistance + y1);
        }
        this.resolvedVertical = true;
    }

    public void setFinalBaseline(int baselineValue) {
        if (!this.hasBaseline) {
            return;
        }
        int y1 = baselineValue - this.mBaselineDistance;
        int y2 = this.mHeight + y1;
        this.mY = y1;
        this.mTop.setFinalValue(y1);
        this.mBottom.setFinalValue(y2);
        this.mBaseline.setFinalValue(baselineValue);
        this.resolvedVertical = true;
    }

    public boolean isResolvedHorizontally() {
        return this.resolvedHorizontal || (this.mLeft.hasFinalValue() && this.mRight.hasFinalValue());
    }

    public boolean isResolvedVertically() {
        return this.resolvedVertical || (this.mTop.hasFinalValue() && this.mBottom.hasFinalValue());
    }

    public void resetFinalResolution() {
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        int mAnchorsSize = this.mAnchors.size();
        for (int i = 0; i < mAnchorsSize; i++) {
            ConstraintAnchor anchor = this.mAnchors.get(i);
            anchor.resetFinalResolution();
        }
    }

    public void ensureMeasureRequested() {
        this.mMeasureRequested = true;
    }

    public boolean hasDependencies() {
        int mAnchorsSize = this.mAnchors.size();
        for (int i = 0; i < mAnchorsSize; i++) {
            ConstraintAnchor anchor = this.mAnchors.get(i);
            if (anchor.hasDependents()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDanglingDimension(int orientation) {
        if (orientation == 0) {
            int horizontalTargets = (this.mLeft.mTarget != null ? 1 : 0) + (this.mRight.mTarget != null ? 1 : 0);
            return horizontalTargets < 2;
        }
        int verticalTargets = (this.mTop.mTarget != null ? 1 : 0) + (this.mBottom.mTarget != null ? 1 : 0) + (this.mBaseline.mTarget != null ? 1 : 0);
        return verticalTargets < 2;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtuaLayout;
    }

    public void setInVirtualLayout(boolean inVirtualLayout) {
        this.mInVirtuaLayout = inVirtualLayout;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int maxWidth) {
        this.mMaxDimension[0] = maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.mMaxDimension[1] = maxHeight;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void setHasBaseline(boolean hasBaseline) {
        this.hasBaseline = hasBaseline;
    }

    public boolean getHasBaseline() {
        return this.hasBaseline;
    }

    public boolean isInPlaceholder() {
        return this.inPlaceholder;
    }

    public void setInPlaceholder(boolean inPlaceholder) {
        this.inPlaceholder = inPlaceholder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInBarrier(int orientation, boolean value) {
        this.mIsInBarrier[orientation] = value;
    }

    public void setMeasureRequested(boolean measureRequested) {
        this.mMeasureRequested = measureRequested;
    }

    public boolean isMeasureRequested() {
        return this.mMeasureRequested && this.mVisibility != 8;
    }

    public int getLastHorizontalMeasureSpec() {
        return this.mLastHorizontalMeasureSpec;
    }

    public int getLastVerticalMeasureSpec() {
        return this.mLastVerticalMeasureSpec;
    }

    public void setLastMeasureSpec(int horizontal, int vertical) {
        this.mLastHorizontalMeasureSpec = horizontal;
        this.mLastVerticalMeasureSpec = vertical;
        setMeasureRequested(false);
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mMatchConstraintMaxHeight = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mGroupsToSolver = false;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtuaLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
        this.mMeasureRequested = true;
    }

    public boolean oppositeDimensionDependsOn(int orientation) {
        int oppositeOrientation = orientation == 0 ? (char) 1 : 0;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[orientation];
        DimensionBehaviour oppositeDimensionBehaviour = dimensionBehaviourArr[oppositeOrientation];
        return dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && oppositeDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean oppositeDimensionsTied() {
        return this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtuaLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
    }

    public ConstraintWidget(String debugName) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtuaLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
        setDebugName(debugName);
    }

    public ConstraintWidget(int x, int y, int width, int height) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtuaLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        this.mX = x;
        this.mY = y;
        this.mWidth = width;
        this.mHeight = height;
        addAnchors();
    }

    public ConstraintWidget(String debugName, int x, int y, int width, int height) {
        this(x, y, width, height);
        setDebugName(debugName);
    }

    public ConstraintWidget(int width, int height) {
        this(0, 0, width, height);
    }

    public void ensureWidgetRuns() {
        if (this.horizontalRun == null) {
            this.horizontalRun = new HorizontalWidgetRun(this);
        }
        if (this.verticalRun == null) {
            this.verticalRun = new VerticalWidgetRun(this);
        }
    }

    public ConstraintWidget(String debugName, int width, int height) {
        this(width, height);
        setDebugName(debugName);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget widget) {
        this.mParent = widget;
    }

    public void setWidthWrapContent(boolean widthWrapContent) {
        this.mIsWidthWrapContent = widthWrapContent;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean heightWrapContent) {
        this.mIsHeightWrapContent = heightWrapContent;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget target, float angle, int radius) {
        immediateConnect(ConstraintAnchor.Type.CENTER, target, ConstraintAnchor.Type.CENTER, radius, 0);
        this.mCircleConstraintAngle = angle;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public void setVisibility(int visibility) {
        this.mVisibility = visibility;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String name) {
        this.mDebugName = name;
    }

    public void setDebugSolverName(LinearSystem system, String name) {
        this.mDebugName = name;
        SolverVariable left = system.createObjectVariable(this.mLeft);
        SolverVariable top = system.createObjectVariable(this.mTop);
        SolverVariable right = system.createObjectVariable(this.mRight);
        SolverVariable bottom = system.createObjectVariable(this.mBottom);
        left.setName(name + ".left");
        top.setName(name + ".top");
        right.setName(name + ".right");
        bottom.setName(name + ".bottom");
        SolverVariable baseline = system.createObjectVariable(this.mBaseline);
        baseline.setName(name + ".baseline");
    }

    public void createObjectVariables(LinearSystem system) {
        system.createObjectVariable(this.mLeft);
        system.createObjectVariable(this.mTop);
        system.createObjectVariable(this.mRight);
        system.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            system.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = this.mType;
        String str3 = BuildConfig.FLAVOR;
        if (str2 != null) {
            str = "type: " + this.mType + " ";
        } else {
            str = BuildConfig.FLAVOR;
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str3 = "id: " + this.mDebugName + " ";
        }
        sb.append(str3);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(")");
        return sb.toString();
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null && (constraintWidget instanceof ConstraintWidgetContainer)) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.mX;
        }
        return this.mX;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null && (constraintWidget instanceof ConstraintWidgetContainer)) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.mY;
        }
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int w;
        int w2 = this.mWidth;
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultWidth == 1) {
                w = Math.max(this.mMatchConstraintMinWidth, w2);
            } else if (this.mMatchConstraintMinWidth > 0) {
                w = this.mMatchConstraintMinWidth;
                this.mWidth = w;
            } else {
                w = 0;
            }
            int i = this.mMatchConstraintMaxWidth;
            if (i > 0 && i < w) {
                return this.mMatchConstraintMaxWidth;
            }
            return w;
        }
        return w2;
    }

    public int getOptimizerWrapHeight() {
        int h;
        int h2 = this.mHeight;
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                h = Math.max(this.mMatchConstraintMinHeight, h2);
            } else if (this.mMatchConstraintMinHeight > 0) {
                h = this.mMatchConstraintMinHeight;
                this.mHeight = h;
            } else {
                h = 0;
            }
            int i = this.mMatchConstraintMaxHeight;
            if (i > 0 && i < h) {
                return this.mMatchConstraintMaxHeight;
            }
            return h;
        }
        return h2;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getLength(int orientation) {
        if (orientation == 0) {
            return getWidth();
        }
        if (orientation == 1) {
            return getHeight();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int margin = constraintAnchor != null ? 0 + constraintAnchor.mMargin : 0;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        if (constraintAnchor2 != null) {
            return margin + constraintAnchor2.mMargin;
        }
        return margin;
    }

    public int getVerticalMargin() {
        int margin = this.mLeft != null ? 0 + this.mTop.mMargin : 0;
        if (this.mRight != null) {
            return margin + this.mBottom.mMargin;
        }
        return margin;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int orientation) {
        if (orientation == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (orientation == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.hasBaseline;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int x) {
        this.mX = x;
    }

    public void setY(int y) {
        this.mY = y;
    }

    public void setOrigin(int x, int y) {
        this.mX = x;
        this.mY = y;
    }

    public void setOffset(int x, int y) {
        this.mOffsetX = x;
        this.mOffsetY = y;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int goneMargin) {
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[type.ordinal()];
        if (i == 1) {
            this.mLeft.mGoneMargin = goneMargin;
        } else if (i == 2) {
            this.mTop.mGoneMargin = goneMargin;
        } else if (i == 3) {
            this.mRight.mGoneMargin = goneMargin;
        } else if (i == 4) {
            this.mBottom.mGoneMargin = goneMargin;
        }
    }

    public void setWidth(int w) {
        this.mWidth = w;
        int i = this.mWidth;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setHeight(int h) {
        this.mHeight = h;
        int i = this.mHeight;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public void setLength(int length, int orientation) {
        if (orientation == 0) {
            setWidth(length);
        } else if (orientation == 1) {
            setHeight(length);
        }
    }

    public void setHorizontalMatchStyle(int horizontalMatchStyle, int min, int max, float percent) {
        this.mMatchConstraintDefaultWidth = horizontalMatchStyle;
        this.mMatchConstraintMinWidth = min;
        this.mMatchConstraintMaxWidth = max == Integer.MAX_VALUE ? 0 : max;
        this.mMatchConstraintPercentWidth = percent;
        if (percent > 0.0f && percent < 1.0f && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int verticalMatchStyle, int min, int max, float percent) {
        this.mMatchConstraintDefaultHeight = verticalMatchStyle;
        this.mMatchConstraintMinHeight = min;
        this.mMatchConstraintMaxHeight = max == Integer.MAX_VALUE ? 0 : max;
        this.mMatchConstraintPercentHeight = percent;
        if (percent > 0.0f && percent < 1.0f && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    public void setDimensionRatio(String ratio) {
        int commaIndex;
        if (ratio == null || ratio.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int dimensionRatioSide = -1;
        float dimensionRatio = 0.0f;
        int len = ratio.length();
        int commaIndex2 = ratio.indexOf(44);
        if (commaIndex2 > 0 && commaIndex2 < len - 1) {
            String dimension = ratio.substring(0, commaIndex2);
            if (dimension.equalsIgnoreCase("W")) {
                dimensionRatioSide = 0;
            } else if (dimension.equalsIgnoreCase("H")) {
                dimensionRatioSide = 1;
            }
            commaIndex = commaIndex2 + 1;
        } else {
            commaIndex = 0;
        }
        int colonIndex = ratio.indexOf(58);
        if (colonIndex >= 0 && colonIndex < len - 1) {
            String nominator = ratio.substring(commaIndex, colonIndex);
            String denominator = ratio.substring(colonIndex + 1);
            if (nominator.length() > 0 && denominator.length() > 0) {
                try {
                    float nominatorValue = Float.parseFloat(nominator);
                    float denominatorValue = Float.parseFloat(denominator);
                    if (nominatorValue > 0.0f && denominatorValue > 0.0f) {
                        dimensionRatio = dimensionRatioSide == 1 ? Math.abs(denominatorValue / nominatorValue) : Math.abs(nominatorValue / denominatorValue);
                    }
                } catch (NumberFormatException e) {
                }
            }
        } else {
            String r = ratio.substring(commaIndex);
            if (r.length() > 0) {
                try {
                    dimensionRatio = Float.parseFloat(r);
                } catch (NumberFormatException e2) {
                }
            }
        }
        if (dimensionRatio > 0.0f) {
            this.mDimensionRatio = dimensionRatio;
            this.mDimensionRatioSide = dimensionRatioSide;
        }
    }

    public void setDimensionRatio(float ratio, int dimensionRatioSide) {
        this.mDimensionRatio = ratio;
        this.mDimensionRatioSide = dimensionRatioSide;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float horizontalBiasPercent) {
        this.mHorizontalBiasPercent = horizontalBiasPercent;
    }

    public void setVerticalBiasPercent(float verticalBiasPercent) {
        this.mVerticalBiasPercent = verticalBiasPercent;
    }

    public void setMinWidth(int w) {
        if (w < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = w;
        }
    }

    public void setMinHeight(int h) {
        if (h < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = h;
        }
    }

    public void setDimension(int w, int h) {
        this.mWidth = w;
        int i = this.mWidth;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
        this.mHeight = h;
        int i3 = this.mHeight;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    public void setFrame(int left, int top, int right, int bottom) {
        int w = right - left;
        int h = bottom - top;
        this.mX = left;
        this.mY = top;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && w < this.mWidth) {
            w = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && h < this.mHeight) {
            h = this.mHeight;
        }
        this.mWidth = w;
        this.mHeight = h;
        int i = this.mHeight;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
        int i3 = this.mWidth;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public void setFrame(int start, int end, int orientation) {
        if (orientation == 0) {
            setHorizontalDimension(start, end);
        } else if (orientation == 1) {
            setVerticalDimension(start, end);
        }
    }

    public void setHorizontalDimension(int left, int right) {
        this.mX = left;
        this.mWidth = right - left;
        int i = this.mWidth;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setVerticalDimension(int top, int bottom) {
        this.mY = top;
        this.mHeight = bottom - top;
        int i = this.mHeight;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    int getRelativePositioning(int orientation) {
        if (orientation == 0) {
            return this.mRelX;
        }
        if (orientation == 1) {
            return this.mRelY;
        }
        return 0;
    }

    void setRelativePositioning(int offset, int orientation) {
        if (orientation == 0) {
            this.mRelX = offset;
        } else if (orientation == 1) {
            this.mRelY = offset;
        }
    }

    public void setBaselineDistance(int baseline) {
        this.mBaselineDistance = baseline;
        this.hasBaseline = baseline > 0;
    }

    public void setCompanionWidget(Object companion) {
        this.mCompanionWidget = companion;
    }

    public void setContainerItemSkip(int skip) {
        if (skip >= 0) {
            this.mContainerItemSkip = skip;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float horizontalWeight) {
        this.mWeight[0] = horizontalWeight;
    }

    public void setVerticalWeight(float verticalWeight) {
        this.mWeight[1] = verticalWeight;
    }

    public void setHorizontalChainStyle(int horizontalChainStyle) {
        this.mHorizontalChainStyle = horizontalChainStyle;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int verticalChainStyle) {
        this.mVerticalChainStyle = verticalChainStyle;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(ConstraintAnchor.Type startType, ConstraintWidget target, ConstraintAnchor.Type endType, int margin, int goneMargin) {
        ConstraintAnchor startAnchor = getAnchor(startType);
        ConstraintAnchor endAnchor = target.getAnchor(endType);
        startAnchor.connect(endAnchor, margin, goneMargin, true);
    }

    public void connect(ConstraintAnchor from, ConstraintAnchor to, int margin) {
        if (from.getOwner() == this) {
            connect(from.getType(), to.getOwner(), to.getType(), margin);
        }
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo) {
        connect(constraintFrom, target, constraintTo, 0);
    }

    public void connect(ConstraintAnchor.Type constraintFrom, ConstraintWidget target, ConstraintAnchor.Type constraintTo, int margin) {
        if (constraintFrom == ConstraintAnchor.Type.CENTER) {
            if (constraintTo != ConstraintAnchor.Type.CENTER) {
                if (constraintTo == ConstraintAnchor.Type.LEFT || constraintTo == ConstraintAnchor.Type.RIGHT) {
                    connect(ConstraintAnchor.Type.LEFT, target, constraintTo, 0);
                    connect(ConstraintAnchor.Type.RIGHT, target, constraintTo, 0);
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(constraintTo), 0);
                    return;
                } else if (constraintTo == ConstraintAnchor.Type.TOP || constraintTo == ConstraintAnchor.Type.BOTTOM) {
                    connect(ConstraintAnchor.Type.TOP, target, constraintTo, 0);
                    connect(ConstraintAnchor.Type.BOTTOM, target, constraintTo, 0);
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(constraintTo), 0);
                    return;
                } else {
                    return;
                }
            }
            ConstraintAnchor left = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor right = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor top = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor bottom = getAnchor(ConstraintAnchor.Type.BOTTOM);
            boolean centerX = false;
            boolean centerY = false;
            if ((left == null || !left.isConnected()) && (right == null || !right.isConnected())) {
                connect(ConstraintAnchor.Type.LEFT, target, ConstraintAnchor.Type.LEFT, 0);
                connect(ConstraintAnchor.Type.RIGHT, target, ConstraintAnchor.Type.RIGHT, 0);
                centerX = true;
            }
            if ((top == null || !top.isConnected()) && (bottom == null || !bottom.isConnected())) {
                connect(ConstraintAnchor.Type.TOP, target, ConstraintAnchor.Type.TOP, 0);
                connect(ConstraintAnchor.Type.BOTTOM, target, ConstraintAnchor.Type.BOTTOM, 0);
                centerY = true;
            }
            if (centerX && centerY) {
                getAnchor(ConstraintAnchor.Type.CENTER).connect(target.getAnchor(ConstraintAnchor.Type.CENTER), 0);
            } else if (centerX) {
                getAnchor(ConstraintAnchor.Type.CENTER_X).connect(target.getAnchor(ConstraintAnchor.Type.CENTER_X), 0);
            } else if (centerY) {
                getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(target.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0);
            }
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_X && (constraintTo == ConstraintAnchor.Type.LEFT || constraintTo == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor left2 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor targetAnchor = target.getAnchor(constraintTo);
            ConstraintAnchor right2 = getAnchor(ConstraintAnchor.Type.RIGHT);
            left2.connect(targetAnchor, 0);
            right2.connect(targetAnchor, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(targetAnchor, 0);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_Y && (constraintTo == ConstraintAnchor.Type.TOP || constraintTo == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor targetAnchor2 = target.getAnchor(constraintTo);
            getAnchor(ConstraintAnchor.Type.TOP).connect(targetAnchor2, 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(targetAnchor2, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(targetAnchor2, 0);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_X && constraintTo == ConstraintAnchor.Type.CENTER_X) {
            ConstraintAnchor left3 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor leftTarget = target.getAnchor(ConstraintAnchor.Type.LEFT);
            left3.connect(leftTarget, 0);
            ConstraintAnchor right3 = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor rightTarget = target.getAnchor(ConstraintAnchor.Type.RIGHT);
            right3.connect(rightTarget, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(target.getAnchor(constraintTo), 0);
        } else if (constraintFrom == ConstraintAnchor.Type.CENTER_Y && constraintTo == ConstraintAnchor.Type.CENTER_Y) {
            ConstraintAnchor top2 = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor topTarget = target.getAnchor(ConstraintAnchor.Type.TOP);
            top2.connect(topTarget, 0);
            ConstraintAnchor bottom2 = getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintAnchor bottomTarget = target.getAnchor(ConstraintAnchor.Type.BOTTOM);
            bottom2.connect(bottomTarget, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(target.getAnchor(constraintTo), 0);
        } else {
            ConstraintAnchor fromAnchor = getAnchor(constraintFrom);
            ConstraintAnchor toAnchor = target.getAnchor(constraintTo);
            if (fromAnchor.isValidConnection(toAnchor)) {
                if (constraintFrom == ConstraintAnchor.Type.BASELINE) {
                    ConstraintAnchor top3 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor bottom3 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (top3 != null) {
                        top3.reset();
                    }
                    if (bottom3 != null) {
                        bottom3.reset();
                    }
                    margin = 0;
                } else if (constraintFrom == ConstraintAnchor.Type.TOP || constraintFrom == ConstraintAnchor.Type.BOTTOM) {
                    ConstraintAnchor baseline = getAnchor(ConstraintAnchor.Type.BASELINE);
                    if (baseline != null) {
                        baseline.reset();
                    }
                    ConstraintAnchor center = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (center.getTarget() != toAnchor) {
                        center.reset();
                    }
                    ConstraintAnchor opposite = getAnchor(constraintFrom).getOpposite();
                    ConstraintAnchor centerY2 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                    if (centerY2.isConnected()) {
                        opposite.reset();
                        centerY2.reset();
                    }
                } else if (constraintFrom == ConstraintAnchor.Type.LEFT || constraintFrom == ConstraintAnchor.Type.RIGHT) {
                    ConstraintAnchor center2 = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (center2.getTarget() != toAnchor) {
                        center2.reset();
                    }
                    ConstraintAnchor opposite2 = getAnchor(constraintFrom).getOpposite();
                    ConstraintAnchor centerX2 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                    if (centerX2.isConnected()) {
                        opposite2.reset();
                        centerX2.reset();
                    }
                }
                fromAnchor.connect(toAnchor, margin);
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor anchor) {
        if (getParent() != null && (getParent() instanceof ConstraintWidgetContainer)) {
            ConstraintWidgetContainer parent = (ConstraintWidgetContainer) getParent();
            if (parent.handlesInternalConstraints()) {
                return;
            }
        }
        ConstraintAnchor left = getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor right = getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintAnchor top = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor bottom = getAnchor(ConstraintAnchor.Type.BOTTOM);
        ConstraintAnchor center = getAnchor(ConstraintAnchor.Type.CENTER);
        ConstraintAnchor centerX = getAnchor(ConstraintAnchor.Type.CENTER_X);
        ConstraintAnchor centerY = getAnchor(ConstraintAnchor.Type.CENTER_Y);
        if (anchor == center) {
            if (left.isConnected() && right.isConnected() && left.getTarget() == right.getTarget()) {
                left.reset();
                right.reset();
            }
            if (top.isConnected() && bottom.isConnected() && top.getTarget() == bottom.getTarget()) {
                top.reset();
                bottom.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
            this.mVerticalBiasPercent = 0.5f;
        } else if (anchor == centerX) {
            if (left.isConnected() && right.isConnected() && left.getTarget().getOwner() == right.getTarget().getOwner()) {
                left.reset();
                right.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
        } else if (anchor == centerY) {
            if (top.isConnected() && bottom.isConnected() && top.getTarget().getOwner() == bottom.getTarget().getOwner()) {
                top.reset();
                bottom.reset();
            }
            this.mVerticalBiasPercent = 0.5f;
        } else if (anchor == left || anchor == right) {
            if (left.isConnected() && left.getTarget() == right.getTarget()) {
                center.reset();
            }
        } else if ((anchor == top || anchor == bottom) && top.isConnected() && top.getTarget() == bottom.getTarget()) {
            center.reset();
        }
        anchor.reset();
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer)) {
            ConstraintWidgetContainer parentContainer = (ConstraintWidgetContainer) getParent();
            if (parentContainer.handlesInternalConstraints()) {
                return;
            }
        }
        int mAnchorsSize = this.mAnchors.size();
        for (int i = 0; i < mAnchorsSize; i++) {
            ConstraintAnchor anchor = this.mAnchors.get(i);
            anchor.reset();
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type anchorType) {
        switch (anchorType) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                throw new AssertionError(anchorType.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int orientation) {
        if (orientation == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (orientation == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mListDimensionBehaviors[0] = behaviour;
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mListDimensionBehaviors[1] = behaviour;
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            if (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight) {
                return true;
            }
            return false;
        }
        return true;
    }

    public ConstraintWidget getPreviousChainMember(int orientation) {
        if (orientation == 0) {
            if (this.mLeft.mTarget != null) {
                ConstraintAnchor constraintAnchor = this.mLeft.mTarget.mTarget;
                ConstraintAnchor constraintAnchor2 = this.mLeft;
                if (constraintAnchor == constraintAnchor2) {
                    return constraintAnchor2.mTarget.mOwner;
                }
                return null;
            }
            return null;
        } else if (orientation != 1 || this.mTop.mTarget == null) {
            return null;
        } else {
            ConstraintAnchor constraintAnchor3 = this.mTop.mTarget.mTarget;
            ConstraintAnchor constraintAnchor4 = this.mTop;
            if (constraintAnchor3 == constraintAnchor4) {
                return constraintAnchor4.mTarget.mOwner;
            }
            return null;
        }
    }

    public ConstraintWidget getNextChainMember(int orientation) {
        if (orientation == 0) {
            if (this.mRight.mTarget != null) {
                ConstraintAnchor constraintAnchor = this.mRight.mTarget.mTarget;
                ConstraintAnchor constraintAnchor2 = this.mRight;
                if (constraintAnchor == constraintAnchor2) {
                    return constraintAnchor2.mTarget.mOwner;
                }
                return null;
            }
            return null;
        } else if (orientation != 1 || this.mBottom.mTarget == null) {
            return null;
        } else {
            ConstraintAnchor constraintAnchor3 = this.mBottom.mTarget.mTarget;
            ConstraintAnchor constraintAnchor4 = this.mBottom;
            if (constraintAnchor3 == constraintAnchor4) {
                return constraintAnchor4.mTarget.mOwner;
            }
            return null;
        }
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        ConstraintWidget found = null;
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget tmp = this;
        while (found == null && tmp != null) {
            ConstraintAnchor anchor = tmp.getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor targetOwner = anchor == null ? null : anchor.getTarget();
            ConstraintWidget target = targetOwner == null ? null : targetOwner.getOwner();
            if (target == getParent()) {
                ConstraintWidget found2 = tmp;
                return found2;
            }
            ConstraintAnchor targetAnchor = target != null ? target.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget() : null;
            if (targetAnchor != null && targetAnchor.getOwner() != tmp) {
                found = tmp;
            } else {
                tmp = target;
            }
        }
        return found;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            if (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom) {
                return true;
            }
            return false;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        ConstraintWidget found = null;
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget tmp = this;
        while (found == null && tmp != null) {
            ConstraintAnchor anchor = tmp.getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor targetOwner = anchor == null ? null : anchor.getTarget();
            ConstraintWidget target = targetOwner == null ? null : targetOwner.getOwner();
            if (target == getParent()) {
                ConstraintWidget found2 = tmp;
                return found2;
            }
            ConstraintAnchor targetAnchor = target != null ? target.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget() : null;
            if (targetAnchor != null && targetAnchor.getOwner() != tmp) {
                found = tmp;
            } else {
                tmp = target;
            }
        }
        return found;
    }

    private boolean isChainHead(int orientation) {
        int offset = orientation * 2;
        if (this.mListAnchors[offset].mTarget != null) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[offset].mTarget.mTarget;
            ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
            if (constraintAnchor != constraintAnchorArr[offset] && constraintAnchorArr[offset + 1].mTarget != null && this.mListAnchors[offset + 1].mTarget.mTarget == this.mListAnchors[offset + 1]) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0551  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0564  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0567  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x056a  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0601  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0604  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0644  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x0670  */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addToSolver(LinearSystem system, boolean optimize) {
        boolean horizontalParentWrapContent;
        boolean verticalParentWrapContent;
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2;
        boolean verticalParentWrapContent2;
        boolean inHorizontalChain;
        boolean inVerticalChain;
        int height;
        boolean verticalParentWrapContent3;
        int matchConstraintDefaultHeight;
        int matchConstraintDefaultWidth;
        int height2;
        boolean useRatio;
        int width;
        boolean wrapContent;
        int width2;
        boolean applyPosition;
        boolean horizontalParentWrapContent2;
        boolean useRatio2;
        SolverVariable baseline;
        SolverVariable bottom;
        SolverVariable top;
        SolverVariable right;
        SolverVariable left;
        boolean verticalParentWrapContent4;
        ConstraintWidget constraintWidget3;
        LinearSystem linearSystem;
        SolverVariable baseline2;
        SolverVariable bottom2;
        SolverVariable top2;
        int i;
        ?? r3;
        int i2;
        boolean applyVerticalConstraints;
        int height3;
        boolean applyPosition2;
        boolean z;
        boolean z2;
        char c;
        HorizontalWidgetRun horizontalWidgetRun;
        int i3;
        int i4;
        boolean inHorizontalChain2;
        boolean inVerticalChain2;
        HorizontalWidgetRun horizontalWidgetRun2;
        SolverVariable left2 = system.createObjectVariable(this.mLeft);
        SolverVariable right2 = system.createObjectVariable(this.mRight);
        SolverVariable top3 = system.createObjectVariable(this.mTop);
        SolverVariable bottom3 = system.createObjectVariable(this.mBottom);
        SolverVariable baseline3 = system.createObjectVariable(this.mBaseline);
        ConstraintWidget constraintWidget4 = this.mParent;
        if (constraintWidget4 == null) {
            horizontalParentWrapContent = false;
            verticalParentWrapContent = false;
        } else {
            boolean horizontalParentWrapContent3 = constraintWidget4 != null && constraintWidget4.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            ConstraintWidget constraintWidget5 = this.mParent;
            boolean verticalParentWrapContent5 = constraintWidget5 != null && constraintWidget5.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
            horizontalParentWrapContent = horizontalParentWrapContent3;
            verticalParentWrapContent = verticalParentWrapContent5;
        }
        if (this.mVisibility == 8 && !hasDependencies()) {
            boolean[] zArr = this.mIsInBarrier;
            if (!zArr[0] && !zArr[1]) {
                return;
            }
        }
        if (this.resolvedHorizontal || this.resolvedVertical) {
            if (this.resolvedHorizontal) {
                system.addEquality(left2, this.mX);
                system.addEquality(right2, this.mX + this.mWidth);
                if (horizontalParentWrapContent && (constraintWidget2 = this.mParent) != null) {
                    if (this.OPTIMIZE_WRAP_ON_RESOLVED) {
                        ConstraintWidgetContainer container = (ConstraintWidgetContainer) constraintWidget2;
                        container.addVerticalWrapMinVariable(this.mLeft);
                        container.addHorizontalWrapMaxVariable(this.mRight);
                    } else {
                        system.addGreaterThan(system.createObjectVariable(constraintWidget2.mRight), right2, 0, 5);
                    }
                }
            }
            if (this.resolvedVertical) {
                system.addEquality(top3, this.mY);
                system.addEquality(bottom3, this.mY + this.mHeight);
                if (this.mBaseline.hasDependents()) {
                    system.addEquality(baseline3, this.mY + this.mBaselineDistance);
                }
                if (verticalParentWrapContent && (constraintWidget = this.mParent) != null) {
                    if (this.OPTIMIZE_WRAP_ON_RESOLVED) {
                        ConstraintWidgetContainer container2 = (ConstraintWidgetContainer) constraintWidget;
                        container2.addVerticalWrapMinVariable(this.mTop);
                        container2.addVerticalWrapMaxVariable(this.mBottom);
                    } else {
                        system.addGreaterThan(system.createObjectVariable(constraintWidget.mBottom), bottom3, 0, 5);
                    }
                }
            }
            if (this.resolvedHorizontal && this.resolvedVertical) {
                this.resolvedHorizontal = false;
                this.resolvedVertical = false;
                return;
            }
        }
        if (LinearSystem.sMetrics == null) {
            verticalParentWrapContent2 = verticalParentWrapContent;
        } else {
            verticalParentWrapContent2 = verticalParentWrapContent;
            LinearSystem.sMetrics.widgets++;
        }
        if (optimize && (horizontalWidgetRun2 = this.horizontalRun) != null && this.verticalRun != null && horizontalWidgetRun2.start.resolved && this.horizontalRun.end.resolved && this.verticalRun.start.resolved && this.verticalRun.end.resolved) {
            if (LinearSystem.sMetrics != null) {
                LinearSystem.sMetrics.graphSolved++;
            }
            system.addEquality(left2, this.horizontalRun.start.value);
            system.addEquality(right2, this.horizontalRun.end.value);
            system.addEquality(top3, this.verticalRun.start.value);
            system.addEquality(bottom3, this.verticalRun.end.value);
            system.addEquality(baseline3, this.verticalRun.baseline.value);
            if (this.mParent != null) {
                if (horizontalParentWrapContent && this.isTerminalWidget[0] && !isInHorizontalChain()) {
                    system.addGreaterThan(system.createObjectVariable(this.mParent.mRight), right2, 0, 8);
                }
                if (verticalParentWrapContent2 && this.isTerminalWidget[1] && !isInVerticalChain()) {
                    system.addGreaterThan(system.createObjectVariable(this.mParent.mBottom), bottom3, 0, 8);
                }
            }
            this.resolvedHorizontal = false;
            this.resolvedVertical = false;
            return;
        }
        if (LinearSystem.sMetrics != null) {
            LinearSystem.sMetrics.linearSolved++;
        }
        if (this.mParent == null) {
            inHorizontalChain = false;
            inVerticalChain = false;
        } else {
            if (isChainHead(0)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
                inHorizontalChain2 = true;
            } else {
                inHorizontalChain2 = isInHorizontalChain();
            }
            if (isChainHead(1)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
                inVerticalChain2 = true;
            } else {
                inVerticalChain2 = isInVerticalChain();
            }
            if (!inHorizontalChain2 && horizontalParentWrapContent && this.mVisibility != 8 && this.mLeft.mTarget == null && this.mRight.mTarget == null) {
                SolverVariable parentRight = system.createObjectVariable(this.mParent.mRight);
                system.addGreaterThan(parentRight, right2, 0, 1);
            }
            if (!inVerticalChain2 && verticalParentWrapContent2 && this.mVisibility != 8 && this.mTop.mTarget == null && this.mBottom.mTarget == null && this.mBaseline == null) {
                SolverVariable parentBottom = system.createObjectVariable(this.mParent.mBottom);
                system.addGreaterThan(parentBottom, bottom3, 0, 1);
            }
            inHorizontalChain = inHorizontalChain2;
            inVerticalChain = inVerticalChain2;
        }
        int width3 = this.mWidth;
        if (width3 < this.mMinWidth) {
            width3 = this.mMinWidth;
        }
        int height4 = this.mHeight;
        if (height4 < this.mMinHeight) {
            height4 = this.mMinHeight;
        }
        boolean horizontalDimensionFixed = this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalDimensionFixed = this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean useRatio3 = false;
        this.mResolvedDimensionRatioSide = this.mDimensionRatioSide;
        float f = this.mDimensionRatio;
        this.mResolvedDimensionRatio = f;
        int matchConstraintDefaultWidth2 = this.mMatchConstraintDefaultWidth;
        int matchConstraintDefaultHeight2 = this.mMatchConstraintDefaultHeight;
        int width4 = width3;
        if (f <= 0.0f || this.mVisibility == 8) {
            height = height4;
            verticalParentWrapContent3 = verticalParentWrapContent2;
        } else {
            useRatio3 = true;
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && matchConstraintDefaultWidth2 == 0) {
                matchConstraintDefaultWidth2 = 3;
            }
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && matchConstraintDefaultHeight2 == 0) {
                matchConstraintDefaultHeight2 = 3;
            }
            height = height4;
            if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT || this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT || matchConstraintDefaultWidth2 != 3 || matchConstraintDefaultHeight2 != 3) {
                boolean verticalParentWrapContent6 = verticalParentWrapContent2;
                if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && matchConstraintDefaultWidth2 == 3) {
                    this.mResolvedDimensionRatioSide = 0;
                    width = (int) (this.mResolvedDimensionRatio * this.mHeight);
                    verticalParentWrapContent3 = verticalParentWrapContent6;
                    if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
                        matchConstraintDefaultHeight = matchConstraintDefaultHeight2;
                        matchConstraintDefaultWidth = matchConstraintDefaultWidth2;
                        height2 = height;
                        useRatio = true;
                    } else {
                        matchConstraintDefaultWidth = 4;
                        matchConstraintDefaultHeight = matchConstraintDefaultHeight2;
                        useRatio = false;
                        height2 = height;
                    }
                } else {
                    verticalParentWrapContent3 = verticalParentWrapContent6;
                    if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && matchConstraintDefaultHeight2 == 3) {
                        this.mResolvedDimensionRatioSide = 1;
                        if (this.mDimensionRatioSide == -1) {
                            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                        }
                        int height5 = (int) (this.mResolvedDimensionRatio * this.mWidth);
                        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
                            height2 = height5;
                            matchConstraintDefaultHeight = matchConstraintDefaultHeight2;
                            matchConstraintDefaultWidth = matchConstraintDefaultWidth2;
                            width = width4;
                            useRatio = true;
                        } else {
                            matchConstraintDefaultHeight = 4;
                            height2 = height5;
                            matchConstraintDefaultWidth = matchConstraintDefaultWidth2;
                            width = width4;
                            useRatio = false;
                        }
                    }
                }
                int[] iArr = this.mResolvedMatchConstraintDefault;
                iArr[0] = matchConstraintDefaultWidth;
                iArr[1] = matchConstraintDefaultHeight;
                this.mResolvedHasRatio = useRatio;
                boolean useHorizontalRatio = !useRatio && ((i4 = this.mResolvedDimensionRatioSide) == 0 || i4 == -1);
                boolean useVerticalRatio = !useRatio && ((i3 = this.mResolvedDimensionRatioSide) == 1 || i3 == -1);
                wrapContent = this.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
                if (wrapContent) {
                    width2 = width;
                } else {
                    width2 = 0;
                }
                if (this.mCenter.isConnected()) {
                    applyPosition = true;
                } else {
                    applyPosition = false;
                }
                boolean[] zArr2 = this.mIsInBarrier;
                boolean isInHorizontalBarrier = zArr2[0];
                boolean isInVerticalBarrier = zArr2[1];
                if (this.mHorizontalResolution != 2 || this.resolvedHorizontal) {
                    horizontalParentWrapContent2 = horizontalParentWrapContent;
                    useRatio2 = useRatio;
                    baseline = baseline3;
                    bottom = bottom3;
                    top = top3;
                    right = right2;
                    left = left2;
                    verticalParentWrapContent4 = verticalParentWrapContent3;
                } else {
                    if (optimize && (horizontalWidgetRun = this.horizontalRun) != null && horizontalWidgetRun.start.resolved) {
                        if (!this.horizontalRun.end.resolved) {
                            c = '\b';
                        } else if (optimize) {
                            system.addEquality(left2, this.horizontalRun.start.value);
                            system.addEquality(right2, this.horizontalRun.end.value);
                            if (this.mParent == null) {
                                horizontalParentWrapContent2 = horizontalParentWrapContent;
                                useRatio2 = useRatio;
                                baseline = baseline3;
                                bottom = bottom3;
                                top = top3;
                                right = right2;
                                left = left2;
                                verticalParentWrapContent4 = verticalParentWrapContent3;
                            } else if (horizontalParentWrapContent && this.isTerminalWidget[0] && !isInHorizontalChain()) {
                                system.addGreaterThan(system.createObjectVariable(this.mParent.mRight), right2, 0, 8);
                                horizontalParentWrapContent2 = horizontalParentWrapContent;
                                useRatio2 = useRatio;
                                baseline = baseline3;
                                bottom = bottom3;
                                top = top3;
                                right = right2;
                                left = left2;
                                verticalParentWrapContent4 = verticalParentWrapContent3;
                            } else {
                                horizontalParentWrapContent2 = horizontalParentWrapContent;
                                useRatio2 = useRatio;
                                baseline = baseline3;
                                bottom = bottom3;
                                top = top3;
                                right = right2;
                                left = left2;
                                verticalParentWrapContent4 = verticalParentWrapContent3;
                            }
                        } else {
                            horizontalParentWrapContent2 = horizontalParentWrapContent;
                            useRatio2 = useRatio;
                            baseline = baseline3;
                            bottom = bottom3;
                            top = top3;
                            right = right2;
                            left = left2;
                            verticalParentWrapContent4 = verticalParentWrapContent3;
                        }
                    } else {
                        c = '\b';
                    }
                    ConstraintWidget constraintWidget6 = this.mParent;
                    SolverVariable parentMax = constraintWidget6 != null ? system.createObjectVariable(constraintWidget6.mRight) : null;
                    ConstraintWidget constraintWidget7 = this.mParent;
                    SolverVariable parentMin = constraintWidget7 != null ? system.createObjectVariable(constraintWidget7.mLeft) : null;
                    boolean z3 = this.isTerminalWidget[0];
                    DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
                    verticalParentWrapContent4 = verticalParentWrapContent3;
                    boolean horizontalDimensionFixed2 = horizontalParentWrapContent;
                    horizontalParentWrapContent2 = horizontalParentWrapContent;
                    useRatio2 = useRatio;
                    baseline = baseline3;
                    bottom = bottom3;
                    top = top3;
                    right = right2;
                    left = left2;
                    applyConstraints(system, true, horizontalDimensionFixed2, verticalParentWrapContent4, z3, parentMin, parentMax, dimensionBehaviourArr[0], wrapContent, this.mLeft, this.mRight, this.mX, width2, this.mMinWidth, this.mMaxDimension[0], this.mHorizontalBiasPercent, useHorizontalRatio, dimensionBehaviourArr[1] == DimensionBehaviour.MATCH_CONSTRAINT, inHorizontalChain, inVerticalChain, isInHorizontalBarrier, matchConstraintDefaultWidth, matchConstraintDefaultHeight, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, applyPosition);
                }
                boolean applyVerticalConstraints2 = true;
                if (optimize) {
                    constraintWidget3 = this;
                } else {
                    constraintWidget3 = this;
                    VerticalWidgetRun verticalWidgetRun = constraintWidget3.verticalRun;
                    if (verticalWidgetRun != null && verticalWidgetRun.start.resolved && constraintWidget3.verticalRun.end.resolved) {
                        linearSystem = system;
                        top2 = top;
                        linearSystem.addEquality(top2, constraintWidget3.verticalRun.start.value);
                        bottom2 = bottom;
                        linearSystem.addEquality(bottom2, constraintWidget3.verticalRun.end.value);
                        baseline2 = baseline;
                        linearSystem.addEquality(baseline2, constraintWidget3.verticalRun.baseline.value);
                        ConstraintWidget constraintWidget8 = constraintWidget3.mParent;
                        if (constraintWidget8 == null) {
                            i = 8;
                            z = true;
                            i2 = 0;
                        } else {
                            if (inVerticalChain || !verticalParentWrapContent4) {
                                i = 8;
                                z2 = true;
                            } else {
                                z2 = true;
                                z = true;
                                if (!constraintWidget3.isTerminalWidget[1]) {
                                    i = 8;
                                } else {
                                    i = 8;
                                    i2 = 0;
                                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(constraintWidget8.mBottom), bottom2, 0, 8);
                                }
                            }
                            i2 = 0;
                            z = z2;
                        }
                        applyVerticalConstraints2 = false;
                        r3 = z;
                        if (constraintWidget3.mVerticalResolution != 2) {
                            applyVerticalConstraints = applyVerticalConstraints2;
                        } else {
                            applyVerticalConstraints = false;
                        }
                        if (!applyVerticalConstraints && !constraintWidget3.resolvedVertical) {
                            boolean wrapContent2 = (constraintWidget3.mListDimensionBehaviors[r3] == DimensionBehaviour.WRAP_CONTENT && (constraintWidget3 instanceof ConstraintWidgetContainer)) ? r3 : i2;
                            if (!wrapContent2) {
                                height3 = height2;
                            } else {
                                height3 = 0;
                            }
                            ConstraintWidget constraintWidget9 = constraintWidget3.mParent;
                            SolverVariable parentMax2 = constraintWidget9 != null ? linearSystem.createObjectVariable(constraintWidget9.mBottom) : null;
                            ConstraintWidget constraintWidget10 = constraintWidget3.mParent;
                            SolverVariable parentMin2 = constraintWidget10 != null ? linearSystem.createObjectVariable(constraintWidget10.mTop) : null;
                            if (constraintWidget3.mBaselineDistance > 0 || constraintWidget3.mVisibility == i) {
                                if (constraintWidget3.mBaseline.mTarget != null) {
                                    linearSystem.addEquality(baseline2, top2, getBaselineDistance(), i);
                                    SolverVariable baselineTarget = linearSystem.createObjectVariable(constraintWidget3.mBaseline.mTarget);
                                    linearSystem.addEquality(baseline2, baselineTarget, 0, i);
                                    applyPosition2 = false;
                                    if (verticalParentWrapContent4) {
                                        SolverVariable end = linearSystem.createObjectVariable(constraintWidget3.mBottom);
                                        linearSystem.addGreaterThan(parentMax2, end, i2, 5);
                                    }
                                    boolean z4 = constraintWidget3.isTerminalWidget[r3];
                                    DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidget3.mListDimensionBehaviors;
                                    applyConstraints(system, false, verticalParentWrapContent4, horizontalParentWrapContent2, z4, parentMin2, parentMax2, dimensionBehaviourArr2[r3], wrapContent2, constraintWidget3.mTop, constraintWidget3.mBottom, constraintWidget3.mY, height3, constraintWidget3.mMinHeight, constraintWidget3.mMaxDimension[r3], constraintWidget3.mVerticalBiasPercent, useVerticalRatio, dimensionBehaviourArr2[0] != DimensionBehaviour.MATCH_CONSTRAINT, inVerticalChain, inHorizontalChain, isInVerticalBarrier, matchConstraintDefaultHeight, matchConstraintDefaultWidth, constraintWidget3.mMatchConstraintMinHeight, constraintWidget3.mMatchConstraintMaxHeight, constraintWidget3.mMatchConstraintPercentHeight, applyPosition2);
                                } else if (constraintWidget3.mVisibility == i) {
                                    linearSystem.addEquality(baseline2, top2, i2, i);
                                } else {
                                    linearSystem.addEquality(baseline2, top2, getBaselineDistance(), i);
                                }
                            }
                            applyPosition2 = applyPosition;
                            boolean z42 = constraintWidget3.isTerminalWidget[r3];
                            DimensionBehaviour[] dimensionBehaviourArr22 = constraintWidget3.mListDimensionBehaviors;
                            applyConstraints(system, false, verticalParentWrapContent4, horizontalParentWrapContent2, z42, parentMin2, parentMax2, dimensionBehaviourArr22[r3], wrapContent2, constraintWidget3.mTop, constraintWidget3.mBottom, constraintWidget3.mY, height3, constraintWidget3.mMinHeight, constraintWidget3.mMaxDimension[r3], constraintWidget3.mVerticalBiasPercent, useVerticalRatio, dimensionBehaviourArr22[0] != DimensionBehaviour.MATCH_CONSTRAINT, inVerticalChain, inHorizontalChain, isInVerticalBarrier, matchConstraintDefaultHeight, matchConstraintDefaultWidth, constraintWidget3.mMatchConstraintMinHeight, constraintWidget3.mMatchConstraintMaxHeight, constraintWidget3.mMatchConstraintPercentHeight, applyPosition2);
                        }
                        if (useRatio2) {
                            if (constraintWidget3.mResolvedDimensionRatioSide == 1) {
                                system.addRatio(bottom2, top2, right, left, constraintWidget3.mResolvedDimensionRatio, 8);
                            } else {
                                system.addRatio(right, left, bottom2, top2, constraintWidget3.mResolvedDimensionRatio, 8);
                            }
                        }
                        if (constraintWidget3.mCenter.isConnected()) {
                            linearSystem.addCenterPoint(constraintWidget3, constraintWidget3.mCenter.getTarget().getOwner(), (float) Math.toRadians(constraintWidget3.mCircleConstraintAngle + 90.0f), constraintWidget3.mCenter.getMargin());
                        }
                        constraintWidget3.resolvedHorizontal = false;
                        constraintWidget3.resolvedVertical = false;
                    }
                }
                linearSystem = system;
                baseline2 = baseline;
                bottom2 = bottom;
                top2 = top;
                i = 8;
                r3 = 1;
                i2 = 0;
                if (constraintWidget3.mVerticalResolution != 2) {
                }
                if (!applyVerticalConstraints) {
                }
                if (useRatio2) {
                }
                if (constraintWidget3.mCenter.isConnected()) {
                }
                constraintWidget3.resolvedHorizontal = false;
                constraintWidget3.resolvedVertical = false;
            }
            boolean verticalParentWrapContent7 = verticalParentWrapContent2;
            setupDimensionRatio(horizontalParentWrapContent, verticalParentWrapContent7, horizontalDimensionFixed, verticalDimensionFixed);
            verticalParentWrapContent3 = verticalParentWrapContent7;
        }
        matchConstraintDefaultHeight = matchConstraintDefaultHeight2;
        matchConstraintDefaultWidth = matchConstraintDefaultWidth2;
        width = width4;
        height2 = height;
        useRatio = useRatio3;
        int[] iArr2 = this.mResolvedMatchConstraintDefault;
        iArr2[0] = matchConstraintDefaultWidth;
        iArr2[1] = matchConstraintDefaultHeight;
        this.mResolvedHasRatio = useRatio;
        if (useRatio) {
        }
        if (useRatio) {
        }
        wrapContent = this.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
        if (wrapContent) {
        }
        if (this.mCenter.isConnected()) {
        }
        boolean[] zArr22 = this.mIsInBarrier;
        boolean isInHorizontalBarrier2 = zArr22[0];
        boolean isInVerticalBarrier2 = zArr22[1];
        if (this.mHorizontalResolution != 2) {
        }
        horizontalParentWrapContent2 = horizontalParentWrapContent;
        useRatio2 = useRatio;
        baseline = baseline3;
        bottom = bottom3;
        top = top3;
        right = right2;
        left = left2;
        verticalParentWrapContent4 = verticalParentWrapContent3;
        boolean applyVerticalConstraints22 = true;
        if (optimize) {
        }
        linearSystem = system;
        baseline2 = baseline;
        bottom2 = bottom;
        top2 = top;
        i = 8;
        r3 = 1;
        i2 = 0;
        if (constraintWidget3.mVerticalResolution != 2) {
        }
        if (!applyVerticalConstraints) {
        }
        if (useRatio2) {
        }
        if (constraintWidget3.mCenter.isConnected()) {
        }
        constraintWidget3.resolvedHorizontal = false;
        constraintWidget3.resolvedVertical = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addFirst() {
        return (this instanceof VirtualLayout) || (this instanceof Guideline);
    }

    public void setupDimensionRatio(boolean hparentWrapContent, boolean vparentWrapContent, boolean horizontalDimensionFixed, boolean verticalDimensionFixed) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (horizontalDimensionFixed && !verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!horizontalDimensionFixed && verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:251:0x0585, code lost:
        if ((r4 instanceof androidx.constraintlayout.solver.widgets.Barrier) != false) goto L230;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void applyConstraints(LinearSystem system, boolean isHorizontal, boolean parentWrapContent, boolean oppositeParentWrapContent, boolean isTerminal, SolverVariable parentMin, SolverVariable parentMax, DimensionBehaviour dimensionBehaviour, boolean wrapContent, ConstraintAnchor beginAnchor, ConstraintAnchor endAnchor, int beginPosition, int dimension, int minDimension, int maxDimension, float bias, boolean useRatio, boolean oppositeVariable, boolean inChain, boolean oppositeInChain, boolean inBarrier, int matchConstraintDefault, int oppositeMatchConstraintDefault, int matchMinDimension, int matchMaxDimension, float matchPercentDimension, boolean applyPosition) {
        boolean dimension2;
        int dimension3;
        boolean isEndConnected;
        SolverVariable beginTarget;
        SolverVariable endTarget;
        char c;
        SolverVariable end;
        boolean variableSize;
        int matchMaxDimension2;
        int matchMinDimension2;
        boolean variableSize2;
        SolverVariable percentBegin;
        SolverVariable percentEnd;
        SolverVariable solverVariable;
        ConstraintWidget constraintWidget;
        int numConnections;
        SolverVariable solverVariable2;
        ConstraintWidget constraintWidget2;
        int wrapStrength;
        SolverVariable endTarget2;
        boolean applyCentering;
        ConstraintWidget endWidget;
        int wrapStrength2;
        SolverVariable begin;
        int matchConstraintDefault2;
        SolverVariable beginTarget2;
        SolverVariable begin2;
        ConstraintWidget beginWidget;
        SolverVariable beginTarget3;
        int applyRangeCheck;
        int applyRangeCheck2;
        int wrapStrength3;
        SolverVariable beginTarget4;
        SolverVariable endTarget3;
        SolverVariable endTarget4;
        boolean applyBoundsCheck;
        boolean parentWrapContent2;
        ConstraintWidget endWidget2;
        SolverVariable begin3;
        int rangeCheckStrength;
        int boundsCheckStrength;
        int i;
        SolverVariable endTarget5;
        SolverVariable beginTarget5;
        boolean applyCentering2;
        int boundsCheckStrength2;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4;
        int i2;
        SolverVariable begin4 = system.createObjectVariable(beginAnchor);
        SolverVariable end2 = system.createObjectVariable(endAnchor);
        SolverVariable beginTarget6 = system.createObjectVariable(beginAnchor.getTarget());
        SolverVariable endTarget6 = system.createObjectVariable(endAnchor.getTarget());
        if (LinearSystem.getMetrics() != null) {
            LinearSystem.getMetrics().nonresolvedWidgets++;
        }
        boolean isBeginConnected = beginAnchor.isConnected();
        boolean isEndConnected2 = endAnchor.isConnected();
        boolean isCenterConnected = this.mCenter.isConnected();
        int numConnections2 = isBeginConnected ? 0 + 1 : 0;
        if (isEndConnected2) {
            numConnections2++;
        }
        int numConnections3 = isCenterConnected ? numConnections2 + 1 : numConnections2;
        int matchConstraintDefault3 = useRatio ? 3 : matchConstraintDefault;
        int i3 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[dimensionBehaviour.ordinal()];
        boolean variableSize3 = i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? false : matchConstraintDefault3 != 4 : false : false : false;
        if (this.mVisibility == 8) {
            dimension2 = false;
            dimension3 = 0;
        } else {
            dimension2 = variableSize3;
            dimension3 = dimension;
        }
        if (!applyPosition) {
            isEndConnected = isEndConnected2;
        } else if (!isBeginConnected && !isEndConnected2 && !isCenterConnected) {
            system.addEquality(begin4, beginPosition);
            isEndConnected = isEndConnected2;
        } else if (!isBeginConnected || isEndConnected2) {
            isEndConnected = isEndConnected2;
        } else {
            isEndConnected = isEndConnected2;
            system.addEquality(begin4, beginTarget6, beginAnchor.getMargin(), 8);
        }
        if (!dimension2) {
            if (wrapContent) {
                system.addEquality(end2, begin4, 0, 3);
                if (minDimension > 0) {
                    i2 = 8;
                    system.addGreaterThan(end2, begin4, minDimension, 8);
                } else {
                    i2 = 8;
                }
                if (maxDimension < Integer.MAX_VALUE) {
                    system.addLowerThan(end2, begin4, maxDimension, i2);
                }
            } else {
                system.addEquality(end2, begin4, dimension3, 8);
            }
            matchMaxDimension2 = matchMaxDimension;
            beginTarget = beginTarget6;
            end = end2;
            variableSize = dimension2;
            endTarget = endTarget6;
            c = 3;
            variableSize2 = isTerminal;
            matchMinDimension2 = matchMinDimension;
        } else if (numConnections3 == 2 || useRatio || !(matchConstraintDefault3 == 1 || matchConstraintDefault3 == 0)) {
            int matchMinDimension3 = matchMinDimension == -2 ? dimension3 : matchMinDimension;
            int matchMaxDimension3 = matchMaxDimension == -2 ? dimension3 : matchMaxDimension;
            if (dimension3 > 0 && matchConstraintDefault3 != 1) {
                dimension3 = 0;
            }
            if (matchMinDimension3 > 0) {
                system.addGreaterThan(end2, begin4, matchMinDimension3, 8);
                dimension3 = Math.max(dimension3, matchMinDimension3);
            }
            if (matchMaxDimension3 > 0) {
                boolean applyLimit = true;
                if (parentWrapContent && matchConstraintDefault3 == 1) {
                    applyLimit = false;
                }
                if (applyLimit) {
                    system.addLowerThan(end2, begin4, matchMaxDimension3, 8);
                }
                dimension3 = Math.min(dimension3, matchMaxDimension3);
            }
            if (matchConstraintDefault3 == 1) {
                if (parentWrapContent) {
                    system.addEquality(end2, begin4, dimension3, 8);
                } else if (inChain) {
                    system.addEquality(end2, begin4, dimension3, 5);
                    system.addLowerThan(end2, begin4, dimension3, 8);
                } else {
                    system.addEquality(end2, begin4, dimension3, 5);
                    system.addLowerThan(end2, begin4, dimension3, 8);
                }
                matchMaxDimension2 = matchMaxDimension3;
                beginTarget = beginTarget6;
                variableSize = dimension2;
                endTarget = endTarget6;
                c = 3;
                variableSize2 = isTerminal;
                matchMinDimension2 = matchMinDimension3;
                end = end2;
            } else if (matchConstraintDefault3 == 2) {
                if (beginAnchor.getType() == ConstraintAnchor.Type.TOP || beginAnchor.getType() == ConstraintAnchor.Type.BOTTOM) {
                    SolverVariable percentBegin2 = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.TOP));
                    percentBegin = percentBegin2;
                    percentEnd = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.BOTTOM));
                } else {
                    SolverVariable percentBegin3 = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.LEFT));
                    percentBegin = percentBegin3;
                    percentEnd = system.createObjectVariable(this.mParent.getAnchor(ConstraintAnchor.Type.RIGHT));
                }
                int matchMinDimension4 = matchMinDimension3;
                endTarget = endTarget6;
                c = 3;
                beginTarget = beginTarget6;
                end = end2;
                system.addConstraint(system.createRow().createRowDimensionRatio(end2, begin4, percentEnd, percentBegin, matchPercentDimension));
                variableSize = false;
                matchMaxDimension2 = matchMaxDimension3;
                variableSize2 = isTerminal;
                matchMinDimension2 = matchMinDimension4;
            } else {
                beginTarget = beginTarget6;
                int matchMinDimension5 = matchMinDimension3;
                endTarget = endTarget6;
                c = 3;
                end = end2;
                variableSize = dimension2;
                matchMaxDimension2 = matchMaxDimension3;
                matchMinDimension2 = matchMinDimension5;
                variableSize2 = true;
            }
        } else {
            int d = Math.max(matchMinDimension, dimension3);
            if (matchMaxDimension > 0) {
                d = Math.min(matchMaxDimension, d);
            }
            system.addEquality(end2, begin4, d, 8);
            matchMaxDimension2 = matchMaxDimension;
            beginTarget = beginTarget6;
            end = end2;
            variableSize = false;
            endTarget = endTarget6;
            c = 3;
            variableSize2 = isTerminal;
            matchMinDimension2 = matchMinDimension;
        }
        if (!applyPosition) {
            solverVariable = parentMax;
            constraintWidget = this;
            numConnections = numConnections3;
            solverVariable2 = parentMin;
        } else if (!inChain) {
            if (!isBeginConnected && !isEndConnected && !isCenterConnected) {
                constraintWidget2 = this;
                wrapStrength = 5;
                endTarget2 = endTarget;
            } else if (isBeginConnected && !isEndConnected) {
                constraintWidget2 = this;
                wrapStrength = 5;
                endTarget2 = endTarget;
            } else if (!isBeginConnected && isEndConnected) {
                system.addEquality(end, endTarget, -endAnchor.getMargin(), 8);
                if (!parentWrapContent) {
                    constraintWidget2 = this;
                    wrapStrength = 5;
                    endTarget2 = endTarget;
                } else if (this.OPTIMIZE_WRAP && begin4.isFinalValue && (constraintWidget3 = this.mParent) != null) {
                    ConstraintWidgetContainer container = (ConstraintWidgetContainer) constraintWidget3;
                    if (isHorizontal) {
                        container.addHorizontalWrapMinVariable(beginAnchor);
                    } else {
                        container.addVerticalWrapMinVariable(beginAnchor);
                    }
                    constraintWidget2 = this;
                    wrapStrength = 5;
                    endTarget2 = endTarget;
                } else {
                    system.addGreaterThan(begin4, parentMin, 0, 5);
                    constraintWidget2 = this;
                    wrapStrength = 5;
                    endTarget2 = endTarget;
                }
            } else if (isBeginConnected && isEndConnected) {
                boolean applyBoundsCheck2 = true;
                boolean applyStrongChecks = false;
                int boundsCheckStrength3 = 4;
                int centeringStrength = 6;
                int rangeCheckStrength2 = parentWrapContent ? 5 : 5;
                ConstraintWidget beginWidget2 = beginAnchor.mTarget.mOwner;
                int matchConstraintDefault4 = matchConstraintDefault3;
                ConstraintWidget endWidget3 = endAnchor.mTarget.mOwner;
                ConstraintWidget parent = getParent();
                if (!variableSize) {
                    SolverVariable beginTarget7 = beginTarget;
                    applyCentering = true;
                    if (beginTarget7.isFinalValue && endTarget.isFinalValue) {
                        SolverVariable endTarget7 = endTarget;
                        system.addCentering(begin4, beginTarget7, beginAnchor.getMargin(), bias, endTarget, end, endAnchor.getMargin(), 8);
                        if (parentWrapContent && variableSize2) {
                            int margin = endAnchor.mTarget != null ? endAnchor.getMargin() : 0;
                            if (endTarget7 != parentMax) {
                                system.addGreaterThan(parentMax, end, margin, 5);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    endWidget = endWidget3;
                    wrapStrength2 = 5;
                    begin = begin4;
                    matchConstraintDefault2 = matchConstraintDefault4;
                    beginTarget2 = beginTarget7;
                    begin2 = parentMax;
                    beginWidget = beginWidget2;
                    beginTarget3 = endTarget;
                    applyRangeCheck = 1;
                } else if (matchConstraintDefault4 == 0) {
                    if (matchMaxDimension2 == 0 && matchMinDimension2 == 0) {
                        applyStrongChecks = true;
                        beginTarget5 = beginTarget;
                        boolean applyRangeCheck3 = beginTarget5.isFinalValue;
                        if (applyRangeCheck3 && endTarget.isFinalValue) {
                            system.addEquality(begin4, beginTarget5, beginAnchor.getMargin(), 8);
                            system.addEquality(end, endTarget, -endAnchor.getMargin(), 8);
                            return;
                        }
                        applyCentering2 = false;
                        rangeCheckStrength2 = 8;
                        boundsCheckStrength3 = 8;
                        boundsCheckStrength2 = 0;
                    } else {
                        beginTarget5 = beginTarget;
                        applyCentering2 = true;
                        applyBoundsCheck2 = true;
                        boundsCheckStrength2 = 1;
                        rangeCheckStrength2 = 5;
                        boundsCheckStrength3 = 5;
                    }
                    if ((beginWidget2 instanceof Barrier) || (endWidget3 instanceof Barrier)) {
                        boundsCheckStrength3 = 4;
                        endWidget = endWidget3;
                        applyCentering = applyCentering2;
                        wrapStrength2 = 5;
                        begin = begin4;
                        matchConstraintDefault2 = matchConstraintDefault4;
                        beginTarget2 = beginTarget5;
                        applyRangeCheck = boundsCheckStrength2;
                        begin2 = parentMax;
                        beginWidget = beginWidget2;
                        beginTarget3 = endTarget;
                    } else {
                        endWidget = endWidget3;
                        applyCentering = applyCentering2;
                        wrapStrength2 = 5;
                        begin = begin4;
                        matchConstraintDefault2 = matchConstraintDefault4;
                        beginTarget2 = beginTarget5;
                        applyRangeCheck = boundsCheckStrength2;
                        begin2 = parentMax;
                        beginWidget = beginWidget2;
                        beginTarget3 = endTarget;
                    }
                } else {
                    SolverVariable beginTarget8 = beginTarget;
                    if (matchConstraintDefault4 == 1) {
                        applyRangeCheck = 1;
                        rangeCheckStrength2 = 8;
                        endWidget = endWidget3;
                        applyCentering = true;
                        wrapStrength2 = 5;
                        begin = begin4;
                        matchConstraintDefault2 = matchConstraintDefault4;
                        beginTarget2 = beginTarget8;
                        begin2 = parentMax;
                        beginWidget = beginWidget2;
                        beginTarget3 = endTarget;
                    } else if (matchConstraintDefault4 != 3) {
                        applyCentering = false;
                        applyRangeCheck = 0;
                        endWidget = endWidget3;
                        wrapStrength2 = 5;
                        begin = begin4;
                        matchConstraintDefault2 = matchConstraintDefault4;
                        beginTarget2 = beginTarget8;
                        begin2 = parentMax;
                        beginWidget = beginWidget2;
                        beginTarget3 = endTarget;
                    } else if (this.mResolvedDimensionRatioSide == -1) {
                        applyRangeCheck = 1;
                        applyStrongChecks = true;
                        rangeCheckStrength2 = 8;
                        boundsCheckStrength3 = 5;
                        if (oppositeInChain) {
                            boundsCheckStrength3 = 5;
                            centeringStrength = 4;
                            if (parentWrapContent) {
                                centeringStrength = 5;
                                endWidget = endWidget3;
                                applyCentering = true;
                                wrapStrength2 = 5;
                                begin = begin4;
                                matchConstraintDefault2 = matchConstraintDefault4;
                                beginTarget2 = beginTarget8;
                                begin2 = parentMax;
                                beginWidget = beginWidget2;
                                beginTarget3 = endTarget;
                            } else {
                                endWidget = endWidget3;
                                applyCentering = true;
                                wrapStrength2 = 5;
                                begin = begin4;
                                matchConstraintDefault2 = matchConstraintDefault4;
                                beginTarget2 = beginTarget8;
                                begin2 = parentMax;
                                beginWidget = beginWidget2;
                                beginTarget3 = endTarget;
                            }
                        } else {
                            centeringStrength = 8;
                            endWidget = endWidget3;
                            applyCentering = true;
                            wrapStrength2 = 5;
                            begin = begin4;
                            matchConstraintDefault2 = matchConstraintDefault4;
                            beginTarget2 = beginTarget8;
                            begin2 = parentMax;
                            beginWidget = beginWidget2;
                            beginTarget3 = endTarget;
                        }
                    } else {
                        applyRangeCheck = 1;
                        applyStrongChecks = true;
                        if (useRatio) {
                            boolean otherSideInvariable = oppositeMatchConstraintDefault == 2 || oppositeMatchConstraintDefault == 1;
                            if (!otherSideInvariable) {
                                rangeCheckStrength2 = 8;
                                boundsCheckStrength3 = 5;
                            }
                            applyCentering = true;
                            endWidget = endWidget3;
                            wrapStrength2 = 5;
                            begin = begin4;
                            matchConstraintDefault2 = matchConstraintDefault4;
                            beginTarget2 = beginTarget8;
                            begin2 = parentMax;
                            beginWidget = beginWidget2;
                            beginTarget3 = endTarget;
                        } else {
                            rangeCheckStrength2 = 5;
                            if (matchMaxDimension2 > 0) {
                                boundsCheckStrength3 = 5;
                                applyCentering = true;
                                endWidget = endWidget3;
                                wrapStrength2 = 5;
                                begin = begin4;
                                matchConstraintDefault2 = matchConstraintDefault4;
                                beginTarget2 = beginTarget8;
                                begin2 = parentMax;
                                beginWidget = beginWidget2;
                                beginTarget3 = endTarget;
                            } else if (matchMaxDimension2 != 0 || matchMinDimension2 != 0) {
                                applyCentering = true;
                                endWidget = endWidget3;
                                wrapStrength2 = 5;
                                begin = begin4;
                                matchConstraintDefault2 = matchConstraintDefault4;
                                beginTarget2 = beginTarget8;
                                begin2 = parentMax;
                                beginWidget = beginWidget2;
                                beginTarget3 = endTarget;
                            } else if (oppositeInChain) {
                                rangeCheckStrength2 = (beginWidget2 == parent || endWidget3 == parent) ? 5 : 4;
                                boundsCheckStrength3 = 4;
                                applyCentering = true;
                                endWidget = endWidget3;
                                wrapStrength2 = 5;
                                begin = begin4;
                                matchConstraintDefault2 = matchConstraintDefault4;
                                beginTarget2 = beginTarget8;
                                begin2 = parentMax;
                                beginWidget = beginWidget2;
                                beginTarget3 = endTarget;
                            } else {
                                boundsCheckStrength3 = 8;
                                applyCentering = true;
                                endWidget = endWidget3;
                                wrapStrength2 = 5;
                                begin = begin4;
                                matchConstraintDefault2 = matchConstraintDefault4;
                                beginTarget2 = beginTarget8;
                                begin2 = parentMax;
                                beginWidget = beginWidget2;
                                beginTarget3 = endTarget;
                            }
                        }
                    }
                }
                if (applyRangeCheck == 0 || beginTarget2 != beginTarget3 || beginWidget == parent) {
                    applyRangeCheck2 = applyRangeCheck;
                } else {
                    applyBoundsCheck2 = false;
                    applyRangeCheck2 = 0;
                }
                if (applyCentering) {
                    if (variableSize || oppositeVariable || oppositeInChain) {
                        endTarget5 = beginTarget3;
                        endTarget4 = parentMin;
                    } else {
                        endTarget5 = beginTarget3;
                        endTarget4 = parentMin;
                        if (beginTarget2 == endTarget4 && endTarget5 == begin2) {
                            centeringStrength = 8;
                            rangeCheckStrength2 = 8;
                            applyBoundsCheck = false;
                            parentWrapContent2 = false;
                            wrapStrength3 = wrapStrength2;
                            endTarget3 = endTarget5;
                            beginTarget4 = beginTarget2;
                            system.addCentering(begin, beginTarget2, beginAnchor.getMargin(), bias, endTarget5, end, endAnchor.getMargin(), centeringStrength);
                        }
                    }
                    applyBoundsCheck = applyBoundsCheck2;
                    parentWrapContent2 = parentWrapContent;
                    wrapStrength3 = wrapStrength2;
                    endTarget3 = endTarget5;
                    beginTarget4 = beginTarget2;
                    system.addCentering(begin, beginTarget2, beginAnchor.getMargin(), bias, endTarget5, end, endAnchor.getMargin(), centeringStrength);
                } else {
                    wrapStrength3 = wrapStrength2;
                    beginTarget4 = beginTarget2;
                    endTarget3 = beginTarget3;
                    endTarget4 = parentMin;
                    applyBoundsCheck = applyBoundsCheck2;
                    parentWrapContent2 = parentWrapContent;
                }
                constraintWidget2 = this;
                wrapStrength = wrapStrength3;
                if (constraintWidget2.mVisibility != 8 || endAnchor.hasDependents()) {
                    if (applyRangeCheck2 != 0) {
                        if (parentWrapContent2) {
                            endTarget2 = endTarget3;
                            if (beginTarget4 == endTarget2 || variableSize) {
                                endWidget2 = endWidget;
                            } else {
                                if (beginWidget instanceof Barrier) {
                                    endWidget2 = endWidget;
                                } else {
                                    endWidget2 = endWidget;
                                }
                                rangeCheckStrength = 6;
                                begin3 = begin;
                                system.addGreaterThan(begin3, beginTarget4, beginAnchor.getMargin(), rangeCheckStrength);
                                system.addLowerThan(end, endTarget2, -endAnchor.getMargin(), rangeCheckStrength);
                            }
                        } else {
                            endWidget2 = endWidget;
                            endTarget2 = endTarget3;
                        }
                        rangeCheckStrength = rangeCheckStrength2;
                        begin3 = begin;
                        system.addGreaterThan(begin3, beginTarget4, beginAnchor.getMargin(), rangeCheckStrength);
                        system.addLowerThan(end, endTarget2, -endAnchor.getMargin(), rangeCheckStrength);
                    } else {
                        endWidget2 = endWidget;
                        endTarget2 = endTarget3;
                        begin3 = begin;
                        rangeCheckStrength = rangeCheckStrength2;
                    }
                    if (!parentWrapContent2 || !inBarrier || (beginWidget instanceof Barrier) || (endWidget2 instanceof Barrier)) {
                        boundsCheckStrength = boundsCheckStrength3;
                    } else {
                        rangeCheckStrength = 6;
                        applyBoundsCheck = true;
                        boundsCheckStrength = 6;
                    }
                    if (applyBoundsCheck) {
                        if (applyStrongChecks && (!oppositeInChain || oppositeParentWrapContent)) {
                            int strength = boundsCheckStrength;
                            strength = (beginWidget == parent || endWidget2 == parent) ? 6 : 6;
                            strength = ((beginWidget instanceof Guideline) || (endWidget2 instanceof Guideline)) ? 5 : 5;
                            strength = ((beginWidget instanceof Barrier) || (endWidget2 instanceof Barrier)) ? 5 : 5;
                            if (oppositeInChain) {
                                strength = 5;
                            }
                            boundsCheckStrength = Math.max(strength, boundsCheckStrength);
                        }
                        if (parentWrapContent2) {
                            boundsCheckStrength = Math.min(rangeCheckStrength, boundsCheckStrength);
                            if (useRatio && !oppositeInChain && (beginWidget == parent || endWidget2 == parent)) {
                                boundsCheckStrength = 4;
                            }
                        }
                        system.addEquality(begin3, beginTarget4, beginAnchor.getMargin(), boundsCheckStrength);
                        system.addEquality(end, endTarget2, -endAnchor.getMargin(), boundsCheckStrength);
                    }
                    if (parentWrapContent2) {
                        int margin2 = endTarget4 == beginTarget4 ? beginAnchor.getMargin() : 0;
                        if (beginTarget4 != endTarget4) {
                            system.addGreaterThan(begin3, endTarget4, margin2, wrapStrength);
                        }
                    }
                    if (parentWrapContent2 && variableSize) {
                        if (minDimension == 0 && matchMinDimension2 == 0) {
                            if (!variableSize) {
                                i = 0;
                            } else if (matchConstraintDefault2 == 3) {
                                system.addGreaterThan(end, begin3, 0, 8);
                            } else {
                                i = 0;
                            }
                            system.addGreaterThan(end, begin3, i, wrapStrength);
                        }
                    }
                    if (parentWrapContent2 || !variableSize2) {
                        return;
                    }
                    int margin3 = endAnchor.mTarget != null ? endAnchor.getMargin() : 0;
                    if (endTarget2 != parentMax) {
                        if (!constraintWidget2.OPTIMIZE_WRAP || !end.isFinalValue || (constraintWidget4 = constraintWidget2.mParent) == null) {
                            system.addGreaterThan(parentMax, end, margin3, wrapStrength);
                            return;
                        }
                        ConstraintWidgetContainer container2 = (ConstraintWidgetContainer) constraintWidget4;
                        if (isHorizontal) {
                            container2.addHorizontalWrapMaxVariable(endAnchor);
                            return;
                        } else {
                            container2.addVerticalWrapMaxVariable(endAnchor);
                            return;
                        }
                    }
                    return;
                }
                return;
            } else {
                constraintWidget2 = this;
                wrapStrength = 5;
                endTarget2 = endTarget;
            }
            parentWrapContent2 = parentWrapContent;
            if (parentWrapContent2) {
            }
            return;
        } else {
            solverVariable = parentMax;
            constraintWidget = this;
            numConnections = numConnections3;
            solverVariable2 = parentMin;
        }
        if (numConnections < 2 && parentWrapContent && variableSize2) {
            system.addGreaterThan(begin4, solverVariable2, 0, 8);
            boolean applyEnd = isHorizontal || constraintWidget.mBaseline.mTarget == null;
            if (!isHorizontal && constraintWidget.mBaseline.mTarget != null) {
                ConstraintWidget target = constraintWidget.mBaseline.mTarget.mOwner;
                applyEnd = target.mDimensionRatio != 0.0f && target.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && target.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
            }
            if (applyEnd) {
                system.addGreaterThan(solverVariable, end, 0, 8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.solver.widgets.ConstraintWidget$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour = new int[DimensionBehaviour.values().length];

        static {
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type = new int[ConstraintAnchor.Type.values().length];
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    public void updateFromSolver(LinearSystem system, boolean optimize) {
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int left = system.getObjectVariableValue(this.mLeft);
        int top = system.getObjectVariableValue(this.mTop);
        int right = system.getObjectVariableValue(this.mRight);
        int bottom = system.getObjectVariableValue(this.mBottom);
        if (optimize && (horizontalWidgetRun = this.horizontalRun) != null && horizontalWidgetRun.start.resolved && this.horizontalRun.end.resolved) {
            left = this.horizontalRun.start.value;
            right = this.horizontalRun.end.value;
        }
        if (optimize && (verticalWidgetRun = this.verticalRun) != null && verticalWidgetRun.start.resolved && this.verticalRun.end.resolved) {
            top = this.verticalRun.start.value;
            bottom = this.verticalRun.end.value;
        }
        int w = right - left;
        int h = bottom - top;
        if (w < 0 || h < 0 || left == Integer.MIN_VALUE || left == Integer.MAX_VALUE || top == Integer.MIN_VALUE || top == Integer.MAX_VALUE || right == Integer.MIN_VALUE || right == Integer.MAX_VALUE || bottom == Integer.MIN_VALUE || bottom == Integer.MAX_VALUE) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }
        setFrame(left, top, right, bottom);
    }

    public void copy(ConstraintWidget src, HashMap<ConstraintWidget, ConstraintWidget> map) {
        this.mHorizontalResolution = src.mHorizontalResolution;
        this.mVerticalResolution = src.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = src.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = src.mMatchConstraintDefaultHeight;
        int[] iArr = this.mResolvedMatchConstraintDefault;
        int[] iArr2 = src.mResolvedMatchConstraintDefault;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.mMatchConstraintMinWidth = src.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = src.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = src.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = src.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = src.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = src.mIsWidthWrapContent;
        this.mIsHeightWrapContent = src.mIsHeightWrapContent;
        this.mResolvedDimensionRatioSide = src.mResolvedDimensionRatioSide;
        this.mResolvedDimensionRatio = src.mResolvedDimensionRatio;
        int[] iArr3 = src.mMaxDimension;
        this.mMaxDimension = Arrays.copyOf(iArr3, iArr3.length);
        this.mCircleConstraintAngle = src.mCircleConstraintAngle;
        this.hasBaseline = src.hasBaseline;
        this.inPlaceholder = src.inPlaceholder;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[]) Arrays.copyOf(this.mListDimensionBehaviors, 2);
        this.mParent = this.mParent == null ? null : map.get(src.mParent);
        this.mWidth = src.mWidth;
        this.mHeight = src.mHeight;
        this.mDimensionRatio = src.mDimensionRatio;
        this.mDimensionRatioSide = src.mDimensionRatioSide;
        this.mX = src.mX;
        this.mY = src.mY;
        this.mRelX = src.mRelX;
        this.mRelY = src.mRelY;
        this.mOffsetX = src.mOffsetX;
        this.mOffsetY = src.mOffsetY;
        this.mBaselineDistance = src.mBaselineDistance;
        this.mMinWidth = src.mMinWidth;
        this.mMinHeight = src.mMinHeight;
        this.mHorizontalBiasPercent = src.mHorizontalBiasPercent;
        this.mVerticalBiasPercent = src.mVerticalBiasPercent;
        this.mCompanionWidget = src.mCompanionWidget;
        this.mContainerItemSkip = src.mContainerItemSkip;
        this.mVisibility = src.mVisibility;
        this.mDebugName = src.mDebugName;
        this.mType = src.mType;
        this.mDistToTop = src.mDistToTop;
        this.mDistToLeft = src.mDistToLeft;
        this.mDistToRight = src.mDistToRight;
        this.mDistToBottom = src.mDistToBottom;
        this.mLeftHasCentered = src.mLeftHasCentered;
        this.mRightHasCentered = src.mRightHasCentered;
        this.mTopHasCentered = src.mTopHasCentered;
        this.mBottomHasCentered = src.mBottomHasCentered;
        this.mHorizontalWrapVisited = src.mHorizontalWrapVisited;
        this.mVerticalWrapVisited = src.mVerticalWrapVisited;
        this.mHorizontalChainStyle = src.mHorizontalChainStyle;
        this.mVerticalChainStyle = src.mVerticalChainStyle;
        this.mHorizontalChainFixedPosition = src.mHorizontalChainFixedPosition;
        this.mVerticalChainFixedPosition = src.mVerticalChainFixedPosition;
        float[] fArr = this.mWeight;
        float[] fArr2 = src.mWeight;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.mListNextMatchConstraintsWidget;
        ConstraintWidget[] constraintWidgetArr2 = src.mListNextMatchConstraintsWidget;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.mNextChainWidget;
        ConstraintWidget[] constraintWidgetArr4 = src.mNextChainWidget;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget = src.mHorizontalNextWidget;
        this.mHorizontalNextWidget = constraintWidget == null ? null : map.get(constraintWidget);
        ConstraintWidget constraintWidget2 = src.mVerticalNextWidget;
        this.mVerticalNextWidget = constraintWidget2 != null ? map.get(constraintWidget2) : null;
    }

    public void updateFromRuns(boolean updateHorizontal, boolean updateVertical) {
        boolean updateHorizontal2 = updateHorizontal & this.horizontalRun.isResolved();
        boolean updateVertical2 = updateVertical & this.verticalRun.isResolved();
        int left = this.horizontalRun.start.value;
        int top = this.verticalRun.start.value;
        int right = this.horizontalRun.end.value;
        int bottom = this.verticalRun.end.value;
        int h = bottom - top;
        if (right - left < 0 || h < 0 || left == Integer.MIN_VALUE || left == Integer.MAX_VALUE || top == Integer.MIN_VALUE || top == Integer.MAX_VALUE || right == Integer.MIN_VALUE || right == Integer.MAX_VALUE || bottom == Integer.MIN_VALUE || bottom == Integer.MAX_VALUE) {
            left = 0;
            top = 0;
            right = 0;
            bottom = 0;
        }
        int w = right - left;
        int h2 = bottom - top;
        if (updateHorizontal2) {
            this.mX = left;
        }
        if (updateVertical2) {
            this.mY = top;
        }
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (updateHorizontal2) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && w < this.mWidth) {
                w = this.mWidth;
            }
            this.mWidth = w;
            int i = this.mWidth;
            int i2 = this.mMinWidth;
            if (i < i2) {
                this.mWidth = i2;
            }
        }
        if (updateVertical2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && h2 < this.mHeight) {
                h2 = this.mHeight;
            }
            this.mHeight = h2;
            int i3 = this.mHeight;
            int i4 = this.mMinHeight;
            if (i3 < i4) {
                this.mHeight = i4;
            }
        }
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer container, LinearSystem system, HashSet<ConstraintWidget> widgets, int orientation, boolean addSelf) {
        if (addSelf) {
            if (!widgets.contains(this)) {
                return;
            }
            Optimizer.checkMatchParent(container, system, this);
            widgets.remove(this);
            addToSolver(system, container.optimizeFor(64));
        }
        if (orientation == 0) {
            HashSet<ConstraintAnchor> dependents = this.mLeft.getDependents();
            if (dependents != null) {
                Iterator<ConstraintAnchor> it = dependents.iterator();
                while (it.hasNext()) {
                    ConstraintAnchor anchor = it.next();
                    anchor.mOwner.addChildrenToSolverByDependency(container, system, widgets, orientation, true);
                }
            }
            HashSet<ConstraintAnchor> dependents2 = this.mRight.getDependents();
            if (dependents2 != null) {
                Iterator<ConstraintAnchor> it2 = dependents2.iterator();
                while (it2.hasNext()) {
                    ConstraintAnchor anchor2 = it2.next();
                    anchor2.mOwner.addChildrenToSolverByDependency(container, system, widgets, orientation, true);
                }
                return;
            }
            return;
        }
        HashSet<ConstraintAnchor> dependents3 = this.mTop.getDependents();
        if (dependents3 != null) {
            Iterator<ConstraintAnchor> it3 = dependents3.iterator();
            while (it3.hasNext()) {
                ConstraintAnchor anchor3 = it3.next();
                anchor3.mOwner.addChildrenToSolverByDependency(container, system, widgets, orientation, true);
            }
        }
        HashSet<ConstraintAnchor> dependents4 = this.mBottom.getDependents();
        if (dependents4 != null) {
            Iterator<ConstraintAnchor> it4 = dependents4.iterator();
            while (it4.hasNext()) {
                ConstraintAnchor anchor4 = it4.next();
                anchor4.mOwner.addChildrenToSolverByDependency(container, system, widgets, orientation, true);
            }
        }
        HashSet<ConstraintAnchor> dependents5 = this.mBaseline.getDependents();
        if (dependents5 != null) {
            Iterator<ConstraintAnchor> it5 = dependents5.iterator();
            while (it5.hasNext()) {
                ConstraintAnchor anchor5 = it5.next();
                anchor5.mOwner.addChildrenToSolverByDependency(container, system, widgets, orientation, true);
            }
        }
    }
}
