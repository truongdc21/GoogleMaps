package com.example.maps

import android.app.Dialog
import android.os.Bundle
import com.example.maps.databinding.LayoutMarkerDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MarkerDetailBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheet = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val viewBinding = LayoutMarkerDetailBinding.inflate(layoutInflater)
        bottomSheet.setContentView(viewBinding.root)
        val data = arguments?.getSerializable("Marker") as? Marker
        data?.let {
            viewBinding.apply {
                /**
                 * import libary Glide to load image address
                 */
                tvTitle.text = it.title
                tvAddress.text = it.address
            }
        }
        return bottomSheet
    }
}
