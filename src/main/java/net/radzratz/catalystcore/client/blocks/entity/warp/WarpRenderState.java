package net.radzratz.catalystcore.client.blocks.entity.warp;

import java.io.IOException;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

@EventBusSubscriber(modid = "catalystcore", value = Dist.CLIENT)
public class WarpRenderState extends RenderStateShard
{

    public static ShaderInstance warpPortalShader;
    public static final long START_TIME = System.currentTimeMillis();

    public WarpRenderState(String name, Runnable setupState, Runnable clearState)
    {
        super(name, setupState, clearState);
    }

    @SuppressWarnings("null")
    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException
    {
        event.registerShader(
            new ShaderInstance(
                event.getResourceProvider(),
                ResourceLocation.fromNamespaceAndPath("catalystcore", "warp_portal"),
                DefaultVertexFormat.POSITION_TEX_COLOR
            ),
            shaderInstance -> warpPortalShader = shaderInstance
        );
    }

    @SuppressWarnings("null")
    public static RenderType getWarpPortalRenderType() {
        return RenderType.create(
            "catalystcore:warp_portal",
            DefaultVertexFormat.POSITION_TEX_COLOR,
            VertexFormat.Mode.QUADS,
            256,
            false,
            true,
            RenderType.CompositeState.builder()
                .setShaderState(new ShaderStateShard(() -> {
                    if(warpPortalShader != null)
                    {
                        if(warpPortalShader.getUniform("WarpTime") != null)
                        {
                            float time = (System.currentTimeMillis() - START_TIME) / 1000.0f;
                            warpPortalShader.safeGetUniform("WarpTime").set(time);
                        }
                    }
                    return warpPortalShader;
                }))
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setDepthTestState(LEQUAL_DEPTH_TEST)
                .setWriteMaskState(COLOR_DEPTH_WRITE)
                .createCompositeState(false)
        );
    }
}