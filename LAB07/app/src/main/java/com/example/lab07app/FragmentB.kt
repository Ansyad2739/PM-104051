package com.example.lab07app

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentB : Fragment(R.layout.fragment_b) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvReceivedMessage = view.findViewById<TextView>(R.id.tvReceivedMessage)

        parentFragmentManager.setFragmentResultListener("requestKey", this) { _, bundle ->
            val result = bundle.getString("userText")
            tvReceivedMessage.text = "Odebrano:\n$result"
        }
    }
}
