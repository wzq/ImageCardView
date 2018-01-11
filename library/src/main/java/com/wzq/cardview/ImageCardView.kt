package com.wzq.cardview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by wzq on 2017/5/18.
 */
class ImageCardView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr),
        Animation.AnimationListener {

    constructor(context: Context?) : this(context, null, 0)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    var lightTime = 700

    var showBottom = false

    var isLight = true

    var bgResId = R.drawable.bg_zoom_img

    private val scaleBig = AnimationUtils.loadAnimation(context, R.anim.anim_scale_big)!!

    private val scaleSmall = AnimationUtils.loadAnimation(context, R.anim.anim_scale_small)!!

    private val light by lazy { findViewById<ImageView>(R.id.light) }

    val mainImage by lazy { findViewById<ImageView>(R.id.ic_img) }

    val title by lazy { findViewById<TextView>(R.id.ic_text) }

    init {
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ImageCardView)
        bgResId = typedArray.getResourceId(R.styleable.ImageCardView_ic_border, R.drawable.bg_zoom_img)
        showBottom = typedArray.getBoolean(R.styleable.ImageCardView_ic_showBottom, false)
        lightTime = typedArray.getInteger(R.styleable.ImageCardView_ic_light_duration, 700)
        isLight = typedArray.getBoolean(R.styleable.ImageCardView_ic_is_light, true)
        val defaultImg = typedArray.getResourceId(R.styleable.ImageCardView_ic_img, 0)
        typedArray.recycle()

        setBackgroundResource(bgResId)
        isFocusable = true
        scaleBig.setAnimationListener(this)
        LayoutInflater.from(context).inflate(R.layout.view_imagecard, this)
        if (defaultImg != 0) mainImage.setImageResource(defaultImg)
    }

    override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        scaleAnim(gainFocus)
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
    }


    fun setTitle(s: String){
        title.text = s
    }


    fun scaleAnim(b: Boolean) {
        isSelected = b
        startAnimation(if (b) scaleBig else scaleSmall)
        title.visibility = if (b&&showBottom) View.VISIBLE else View.GONE
        if (!b) {
            light.visibility = View.GONE
        }
    }

    private fun move(view: View) {
        view.visibility = View.VISIBLE
        val w = width
        val valueAnimator = ValueAnimator.ofFloat((-w - 30).toFloat(), (w + 30).toFloat())
        valueAnimator.addUpdateListener {
            val x = valueAnimator.animatedValue as Float
            view.translationX = x
            val alpha = x / w
            val a1 = if (alpha > 0) 1 - alpha else 1 + alpha
            val a2 = (a1 / 2 + 0.5).toFloat()
            view.alpha = a2
        }
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        val d = w / 355 - 1
        val ff = lightTime * (d * 0.25f + 1)
        valueAnimator.duration = ff.toLong()
        valueAnimator.start()
    }


    override fun onAnimationEnd(p0: Animation?) {
        if (isLight && isFocused) move(light)
    }

    override fun onAnimationStart(p0: Animation?) {}

    override fun onAnimationRepeat(p0: Animation?) {}
}