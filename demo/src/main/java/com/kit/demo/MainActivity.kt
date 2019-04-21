package com.kit.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.DecelerateInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(myTab){
//            setTabColor(ContextCompat.getColor(this@MainActivity,R.color.accent_material_dark))
//            setTabBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.material_blue_grey_900))
//            setLabelTextFont(R.font.montserrat_medium)
//            setLabelTextSize(20f)
//            setLabelTextColor(ContextCompat.getColor(this@MainActivity,R.color.material_grey_600))
            setTabAnimationInterpolator(DecelerateInterpolator())
//            setTabAnimationDuration(300L)
//            setOnTabSelectedListener { position ->
//                Log.v("Main", position.toString())
//            }
        }
    }
}
