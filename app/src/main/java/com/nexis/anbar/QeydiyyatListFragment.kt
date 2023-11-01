package com.nexis.anbar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nexis.anbar.Adapter.AdapterHorizontal
import com.nexis.anbar.Adapter.AdapterQeydiyyatList
import com.nexis.anbar.Adapter.AdapterVertical
import com.nexis.anbar.Adapter.MyAdapter
import com.nexis.anbar.data.mock.getHorizontalKino
import com.nexis.anbar.data.mock.getVerticalKino
import com.nexis.anbar.data.model.dataVerticalKino
import com.nexis.anbar.data.model.tblQeydiyyat
import com.nexis.anbar.databinding.FragmentKinoBinding
import com.nexis.anbar.databinding.FragmentQeydiyyatListBinding
import com.nexis.ders_25_webserivce.Helper.RetrofitHelper
import com.nexis.ders_25_webserivce.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Array


class QeydiyyatListFragment : Fragment() {
    lateinit var binding: FragmentQeydiyyatListBinding

    lateinit var dbref: DatabaseReference
    lateinit var userRecyclerview: RecyclerView
    lateinit var userArrayList: ArrayList<tblQeydiyyat>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQeydiyyatListBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerview = binding.RVQeydiyyat
        userRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<tblQeydiyyat>()
        getUserData()


//        val database = FirebaseDatabase.getInstance()
//
//        val refKisiler = database.getReference("tblQeydiyyat")
//
//        val id = refKisiler.push()
//        id.child("id").setValue(id.key.toString())
//
//        refKisiler.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(ds: DataSnapshot) {
//
//                val dataList = ArrayList<tblQeydiyyat>()
//
//                for (i in ds.children){
//                    val kisi = i.getValue(tblQeydiyyat::class.java)
//
//                    if (kisi != null){
//                        val key = i.key
//
//                        dataList.add(kisi)
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        }
//        )


//        val database = FirebaseDatabase.getInstance()
//
//        val qeyd = database.getReference("tblQeydiyyat")
//
//        val netice = qeyd.orderByChild("ad").equalTo("Orxan")
//
//        qeyd.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(ds: DataSnapshot) {
//                for (p in ds.children) {
//                    val kisi = p.getValue(tblQeydiyyat::class.java)
//
//                    if (kisi != null){
//                        val key = p.key
//
//                        binding
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })


//        val list = ArrayList<tblQeydiyyat>()
//
//        binding.RVQeydiyyat.adapter = AdapterQeydiyyatList(
//            list,
//            { model ->
//                navigateToKinoEtrafli(model)
//            }
//        )
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


    private fun getUserById(id: String) {
        val retrofit = RetrofitHelper.retrofitBuilder()
        val userApi = retrofit.create(UserService::class.java)
        val call = userApi.getFriendById(id)

        call.enqueue(object : Callback<tblQeydiyyat?> {
            override fun onResponse(
                call: Call<tblQeydiyyat?>,
                response: Response<tblQeydiyyat?>
            ) {
                val body = response.body()
                body

//                val idd = findViewById<TextView>(R.id.txtId)
//                val adi = findViewById<TextView>(R.id.txtAdi)
//                val istifadeci = findViewById<TextView>(R.id.txtIstifadeciAdi)

//                if (!idd.toString().isEmpty() && !adi.toString().isEmpty() && !istifadeci.toString()
//                        .isEmpty()
//                ) {
//                    Log.e("*****", "*****")
//                    Log.e("Kisi Id", (body?.id).toString())
//                    Log.e("Kisi Ad", (body?.name).toString())
//                    Log.e("Kisi Tel", (body?.username.toString()))
//
//                    idd.setText((body?.id).toString())
//                    adi.setText((body?.name).toString())
//                    istifadeci.setText((body?.username).toString())
//                }

            }

            override fun onFailure(call: Call<tblQeydiyyat?>, t: Throwable) {

            }

        })
    }

    private fun getUserlist() {
        val retrofit = RetrofitHelper.retrofitBuilder()
        val userApi = retrofit.create(UserService::class.java)
        val call = userApi.getFriends()
        call.enqueue(object : Callback<List<tblQeydiyyat?>> {

            override fun onResponse(
                call: Call<List<tblQeydiyyat?>>,
                response: Response<List<tblQeydiyyat?>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body


                }
            }

            override fun onFailure(call: Call<List<tblQeydiyyat?>>, t: Throwable) {
                t.printStackTrace()
            }


        })
    }


    fun navigateToKinoEtrafli(model: tblQeydiyyat) {
        val bundle = Bundle()
        bundle.putString("data", model.ad)
        bundle.putString("data1", model.soyad)
        bundle.putString("data2", model.ata_ad)

//        findNavController().navigate(
//            R.id.action_kinoFragment_to_kinoFragmentEtrafli,
//            bundle
//        )
    }
}