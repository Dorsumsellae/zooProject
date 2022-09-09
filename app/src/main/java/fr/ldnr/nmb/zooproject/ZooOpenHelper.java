package fr.ldnr.nmb.zooproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ZooOpenHelper extends SQLiteOpenHelper {

    public ZooOpenHelper(@Nullable Context context) {
        super(context, "zoo2.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE species (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "age_max INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE box (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "surface INTEGER NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE animals (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "age INTEGER NOT NULL, " +
                "date_arrive DATE NOT NULL," +
                "specie_id INTEGER NOT NULL, " +
                "box_id INTEGER NOT NULL, " +
                "FOREIGN KEY(specie_id)REFERENCES specie(id), FOREIGN KEY(box_id)REFERENCES box(id))");

        String insertion = "INSERT INTO box (name, surface) VALUES (?, ?)";
        sqLiteDatabase.execSQL(insertion, new Object[]{"Box 1", 1360});
        sqLiteDatabase.execSQL(insertion, new Object[]{"Box 2", 201331});
        sqLiteDatabase.execSQL(insertion, new Object[]{"Box 3", 3013});
        sqLiteDatabase.execSQL(insertion, new Object[]{"Box 4", 401});
        sqLiteDatabase.execSQL(insertion, new Object[]{"Box 5", 50131});

        insertion = "INSERT INTO species (name, age_max) VALUES (?, ?)";
        sqLiteDatabase.execSQL(insertion, new Object[]{"Lion", 20});
        sqLiteDatabase.execSQL(insertion, new Object[]{"Tigre", 25});
        sqLiteDatabase.execSQL(insertion, new Object[]{"Zèbre", 30});
        sqLiteDatabase.execSQL(insertion, new Object[]{"Girafe", 35});
        sqLiteDatabase.execSQL(insertion, new Object[]{"Panthère", 40});

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    //function to insert a specie in database
    public void insertSpecie(String name, Integer ageMax){
        SQLiteDatabase db = getWritableDatabase();
        String insertion = "INSERT INTO species (name, age_max) VALUES (?,?)";
        db.execSQL(insertion, new Object[]{name, ageMax});
        db.close();
    }

    //function to update a specie in database
    public void updateSpecie(int id, String name, int ageMax){
        SQLiteDatabase db = getWritableDatabase();
        String update = "UPDATE species SET name = ?, age_max = ? WHERE id = ?";
        db.execSQL(update, new Object[]{name, ageMax, id});
        db.close();
    }

    //function to delete a specie in database
    public void deleteSpecie(int id){
        SQLiteDatabase db = getWritableDatabase();
        String delete = "DELETE FROM species WHERE id = ?";
        db.execSQL(delete, new Object[]{id});
        db.close();
    }

    //function to read a specie in database
    public void readSpecie(int id){
        SQLiteDatabase db = getReadableDatabase();
        String read = "SELECT * FROM species WHERE id = ?";
        db.rawQuery(read, new String[]{String.valueOf(id)});
        db.close();
    }

    //function to read all species in database
    public List<String> readAllSpecies(){
        SQLiteDatabase db = getReadableDatabase();
        String read = "SELECT * FROM species ORDER BY name";
        Cursor cursor = db.rawQuery(read, null);
        ArrayList<String> species = new ArrayList<>();
        while (cursor.moveToNext()){
            species.add(cursor.getString(0));
        }
        db.close();
        return species;
    }

    //function to insert a box in database
    public void insertBox(String name, int surface){
        SQLiteDatabase db = getWritableDatabase();
        String insertion = "INSERT INTO box (name, surface) VALUES (?,?)";
        db.execSQL(insertion, new Object[]{name, surface});
        db.close();
    }
    //function to update a box in database
    public void updateBox(int id, String name, int surface){
        SQLiteDatabase db = getWritableDatabase();
        String update = "UPDATE box SET name = ?, surface = ? WHERE id = ?";
        db.execSQL(update, new Object[]{name, surface, id});
        db.close();
    }

    //function to insert an animal in database
    public void insertAnimal(String name, int age, String dateArrive, int specieId, int boxId){
        SQLiteDatabase db = getWritableDatabase();
        String insertion = "INSERT INTO animals (name, age, date_arrive, specie_id, box_id) VALUES (?,?,?,?,?)";
        db.execSQL(insertion, new Object[]{name, age, dateArrive, specieId, boxId});
        db.close();
    }
}
