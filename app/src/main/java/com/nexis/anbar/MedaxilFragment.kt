package com.nexis.anbar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.nexis.anbar.data.model.tblMedaxil
import com.nexis.anbar.databinding.FragmentMedaxilBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MedaxilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MedaxilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentMedaxilBinding

    private val gender = ArrayList<String>()
    private lateinit var veriAdap: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedaxilBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gender.add("Kişi")
        gender.add("Qadın")

        veriAdap = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            gender
        )

        binding.spinner.adapter = veriAdap

        binding.button.setOnClickListener() {

            val malAdi = binding.txtMalinAdi.text.toString()
            val malSayi = binding.txtSayi.text.toString()
            val malAlisQiymeti = binding.txtAlisQiymeti.text.toString()
            val malSatisQiymeti = binding.txtSatisQiymeti.text.toString()



            if (!malAdi.isEmpty() && !malSayi.isEmpty() && !malAlisQiymeti
                    .isEmpty() && !malSatisQiymeti.isEmpty()
            ) {

                val database = FirebaseDatabase.getInstance()
                val refId = database.getReference("tblMedaxil")

                val medaxil = tblMedaxil(malAdi, malSayi, malAlisQiymeti, malSatisQiymeti)

                refId.push().setValue(medaxil)

                Toast.makeText(requireContext(), "Ugurlu emeliyyat", Toast.LENGTH_SHORT).show()

                binding.txtMalinAdi.setText("")
                binding.txtSayi.setText("")
                binding.txtAlisQiymeti.setText("")
                binding.txtSatisQiymeti.setText("")
                binding.txtMalinAdi.requestFocus()

            }
        }

        binding.IVQRScanner.setOnClickListener {
            startBarcodeScanner()
        }

    }

    fun startBarcodeScanner() {
        val integrator = IntentIntegrator(requireActivity())
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Bir QR kod tarayın")
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    // onActivityResult ile sonuçları işleme
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                println("xeta vermedi")
            } else {
                val scannedData = result.contents

                binding.txtBarcode.setText(scannedData.toString())
            }
        }
    }
}
