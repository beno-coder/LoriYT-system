package de.loriyt.lobbysystem.Utils;

import de.loriyt.api.Manager.elo.ELO;
import de.loriyt.lobbysystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import javax.xml.bind.annotation.XmlElementDecl;

public class ScoreboardUtil {
    private static Integer animationcount;

    private String[] animation = new String[] {
        "§b§lLoriYT &7| Lobby", "§b§lLoriYT &7| Lobb", "§b§lLoriYT &7| Lob", "§b§lLoriYT &7| Lo",
            "§b§lLoriYT &7| L", "§b§lLoriYT &7|", "§b§lLoriYT", "§b§lLoriY", "§b§lLori", "§b§lLor", "§b§lLo", "§b§lL", "§b§l",
            "§b§lL", "§b§lLo", "§b§lLor", "§b§lLori", "§b§lLoriY", "§b§lLoriYT", "§b§lLoriYT &7|", "§b§lLoriYT &7| L", "§b§lLoriYT &7| Lo",
            "§b§lLoriYT &7| Lob", "§b§lLoriYT &7| Lobb","§b§lLoriYT &7| Lobby",

    };


    public void setBoard(Player p) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("aaa", "bbb");
        obj.setDisplayName(animation[animationcount]);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);



        Score PL00 = obj.getScore("§k");
        Score PL0 = obj.getScore("§0");
        Score PL1 = obj.getScore("§f");
        Score PL2 = obj.getScore("§g");
        Score PL3 = obj.getScore("§h");

        Score Linie = obj.getScore("§7§m-----------------");
        Score Rank = obj.getScore("§8§l» §7Rank");
        Score getRank = obj.getScore("§8» " + Main.getInstance().getCloudPermission().getRankColor(p.getUniqueId()).replace("&", "§") + Main.getInstance().getCloudPermission().getRankName(p.getUniqueId()));
        Score coins = obj.getScore("§8§l» §7Coins");
        Score getcoins = obj.getScore("§d");
        Score globalranking = obj.getScore("§8§l» §7Globales Ranking");
        Score getglobalranking = obj.getScore("§n");
        Score hoster = obj.getScore("§8§l» §7Hoster");
        Score gethoster = obj.getScore("§8» §b§lHT-Hosting");


        Team Coins = board.registerNewTeam("coins");
        Team Globalranking = board.registerNewTeam("ranking");

        Globalranking.addEntry("§n");
        if(ELO.getElo(p) <= 250) {
            Globalranking.setPrefix("§cBronze§7[I§7]");
        }else if(ELO.getElo(p) >= 500) {
            Globalranking.setPrefix("§cBronze§7[II§7]");
        }


        Linie.setScore(17);
        PL00.setScore(16);
        Rank.setScore(15);
        getRank.setScore(14);
        PL0.setScore(13);
        coins.setScore(12);
        getcoins.setScore(11);
        PL1.setScore(10);
        PL2.setScore(7);
        globalranking.setScore(6);
        getglobalranking.setScore(5);
        PL3.setScore(4);
        hoster.setScore(3);
        gethoster.setScore(2);
        p.setScoreboard(board);
    }

    public void updateScore(Player p) {
        Scoreboard board = p.getScoreboard();
        Team Globalranking = board.getTeam("ranking");
        Team Coins = board.getTeam("coins");
        Globalranking.addEntry("§n");
        if(ELO.getElo(p) <= 250) {
            Globalranking.setPrefix("§cBronze§7[I§7]");
        }else if(ELO.getElo(p) >= 500) {
            Globalranking.setPrefix("§cBronze§7[II§7]");
        }
    }


    public void start() {
        animationcount = 0;
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(current -> {
                    current.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(animation[animationcount]);
                });
                animationcount++;

                if (animationcount == animation.length)
                    animationcount = 0;
            }
        }, 0L, 8L);
    }
}
