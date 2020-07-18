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

public class SetJoinChannel extends Command{

    public SetJoinChannel(){
        super("hosgeldin", "Hoşgeldin kanalını ayarlamanızı sağlar.", "b!hosgeldin (kanal) (hedef)");
    }
    @Override
    public void execute(Member user, Message message, String[] args) {
        if (user.hasPermission(Permission.ADMINISTRATOR)) {
            if (!(args.length < 2)) {
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
                if (user.getGuild().getTextChannelById(args[0].replace("<#", "").replace(">", "")) == null){
                    MessageEmbed embed = new EmbedBuilder()
                            .setAuthor("HATA")
                            .setDescription("Böyle bir kanal bulunamadı.")
                            .setColor(Color.RED)
                            .build();
                    message.getChannel().sendMessage(embed).queue();
                    return;
                }
                TextChannel channel = user.getGuild().getTextChannelById(args[0].replace("<#", "").replace(">", ""));

                MessageEmbed embed = new EmbedBuilder()
                        .setAuthor("Başarılı")
                        .setDescription("Hoşgeldin kanalı ayarlandı.")
                        .setColor(Color.GREEN)
                        .build();
                message.getChannel().sendMessage(embed).queue();
                Server server = Support.getProfile(user.getGuild().getId());
                server.setJoinch(channel.getId());
                server.setTarget(num);
                Support.saveProfile(server);
            }else{
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
                    .setDescription("Bu komutu kullanabilmek için ``ADMINISTRATOR`` yetkisine sahip olmalısın")
                    .setColor(Color.RED)
                    .build();
            message.getChannel().sendMessage(embed).queue();
        }
    }
}
