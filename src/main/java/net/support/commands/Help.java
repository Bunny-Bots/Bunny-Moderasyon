package net.support.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.support.CommandMap;

import java.awt.*;

public class Help extends Command{
    
    public Help(){
        super("yardım", "Bunny moderasyon botunda bulunan tüm komutları görmenizi sağlar.", "b!yardim");
    }
    @Override
    public void execute(Member user, Message message, String[] args) {
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(Color.YELLOW);
        CommandMap.getCommands().forEach((name, cmd)->{
            embed.addField(cmd.getUsage(), cmd.getDescription(), false);
        });
        embed.addField("Davet linki", "https://bit.ly/davetbunny", false);
         MessageEmbed emb = embed.build();
         message.getChannel().sendMessage(emb).queue();
    }
}
