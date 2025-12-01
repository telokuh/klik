package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.b.b.BuildConfig;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;
/* loaded from: classes.dex */
public class TextInputLayout extends LinearLayout {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_TextInputLayout;
    public static final int END_ICON_CLEAR_TEXT = 2;
    public static final int END_ICON_CUSTOM = -1;
    public static final int END_ICON_DROPDOWN_MENU = 3;
    public static final int END_ICON_NONE = 0;
    public static final int END_ICON_PASSWORD_TOGGLE = 1;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
    private static final String LOG_TAG = "TextInputLayout";
    private ValueAnimator animator;
    private MaterialShapeDrawable boxBackground;
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private final int boxCollapsedPaddingTopPx;
    private final int boxLabelCutoutPaddingPx;
    private int boxStrokeColor;
    private int boxStrokeWidthDefaultPx;
    private int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    private MaterialShapeDrawable boxUnderline;
    final CollapsingTextHelper collapsingTextHelper;
    boolean counterEnabled;
    private int counterMaxLength;
    private int counterOverflowTextAppearance;
    private ColorStateList counterOverflowTextColor;
    private boolean counterOverflowed;
    private int counterTextAppearance;
    private ColorStateList counterTextColor;
    private TextView counterView;
    private int defaultFilledBackgroundColor;
    private ColorStateList defaultHintTextColor;
    private int defaultStrokeColor;
    private int disabledColor;
    private int disabledFilledBackgroundColor;
    EditText editText;
    private final LinkedHashSet<OnEditTextAttachedListener> editTextAttachedListeners;
    private Drawable endDummyDrawable;
    private int endDummyDrawableWidth;
    private final LinkedHashSet<OnEndIconChangedListener> endIconChangedListeners;
    private final SparseArray<EndIconDelegate> endIconDelegates;
    private final FrameLayout endIconFrame;
    private int endIconMode;
    private View.OnLongClickListener endIconOnLongClickListener;
    private ColorStateList endIconTintList;
    private PorterDuff.Mode endIconTintMode;
    private final CheckableImageButton endIconView;
    private final LinearLayout endLayout;
    private View.OnLongClickListener errorIconOnLongClickListener;
    private ColorStateList errorIconTintList;
    private final CheckableImageButton errorIconView;
    private int focusedFilledBackgroundColor;
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean hasEndIconTintList;
    private boolean hasEndIconTintMode;
    private boolean hasStartIconTintList;
    private boolean hasStartIconTintMode;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;
    private int hoveredFilledBackgroundColor;
    private int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private Drawable originalEditTextEndDrawable;
    private CharSequence originalHint;
    private boolean placeholderEnabled;
    private CharSequence placeholderText;
    private int placeholderTextAppearance;
    private ColorStateList placeholderTextColor;
    private TextView placeholderTextView;
    private CharSequence prefixText;
    private final TextView prefixTextView;
    private boolean restoringSavedState;
    private ShapeAppearanceModel shapeAppearanceModel;
    private Drawable startDummyDrawable;
    private int startDummyDrawableWidth;
    private View.OnLongClickListener startIconOnLongClickListener;
    private ColorStateList startIconTintList;
    private PorterDuff.Mode startIconTintMode;
    private final CheckableImageButton startIconView;
    private final LinearLayout startLayout;
    private ColorStateList strokeErrorColor;
    private CharSequence suffixText;
    private final TextView suffixTextView;
    private final Rect tmpBoundsRect;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BoxBackgroundMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EndIconMode {
    }

    /* loaded from: classes.dex */
    public interface OnEditTextAttachedListener {
        void onEditTextAttached(TextInputLayout textInputLayout);
    }

