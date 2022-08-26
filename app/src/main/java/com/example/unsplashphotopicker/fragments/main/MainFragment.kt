package com.example.unsplashphotopicker.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unsplashphotopicker.R
import com.example.unsplashphotopicker.databinding.FragmentMainBinding
import com.example.unsplashphotopicker.fragments.main.adapter.MainPhotosAdapter
import com.example.unsplashphotopicker.fragments.main.viewmodel.MainViewModel
import com.example.unsplashphotopicker.fragments.main.viewmodel.MainViewModelFactory
import com.example.unsplashphotopicker.fragments.main.viewmodel.network.MainRepository
import com.example.unsplashphotopicker.fragments.main.viewmodel.network.NumbersApiService

class MainFragment : Fragment() {
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var retrofitService: NumbersApiService
    private lateinit var mainRepository: MainRepository
    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MainPhotosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMainBinding.bind(view)
        retrofitService = NumbersApiService.getInstance()
        mainRepository = MainRepository(retrofitService)
        mViewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository))[MainViewModel::class.java]

        setAdapter()
    }

    private fun setAdapter() {
        if (!this::mAdapter.isInitialized) {
            mAdapter = MainPhotosAdapter {
                //@TODO could use bundle withour companion object, but then will be needed 1 more request for 1 photo/iterate for list of photos
                findNavController().navigate(R.id.action_mainFragment_to_photoFragment)
            }
            setAdapterPhotos()
        } else {
            if (mViewModel.photos.value?.isEmpty() == true) {
                setAdapterPhotos()
            }
        }
        binding.mainRecyclerView.adapter = mAdapter
        //@TODO Can be replaced with grid layout
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setAdapterPhotos() {
        mViewModel.getPhotos()
        mViewModel.photos.observe(viewLifecycleOwner) {
            mAdapter.photos = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}