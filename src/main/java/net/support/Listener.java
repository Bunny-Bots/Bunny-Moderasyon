package net.support;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
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
    public void onGuildMessageDelete(@Nonnull GuildMessageDeleteEvent event){
       /* Message message = event.getChannel().getHistory().getMessageById(event.getMessageIdLong());
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .addField(message.getAuthor().getName(), "Bir mesaj silindi.", false)
                .addField("Mesaj içeriği", message.getContentRaw(),false)
                .setColor(Color.GREEN)
                .setThumbnail(message.getAuthor().getAvatarUrl())
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }*/
    }

    @Override
    public void onMessageUpdate(@Nonnull MessageUpdateEvent event){

        //TODO UPDATE MESSAGE LOG
    }

    @Override
    public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event){
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .addField(event.getMember().getUser().getName(), event.getChannelJoined().getName()+" adlı sesli kanala giriş yaptı.", false)
                .setColor(Color.GREEN)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event){
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .addField(event.getMember().getUser().getName(), event.getChannelJoined().getName()+" adlı sesli kanaldan ayrıldı", false)
                .setAuthor("", event.getMember().getUser().getAvatarUrl())
                .setColor(Color.RED)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onGuildUpdateName(@Nonnull GuildUpdateNameEvent event){
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .setAuthor("Sunucu ismi değiştirildi")
                .setDescription(event.getOldName()+" -> "+event.getNewName())
                .setColor(Color.CYAN)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onCategoryCreate(@Nonnull CategoryCreateEvent event){
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .setDescription("``"+event.getCategory().getName()+"`` adlı yeni kategori oluşturuldu")
                .setColor(Color.ORANGE)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onCategoryDelete(@Nonnull CategoryDeleteEvent event){
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .setDescription("``"+event.getCategory().getName()+"`` adlı kategori silindi.")
                .setColor(Color.BLUE)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onTextChannelCreate(@Nonnull TextChannelCreateEvent event){
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .setDescription("``"+event.getChannel().getName()+"`` adlı yeni yazı kanalı oluşturuldu")
                .setColor(Color.ORANGE)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onTextChannelDelete(@Nonnull TextChannelDeleteEvent event){
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .setDescription("``"+event.getChannel().getName()+"`` adlı yazı kanalı silindi.")
                .setColor(Color.BLUE)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onVoiceChannelCreate(@Nonnull VoiceChannelCreateEvent event){
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .setDescription("``"+event.getChannel().getName()+"`` adlı yeni ses kanalı oluşturuldu")
                .setColor(Color.ORANGE)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onVoiceChannelDelete(VoiceChannelDeleteEvent event){
        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .setDescription("``"+event.getChannel().getName()+"`` adlı ses kanalı silindi.")
                .setColor(Color.BLUE)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
    }
    @Override
    public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event){

        Server server = Support.getProfile(event.getGuild().getId());
        MessageEmbed embed = new EmbedBuilder()
                .addField(event.getMember().getUser().getName(), event.getChannelLeft().getName()+" adlı sesli kanaldan ayrılarak "+event.getChannelJoined().getName()+" kanalına katıldı.", false)
                .setAuthor("", event.getMember().getUser().getAvatarUrl())
                .setColor(Color.YELLOW)
                .build();
        if (!server.getLog().equals("1")){
            event.getGuild().getTextChannelById(server.getLog()).sendMessage(embed).queue();
        }
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
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event){
        Member member = event.getMember();
        Server server = Support.getProfile(event.getGuild().getId());
        int target = server.getTarget();
        int t = target - event.getGuild().getMemberCount();

        if (t <= 0){
           int target1 = Math.round(event.getGuild().getMemberCount() / target) + 1;
           target = target1*2;
            server.setTarget(target);
            Support.saveProfile(server);
        }
        MessageEmbed embed = new EmbedBuilder()
                .addField(member.getUser().getName(), "Aramıza katıldı "+target+" kişi olmamıza "+t+" kişi kaldı.", false)
                .setColor(Color.GREEN)
                .setThumbnail(member.getUser().getAvatarUrl())
                .build();
        if (!server.getJoinch().equals("1")){
            event.getGuild().getTextChannelById(server.getJoinch()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onGuildMemberRoleAdd(@Nonnull GuildMemberRoleAddEvent event){
        for (Role role : event.getRoles()) {
            MessageEmbed embed = new EmbedBuilder()
                    .setFooter("Bunny", event.getMember().getUser().getAvatarUrl())
                    .setTitle("Rol verildi")
                    .setDescription(event.getMember().getUser().getName()+" adlı kullanıcıya ``"+role.getName()+"`` rolü verildi")
                    .setColor(Color.CYAN)
                    .build();
            event.getGuild().getTextChannelById(Support.getProfile(event.getGuild().getId()).getLog()).sendMessage(embed).queue();

        }
    }

    @Override
    public void onGuildMemberRoleRemove(@Nonnull GuildMemberRoleRemoveEvent event){
        for (Role role : event.getRoles()) {
            MessageEmbed embed = new EmbedBuilder()
                    .setFooter("Bunny", event.getMember().getUser().getAvatarUrl())
                    .setTitle("Rol alındı")
                    .setDescription(event.getMember().getUser().getName()+" adlı kullanıcıdan ``"+role.getName()+"`` rolü alındı")
                    .setColor(Color.BLUE)
                    .build();
            event.getGuild().getTextChannelById(Support.getProfile(event.getGuild().getId()).getLog()).sendMessage(embed).queue();

        }
    }
    @Override
    public void onGuildMemberLeave(@Nonnull GuildMemberLeaveEvent event){
        Member member = event.getMember();
        Server server = Support.getProfile(event.getGuild().getId());
        int target = server.getTarget();
        int t = target - event.getGuild().getMemberCount();
        MessageEmbed embed = new EmbedBuilder()
                .addField(member.getUser().getName(), "Aramızdan ayrıldı "+target+" kişi olmamıza "+t+" kişi kaldı.", false)
                .setColor(Color.RED)
                .setThumbnail(member.getUser().getAvatarUrl())
                .build();
        if (!server.getJoinch().equals("1")){
            event.getGuild().getTextChannelById(server.getJoinch()).sendMessage(embed).queue();
        }
    }

    @Override
    public void onGuildJoin(@Nonnull GuildJoinEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setColor(Color.ORANGE)
                .addField("Merhaba", "Beni sunucunuza eklediğiniz için teşekkürler. Sunucunuzla beraber artık "+Support.getJda().getGuilds().size()+" sunucuda çalışıyorum.", false)
                .addField("Kurulum", "https://youtu.be/widz4PK9KUE", false)
                .addField("Web sitemiz", "https://bunny.team",false)
                .build();
        event.getGuild().getTextChannels().get(0).sendMessage(embed).queue();
        Support.saveProfile(new Server(event.getGuild().getId(), "1", "b!mesaj-ayarla <mesaj> ile bu mesajı düzenleyiniz.", "Bunny Moderasyon", "1", "1", "1", 1));
    }

    public void onConnect(GenericEvent event) {
    }
}
