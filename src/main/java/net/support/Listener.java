package net.support;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.support.database.Server;
import net.dv8tion.jda.api.events.ReadyEvent;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class Listener extends ListenerAdapter {

    private CommandMap cmdmap;

    public Listener(Support main) {
        cmdmap = main.getCommandMap();
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        Support.getJda().getPresence().setActivity(Activity.of(Activity.ActivityType.fromKey(0), Support.getJda().getGuilds().size() + " Sunucuda | b!yardim | https://bunny.team"));
        Message message = event.getMessage();
        String content = message.getContentRaw();
        if (message.getAuthor().getId().equals("733721372206825502")){
            return;
        }
        Server server = Support.getProfile(event.getMember().getGuild().getId());
        if (message.getChannel().getId().equals(server.getChannel())) {
            message.delete().queue();
            if (event.getGuild().getTextChannelsByName("talep-" + event.getMember().getId(), false).size() != 0) {
                MessageEmbed embed = new EmbedBuilder()
                        .setTitle(message.getAuthor().getName())
                        .setDescription("Zaten aktif bir talebin bulunmakta.")
                        .setColor(Color.MAGENTA)
                        .addField("Mesajınız", message.getContentRaw(), false)
                        .setFooter("Bunny Moderasyon")
                        .setThumbnail(message.getAuthor().getAvatarUrl())
                        .build();
                event.getGuild().getTextChannelsByName("talep-" + event.getMember().getId(), false).get(0).sendMessage(embed).queue();
                return;
            }
            event.getGuild().createTextChannel("talep-" + event.getMember().getId())
                    .addPermissionOverride(event.getGuild().getRolesByName("Destek Ekibi", false).get(0), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(event.getGuild().getRolesByName("@everyone", false).get(0), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .queue();
            MessageEmbed embed = new EmbedBuilder()
                    .setTitle(message.getAuthor().getName())
                    .setDescription("Destek talebi kanalına hoşgeldin.")
                    .setColor(Color.MAGENTA)
                    .addField("Bekleyiniz!", "Destek ekibi en kısa süre içinde sizinle ilgilenecektir", false)
                    .addField("Mesajınız", message.getContentRaw(), false)
                    .addField("Destek talebini kapatmak için ``b!kapat`` yazınız.", "", false)
                    .setFooter("Bunny Moderasyon")
                    .setThumbnail(message.getAuthor().getAvatarUrl())
                    .build();
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    event.getGuild().getTextChannelsByName("talep-" + message.getAuthor().getId(), false).get(0).sendMessage(embed).queue();
                }
            }, 1 * 500);

        }
            if (content.startsWith("b!") && !content.equals("b!")) {
                String content2 = content.replaceFirst("b!", "");
                String[] args = content2.split(" ");
                if (cmdmap.getCommand(args[0]) != null) {
                    String[] args2 = Arrays.copyOfRange(args, 1, args.length);
                    cmdmap.getCommand(args[0]).execute(event.getMember(), message, args2);
                    message.delete().queue();
                }
            }

    }

    @Override
    public void onGuildJoin(@Nonnull GuildJoinEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setColor(Color.ORANGE)
                .addField("Merhaba", "Beni sunucunuza eklediğiniz için teşekkürler. Sunucunuzla beraber artık "+Support.getJda().getGuilds().size()+" sunucuda çalışıyorum.", false)
                .addField("Kurulum", "Link", false)
                .addField("Web sitemiz", "https://bunny.team",false)
                .build();
        event.getGuild().getTextChannels().get(0).sendMessage(embed).queue();
        Support.saveProfile(new Server(event.getGuild().getId(), "1", "b!mesaj-ayarla <mesaj> ile bu mesajı düzenleyiniz.", "Bunny Moderasyon", "1", "1"));
    }

    public void onConnect(GenericEvent event) {
    }
}
