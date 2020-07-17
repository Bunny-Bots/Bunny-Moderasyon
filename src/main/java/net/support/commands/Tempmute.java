package net.support.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import net.support.Support;

import java.awt.*;
import java.sql.Time;
import java.util.*;

public class Tempmute extends Command {

    public Tempmute(){
        super("sustur", "Bir oyuncuyu susturmanızı sağlar", "b!sustur (kullanıcı) (süre) (süre formatı[saat/dakika/saniye]) (sebep)");


    }
    @Override
    public void execute(Member user, Message message, String[] args) {

        if (user.hasPermission(Permission.KICK_MEMBERS)) {
            if (!(args.length < 4)){
                HashMap<String, Integer> format = new HashMap<>();
                format.put("saniye", 1000);
                format.put("dakika", 60000);
                format.put("saat", 3600000);
                if (format.get(args[2]) == null){
                    MessageEmbed embed4 = new EmbedBuilder()
                            .setDescription("Geçersiz süre formatı.")
                            .setColor(Color.RED)
                            .build();
                    message.getChannel().sendMessage(embed4).queue();
                    return;
                }
                String id1 = args[0].replace("<@!", "").replace(">", "");
                Member member = user.getGuild().getMemberById(id1);
                String[] messages = Arrays.copyOfRange(args, 3, args.length);
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < messages.length; i++) {
                    sb.append(" "+messages[i]);
                }
               int num;
                try{
                    num = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    MessageEmbed embed3 = new EmbedBuilder()
                            .setDescription("Sayısal bir değer giriniz.")
                            .setColor(Color.RED)
                            .build();
                    message.getChannel().sendMessage(embed3).queue();
                    return;
                }
                MessageEmbed embed = new EmbedBuilder()
                        .setAuthor(member.getUser().getName())
                        .setDescription("Oyuncu susturuldu.")
                        .setThumbnail(member.getUser().getAvatarUrl())
                        .addField("Süre", args[1]+" "+args[2], false)
                        .addField("Susturan kişi", user.getUser().getAsMention(), false)
                        .addField("Sebep", sb.toString(), false)
                        .setColor(Color.GREEN)
                        .build();
                message.getChannel().sendMessage(embed).queue();

                user.getGuild().getTextChannelById(Support.getProfile(user.getGuild().getId()).getLog()).sendMessage(embed).queue();
                for (GuildChannel channel : user.getGuild().getChannels()) {
                    channel.putPermissionOverride(member)
                    .setDeny(Permission.MESSAGE_WRITE)
                    .queue();
                }
                new Timer().schedule(new TimerTask(){

                    @Override
                    public void run() {
                        for (GuildChannel channel : user.getGuild().getChannels()) {
                            channel.putPermissionOverride(member)
                                    .setAllow(Permission.MESSAGE_WRITE)
                                    .queue();
                        }
                        MessageEmbed embed = new EmbedBuilder()
                                .setAuthor("Süre doldu!")
                                .setDescription(member.getUser().getAsMention()+" artık konuşabilirsin.")
                                .setThumbnail(member.getUser().getAvatarUrl())
                                .setColor(Color.BLUE)
                                .build();
                        message.getChannel().sendMessage(embed).queue();
                    }
                }, num*format.get(args[2]));

        }else {
                MessageEmbed embed = new EmbedBuilder()
                        .setAuthor("HATA")
                        .setDescription(this.getUsage())
                        .setColor(Color.RED)
                        .build();
                message.getChannel().sendMessage(embed).queue();
            }
        }else{
            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("HATA")
                    .setDescription("Bu komutu kullanabilmek için ``KICK_MEMBERS`` yetkisine sahip olmalısın")
                    .setColor(Color.RED)
                    .build();
            message.getChannel().sendMessage(embed).queue();
        }
    }
}
