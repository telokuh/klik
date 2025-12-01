package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.Optimizer;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer constraintWidgetContainer;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();
    private Measure mMeasure = new Measure();

    /* loaded from: classes.dex */
    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    /* loaded from: classes.dex */
    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer layout) {
        this.mVariableDimensionsWidgets.clear();
        int childCount = layout.mChildren.size();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget widget = layout.mChildren.get(i);
            if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                this.mVariableDimensionsWidgets.add(widget);
            }
        }
        layout.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.constraintWidgetContainer = constraintWidgetContainer;
    }

    private void measureChildren(ConstraintWidgetContainer layout) {
        int childCount = layout.mChildren.size();
        boolean optimize = layout.optimizeFor(64);
        Measurer measurer = layout.getMeasurer();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget child = layout.mChildren.get(i);
            if (!(child instanceof Guideline) && !(child instanceof Barrier) && !child.isInVirtualLayout() && (!optimize || child.horizontalRun == null || child.verticalRun == null || !child.horizontalRun.dimension.resolved || !child.verticalRun.dimension.resolved)) {
                boolean skip = false;
                ConstraintWidget.DimensionBehaviour widthBehavior = child.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour heightBehavior = child.getDimensionBehaviour(1);
                if (widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultWidth != 1 && heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultHeight != 1) {
                    skip = true;
                }
                if (!skip && layout.optimizeFor(1) && !(child instanceof VirtualLayout)) {
                    if (widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultWidth == 0 && heightBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !child.isInHorizontalChain()) {
                        skip = true;
                    }
                    if (heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && child.mMatchConstraintDefaultHeight == 0 && widthBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && !child.isInHorizontalChain()) {
                        skip = true;
                    }
                    if ((widthBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || heightBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && child.mDimensionRatio > 0.0f) {
                        skip = true;
                    }
                }
                if (!skip) {
                    measure(measurer, child, Measure.SELF_DIMENSIONS);
                    if (layout.mMetrics != null) {
                        layout.mMetrics.measuredWidgets++;
                    }
                }
            }
        }
        measurer.didMeasures();
    }

    private void solveLinearSystem(ConstraintWidgetContainer layout, String reason, int w, int h) {
        int minWidth = layout.getMinWidth();
        int minHeight = layout.getMinHeight();
        layout.setMinWidth(0);
        layout.setMinHeight(0);
        layout.setWidth(w);
        layout.setHeight(h);
        layout.setMinWidth(minWidth);
        layout.setMinHeight(minHeight);
        this.constraintWidgetContainer.layout();
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x009c, code lost:
        r18 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long solverMeasure(ConstraintWidgetContainer layout, int optimizationLevel, int paddingX, int paddingY, int widthMode, int widthSize, int heightMode, int heightSize, int lastMeasureWidth, int lastMeasureHeight) {
        boolean optimize;
        int heightSize2;
        int heightSize3;
        int widthSize2;
        int optimizations;
        long layoutTime;
        int startingWidth;
        int startingHeight;
        int sizeDependentWidgetsCount;
        boolean optimize2;
        int measureStrategy;
        int maxIterations;
        Measurer measurer;
        int j;
        int optimizations2;
        int startingWidth2;
        int needSolverPass;
        int startingHeight2;
        int preWidth;
        boolean allSolved;
        boolean allSolved2;
        Measurer measurer2 = layout.getMeasurer();
        long layoutTime2 = 0;
        int childCount = layout.mChildren.size();
        int startingWidth3 = layout.getWidth();
        int startingHeight3 = layout.getHeight();
        boolean optimizeWrap = Optimizer.enabled(optimizationLevel, 128);
        boolean optimize3 = optimizeWrap || Optimizer.enabled(optimizationLevel, 64);
        if (!optimize3) {
            optimize = optimize3;
        } else {
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    optimize = optimize3;
                    break;
                }
                ConstraintWidget child = layout.mChildren.get(i);
                boolean matchWidth = child.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean optimize4 = optimize3;
                boolean matchHeight = child.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean ratio = matchWidth && matchHeight && child.getDimensionRatio() > 0.0f;
                if (child.isInHorizontalChain() && ratio) {
                    optimize = false;
                    break;
                } else if (child.isInVerticalChain() && ratio) {
                    optimize = false;
                    break;
                } else {
                    boolean matchWidth2 = child instanceof VirtualLayout;
                    if (matchWidth2) {
                        optimize = false;
                        break;
                    }
                    if (child.isInHorizontalChain() || child.isInVerticalChain()) {
                        break;
                    }
                    i++;
                    optimize3 = optimize4;
                }
            }
        }
        if (optimize && LinearSystem.sMetrics != null) {
            LinearSystem.sMetrics.measures++;
        }
        boolean allSolved3 = false;
        boolean optimize5 = optimize & ((widthMode == 1073741824 && heightMode == 1073741824) || optimizeWrap);
        int computations = 0;
        if (!optimize5) {
            heightSize2 = widthSize;
            heightSize3 = heightSize;
            widthSize2 = 0;
        } else {
            int widthSize3 = Math.min(layout.getMaxWidth(), widthSize);
            int heightSize4 = Math.min(layout.getMaxHeight(), heightSize);
            if (widthMode == 1073741824 && layout.getWidth() != widthSize3) {
                layout.setWidth(widthSize3);
                layout.invalidateGraph();
            }
            if (heightMode == 1073741824 && layout.getHeight() != heightSize4) {
                layout.setHeight(heightSize4);
                layout.invalidateGraph();
            }
            if (widthMode == 1073741824 && heightMode == 1073741824) {
                allSolved = layout.directMeasure(optimizeWrap);
                computations = 2;
            } else {
                allSolved = layout.directMeasureSetup(optimizeWrap);
                if (widthMode == 1073741824) {
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 0);
                    computations = 0 + 1;
                }
                if (heightMode == 1073741824) {
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 1);
                    computations++;
                }
            }
            if (!allSolved) {
                allSolved2 = allSolved;
            } else {
                allSolved2 = allSolved;
                boolean allSolved4 = widthMode == 1073741824;
                layout.updateFromRuns(allSolved4, heightMode == 1073741824);
            }
            allSolved3 = allSolved2;
            heightSize3 = heightSize4;
            heightSize2 = widthSize3;
            widthSize2 = computations;
        }
        if (allSolved3 && widthSize2 == 2) {
            return 0L;
        }
        int optimizations3 = layout.getOptimizationLevel();
        if (childCount > 0) {
            measureChildren(layout);
        }
        updateHierarchy(layout);
        int sizeDependentWidgetsCount2 = this.mVariableDimensionsWidgets.size();
        if (childCount > 0) {
            solveLinearSystem(layout, "First pass", startingWidth3, startingHeight3);
        }
        if (sizeDependentWidgetsCount2 <= 0) {
            optimizations = optimizations3;
            layoutTime = 0;
        } else {
            boolean containerWrapWidth = layout.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean containerWrapHeight = layout.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            int minWidth = Math.max(layout.getWidth(), this.constraintWidgetContainer.getMinWidth());
            int minHeight = Math.max(layout.getHeight(), this.constraintWidgetContainer.getMinHeight());
            int i2 = 0;
            int minHeight2 = 0;
            int widthSize4 = minWidth;
            int heightSize5 = minHeight;
            while (i2 < sizeDependentWidgetsCount2) {
                long layoutTime3 = layoutTime2;
                ConstraintWidget widget = this.mVariableDimensionsWidgets.get(i2);
                if (!(widget instanceof VirtualLayout)) {
                    optimizations2 = optimizations3;
                    startingWidth2 = startingWidth3;
                    startingHeight2 = startingHeight3;
                } else {
                    int preWidth2 = widget.getWidth();
                    optimizations2 = optimizations3;
                    int preHeight = widget.getHeight();
                    startingWidth2 = startingWidth3;
                    int needSolverPass2 = measure(measurer2, widget, Measure.TRY_GIVEN_DIMENSIONS) | minHeight2;
                    if (layout.mMetrics == null) {
                        needSolverPass = needSolverPass2;
                        startingHeight2 = startingHeight3;
                    } else {
                        needSolverPass = needSolverPass2;
                        startingHeight2 = startingHeight3;
                        layout.mMetrics.measuredMatchWidgets++;
                    }
                    int measuredWidth = widget.getWidth();
                    int measuredHeight = widget.getHeight();
                    if (measuredWidth != preWidth2) {
                        widget.setWidth(measuredWidth);
                        if (containerWrapWidth && widget.getRight() > widthSize4) {
                            int w = widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                            widthSize4 = Math.max(widthSize4, w);
                        }
                        preWidth = 1;
                    } else {
                        preWidth = needSolverPass;
                    }
                    if (measuredHeight != preHeight) {
                        widget.setHeight(measuredHeight);
                        if (containerWrapHeight && widget.getBottom() > heightSize5) {
                            int h = widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                            heightSize5 = Math.max(heightSize5, h);
                        }
                        preWidth = 1;
                    }
                    VirtualLayout virtualLayout = (VirtualLayout) widget;
                    int needSolverPass3 = preWidth | virtualLayout.needSolverPass();
                    minHeight2 = needSolverPass3;
                }
                i2++;
                layoutTime2 = layoutTime3;
                optimizations3 = optimizations2;
                startingWidth3 = startingWidth2;
                startingHeight3 = startingHeight2;
            }
            optimizations = optimizations3;
            layoutTime = layoutTime2;
            int startingWidth4 = startingWidth3;
            int startingHeight4 = startingHeight3;
            int maxIterations2 = 2;
            int j2 = 0;
            while (true) {
                if (j2 >= maxIterations2) {
                    startingWidth = startingWidth4;
                    startingHeight = startingHeight4;
                    break;
                }
                int i3 = 0;
                while (i3 < sizeDependentWidgetsCount2) {
                    ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i3);
                    if (((widget2 instanceof Helper) && !(widget2 instanceof VirtualLayout)) || (widget2 instanceof Guideline) || widget2.getVisibility() == 8 || ((optimize5 && widget2.horizontalRun.dimension.resolved && widget2.verticalRun.dimension.resolved) || (widget2 instanceof VirtualLayout))) {
                        maxIterations = maxIterations2;
                        sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                        measurer = measurer2;
                        j = j2;
                        optimize2 = optimize5;
                    } else {
                        int preWidth3 = widget2.getWidth();
                        int preHeight2 = widget2.getHeight();
                        sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                        int preBaselineDistance = widget2.getBaselineDistance();
                        int measureStrategy2 = Measure.TRY_GIVEN_DIMENSIONS;
                        optimize2 = optimize5;
                        if (j2 != maxIterations2 - 1) {
                            measureStrategy = measureStrategy2;
                        } else {
                            int measureStrategy3 = Measure.USE_GIVEN_DIMENSIONS;
                            measureStrategy = measureStrategy3;
                        }
                        boolean hasMeasure = measure(measurer2, widget2, measureStrategy);
                        minHeight2 |= hasMeasure;
                        maxIterations = maxIterations2;
                        if (layout.mMetrics == null) {
                            measurer = measurer2;
                            j = j2;
                        } else {
                            measurer = measurer2;
                            j = j2;
                            layout.mMetrics.measuredMatchWidgets++;
                        }
                        int measuredWidth2 = widget2.getWidth();
                        int measuredHeight2 = widget2.getHeight();
                        if (measuredWidth2 != preWidth3) {
                            widget2.setWidth(measuredWidth2);
                            if (containerWrapWidth && widget2.getRight() > widthSize4) {
                                int w2 = widget2.getRight() + widget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                widthSize4 = Math.max(widthSize4, w2);
                            }
                            minHeight2 = 1;
                        }
                        if (measuredHeight2 != preHeight2) {
                            widget2.setHeight(measuredHeight2);
                            if (containerWrapHeight && widget2.getBottom() > heightSize5) {
                                int h2 = widget2.getBottom() + widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                heightSize5 = Math.max(heightSize5, h2);
                            }
                            minHeight2 = 1;
                        }
                        if (widget2.hasBaseline() && preBaselineDistance != widget2.getBaselineDistance()) {
                            minHeight2 = 1;
                        }
                    }
                    i3++;
                    sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                    optimize5 = optimize2;
                    maxIterations2 = maxIterations;
                    measurer2 = measurer;
                    j2 = j;
                }
                int maxIterations3 = maxIterations2;
                int sizeDependentWidgetsCount3 = sizeDependentWidgetsCount2;
                Measurer measurer3 = measurer2;
                int j3 = j2;
                boolean optimize6 = optimize5;
                if (minHeight2 != 0) {
                    solveLinearSystem(layout, "intermediate pass", startingWidth4, startingHeight4);
                    minHeight2 = 0;
                    j2 = j3 + 1;
                    sizeDependentWidgetsCount2 = sizeDependentWidgetsCount3;
                    optimize5 = optimize6;
                    maxIterations2 = maxIterations3;
                    measurer2 = measurer3;
                } else {
                    startingWidth = startingWidth4;
                    startingHeight = startingHeight4;
                    break;
                }
            }
            if (minHeight2 != 0) {
                solveLinearSystem(layout, "2nd pass", startingWidth, startingHeight);
                boolean needSolverPass4 = false;
                if (layout.getWidth() < widthSize4) {
                    layout.setWidth(widthSize4);
                    needSolverPass4 = true;
                }
                if (layout.getHeight() < heightSize5) {
                    layout.setHeight(heightSize5);
                    needSolverPass4 = true;
                }
                if (needSolverPass4) {
                    solveLinearSystem(layout, "3rd pass", startingWidth, startingHeight);
                }
            }
        }
        layout.setOptimizationLevel(optimizations);
        return layoutTime;
    }

    private boolean measure(Measurer measurer, ConstraintWidget widget, int measureStrategy) {
        this.mMeasure.horizontalBehavior = widget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = widget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = widget.getWidth();
        this.mMeasure.verticalDimension = widget.getHeight();
        Measure measure = this.mMeasure;
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = measureStrategy;
        boolean horizontalMatchConstraints = measure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalMatchConstraints = this.mMeasure.verticalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean horizontalUseRatio = horizontalMatchConstraints && widget.mDimensionRatio > 0.0f;
        boolean verticalUseRatio = verticalMatchConstraints && widget.mDimensionRatio > 0.0f;
        if (horizontalUseRatio && widget.mResolvedMatchConstraintDefault[0] == 4) {
            this.mMeasure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (verticalUseRatio && widget.mResolvedMatchConstraintDefault[1] == 4) {
            this.mMeasure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(widget, this.mMeasure);
        widget.setWidth(this.mMeasure.measuredWidth);
        widget.setHeight(this.mMeasure.measuredHeight);
        widget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        widget.setBaselineDistance(this.mMeasure.measuredBaseline);
        this.mMeasure.measureStrategy = Measure.SELF_DIMENSIONS;
        return this.mMeasure.measuredNeedsSolverPass;
    }
}
