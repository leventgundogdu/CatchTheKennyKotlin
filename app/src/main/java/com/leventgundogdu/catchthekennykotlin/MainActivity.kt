package com.leventgundogdu.catchthekennykotlin

import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.leventgundogdu.catchthekennykotlin.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var score = 0
        var lastChosenItem : ImageView? = null

        val imageViews = arrayOf(
            binding.imageView, binding.imageView1, binding.imageView2,
            binding.imageView3, binding.imageView4, binding.imageView5,
            binding.imageView6, binding.imageView7, binding.imageView8  )

        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.textView.text = "Time: ${millisUntilFinished / 1000}"

                for (imageView in imageViews) {
                    imageView.visibility = View.INVISIBLE
                }

                var randomElement = imageViews[Random.nextInt(imageViews.size)]

                do {
                    randomElement = imageViews[Random.nextInt(imageViews.size)]
                } while (randomElement == lastChosenItem) //Ensuring that the random selected item is not the same.

                randomElement.visibility = View.VISIBLE
                randomElement.setOnClickListener {
                    score = score + 1
                    binding.scoreTextView.text = "Score: $score"
                }
                lastChosenItem = randomElement

            }

            override fun onFinish() {
                binding.textView.text = "Time: 0"

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Try Again?")
                alert.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        recreate() //This method re-creates the onCreate() method.
                    }

                })
                alert.setNegativeButton("No") { p0, p1 ->
                    Toast.makeText(this@MainActivity, "Good Game", Toast.LENGTH_LONG).show()
                }

                alert.show()

            }

        }.start()

    }



}