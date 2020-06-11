package com.example.constraintlayoutexample

import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_shopping.*

@RequiresApi(Build.VERSION_CODES.KITKAT)
class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        setupAnimations()
    }

    private fun setupAnimations() {
        askSize.setOnClickListener {
            updateConstraints(R.layout.activity_shopping_alt)
            askSize.setText("ADD TO CART - 1234 INR")
        }

        close.setOnClickListener {
            updateConstraints(R.layout.activity_shopping)
            askSize.setText("SELECT SIZE")
        }
    }


    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(shoppingRoot)
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(shoppingRoot, transition)
    }
}