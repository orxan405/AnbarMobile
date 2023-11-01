package com.nexis.anbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nexis.anbar.Adapter.AdapterHorizontal
import com.nexis.anbar.Adapter.AdapterVertical
import com.nexis.anbar.data.mock.getHorizontalKino
import com.nexis.anbar.data.mock.getVerticalKino
import com.nexis.anbar.data.model.dataVerticalKino
import com.nexis.anbar.databinding.FragmentKinoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class KinoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentKinoBinding


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
        binding = FragmentKinoBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RVHorizontal.adapter = AdapterHorizontal(getHorizontalKino(), requireContext())

        val list = getVerticalKino()

        binding.RVVertical.adapter = AdapterVertical(
            list,
            { model ->
                navigateToKinoEtrafli(model)
            }
        )


    }




    fun navigateToKinoEtrafli(model: dataVerticalKino) {
        val bundle = Bundle()
        bundle.putString("data", model.basliqVertical)
        bundle.putString("data1", model.icerikVertical)
        bundle.putString("data2", model.sekilVertical)

        findNavController().navigate(
            R.id.action_kinoFragment_to_kinoFragmentEtrafli,
            bundle
        )
    }


}