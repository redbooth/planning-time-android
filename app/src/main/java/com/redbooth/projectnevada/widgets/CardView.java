package com.redbooth.projectnevada.widgets;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.redbooth.projectnevada.model.CardViewModel;

public class CardView extends FrameLayout {

    public interface OnCardStatusChangeListener {
        void onCardStatusChange(CardView view, CardViewModel card, CardViewModel.CardStatus newStatus);
    }

    //region "PRIVATE VARIABLES"

    private final static int FLIP_ANIMATION_DURATION = 200;

    private CardViewModel.CardStatus cardStatus;
    private CardViewModel card;
    private ImageView upwardView;
    private ImageView downwardView;
    private Animator currentAnimator;
    private OnCardStatusChangeListener listener;
    private int animationDuration = FLIP_ANIMATION_DURATION;

    //endregion

    //region "LISTENERS"

    private OnClickListener onCardClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (cardStatus == CardViewModel.CardStatus.UPWARDS) {
                hideCard();
            } else {
                revealCard();
            }
        }
    };

    //endregion

    //region "PUBLIC METHODS"

    public void setOnCardViewStatusChangeListener(OnCardStatusChangeListener listener) {
        this.listener = listener;
    }

    public void revealCard() {
        if (cardStatus == CardViewModel.CardStatus.DOWNWARDS) {
            this.cardStatus = CardViewModel.CardStatus.UPWARDS;
            startDiscoverCardAnimation();
            if (listener != null) {
                listener.onCardStatusChange(this, card, cardStatus);
            }
        }
    }

    public void hideCard() {
        if (cardStatus == CardViewModel.CardStatus.UPWARDS) {
            this.cardStatus = CardViewModel.CardStatus.DOWNWARDS;
            startHideCardAnimation();
            if (listener != null) {
                listener.onCardStatusChange(this, card, cardStatus);
            }
        }
    }

    public void setCard(CardViewModel card) {
        this.card = card;
        this.cardStatus = card.getStatus();
        renderCard();
    }

    //endregion

    //region "CONSTRUCTORS"

    public CardView(Context context) {
        super(context);
        initializeSelfView();
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeSelfView();
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeSelfView();
    }

    //endregion

    //region "LIFE CYCLE"

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        renderCard();
    }

    //endregion

    //region "PRIVATE METHODS"

    private CardViewModel getCard() {
        if (!isInEditMode()) {
            return card;
        }
        return null;
    }

    private void startHideCardAnimation() {
        if (currentAnimator != null && currentAnimator.isRunning()) {
            currentAnimator.cancel();
        }
        ObjectAnimator rotationCardHideStepOne = ObjectAnimator.ofFloat(upwardView, "rotationY", 0f, -90f);
        ObjectAnimator alphaCardHideStepOne = ObjectAnimator.ofFloat(upwardView, "alpha", 1f, .5f);
        ObjectAnimator scaleXCardHideStepOne = ObjectAnimator.ofFloat(upwardView, "scaleX", 1f, .5f);
        ObjectAnimator scaleYCardHideStepOne = ObjectAnimator.ofFloat(upwardView, "scaleY", 1f, .5f);
        AnimatorSet hideStepOne = new AnimatorSet();
        hideStepOne.playTogether(rotationCardHideStepOne, alphaCardHideStepOne, scaleXCardHideStepOne, scaleYCardHideStepOne);

        ObjectAnimator rotationCardHideStepTwo = ObjectAnimator.ofFloat(upwardView, "rotationY", -90f, -180f);
        ObjectAnimator alphaCardHideStepTwo = ObjectAnimator.ofFloat(upwardView, "alpha", .5f, 0f);
        ObjectAnimator scaleXCardHideStepTwo = ObjectAnimator.ofFloat(upwardView, "scaleX", .5f, 1f);
        ObjectAnimator scaleYCardHideStepTwo = ObjectAnimator.ofFloat(upwardView, "scaleY", .5f, 1f);
        AnimatorSet hideStepTwo = new AnimatorSet();
        hideStepTwo.playTogether(rotationCardHideStepTwo, alphaCardHideStepTwo, scaleXCardHideStepTwo, scaleYCardHideStepTwo);

        ObjectAnimator rotationCardDiscoverStepOne = ObjectAnimator.ofFloat(downwardView, "rotationY", 0f, -90f);
        ObjectAnimator alphaCardDiscoverStepOne = ObjectAnimator.ofFloat(downwardView, "alpha", 0f, .5f);
        ObjectAnimator maximizeScaleXCardHideStepOne = ObjectAnimator.ofFloat(downwardView, "scaleX", 1f, .5f);
        ObjectAnimator maximizeScaleYCardHideStepOne = ObjectAnimator.ofFloat(downwardView, "scaleY", 1f, .5f);
        AnimatorSet discoverStepOne = new AnimatorSet();
        discoverStepOne.playTogether(rotationCardDiscoverStepOne, alphaCardDiscoverStepOne, maximizeScaleXCardHideStepOne, maximizeScaleYCardHideStepOne);

        ObjectAnimator rotationCardDiscoverStepTwo = ObjectAnimator.ofFloat(downwardView, "rotationY", -90f, -180f);
        ObjectAnimator alphaCardDiscoverStepTwo = ObjectAnimator.ofFloat(downwardView, "alpha", .5f, 1f);
        ObjectAnimator maximizeScaleXCardHideStepTwo = ObjectAnimator.ofFloat(downwardView, "scaleX", .5f, 1f);
        ObjectAnimator maximizeScaleYCardHideStepTwo = ObjectAnimator.ofFloat(downwardView, "scaleY", .5f, 1f);
        AnimatorSet discoverStepTwo = new AnimatorSet();
        discoverStepTwo.playTogether(rotationCardDiscoverStepTwo, alphaCardDiscoverStepTwo, maximizeScaleXCardHideStepTwo, maximizeScaleYCardHideStepTwo);

        AnimatorSet animationStepOne = new AnimatorSet();
        animationStepOne.play(hideStepOne).with(discoverStepOne);

        AnimatorSet animationStepTwo = new AnimatorSet();
        animationStepTwo.play(hideStepTwo).with(discoverStepTwo);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(animationDuration);
        animatorSet.play(animationStepTwo).after(animationStepOne);
        animatorSet.start();
        currentAnimator = animatorSet;
    }

    private void startDiscoverCardAnimation() {
        if (currentAnimator != null && currentAnimator.isRunning()) {
            currentAnimator.cancel();
        }
        ObjectAnimator rotationCardHideStepOne = ObjectAnimator.ofFloat(upwardView, "rotationY", -180f, -90f);
        ObjectAnimator alphaCardHideStepOne = ObjectAnimator.ofFloat(upwardView, "alpha", 0f, .5f);
        ObjectAnimator scaleXCardHideStepOne = ObjectAnimator.ofFloat(upwardView, "scaleX", 1f, .5f);
        ObjectAnimator scaleYCardHideStepOne = ObjectAnimator.ofFloat(upwardView, "scaleY", 1f, .5f);
        AnimatorSet hideStepOne = new AnimatorSet();
        hideStepOne.playTogether(rotationCardHideStepOne, alphaCardHideStepOne, scaleXCardHideStepOne, scaleYCardHideStepOne);

        ObjectAnimator rotationCardHideStepTwo = ObjectAnimator.ofFloat(upwardView, "rotationY", -90f, 0f);
        ObjectAnimator alphaCardHideStepTwo = ObjectAnimator.ofFloat(upwardView, "alpha", .5f, 1f);
        ObjectAnimator scaleXCardHideStepTwo = ObjectAnimator.ofFloat(upwardView, "scaleX", .5f, 1f);
        ObjectAnimator scaleYCardHideStepTwo = ObjectAnimator.ofFloat(upwardView, "scaleY", .5f, 1f);
        AnimatorSet hideStepTwo = new AnimatorSet();
        hideStepTwo.playTogether(rotationCardHideStepTwo, alphaCardHideStepTwo, scaleXCardHideStepTwo, scaleYCardHideStepTwo);

        ObjectAnimator rotationCardDiscoverStepOne = ObjectAnimator.ofFloat(downwardView, "rotationY", -180f, -90f);
        ObjectAnimator alphaCardDiscoverStepOne = ObjectAnimator.ofFloat(downwardView, "alpha", 1f, .5f);
        ObjectAnimator maximizeScaleXCardHideStepOne = ObjectAnimator.ofFloat(downwardView, "scaleX", 1f, .5f);
        ObjectAnimator maximizeScaleYCardHideStepOne = ObjectAnimator.ofFloat(downwardView, "scaleY", 1f, .5f);
        AnimatorSet discoverStepOne = new AnimatorSet();
        discoverStepOne.playTogether(rotationCardDiscoverStepOne, alphaCardDiscoverStepOne, maximizeScaleXCardHideStepOne, maximizeScaleYCardHideStepOne);

        ObjectAnimator rotationCardDiscoverStepTwo = ObjectAnimator.ofFloat(downwardView, "rotationY", -90f, 0f);
        ObjectAnimator alphaCardDiscoverStepTwo = ObjectAnimator.ofFloat(downwardView, "alpha", .5f, 0f);
        ObjectAnimator maximizeScaleXCardHideStepTwo = ObjectAnimator.ofFloat(downwardView, "scaleX", .5f, 1f);
        ObjectAnimator maximizeScaleYCardHideStepTwo = ObjectAnimator.ofFloat(downwardView, "scaleY", .5f, 1f);
        AnimatorSet discoverStepTwo = new AnimatorSet();
        discoverStepTwo.playTogether(rotationCardDiscoverStepTwo, alphaCardDiscoverStepTwo, maximizeScaleXCardHideStepTwo, maximizeScaleYCardHideStepTwo);

        AnimatorSet animationStepOne = new AnimatorSet();
        animationStepOne.play(hideStepOne).with(discoverStepOne);

        AnimatorSet animationStepTwo = new AnimatorSet();
        animationStepTwo.play(hideStepTwo).with(discoverStepTwo);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(animationDuration);
        animatorSet.play(animationStepTwo).after(animationStepOne);
        animatorSet.start();
        currentAnimator = animatorSet;
    }

    private void initializeSelfView() {
        setClickable(true);
        setOnClickListener(onCardClick);
        FrameLayout.LayoutParams viewParams =
                                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                ViewGroup.LayoutParams.MATCH_PARENT);
        upwardView = new ImageView(getContext());
        upwardView.setScaleType(ImageView.ScaleType.FIT_XY);
        upwardView.setLayoutParams(viewParams);
        downwardView = new ImageView(getContext());
        downwardView.setScaleType(ImageView.ScaleType.FIT_XY);
        downwardView.setLayoutParams(viewParams);
        this.addView(downwardView);
        this.addView(upwardView);
    }

    private void renderCard() {
        if (getCard() != null) {
            upwardView.setImageDrawable(getUpwardDrawable());
            downwardView.setImageDrawable(getDownwardDrawable());
        }
        if (card.getStatus() == CardViewModel.CardStatus.UPWARDS) {
            upwardView.bringToFront();
        } else {
            downwardView.bringToFront();
        }
    }

    private Drawable getUpwardDrawable() {
        return getDrawable(getCard().getUpwardResourceId());
    }

    private Drawable getDownwardDrawable() {
        return getDrawable(getCard().getDownwardResourceId());
    }

    private Drawable getDrawable(int resourceId) {
        return getResources().getDrawable(resourceId);
    }

    //endregion
}
