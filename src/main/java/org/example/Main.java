package org.example;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;
import com.google.gson.Gson;
import org.example.command.Anecdote;
import org.example.command.RPcommand.enumOfRp;
import org.example.gson.parser.JsonWriterMy;
import org.example.gson.parser.modules.People;

import java.util.*;


public class Main extends LongPollBot {

    @Override
    public void onMessageNew(MessageNew messageNew) {
        Message message = messageNew.getMessage();
        String text = message.getText();
        enumOfRp hug = enumOfRp.обнять;
        enumOfRp kickoff = enumOfRp.уебать;
        enumOfRp kiss = enumOfRp.поцеловать;
        JsonWriterMy jwm = new JsonWriterMy();
        jwm.createPeopleObject();



//Команды
        if (text.length() > 0 && text.charAt(0) == '!') {
            String[] params = text.substring(1).split(" ");
            String command = params[0].toLowerCase();
            if (command != null) {
                String[] args = Arrays.copyOfRange(params, 1, params.length);
                //Команды бота
                switch (command) {
                    case "помощь": {
                        try {
                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage("Все команды пишутся с префиксом : !\n" +
                                            "\n" +
                                            "grname :  Изменить имя беседы\n" +
                                            "кик :     Кикает человека\n" +
                                            "анекдот : Случайный анекдот !")
                                    .execute();
                        } catch (VkApiException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                    case "анекдот": {
                        try {
                            Anecdote anecdote = new Anecdote();
                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage(anecdote.getAnecdote()).
                                    execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case "кик": {
                        try {
//                            vk.messages.removeChatUser()
//                                    .setChatId(message.getPeerId()-2000000000)
//                                    .setUserId(message.getReplyMessage().getFromId())
//                                    .execute();
                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage("Команда кик отключена!")
                                    .execute();
                        } catch (VkApiException e) {
                            e.printStackTrace();
                            vk.messages.send().setMessage("Перешлите это сообщения, того кого хотите кикнуть");
                        }
                    }
                    break;

                    case "ботинфа": {
                        try {
                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage("Добро пожаловать)\n"+
                                            "Бот был создан в развлекательном характере\n" +
                                            "Создатель бота : vk.com/tamoha \n" +
                                            "Все права защищены☻")
                                    .execute();
                        } catch (VkApiException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                    case "grname": {
                        try {
                            vk.messages.editChat()
                                    .setChatId(message.getPeerId() - 2000000000)
                                    .setTitle(message.getText().substring(3))
                                    .execute();
                            String n = "Имя беседы успешно было изменено на :\n";
                            vk.messages.send()
                                    .setPeerId(message.getPeerId())
                                    .setMessage(n + message.getText().substring(3))
                                    .execute();
                        } catch (VkApiException e) {
                            vk.messages.send().setMessage("Нет прав на изменения имя беседы");
                            e.printStackTrace();
                        }
                    }
                    break;
                    ///РП КОМАНДЫ
                    case "уебать":{
                        vk.messages.send().setPeerId(message.getPeerId())
                            .setMessage( vk.utils.resolveScreenName() + kickoff.getDoIt());

                    }break;
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
    }
    @Override
    public String getAccessToken() {
            return "vk1.a.vdm7bY8GzzfO59krykZAC3n6aaH7ZdE37hOVsTTe2Dh6HvJnmrTyOY2mMJgeQ1Ex477VwGm05QGl0uDMPvBEXA_0FIKJuieomCjcP0faZqgIrS_Wvc87Q562K3wSr2mMHKw6svYqcLo5gE7cSUdU7Sj2zdqfZFLxyV9Ohc3jOMoJYniAKU7vc4h66xgVBzY3wkqxIvJMf4NyGTdpP4OnGQ";
    }

    public static void main(String[] args) throws VkApiException {

        new Main().startPolling();



    }
}