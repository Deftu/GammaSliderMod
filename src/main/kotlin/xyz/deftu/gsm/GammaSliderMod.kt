package xyz.deftu.gsm

import gg.essential.api.EssentialAPI
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
import org.lwjgl.input.Keyboard
import xyz.deftu.gsm.gui.GammaSliderMenu

@Mod(
    name = GammaSliderMod.NAME,
    version = GammaSliderMod.VERSION,
    modid = GammaSliderMod.ID
) class GammaSliderMod {
    companion object {
        const val NAME = "@MOD_NAME@"
        const val VERSION = "@MOD_VERSION"
        const val ID = "@MOD_ID@"

        @Mod.Instance
        lateinit var instance: GammaSliderMod
            private set
        @JvmStatic
        var gammaValue: Float
            get() = Minecraft.getMinecraft().gameSettings.gammaSetting
            set(value) {
                Minecraft.getMinecraft().gameSettings.gammaSetting = value
            }
    }

    private lateinit var keybind: KeyBinding

    @Mod.EventHandler
    fun initialize(event: FMLInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(this)
        keybind = KeyBinding("Open Menu", Keyboard.KEY_O, NAME)
        ClientRegistry.registerKeyBinding(keybind)
    }

    @Mod.EventHandler
    fun postInitialize(event: FMLPostInitializationEvent) {
        GammaSliderCommand.register()
    }

    @SubscribeEvent
    fun onKeyPressed(event: KeyInputEvent) {
        if (Minecraft.getMinecraft().currentScreen != null)
            return
        if (!Keyboard.getEventKeyState())
            return
        if (Keyboard.getEventKey() != keybind.keyCode)
            return

        EssentialAPI.getGuiUtil().openScreen(GammaSliderMenu())
    }
}
