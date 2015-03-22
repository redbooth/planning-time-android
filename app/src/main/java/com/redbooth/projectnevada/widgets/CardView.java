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

import com.redbooth.projectnevada.R;
import com.redbooth.projectnevada.model.CardModel;

public class CardView extends FrameLayout {

    //region "PRIVATE VARIABLES"

    private final static int FLIP_ANIMATION_DURATION = 200;

    private CardModel.CardStatus cardStatus;
    private CardModel card;
    private ImageView upwardView;
    private ImageView downwardView;
    private Animator currentAnimator;

    //endregion

    //region "LISTENERS"

    private OnClickListener onCardClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (cardStatus == CardModel.CardStatus.UPWARDS) {
                hideCard();
            } else {
                revealCard();
            }
        }
    };

    //endregion

    //region "PUBLIC METHODS"

    public void revealCard() {
        if (cardStatus == CardModel.CardStatus.DOWNWARDS) {
            this.cardStatus = CardModel.CardStatus.UPWARDS;
            startDiscoverCardAnimation();
        }
    }

    public void hideCard() {
        if (cardStatus == CardModel.CardStatus.UPWARDS) {
            this.cardStatus = CardModel.CardStatus.DOWNWARDS;
            startHideCardAnimation();
        }
    }

    public void setCard(CardModel card) {
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

    private CardModel getCard() {
        if (!isInEditMode()) {
            return card;
        } else {
            return new CardModel.Builder()
                                    .setUpwardResourceId(R.drawable.card_upward_ping_pong)
                                    .setDownwardResourceId(R.drawable.card_downward)
                                    .build();
        }
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
        animatorSet.setDuration(FLIP_ANIMATION_DURATION);
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
        animatorSet.setDuration(FLIP_ANIMATION_DURATION);
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
        if (card.getStatus() == CardModel.CardStatus.UPWARDS) {
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
