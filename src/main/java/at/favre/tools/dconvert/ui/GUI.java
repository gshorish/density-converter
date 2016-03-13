/*
 * Copyright (C) 2016 Patrick Favre-Bulle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package at.favre.tools.dconvert.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main GUI Class
 */
public class GUI extends Application {
	public void launchApp(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setup(primaryStage, new SerializePreferenceStore());
		primaryStage.show();
	}

	public static GUIController setup(Stage primaryStage, IPreferenceStore store) throws IOException {
		primaryStage.setTitle("Density Converter");

		ResourceBundle bundle = ResourceBundle.getBundle("bundles.strings", Locale.getDefault());

		FXMLLoader loader = new FXMLLoader(GUI.class.getClassLoader().getResource("main.fxml"));
		loader.setResources(bundle);
		Parent root = loader.load();
		GUIController controller = loader.<GUIController>getController();
		controller.onCreate(store, bundle);

		Scene scene = new Scene(root, 570, 820);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("img/density_converter_icon_36.png"));
		return controller;
	}
}
