# StanBan
Ban stans from your server automatically.

## Install:

Put the plugin in your server's plugin files.  

## Information:

### Behaviour:
When a player joins, the plugin reads their username and searhes it for string matches that are set in the config.  
If there is a match:
  * The plugin kicks them
  * A ban is added
  * Message sent in chat
  * If you have commands to be run enabled in config, they will be run
  * Their username is added to `banned.txt` for use later with the `/unbanallstans` command
 

### Permissions:
`stanban.reload` - gives permisson to reload the plugin's config file, duh!
`stanban.unbanall` - gives permisson to run the command `/unbanallstans`.

### Commands:
`/stanbanreload` (alias: `/sbreload`) - reloads the plugin's config file, duh!
`/unbannallstans` - unban all players that the plugin has banned for being a stan (the players are stored in `banned.txt`).

### Config:
The `config.yml` is pretty self-explanatory but here is a basic rundown:

*Using `%player%` throughout the config will replace it with the banned players name!*  
```yml
# StanBan by MrRazamataz. This config file was generated for v1.1.1 but should work on older/newer versions.
# Using %player%, you can represent the username of the player whos being banned.

stanban: 
  names: # here is a list of strings that a username should contain in order to be detected as a stan
    - Dream
    - NotFound
  ban-message: "&cYou have been detected as a Dream or GeorgeNotFound stan!"
  chat-broadcast-message: "[&cStanBan&r] &a&l%player% was detected as a stan and was banned from the server!"
  
actions:
  on-stan-join-commands:
    - "say hello there guys, %player% just got banned for being a stan!"
    - "discord action command or something %player%"
  enabled: false


```

Under `names` you can add as many strings that you would like stan usernames to contain. 
For example, in the above config, the name "TestingNotFound" would be detected as a stan.

`ban-message` is the message that the stan is banned with.  
`chat-broadcast-message` is the message in chat that is broadcast when a stan is banned.

#### Actions:
You can set actions (just console commands atm) that run when a player is banned. You can add as many as you wish in the list.

## Why?

![image](https://media.discordapp.net/attachments/764929646063386624/927464328918097930/BurnInHell.png?width=1202&height=676)


I am trying to help other peoples pain.
