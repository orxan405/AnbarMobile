package com.nexis.anbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nexis.anbar.databinding.FragmentHomPageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentHomPageBinding

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
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_hom_page, container, false)
        binding = FragmentHomPageBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn: Button = view.findViewById(R.id.btnCixis)
        val btnQaliq: Button = view.findViewById(R.id.btnQaliq)
        val btnMedaxil: Button = binding.btnMedaxil
        val btnKino: Button = binding.btnKino
        val btnqeyd: Button = binding.btnQeydiyyatList
        val ad: TextView = view.findViewById(R.id.txtBasliq)

        var gelenDeyer: String = requireArguments().getString("data").toString()
        ad.setText(gelenDeyer)

        btn.setOnClickListener {
            Toast.makeText(requireContext(), "Uğurlu əməliyyat", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.action_homPageFragment2_to_homeFragment2
            )
        }

        btnQaliq.setOnClickListener {
            Toast.makeText(requireContext(), "Uğurlu əməliyyat", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.action_homPageFragment2_to_qaliqFragment
            )
        }

        btnMedaxil.setOnClickListener {
            Toast.makeText(requireContext(), "Uğurlu əməliyyat", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.action_homPageFragment2_to_medaxilFragment2
            )
        }

        btnKino.setOnClickListener {
            Toast.makeText(requireContext(), "Uğurlu əməliyyat", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.action_homPageFragment2_to_kinoFragment
            )
        }

        btnqeyd.setOnClickListener {
            Toast.makeText(requireContext(), "Uğurlu əməliyyat", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.action_homPageFragment2_to_qeydiyyatListFragment
            )
        }

    }
}