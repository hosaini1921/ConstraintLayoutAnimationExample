package com.example.constraintlayoutexample

import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_carousal.*

@RequiresApi(Build.VERSION_CODES.KITKAT)
class CarouselActivity : AppCompatActivity() {

    private var selectedView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousal)
        setupAnimations()
    }

    private fun setupAnimations() {
        selectedView = null

        root.setOnClickListener {
            toDefault()
        }

        javaImg.setOnClickListener {
            if (selectedView != javaImg) {
                updateConstraints(R.layout.activity_carousel_java)
                selectedView = javaImg
            } else
                toDefault()
        }

        kotlinImg.setOnClickListener {
            if (selectedView != kotlinImg) {
                updateConstraints(R.layout.activity_carousel_kotlin)
                selectedView = kotlinImg
            } else
                toDefault()
        }
    }

    private fun toDefault() {
        if (selectedView != null) {
            updateConstraints(R.layout.activity_carousal)
            selectedView = null
        }
    }

    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(root)
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(root, transition)
    }
}