package com.example.tt_bssd5250_hw21

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayout: LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val r = makeButton("red")

        val b = makeButton("blue")

        val redlinearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.DKGRAY)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            addView(r)
        }
        val bluelinearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.GRAY)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            addView(b)
        }

        // Create a ConstraintLayout in which to add the ImageView
        linearLayout = LinearLayoutCompat(this).apply {
//            setBackgroundColor(Color.LTGRAY)
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            // Add the ImageView to the layout.
            addView(redlinearLayout)
            addView(bluelinearLayout)
        }

        // Set the layout as the content view.
        setContentView(linearLayout)
    }

    private fun makeButton(color: String): ImageButton {
        lateinit var button: ImageButton
        if (color == "red") {
            // Instantiate an ImageView and define its properties
            button = ImageButton(this).apply {
                setImageResource(R.drawable.red)
                background = null
                setOnClickListener(View.OnClickListener { view ->
                    val parent = view.parent as? LinearLayoutCompat
                    parent?.addView(makeButton("blue"))
//                    (view.parent as LinearLayoutCompat).addView(makeButton("blue"))
                })
                // set the ImageView bounds to match the Drawable's dimensions
                adjustViewBounds = true
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        } else {
            button = ImageButton(this).apply {
                setImageResource(R.drawable.blue)
                background = null
                setOnClickListener {
                    val parent = it.parent as? LinearLayoutCompat
                    parent?.removeView(it)
//                    (it.parent as LinearLayoutCompat).removeView(it)
                }
                // set the ImageView bounds to match the Drawable's dimensions
                adjustViewBounds = true
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
        return button
    }
}