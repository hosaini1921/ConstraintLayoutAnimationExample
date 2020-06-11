package com.example.constraintlayoutexample

import android.os.Build
import android.os.Bundle
import android.transition.TransitionManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        addAnimationOperation()
    }

    private fun addAnimationOperation() {
        var set = false
        val constraint1 = ConstraintSet()
        constraint1.clone(this, R.layout.activity_image)
        val constraint2 = ConstraintSet()
        constraint2.clone(this, R.layout.activity_image_alt)

        findViewById<ImageView>(R.id.iv).setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(root)
                val constraint = if (set) constraint1 else constraint2
                constraint.applyTo(root)
                set = !set
            }
        }

    }

}
