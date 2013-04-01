package net.loonat.junaid;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class CmdBukkit extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if(cmd.getName().equalsIgnoreCase("cmd")) {
    		String cmdl = implode(args).trim();
    		return executeCommand(sender, cmdl);
    	}
    	return false;
    }
    
    private String implode(String[] strParts) {
    	StringBuilder retSb = new StringBuilder();
    	for (int j=0; j < strParts.length; j++) {
    		retSb.append(strParts[j] + " ");
    	}
    	return retSb.toString();
    }
    
    private boolean executeCommand(CommandSender sender, String cmdl) {
	boolean retval = false;
	if (sender.isOp()) {
    		try {
	    		for (int j=0; j < 20; j++) {
    				sender.sendMessage("");
    			}
		    	sender.sendMessage(sender.getName() + ":~$ " + cmdl);
		    	Process ePr = Runtime.getRuntime().exec(cmdl);
	    		ePr.waitFor();
		    	BufferedReader buf = new BufferedReader(new InputStreamReader(ePr.getInputStream()));
		    	String line = "";
	    		while ((line=buf.readLine())!=null) {
		    		sender.sendMessage(line);
		    	}
			retval = true;
	    	} catch (Exception e) {
    			sender.sendMessage("[Exception] " + e.getMessage());
    		}
	}
    	return retval;
    }

}