    /* loaded from: classes.dex */
    public interface OnEndIconChangedListener {
        void onEndIconChanged(TextInputLayout textInputLayout, int i);
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public TextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.textInputStyle);
    }

    public TextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, DEF_STYLE_RES), attrs, defStyleAttr);
        this.indicatorViewController = new IndicatorViewController(this);
        this.tmpRect = new Rect();
        this.tmpBoundsRect = new Rect();
        this.tmpRectF = new RectF();
        this.editTextAttachedListeners = new LinkedHashSet<>();
        this.endIconMode = 0;
        this.endIconDelegates = new SparseArray<>();
        this.endIconChangedListeners = new LinkedHashSet<>();
        this.collapsingTextHelper = new CollapsingTextHelper(this);
        Context context2 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        this.inputFrame = new FrameLayout(context2);
        this.inputFrame.setAddStatesFromChildren(true);
        addView(this.inputFrame);
        this.startLayout = new LinearLayout(context2);
        this.startLayout.setOrientation(0);
        this.startLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, GravityCompat.START));
        this.inputFrame.addView(this.startLayout);
        this.endLayout = new LinearLayout(context2);
        this.endLayout.setOrientation(0);
        this.endLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, GravityCompat.END));
        this.inputFrame.addView(this.endLayout);
        this.endIconFrame = new FrameLayout(context2);
        this.endIconFrame.setLayoutParams(new FrameLayout.LayoutParams(-2, -1));
        this.collapsingTextHelper.setTextSizeInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        this.collapsingTextHelper.setPositionInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        this.collapsingTextHelper.setCollapsedTextGravity(BadgeDrawable.TOP_START);
        TintTypedArray a = ThemeEnforcement.obtainTintedStyledAttributes(context2, attrs, R.styleable.TextInputLayout, defStyleAttr, DEF_STYLE_RES, R.styleable.TextInputLayout_counterTextAppearance, R.styleable.TextInputLayout_counterOverflowTextAppearance, R.styleable.TextInputLayout_errorTextAppearance, R.styleable.TextInputLayout_helperTextTextAppearance, R.styleable.TextInputLayout_hintTextAppearance);
        this.hintEnabled = a.getBoolean(R.styleable.TextInputLayout_hintEnabled, true);
        setHint(a.getText(R.styleable.TextInputLayout_android_hint));
        this.hintAnimationEnabled = a.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
        this.shapeAppearanceModel = ShapeAppearanceModel.builder(context2, attrs, defStyleAttr, DEF_STYLE_RES).build();
        this.boxLabelCutoutPaddingPx = context2.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_label_cutout_padding);
        this.boxCollapsedPaddingTopPx = a.getDimensionPixelOffset(R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.boxStrokeWidthDefaultPx = a.getDimensionPixelSize(R.styleable.TextInputLayout_boxStrokeWidth, context2.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_default));
        this.boxStrokeWidthFocusedPx = a.getDimensionPixelSize(R.styleable.TextInputLayout_boxStrokeWidthFocused, context2.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_focused));
        this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
        float boxCornerRadiusTopStart = a.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float boxCornerRadiusTopEnd = a.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float boxCornerRadiusBottomEnd = a.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float boxCornerRadiusBottomStart = a.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        ShapeAppearanceModel.Builder shapeBuilder = this.shapeAppearanceModel.toBuilder();
        if (boxCornerRadiusTopStart >= 0.0f) {
            shapeBuilder.setTopLeftCornerSize(boxCornerRadiusTopStart);
        }
        if (boxCornerRadiusTopEnd >= 0.0f) {
            shapeBuilder.setTopRightCornerSize(boxCornerRadiusTopEnd);
        }
        if (boxCornerRadiusBottomEnd >= 0.0f) {
            shapeBuilder.setBottomRightCornerSize(boxCornerRadiusBottomEnd);
        }
        if (boxCornerRadiusBottomStart >= 0.0f) {
            shapeBuilder.setBottomLeftCornerSize(boxCornerRadiusBottomStart);
        }
        this.shapeAppearanceModel = shapeBuilder.build();
        ColorStateList filledBackgroundColorStateList = MaterialResources.getColorStateList(context2, a, R.styleable.TextInputLayout_boxBackgroundColor);
        if (filledBackgroundColorStateList != null) {
            this.defaultFilledBackgroundColor = filledBackgroundColorStateList.getDefaultColor();
            this.boxBackgroundColor = this.defaultFilledBackgroundColor;
            if (filledBackgroundColorStateList.isStateful()) {
                this.disabledFilledBackgroundColor = filledBackgroundColorStateList.getColorForState(new int[]{-16842910}, -1);
                this.focusedFilledBackgroundColor = filledBackgroundColorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
                this.hoveredFilledBackgroundColor = filledBackgroundColorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
            } else {
                this.focusedFilledBackgroundColor = this.defaultFilledBackgroundColor;
                ColorStateList mtrlFilledBackgroundColorStateList = AppCompatResources.getColorStateList(context2, R.color.mtrl_filled_background_color);
                this.disabledFilledBackgroundColor = mtrlFilledBackgroundColorStateList.getColorForState(new int[]{-16842910}, -1);
                this.hoveredFilledBackgroundColor = mtrlFilledBackgroundColorStateList.getColorForState(new int[]{16843623}, -1);
            }
        } else {
            this.boxBackgroundColor = 0;
            this.defaultFilledBackgroundColor = 0;
            this.disabledFilledBackgroundColor = 0;
            this.focusedFilledBackgroundColor = 0;
            this.hoveredFilledBackgroundColor = 0;
        }
        if (a.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
            ColorStateList colorStateList = a.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
            this.focusedTextColor = colorStateList;
            this.defaultHintTextColor = colorStateList;
        }
        ColorStateList boxStrokeColorStateList = MaterialResources.getColorStateList(context2, a, R.styleable.TextInputLayout_boxStrokeColor);
        this.focusedStrokeColor = a.getColor(R.styleable.TextInputLayout_boxStrokeColor, 0);
        this.defaultStrokeColor = ContextCompat.getColor(context2, R.color.mtrl_textinput_default_box_stroke_color);
        this.disabledColor = ContextCompat.getColor(context2, R.color.mtrl_textinput_disabled_color);
        this.hoveredStrokeColor = ContextCompat.getColor(context2, R.color.mtrl_textinput_hovered_box_stroke_color);
        if (boxStrokeColorStateList != null) {
            setBoxStrokeColorStateList(boxStrokeColorStateList);
        }
        if (a.hasValue(R.styleable.TextInputLayout_boxStrokeErrorColor)) {
            setBoxStrokeErrorColor(MaterialResources.getColorStateList(context2, a, R.styleable.TextInputLayout_boxStrokeErrorColor));
        }
        int hintAppearance = a.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1);
        if (hintAppearance != -1) {
            setHintTextAppearance(a.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
        }
        int errorTextAppearance = a.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
        CharSequence errorContentDescription = a.getText(R.styleable.TextInputLayout_errorContentDescription);
        boolean errorEnabled = a.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
        this.errorIconView = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_end_icon, (ViewGroup) this.endLayout, false);
        this.errorIconView.setVisibility(8);
        if (a.hasValue(R.styleable.TextInputLayout_errorIconDrawable)) {
            setErrorIconDrawable(a.getDrawable(R.styleable.TextInputLayout_errorIconDrawable));
        }
        if (a.hasValue(R.styleable.TextInputLayout_errorIconTint)) {
            setErrorIconTintList(MaterialResources.getColorStateList(context2, a, R.styleable.TextInputLayout_errorIconTint));
        }
        if (a.hasValue(R.styleable.TextInputLayout_errorIconTintMode)) {
            setErrorIconTintMode(ViewUtils.parseTintMode(a.getInt(R.styleable.TextInputLayout_errorIconTintMode, -1), null));
        }
        this.errorIconView.setContentDescription(getResources().getText(R.string.error_icon_content_description));
        ViewCompat.setImportantForAccessibility(this.errorIconView, 2);
        this.errorIconView.setClickable(false);
        this.errorIconView.setPressable(false);
        this.errorIconView.setFocusable(false);
        int helperTextTextAppearance = a.getResourceId(R.styleable.TextInputLayout_helperTextTextAppearance, 0);
        boolean helperTextEnabled = a.getBoolean(R.styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence helperText = a.getText(R.styleable.TextInputLayout_helperText);
        int placeholderTextAppearance = a.getResourceId(R.styleable.TextInputLayout_placeholderTextAppearance, 0);
        CharSequence placeholderText = a.getText(R.styleable.TextInputLayout_placeholderText);
        int prefixTextAppearance = a.getResourceId(R.styleable.TextInputLayout_prefixTextAppearance, 0);
        CharSequence prefixText = a.getText(R.styleable.TextInputLayout_prefixText);
        int suffixTextAppearance = a.getResourceId(R.styleable.TextInputLayout_suffixTextAppearance, 0);
        CharSequence suffixText = a.getText(R.styleable.TextInputLayout_suffixText);
        int hintAppearance2 = R.styleable.TextInputLayout_counterEnabled;
        boolean counterEnabled = a.getBoolean(hintAppearance2, false);
        setCounterMaxLength(a.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
        this.counterTextAppearance = a.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.counterOverflowTextAppearance = a.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        this.startIconView = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_start_icon, (ViewGroup) this.startLayout, false);
        this.startIconView.setVisibility(8);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        if (a.hasValue(R.styleable.TextInputLayout_startIconDrawable)) {
            setStartIconDrawable(a.getDrawable(R.styleable.TextInputLayout_startIconDrawable));
            if (a.hasValue(R.styleable.TextInputLayout_startIconContentDescription)) {
                setStartIconContentDescription(a.getText(R.styleable.TextInputLayout_startIconContentDescription));
            }
            setStartIconCheckable(a.getBoolean(R.styleable.TextInputLayout_startIconCheckable, true));
        }
        if (a.hasValue(R.styleable.TextInputLayout_startIconTint)) {
            setStartIconTintList(MaterialResources.getColorStateList(context2, a, R.styleable.TextInputLayout_startIconTint));
        }
        if (a.hasValue(R.styleable.TextInputLayout_startIconTintMode)) {
            setStartIconTintMode(ViewUtils.parseTintMode(a.getInt(R.styleable.TextInputLayout_startIconTintMode, -1), null));
        }
        setBoxBackgroundMode(a.getInt(R.styleable.TextInputLayout_boxBackgroundMode, 0));
        this.endIconView = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_end_icon, (ViewGroup) this.endIconFrame, false);
        this.endIconFrame.addView(this.endIconView);
        this.endIconView.setVisibility(8);
        this.endIconDelegates.append(-1, new CustomEndIconDelegate(this));
        this.endIconDelegates.append(0, new NoEndIconDelegate(this));
        this.endIconDelegates.append(1, new PasswordToggleEndIconDelegate(this));
        this.endIconDelegates.append(2, new ClearTextEndIconDelegate(this));
        this.endIconDelegates.append(3, new DropdownMenuEndIconDelegate(this));
        if (a.hasValue(R.styleable.TextInputLayout_endIconMode)) {
            setEndIconMode(a.getInt(R.styleable.TextInputLayout_endIconMode, 0));
            if (a.hasValue(R.styleable.TextInputLayout_endIconDrawable)) {
                setEndIconDrawable(a.getDrawable(R.styleable.TextInputLayout_endIconDrawable));
            }
            if (a.hasValue(R.styleable.TextInputLayout_endIconContentDescription)) {
                setEndIconContentDescription(a.getText(R.styleable.TextInputLayout_endIconContentDescription));
            }
            setEndIconCheckable(a.getBoolean(R.styleable.TextInputLayout_endIconCheckable, true));
        } else if (a.hasValue(R.styleable.TextInputLayout_passwordToggleEnabled)) {
            setEndIconMode(a.getBoolean(R.styleable.TextInputLayout_passwordToggleEnabled, false) ? 1 : 0);
            setEndIconDrawable(a.getDrawable(R.styleable.TextInputLayout_passwordToggleDrawable));
            setEndIconContentDescription(a.getText(R.styleable.TextInputLayout_passwordToggleContentDescription));
            if (a.hasValue(R.styleable.TextInputLayout_passwordToggleTint)) {
                setEndIconTintList(MaterialResources.getColorStateList(context2, a, R.styleable.TextInputLayout_passwordToggleTint));
            }
            if (a.hasValue(R.styleable.TextInputLayout_passwordToggleTintMode)) {
                setEndIconTintMode(ViewUtils.parseTintMode(a.getInt(R.styleable.TextInputLayout_passwordToggleTintMode, -1), null));
            }
        }
        if (!a.hasValue(R.styleable.TextInputLayout_passwordToggleEnabled)) {
            if (a.hasValue(R.styleable.TextInputLayout_endIconTint)) {
                setEndIconTintList(MaterialResources.getColorStateList(context2, a, R.styleable.TextInputLayout_endIconTint));
            }
            if (a.hasValue(R.styleable.TextInputLayout_endIconTintMode)) {
                setEndIconTintMode(ViewUtils.parseTintMode(a.getInt(R.styleable.TextInputLayout_endIconTintMode, -1), null));
            }
        }
        this.prefixTextView = new AppCompatTextView(context2);
        this.prefixTextView.setId(R.id.textinput_prefix_text);
        this.prefixTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        ViewCompat.setAccessibilityLiveRegion(this.prefixTextView, 1);
        this.startLayout.addView(this.startIconView);
        this.startLayout.addView(this.prefixTextView);
        this.suffixTextView = new AppCompatTextView(context2);
        this.suffixTextView.setId(R.id.textinput_suffix_text);
        this.suffixTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 80));
        ViewCompat.setAccessibilityLiveRegion(this.suffixTextView, 1);
        this.endLayout.addView(this.suffixTextView);
        this.endLayout.addView(this.errorIconView);
        this.endLayout.addView(this.endIconFrame);
        setHelperTextEnabled(helperTextEnabled);
        setHelperText(helperText);
        setHelperTextTextAppearance(helperTextTextAppearance);
        setErrorEnabled(errorEnabled);
        setErrorTextAppearance(errorTextAppearance);
        setErrorContentDescription(errorContentDescription);
        setCounterTextAppearance(this.counterTextAppearance);
        setCounterOverflowTextAppearance(this.counterOverflowTextAppearance);
        setPlaceholderText(placeholderText);
        setPlaceholderTextAppearance(placeholderTextAppearance);
        setPrefixText(prefixText);
        setPrefixTextAppearance(prefixTextAppearance);
        setSuffixText(suffixText);
        setSuffixTextAppearance(suffixTextAppearance);
        if (a.hasValue(R.styleable.TextInputLayout_errorTextColor)) {
            setErrorTextColor(a.getColorStateList(R.styleable.TextInputLayout_errorTextColor));
        }
        if (a.hasValue(R.styleable.TextInputLayout_helperTextTextColor)) {
            setHelperTextColor(a.getColorStateList(R.styleable.TextInputLayout_helperTextTextColor));
        }
        if (a.hasValue(R.styleable.TextInputLayout_hintTextColor)) {
            setHintTextColor(a.getColorStateList(R.styleable.TextInputLayout_hintTextColor));
        }
        if (a.hasValue(R.styleable.TextInputLayout_counterTextColor)) {
            setCounterTextColor(a.getColorStateList(R.styleable.TextInputLayout_counterTextColor));
        }
        if (a.hasValue(R.styleable.TextInputLayout_counterOverflowTextColor)) {
            setCounterOverflowTextColor(a.getColorStateList(R.styleable.TextInputLayout_counterOverflowTextColor));
        }
        if (a.hasValue(R.styleable.TextInputLayout_placeholderTextColor)) {
            setPlaceholderTextColor(a.getColorStateList(R.styleable.TextInputLayout_placeholderTextColor));
        }
        if (a.hasValue(R.styleable.TextInputLayout_prefixTextColor)) {
            setPrefixTextColor(a.getColorStateList(R.styleable.TextInputLayout_prefixTextColor));
        }
        if (a.hasValue(R.styleable.TextInputLayout_suffixTextColor)) {
            setSuffixTextColor(a.getColorStateList(R.styleable.TextInputLayout_suffixTextColor));
        }
        setCounterEnabled(counterEnabled);
        setEnabled(a.getBoolean(R.styleable.TextInputLayout_android_enabled, true));
        a.recycle();
        ViewCompat.setImportantForAccessibility(this, 2);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof EditText) {
            FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(params);
            flp.gravity = (flp.gravity & (-113)) | 16;
            this.inputFrame.addView(child, flp);
            this.inputFrame.setLayoutParams(params);
            updateInputLayoutMargins();
            setEditText((EditText) child);
            return;
        }
        super.addView(child, index, params);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MaterialShapeDrawable getBoxBackground() {
        int i = this.boxBackgroundMode;
        if (i == 1 || i == 2) {
            return this.boxBackground;
        }
        throw new IllegalStateException();
    }

    public void setBoxBackgroundMode(int boxBackgroundMode) {
        if (boxBackgroundMode == this.boxBackgroundMode) {
            return;
        }
        this.boxBackgroundMode = boxBackgroundMode;
        if (this.editText != null) {
            onApplyBoxBackgroundMode();
        }
    }

    public int getBoxBackgroundMode() {
        return this.boxBackgroundMode;
    }

    private void onApplyBoxBackgroundMode() {
        assignBoxBackgroundByMode();
        setEditTextBoxBackground();
        updateTextInputBoxState();
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
    }

    private void assignBoxBackgroundByMode() {
        int i = this.boxBackgroundMode;
        if (i == 0) {
            this.boxBackground = null;
            this.boxUnderline = null;
        } else if (i == 1) {
            this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.boxUnderline = new MaterialShapeDrawable();
        } else if (i == 2) {
            if (this.hintEnabled && !(this.boxBackground instanceof CutoutDrawable)) {
                this.boxBackground = new CutoutDrawable(this.shapeAppearanceModel);
            } else {
                this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            }
            this.boxUnderline = null;
        } else {
            throw new IllegalArgumentException(this.boxBackgroundMode + " is illegal; only @BoxBackgroundMode constants are supported.");
        }
    }

    private void setEditTextBoxBackground() {
        if (shouldUseEditTextBackgroundForBoxBackground()) {
            ViewCompat.setBackground(this.editText, this.boxBackground);
        }
    }

    private boolean shouldUseEditTextBackgroundForBoxBackground() {
        EditText editText = this.editText;
        return (editText == null || this.boxBackground == null || editText.getBackground() != null || this.boxBackgroundMode == 0) ? false : true;
    }

    public void setBoxStrokeWidthResource(int boxStrokeWidthResId) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(boxStrokeWidthResId));
    }

    public void setBoxStrokeWidth(int boxStrokeWidth) {
        this.boxStrokeWidthDefaultPx = boxStrokeWidth;
        updateTextInputBoxState();
    }

    public int getBoxStrokeWidth() {
        return this.boxStrokeWidthDefaultPx;
    }

    public void setBoxStrokeWidthFocusedResource(int boxStrokeWidthFocusedResId) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(boxStrokeWidthFocusedResId));
    }

    public void setBoxStrokeWidthFocused(int boxStrokeWidthFocused) {
        this.boxStrokeWidthFocusedPx = boxStrokeWidthFocused;
        updateTextInputBoxState();
    }

    public int getBoxStrokeWidthFocused() {
        return this.boxStrokeWidthFocusedPx;
    }

    public void setBoxStrokeColor(int boxStrokeColor) {
        if (this.focusedStrokeColor != boxStrokeColor) {
            this.focusedStrokeColor = boxStrokeColor;
            updateTextInputBoxState();
        }
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    public void setBoxStrokeColorStateList(ColorStateList boxStrokeColorStateList) {
        if (boxStrokeColorStateList.isStateful()) {
            this.defaultStrokeColor = boxStrokeColorStateList.getDefaultColor();
            this.disabledColor = boxStrokeColorStateList.getColorForState(new int[]{-16842910}, -1);
            this.hoveredStrokeColor = boxStrokeColorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
            this.focusedStrokeColor = boxStrokeColorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        } else if (this.focusedStrokeColor != boxStrokeColorStateList.getDefaultColor()) {
            this.focusedStrokeColor = boxStrokeColorStateList.getDefaultColor();
        }
        updateTextInputBoxState();
    }

    public void setBoxStrokeErrorColor(ColorStateList strokeErrorColor) {
        if (this.strokeErrorColor != strokeErrorColor) {
            this.strokeErrorColor = strokeErrorColor;
            updateTextInputBoxState();
        }
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.strokeErrorColor;
    }

    public void setBoxBackgroundColorResource(int boxBackgroundColorId) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), boxBackgroundColorId));
    }

    public void setBoxBackgroundColor(int boxBackgroundColor) {
        if (this.boxBackgroundColor != boxBackgroundColor) {
            this.boxBackgroundColor = boxBackgroundColor;
            this.defaultFilledBackgroundColor = boxBackgroundColor;
            this.focusedFilledBackgroundColor = boxBackgroundColor;
            this.hoveredFilledBackgroundColor = boxBackgroundColor;
            applyBoxAttributes();
        }
    }

    public void setBoxBackgroundColorStateList(ColorStateList boxBackgroundColorStateList) {
        this.defaultFilledBackgroundColor = boxBackgroundColorStateList.getDefaultColor();
        this.boxBackgroundColor = this.defaultFilledBackgroundColor;
        this.disabledFilledBackgroundColor = boxBackgroundColorStateList.getColorForState(new int[]{-16842910}, -1);
        this.focusedFilledBackgroundColor = boxBackgroundColorStateList.getColorForState(new int[]{16842908, 16842910}, -1);
        this.hoveredFilledBackgroundColor = boxBackgroundColorStateList.getColorForState(new int[]{16843623, 16842910}, -1);
        applyBoxAttributes();
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public void setBoxCornerRadiiResources(int boxCornerRadiusTopStartId, int boxCornerRadiusTopEndId, int boxCornerRadiusBottomEndId, int boxCornerRadiusBottomStartId) {
        setBoxCornerRadii(getContext().getResources().getDimension(boxCornerRadiusTopStartId), getContext().getResources().getDimension(boxCornerRadiusTopEndId), getContext().getResources().getDimension(boxCornerRadiusBottomStartId), getContext().getResources().getDimension(boxCornerRadiusBottomEndId));
    }

    public void setBoxCornerRadii(float boxCornerRadiusTopStart, float boxCornerRadiusTopEnd, float boxCornerRadiusBottomStart, float boxCornerRadiusBottomEnd) {
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        if (materialShapeDrawable == null || materialShapeDrawable.getTopLeftCornerResolvedSize() != boxCornerRadiusTopStart || this.boxBackground.getTopRightCornerResolvedSize() != boxCornerRadiusTopEnd || this.boxBackground.getBottomRightCornerResolvedSize() != boxCornerRadiusBottomEnd || this.boxBackground.getBottomLeftCornerResolvedSize() != boxCornerRadiusBottomStart) {
            this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder().setTopLeftCornerSize(boxCornerRadiusTopStart).setTopRightCornerSize(boxCornerRadiusTopEnd).setBottomRightCornerSize(boxCornerRadiusBottomEnd).setBottomLeftCornerSize(boxCornerRadiusBottomStart).build();
            applyBoxAttributes();
        }
    }

    public float getBoxCornerRadiusTopStart() {
        return this.boxBackground.getTopLeftCornerResolvedSize();
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.boxBackground.getTopRightCornerResolvedSize();
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.boxBackground.getBottomLeftCornerResolvedSize();
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.boxBackground.getBottomRightCornerResolvedSize();
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.typeface) {
            this.typeface = typeface;
            this.collapsingTextHelper.setTypefaces(typeface);
            this.indicatorViewController.setTypefaces(typeface);
            TextView textView = this.counterView;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure structure, int flags) {
        EditText editText;
        if (this.originalHint == null || (editText = this.editText) == null) {
            super.dispatchProvideAutofillStructure(structure, flags);
            return;
        }
        boolean wasProvidingHint = this.isProvidingHint;
        this.isProvidingHint = false;
        CharSequence hint = editText.getHint();
        this.editText.setHint(this.originalHint);
        try {
            super.dispatchProvideAutofillStructure(structure, flags);
        } finally {
            this.editText.setHint(hint);
            this.isProvidingHint = wasProvidingHint;
        }
    }

    private void setEditText(EditText editText) {
        if (this.editText != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (this.endIconMode != 3 && !(editText instanceof TextInputEditText)) {
            Log.i(LOG_TAG, "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.editText = editText;
        onApplyBoxBackgroundMode();
        setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
        this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
        this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
        int editTextGravity = this.editText.getGravity();
        this.collapsingTextHelper.setCollapsedTextGravity((editTextGravity & (-113)) | 48);
        this.collapsingTextHelper.setExpandedTextGravity(editTextGravity);
        this.editText.addTextChangedListener(new TextWatcher() { // from class: com.google.android.material.textfield.TextInputLayout.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                TextInputLayout textInputLayout = TextInputLayout.this;
                textInputLayout.updateLabelState(!textInputLayout.restoringSavedState);
                if (TextInputLayout.this.counterEnabled) {
                    TextInputLayout.this.updateCounter(s.length());
                }
                if (TextInputLayout.this.placeholderEnabled) {
                    TextInputLayout.this.updatePlaceholderText(s.length());
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        if (this.defaultHintTextColor == null) {
            this.defaultHintTextColor = this.editText.getHintTextColors();
        }
        if (this.hintEnabled) {
            if (TextUtils.isEmpty(this.hint)) {
                this.originalHint = this.editText.getHint();
                setHint(this.originalHint);
                this.editText.setHint((CharSequence) null);
            }
            this.isProvidingHint = true;
        }
        if (this.counterView != null) {
            updateCounter(this.editText.getText().length());
        }
        updateEditTextBackground();
        this.indicatorViewController.adjustIndicatorPadding();
        this.startLayout.bringToFront();
        this.endLayout.bringToFront();
        this.endIconFrame.bringToFront();
        this.errorIconView.bringToFront();
        dispatchOnEditTextAttached();
        updatePrefixTextViewPadding();
        updateSuffixTextViewPadding();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        updateLabelState(false, true);
    }

    private void updateInputLayoutMargins() {
        if (this.boxBackgroundMode != 1) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
            int newTopMargin = calculateLabelMarginTop();
            if (newTopMargin != lp.topMargin) {
                lp.topMargin = newTopMargin;
                this.inputFrame.requestLayout();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.editText;
        if (editText != null) {
            return editText.getBaseline() + getPaddingTop() + calculateLabelMarginTop();
        }
        return super.getBaseline();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateLabelState(boolean animate) {
        updateLabelState(animate, false);
    }

    private void updateLabelState(boolean animate, boolean force) {
        ColorStateList colorStateList;
        TextView textView;
        boolean isEnabled = isEnabled();
        EditText editText = this.editText;
        boolean hasText = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.editText;
        boolean hasFocus = editText2 != null && editText2.hasFocus();
        boolean errorShouldBeShown = this.indicatorViewController.errorShouldBeShown();
        ColorStateList colorStateList2 = this.defaultHintTextColor;
        if (colorStateList2 != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList2);
            this.collapsingTextHelper.setExpandedTextColor(this.defaultHintTextColor);
        }
        if (!isEnabled) {
            ColorStateList colorStateList3 = this.defaultHintTextColor;
            int disabledHintColor = colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.disabledColor) : this.disabledColor;
            this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(disabledHintColor));
            this.collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(disabledHintColor));
        } else if (errorShouldBeShown) {
            this.collapsingTextHelper.setCollapsedTextColor(this.indicatorViewController.getErrorViewTextColors());
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(textView.getTextColors());
        } else if (hasFocus && (colorStateList = this.focusedTextColor) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
        }
        if (hasText || (isEnabled() && (hasFocus || errorShouldBeShown))) {
            if (force || this.hintExpanded) {
                collapseHint(animate);
            }
        } else if (force || !this.hintExpanded) {
            expandHint(animate);
        }
    }

    public EditText getEditText() {
        return this.editText;
    }

    public void setHint(CharSequence hint) {
        if (this.hintEnabled) {
            setHintInternal(hint);
            sendAccessibilityEvent(2048);
        }
    }

    private void setHintInternal(CharSequence hint) {
        if (!TextUtils.equals(hint, this.hint)) {
            this.hint = hint;
            this.collapsingTextHelper.setText(hint);
            if (!this.hintExpanded) {
                openCutout();
            }
        }
    }

    public CharSequence getHint() {
        if (this.hintEnabled) {
            return this.hint;
        }
        return null;
    }

    public void setHintEnabled(boolean enabled) {
        if (enabled != this.hintEnabled) {
            this.hintEnabled = enabled;
            if (!this.hintEnabled) {
                this.isProvidingHint = false;
                if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
                    this.editText.setHint(this.hint);
                }
                setHintInternal(null);
            } else {
                CharSequence editTextHint = this.editText.getHint();
                if (!TextUtils.isEmpty(editTextHint)) {
                    if (TextUtils.isEmpty(this.hint)) {
                        setHint(editTextHint);
                    }
                    this.editText.setHint((CharSequence) null);
                }
                this.isProvidingHint = true;
            }
            if (this.editText != null) {
                updateInputLayoutMargins();
            }
        }
    }

    public boolean isHintEnabled() {
        return this.hintEnabled;
    }

    public boolean isProvidingHint() {
        return this.isProvidingHint;
    }

    public void setHintTextAppearance(int resId) {
        this.collapsingTextHelper.setCollapsedTextAppearance(resId);
        this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
        if (this.editText != null) {
            updateLabelState(false);
            updateInputLayoutMargins();
        }
    }

    public void setHintTextColor(ColorStateList hintTextColor) {
        if (this.focusedTextColor != hintTextColor) {
            if (this.defaultHintTextColor == null) {
                this.collapsingTextHelper.setCollapsedTextColor(hintTextColor);
            }
            this.focusedTextColor = hintTextColor;
            if (this.editText != null) {
                updateLabelState(false);
            }
        }
    }

    public ColorStateList getHintTextColor() {
        return this.focusedTextColor;
    }

    public void setDefaultHintTextColor(ColorStateList textColor) {
        this.defaultHintTextColor = textColor;
        this.focusedTextColor = textColor;
        if (this.editText != null) {
            updateLabelState(false);
        }
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    public void setErrorEnabled(boolean enabled) {
        this.indicatorViewController.setErrorEnabled(enabled);
    }

    public void setErrorTextAppearance(int errorTextAppearance) {
        this.indicatorViewController.setErrorTextAppearance(errorTextAppearance);
    }

    public void setErrorTextColor(ColorStateList errorTextColor) {
        this.indicatorViewController.setErrorViewTextColor(errorTextColor);
    }

    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public void setHelperTextTextAppearance(int helperTextTextAppearance) {
        this.indicatorViewController.setHelperTextAppearance(helperTextTextAppearance);
    }

    public void setHelperTextColor(ColorStateList helperTextColor) {
        this.indicatorViewController.setHelperTextViewTextColor(helperTextColor);
    }

    public boolean isErrorEnabled() {
        return this.indicatorViewController.isErrorEnabled();
    }

    public void setHelperTextEnabled(boolean enabled) {
        this.indicatorViewController.setHelperTextEnabled(enabled);
    }

    public void setHelperText(CharSequence helperText) {
        if (TextUtils.isEmpty(helperText)) {
            if (isHelperTextEnabled()) {
                setHelperTextEnabled(false);
                return;
            }
            return;
        }
        if (!isHelperTextEnabled()) {
            setHelperTextEnabled(true);
        }
        this.indicatorViewController.showHelper(helperText);
    }

    public boolean isHelperTextEnabled() {
        return this.indicatorViewController.isHelperTextEnabled();
    }

    public int getHelperTextCurrentTextColor() {
        return this.indicatorViewController.getHelperTextViewCurrentTextColor();
    }

    public void setErrorContentDescription(CharSequence errorContentDecription) {
        this.indicatorViewController.setErrorContentDescription(errorContentDecription);
    }

    public CharSequence getErrorContentDescription() {
        return this.indicatorViewController.getErrorContentDescription();
    }

    public void setError(CharSequence errorText) {
        if (!this.indicatorViewController.isErrorEnabled()) {
            if (TextUtils.isEmpty(errorText)) {
                return;
            }
            setErrorEnabled(true);
        }
        if (!TextUtils.isEmpty(errorText)) {
            this.indicatorViewController.showError(errorText);
        } else {
            this.indicatorViewController.hideError();
        }
    }

    public void setErrorIconDrawable(int resId) {
        setErrorIconDrawable(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    public void setErrorIconDrawable(Drawable errorIconDrawable) {
        this.errorIconView.setImageDrawable(errorIconDrawable);
        setErrorIconVisible(errorIconDrawable != null && this.indicatorViewController.isErrorEnabled());
    }

    public Drawable getErrorIconDrawable() {
        return this.errorIconView.getDrawable();
    }

    public void setErrorIconTintList(ColorStateList errorIconTintList) {
        this.errorIconTintList = errorIconTintList;
        Drawable icon = this.errorIconView.getDrawable();
        if (icon != null) {
            icon = DrawableCompat.wrap(icon).mutate();
            DrawableCompat.setTintList(icon, errorIconTintList);
        }
        if (this.errorIconView.getDrawable() != icon) {
            this.errorIconView.setImageDrawable(icon);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode errorIconTintMode) {
        Drawable icon = this.errorIconView.getDrawable();
        if (icon != null) {
            icon = DrawableCompat.wrap(icon).mutate();
            DrawableCompat.setTintMode(icon, errorIconTintMode);
        }
        if (this.errorIconView.getDrawable() != icon) {
            this.errorIconView.setImageDrawable(icon);
        }
    }

    public void setCounterEnabled(boolean enabled) {
        if (this.counterEnabled != enabled) {
            if (!enabled) {
                this.indicatorViewController.removeIndicator(this.counterView, 2);
                this.counterView = null;
            } else {
                this.counterView = new AppCompatTextView(getContext());
                this.counterView.setId(R.id.textinput_counter);
                Typeface typeface = this.typeface;
                if (typeface != null) {
                    this.counterView.setTypeface(typeface);
                }
                this.counterView.setMaxLines(1);
                this.indicatorViewController.addIndicator(this.counterView, 2);
                MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) this.counterView.getLayoutParams(), getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_counter_margin_start));
                updateCounterTextAppearanceAndColor();
                updateCounter();
            }
            this.counterEnabled = enabled;
        }
    }

    public void setCounterTextAppearance(int counterTextAppearance) {
        if (this.counterTextAppearance != counterTextAppearance) {
            this.counterTextAppearance = counterTextAppearance;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterTextColor(ColorStateList counterTextColor) {
        if (this.counterTextColor != counterTextColor) {
            this.counterTextColor = counterTextColor;
            updateCounterTextAppearanceAndColor();
        }
    }

    public ColorStateList getCounterTextColor() {
        return this.counterTextColor;
    }

    public void setCounterOverflowTextAppearance(int counterOverflowTextAppearance) {
        if (this.counterOverflowTextAppearance != counterOverflowTextAppearance) {
            this.counterOverflowTextAppearance = counterOverflowTextAppearance;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList counterOverflowTextColor) {
        if (this.counterOverflowTextColor != counterOverflowTextColor) {
            this.counterOverflowTextColor = counterOverflowTextColor;
            updateCounterTextAppearanceAndColor();
        }
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.counterTextColor;
    }

    public boolean isCounterEnabled() {
        return this.counterEnabled;
    }

    public void setCounterMaxLength(int maxLength) {
        if (this.counterMaxLength != maxLength) {
            if (maxLength > 0) {
                this.counterMaxLength = maxLength;
            } else {
                this.counterMaxLength = -1;
            }
            if (this.counterEnabled) {
                updateCounter();
            }
        }
    }

    private void updateCounter() {
        if (this.counterView != null) {
            EditText editText = this.editText;
            updateCounter(editText == null ? 0 : editText.getText().length());
        }
    }

    void updateCounter(int length) {
        boolean wasCounterOverflowed = this.counterOverflowed;
        int i = this.counterMaxLength;
        if (i == -1) {
            this.counterView.setText(String.valueOf(length));
            this.counterView.setContentDescription(null);
            this.counterOverflowed = false;
        } else {
            this.counterOverflowed = length > i;
            updateCounterContentDescription(getContext(), this.counterView, length, this.counterMaxLength, this.counterOverflowed);
            if (wasCounterOverflowed != this.counterOverflowed) {
                updateCounterTextAppearanceAndColor();
            }
            BidiFormatter bidiFormatter = BidiFormatter.getInstance();
            this.counterView.setText(bidiFormatter.unicodeWrap(getContext().getString(R.string.character_counter_pattern, Integer.valueOf(length), Integer.valueOf(this.counterMaxLength))));
        }
        if (this.editText != null && wasCounterOverflowed != this.counterOverflowed) {
            updateLabelState(false);
            updateTextInputBoxState();
            updateEditTextBackground();
        }
    }

    private static void updateCounterContentDescription(Context context, TextView counterView, int length, int counterMaxLength, boolean counterOverflowed) {
        counterView.setContentDescription(context.getString(counterOverflowed ? R.string.character_counter_overflowed_content_description : R.string.character_counter_content_description, Integer.valueOf(length), Integer.valueOf(counterMaxLength)));
    }

    public void setPlaceholderText(CharSequence placeholderText) {
        if (this.placeholderEnabled && TextUtils.isEmpty(placeholderText)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.placeholderEnabled) {
                setPlaceholderTextEnabled(true);
            }
            this.placeholderText = placeholderText;
        }
        updatePlaceholderText();
    }

    public CharSequence getPlaceholderText() {
        if (this.placeholderEnabled) {
            return this.placeholderText;
        }
        return null;
    }

    private void setPlaceholderTextEnabled(boolean placeholderEnabled) {
        if (this.placeholderEnabled == placeholderEnabled) {
            return;
        }
        if (placeholderEnabled) {
            this.placeholderTextView = new AppCompatTextView(getContext());
            this.placeholderTextView.setId(R.id.textinput_placeholder);
            ViewCompat.setAccessibilityLiveRegion(this.placeholderTextView, 1);
            setPlaceholderTextAppearance(this.placeholderTextAppearance);
            setPlaceholderTextColor(this.placeholderTextColor);
            addPlaceholderTextView();
        } else {
            removePlaceholderTextView();
            this.placeholderTextView = null;
        }
        this.placeholderEnabled = placeholderEnabled;
    }

    private void updatePlaceholderText() {
        EditText editText = this.editText;
        updatePlaceholderText(editText == null ? 0 : editText.getText().length());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlaceholderText(int inputTextLength) {
        if (inputTextLength == 0 && !this.hintExpanded) {
            showPlaceholderText();
        } else {
            hidePlaceholderText();
        }
    }

    private void showPlaceholderText() {
        TextView textView = this.placeholderTextView;
        if (textView != null && this.placeholderEnabled) {
            textView.setText(this.placeholderText);
            this.placeholderTextView.setVisibility(0);
            this.placeholderTextView.bringToFront();
        }
    }

    private void hidePlaceholderText() {
        TextView textView = this.placeholderTextView;
        if (textView != null && this.placeholderEnabled) {
            textView.setText((CharSequence) null);
            this.placeholderTextView.setVisibility(4);
        }
    }

    private void addPlaceholderTextView() {
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            this.inputFrame.addView(textView);
            this.placeholderTextView.setVisibility(0);
        }
    }

    private void removePlaceholderTextView() {
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public void setPlaceholderTextColor(ColorStateList placeholderTextColor) {
        if (this.placeholderTextColor != placeholderTextColor) {
            this.placeholderTextColor = placeholderTextColor;
            TextView textView = this.placeholderTextView;
            if (textView != null && placeholderTextColor != null) {
                textView.setTextColor(placeholderTextColor);
            }
        }
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.placeholderTextColor;
    }

    public void setPlaceholderTextAppearance(int placeholderTextAppearance) {
        this.placeholderTextAppearance = placeholderTextAppearance;
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            TextViewCompat.setTextAppearance(textView, placeholderTextAppearance);
        }
    }

    public int getPlaceholderTextAppearance() {
        return this.placeholderTextAppearance;
    }

    public void setPrefixText(CharSequence prefixText) {
        this.prefixText = TextUtils.isEmpty(prefixText) ? null : prefixText;
        this.prefixTextView.setText(prefixText);
        updatePrefixTextVisibility();
    }

    public CharSequence getPrefixText() {
        return this.prefixText;
    }

    public TextView getPrefixTextView() {
        return this.prefixTextView;
    }

    private void updatePrefixTextVisibility() {
        this.prefixTextView.setVisibility((this.prefixText == null || isHintExpanded()) ? 8 : 0);
        updateDummyDrawables();
    }

    public void setPrefixTextColor(ColorStateList prefixTextColor) {
        this.prefixTextView.setTextColor(prefixTextColor);
    }

    public ColorStateList getPrefixTextColor() {
        return this.prefixTextView.getTextColors();
    }

    public void setPrefixTextAppearance(int prefixTextAppearance) {
        TextViewCompat.setTextAppearance(this.prefixTextView, prefixTextAppearance);
    }

    private void updatePrefixTextViewPadding() {
        if (this.editText == null) {
            return;
        }
        int startPadding = isStartIconVisible() ? 0 : ViewCompat.getPaddingStart(this.editText);
        ViewCompat.setPaddingRelative(this.prefixTextView, startPadding, this.editText.getCompoundPaddingTop(), 0, this.editText.getCompoundPaddingBottom());
    }

    public void setSuffixText(CharSequence suffixText) {
        this.suffixText = TextUtils.isEmpty(suffixText) ? null : suffixText;
        this.suffixTextView.setText(suffixText);
        updateSuffixTextVisibility();
    }

    public CharSequence getSuffixText() {
        return this.suffixText;
    }

    public TextView getSuffixTextView() {
        return this.suffixTextView;
    }

    private void updateSuffixTextVisibility() {
        int oldSuffixVisibility = this.suffixTextView.getVisibility();
        boolean visible = (this.suffixText == null || isHintExpanded()) ? false : true;
        this.suffixTextView.setVisibility(visible ? 0 : 8);
        if (oldSuffixVisibility != this.suffixTextView.getVisibility()) {
            getEndIconDelegate().onSuffixVisibilityChanged(visible);
        }
        updateDummyDrawables();
    }

    public void setSuffixTextColor(ColorStateList suffixTextColor) {
        this.suffixTextView.setTextColor(suffixTextColor);
    }

    public ColorStateList getSuffixTextColor() {
        return this.suffixTextView.getTextColors();
    }

    public void setSuffixTextAppearance(int suffixTextAppearance) {
        TextViewCompat.setTextAppearance(this.suffixTextView, suffixTextAppearance);
    }

    private void updateSuffixTextViewPadding() {
        if (this.editText == null) {
            return;
        }
        int endPadding = (isEndIconVisible() || isErrorIconVisible()) ? 0 : ViewCompat.getPaddingEnd(this.editText);
        ViewCompat.setPaddingRelative(this.suffixTextView, 0, this.editText.getPaddingTop(), endPadding, this.editText.getPaddingBottom());
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        recursiveSetEnabled(this, enabled);
        super.setEnabled(enabled);
    }

    private static void recursiveSetEnabled(ViewGroup vg, boolean enabled) {
        int count = vg.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enabled);
            if (child instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) child, enabled);
            }
        }
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.counterEnabled && this.counterOverflowed && (textView = this.counterView) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    private void updateCounterTextAppearanceAndColor() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.counterView;
        if (textView != null) {
            setTextAppearanceCompatWithErrorFallback(textView, this.counterOverflowed ? this.counterOverflowTextAppearance : this.counterTextAppearance);
            if (!this.counterOverflowed && (colorStateList2 = this.counterTextColor) != null) {
                this.counterView.setTextColor(colorStateList2);
            }
            if (this.counterOverflowed && (colorStateList = this.counterOverflowTextColor) != null) {
                this.counterView.setTextColor(colorStateList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTextAppearanceCompatWithErrorFallback(TextView textView, int textAppearance) {
        boolean useDefaultColor = false;
        try {
            TextViewCompat.setTextAppearance(textView, textAppearance);
            if (Build.VERSION.SDK_INT >= 23) {
                if (textView.getTextColors().getDefaultColor() == -65281) {
                    useDefaultColor = true;
                }
            }
        } catch (Exception e) {
            useDefaultColor = true;
        }
        if (useDefaultColor) {
            TextViewCompat.setTextAppearance(textView, R.style.TextAppearance_AppCompat_Caption);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.design_error));
        }
    }

    private int calculateLabelMarginTop() {
        if (this.hintEnabled) {
            int i = this.boxBackgroundMode;
            if (i == 0 || i == 1) {
                return (int) this.collapsingTextHelper.getCollapsedTextHeight();
            }
            if (i != 2) {
                return 0;
            }
            return (int) (this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f);
        }
        return 0;
    }

    private Rect calculateCollapsedTextBounds(Rect rect) {
        if (this.editText == null) {
            throw new IllegalStateException();
        }
        Rect bounds = this.tmpBoundsRect;
        boolean isRtl = ViewCompat.getLayoutDirection(this) == 1;
        bounds.bottom = rect.bottom;
        int i = this.boxBackgroundMode;
        if (i == 1) {
            bounds.left = getLabelLeftBoundAlightWithPrefix(rect.left, isRtl);
            bounds.top = rect.top + this.boxCollapsedPaddingTopPx;
            bounds.right = getLabelRightBoundAlignedWithSuffix(rect.right, isRtl);
            return bounds;
        } else if (i == 2) {
            bounds.left = rect.left + this.editText.getPaddingLeft();
            bounds.top = rect.top - calculateLabelMarginTop();
            bounds.right = rect.right - this.editText.getPaddingRight();
            return bounds;
        } else {
            bounds.left = getLabelLeftBoundAlightWithPrefix(rect.left, isRtl);
            bounds.top = getPaddingTop();
            bounds.right = getLabelRightBoundAlignedWithSuffix(rect.right, isRtl);
            return bounds;
        }
    }

    private int getLabelLeftBoundAlightWithPrefix(int rectLeft, boolean isRtl) {
        int left = this.editText.getCompoundPaddingLeft() + rectLeft;
        if (this.prefixText != null && !isRtl) {
            return (left - this.prefixTextView.getMeasuredWidth()) + this.prefixTextView.getPaddingLeft();
        }
        return left;
    }

    private int getLabelRightBoundAlignedWithSuffix(int rectRight, boolean isRtl) {
        int right = rectRight - this.editText.getCompoundPaddingRight();
        if (this.prefixText != null && isRtl) {
            return right + (this.prefixTextView.getMeasuredWidth() - this.prefixTextView.getPaddingRight());
        }
        return right;
    }

    private Rect calculateExpandedTextBounds(Rect rect) {
        if (this.editText == null) {
            throw new IllegalStateException();
        }
        Rect bounds = this.tmpBoundsRect;
        float labelHeight = this.collapsingTextHelper.getExpandedTextHeight();
        bounds.left = rect.left + this.editText.getCompoundPaddingLeft();
        bounds.top = calculateExpandedLabelTop(rect, labelHeight);
        bounds.right = rect.right - this.editText.getCompoundPaddingRight();
        bounds.bottom = calculateExpandedLabelBottom(rect, bounds, labelHeight);
        return bounds;
    }

    private int calculateExpandedLabelTop(Rect rect, float labelHeight) {
        if (isSingleLineFilledTextField()) {
            return (int) (rect.centerY() - (labelHeight / 2.0f));
        }
        return rect.top + this.editText.getCompoundPaddingTop();
    }

    private int calculateExpandedLabelBottom(Rect rect, Rect bounds, float labelHeight) {
        if (isSingleLineFilledTextField()) {
            return (int) (bounds.top + labelHeight);
        }
        return rect.bottom - this.editText.getCompoundPaddingBottom();
    }

    private boolean isSingleLineFilledTextField() {
        return this.boxBackgroundMode == 1 && (Build.VERSION.SDK_INT < 16 || this.editText.getMinLines() <= 1);
    }

    private int calculateBoxBackgroundColor() {
        int backgroundColor = this.boxBackgroundColor;
        if (this.boxBackgroundMode == 1) {
            int surfaceLayerColor = MaterialColors.getColor(this, R.attr.colorSurface, 0);
            int backgroundColor2 = MaterialColors.layer(surfaceLayerColor, this.boxBackgroundColor);
            return backgroundColor2;
        }
        return backgroundColor;
    }

    private void applyBoxAttributes() {
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        if (materialShapeDrawable == null) {
            return;
        }
        materialShapeDrawable.setShapeAppearanceModel(this.shapeAppearanceModel);
        if (canDrawOutlineStroke()) {
            this.boxBackground.setStroke(this.boxStrokeWidthPx, this.boxStrokeColor);
        }
        this.boxBackgroundColor = calculateBoxBackgroundColor();
        this.boxBackground.setFillColor(ColorStateList.valueOf(this.boxBackgroundColor));
        if (this.endIconMode == 3) {
            this.editText.getBackground().invalidateSelf();
        }
        applyBoxUnderlineAttributes();
        invalidate();
    }

    private void applyBoxUnderlineAttributes() {
        if (this.boxUnderline == null) {
            return;
        }
        if (canDrawStroke()) {
            this.boxUnderline.setFillColor(ColorStateList.valueOf(this.boxStrokeColor));
        }
        invalidate();
    }

    private boolean canDrawOutlineStroke() {
        return this.boxBackgroundMode == 2 && canDrawStroke();
    }

    private boolean canDrawStroke() {
        return this.boxStrokeWidthPx > -1 && this.boxStrokeColor != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateEditTextBackground() {
        Drawable editTextBackground;
        TextView textView;
        EditText editText = this.editText;
        if (editText == null || this.boxBackgroundMode != 0 || (editTextBackground = editText.getBackground()) == null) {
            return;
        }
        if (DrawableUtils.canSafelyMutateDrawable(editTextBackground)) {
            editTextBackground = editTextBackground.mutate();
        }
        if (this.indicatorViewController.errorShouldBeShown()) {
            editTextBackground.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.indicatorViewController.getErrorViewCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            editTextBackground.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            DrawableCompat.clearColorFilter(editTextBackground);
            this.editText.refreshDrawableState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.textfield.TextInputLayout.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in, null);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        CharSequence error;
        boolean isEndIconChecked;

        SavedState(Parcelable superState) {
            super(superState);
        }

        SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(source);
            this.isEndIconChecked = source.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            TextUtils.writeToParcel(this.error, dest, flags);
            dest.writeInt(this.isEndIconChecked ? 1 : 0);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.error) + "}";
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        if (this.indicatorViewController.errorShouldBeShown()) {
            ss.error = getError();
        }
        ss.isEndIconChecked = hasEndIcon() && this.endIconView.isChecked();
        return ss;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setError(ss.error);
        if (ss.isEndIconChecked) {
            this.endIconView.post(new Runnable() { // from class: com.google.android.material.textfield.TextInputLayout.2
                @Override // java.lang.Runnable
                public void run() {
                    TextInputLayout.this.endIconView.performClick();
                    TextInputLayout.this.endIconView.jumpDrawablesToCurrentState();
                }
            });
        }
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(container);
        this.restoringSavedState = false;
    }

    public CharSequence getError() {
        if (this.indicatorViewController.isErrorEnabled()) {
            return this.indicatorViewController.getErrorText();
        }
        return null;
    }

    public CharSequence getHelperText() {
        if (this.indicatorViewController.isHelperTextEnabled()) {
            return this.indicatorViewController.getHelperText();
        }
        return null;
    }

    public boolean isHintAnimationEnabled() {
        return this.hintAnimationEnabled;
    }

    public void setHintAnimationEnabled(boolean enabled) {
        this.hintAnimationEnabled = enabled;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        boolean updatedHeight = updateEditTextHeightBasedOnIcon();
        boolean updatedIcon = updateDummyDrawables();
        if (updatedHeight || updatedIcon) {
            this.editText.post(new Runnable() { // from class: com.google.android.material.textfield.TextInputLayout.3
                @Override // java.lang.Runnable
                public void run() {
                    TextInputLayout.this.editText.requestLayout();
                }
            });
        }
        updatePlaceholderMeasurementsBasedOnEditText();
        updatePrefixTextViewPadding();
        updateSuffixTextViewPadding();
    }

    private boolean updateEditTextHeightBasedOnIcon() {
        int maxIconHeight;
        if (this.editText != null && this.editText.getMeasuredHeight() < (maxIconHeight = Math.max(this.endLayout.getMeasuredHeight(), this.startLayout.getMeasuredHeight()))) {
            this.editText.setMinimumHeight(maxIconHeight);
            return true;
        }
        return false;
    }

    private void updatePlaceholderMeasurementsBasedOnEditText() {
        EditText editText;
        if (this.placeholderTextView != null && (editText = this.editText) != null) {
            int editTextGravity = editText.getGravity();
            this.placeholderTextView.setGravity(editTextGravity);
            this.placeholderTextView.setPadding(this.editText.getCompoundPaddingLeft(), this.editText.getCompoundPaddingTop(), this.editText.getCompoundPaddingRight(), this.editText.getCompoundPaddingBottom());
        }
    }

    public void setStartIconDrawable(int resId) {
        setStartIconDrawable(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    public void setStartIconDrawable(Drawable startIconDrawable) {
        this.startIconView.setImageDrawable(startIconDrawable);
        if (startIconDrawable != null) {
            setStartIconVisible(true);
            applyStartIconTint();
            return;
        }
        setStartIconVisible(false);
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        setStartIconContentDescription((CharSequence) null);
    }

    public Drawable getStartIconDrawable() {
        return this.startIconView.getDrawable();
    }

    public void setStartIconOnClickListener(View.OnClickListener startIconOnClickListener) {
        setIconOnClickListener(this.startIconView, startIconOnClickListener, this.startIconOnLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener startIconOnLongClickListener) {
        this.startIconOnLongClickListener = startIconOnLongClickListener;
        setIconOnLongClickListener(this.startIconView, startIconOnLongClickListener);
    }

    public void setStartIconVisible(boolean visible) {
        if (isStartIconVisible() != visible) {
            this.startIconView.setVisibility(visible ? 0 : 8);
            updatePrefixTextViewPadding();
            updateDummyDrawables();
        }
    }

    public boolean isStartIconVisible() {
        return this.startIconView.getVisibility() == 0;
    }

    public void setStartIconCheckable(boolean startIconCheckable) {
        this.startIconView.setCheckable(startIconCheckable);
    }

    public boolean isStartIconCheckable() {
        return this.startIconView.isCheckable();
    }

    public void setStartIconContentDescription(int resId) {
        setStartIconContentDescription(resId != 0 ? getResources().getText(resId) : null);
    }

    public void setStartIconContentDescription(CharSequence startIconContentDescription) {
        if (getStartIconContentDescription() != startIconContentDescription) {
            this.startIconView.setContentDescription(startIconContentDescription);
        }
    }

    public CharSequence getStartIconContentDescription() {
        return this.startIconView.getContentDescription();
    }

    public void setStartIconTintList(ColorStateList startIconTintList) {
        if (this.startIconTintList != startIconTintList) {
            this.startIconTintList = startIconTintList;
            this.hasStartIconTintList = true;
            applyStartIconTint();
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode startIconTintMode) {
        if (this.startIconTintMode != startIconTintMode) {
            this.startIconTintMode = startIconTintMode;
            this.hasStartIconTintMode = true;
            applyStartIconTint();
        }
    }

    public void setEndIconMode(int endIconMode) {
        int previousEndIconMode = this.endIconMode;
        this.endIconMode = endIconMode;
        dispatchOnEndIconChanged(previousEndIconMode);
        setEndIconVisible(endIconMode != 0);
        if (getEndIconDelegate().isBoxBackgroundModeSupported(this.boxBackgroundMode)) {
            getEndIconDelegate().initialize();
            applyEndIconTint();
            return;
        }
        throw new IllegalStateException("The current box background mode " + this.boxBackgroundMode + " is not supported by the end icon mode " + endIconMode);
    }

    public int getEndIconMode() {
        return this.endIconMode;
    }

    public void setEndIconOnClickListener(View.OnClickListener endIconOnClickListener) {
        setIconOnClickListener(this.endIconView, endIconOnClickListener, this.endIconOnLongClickListener);
    }

    public void setErrorIconOnClickListener(View.OnClickListener errorIconOnClickListener) {
        setIconOnClickListener(this.errorIconView, errorIconOnClickListener, this.errorIconOnLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener endIconOnLongClickListener) {
        this.endIconOnLongClickListener = endIconOnLongClickListener;
        setIconOnLongClickListener(this.endIconView, endIconOnLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener errorIconOnLongClickListener) {
        this.errorIconOnLongClickListener = errorIconOnLongClickListener;
        setIconOnLongClickListener(this.errorIconView, errorIconOnLongClickListener);
    }

    public void setEndIconVisible(boolean visible) {
        if (isEndIconVisible() != visible) {
            this.endIconView.setVisibility(visible ? 0 : 8);
            updateSuffixTextViewPadding();
            updateDummyDrawables();
        }
    }

    public boolean isEndIconVisible() {
        return this.endIconFrame.getVisibility() == 0 && this.endIconView.getVisibility() == 0;
    }

    public void setEndIconActivated(boolean endIconActivated) {
        this.endIconView.setActivated(endIconActivated);
    }

    public void setEndIconCheckable(boolean endIconCheckable) {
        this.endIconView.setCheckable(endIconCheckable);
    }

    public boolean isEndIconCheckable() {
        return this.endIconView.isCheckable();
    }

    public void setEndIconDrawable(int resId) {
        setEndIconDrawable(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    public void setEndIconDrawable(Drawable endIconDrawable) {
        this.endIconView.setImageDrawable(endIconDrawable);
    }

    public Drawable getEndIconDrawable() {
        return this.endIconView.getDrawable();
    }

    public void setEndIconContentDescription(int resId) {
        setEndIconContentDescription(resId != 0 ? getResources().getText(resId) : null);
    }

    public void setEndIconContentDescription(CharSequence endIconContentDescription) {
        if (getEndIconContentDescription() != endIconContentDescription) {
            this.endIconView.setContentDescription(endIconContentDescription);
        }
    }

    public CharSequence getEndIconContentDescription() {
        return this.endIconView.getContentDescription();
    }

    public void setEndIconTintList(ColorStateList endIconTintList) {
        if (this.endIconTintList != endIconTintList) {
            this.endIconTintList = endIconTintList;
            this.hasEndIconTintList = true;
            applyEndIconTint();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode endIconTintMode) {
        if (this.endIconTintMode != endIconTintMode) {
            this.endIconTintMode = endIconTintMode;
            this.hasEndIconTintMode = true;
            applyEndIconTint();
        }
    }

    public void addOnEndIconChangedListener(OnEndIconChangedListener listener) {
        this.endIconChangedListeners.add(listener);
    }

    public void removeOnEndIconChangedListener(OnEndIconChangedListener listener) {
        this.endIconChangedListeners.remove(listener);
    }

    public void clearOnEndIconChangedListeners() {
        this.endIconChangedListeners.clear();
    }

    public void addOnEditTextAttachedListener(OnEditTextAttachedListener listener) {
        this.editTextAttachedListeners.add(listener);
        if (this.editText != null) {
            listener.onEditTextAttached(this);
        }
    }

    public void removeOnEditTextAttachedListener(OnEditTextAttachedListener listener) {
        this.editTextAttachedListeners.remove(listener);
    }

    public void clearOnEditTextAttachedListeners() {
        this.editTextAttachedListeners.clear();
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int resId) {
        setPasswordVisibilityToggleDrawable(resId != 0 ? AppCompatResources.getDrawable(getContext(), resId) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable icon) {
        this.endIconView.setImageDrawable(icon);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int resId) {
        setPasswordVisibilityToggleContentDescription(resId != 0 ? getResources().getText(resId) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence description) {
        this.endIconView.setContentDescription(description);
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.endIconView.getDrawable();
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endIconView.getContentDescription();
    }

    @Deprecated
    public boolean isPasswordVisibilityToggleEnabled() {
        return this.endIconMode == 1;
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean enabled) {
        if (enabled && this.endIconMode != 1) {
            setEndIconMode(1);
        } else if (!enabled) {
            setEndIconMode(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList tintList) {
        this.endIconTintList = tintList;
        this.hasEndIconTintList = true;
        applyEndIconTint();
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        this.endIconTintMode = mode;
        this.hasEndIconTintMode = true;
        applyEndIconTint();
    }

    @Deprecated
    public void passwordVisibilityToggleRequested(boolean shouldSkipAnimations) {
        if (this.endIconMode == 1) {
            this.endIconView.performClick();
            if (shouldSkipAnimations) {
                this.endIconView.jumpDrawablesToCurrentState();
            }
        }
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate delegate) {
        EditText editText = this.editText;
        if (editText != null) {
            ViewCompat.setAccessibilityDelegate(editText, delegate);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CheckableImageButton getEndIconView() {
        return this.endIconView;
    }

    private EndIconDelegate getEndIconDelegate() {
        EndIconDelegate endIconDelegate = this.endIconDelegates.get(this.endIconMode);
        return endIconDelegate != null ? endIconDelegate : this.endIconDelegates.get(0);
    }

    private void dispatchOnEditTextAttached() {
        Iterator<OnEditTextAttachedListener> it = this.editTextAttachedListeners.iterator();
        while (it.hasNext()) {
            OnEditTextAttachedListener listener = it.next();
            listener.onEditTextAttached(this);
        }
    }

    private void applyStartIconTint() {
        applyIconTint(this.startIconView, this.hasStartIconTintList, this.startIconTintList, this.hasStartIconTintMode, this.startIconTintMode);
    }

    private boolean hasEndIcon() {
        return this.endIconMode != 0;
    }

    private void dispatchOnEndIconChanged(int previousIcon) {
        Iterator<OnEndIconChangedListener> it = this.endIconChangedListeners.iterator();
        while (it.hasNext()) {
            OnEndIconChangedListener listener = it.next();
            listener.onEndIconChanged(this, previousIcon);
        }
    }

    private void tintEndIconOnError(boolean tintEndIconOnError) {
        if (tintEndIconOnError && getEndIconDrawable() != null) {
            Drawable endIconDrawable = DrawableCompat.wrap(getEndIconDrawable()).mutate();
            DrawableCompat.setTint(endIconDrawable, this.indicatorViewController.getErrorViewCurrentTextColor());
            this.endIconView.setImageDrawable(endIconDrawable);
            return;
        }
        applyEndIconTint();
    }

    private void applyEndIconTint() {
        applyIconTint(this.endIconView, this.hasEndIconTintList, this.endIconTintList, this.hasEndIconTintMode, this.endIconTintMode);
    }

    private boolean updateDummyDrawables() {
        if (this.editText == null) {
            return false;
        }
        boolean updatedIcon = false;
        if (shouldUpdateStartDummyDrawable()) {
            int right = this.startLayout.getMeasuredWidth() - this.editText.getPaddingLeft();
            if (this.startDummyDrawable == null || this.startDummyDrawableWidth != right) {
                this.startDummyDrawable = new ColorDrawable();
                this.startDummyDrawableWidth = right;
                this.startDummyDrawable.setBounds(0, 0, this.startDummyDrawableWidth, 1);
            }
            Drawable[] compounds = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            Drawable drawable = compounds[0];
            Drawable drawable2 = this.startDummyDrawable;
            if (drawable != drawable2) {
                TextViewCompat.setCompoundDrawablesRelative(this.editText, drawable2, compounds[1], compounds[2], compounds[3]);
                updatedIcon = true;
            }
        } else if (this.startDummyDrawable != null) {
            Drawable[] compounds2 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            TextViewCompat.setCompoundDrawablesRelative(this.editText, null, compounds2[1], compounds2[2], compounds2[3]);
            this.startDummyDrawable = null;
            updatedIcon = true;
        }
        if (shouldUpdateEndDummyDrawable()) {
            int right2 = this.suffixTextView.getMeasuredWidth() - this.editText.getPaddingRight();
            View iconView = getEndIconToUpdateDummyDrawable();
            if (iconView != null) {
                right2 = iconView.getMeasuredWidth() + right2 + MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) iconView.getLayoutParams());
            }
            Drawable[] compounds3 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            Drawable drawable3 = this.endDummyDrawable;
            if (drawable3 != null && this.endDummyDrawableWidth != right2) {
                this.endDummyDrawableWidth = right2;
                drawable3.setBounds(0, 0, this.endDummyDrawableWidth, 1);
                TextViewCompat.setCompoundDrawablesRelative(this.editText, compounds3[0], compounds3[1], this.endDummyDrawable, compounds3[3]);
                return true;
            }
            if (this.endDummyDrawable == null) {
                this.endDummyDrawable = new ColorDrawable();
                this.endDummyDrawableWidth = right2;
                this.endDummyDrawable.setBounds(0, 0, this.endDummyDrawableWidth, 1);
            }
            Drawable drawable4 = compounds3[2];
            Drawable drawable5 = this.endDummyDrawable;
            if (drawable4 != drawable5) {
                this.originalEditTextEndDrawable = compounds3[2];
                TextViewCompat.setCompoundDrawablesRelative(this.editText, compounds3[0], compounds3[1], drawable5, compounds3[3]);
                return true;
            }
            return updatedIcon;
        } else if (this.endDummyDrawable != null) {
            Drawable[] compounds4 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            if (compounds4[2] == this.endDummyDrawable) {
                TextViewCompat.setCompoundDrawablesRelative(this.editText, compounds4[0], compounds4[1], this.originalEditTextEndDrawable, compounds4[3]);
                updatedIcon = true;
            }
            this.endDummyDrawable = null;
            return updatedIcon;
        } else {
            return updatedIcon;
        }
    }

    private boolean shouldUpdateStartDummyDrawable() {
        return !(getStartIconDrawable() == null && this.prefixText == null) && this.startLayout.getMeasuredWidth() > 0;
    }

    private boolean shouldUpdateEndDummyDrawable() {
        return (this.errorIconView.getVisibility() == 0 || ((hasEndIcon() && isEndIconVisible()) || this.suffixText != null)) && this.endLayout.getMeasuredWidth() > 0;
    }

    private CheckableImageButton getEndIconToUpdateDummyDrawable() {
        if (this.errorIconView.getVisibility() == 0) {
            return this.errorIconView;
        }
        if (hasEndIcon() && isEndIconVisible()) {
            return this.endIconView;
        }
        return null;
    }

    private void applyIconTint(CheckableImageButton iconView, boolean hasIconTintList, ColorStateList iconTintList, boolean hasIconTintMode, PorterDuff.Mode iconTintMode) {
        Drawable icon = iconView.getDrawable();
        if (icon != null && (hasIconTintList || hasIconTintMode)) {
            icon = DrawableCompat.wrap(icon).mutate();
            if (hasIconTintList) {
                DrawableCompat.setTintList(icon, iconTintList);
            }
            if (hasIconTintMode) {
                DrawableCompat.setTintMode(icon, iconTintMode);
            }
        }
        if (iconView.getDrawable() != icon) {
            iconView.setImageDrawable(icon);
        }
    }

    private static void setIconOnClickListener(CheckableImageButton iconView, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener) {
        iconView.setOnClickListener(onClickListener);
        setIconClickable(iconView, onLongClickListener);
    }

    private static void setIconOnLongClickListener(CheckableImageButton iconView, View.OnLongClickListener onLongClickListener) {
        iconView.setOnLongClickListener(onLongClickListener);
        setIconClickable(iconView, onLongClickListener);
    }

    private static void setIconClickable(CheckableImageButton iconView, View.OnLongClickListener onLongClickListener) {
        boolean iconClickable = ViewCompat.hasOnClickListeners(iconView);
        boolean iconFocusable = false;
        boolean iconLongClickable = onLongClickListener != null;
        if (iconClickable || iconLongClickable) {
            iconFocusable = true;
        }
        iconView.setFocusable(iconFocusable);
        iconView.setClickable(iconClickable);
        iconView.setPressable(iconClickable);
        iconView.setLongClickable(iconLongClickable);
        ViewCompat.setImportantForAccessibility(iconView, iconFocusable ? 1 : 2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        EditText editText = this.editText;
        if (editText != null) {
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, editText, rect);
            updateBoxUnderlineBounds(rect);
            if (this.hintEnabled) {
                this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
                int editTextGravity = this.editText.getGravity();
                this.collapsingTextHelper.setCollapsedTextGravity((editTextGravity & (-113)) | 48);
                this.collapsingTextHelper.setExpandedTextGravity(editTextGravity);
                this.collapsingTextHelper.setCollapsedBounds(calculateCollapsedTextBounds(rect));
                this.collapsingTextHelper.setExpandedBounds(calculateExpandedTextBounds(rect));
                this.collapsingTextHelper.recalculate();
                if (cutoutEnabled() && !this.hintExpanded) {
                    openCutout();
                }
            }
        }
    }

    private void updateBoxUnderlineBounds(Rect bounds) {
        if (this.boxUnderline != null) {
            int top = bounds.bottom - this.boxStrokeWidthFocusedPx;
            this.boxUnderline.setBounds(bounds.left, top, bounds.right, bounds.bottom);
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawHint(canvas);
        drawBoxUnderline(canvas);
    }

    private void drawHint(Canvas canvas) {
        if (this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas);
        }
    }

    private void drawBoxUnderline(Canvas canvas) {
        MaterialShapeDrawable materialShapeDrawable = this.boxUnderline;
        if (materialShapeDrawable != null) {
            Rect underlineBounds = materialShapeDrawable.getBounds();
            underlineBounds.top = underlineBounds.bottom - this.boxStrokeWidthPx;
            this.boxUnderline.draw(canvas);
        }
    }

    private void collapseHint(boolean animate) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (!animate || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(1.0f);
        } else {
            animateToExpansionFraction(1.0f);
        }
        this.hintExpanded = false;
        if (cutoutEnabled()) {
            openCutout();
        }
        updatePlaceholderText();
        updatePrefixTextVisibility();
        updateSuffixTextVisibility();
    }

    private boolean cutoutEnabled() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable);
    }

    private void openCutout() {
        if (!cutoutEnabled()) {
            return;
        }
        RectF cutoutBounds = this.tmpRectF;
        this.collapsingTextHelper.getCollapsedTextActualBounds(cutoutBounds, this.editText.getWidth(), this.editText.getGravity());
        applyCutoutPadding(cutoutBounds);
        cutoutBounds.offset(-getPaddingLeft(), -getPaddingTop());
        ((CutoutDrawable) this.boxBackground).setCutout(cutoutBounds);
    }

    private void closeCutout() {
        if (cutoutEnabled()) {
            ((CutoutDrawable) this.boxBackground).removeCutout();
        }
    }

    private void applyCutoutPadding(RectF cutoutBounds) {
        cutoutBounds.left -= this.boxLabelCutoutPaddingPx;
        cutoutBounds.top -= this.boxLabelCutoutPaddingPx;
        cutoutBounds.right += this.boxLabelCutoutPaddingPx;
        cutoutBounds.bottom += this.boxLabelCutoutPaddingPx;
    }

    boolean cutoutIsOpen() {
        return cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.inDrawableStateChanged) {
            return;
        }
        boolean z = true;
        this.inDrawableStateChanged = true;
        super.drawableStateChanged();
        int[] state = getDrawableState();
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        boolean changed = collapsingTextHelper != null ? false | collapsingTextHelper.setState(state) : false;
        if (this.editText != null) {
            if (!ViewCompat.isLaidOut(this) || !isEnabled()) {
                z = false;
            }
            updateLabelState(z);
        }
        updateEditTextBackground();
        updateTextInputBoxState();
        if (changed) {
            invalidate();
        }
        this.inDrawableStateChanged = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateTextInputBoxState() {
        TextView textView;
        EditText editText;
        EditText editText2;
        if (this.boxBackground == null || this.boxBackgroundMode == 0) {
            return;
        }
        boolean z = false;
        boolean hasFocus = isFocused() || ((editText2 = this.editText) != null && editText2.hasFocus());
        boolean isHovered = isHovered() || ((editText = this.editText) != null && editText.isHovered());
        if (!isEnabled()) {
            this.boxStrokeColor = this.disabledColor;
        } else if (this.indicatorViewController.errorShouldBeShown()) {
            if (this.strokeErrorColor != null) {
                updateStrokeErrorColor(hasFocus, isHovered);
            } else {
                this.boxStrokeColor = this.indicatorViewController.getErrorViewCurrentTextColor();
            }
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            if (this.strokeErrorColor != null) {
                updateStrokeErrorColor(hasFocus, isHovered);
            } else {
                this.boxStrokeColor = textView.getCurrentTextColor();
            }
        } else if (hasFocus) {
            this.boxStrokeColor = this.focusedStrokeColor;
        } else if (isHovered) {
            this.boxStrokeColor = this.hoveredStrokeColor;
        } else {
            this.boxStrokeColor = this.defaultStrokeColor;
        }
        if (getErrorIconDrawable() != null && this.indicatorViewController.isErrorEnabled() && this.indicatorViewController.errorShouldBeShown()) {
            z = true;
        }
        setErrorIconVisible(z);
        updateIconColorOnState(this.errorIconView, this.errorIconTintList);
        updateIconColorOnState(this.startIconView, this.startIconTintList);
        updateIconColorOnState(this.endIconView, this.endIconTintList);
        if (getEndIconDelegate().shouldTintIconOnError()) {
            tintEndIconOnError(this.indicatorViewController.errorShouldBeShown());
        }
        if (hasFocus && isEnabled()) {
            this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
        } else {
            this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
        }
        if (this.boxBackgroundMode == 1) {
            if (!isEnabled()) {
                this.boxBackgroundColor = this.disabledFilledBackgroundColor;
            } else if (isHovered && !hasFocus) {
                this.boxBackgroundColor = this.hoveredFilledBackgroundColor;
            } else if (hasFocus) {
                this.boxBackgroundColor = this.focusedFilledBackgroundColor;
            } else {
                this.boxBackgroundColor = this.defaultFilledBackgroundColor;
            }
        }
        applyBoxAttributes();
    }

    private void updateStrokeErrorColor(boolean hasFocus, boolean isHovered) {
        int defaultStrokeErrorColor = this.strokeErrorColor.getDefaultColor();
        int hoveredStrokeErrorColor = this.strokeErrorColor.getColorForState(new int[]{16843623, 16842910}, defaultStrokeErrorColor);
        int focusedStrokeErrorColor = this.strokeErrorColor.getColorForState(new int[]{16843518, 16842910}, defaultStrokeErrorColor);
        if (hasFocus) {
            this.boxStrokeColor = focusedStrokeErrorColor;
        } else if (isHovered) {
            this.boxStrokeColor = hoveredStrokeErrorColor;
        } else {
            this.boxStrokeColor = defaultStrokeErrorColor;
        }
    }

    private void setErrorIconVisible(boolean errorIconVisible) {
        this.errorIconView.setVisibility(errorIconVisible ? 0 : 8);
        this.endIconFrame.setVisibility(errorIconVisible ? 8 : 0);
        updateSuffixTextViewPadding();
        if (!hasEndIcon()) {
            updateDummyDrawables();
        }
    }

    private boolean isErrorIconVisible() {
        return this.errorIconView.getVisibility() == 0;
    }

    private void updateIconColorOnState(CheckableImageButton iconView, ColorStateList colorStateList) {
        Drawable icon = iconView.getDrawable();
        if (iconView.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int color = colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
        Drawable icon2 = DrawableCompat.wrap(icon).mutate();
        DrawableCompat.setTintList(icon2, ColorStateList.valueOf(color));
        iconView.setImageDrawable(icon2);
    }

    private void expandHint(boolean animate) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (!animate || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(0.0f);
        } else {
            animateToExpansionFraction(0.0f);
        }
        if (cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout()) {
            closeCutout();
        }
        this.hintExpanded = true;
        hidePlaceholderText();
        updatePrefixTextVisibility();
        updateSuffixTextVisibility();
    }

    void animateToExpansionFraction(float target) {
        if (this.collapsingTextHelper.getExpansionFraction() == target) {
            return;
        }
        if (this.animator == null) {
            this.animator = new ValueAnimator();
            this.animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.animator.setDuration(167L);
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.TextInputLayout.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator animator) {
                    TextInputLayout.this.collapsingTextHelper.setExpansionFraction(((Float) animator.getAnimatedValue()).floatValue());
                }
            });
        }
        this.animator.setFloatValues(this.collapsingTextHelper.getExpansionFraction(), target);
        this.animator.start();
    }

    final boolean isHintExpanded() {
        return this.hintExpanded;
    }

    final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.helperTextIsDisplayed();
    }

    final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    final int getErrorTextCurrentColor() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    /* loaded from: classes.dex */
    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout layout) {
            this.layout = layout;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            CharSequence charSequence;
            String text;
            super.onInitializeAccessibilityNodeInfo(host, info);
            EditText editText = this.layout.getEditText();
            CharSequence inputText = editText != null ? editText.getText() : null;
            CharSequence hintText = this.layout.getHint();
            CharSequence helperText = this.layout.getHelperText();
            CharSequence errorText = this.layout.getError();
            int maxCharLimit = this.layout.getCounterMaxLength();
            CharSequence counterOverflowDesc = this.layout.getCounterOverflowDescription();
            boolean showingText = !TextUtils.isEmpty(inputText);
            boolean hasHint = !TextUtils.isEmpty(hintText);
            boolean hasHelperText = !TextUtils.isEmpty(helperText);
            boolean showingError = !TextUtils.isEmpty(errorText);
            boolean contentInvalid = showingError || !TextUtils.isEmpty(counterOverflowDesc);
            String hint = hasHint ? hintText.toString() : BuildConfig.FLAVOR;
            StringBuilder sb = new StringBuilder();
            sb.append(hint);
            sb.append(((showingError || hasHelperText) && !TextUtils.isEmpty(hint)) ? ", " : BuildConfig.FLAVOR);
            String hint2 = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(hint2);
            if (showingError) {
                charSequence = errorText;
            } else {
                charSequence = hasHelperText ? helperText : BuildConfig.FLAVOR;
            }
            sb2.append((Object) charSequence);
            String hint3 = sb2.toString();
            if (showingText) {
                info.setText(inputText);
            } else if (!TextUtils.isEmpty(hint3)) {
                info.setText(hint3);
            }
            if (!TextUtils.isEmpty(hint3)) {
                if (Build.VERSION.SDK_INT >= 26) {
                    info.setHintText(hint3);
                } else {
                    if (showingText) {
                        text = ((Object) inputText) + ", " + hint3;
                    } else {
                        text = hint3;
                    }
                    info.setText(text);
                }
                info.setShowingHintText(!showingText);
            }
            info.setMaxTextLength((inputText == null || inputText.length() != maxCharLimit) ? -1 : maxCharLimit);
            if (contentInvalid) {
                info.setError(showingError ? errorText : counterOverflowDesc);
            }
        }
    }
}
