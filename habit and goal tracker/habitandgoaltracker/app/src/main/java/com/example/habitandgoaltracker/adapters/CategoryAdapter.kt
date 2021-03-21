package com.example.habitandgoaltracker.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.habitandgoaltracker.R
import com.example.habitandgoaltracker.database.entities.Category
import com.example.habitandgoaltracker.screens.MenuFragmentDirections
import java.util.*

//Adapter za kategorije koje imamo u aplikaciji

 class CategoryAdapter(categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val categoryList: ArrayList<Category>

    override fun onCreateViewHolder(
        parent: ViewGroup,

        viewType: Int

    ): CategoryViewHolder {

        Log.d("tip",parent.toString())

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(

        holder: CategoryViewHolder,

        position: Int

    ) {
        val category: Category = categoryList[position]

        // Postavljanje imena kategorije u menu

        holder.categoryName.setText(category.name)

        holder.card.setOnClickListener(
            Navigation.createNavigateOnClickListener(MenuFragmentDirections.actionHomeFragmentToActivityFragment(category.categoryId, category.type, category.unitOfMeasure ?: ""))
        )

        //postavljanje ikona za kategorije

        when (category.name) {

            "Medication" -> holder.imageCategory.setImageResource(R.drawable.medicament)

            "Workout" -> holder.imageCategory.setImageResource(R.drawable.workout)

            "Study" -> holder.imageCategory.setImageResource(R.drawable.study)

            "Eat" -> holder.imageCategory.setImageResource(R.drawable.eating)

            "Sleep" -> holder.imageCategory.setImageResource(R.drawable.sleep)


        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

     class CategoryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var categoryName: TextView
         var imageCategory : ImageView
         var card : CardView




        init {
            categoryName = itemView.findViewById(R.id.categoryName)
            imageCategory = itemView.findViewById(R.id.imageCategory)
            card = itemView.findViewById(R.id.cardCategory)

        }
    }

    init {
        this.categoryList = categoryList
    }
}