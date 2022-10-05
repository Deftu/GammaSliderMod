package xyz.deftu.gsm

import gg.essential.api.EssentialAPI
import gg.essential.api.commands.Command
import gg.essential.api.commands.DefaultHandler
import xyz.deftu.gsm.gui.GammaSliderMenu

object GammaSliderCommand : Command(
    name = GammaSliderMod.ID
) {
    @DefaultHandler
    fun execute() {
        EssentialAPI.getGuiUtil().openScreen(GammaSliderMenu())
    }
}
