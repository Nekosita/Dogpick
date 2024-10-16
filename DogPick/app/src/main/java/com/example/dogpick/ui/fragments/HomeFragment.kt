package com.example.dogpick.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.dogpick.adapters.mayBelikeAdapter
import com.example.dogpick.data.dogData
import com.example.dogpick.data.dogDataS
import com.example.dogpick.databinding.FragmentHomeBinding
import com.example.dogpick.ui.PictureActivity
import com.example.dogpick.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var dogDataToPicActivity: dogData
    private lateinit var maybelikeAdapter: mayBelikeAdapter

    companion object{
        const val DOG_DATA = "com.example.dogpick.ui.dogData"
    }

    //初期化
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        maybelikeAdapter = mayBelikeAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareDogDataSItemsRecyclerView()

        homeViewModel.getRandomDogData()
        observeRandomDogData()
        onRandomDogButtonClick()

        homeViewModel.getDogDataSItem()
        oberserveDogDataSItemsLiveData()
        onDogDataSItemsClick()

    }


    private fun prepareDogDataSItemsRecyclerView() {
        binding.hmCard2RecDogs.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = maybelikeAdapter
        }
    }

    private fun onDogDataSItemsClick() {
        maybelikeAdapter.onItemClickListener = { dogPicMaybelike ->
            val intent = Intent(activity, PictureActivity::class.java)
            intent.putExtra(DOG_DATA, dogPicMaybelike)
            startActivity(intent)
        }
    }


    private fun oberserveDogDataSItemsLiveData() {
        homeViewModel.getDogDataSItemLiveData().observe(viewLifecycleOwner) { dogDataS ->
            if(dogDataS != null){
            maybelikeAdapter.setDogDataSList(dogDataSList = dogDataS as dogDataS )}
        }
    }

    private fun onRandomDogButtonClick() {
       binding.hmRandomImage.setOnClickListener {
           val intent = Intent(requireContext(), PictureActivity::class.java)
           intent.putExtra(DOG_DATA, dogDataToPicActivity.message)
           startActivity(intent)
       }
    }

    private fun observeRandomDogData() {
        homeViewModel.getRandomDogLiveData().observe(viewLifecycleOwner) { value ->
            Glide.with(this@HomeFragment).load(value!!.message).into(binding.hmRandomImage)
            this.dogDataToPicActivity = value
        }
    }


}