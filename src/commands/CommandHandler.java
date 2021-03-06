package commands;


import com.google.gson.Gson;
import controller.Controller;
import data.Parser;
import jdo.client.SimpleRoom;
import jdo.server.Room;
import jdo.server.User;
import jpa.JpqlCommands;

import java.util.List;

import javax.validation.constraints.Null;


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
        try {
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
                    Room currentRoom = new Room(newRoom, counter);
                    currentRoom.users.add(user);
                    currentRoom.connectedPlayers++;
                    user.setInRoom(true);
                    controller.getRooms().add(currentRoom);
                    updateLobbyList();
                    updateRoomPlayerList(currentRoom);
                    break;

                case PLAYERLEAVE:
                    System.out.println("RECEIVED: PLAYERLEAVE");
                    SimpleRoom simpleRoom = gson.fromJson(command.data, SimpleRoom.class);
                    playerLeave(simpleRoom);
                    if(!deleteRoomIfEmpty()){
                        updateRoomPlayerList(getRoomWithSimpleRoom(simpleRoom));
                    }
                    updateAllPlayerLobbyLists();
//                    updateLobbyList();
                    break;

                case PLAYERJOIN:
                    System.out.println("RECEIVED: PLAYERJOIN");
                    SimpleRoom selectedRoom = gson.fromJson(command.data, SimpleRoom.class);
                    playerJoin(selectedRoom);
                    updateRoomPlayerList(getRoomWithSimpleRoom(selectedRoom));
                    updateLobbyList();
                    break;

                case PLAYERREADY:
                    System.out.println("RECEIVED: PLAYERREADY");
                    playerReady();
                    updateRoomPlayerList(getUsersesRoom(user));
                    break;

                case PLAYERNOTREADY:
                    System.out.println("RECEIVED: PLAYERNOTREADY");
                    playerNotReady();
                    updateRoomPlayerList(getUsersesRoom(user));
                    break;

                case PLAYERSENDMESSAGE:
                    System.out.println("RECEIVED: PLAYERSENDMESSAGE");
                    sendMessageToRoom(getUsersesRoom(user), command);
                    break;

                default:
                    System.out.println("Command Type Could Not Be Resolved");
                    break;

            }
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Command Type is null");
        }
    }

    public void updateAllPlayerLobbyLists(){
        controller.getUserHandler().users.forEach(user -> user.dataHandler.send(commandMaker.makeUpdateLobbyList(controller.getRooms())));
        System.out.println("SENT: UPDATEALLPLAYERLOBBYLISTS");
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
            System.out.println("User.isinroom join: " + user.isInRoom());
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

    public void playerLeave(SimpleRoom room) {
        for (int i = 0; i < controller.getRooms().size(); i++) {
            System.out.println("ROOM: " + room.getId());
            System.out.println("ROOMS CONTROLLER: " + controller.getRooms().get(i).id);
            if (controller.getRooms().get(i).id == room.getId() && user.isInRoom()) {
                System.out.println("Found the selected Room when leaving");
                for (int j = 0; j < controller.getRooms().get(i).connectedPlayers; j++) {
                    System.out.println("ConnectedPlayers.getPort(): " + controller.getRooms().get(i).users.get(j).socket.getPort()
                                        +"User.socket.getPort.1Player: " + user.socket.getPort());
                    if (controller.getRooms().get(i).users.get(j).socket.getPort() == user.socket.getPort()) {
                        System.out.println("Port match is TRUE");
                        controller.getRooms().get(i).users.remove(j);
                        user.setInRoom(false);
                        controller.getRooms().get(i).connectedPlayers--;
                    }
                }
            }
        }
    }

    public void playerReady() {
        user.playerData.setReady(true);
    }

    public void playerNotReady() {
        user.playerData.setReady(false);
    }

    public boolean deleteRoomIfEmpty() {
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
        return shouldRemove;
    }

    public void updateRoomPlayerList(Room room) {
        try {
            for (int i = 0; i < room.users.size(); i++) {
                room.users.get(i).dataHandler.send(commandMaker.makeUpdateRoomPlayerList(room.users));
            }
        } catch (NullPointerException e) {
            System.out.println("Room was not found");
            e.printStackTrace();
        }
    }

    public Room getUsersesRoom(User user) {
        for (int i = 0; i < controller.getRooms().size(); i++) {
            for (int j = 0; j < controller.getRooms().get(i).users.size(); j++) {
                if (controller.getRooms().get(i).users.get(j).socket.getPort() == user.socket.getPort()) {
                    return controller.getRooms().get(i);
                }
            }
        }
        return null;
    }

    public void sendMessageToRoom(Room room, Command command) {
        String newCommand = commandMaker.makeSendMessageCommand(user.nickname + ": " + command.data + "\n");
        for (int i = 0; i < room.users.size(); i++) {
            room.users.get(i).dataHandler.send(newCommand);
        }
    }

    public Room getRoomWithSimpleRoom(SimpleRoom simpleRoom) {
        for (int i = 0; i < controller.getRooms().size(); i++) {
            if (controller.getRooms().get(i).id == simpleRoom.getId()) {
                return controller.getRooms().get(i);
            }
        }
        return null;
    }

    public boolean checkIfPlayersAreReady(User user) {
        boolean flag = true;
        Room room = getUsersesRoom(user);

        for (int i = 0; i < room.users.size(); i++) {
            if (!room.users.get(i).playerData.isReady) {
                flag = false;
            }

        }
        return flag;
    }

    public boolean checkIfNumbersOfPlayersisEnough(User user) {
        Room room = getUsersesRoom(user);
        return room.users.size() > 3;
    }

    public boolean checkIfRoomIsReady(User user) {
        return checkIfNumbersOfPlayersisEnough(user) && checkIfPlayersAreReady(user);
    }
}