package com.acuon.sampleapp.ui.favorite

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.acuon.sampleapp.R
import com.acuon.sampleapp.common.BaseFragment
import com.acuon.sampleapp.common.BundleKeys
import com.acuon.sampleapp.common.ResultOf
import com.acuon.sampleapp.databinding.FragmentFavoriteBinding
import com.acuon.sampleapp.ui.favorite.viewmodel.FavoriteViewModel
import com.acuon.sampleapp.ui.home.UsersAdapter
import com.acuon.sampleapp.utils.extensions.addDecoration
import com.acuon.sampleapp.utils.extensions.createDecorator
import com.acuon.sampleapp.utils.extensions.dp
import com.acuon.sampleapp.utils.extensions.executeWithAction
import com.acuon.sampleapp.utils.extensions.show
import com.acuon.sampleapp.utils.extensions.verticalView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    override fun getLayoutId() = R.layout.fragment_favorite

    @Inject
    lateinit var usersAdapter: UsersAdapter

    private val viewModel: FavoriteViewModel by activityViewModels()

    override fun setupViews() {
        binding.apply {
            header.apply {
                tvHeading.text = getString(R.string.favorite_screen)
                ivLeftIcon.setImageResource(R.drawable.ic_back_arrow)
                ivLeftIcon.setOnClickListener(clickListener)
                ivLeftIcon.show()
            }
            rcvUsers.apply {
                verticalView(context)
                adapter = usersAdapter
                addDecoration(
                    createDecorator(
                        top = 8.dp,
                        bottom = 8.dp,
                        left = 8.dp,
                        right = 8.dp
                    )
                )
            }
            usersAdapter.onItemClick {
                findNavController().navigate(
                    R.id.action_favoriteFragment_to_detailsFragment,
                    bundleOf(BundleKeys.USER_DATA to it)
                )
            }
            usersAdapter.onFavoriteClick { item, favorite ->
                if (item?.id != null && favorite != null) {
                    viewModel.addToFavorite(item.id, favorite)
                }
            }
        }
    }

    override fun bindViewModel() {
        viewModel.users.observe(viewLifecycleOwner) {
            when (it) {
                is ResultOf.Success -> usersAdapter.list = it.value
                else -> {}
            }
            binding.executeWithAction { uiState = FavoriteUiState(it) }
        }
    }

    override fun onViewClicked(view: View?) {
        when (view) {
            binding.header.ivLeftIcon -> {
                findNavController().popBackStack()
            }
        }
    }

}