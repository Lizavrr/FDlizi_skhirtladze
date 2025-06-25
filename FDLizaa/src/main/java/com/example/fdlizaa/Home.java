package com.example.fdlizaa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class Home {
    @FXML
    private Button Logout;
    @FXML
    private Button home;
    @FXML
    private Button favouritess;
    @FXML
    private Button orderHistory;
    @FXML
    private Button profileSettings;
    @FXML
    private Button mcdonaldsMenu;
    @FXML
    private Button wendysMenu;
    @FXML
    private Button kfcMenu;
    @FXML
    private Button subwayMenu;
    @FXML
    private Button burgerkingMenu;
    @FXML
    private Button dunkinMenu;
    @FXML
    private Button DominosPizzaMenu;
    @FXML
    private Button MacShaurmaMenu;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ImageView background;
    @FXML
    private ImageView heart1;
    @FXML
    private ImageView heart2;
    @FXML
    private ImageView heart3;
    @FXML
    private ImageView heart4;
    @FXML
    private ImageView heart5;
    @FXML
    private ImageView heart6;
    @FXML
    private ImageView heart7;
    @FXML
    private ImageView heart8;

    private final boolean[] isFavourite = new boolean[8];
    private final String[] restaurantNames = {
            "McDonald's", "Wendys", "KFC", "Subway",
            "BurgerKing", "Dunkin", "DominosPizza", "MacShaurma"
    };
    private final String[] imagePaths = {
            "/McDonalds.png", "/Wendys.png", "/KFC.png", "Subway.png",
            "/BurgerKing.png", "/Dunkin.png", "/DominosPizza.png", "/MacShaurma.png"
    };

    public static final ObservableList<FavouriteItem> favourites = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ImageView[] hearts = {heart1, heart2, heart3, heart4, heart5, heart6, heart7, heart8};

        for (int i = 0; i < hearts.length; i++) {
            final int index = i;
            hearts[i].setOnMouseClicked(event -> {
                if (isFavourite[index]) {
                    hearts[index].setImage(new Image(getClass().getResourceAsStream("/heart.png")));
                    removeFavourite(restaurantNames[index]);
                    isFavourite[index] = false;
                } else {
                    hearts[index].setImage(new Image(getClass().getResourceAsStream("/redheart.png")));
                    addFavourite(restaurantNames[index], imagePaths[index]);
                    isFavourite[index] = true;
                }
            });
        }
    }

    private void addFavourite(String name, String imagePath) {
        boolean exists = favourites.stream()
                .anyMatch(item -> item.getName().equals(name));
        if (!exists) {
            favourites.add(new FavouriteItem(name, imagePath));
        }
    }

    private void removeFavourite(String name) {
        favourites.removeIf(item -> item.getName().equals(name));
    }

    @FXML
    private void mcdonaldsMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/McdonaldsMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void wendysMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WendysMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void kfcMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/kfcMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void subwayMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/subwayMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void burgerkingMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/burgerkingMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void dunkinMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dunkinMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ProfileSettings(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ProfileSettings.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToHistory(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/OrderHistory.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToFavourites(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Favourites.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DominosPizzaMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DominosPizzaMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void MacshaurmaMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MacshaurmaMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Logout(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}
