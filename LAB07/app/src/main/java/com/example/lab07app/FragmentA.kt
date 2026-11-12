package com.example.lab07app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentA : Fragment(R.layout.fragment_a) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etMessage = view.findViewById<EditText>(R.id.etMessage)
        val btnSendToB = view.findViewById<Button>(R.id.btnSendToB)

        btnSendToB.setOnClickListener {
            val text = etMessage.text.toString().ifEmpty { "Pozdrowienia" }
            val bundle = Bundle().apply {
                putString("userText", "$text (od Anastasiia 104051)")
            }
            parentFragmentManager.setFragmentResult("requestKey", bundle)
            Toast.makeText(requireContext(), "Zapisano do wysłania!", Toast.LENGTH_SHORT).show()
        }
    }
}