package com.example.creditsystem.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.creditsystem.tools.MyApplication
import com.example.creditsystem.R
import kotlinx.android.synthetic.main.activity_register.*

/**
@author chenjinhui
@date : 2020/9/3 23:00
@description:带动画效果的注册activity
 */
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 只有minSdkVersion > 21 才能支持对应的动画效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            showEnterAnimation()
        }
        fab.setOnClickListener(View.OnClickListener {
            animateRevealClose()
        })
    }

    /**
     * 准备进场动画
     */
    private fun showEnterAnimation() {
        val transition =
            TransitionInflater.from(MyApplication.context)
                .inflateTransition(R.transition.fabtransition)
        window.sharedElementEnterTransition = transition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
                cv_add.visibility = View.GONE
            }

            override fun onTransitionEnd(transition: Transition) {
                transition.removeListener(this)
                animateRevealShow()
            }

            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionResume(transition: Transition) {}
        })
    }

    /**
     * CardView动画展示
     */
    fun animateRevealShow() {
        val mAnimator = ViewAnimationUtils.createCircularReveal(
            cv_add,
            cv_add.width / 2,
            0,
            (fab.width / 2).toFloat(),
            cv_add.height.toFloat()
        )
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                cv_add.visibility = View.VISIBLE
                super.onAnimationStart(animation)
            }
        })
        mAnimator.start()
    }

    private fun animateRevealClose() {
        val mAnimator = ViewAnimationUtils.createCircularReveal(
            cv_add,
            cv_add.width / 2,
            0,
            cv_add.height.toFloat(),
            (fab.width / 2).toFloat()
        )
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                cv_add.visibility = View.INVISIBLE
                super.onAnimationEnd(animation)
                fab.setImageResource(R.drawable.plus)
                super@RegisterActivity.onBackPressed()
            }
        })
        mAnimator.start()
    }

    override fun onBackPressed() {
        animateRevealClose()
    }

}