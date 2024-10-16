package com.example.dogpick.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.dogpick.adapters.favDogAdapter
import com.example.dogpick.databinding.FragmentFavDogBinding
import com.example.dogpick.ui.BigPictureActivity
import com.example.dogpick.viewmodel.FavDogViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavDogFragment : Fragment() {

    lateinit var binding: FragmentFavDogBinding
    private lateinit var favDogAdapter: favDogAdapter
    private val favDogviewModel: FavDogViewModel by viewModel()

    companion object{
        const val DOG_DATA = "com.example.dogpick.ui.dogData"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavDogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prepareRecyclerView()
        oberveFavDogLiveData()

        //オブジェクトの移動の監視事件設定
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            )=true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               val position = viewHolder.adapterPosition
                val dogData = favDogAdapter.differ.currentList[position]
                favDogviewModel.deleteDogData(dogData)

                Snackbar.make(requireView(), "消しました", Snackbar.LENGTH_LONG).apply {
                    setAction("取り消し"){
                        favDogviewModel.insertDogData(dogData)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.favDog)


        //クリック事件の設定
        onDogDataItemsClick()

    }

    private fun prepareRecyclerView() {
        favDogAdapter = favDogAdapter()
        favDogviewModel.getAllFavDogData().observe(viewLifecycleOwner) {dogDataS ->
            favDogAdapter.differ.submitList(dogDataS)
        }
    }

    private fun oberveFavDogLiveData() {
        binding.favDog.apply {
            layoutManager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL,false)
            adapter = favDogAdapter
        }
    }

    private fun onDogDataItemsClick() {
        favDogAdapter.onItemClickListener = { dogData ->
            val intent = Intent(activity, BigPictureActivity::class.java)
            intent.putExtra(DOG_DATA, dogData)
            startActivity(intent)
        }
    }


}