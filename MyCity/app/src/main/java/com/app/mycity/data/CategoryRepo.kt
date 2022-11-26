package com.app.mycity.data

import com.app.mycity.R

object CategoryRepo {
    val defaultCategory = CategoryData()[0]
    val defaultItem = CategoryData()[0].places[0]
    fun CategoryData(): List<Category> {
        return listOf(
            Category(
                1,
                R.string.category_one,
                R.drawable.coffee_shop_1,
                places = listOf(
                    Places(1,R.string.coffee_shop_one,R.drawable.coffee_shop_1,R.string.desc),
                    Places(2,R.string.coffee_shop_two,R.drawable.coffee_shop_2,R.string.desc),
                    Places(3,R.string.coffee_shop_three,R.drawable.coffee_shop_3,R.string.desc),
                )
            ),
            Category(
                2,
                R.string.category_two,
                R.drawable.restaurant_1,
                places = listOf(
                    Places(1,R.string.restaurant_one,R.drawable.restaurant_1,R.string.desc),
                    Places(2,R.string.restaurant_two,R.drawable.restaurant_2,R.string.desc),
                    Places(3,R.string.restaurant_three,R.drawable.restaurant_3,R.string.desc),
                )
            ),
        )
    }
}