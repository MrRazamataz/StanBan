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
		PluginMain.createResourceFile("banned.txt");
		saveDefaultConfig();
		try {
			if (PluginMain.hasUpdate("98924", true)) {
				PluginMain.getInstance().getLogger()
						.warning((ChatColor.translateAlternateColorCodes('&',
								"[&cStanBan&r] &6There is an update available which is: ")
								+ PluginMain.getNewVersion("98924", true)));
			}
			PluginMain.getInstance().getLogger()
					.info((ChatColor.translateAlternateColorCodes('&', "[&cStanBan&r] &6Running version: ")
							+ PluginMain.getInstance().getDescription().getVersion()));
			new Metrics(PluginMain.getInstance(), ((int) (13846d)));
			PluginMain.getInstance().getLogger()
					.info(ChatColor.translateAlternateColorCodes('&', "[&cStanBan&r] Loading config..."));
			PluginMain.getInstance().getLogger()
					.info(ChatColor.translateAlternateColorCodes('&', "[&cStanBan&r] Config loaded!"));
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
		if (command.getName().equalsIgnoreCase("unbanallstans")) {
			try {
				commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aUnbanning all stans..."));
				for (Object FINAL_loopValue1 : java.nio.file.Files.readAllLines(
						new File(String.valueOf(PluginMain.getInstance().getDataFolder()), "banned.txt").toPath(),
						java.nio.charset.StandardCharsets.UTF_8)) {
					((org.bukkit.BanList) org.bukkit.Bukkit
							.getBanList(((org.bukkit.BanList.Type) org.bukkit.BanList.Type.NAME)))
									.pardon(String.valueOf(FINAL_loopValue1));
					commandSender.sendMessage((ChatColor.translateAlternateColorCodes('&',
							ChatColor.translateAlternateColorCodes('&', "&aUnbanned: &c"))
							+ String.valueOf(FINAL_loopValue1)));
				}
				new File(String.valueOf(PluginMain.getInstance().getDataFolder()), "banned.txt").delete();
				new File(String.valueOf(PluginMain.getInstance().getDataFolder()), "banned.txt").createNewFile();
				commandSender
						.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aAll stans have been unbanned!"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		if (command.getName().equalsIgnoreCase("stanbanreload")) {
			try {
				PluginMain.getInstance().reloadConfig();
				if ((commandSender instanceof org.bukkit.entity.Player)) {
					((org.bukkit.entity.Player) (Object) commandSender)
							.sendTitle(
									ChatColor.translateAlternateColorCodes('&',
											ChatColor.translateAlternateColorCodes('&',
													"[&cStanBan&r] &aConfig reloaded!")),
									((java.lang.String) null), ((int) (0d)), ((int) (100d)), ((int) (0d)));
				}
				PluginMain.getInstance().getLogger().info(
						ChatColor.translateAlternateColorCodes('&', "[&cStanBan&r] &aThe config has been reloaded!"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
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
		Object $24c5b3a5b3df7260f1023d2b3f1dcdcc = null;
		$24c5b3a5b3df7260f1023d2b3f1dcdcc = ((java.lang.Object) (Object) true);
		for (Object FINAL_loopValue1 : ((java.util.List) (Object) PluginMain.getInstance().getConfig()
				.get("stanban.names"))) {
			if (((boolean) String.valueOf(((org.bukkit.entity.Player) event.getPlayer()))
					.contains(String.valueOf(FINAL_loopValue1)))) {
				((org.bukkit.BanList) org.bukkit.Bukkit
						.getBanList(((org.bukkit.BanList.Type) org.bukkit.BanList.Type.NAME))).addBan(
								((java.lang.String) ((org.bukkit.command.CommandSender) (Object) ((org.bukkit.entity.Player) event
										.getPlayer())).getName()),
								ChatColor.translateAlternateColorCodes('&', ((java.lang.String) String
										.valueOf(PluginMain.getInstance().getConfig().get("stanban.ban-message"))
										.replaceAll("%player%",
												((java.lang.String) ((org.bukkit.entity.Player) event.getPlayer())
														.getDisplayName())))),
								((java.util.Date) null), ChatColor.translateAlternateColorCodes('&', "[&cStanBan&r]"));
				((org.bukkit.entity.Player) event.getPlayer()).kickPlayer(ChatColor.translateAlternateColorCodes('&',
						((java.lang.String) String
								.valueOf(PluginMain.getInstance().getConfig().get("stanban.ban-message")).replaceAll(
										"%player%", ((java.lang.String) ((org.bukkit.entity.Player) event.getPlayer())
												.getDisplayName())))));
				if (((boolean) (Object) $24c5b3a5b3df7260f1023d2b3f1dcdcc)) {
					org.bukkit.Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
							((java.lang.String) String
									.valueOf(PluginMain.getInstance().getConfig().get("stanban.chat-broadcast-message"))
									.replaceAll("%player%",
											((java.lang.String) ((org.bukkit.entity.Player) event.getPlayer())
													.getDisplayName())))));
					$24c5b3a5b3df7260f1023d2b3f1dcdcc = ((java.lang.Object) (Object) false);
					if (((boolean) (Object) PluginMain.getInstance().getConfig().get("actions.enabled"))) {
						for (Object FINAL_loopValue2 : ((java.util.List) (Object) PluginMain.getInstance().getConfig()
								.get("actions.on-stan-join-commands"))) {
							org.bukkit.Bukkit.dispatchCommand(
									((org.bukkit.command.CommandSender) (Object) ((org.bukkit.command.ConsoleCommandSender) org.bukkit.Bukkit
											.getConsoleSender())),
									((java.lang.String) String.valueOf(FINAL_loopValue2).replaceAll("%player%",
											((java.lang.String) ((org.bukkit.entity.Player) event.getPlayer())
													.getDisplayName()))));
						}
					}
					java.nio.file.Files.write(
							new File(String.valueOf(PluginMain.getInstance().getDataFolder()), "banned.txt").toPath(),
							Collections.singleton(((java.lang.String) ((org.bukkit.entity.Player) event.getPlayer())
									.getDisplayName())),
							java.nio.charset.StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.APPEND);
				}
			}
		}
	}

	public static boolean hasUpdate(String resourceId, boolean throwInError) {
		boolean hasUpdate = false;
		try (java.io.InputStream inputStream = new java.net.URL(
				"https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openStream();
				java.util.Scanner scanner = new java.util.Scanner(inputStream)) {
			if (scanner.hasNext())
				hasUpdate = !PluginMain.getInstance().getDescription().getVersion().equalsIgnoreCase(scanner.next());
		} catch (java.io.IOException ioException) {
			hasUpdate = false;
			if (throwInError)
				ioException.printStackTrace();
		}
		return hasUpdate;
	}

	public static String getNewVersion(String resourceId, boolean throwInError) {
		String newVersion = PluginMain.getInstance().getDescription().getVersion();
		try (java.io.InputStream inputStream = new java.net.URL(
				"https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openStream();
				java.util.Scanner scanner = new java.util.Scanner(inputStream)) {
			if (scanner.hasNext())
				newVersion = String.valueOf(scanner.next());
		} catch (java.io.IOException ioException) {
			newVersion = "Sal4iDev#4767";
			if (throwInError)
				ioException.printStackTrace();
		}
		return newVersion;
	}
}
