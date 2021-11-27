package com.example.poketwordtranslator.presentation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poketwordtranslator.R
import com.example.poketwordtranslator.databinding.ActivityMainBinding
import com.example.poketwordtranslator.domain.presenter.MainPresenter
import com.example.poketwordtranslator.domain.presenter.MainPresenterImpl
import com.example.poketwordtranslator.presentation.adapter.TranslationListAdapter
import com.example.poketwordtranslator.presentation.model.AppViewState
import com.example.poketwordtranslator.presentation.model.TranslateWordUi
import com.example.poketwordtranslator.presentation.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainPresenter: MainPresenter =
        MainPresenterImpl()
    private val transitionListAdapter by lazy {
        TranslationListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        attach()
        init()
    }


    override fun init() {
        binding.mainContent.translationList.run {
            adapter = transitionListAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
        binding.wordSearchButton.setOnClickListener {
            showEnterWordDialog()
        }
        showMainContent()
    }

    override fun attach() {
        mainPresenter.attachView(this)
    }

    override fun detach() {
        mainPresenter.detachView(this)
    }

    override fun handlingData(viewState: AppViewState) {
        when (viewState) {
            is AppViewState.Success<*> -> {
                showMainContent()
                val loadData = (viewState.data as List<*>).map { it as TranslateWordUi }
                transitionListAdapter.submitList(loadData)
            }
            is AppViewState.Loading -> {
                showLoading()
            }
            is AppViewState.Error -> {
                showError()
            }
        }
    }

    private fun showEnterWordDialog() {
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.word_enter_title))
            .setView(editText)
            .setCancelable(false)
            .setPositiveButton(R.string.translate) { dialog, _ ->
                mainPresenter.getData(editText.editableText.toString(), true)
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun showMainContent() {
        binding.run {
            mainContent.root.visibility = View.VISIBLE
            loadingIndicatorContent.visibility = View.GONE
            wordSearchButton.isEnabled = true
        }
    }

    private fun showLoading() {
        binding.run {
            mainContent.root.visibility = View.GONE
            loadingIndicatorContent.visibility = View.VISIBLE
            wordSearchButton.isEnabled = false
        }
    }

    private fun showError() {
        AlertDialog.Builder(this)
            .setMessage(R.string.error_message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                showMainContent()
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroy() {
        detach()
        super.onDestroy()
    }
}