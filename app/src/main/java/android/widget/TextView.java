//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.Editable.Factory;
import android.text.InputFilter;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.MovementMethod;
import android.text.method.TransformationMethod;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug.CapturedViewProperty;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewStructure;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.RemoteViews.RemoteView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;


/**
 * 重写 textview 控件
 */

@RemoteView
public class TextView extends View implements OnPreDrawListener {
    public TextView(Context context) {
        super((Context) null, (AttributeSet) null, 0, 0);
        throw new RuntimeException("Stub!");
    }

    public TextView(Context context, AttributeSet attrs) {
        super((Context) null, (AttributeSet) null, 0, 0);
        throw new RuntimeException("Stub!");
    }

    public TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super((Context) null, (AttributeSet) null, 0, 0);
        throw new RuntimeException("Stub!");
    }

    public TextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super((Context) null, (AttributeSet) null, 0, 0);
        throw new RuntimeException("Stub!");
    }

    public void setEnabled(boolean enabled) {
        throw new RuntimeException("Stub!");
    }

    public void setTypeface(Typeface tf, int style) {
        throw new RuntimeException("Stub!");
    }

    protected boolean getDefaultEditable() {
        throw new RuntimeException("Stub!");
    }

    protected MovementMethod getDefaultMovementMethod() {
        throw new RuntimeException("Stub!");
    }

    @CapturedViewProperty
    public CharSequence getText() {
        throw new RuntimeException("Stub!");
    }

    public int length() {
        throw new RuntimeException("Stub!");
    }

    public Editable getEditableText() {
        throw new RuntimeException("Stub!");
    }

    public int getLineHeight() {
        throw new RuntimeException("Stub!");
    }

    public final Layout getLayout() {
        throw new RuntimeException("Stub!");
    }

    public final KeyListener getKeyListener() {
        throw new RuntimeException("Stub!");
    }

    public void setKeyListener(KeyListener input) {
        throw new RuntimeException("Stub!");
    }

    public final MovementMethod getMovementMethod() {
        throw new RuntimeException("Stub!");
    }

    public final void setMovementMethod(MovementMethod movement) {
        throw new RuntimeException("Stub!");
    }

    public final TransformationMethod getTransformationMethod() {
        throw new RuntimeException("Stub!");
    }

    public final void setTransformationMethod(TransformationMethod method) {
        throw new RuntimeException("Stub!");
    }

    public int getCompoundPaddingTop() {
        throw new RuntimeException("Stub!");
    }

    public int getCompoundPaddingBottom() {
        throw new RuntimeException("Stub!");
    }

    public int getCompoundPaddingLeft() {
        throw new RuntimeException("Stub!");
    }

    public int getCompoundPaddingRight() {
        throw new RuntimeException("Stub!");
    }

    public int getCompoundPaddingStart() {
        throw new RuntimeException("Stub!");
    }

    public int getCompoundPaddingEnd() {
        throw new RuntimeException("Stub!");
    }

    public int getExtendedPaddingTop() {
        throw new RuntimeException("Stub!");
    }

    public int getExtendedPaddingBottom() {
        throw new RuntimeException("Stub!");
    }

    public int getTotalPaddingLeft() {
        throw new RuntimeException("Stub!");
    }

    public int getTotalPaddingRight() {
        throw new RuntimeException("Stub!");
    }

    public int getTotalPaddingStart() {
        throw new RuntimeException("Stub!");
    }

    public int getTotalPaddingEnd() {
        throw new RuntimeException("Stub!");
    }

    public int getTotalPaddingTop() {
        throw new RuntimeException("Stub!");
    }

    public int getTotalPaddingBottom() {
        throw new RuntimeException("Stub!");
    }

    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        throw new RuntimeException("Stub!");
    }

    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {
        throw new RuntimeException("Stub!");
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable
            right, Drawable bottom) {
        throw new RuntimeException("Stub!");
    }

    public void setCompoundDrawablesRelative(Drawable start, Drawable top, Drawable end, Drawable
            bottom) {
        throw new RuntimeException("Stub!");
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int
            bottom) {
        throw new RuntimeException("Stub!");
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable start, Drawable top,
                                                                Drawable end, Drawable bottom) {
        throw new RuntimeException("Stub!");
    }

    public Drawable[] getCompoundDrawables() {
        throw new RuntimeException("Stub!");
    }

    public Drawable[] getCompoundDrawablesRelative() {
        throw new RuntimeException("Stub!");
    }

    public void setCompoundDrawablePadding(int pad) {
        throw new RuntimeException("Stub!");
    }

    public int getCompoundDrawablePadding() {
        throw new RuntimeException("Stub!");
    }

    public void setCompoundDrawableTintList(ColorStateList tint) {
        throw new RuntimeException("Stub!");
    }

    public ColorStateList getCompoundDrawableTintList() {
        throw new RuntimeException("Stub!");
    }

    public void setCompoundDrawableTintMode(Mode tintMode) {
        throw new RuntimeException("Stub!");
    }

    public Mode getCompoundDrawableTintMode() {
        throw new RuntimeException("Stub!");
    }

    public void setPadding(int left, int top, int right, int bottom) {
        throw new RuntimeException("Stub!");
    }

    public void setPaddingRelative(int start, int top, int end, int bottom) {
        throw new RuntimeException("Stub!");
    }

    public final int getAutoLinkMask() {
        throw new RuntimeException("Stub!");
    }

    public void setTextAppearance(int resId) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setTextAppearance(Context context, int resId) {
        throw new RuntimeException("Stub!");
    }

    public Locale getTextLocale() {
        throw new RuntimeException("Stub!");
    }


    public void setTextLocale(Locale locale) {
        throw new RuntimeException("Stub!");
    }


    protected void onConfigurationChanged(Configuration newConfig) {
        throw new RuntimeException("Stub!");
    }

    @ExportedProperty(
            category = "text"
    )
    public float getTextSize() {


        return 5.5f;


    }

    public void setTextSize(float size) {
        throw new RuntimeException("Stub!");
    }

    public void setTextSize(int unit, float size) {
        throw new RuntimeException("Stub!");
    }

    public float getTextScaleX() {
        throw new RuntimeException("Stub!");
    }

    public void setTextScaleX(float size) {
        throw new RuntimeException("Stub!");
    }

    public void setTypeface(Typeface tf) {
        throw new RuntimeException("Stub!");
    }

    public Typeface getTypeface() {
        throw new RuntimeException("Stub!");
    }

    public void setElegantTextHeight(boolean elegant) {
        throw new RuntimeException("Stub!");
    }

    public float getLetterSpacing() {
        throw new RuntimeException("Stub!");
    }

    public void setLetterSpacing(float letterSpacing) {
        throw new RuntimeException("Stub!");
    }

    public String getFontFeatureSettings() {
        throw new RuntimeException("Stub!");
    }

    public void setBreakStrategy(int breakStrategy) {
        throw new RuntimeException("Stub!");
    }

    public int getBreakStrategy() {
        throw new RuntimeException("Stub!");
    }

    public void setHyphenationFrequency(int hyphenationFrequency) {
        throw new RuntimeException("Stub!");
    }

    public int getHyphenationFrequency() {
        throw new RuntimeException("Stub!");
    }

    public void setFontFeatureSettings(String fontFeatureSettings) {
        throw new RuntimeException("Stub!");
    }

    public void setTextColor(int color) {
        throw new RuntimeException("Stub!");
    }

    public void setTextColor(ColorStateList colors) {
        throw new RuntimeException("Stub!");
    }

    public final ColorStateList getTextColors() {
        throw new RuntimeException("Stub!");
    }

    public final int getCurrentTextColor() {
        throw new RuntimeException("Stub!");
    }

    public void setHighlightColor(int color) {
        throw new RuntimeException("Stub!");
    }

    public int getHighlightColor() {
        throw new RuntimeException("Stub!");
    }

    public final void setShowSoftInputOnFocus(boolean show) {
        throw new RuntimeException("Stub!");
    }

    public final boolean getShowSoftInputOnFocus() {
        throw new RuntimeException("Stub!");
    }

    public void setShadowLayer(float radius, float dx, float dy, int color) {
        throw new RuntimeException("Stub!");
    }

    public float getShadowRadius() {
        throw new RuntimeException("Stub!");
    }

    public float getShadowDx() {
        throw new RuntimeException("Stub!");
    }

    public float getShadowDy() {
        throw new RuntimeException("Stub!");
    }

    public int getShadowColor() {
        throw new RuntimeException("Stub!");
    }

    public TextPaint getPaint() {
        throw new RuntimeException("Stub!");
    }

    public final void setAutoLinkMask(int mask) {
        throw new RuntimeException("Stub!");
    }

    public final void setLinksClickable(boolean whether) {
        throw new RuntimeException("Stub!");
    }

    public final boolean getLinksClickable() {
        throw new RuntimeException("Stub!");
    }

    public URLSpan[] getUrls() {
        throw new RuntimeException("Stub!");
    }

    public final void setHintTextColor(int color) {
        throw new RuntimeException("Stub!");
    }

    public final void setHintTextColor(ColorStateList colors) {
        throw new RuntimeException("Stub!");
    }

    public final ColorStateList getHintTextColors() {
        throw new RuntimeException("Stub!");
    }

    public final int getCurrentHintTextColor() {
        throw new RuntimeException("Stub!");
    }

    public final void setLinkTextColor(int color) {
        throw new RuntimeException("Stub!");
    }

    public final void setLinkTextColor(ColorStateList colors) {
        throw new RuntimeException("Stub!");
    }

    public final ColorStateList getLinkTextColors() {
        throw new RuntimeException("Stub!");
    }

    public void setGravity(int gravity) {
        throw new RuntimeException("Stub!");
    }

    public int getGravity() {
        throw new RuntimeException("Stub!");
    }

    public int getPaintFlags() {
        throw new RuntimeException("Stub!");
    }

    public void setPaintFlags(int flags) {
        throw new RuntimeException("Stub!");
    }

    public void setHorizontallyScrolling(boolean whether) {
        throw new RuntimeException("Stub!");
    }

    public void setMinLines(int minlines) {
        throw new RuntimeException("Stub!");
    }

    public int getMinLines() {
        throw new RuntimeException("Stub!");
    }

    public void setMinHeight(int minHeight) {
        throw new RuntimeException("Stub!");
    }

    public int getMinHeight() {
        throw new RuntimeException("Stub!");
    }

    public void setMaxLines(int maxlines) {
        throw new RuntimeException("Stub!");
    }

    public int getMaxLines() {
        throw new RuntimeException("Stub!");
    }

    public void setMaxHeight(int maxHeight) {
        throw new RuntimeException("Stub!");
    }

    public int getMaxHeight() {
        throw new RuntimeException("Stub!");
    }

    public void setLines(int lines) {
        throw new RuntimeException("Stub!");
    }

    public void setHeight(int pixels) {
        throw new RuntimeException("Stub!");
    }

    public void setMinEms(int minems) {
        throw new RuntimeException("Stub!");
    }

    public int getMinEms() {
        throw new RuntimeException("Stub!");
    }

    public void setMinWidth(int minpixels) {
        throw new RuntimeException("Stub!");
    }

    public int getMinWidth() {
        throw new RuntimeException("Stub!");
    }

    public void setMaxEms(int maxems) {
        throw new RuntimeException("Stub!");
    }

    public int getMaxEms() {
        throw new RuntimeException("Stub!");
    }

    public void setMaxWidth(int maxpixels) {
        throw new RuntimeException("Stub!");
    }

    public int getMaxWidth() {
        throw new RuntimeException("Stub!");
    }

    public void setEms(int ems) {
        throw new RuntimeException("Stub!");
    }

    public void setWidth(int pixels) {
        throw new RuntimeException("Stub!");
    }

    public void setLineSpacing(float add, float mult) {
        throw new RuntimeException("Stub!");
    }

    public float getLineSpacingMultiplier() {
        throw new RuntimeException("Stub!");
    }

    public float getLineSpacingExtra() {
        throw new RuntimeException("Stub!");
    }

    public final void append(CharSequence text) {
        throw new RuntimeException("Stub!");
    }

    public void append(CharSequence text, int start, int end) {
        throw new RuntimeException("Stub!");
    }


    public void setFreezesText(boolean freezesText) {
        throw new RuntimeException("Stub!");
    }

    public boolean getFreezesText() {
        throw new RuntimeException("Stub!");
    }

    public final void setEditableFactory(Factory factory) {
        throw new RuntimeException("Stub!");
    }

    public final void setSpannableFactory(android.text.Spannable.Factory factory) {
        throw new RuntimeException("Stub!");
    }

    public final void setText(CharSequence text) {
        throw new RuntimeException("Stub!");
    }

    public final void setTextKeepState(CharSequence text) {
        throw new RuntimeException("Stub!");
    }

    public void setText(CharSequence text, TextView.BufferType type) {
        throw new RuntimeException("Stub!");
    }

    public final void setText(char[] text, int start, int len) {
        throw new RuntimeException("Stub!");
    }

    public final void setTextKeepState(CharSequence text, TextView.BufferType type) {
        throw new RuntimeException("Stub!");
    }

    public final void setText(int resid) {
        throw new RuntimeException("Stub!");
    }

    public final void setText(int resid, TextView.BufferType type) {
        throw new RuntimeException("Stub!");
    }

    public final void setHint(CharSequence hint) {
        throw new RuntimeException("Stub!");
    }

    public final void setHint(int resid) {
        throw new RuntimeException("Stub!");
    }

    @CapturedViewProperty
    public CharSequence getHint() {
        throw new RuntimeException("Stub!");
    }

    public void setInputType(int type) {
        throw new RuntimeException("Stub!");
    }

    public void setRawInputType(int type) {
        throw new RuntimeException("Stub!");
    }

    public int getInputType() {
        throw new RuntimeException("Stub!");
    }

    public void setImeOptions(int imeOptions) {
        throw new RuntimeException("Stub!");
    }

    public int getImeOptions() {
        throw new RuntimeException("Stub!");
    }

    public void setImeActionLabel(CharSequence label, int actionId) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getImeActionLabel() {
        throw new RuntimeException("Stub!");
    }

    public int getImeActionId() {
        throw new RuntimeException("Stub!");
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener l) {
        throw new RuntimeException("Stub!");
    }

    public void onEditorAction(int actionCode) {
        throw new RuntimeException("Stub!");
    }

    public void setPrivateImeOptions(String type) {
        throw new RuntimeException("Stub!");
    }

    public String getPrivateImeOptions() {
        throw new RuntimeException("Stub!");
    }

    public void setInputExtras(int xmlResId) throws XmlPullParserException, IOException {
        throw new RuntimeException("Stub!");
    }

    public Bundle getInputExtras(boolean create) {
        throw new RuntimeException("Stub!");
    }


    public CharSequence getError() {
        throw new RuntimeException("Stub!");
    }

    public void setError(CharSequence error) {
        throw new RuntimeException("Stub!");
    }

    public void setError(CharSequence error, Drawable icon) {
        throw new RuntimeException("Stub!");
    }

    protected boolean setFrame(int l, int t, int r, int b) {
        throw new RuntimeException("Stub!");
    }

    public void setFilters(InputFilter[] filters) {
        throw new RuntimeException("Stub!");
    }

    public InputFilter[] getFilters() {
        throw new RuntimeException("Stub!");
    }

    public boolean onPreDraw() {
        throw new RuntimeException("Stub!");
    }


    public void onScreenStateChanged(int screenState) {
        throw new RuntimeException("Stub!");
    }

    protected boolean isPaddingOffsetRequired() {
        throw new RuntimeException("Stub!");
    }

    protected int getLeftPaddingOffset() {
        throw new RuntimeException("Stub!");
    }

    protected int getTopPaddingOffset() {
        throw new RuntimeException("Stub!");
    }

    protected int getBottomPaddingOffset() {
        throw new RuntimeException("Stub!");
    }

    protected int getRightPaddingOffset() {
        throw new RuntimeException("Stub!");
    }


    public void invalidateDrawable(Drawable drawable) {
        throw new RuntimeException("Stub!");
    }

    public boolean hasOverlappingRendering() {
        throw new RuntimeException("Stub!");
    }

    public boolean isTextSelectable() {
        throw new RuntimeException("Stub!");
    }

    public void setTextIsSelectable(boolean selectable) {
        throw new RuntimeException("Stub!");
    }

    protected int[] onCreateDrawableState(int extraSpace) {
        throw new RuntimeException("Stub!");
    }

    protected void onDraw(Canvas canvas) {
        throw new RuntimeException("Stub!");
    }

    public void getFocusedRect(Rect r) {
        throw new RuntimeException("Stub!");
    }

    public int getLineCount() {
        throw new RuntimeException("Stub!");
    }

    public int getLineBounds(int line, Rect bounds) {
        throw new RuntimeException("Stub!");
    }

    public int getBaseline() {
        throw new RuntimeException("Stub!");
    }


    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    public boolean onCheckIsTextEditor() {
        throw new RuntimeException("Stub!");
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        throw new RuntimeException("Stub!");
    }

    public boolean extractText(ExtractedTextRequest request, ExtractedText outText) {
        throw new RuntimeException("Stub!");
    }

    public void setExtractedText(ExtractedText text) {
        throw new RuntimeException("Stub!");
    }

    public void onCommitCompletion(CompletionInfo text) {
        throw new RuntimeException("Stub!");
    }

    public void onCommitCorrection(CorrectionInfo info) {
        throw new RuntimeException("Stub!");
    }

    public void beginBatchEdit() {
        throw new RuntimeException("Stub!");
    }

    public void endBatchEdit() {
        throw new RuntimeException("Stub!");
    }

    public void onBeginBatchEdit() {
        throw new RuntimeException("Stub!");
    }

    public void onEndBatchEdit() {
        throw new RuntimeException("Stub!");
    }

    public boolean onPrivateIMECommand(String action, Bundle data) {
        throw new RuntimeException("Stub!");
    }

    public void setIncludeFontPadding(boolean includepad) {
        throw new RuntimeException("Stub!");
    }

    public boolean getIncludeFontPadding() {
        throw new RuntimeException("Stub!");
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        throw new RuntimeException("Stub!");
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        throw new RuntimeException("Stub!");
    }

    public boolean bringPointIntoView(int offset) {
        throw new RuntimeException("Stub!");
    }

    public boolean moveCursorToVisibleOffset() {
        throw new RuntimeException("Stub!");
    }

    public void computeScroll() {
        throw new RuntimeException("Stub!");
    }

    public void debug(int depth) {
        throw new RuntimeException("Stub!");
    }

    @ExportedProperty(
            category = "text"
    )
    public int getSelectionStart() {
        throw new RuntimeException("Stub!");
    }

    @ExportedProperty(
            category = "text"
    )
    public int getSelectionEnd() {
        throw new RuntimeException("Stub!");
    }

    public boolean hasSelection() {
        throw new RuntimeException("Stub!");
    }

    public void setSingleLine() {
        throw new RuntimeException("Stub!");
    }

    public void setAllCaps(boolean allCaps) {
        throw new RuntimeException("Stub!");
    }

    public void setSingleLine(boolean singleLine) {
        throw new RuntimeException("Stub!");
    }

    public void setEllipsize(TruncateAt where) {
        throw new RuntimeException("Stub!");
    }

    public void setMarqueeRepeatLimit(int marqueeLimit) {
        throw new RuntimeException("Stub!");
    }

    public int getMarqueeRepeatLimit() {
        throw new RuntimeException("Stub!");
    }

    @ExportedProperty
    public TruncateAt getEllipsize() {
        throw new RuntimeException("Stub!");
    }

    public void setSelectAllOnFocus(boolean selectAllOnFocus) {
        throw new RuntimeException("Stub!");
    }

    public void setCursorVisible(boolean visible) {
        throw new RuntimeException("Stub!");
    }

    public boolean isCursorVisible() {
        throw new RuntimeException("Stub!");
    }

    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        throw new RuntimeException("Stub!");
    }

    protected void onSelectionChanged(int selStart, int selEnd) {
        throw new RuntimeException("Stub!");
    }

    public void addTextChangedListener(TextWatcher watcher) {
        throw new RuntimeException("Stub!");
    }

    public void removeTextChangedListener(TextWatcher watcher) {
        throw new RuntimeException("Stub!");
    }


    public void onWindowFocusChanged(boolean hasWindowFocus) {
        throw new RuntimeException("Stub!");
    }

    protected void onVisibilityChanged(View changedView, int visibility) {
        throw new RuntimeException("Stub!");
    }

    public void clearComposingText() {
        throw new RuntimeException("Stub!");
    }

    public void setSelected(boolean selected) {
        throw new RuntimeException("Stub!");
    }

    public boolean onTouchEvent(MotionEvent event) {
        throw new RuntimeException("Stub!");
    }

    public boolean onGenericMotionEvent(MotionEvent event) {
        throw new RuntimeException("Stub!");
    }

    protected void onCreateContextMenu(ContextMenu menu) {
        throw new RuntimeException("Stub!");
    }

    public boolean showContextMenu() {
        throw new RuntimeException("Stub!");
    }

    public boolean showContextMenu(float x, float y) {
        throw new RuntimeException("Stub!");
    }

    public boolean didTouchFocusSelect() {
        throw new RuntimeException("Stub!");
    }

    public void cancelLongPress() {
        throw new RuntimeException("Stub!");
    }

    public boolean onTrackballEvent(MotionEvent event) {
        throw new RuntimeException("Stub!");
    }

    public void setScroller(Scroller s) {
        throw new RuntimeException("Stub!");
    }

    protected float getLeftFadingEdgeStrength() {
        throw new RuntimeException("Stub!");
    }

    protected float getRightFadingEdgeStrength() {
        throw new RuntimeException("Stub!");
    }

    protected int computeHorizontalScrollRange() {
        throw new RuntimeException("Stub!");
    }

    protected int computeVerticalScrollRange() {
        throw new RuntimeException("Stub!");
    }

    protected int computeVerticalScrollExtent() {
        throw new RuntimeException("Stub!");
    }

    public void findViewsWithText(ArrayList<View> outViews, CharSequence searched, int flags) {
        throw new RuntimeException("Stub!");
    }

    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    public CharSequence getAccessibilityClassName() {
        throw new RuntimeException("Stub!");
    }

    public void onProvideStructure(ViewStructure structure) {
        throw new RuntimeException("Stub!");
    }

    public boolean isInputMethodTarget() {
        throw new RuntimeException("Stub!");
    }

    public boolean onTextContextMenuItem(int id) {
        throw new RuntimeException("Stub!");
    }

    public boolean performLongClick() {
        throw new RuntimeException("Stub!");
    }

    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        throw new RuntimeException("Stub!");
    }

    public boolean isSuggestionsEnabled() {
        throw new RuntimeException("Stub!");
    }

    public void setCustomSelectionActionModeCallback(Callback actionModeCallback) {
        throw new RuntimeException("Stub!");
    }

    public Callback getCustomSelectionActionModeCallback() {
        throw new RuntimeException("Stub!");
    }

    public void setCustomInsertionActionModeCallback(Callback actionModeCallback) {
        throw new RuntimeException("Stub!");
    }

    public Callback getCustomInsertionActionModeCallback() {
        throw new RuntimeException("Stub!");
    }

    public int getOffsetForPosition(float x, float y) {
        throw new RuntimeException("Stub!");
    }

    public boolean onDragEvent(DragEvent event) {
        throw new RuntimeException("Stub!");
    }

    public void onRtlPropertiesChanged(int layoutDirection) {
        throw new RuntimeException("Stub!");
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<TextView.SavedState> CREATOR = null;

        SavedState() {
            super((Parcelable) null);
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel out, int flags) {
            throw new RuntimeException("Stub!");
        }

        public String toString() {
            throw new RuntimeException("Stub!");
        }
    }

    public static enum BufferType {
        EDITABLE,
        NORMAL,
        SPANNABLE;

        private BufferType() {
        }
    }

    public interface OnEditorActionListener {
        boolean onEditorAction(TextView var1, int var2, KeyEvent var3);
    }
}
