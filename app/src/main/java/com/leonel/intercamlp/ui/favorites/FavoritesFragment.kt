package com.leonel.intercamlp.ui.favorites


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonel.intercamlp.R
import com.leonel.intercamlp.adapter.BeerAdapter
import com.leonel.intercamlp.adapter.FavoritesAdapter
import com.leonel.intercamlp.databinding.FragmentBeerBinding
import com.leonel.intercamlp.databinding.FragmentFavoritesBinding
import com.leonel.intercamlp.databinding.FragmentLoginBinding
import com.leonel.intercamlp.model.Beer
import com.leonel.intercamlp.model.favorito
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        favoritesViewModel.getBeersData()

        favoritesViewModel.isLoading.observe(viewLifecycleOwner){
            binding.loading.isVisible = it
        }
        binding.rwlistfavorites.layoutManager = LinearLayoutManager(activity)
        favoritesViewModel.beersLiveData.observe(viewLifecycleOwner){
            val adapter = FavoritesAdapter(it)
            binding.rwlistfavorites.adapter = adapter

            adapter.setOnClickListener(object :
                FavoritesAdapter.OnClickListener {
                override fun onClick(position: Int, updateFavoriteRating: favorito) {
                    favoritesViewModel.updateRatingData(updateFavoriteRating)
                }
            })

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}