package Controller.DataBaseControllers;

import Controller.Utils;
import Model.User;
import View.Printer.RegisterProfilePrinter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;

public class UserDataBaseController extends DataBaseController {

    public static void createUser(User user) {

        String path = Utils.getUserFileNameByUsername(user.getUsername());

        if (!checkCreatingUserErrors(user, path)) {
            return;
        }

        File file = new File(path);
        try {
            file.createNewFile();
            RegisterProfilePrinter.printSuccessfulRegister();

            writeDataInfile(makeObjectJson(user), path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkCreatingUserErrors(User user, String userPath) {

        if (isThisFileExist(userPath)) {
            RegisterProfilePrinter.printRepetitiousUsername(user.getUsername());
            return false;
        }
        if (isNickNameRepetitious(user.getNickname())) {
            RegisterProfilePrinter.printRepetitousNickName(user.getNickname());
            return false;
        }
        return true;
    }

    public static boolean isNickNameRepetitious(String nickname) {

        String data;

        for (File userData : getFilesOfOneFolder(getUsersPath())) {
            data = readDataFromFile(userData);
            JsonObject jsonObjectAlt = JsonParser.parseString(data).getAsJsonObject();
            JsonElement nicknameJson = jsonObjectAlt.get("nickname");
            if (nicknameJson.toString().equals("\"" + nickname + "\"")) {
                return true;
            }
        }
        return false;

    }

    public static User getUserByUsername(String username) {

        String path = Utils.getUserFileNameByUsername(username);
        return User.getUserByGson(path);
    }

    public static boolean doesUserExistWithThisUsername(String username) {

        String path = Utils.getUserFileNameByUsername(username);
        return isThisFileExist(path);
    }

    public static boolean isUserPasswordCorrect(String username, String password) {
        String path = Utils.getUserFileNameByUsername(username);
        User user = User.getUserByGson(path);
        return user.isPasswordCorrect(password);
    }

    public static void changePassword(User user, String newPassword) {
        String path = Utils.getUserFileNameByUsername(user.getUsername());
        user.setPassword(newPassword);
        File file = new File(path);
        try {
            file.createNewFile();
            writeDataInfile(makeObjectJson(user), path);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void changeNickname(User user, String newNickname) {
        String path = Utils.getUserFileNameByUsername(user.getUsername());
        user.setNickname(newNickname);
        File file = new File(path);
        try {
            file.createNewFile();
            writeDataInfile(makeObjectJson(user), path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}