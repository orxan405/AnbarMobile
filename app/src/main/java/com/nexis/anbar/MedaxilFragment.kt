package com.nexis.anbar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.nexis.anbar.data.model.tblMedaxil
import com.nexis.anbar.databinding.FragmentMedaxilBinding

class MedaxilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentMedaxilBinding

    private val gender = ArrayList<String>()
    private val olkeAdi = ArrayList<String>()
    private lateinit var veriAdap: ArrayAdapter<String>
    private lateinit var veriAdapOlkeAdi: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedaxilBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gender.add("Ədəd")
        olkeAdi.add("Azərbaycan")
        olkeAdi.add("Türkiyə")
        olkeAdi.add("ABŞ")
        olkeAdi.add("Rusiya")
        olkeAdi.add("Çin")
        olkeAdi.add("Almaniya")
        olkeAdi.add("Pakistan")
        olkeAdi.add("Hindistan")
        olkeAdi.add("Fransa")
        olkeAdi.add("İtaliya")
        olkeAdi.add("İngiltərə")
        olkeAdi.add("Gürcüstan")
        olkeAdi.add("Qazaxstan")

        veriAdapOlkeAdi = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            olkeAdi
        )

        binding.spinnerOlkeAdi.adapter = veriAdapOlkeAdi

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
            binding.txtBarcode.setText("")
            startBarcodeScanner()
        }

    }

    private val scannerResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val qrCode = result.data?.extras?.getString("qrCodeResult") ?: ""
                binding.txtBarcode.setText(qrCode)
            }
        }

    fun startBarcodeScanner() {
        val scannerIntent = Intent(requireActivity(), ScannerActivity::class.java)
        scannerResult.launch(scannerIntent)
    }
}
