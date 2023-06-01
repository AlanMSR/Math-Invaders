package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager implements Serializable {
    private static final String FILE_PATH = "scores.ser";

    public static void saveScores(List<Integer> scores) {
        List<Integer> existingScores = loadScores();
        existingScores.addAll(scores);

        try {
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(existingScores);
            out.close();
            fileOut.close();
            System.out.println("Scores saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> loadScores() {
        List<Integer> scores = new ArrayList<>();
        FileHandle fileHandle = Gdx.files.internal(FILE_PATH);

        if (fileHandle.exists() && fileHandle.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(fileHandle.read())) {
                scores = (List<Integer>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return scores;
    }
}

