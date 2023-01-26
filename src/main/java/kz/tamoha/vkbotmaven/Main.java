package kz.tamoha.vkbotmaven;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.exceptions.VkApiResponseException;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.Anecdote;
import kz.tamoha.vkbotmaven.gson.GetUserExample;
import kz.tamoha.vkbotmaven.gson.parser.JsonWriterMy;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.io.IoBuilder;

import java.util.*;


public class Main extends LongPollBot {
    static {
        JsonWriterMy jwm=new JsonWriterMy();
        jwm.createPeopleObject();
    }

    static {
        /* for logging */
        System.setErr(IoBuilder.forLogger().setLevel(Level.ERROR).buildPrintStream());
        System.setOut(IoBuilder.forLogger().setLevel(Level.INFO).buildPrintStream());
    }

    @Override
    public void onMessageNew(MessageNew messageNew) {
        try {
            Message message=messageNew.getMessage();

            System.out.printf("[*] New message: %s | %s | %s - %s",
                    message.getPeerId(), message.getFromId(), message.getText(), message);

            GetUserExample gu = new GetUserExample();
            String text=message.getText();
            JsonWriterMy jwm=new JsonWriterMy();
            jwm.createPeopleObject();


//Команды
            if (text.length() > 0 && text.charAt(0) == '!') {
                String[] params=text.substring(1).split(" ");
                String command=params[0].toLowerCase();
                if (command != null) {
                    String[] args=Arrays.copyOfRange(params, 1, params.length);
                    //Команды бота
                    switch (command) {
                        case "помощь": {
                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage("Все команды пишутся с префиксом : !\n" +
                                            "\n" +
                                            "grname :  Изменить имя беседы\n" +
                                            "кик :     Кикает человека\n" +
                                            "анекдот : Случайный анекдот !")
                                    .execute();
                        }
                        break;
                        case "анекдот": {
                            Anecdote anecdote=new Anecdote();
                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage(anecdote.getAnecdote())
                                    .execute();
                            break;
                        }
                        case "кик": {
                            kickCommand(message);
                        }
                        break;

                        case "ботинфа": {

                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage("Добро пожаловать)\n" +
                                            "Бот был создан в развлекательном характере\n" +
                                            "Создатель бота : vk.com/tamoha \n")
                                    .execute();

                        }
                        break;
                        case "grname": {

                            vk.messages.editChat()
                                    .setChatId(message.getPeerId() - 2000000000)
                                    .setTitle(message.getText().substring(3))
                                    .execute();
                            String n="Имя беседы успешно было изменено на :\n";
                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage(n + message.getText().substring(3))
                                    .execute();

                        }
                        break;
                        case "уебать":{
                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage("@id" + message.getFromId()+ "уебал игрока :" + "@id" )
                                    .execute();
                        }

                        // Если не подошла, другая или иная команда:
                        default:
                            try {
                                vk.messages.send()
                                        .setPeerId(message.getPeerId())
                                        .setMessage("Что то пошло не так, пожалуйтса введите !помощь")
                                        .execute();
                            } catch (VkApiException e) {
                                e.printStackTrace();
                                System.out.println("Что то пошло не так в комадне default");
                            }
                    }
                }
            }
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }

    private List<Message> replyMessages(Message message) {
        List<Message> messages=message.getFwdMessages();
        if (message.getReplyMessage() != null) messages.add(0, message.getReplyMessage());

        return messages;
    }


    private void kickCommand(Message message) throws VkApiException {
        List<Message> replyMessages=replyMessages(message);

        if (replyMessages.size() == 0) {
            vk.messages.send()
                    .setPeerId(message.getPeerId())
                    .setMessage("Перешлите это сообщения, того кого хотите кикнуть")
                    .execute();
            return;
        }

        StringBuilder sb=new StringBuilder("Пользовател" + (replyMessages.size() == 1 ? "ь" : "и"));
        for (Message replyMessage : replyMessages) {
            int fromId=replyMessage.getFromId();
            sb.append("\n")
                    .append(fromId > 0 ? "@id" + fromId : "@club" + (fromId / -1));
            try {
                vk.messages.removeChatUser()
                        .setChatId(message.getPeerId() - 2_000_000_000)
                        .setMemberId(fromId)
                        .execute();
            } catch (VkApiResponseException e) {
                if (e.getMessage().contains("User not found in chat")) {
                    sb.append(" - error: данного пользователя нет в чате");
                }
            }
        }
        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage(sb
                        + (replyMessages.size() == 1 ?
                        "\nБыл исключен из чата"
                        : "\nБыли исключены из чата"))
                .execute();
    }


    @Override
    public String getAccessToken() {
        return "vk1.a.vdm7bY8GzzfO59krykZAC3n6aaH7ZdE37hOVsTTe2Dh6HvJnmrTyOY2mMJgeQ1Ex477VwGm05QGl0uDMPvBEXA_0FIKJuieomCjcP0faZqgIrS_Wvc87Q562K3wSr2mMHKw6svYqcLo5gE7cSUdU7Sj2zdqfZFLxyV9Ohc3jOMoJYniAKU7vc4h66xgVBzY3wkqxIvJMf4NyGTdpP4OnGQ";
    }

    public static void main(String[] args) throws VkApiException {
        new Main().startPolling();


    }
}