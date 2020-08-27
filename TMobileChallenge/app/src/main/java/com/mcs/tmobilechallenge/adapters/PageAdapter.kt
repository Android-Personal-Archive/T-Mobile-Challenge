package com.mcs.tmobilechallenge.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mcs.tmobilechallenge.R
import com.mcs.tmobilechallenge.pokos.PagePOKO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.feed_item_layout.view.*
import kotlin.random.Random

class PageAdapter(context: Context, val pokoDataSet: PagePOKO): RecyclerView.Adapter<PageAdapter.PageViewHolder>() {
    class PageViewHolder(feedItemView: View): RecyclerView.ViewHolder(feedItemView){
        val cvCard: CardView = feedItemView.cv_card
        val ivImage: ImageView = feedItemView.iv_image
        val tvTitle: TextView = feedItemView.tv_title
        val tvDescription: TextView = feedItemView.tv_description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val fiView: View = LayoutInflater.from(parent.context).inflate(R.layout.feed_item_layout, parent, false)
        return PageViewHolder(fiView)
    }

    override fun getItemCount(): Int {
        return pokoDataSet.page.cards.size
    }

    ///card_type: text
    //value (text)
    //attributes {color, font {size}}

    ///card_type: title_description
    //title {value (text), attributes {color, font {size}}}
    //description {value (text), attributes {color, font {size}}}

    ///card_type: image_title_description
    //image {url (src), size {width, height}}
    //title {value (text), attributes {color, font {size}}}
    //description {value (text), attributes {color, font {size}}}
    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        when(pokoDataSet.page.cards[position].cardType.toLowerCase())
        {
            "text" -> {
                holder.ivImage.visibility = View.GONE
                holder.tvDescription.visibility = View.GONE

                holder.tvTitle.text = pokoDataSet.page.cards[position].card.value
                holder.tvTitle.setTextColor(Color.parseColor(pokoDataSet.page.cards[position].card.attributes.textColor))
                holder.tvTitle.textSize = pokoDataSet.page.cards[position].card.attributes.font.size.toFloat()

                //todo I may have to create a single instance of Random before use since the seed is likely to be the same otherwise. let's see...
                //todo make a function for randomization anyways. It is going to get redundant.
                holder.cvCard.invalidate()
                holder.cvCard.setCardBackgroundColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
            }
            "title_description" -> {
                holder.ivImage.visibility = View.GONE

                holder.tvTitle.text = pokoDataSet.page.cards[position].card.title.value
                holder.tvTitle.setTextColor(Color.parseColor(pokoDataSet.page.cards[position].card.title.attributes.textColor))
                holder.tvTitle.textSize = pokoDataSet.page.cards[position].card.title.attributes.font.size.toFloat()

                holder.tvDescription.text = pokoDataSet.page.cards[position].card.description.value
                holder.tvDescription.setTextColor(Color.parseColor(pokoDataSet.page.cards[position].card.description.attributes.textColor))
                holder.tvDescription.textSize = pokoDataSet.page.cards[position].card.description.attributes.font.size.toFloat()

                holder.cvCard.invalidate()
                holder.cvCard.setCardBackgroundColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
            }
            "image_title_description" -> {
                Picasso.get().load(pokoDataSet.page.cards[position].card.image.url).into(holder.ivImage)
                holder.ivImage.requestLayout()
                holder.ivImage.layoutParams.width = pokoDataSet.page.cards[position].card.image.size.width.toInt()
                holder.ivImage.layoutParams.height = pokoDataSet.page.cards[position].card.image.size.height.toInt()

                holder.tvTitle.text = pokoDataSet.page.cards[position].card.title.value
                holder.tvTitle.setTextColor(Color.parseColor(pokoDataSet.page.cards[position].card.title.attributes.textColor))
                holder.tvTitle.textSize = pokoDataSet.page.cards[position].card.title.attributes.font.size.toFloat()

                holder.tvDescription.text = pokoDataSet.page.cards[position].card.description.value
                holder.tvDescription.setTextColor(Color.parseColor(pokoDataSet.page.cards[position].card.description.attributes.textColor))
                holder.tvDescription.textSize = pokoDataSet.page.cards[position].card.description.attributes.font.size.toFloat()
            }
        }
    }
}