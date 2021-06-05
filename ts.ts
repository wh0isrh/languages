import { Client, Message } from 'discord.js'
const client: Client = new Client()

client.on('ready', () => {
  console.log("Client online")
})

client.on('message', message: Message => {
  let args: string[] = message.content.slice(prefix.length).trim().split(/\s+/g)
  let command: string = args.shift().toLowerCase()

  if(command === "ping") {
    return message.channel.send("Pong!")
  }
})

client.login('token-here')
