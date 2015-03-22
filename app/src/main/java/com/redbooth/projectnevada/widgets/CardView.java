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

    private final static int FLIP_ANIMATION_DURATION = 300;

    private CardModel.CardStatus cardStatus;
    private CardModel card;
    private ImageView upwardView;
    private ImageView downwardView;

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
        upwardView
            .animate()
                .setDuration(FLIP_ANIMATION_DURATION)
                .rotationY(-180f)
                .alpha(0f)
                    .start();

        downwardView
            .animate()
                .setDuration(FLIP_ANIMATION_DURATION)
                .rotationY(-180f)
                .alpha(1f)
                    .start();
    }

    private void startDiscoverCardAnimation() {
        downwardView
                .animate()
                .setDuration(FLIP_ANIMATION_DURATION)
                .rotationY(0f)
                .alpha(0f)
                    .start();

        upwardView
                .animate()
                .setDuration(FLIP_ANIMATION_DURATION)
                .rotationY(0f)
                .alpha(1f)
                    .start();
    }

    private void initializeSelfView() {
        setClickable(true);
        setOnClickListener(onCardClick);
        FrameLayout.LayoutParams viewParams =
                                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT);
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
