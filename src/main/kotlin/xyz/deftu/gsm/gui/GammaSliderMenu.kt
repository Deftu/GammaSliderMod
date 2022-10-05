package xyz.deftu.gsm.gui

import gg.essential.universal.UMatrixStack
import gg.essential.universal.UScreen
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import net.minecraftforge.fml.client.config.GuiSlider
import xyz.deftu.gsm.GammaSliderMod
import java.awt.Color

class GammaSliderMenu : UScreen(
    restoreCurrentGuiOnClose = true
) {
    private var initialized = false

    private lateinit var slider: GuiSlider
    private var value = 0f

    private fun updateValue() {
        if (!initialized)
            return

        value = slider.value.toFloat()
        GammaSliderMod.gammaValue = value / 100
        Minecraft.getMinecraft().renderGlobal.loadRenderers()
        Minecraft.getMinecraft().gameSettings.saveOptions()
    }

    override fun initScreen(width: Int, height: Int) {
        slider = (object : GuiSlider(
            0,
            width / 2 - 50,
            height / 2,
            100,
            20,
            "Gamma: ",
            "%",
            0.0,
            1000.0,
            GammaSliderMod.gammaValue * 100.0,
            false,
            true
        ) {
            override fun mouseReleased(mouseX: Int, mouseY: Int) {
                super.mouseReleased(mouseX, mouseY)
                updateValue()
            }
        }).also(buttonList::add)
        value = slider.value.toFloat()

        buttonList.add(GuiButton(1, width / 2 - 50, height / 2 + 25, 100, 20, "Done"))
        initialized = true
    }

    private fun createChromaEffect() = Color.HSBtoRGB(System.currentTimeMillis() % 2000L / 2000f, 1f, 1f)

    override fun onDrawScreen(matrixStack: UMatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        fontRendererObj.drawString(GammaSliderMod.NAME, width / 2 - fontRendererObj.getStringWidth(GammaSliderMod.NAME) / 2, height / 2 - 15, createChromaEffect())
        super.onDrawScreen(matrixStack, mouseX, mouseY, partialTicks)
    }

    override fun actionPerformed(button: GuiButton) {
        if (!button.enabled) return
        when (button.id) {
            0 -> updateValue()
            1 -> {
                updateValue()
                restorePreviousScreen()
            }
        }
    }

    override fun onScreenClose() {
        super.onScreenClose()
        if (!initialized)
            return

        updateValue()
    }

    override fun doesGuiPauseGame() = false
}
