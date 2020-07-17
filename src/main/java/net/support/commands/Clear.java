package net.support.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.util.List;

public class Clear extends Command {

    public Clear(){
        super("temizle", "Belirli bir sayıdaki mesajları silmenizi sağlar", "b!temizle <sayı>");
    }
    @Override
    public void execute(Member user, Message message, String[] args) {
        TextChannel channel = message.getTextChannel();
        if (!(args.length < 1)){
            MessageHistory history = channel.getHistory();
            List<Message> messages = history.retrievePast(Integer.parseInt(args[0])).complete();
            channel.deleteMessages(messages).queue();
            MessageEmbed embed = new EmbedBuilder()
                    .setDescription(args[0]+" Mesaj silindi")
                    .setColor(Color.CYAN)
                    .setAuthor("Bunny Moderasyon")
                    .build();
            channel.sendMessage(embed).queue();
        }else{
            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("HATA")
                    .setDescription(this.getUsage())
                    .setColor(Color.RED)
                    .build();
            message.getChannel().sendMessage(embed).queue();
        }
    }
}
