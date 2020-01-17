package com.fada21.android.glidetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.fada21.android.glidetest.SpawnFragment.Companion.GlideRequesteWith


class SpawnFragmentActivity : AppCompatActivity(R.layout.fragment_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val glideRequesteWith = when (intent.extras?.getInt(GLIDE_REQUEST_WITH) ?: -1) {
            GlideRequesteWith.ACTIVITY.ordinal -> GlideRequesteWith.ACTIVITY
            GlideRequesteWith.FRAGMENT.ordinal -> GlideRequesteWith.FRAGMENT
            else -> throw IllegalStateException("Dev error")
        }
        supportFragmentManager.commit {
            add(R.id.fragment, SpawnFragment(0, glideRequesteWith))
        }
    }

    fun addFragment(page: Int, with: GlideRequesteWith) {
        supportFragmentManager.commit {
            replace(R.id.fragment, SpawnFragment(page, with))
            addToBackStack(null)
        }
    }
}
