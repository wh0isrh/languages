public class Events extends ListenerAdapter implements Listener {
    public Main plugin;
    public JDA jda;
    public Events(Main main) {
        this.plugin = main;
        startBot();
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
        jda.addEventListener(this);
    }
    private void startBot() {
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(plugin.getConfig().getString("Bot.Token")).buildBlocking();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e){
        String message = e.getMessage();
        TextChannel textChannel = jda.getTextChannelsByName("general-linked",true).get(0);
        textChannel.sendMessage("**"+e.getPlayer().getName()+":** "+message).queue();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(e.getAuthor().isBot() || e.getAuthor().isFake() || e.isWebhookMessage())return;
        String message = e.getMessage().getContentRaw();
        User user = e.getAuthor();
        Bukkit.broadcastMessage("§9§l"+user.getName()+"#"+user.getDiscriminator()+": §a"+message);
    }
}
