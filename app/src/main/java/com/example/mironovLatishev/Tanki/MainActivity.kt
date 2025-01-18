package com.example.mironovLatishev.Tanki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import com.example.mironovLatishev.Tanki.Direction.*
import com.example.mironovLatishev.Tanki.databinding.ActivityMainBinding

const val CELL_SIZE = 50

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val editMode = false
    private val gridDrawer by lazy {
        GridDrawer(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Menu"
    }

    private fun switchEditMode() {
        if (editMode) {
            gridDrawer.removeGrid()
        } else {
            gridDrawer.drawGrid()
        }
        editMode = !editMode
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                switchEditMode()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            19 -> move(UP)
            20 -> move(DOWN)
            21 -> move(LEFT)
            22 -> move(RIGHT)
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun move(direction: Direction) {
        when(direction) {
            UP -> {
                binding.myTank.rotation = 0f
                if(binding.myTank.marginTop>0) {
                (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += -CELL_SIZE
                }
            }

            DOWN -> {
                binding.myTank.rotation = 180f
                if(binding.myTank.marginTop + binding.container.height
                    < binding.container.height / CELL_SIZE * CELL_SIZE) {
                    (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
                }
            }

            LEFT -> {
                binding.myTank.rotation = 270f
                if (binding.myTank.marginLeft > 0) {
                (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
                }
            }

            RIGHT -> {
                binding.myTank.rotation = 90f
                if (binding.myTank.marginLeft + binding.container.height
                    < binding.container.width / CELL_SIZE * CELL_SIZE)
                (binding.myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
            }
        }
        binding.container.removeView(binding.myTank)
        binding.container.addView(binding.myTank)
    }
}
