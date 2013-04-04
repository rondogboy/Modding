package mods.tutorial;


import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxyTutorial extends CommonProxyTutorial
{
       @Override
       public void registerRenderThings()
       {
             RenderingRegistry.registerEntityRenderingHandler(EntityIceCream.class, new RenderIceCream(new ModelIceCream(), 0.3F));
             RenderingRegistry.registerEntityRenderingHandler(EntitySquidMob.class, new RenderSquidMob(new ModelSquidMob(), 0.3F));
       }
}