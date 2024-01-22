
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import pl.chatApi.Controlers.ChatMessageController;
import pl.chatApi.Models.ChatMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ChatMessageControllerTest {


    @InjectMocks
    private ChatMessageController chatMessageController;

    @Test
    public void test_Get_Message() {
        ChatMessage chatMessage = new ChatMessage("1", "Test Message");

        ChatMessage result = chatMessageController.get(chatMessage);

        assertEquals(chatMessage, result);
    }
}
