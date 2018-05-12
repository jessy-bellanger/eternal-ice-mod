package fr.zeshi.eice;

import fr.zeshi.eice.proxy.CommonProxy;
import fr.zeshi.eice.util.References;
import fr.zeshi.eice.util.registering.RegisteringEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
@Mod(modid = References.MOD_ID, name = References.NAME, version = References.VERSION)
public class EIMain {

    @SuppressWarnings("unused")
    @Mod.Instance(References.MOD_ID)
    public static EIMain instance;

    @SuppressWarnings("unused")
    @SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.SERVER_PROXY)
    private static CommonProxy proxy;

    public EIMain() {
        MinecraftForge.EVENT_BUS.register(new RegisteringEvent());
    }

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit();
    }

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init();
    }

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit();
    }

}
