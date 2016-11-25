package com.ayesha.hp.traveljournal;

/**
 * Created by Shaoor Munir on 19-Nov-16.
 */
public class DiaryEntry {
    int id;
    String title;
    String description;
    String coverPath;
    String entryDate;
    double latitude;
    double longitude;

    DiaryEntry(int id, String title, String description, String coverPath, String entryDate, double latitude, double longitude) {
        this.id = id;
        this.coverPath = coverPath;
        this.title = title;
        this.description = description;
        this.entryDate = entryDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.coverPath = coverPath;
    }
}
