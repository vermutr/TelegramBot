import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends TelegramLongPollingBot {


    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String getBotUsername() {
        return "FilmsOfferBot";
    }

    @Override
    public String getBotToken() {
        return "1638279929:AAEQAtefVHgmcnkWo3rVj0RzW3TcqWuuQiI";
    }



    public void sendMsg(Message message) {
        SendMessage sendMessage=new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        MovieGetter movieGetter=new MovieGetter();
        try {
            List<String> filmLinks = movieGetter.getData();
            int size = filmLinks.size();
            int item = new Random().nextInt(size);
            String filmLink=filmLinks.get(item);
            sendMessage.setText(filmLink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            setButton(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void setButton(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows=new ArrayList<>();
        KeyboardRow keyboardButtons= new KeyboardRow();
        keyboardButtons.add(new KeyboardButton("/RandomLinkFilm"));

        keyboardRows.add(keyboardButtons);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message= update.getMessage();
        if(message!=null && message.hasText()){
            if ("/RandomLinkFilm".equals(message.getText())) {
                sendMsg(message);
            }
        }
    }
}
