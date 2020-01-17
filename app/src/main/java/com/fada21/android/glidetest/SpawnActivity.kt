package com.fada21.android.glidetest

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.images_grid.*

class SpawnActivity : AppCompatActivity(R.layout.images_grid) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val page = intent.extras?.getInt(PAGE) ?: 0
        title = "Activity only, page $page"
        grid.adapter = ImagesAdapter(page, Glide.with(this), seed = 200_000)
        grid.layoutManager = GridLayoutManager(this, 5)
        next_screen.setOnClickListener {
            val intent = Intent(this, SpawnActivity::class.java)
            intent.putExtra(PAGE, page + 1)
            startActivity(intent)
        }
    }
}
