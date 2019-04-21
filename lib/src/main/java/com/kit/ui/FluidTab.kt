package com.kit.ui

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.annotation.ColorInt
import android.support.annotation.FontRes
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.FrameLayout
import android.widget.TextView

internal typealias TabSelectListener = (Int)-> Unit

class FluidTab @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener

{
    override fun onClick(v: View) {
        when (v.id){
            R.id.textLeft -> {
                moveLeft()
                onTabSelectedListener?.invoke(0)
            }
            R.id.textRight -> {
                moveRight()
                onTabSelectedListener?.invoke(1)
            }
        }
    }

    private var constraintSetLeft: ConstraintSet? = null
    private var constraintSetRight : ConstraintSet? = null
    private var textLeft: TextView? = null
    private var textRight: TextView? = null
    private var bg: View? = null
    private var tab: View? = null
    private var onTabSelectedListener: TabSelectListener? = null
    var view: ConstraintLayout?=null

    // configs
    private var isAnimated = true
    private var bgColor = ContextCompat.getColor(context, R.color.gray)
    private var tabColor =  ContextCompat.getColor(context, R.color.yellow)
    private var textColor = ContextCompat.getColor(context, R.color.white)

    private var leftText:String?=null
    private var rightText:String?=null
    private var textSize: Float = 0f
    private var textFont = 0

    private var mInterpolator: Interpolator = DecelerateInterpolator()
    private var animationDuration: Long = 300L





    init {
        initAttrs()
        view = LayoutInflater.from(context).inflate(R.layout.tab_left, this, false) as ConstraintLayout
        addView(view)
        initViews()
        initConstraintSet()
        handleClicks()
    }

    private fun initViews() {
        textLeft = view?.findViewById(R.id.textLeft)
        textRight = view?.findViewById(R.id.textRight)
        tab = view?.findViewById(R.id.indicatorView)
        bg = view?.findViewById(R.id.bgView)
        textLeft?.text = leftText
        textRight?.text = rightText
        textLeft?.textSize = textSize
        textRight?.textSize = textSize
        textLeft?.setTextColor(textColor)
        textRight?.setTextColor(textColor)
        if (textFont!=0){
            if (!isInEditMode){
                textLeft?.typeface = ResourcesCompat.getFont(context, textFont)
                textRight?.typeface = ResourcesCompat.getFont(context, textFont)
            }
        }

        setTabBackgroundColor(bgColor)
        setTabColor(tabColor)
    }

     fun setTabBackgroundColor(@ColorInt color: Int){
        val bgDrawable = bg?.background?.current
        if (bgDrawable is GradientDrawable){
            bgDrawable.setColor(color)
        }
        invalidate()
    }

     fun setTabColor(@ColorInt color: Int){
        val tabDrawable = tab?.background?.current
        if (tabDrawable is GradientDrawable){
            tabDrawable.setColor(color)
        }
//        invalidate()
    }

    fun setLabelTextSize(textSize:Float){
        this.textSize = textSize
        textLeft?.textSize = textSize
        textRight?.textSize = textSize
//        invalidate()
    }

    fun setLabelTextColor(@ColorInt color: Int){
        this.textColor = color
        textLeft?.setTextColor(textColor)
        textRight?.setTextColor(textColor)
    }

    fun setLabelTextFont(@FontRes font: Int){
        this.textFont = font
        textLeft?.typeface = ResourcesCompat.getFont(context, font)
        textRight?.typeface = ResourcesCompat.getFont(context, font)
    }

    fun setTabAnimationDuration(duration: Long){
        this.animationDuration = duration
    }

    fun setTabAnimationInterpolator(interpolator: Interpolator){
        this.mInterpolator = interpolator
    }

    private fun initAttrs() {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FluidTab,
            0, 0).apply {

            try {
                isAnimated = getBoolean(R.styleable.FluidTab_isAnimated, false)
                tabColor = getColor(R.styleable.FluidTab_tabColor, tabColor)
                bgColor = getColor(R.styleable.FluidTab_tabBackgroundColor, bgColor)
                leftText = getString(R.styleable.FluidTab_leftLabelText)
                rightText = getString(R.styleable.FluidTab_rightLabelText)
                textColor = getColor(R.styleable.FluidTab_textColor, textColor)
                textFont = getResourceId(R.styleable.FluidTab_textFont, 0)
                textSize = getDimension(R.styleable.FluidTab_textSize, 20f)
                animationDuration = getInt(R.styleable.FluidTab_animationDuration, 300).toLong()
            } finally {
                recycle()
            }
        }
    }



    fun setOnTabSelectedListener(onTabSelectListener:TabSelectListener){
        this.onTabSelectedListener = onTabSelectListener
    }

    private fun handleClicks() {
        textLeft?.setOnClickListener(this)
        textRight?.setOnClickListener(this)
    }

    private fun initConstraintSet() {
        constraintSetRight = ConstraintSet().apply { clone(context, R.layout.tab_right) }
        constraintSetLeft = ConstraintSet().apply { clone(view) }
    }

    private fun moveLeft() {
        if (isAnimated){
            delayTransition()
        }
        setLeft()
    }

    private fun moveRight(){
        if (isAnimated){
            delayTransition()
        }
        setRight()
    }

    private fun delayTransition(){
        TransitionManager.beginDelayedTransition(view, AutoTransition().apply {
            interpolator = mInterpolator
            duration = animationDuration
        })
    }


    private fun setLeft(){
        constraintSetLeft?.applyTo(view)
    }

    private fun setRight(){
        constraintSetRight?.applyTo(view)
    }

}