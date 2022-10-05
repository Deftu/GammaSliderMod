package xyz.deftu.gsm.mixins;

import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.deftu.gsm.GammaSliderMod;

@Mixin(value = {Chunk.class}, priority = 1001)
public class ChunkMixin {
    @Inject(method = {"getLightFor", "getLightSubtracted"}, at = @At("HEAD"), cancellable = true)
    private void gsm$cancelLighting(CallbackInfoReturnable<Integer> cir) {
        if (GammaSliderMod.getGammaValue() >= 10) {
            cir.setReturnValue(15);
        }
    }
}
