# FluidTab
:last_quarter_moon:An animated two-state tab built by constraint layout

![Alt Text](https://media.giphy.com/media/QBeIDLwZdRUw71E4sa/giphy.gif)


build.gradle(Project)
~~~~
allprojects {
    repositories {
   	...
        maven { url 'https://jitpack.io' }

    }
}
~~~~
build.gradle(Module)
~~~~
// Androidx
implementation 'com.github.kdreamix:FluidTab:1.0.1'

// Support lib
implementation 'com.github.kdreamix:FluidTab:1.0.1-support'

~~~~

Usage:
Xml
~~~~
<com.kit.ui.FluidTab android:layout_width="0dp"
                         android:layout_height="50dp"
                         android:layout_marginTop="16dp"
                         app:isAnimated="true"
                         app:animationDuration="300"
                         app:leftLabelText="Hello"
                         app:rightLabelText="12345"
                         app:tabColor="@color/yellow"
                         app:tabBackgroundColor="@color/colorPrimary"
                         app:textFont="@font/montserrat_medium"
                         android:id="@+id/myTab"
                    />
~~~~

Programmtically
~~~~
with(myTab){
            setTabColor(ContextCompat.getColor(this@MainActivity,R.color.accent_material_dark))
            setTabBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.material_blue_grey_900))
            setLabelTextFont(R.font.montserrat_medium)
            setLabelTextSize(20f)
            setLabelTextColor(ContextCompat.getColor(this@MainActivity,R.color.material_grey_600))
            setTabAnimationInterpolator(OvershootInterpolator())
            setTabAnimationDuration(300L)
            setOnTabSelectedListener { position ->
                Log.v("Main", position.toString())
            }
}

~~~~
