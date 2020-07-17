package net.support.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.support.Support;
import net.support.database.Server;

import java.awt.*;
import java.util.List;

public class ChannelSet extends Command {

    public ChannelSet(){
        super("kanal-ayarla", "Destek kanalı ayarlamanızı sağlar.", "b!kanal-ayarla <kanal>");
    }
    @Override
    public void execute(Member user, Message message, String[] args) {
    if (user.hasPermission(Permission.ADMINISTRATOR)){
        if (!(args.length < 1)){
           TextChannel channel = user.getGuild().getTextChannelById(args[0].replace("<#", "").replace(">", ""));
            if (channel == null){
                MessageEmbed embed = new EmbedBuilder()
                        .setAuthor("HATA")
                        .setDescription("Böyle bir kanal bulunamadı.")
                        .setColor(Color.RED)
                        .build();
                message.getChannel().sendMessage(embed).queue();
                return;
            }
            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("Başarılı")
                    .setDescription("Destek talebi kanalı ayarlandı.")
                    .setColor(Color.GREEN)
                    .build();
            message.getChannel().sendMessage(embed).queue();
            Server server = Support.getProfile(user.getGuild().getId());
            server.setChannel(channel.getId());
            Support.saveProfile(server);
            MessageEmbed embed2 = new EmbedBuilder()
                    .setAuthor(server.getTitle())
                    .setDescription(server.getMessage())
                    .setColor(Color.GREEN)
                    .build();
            channel.sendMessage(embed2).queue();
            server.setMessageid(channel.getLatestMessageId());
            Support.saveProfile(server);


        }else{
            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("HATA")
                    .setDescription(this.getUsage())
                    .setColor(Color.RED)
                    .build();
            message.getChannel().sendMessage(embed).queue();        }
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
