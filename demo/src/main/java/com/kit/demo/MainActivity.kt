package com.kit.demo

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(myTab){
            setTabColor(ContextCompat.getColor(this@MainActivity,R.color.accent_material_dark))
            setTabBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.material_blue_grey_900))
            setLabelTextFont(R.font.montserrat_medium)
            setLabelTextSize(20f)
            setLabelTextColor(ContextCompat.getColor(this@MainActivity,R.color.material_grey_600))
            setTabAnimationInterpolator(OvershootInterpolator())
            setTabAnimationDuration(300L)
        }
    }
}
