# StanBan
Ban stans from your server automatically.

## Install:

Put the plugin in your server's plugin files.  

## Information:

### Permissions:
`stanban.reload` - gives permisson to reload the plugin's config file, duh!

### Commands:
`/stanbanreload` (alias: `/sbreload`) - reloads the plugin's config file, duh!

### Config:
The `config.yml` is pretty self-explanatory but here is a basic rundown:

*Using `%player%` throughout the config will replace it with the banned players name!*  
```yml
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