package com.belajar.storyapp.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.belajar.storyapp.R
import com.belajar.storyapp.const.Constant
import com.belajar.storyapp.databinding.ItemStoryBinding
import com.belajar.storyapp.network.response.StoriesResponse
import com.belajar.storyapp.ui.story.detail.DetailStoryActivity
import com.belajar.storyapp.util.timeStamptoString
import com.bumptech.glide.Glide

class StoryListAdapter (private val listStory: List <StoriesResponse.Story>) : RecyclerView.Adapter<StoryListAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemStoryBinding.bind(itemView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_story, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder) {
            val (_, name, description, photoUrl, createdAt) = listStory[position]

            binding.itemStoryTitle.text = name
            binding.itemStoryDesc.text = description
            binding.itemStoryTime.text = createdAt.timeStamptoString()
            Glide.with(holder.itemView.context)
                .load(photoUrl)
                .into(holder.binding.itemStoryImg)

            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailStoryActivity::class.java)
                intent.putExtra(Constant.BUNDLE_STORY, listStory[position])
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = listStory.size
}