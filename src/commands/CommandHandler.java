package commands;


import com.google.gson.Gson;
import controller.Controller;
import data.Parser;
import jdo.client.SimpleRoom;
import jdo.server.Room;
import jdo.server.User;
import jpa.JpqlCommands;

import java.util.List;

/**
 * Created by Meister on 2017-02-06.
 */
public class CommandHandler {
    Controller controller;
    JpqlCommands jpqlCommands = new JpqlCommands();
    Gson gson = new Gson();
    int counter = 0;

    User user;
    Parser parser;
    CommandMaker commandMaker;
    SimpleRoom testRoom;


    public CommandHandler(User user, Controller controller) {
        this.controller = controller;
        this.user = user;
        parser = new Parser();
        commandMaker = new CommandMaker();
    }

    public void handle(String message) {
        Command command = parser.parse(message);
        switch (command.type) {
            case SETNICK:
                System.out.println("RECEIVED: SETNICK");
                setNickname(command.data);
                updateNickname(command.data);
                break;

            case GETCATEGORIES:
                System.out.println("RECEIVED: GETCATEGORIES");
                List categoryList = jpqlCommands.getCategoryNames();
                System.out.println("CATEGORIES: " + categoryList);
                break;

            case GETLOBBYLIST:
                System.out.println("RECEIVED: GETLOBBYLIST");
                updateLobbyList();
                break;

            case GETROOM:
                System.out.println("RECEIVED: GETROOM");
                int roomId = Integer.parseInt(command.data);
                for (int i = 0; i < controller.getRooms().size(); i++) {
                    if (controller.getRooms().get(i).id == roomId) {
                        user.dataHandler.send(commandMaker.makeUpdateRoomCommand(controller.getRooms().get(i)));
                    }
                }
                break;

            case CREATEROOM:
                counter++;
                SimpleRoom newRoom = gson.fromJson(command.data, SimpleRoom.class);
                updateRoomName(gson.fromJson(command.data, SimpleRoom.class));
                Room currentRoom = new Room(newRoom,counter);
                currentRoom.users.add(user);
                currentRoom.connectedPlayers++;
                user.setInRoom(true);
                controller.getRooms().add(currentRoom);
                updateLobbyList();
                break;

            case PLAYERLEAVE:
                System.out.println("RECEIVED: PLAYERLEAVE");
                playerLeave(gson.fromJson(command.data, SimpleRoom.class));
                deleteRoomIfEmpty();
                updateLobbyList();
                break;

            case PLAYERJOIN:
                System.out.println("RECEIVED: PLAYERJOIN");
                SimpleRoom selectedRoom = gson.fromJson(command.data, SimpleRoom.class);
                playerJoin(selectedRoom);
                break;

            case PLAYERREADY:
                System.out.println("RECEIVED: PLAYERREADY");
                playerReady();
                break;

            case PLAYERNOTREADY:
                System.out.println("RECIEVED: PLAYERNOTREADY");
                playerNotReady();

            default:
                System.out.println("Command Type Could Not Be Resolved");
                break;

        }
    }

    public void updateLobbyList() {
        user.dataHandler.send(commandMaker.makeUpdateLobbyList(controller.getRooms()));
        System.out.println("SENT: UPDATELOBBYLIST");
    }

    public void setNickname(String nick) {
        user.setNickname(nick);
        System.out.println(user.socket.getPort() + " = " + user.nickname);
    }

    public void updateNickname(String nick) {
        user.dataHandler.send(commandMaker.makeUpdateNickCommand(nick));
        System.out.println("SENT: UPDATENICKNAME");
    }

    public void updateRoom(Room room) {
        user.dataHandler.send(commandMaker.makeUpdateRoomCommand(room));
        System.out.println("SENT: UPDATEROOM");
    }

    public void updateRoomName(SimpleRoom simpleRoom) {
        user.dataHandler.send(commandMaker.makeUpdateRoomNameCommand(simpleRoom.getName()));
        System.out.println("SENT: UPDATEROOMNAME");
    }

    public void playerJoin(SimpleRoom simpleRoom) {
        updateRoomName(simpleRoom);
        for (int i = 0; i < controller.getRooms().size(); i++) {

            if (controller.getRooms().get(i).id == simpleRoom.getId() && !user.isInRoom()) {
                controller.getRooms().get(i).users.add(user);
                user.setInRoom(true);
                controller.getRooms().get(i).connectedPlayers++;
                System.out.println("USER CONNECTED: " + user.isInRoom());
                System.out.println("USERS ROOM: " + simpleRoom.getName());
                break;
            }

        }
    }

    public void playerReady() {
        user.playerData.setReady(true);
    }

    public void playerNotReady() {
        user.playerData.setReady(false);
    }

    public void playerLeave(SimpleRoom room) {
        for (int i = 0; i < controller.getRooms().size(); i++) {
            System.out.println("ROOM: " + room.getId());
            System.out.println("ROOMS CONTROLLER: " + controller.getRooms().get(i).id);
            if (controller.getRooms().get(i).id == room.getId() && user.isInRoom()) {
                for (int j = 0; j < controller.getRooms().get(i).connectedPlayers; j++) {
                    if (controller.getRooms().get(i).users.get(j).socket.getPort() == user.socket.getPort()) {
                        controller.getRooms().get(i).users.remove(j);
                        user.setInRoom(false);
                        controller.getRooms().get(i).connectedPlayers--;
                    }
                }
            }
        }
    }

    public void deleteRoomIfEmpty() {
        int indexOnEmptyRoom = 0;
        boolean shouldRemove = false;
        for (int i = 0; i < controller.getRooms().size(); i++) {
            if (controller.getRooms().get(i).connectedPlayers == 0) {
                indexOnEmptyRoom = i;
                shouldRemove = true;
                break;
            }
        }
        if (shouldRemove) {
            controller.getRooms().remove(indexOnEmptyRoom);
        }
    }
}