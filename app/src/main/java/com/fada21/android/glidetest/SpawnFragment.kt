package com.fada21.android.glidetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.images_grid.view.*

class SpawnFragment : Fragment() {

    var page: Int = 0
    var requestHandle: GlideRequesteWith = GlideRequesteWith.ACTIVITY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt(PAGE) ?: 0
        requestHandle = when (arguments?.getInt(GLIDE_REQUEST_WITH) ?: -1) {
            GlideRequesteWith.ACTIVITY.ordinal -> GlideRequesteWith.ACTIVITY
            GlideRequesteWith.FRAGMENT.ordinal -> GlideRequesteWith.FRAGMENT
            else -> throw IllegalStateException("Dev error")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.images_grid, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val glideRequester = when (requestHandle) {
            GlideRequesteWith.ACTIVITY -> Glide.with(requireActivity())
            GlideRequesteWith.FRAGMENT -> Glide.with(this)
        }
        view.grid.adapter = ImagesAdapter(page, glideRequester, seed = if (requestHandle == GlideRequesteWith.ACTIVITY) 100_000 else 0)
        view.grid.layoutManager = GridLayoutManager(requireContext(), 5)
        view.next_screen.setOnClickListener {
            with(requireActivity() as SpawnFragmentActivity) {
                addFragment(page + 1, requestHandle)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        requireActivity().title = "Fragments using ${requestHandle.name} context, page $page"
    }

    companion object {
        operator fun invoke(page: Int, with: GlideRequesteWith): SpawnFragment =
            SpawnFragment().apply {
                arguments = Bundle().apply {
                    putInt(PAGE, page)
                    putInt(GLIDE_REQUEST_WITH, with.ordinal)
                }
            }

        enum class GlideRequesteWith {
            ACTIVITY,
            FRAGMENT
        }
    }
}
