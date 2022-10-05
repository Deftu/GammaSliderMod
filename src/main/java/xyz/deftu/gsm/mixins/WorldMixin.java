package xyz.deftu.gsm.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.deftu.gsm.GammaSliderMod;

@Mixin(value = {World.class}, priority = 1001)
public class WorldMixin {
    @Inject(method = "checkLightFor", at = @At("HEAD"), cancellable = true)
    private void gsm$cancelLightingCheck(CallbackInfoReturnable<Boolean> cir) {
        if (Minecraft.getMinecraft().isCallingFromMinecraftThread() && GammaSliderMod.getGammaValue() >= 10) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = {
            "getLightFromNeighbors",
            "getLightFromNeighborsFor",
            "getRawLight",
            "getLight(Lnet/minecraft/util/BlockPos;)I",
            "getLight(Lnet/minecraft/util/BlockPos;Z)I"
    }, at = @At("HEAD"), cancellable = true)
    private void gsm$cancelGetLight(CallbackInfoReturnable<Integer> cir) {
        if (Minecraft.getMinecraft().isCallingFromMinecraftThread() && GammaSliderMod.getGammaValue() >= 10) {
            cir.setReturnValue(15);
        }
    }
}
