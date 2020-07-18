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
         MessageEmbed embed = new EmbedBuilder()
                 .setAuthor("Destek talebi kapatıldı")
                 .addField("Kanal", message.getChannel().getName(), false)
                 .addField("Talebi oluşturan", user.getGuild().getMemberById(message.getChannel().getName().replace("talep-", "")).getUser().getAsMention(), true)
                 .addField("Kapatan", user.getUser().getAsMention(), true)
                 .setColor(Color.LIGHT_GRAY)
                 .build();
        message.getGuild().getTextChannelById(message.getChannel().getId()).delete().reason("Talep").queue();

         user.getGuild().getTextChannelById(Support.getProfile(user.getGuild().getId()).getLog()).sendMessage(embed).queue();
     }
    }
}
