package com.wagyufari.alfardan.ui.presentation.home.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.wagyufari.alfardan.R
import com.wagyufari.alfardan.databinding.ItemTextBinding
import com.wagyufari.alfardan.ui.domain.model.User


class ItemUserFactory : AbstractBindingItem<ItemTextBinding>() {
    var user: User? = null

    override val type: Int
        get() = R.id.item_text

    override fun bindView(binding: ItemTextBinding, payloads: List<Any>) {
        binding.tvTitle.text = user?.name
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemTextBinding {
        return ItemTextBinding.inflate(inflater, parent, false)
    }
}