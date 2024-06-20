package com.acuon.sampleapp.ui

import android.view.View
import com.acuon.sampleapp.R
import com.acuon.sampleapp.common.BaseActivity
import com.acuon.sampleapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId() = R.layout.activity_main

    override fun setupViews() {}

    override fun bindViewModel() {}

    override fun onViewClicked(view: View?) {}
}