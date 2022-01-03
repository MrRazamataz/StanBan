package raza.$stanban;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;

public class PluginMain extends JavaPlugin implements Listener {

	private static PluginMain instance;

	@Override
	public void onEnable() {
		instance = this;
		getDataFolder().mkdir();
		getServer().getPluginManager().registerEvents(this, this);
		try {
			PluginMain.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&',
					"[&cStanBan&r] &aPlugin has been enabled and is active. Ready!"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		try {
			PluginMain.getInstance().getLogger()
					.info(ChatColor.translateAlternateColorCodes('&', "[&cStanBan&r] &cPlugin has been disabled."));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] commandArgs) {
		return true;
	}

	public static void procedure(String procedure, List procedureArgs) throws Exception {
	}

	public static Object function(String function, List functionArgs) throws Exception {
		return null;
	}

	public static List createList(Object obj) {
		if (obj instanceof List) {
			return (List) obj;
		}
		List list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			int length = java.lang.reflect.Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				list.add(java.lang.reflect.Array.get(obj, i));
			}
		} else if (obj instanceof Collection<?>) {
			list.addAll((Collection<?>) obj);
		} else if (obj instanceof Iterator) {
			((Iterator<?>) obj).forEachRemaining(list::add);
		} else {
			list.add(obj);
		}
		return list;
	}

	public static void createResourceFile(String path) {
		Path file = getInstance().getDataFolder().toPath().resolve(path);
		if (Files.notExists(file)) {
			try (InputStream inputStream = PluginMain.class.getResourceAsStream("/" + path)) {
				Files.createDirectories(file.getParent());
				Files.copy(inputStream, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static PluginMain getInstance() {
		return instance;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void event1(org.bukkit.event.player.PlayerJoinEvent event) throws Exception {
		if ((((boolean) String.valueOf(((org.bukkit.entity.Player) event.getPlayer()))
				.contains(String.valueOf("Dream")))
				|| ((boolean) String.valueOf(((org.bukkit.entity.Player) event.getPlayer()))
						.contains(String.valueOf("NotFound"))))) {
			((org.bukkit.BanList) org.bukkit.Bukkit
					.getBanList(((org.bukkit.BanList.Type) org.bukkit.BanList.Type.NAME))).addBan(
							((java.lang.String) ((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event
									.getPlayer())).getName()),
							ChatColor.translateAlternateColorCodes('&',
									"&cYou have been detected as a Dream or GeorgeNotFound stan!"),
							((java.util.Date) null), ChatColor.translateAlternateColorCodes('&', "[&cStanBan&r]"));
			org.bukkit.Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
					"[&cStanBan&r] &a&lA stan was detected and was banned from the server!"));
		}
	}
}
