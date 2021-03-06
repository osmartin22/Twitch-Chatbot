package ozmar.database.dao;

import ozmar.database.dao.interfaces.ChatDaoInterface;
import ozmar.database.tables.interfaces.ChatTableInterface;
import ozmar.user.ChatUser;
import twitch4j_packages.helix.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ChatDao implements ChatDaoInterface {

    private ChatTableInterface chatTable;

    public ChatDao(ChatTableInterface chatTable) {
        this.chatTable = chatTable;
    }

    @Override
    public long getUserId(@Nonnull String userName) {
        return chatTable.getUserId(userName);
    }

    @Override
    public void addUserList(@Nonnull List<User> userList) {
        if (!userList.isEmpty()) {
            chatTable.addUserList(userList);
        }
    }

    @Override
    public void checkIfNamesExist(@Nonnull List<String> list) {
        chatTable.checkIfNameExists(list);
    }

    @Override
    public void updatePoints(@Nonnull Map<Long, ChatUser> map) {
        if (!map.isEmpty()) {
            chatTable.updatePoints(map);
        }
    }

    @Override
    public int getMessageCount(long userId) {
        return chatTable.getMessageCount(userId);
    }

    @Override
    public int getMessageCount(@Nonnull String userName) {
        return chatTable.getMessageCount(userName);
    }

    @Override
    public int getPoints(long userId) {
        return chatTable.getPoints(userId);
    }

    @Override
    public int getPoints(@Nonnull String userName) {
        return chatTable.getPoints(userName);
    }

    @Nullable
    @Override
    public String getPartnerById(long userId) {
        return chatTable.getPartner(userId);
    }

    @Override
    public void updatePartner(long userId, @Nonnull String newPartner) {
        chatTable.updatePartner(userId, newPartner);
    }
}
