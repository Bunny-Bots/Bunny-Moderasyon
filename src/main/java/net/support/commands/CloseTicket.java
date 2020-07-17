package net.support.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.support.Support;

import java.awt.*;

public class CloseTicket extends Command{

    public CloseTicket(){
        super("kapat", "Destek talebini kapatmanızı sağlar.", "b!kapat");
    }

    @Override
    public void execute(Member user, Message message, String[] args) {
     String name = message.getChannel().getName();
     if (name.indexOf("talep-") != -1){
        message.getGuild().getTextChannelById(message.getChannel().getId()).delete().reason("Talep").queue();
         MessageEmbed embed = new EmbedBuilder()
                 .setAuthor("Destek talebi kapatıldı")
                 .addField("Kapatan", user.getUser().getAsMention(), false)
                 .setColor(Color.LIGHT_GRAY)
                 .build();
         user.getGuild().getTextChannelById(Support.getProfile(user.getGuild().getId()).getLog()).sendMessage(embed).queue();
     }
    }
}
