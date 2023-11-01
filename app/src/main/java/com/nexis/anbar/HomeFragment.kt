package com.nexis.anbar

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.applyLinks
import com.nexis.anbar.Adapter.MyAdapter
import com.nexis.anbar.data.model.tblQeydiyyat
import com.nexis.anbar.databinding.FragmentHomPageBinding
import com.nexis.anbar.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var firebaseAuthe: FirebaseAuth

    lateinit var dbref: DatabaseReference
    lateinit var userRecyclerview: RecyclerView
    lateinit var userArrayList: ArrayList<tblQeydiyyat>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
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
                    R.id.action_homeFragment2_to_qeydiyyatFragment2
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

        binding.txtYeniQeydiyyat.applyLinks(instagramLink, youtubeLinik)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linkSetup()
            //val btn = binding.btnQeydiyyat
        val btnDaxil = binding.btnLogin

        val database = FirebaseDatabase.getInstance()
        val login = database.getReference("tblQeydiyyat")

        val myList = ArrayList<String>()
        val myListPar = ArrayList<String>()



        btnDaxil.setOnClickListener {
            val user = binding.txtEmailQ
            val parol = binding.txtPassword

            var ad: String = user.text.toString()
            var par: String = parol.text.toString()

            val ss = "ddd"


            if (!ad.isEmpty() && !par.isEmpty()) {
                login.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(ds: DataSnapshot) {
                        for (i in ds.children) {
                            val deger1 = i.getValue(tblQeydiyyat::class.java)

                            myList.add(deger1?.mail.toString())
                            myListPar.add(deger1?.parolu.toString())
                        }
                        if (myList != null && myListPar != null) {

                            if (myList.contains(ad) && myListPar.contains(par)) {
                                Toast.makeText(
                                    requireContext(),
                                    "Uğurlu əməliyyat",
                                    Toast.LENGTH_SHORT
                                ).show()
                                user.setBackgroundColor(Color.WHITE)
                                parol.setBackgroundColor(Color.WHITE)

                                val bundle = Bundle()
                                bundle.putString("data", "$ad sistemə daxil oldu...")

                                findNavController().navigate(
                                    R.id.action_homeFragment2_to_homPageFragment2,
                                    bundle
                                )
                            }
                        }else {
                            Toast.makeText(requireContext(), "Uğursuz əməliyyat", Toast.LENGTH_SHORT).show()
                            user.setBackgroundColor(Color.RED)
                            parol.setBackgroundColor(Color.RED)
                            user.setText("")
                            parol.setText("")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
            } else {
                Toast.makeText(requireContext(), "Uğursuz əməliyyat", Toast.LENGTH_SHORT).show()
                user.setBackgroundColor(Color.RED)
                parol.setBackgroundColor(Color.RED)
                user.setText("")
                parol.setText("")
            }


        }

//        btn.setOnClickListener {
//            Toast.makeText(requireContext(), "Uğurlu əməliyyat", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(
//                R.id.action_homeFragment2_to_qeydiyyatFragment2
//            )
//        }

        fun onFocusChange(p0: View?, p1: Boolean) {
            val sekil = view.findViewById<Button>(R.id.btnQeydiyyat)
            sekil.isVisible = !p1
        }
    }

    fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("tblQeydiyyat")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(ds: DataSnapshot) {
                if (ds.exists()) {
                    for (i in ds.children) {
                        val kisi = i.getValue(tblQeydiyyat::class.java)
                        userArrayList.add(kisi!!)

                    }

                    userRecyclerview.adapter = MyAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }


}

