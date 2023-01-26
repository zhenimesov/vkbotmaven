package kz.tamoha.vkbotmaven.gson;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.users.Get;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.gson.parser.modules.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetUserExample extends LongPollBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserExample.class);
    private static final String USER_ID = "12312313";
    public static void main(String[] args) {
        try {
            GetUserExample example = new GetUserExample();
            example.getUser();
//            example.getUserAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }
    Message message = new Message();
    public void getUser() throws VkApiException {
        Get.ResponseBody responseBody = vk.users.get()
                .setUserIds()
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }


    public void getUserAsync() {
        CompletableFuture<Get.ResponseBody> future = vk.users.get()
                .setUserIds()
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }



    @Override
    public String getAccessToken() {
        return "vk1.a.vdm7bY8GzzfO59krykZAC3n6aaH7ZdE37hOVsTTe2Dh6HvJnmrTyOY2mMJgeQ1Ex477VwGm05QGl0uDMPvBEXA_0FIKJuieomCjcP0faZqgIrS_Wvc87Q562K3wSr2mMHKw6svYqcLo5gE7cSUdU7Sj2zdqfZFLxyV9Ohc3jOMoJYniAKU7vc4h66xgVBzY3wkqxIvJMf4NyGTdpP4OnGQ";
    }
}


