package net.support.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.support.Support;

import java.awt.*;
import java.util.Arrays;

public class Kick extends Command{

    public Kick(){
        super("kick", "Herhangi bir kullanıcıyı uzaklaştırmanızı sağlar.", "b!kick (kullanıcı) (sebep)");
    }
    @Override
    public void execute(Member user, Message message, String[] args) {
        if (user.hasPermission(Permission.KICK_MEMBERS)) {
            if (!(args.length < 2)){
                String id1 = args[0].replace("<@!", "").replace(">", "");
                Member member = user.getGuild().getMemberById(id1);

                String[] messages = Arrays.copyOfRange(args, 1, args.length);
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < messages.length; i++) {
                    sb.append(" "+messages[i]);
                }
                user.getGuild().kick(member)
                        .reason(sb.toString())
                        .queue();
                MessageEmbed embed = new EmbedBuilder()
                        .setAuthor(member.getUser().getName())
                        .setDescription("Oyuncu Atıldı.")
                        .setThumbnail(member.getUser().getAvatarUrl())
                        .addField("Atan kişi", user.getUser().getAsMention(), false)
                        .addField("Sebep", sb.toString(), false)
                        .setColor(Color.GREEN)
                        .build();
                message.getChannel().sendMessage(embed).queue();
                user.getGuild().getTextChannelById(Support.getProfile(user.getGuild().getId()).getLog()).sendMessage(embed).queue();
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
                    .setDescription("Bu komutu kullanabilmek için ``KICK_MEMBERS`` yetkisine sahip olmalısın")
                    .setColor(Color.RED)
                    .build();
            message.getChannel().sendMessage(embed).queue();
        }

    }
}
