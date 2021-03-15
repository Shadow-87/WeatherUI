package ir.ShadowMiner.WeatherUI;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.event.EventHandler;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.plugin.PluginBase;

public class WeatherUI extends PluginBase implements Listener{

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final FormWindowSimple wui = new FormWindowSimple("§bWeatherUI", "§6Please select weather you want");
        wui.addButton(new ElementButton("§fClear"));
        wui.addButton(new ElementButton("§9Rain"));
        wui.addButton(new ElementButton("§8Thunder Storm!"));
        final Player p = (Player)sender;
        final String lowerCase;
        switch (lowerCase = command.getName().toLowerCase()) {
            case "wui": {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cONLY USE THIS COMMAND INGAME!");
                    return false;
                }
                if (!p.hasPermission("wui.cmd")) {
                    p.sendMessage("§cSorry, but you don't have permission to use this command :(");
                    return false;
                }
                p.showFormWindow((FormWindow)wui);
                break;
            }
            default:
                break;
        }
        return true;
    }

    @EventHandler
    public void onResponse(final PlayerFormRespondedEvent event) {
        final Player player = event.getPlayer();
        final FormWindowSimple fw = (FormWindowSimple)event.getWindow();
        if (fw.getTitle().equals("§bWeatherUI")) {
            if (fw.getResponse().getClickedButton().getText().equals("§fClear")){
                this.getServer().dispatchCommand(event.getPlayer(), "weather clear");
            }
            if (fw.getResponse().getClickedButton().getText().equals("§9Rain")){
                this.getServer().dispatchCommand(event.getPlayer(), "weather rain");
            }
            if (fw.getResponse().getClickedButton().getText().equals("§8Thunder Storm!")){
                this.getServer().dispatchCommand(event.getPlayer(), "weather thunder");
            }
        }
    }
}
