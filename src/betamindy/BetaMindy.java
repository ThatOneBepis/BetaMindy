package betamindy;

import arc.*;
import arc.func.*;
import arc.util.Log;
import mindustry.*;
import mindustry.ctype.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.mod.Mods.*;
import betamindy.content.*;

public class BetaMindy extends Mod{
    public static final String githubURL = "https://github.com/sk7725/BetaMindy";

    private final ContentList[] mindyContent = {
        new OverWriter(),
        new MindyStatusEffects(),
        new MindyBullets(),
        new MindyBlocks()
    };

    public BetaMindy() {
        super();
        MindySounds.load();

        Events.on(DisposeEvent.class, e -> {
            MindySounds.dispose();
        });

        Core.settings.defaults("slimeeffect", true);
    }

    @Override
    public void init(){
        Vars.enableConsole = true;

        if(!Vars.headless){
            //Partial credits to ProjectUnity
            LoadedMod mod = Vars.mods.locateMod("betamindy");
            Func<String, String> stringf = value -> Core.bundle.get("mod." + value);

            mod.meta.displayName = stringf.get(mod.meta.name + ".name");
            mod.meta.description = stringf.get(mod.meta.name + ".description");
            mod.meta.version = mod.meta.version + "\n" + stringf.get(mod.meta.name + ".short");
            mod.meta.author = "[royal]" + mod.meta.author + "[]";
        }
    }

    @Override
    public void loadContent(){
        for(ContentList list : mindyContent){
            list.load();

            Log.info("@: Loaded content list: @", getClass().getSimpleName(), list.getClass().getSimpleName());
        }
    }
}
