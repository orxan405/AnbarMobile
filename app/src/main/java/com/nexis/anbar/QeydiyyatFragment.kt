package com.nexis.anbar

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.text.Spannable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.core.state.Reference
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import com.nexis.anbar.data.model.tblMedaxil
import com.nexis.anbar.data.model.tblQeydiyyat
import com.nexis.anbar.databinding.FragmentMedaxilBinding
import com.nexis.anbar.databinding.FragmentQeydiyyatBinding
import java.util.Calendar
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.applyLinks
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import android.Manifest

class QeydiyyatFragment : Fragment() {
    lateinit var binding: FragmentQeydiyyatBinding

    private val gender = ArrayList<String>()
    private lateinit var veriAdap: ArrayAdapter<String>

        lateinit var mapFragment: SupportMapFragment
        lateinit var mMap: GoogleMap

        private var izinKontrol = 0
        private lateinit var flpc: FusedLocationProviderClient
        private lateinit var locationTask: Task<Location>

        var arrayloc = arrayListOf<String>()

        var textViewEnlem: String? = ""
        var textViewBoylam: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQeydiyyatBinding.inflate(inflater, container, false);
        return binding.root;
    }

    fun linkSetup() {
        val instagramLink = Link("Qeydiyyatdan")
            .setTextColor(Color.BLUE)
            .setTextColorOfHighlightedLink(Color.CYAN)
            .setHighlightAlpha(.4f)
            .setUnderlined(true)
            .setBold(false)
            .setOnClickListener {
//                Toast.makeText(requireContext(), "instagram link clicked", Toast.LENGTH_SHORT)
//                    .show()

                findNavController().navigate(
                    R.id.action_qeydiyyatFragment2_to_homeFragment2
                )

            }

        val youtubeLinik = Link("Youtube")
            .setTextColor(Color.RED)
            .setTextColorOfHighlightedLink(Color.GRAY)
            .setHighlightAlpha(.4f)
            .setUnderlined(true)
            .setBold(false)
            .setOnClickListener {
                Toast.makeText(requireContext(), "instagram link clicked", Toast.LENGTH_SHORT)
                    .show()
            }

        binding.txtLink.applyLinks(instagramLink, youtubeLinik)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linkSetup()
        val qeyd: Button = view.findViewById(R.id.btnQeydiyyat)

        val istifadeci: EditText = view.findViewById(R.id.txtUserNameQ)
        val parol: EditText = view.findViewById(R.id.txtParolQ)
        val emaili: EditText = view.findViewById(R.id.txtEmailQ)
        val nomresi: EditText = view.findViewById(R.id.txtPhoneQ)
        //val cinsi: EditText = view.findViewById(R.id.txtGenderQ)
        val tarix: EditText = view.findViewById(R.id.txtDateQ)
        val adi: EditText = view.findViewById(R.id.txtAdi)
        val soyadi: EditText = view.findViewById(R.id.txtSoyadi)
        val ataAdi: EditText = view.findViewById(R.id.txtAta_adi)

        gender.add("Kişi")
        gender.add("Qadın")

        veriAdap = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            gender
        )

        binding.spinner.adapter = veriAdap
        //binding.txtParolQ.error = "Bos buraxmaq olmaz"

        tarix.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val calendar = Calendar.getInstance()

                val il = calendar.get(Calendar.YEAR)
                val ay = calendar.get(Calendar.MONTH)
                val gun = calendar.get(Calendar.DAY_OF_MONTH)
                val datePicker = DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { datePicker, y, a, g ->
                        tarix.setText("$g/${a + 1}/$y")
                    },
                    il,
                    ay,
                    gun
                )

                datePicker.setTitle("Doğum tarixinizi seçin...")
                datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Təsdiqlə", datePicker)
                datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Ləğv et", datePicker)
                datePicker.show()
            }
        }

        tarix.setOnClickListener {

            if (tarix.hasFocus() == true) {


                val calendar = Calendar.getInstance()

                val il = calendar.get(Calendar.YEAR)
                val ay = calendar.get(Calendar.MONTH)
                val gun = calendar.get(Calendar.DAY_OF_MONTH)
                val datePicker = DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { datePicker, y, a, g ->
                        tarix.setText("$g/${a + 1}/$y")
                    },
                    il,
                    ay,
                    gun
                )

                datePicker.setTitle("Doğum tarixinizi seçin...")
                datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Təsdiqlə", datePicker)
                datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Ləğv et", datePicker)
                datePicker.show()
            }
        }


        flpc = LocationServices.getFusedLocationProviderClient(requireActivity())

        qeyd.setOnClickListener {
            val istifade: String = istifadeci.text.toString()
            val parolu: String = parol.text.toString()
            val mail: String = emaili.text.toString()
            val nomre: String = nomresi.text.toString()
            val cins: String = binding.spinner.selectedItem.toString()
            val dogumT: String = tarix.text.toString()
            val ad: String = adi.text.toString()
            val soyad: String = soyadi.text.toString()
            val ata_ad: String = ataAdi.text.toString()

            if (!istifade.isEmpty() && !parolu.isEmpty() && !mail.isEmpty() && !nomre.isEmpty() && !dogumT.isEmpty() && !ad.isEmpty() && !soyad.isEmpty() && !ata_ad.isEmpty()) {

                val locations =  Manifest.permission.ACCESS_FINE_LOCATION

                izinKontrol = ContextCompat.checkSelfPermission(requireContext(), locations)

                if (izinKontrol != PackageManager.PERMISSION_GRANTED) // icaze verilmeyibse
                {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        100
                    )
                } else // icaze verilibse
                {
                    locationTask = flpc.lastLocation
                    konumBilgisiAl()
                }

                val database = FirebaseDatabase.getInstance()
                val refId = database.reference.child("tblQeydiyyat")
                val id = refId.push()



                id.child("oid").setValue(id.key.toString())
                id.child("istifade").setValue(istifade)
                id.child("parolu").setValue(parolu)
                id.child("mail").setValue(mail)
                id.child("nomre").setValue(nomre)
                id.child("cins").setValue(cins)
                id.child("dogumT").setValue(dogumT)
                id.child("ad").setValue(ad)
                id.child("soyad").setValue(soyad)
                id.child("ata_ad").setValue(ata_ad)
                id.child("lat").setValue(textViewEnlem)
                id.child("lon").setValue(textViewBoylam)

                //val qeydiyyat = tblQeydiyyat(oid, istifade, parolu, mail, nomre, cins, dogumT, ad, soyad, ata_ad)

//                refId.push().setValue(qeydiyyat)

                Toast.makeText(requireContext(), "Uğurlu qeydiyyat", Toast.LENGTH_SHORT)
                    .show()

                binding.txtUserNameQ.setText("")
                binding.txtParolQ.setText("")
                binding.txtEmailQ.setText("")
                binding.txtPhoneQ.setText("")
                binding.txtDateQ.setText("")
                binding.txtAdi.setText("")
                binding.txtSoyadi.setText("")
                binding.txtAtaAdi.setText("")
                binding.txtAdi.requestFocus()

            }
        }


        fun onFocusChange(p0: View?, p1: Boolean) {
            val sekil = view.findViewById<Button>(R.id.HeadLinear)
            sekil.isVisible = !p1
        }


    }


    private fun initViews() {

    }

    fun konumBilgisiAl() {
        locationTask.addOnSuccessListener {
            if (it != null) {
                textViewEnlem = "Enlem : ${it.latitude}"
                textViewBoylam = "Boylam : ${it.longitude}"
            } else {
                textViewEnlem = "Enlem : alinmadi"
                textViewBoylam = "Boylam : alinmadi"
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 100) {
            izinKontrol =
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )

            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Icaze verildi...", Toast.LENGTH_SHORT).show()
                locationTask = flpc.lastLocation
                konumBilgisiAl()
            } else {
                Toast.makeText(requireContext(), "Icaze verilmedi...", Toast.LENGTH_SHORT).show()
            }
        }
    }

}