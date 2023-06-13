package com.leonel.intercamlp.ui.detailbeer


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonel.intercamlp.R
import com.leonel.intercamlp.adapter.BeerAdapter
import com.leonel.intercamlp.adapter.FootPairingAdapter
import com.leonel.intercamlp.adapter.HopsAdapter
import com.leonel.intercamlp.adapter.MaltAdapter
import com.leonel.intercamlp.databinding.FragmentDetailBeerBinding
import com.leonel.intercamlp.databinding.FragmentLoginBinding
import com.leonel.intercamlp.model.Malt
import com.squareup.picasso.Picasso
import java.util.*


class DetailBeerFragment : Fragment() {

    private var _binding: FragmentDetailBeerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(DetailBeerViewModel::class.java)
        _binding = FragmentDetailBeerBinding.inflate(inflater, container, false)
        val root: View = binding.root


        arguments?.let{

           val beer = DetailBeerFragmentArgs.fromBundle(it).detailBeer

            binding.rvHops.layoutManager = LinearLayoutManager(activity)
            binding.rvMalt.layoutManager = LinearLayoutManager(activity)
            binding.rvFootpairing.layoutManager = LinearLayoutManager(activity)

            binding.txtTituloDetalle.text = beer.name
            binding.txtTipsBeer.text = beer.brewersTips
            binding.txtYeast.text = beer.ingredients.yeast
            binding.txtTagline.text = beer.tagline
            binding.firstBrewed.text = getString(R.string.detailbeer_txt_firstbrewer) + beer.firstBrewed
            binding.txtVolume.text = beer.volume.value.toString() +" " + beer.volume.unit
            Picasso.get()
                .load(beer.imageURL)
                .into(binding.imgBeerdetalle)

            binding.include.txtAbv.text= beer.abv.toString()
            binding.include.txtIbu.text= beer.ibu.toString()
            binding.include.txtOg.text= beer.targetOg.toString()
            binding.include.txtFg.text= beer.targetFg.toString()

            val adapterHops = HopsAdapter(beer.ingredients.hops)
            binding.rvHops.adapter = adapterHops
            val adapterMalt = MaltAdapter(beer.ingredients.malt)
            binding.rvMalt.adapter = adapterMalt
            val adapterFootPairing = FootPairingAdapter(beer.foodPairing)
            binding.rvFootpairing.adapter = adapterFootPairing

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}