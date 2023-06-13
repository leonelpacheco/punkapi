package com.leonel.intercamlp.ui.beers

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
import com.leonel.intercamlp.databinding.FragmentBeerBinding
import com.leonel.intercamlp.databinding.FragmentLoginBinding
import com.leonel.intercamlp.model.Beer
import com.leonel.intercamlp.utils.ConnectionLiveData
import com.leonel.intercamlp.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerFragment : Fragment() {

    private var _binding: FragmentBeerBinding? = null
    private val binding get() = _binding!!
    private var isLoadingPagination = false
    private var page=1
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val beerViewModel =
            ViewModelProvider(this).get(BeerViewModel::class.java)
        _binding = FragmentBeerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.loading.isVisible=false
        binding.rwlistbeers.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        connectionLiveData = activity?.let { ConnectionLiveData(it) }!!
        connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                if(it) {
                    beerViewModel.getBeersData(page)
                    page++
                }
            }
        }

        beerViewModel.isLoading.observe(viewLifecycleOwner){
            binding.loading.isVisible = it
        }

        binding.rwlistbeers.layoutManager = LinearLayoutManager(activity)

        beerViewModel.beersLiveData.observe(viewLifecycleOwner){
            val adapter = BeerAdapter(it)
            binding.rwlistbeers.adapter = adapter

            adapter.setOnClickListener(object :
                BeerAdapter.OnClickListener {
                override fun onClick(position: Int, model: Beer) {

                    val  action = BeerFragmentDirections.actionBeerFragmentToDetailbeerFragment(model)
                    findNavController().navigate(action)

                }
                override fun onClickFavorite(position: Int, model: Beer) {
                    beerViewModel.getSaveFavorites(model)
                    Toast.makeText(activity,getString(R.string.toast_txt_save),Toast.LENGTH_SHORT).show()
                }

            })

            binding.rwlistbeers.addOnScrollListener(object :
                PaginationScrollListener(binding.rwlistbeers.layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    beerViewModel.getBeersData(page)
                    page++
                }

                override fun isLastPage() = false
                override fun isLoading() = isLoadingPagination
            })

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}