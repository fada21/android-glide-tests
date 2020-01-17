package com.fada21.android.glidetest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fada21.android.glidetest.SpawnFragment.Companion.GlideRequesteWith
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test_spawning_activities_btn.setOnClickListener {
            startActivity(Intent(this, SpawnActivity::class.java))
        }
        test_spawning_fragments_using_activity_lifecycle_btn.setOnClickListener {
            val intent = Intent(this, SpawnFragmentActivity::class.java)
            intent.putExtra(GLIDE_REQUEST_WITH, GlideRequesteWith.ACTIVITY.ordinal)
            startActivity(intent)
        }
        test_spawning_fragments_using_fragment_lifecycle_btn.setOnClickListener {
            val intent = Intent(this, SpawnFragmentActivity::class.java)
            intent.putExtra(GLIDE_REQUEST_WITH, GlideRequesteWith.FRAGMENT.ordinal)
            startActivity(intent)
        }
    }
}
