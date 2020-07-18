package net.support.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.support.Support;
import net.support.database.Server;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class SetMessage extends Command{

    public SetMessage(){
        super("mesaj-ayarla", "Talep kanalındaki bot mesajını düzenlemenizi sağlar.", "b!mesaj-ayarla <başlık> <mesaj>");
    }
    @Override
    public void execute(Member user, Message message, String[] args) {
        if (user.hasPermission(Permission.ADMINISTRATOR)) {
            if (!(args.length < 2)) {
                Server server = Support.getProfile(user.getGuild().getId());
                if (server.getChannel().equals("1")){
                    MessageEmbed embed1 = new EmbedBuilder()
                            .setAuthor("HATA")
                            .setDescription("Destek talebi kanalı ayarlanmamış.")
                            .setColor(Color.RED)
                            .build();
                    message.getChannel().sendMessage(embed1).queue();
                    return;
                }
                server.setTitle(args[0]);
                String[] args2 = Arrays.copyOfRange(args, 1, args.length);
                StringBuffer sb = new StringBuffer();
                    for(int i = 0; i < args2.length; i++) {
                        sb.append(" "+args2[i]);
                    }
                    server.setMessage(sb.toString());
                Support.saveProfile(server);
                TextChannel channel = user.getGuild().getTextChannelById(server.getChannel());
                MessageEmbed embed = new EmbedBuilder()
                        .setAuthor("Başarılı")
                        .setDescription("Destek talebi kanalı mesajı düzenlendi.")
                        .setColor(Color.GREEN)
                        .build();
                MessageEmbed embed2 = new EmbedBuilder()
                        .setAuthor(server.getTitle())
                        .setDescription(server.getMessage())
                        .setColor(Color.CYAN)
                        .build();
                    channel.sendMessage(embed2).queue();
                    if (!channel.getLatestMessageId().isEmpty())
                    server.setMessageid(channel.getLatestMessageId());
                    Support.saveProfile(server);
                message.getChannel().sendMessage(embed).queue();

            }else{
                MessageEmbed embed = new EmbedBuilder()
                        .setAuthor("HATA")
                        .setDescription(this.getUsage())
                        .setColor(Color.RED)
                        .build();
                message.getChannel().sendMessage(embed).queue();            }
            }else{
            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("HATA")
                    .setDescription("Bu komutu kullanabilmek için ``ADMINISTRATOR`` yetkisine sahip olmalısın")
                    .setColor(Color.RED)
                    .build();
            message.getChannel().sendMessage(embed).queue();
        }
    }
}
